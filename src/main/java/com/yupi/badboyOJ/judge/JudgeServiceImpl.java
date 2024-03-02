package com.yupi.badboyOJ.judge;

import cn.hutool.json.JSONUtil;
import com.yupi.badboyOJ.common.ErrorCode;
import com.yupi.badboyOJ.exception.BusinessException;
import com.yupi.badboyOJ.judge.codesand.CodeSandBox;
import com.yupi.badboyOJ.judge.factory.JudgeStaticFactory;
import com.yupi.badboyOJ.judge.model.ExecuteCodeRequest;
import com.yupi.badboyOJ.judge.model.ExecuteCodeRespose;
import com.yupi.badboyOJ.judge.model.JudgeContext;
import com.yupi.badboyOJ.judge.proxy.CodeSandProxy;
import com.yupi.badboyOJ.judge.strategy.JudgeManage;
import com.yupi.badboyOJ.model.dto.question.JudgeCase;
import com.yupi.badboyOJ.model.dto.questionsubmit.JudgeInfo;
import com.yupi.badboyOJ.model.entity.Question;
import com.yupi.badboyOJ.model.entity.QuestionSubmit;
import com.yupi.badboyOJ.model.enums.QuestionSubmitStatusEnum;
import com.yupi.badboyOJ.service.QuestionService;
import com.yupi.badboyOJ.service.QuestionSubmitService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JudgeServiceImpl implements JudgeService{

    @Value("${codesandbox.type:example}")
    private String type;

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Resource
    private QuestionService questionService;
    @Override
    public QuestionSubmit doJudge(Long questionSubmitId) {
        //获取代码提交信息
        QuestionSubmit questionSubmit = questionSubmitService.getById(questionSubmitId);
        if(questionSubmit == null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR,"提交数据不存在");
        }
        Question question = questionService.getById(questionSubmit.getQuestionId());
        if(question == null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR,"题目数据不存在");
        }
        //根据题目状态记性判断是否进行代码沙箱
        if(!questionSubmit.getStatus().equals(QuestionSubmitStatusEnum.WAITING.getValue())){
            throw new BusinessException(ErrorCode.OPERATION_ERROR,"正在判题，请勿重复提交");
        }

        //修改题目状态
        QuestionSubmit questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.RUNNING.getValue());
        boolean update = questionSubmitService.updateById(questionSubmitUpdate);
        if(!update){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"数据信息更新失败");
        }

        String language = questionSubmit.getLanguage();
        String code = questionSubmit.getCode();

        String judgeCase = question.getJudgeCase();
        //调用代码沙箱
        CodeSandBox codeSandBox = JudgeStaticFactory.getCodeSandBoxByType(type);
        ExecuteCodeRequest executeCodeRequest = new ExecuteCodeRequest();
        List<JudgeCase> judgeCaseList = JSONUtil.toList(judgeCase, JudgeCase.class);
        List<String> inputList = judgeCaseList.stream().map(JudgeCase::getInputCase).collect(Collectors.toList());
        executeCodeRequest.setInputList(inputList);
        executeCodeRequest.setCode(code);
        executeCodeRequest.setLanguage(language);
        //使用代理类
        ExecuteCodeRespose executeCodeRespose = new CodeSandProxy(codeSandBox).executeCode(executeCodeRequest);
        JudgeContext judgeContext = new JudgeContext();
        judgeContext.setQuestionSubmit(questionSubmit);
        judgeContext.setExecuteCodeRespose(executeCodeRespose);
        judgeContext.setInputList(inputList);
        judgeContext.setJudgeCaseList(judgeCaseList);
        judgeContext.setQuestion(question);
        JudgeManage judgeManage = new JudgeManage();
        JudgeInfo judgeInfoResponse = judgeManage.doJudge(judgeContext);
        //最后更新数据库数据
        questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setJudgeInfo(JSONUtil.toJsonStr(judgeInfoResponse));
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());
        questionSubmitUpdate.setId(questionSubmitId);
        boolean up = questionSubmitService.updateById(questionSubmitUpdate);
        if(!up){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"数据更新失败");
        }
        QuestionSubmit questionSubmit1 = questionSubmitService.getById(questionSubmitId);
        return questionSubmit1;
    }
}
