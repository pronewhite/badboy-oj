package com.yupi.badboyOJ.judge.strategy;

import cn.hutool.json.JSONUtil;
import com.yupi.badboyOJ.judge.model.ExecuteCodeRespose;
import com.yupi.badboyOJ.judge.model.JudgeContext;
import com.yupi.badboyOJ.model.dto.question.JudgeCase;
import com.yupi.badboyOJ.model.dto.question.JudgeConfig;
import com.yupi.badboyOJ.model.dto.questionsubmit.JudgeInfo;
import com.yupi.badboyOJ.model.entity.Question;
import com.yupi.badboyOJ.model.enums.JudgeInfoMessageEnum;

import java.util.List;


public class JavaJudgeStrategy implements JudgeStrategy{

    /**
     * 执行判题
     * @param judgeContext 判题上下文
     * @return
     */
    @Override
    public JudgeInfo doJudge(JudgeContext judgeContext) {

        ExecuteCodeRespose executeCodeRespose = judgeContext.getExecuteCodeRespose();
        List<String> inputList = judgeContext.getInputList();
        List<JudgeCase> judgeCaseList = judgeContext.getJudgeCaseList();
        Question question = judgeContext.getQuestion();
        JudgeInfo judgeInfo1 = executeCodeRespose.getJudgeInfo();
        JudgeInfo judgeInfoResponse = new JudgeInfo();
        judgeInfoResponse.setTime(judgeInfo1.getTime());
        judgeInfoResponse.setMemory(50L);
        judgeInfoResponse.setMessage(judgeInfo1.getMessage());

        List<String> outputList = executeCodeRespose.getOutputList();
        JudgeInfoMessageEnum judgeInfoMessageEnum = JudgeInfoMessageEnum.WAITING;
        if (outputList.size() != inputList.size()) {
            judgeInfoMessageEnum = JudgeInfoMessageEnum.WRONG_ANSWER;
            judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
            return judgeInfoResponse;
        }
        for (int i = 0; i < outputList.size(); i++) {
            JudgeCase judgeCase1 = judgeCaseList.get(i);
            String answer = outputList.get(i);
            if (!judgeCase1.getOutputCase().equals(answer)) {
                judgeInfoMessageEnum = JudgeInfoMessageEnum.WRONG_ANSWER;
                judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
                return judgeInfoResponse;

            }
        }
        String judgeConfigStr = question.getJudgeConfig();
        JudgeConfig judgeConfig = JSONUtil.toBean(judgeConfigStr, JudgeConfig.class);
        Long timeLimit = judgeConfig.getTimeLimit();
        Long memoryLimit = judgeConfig.getMemoryLimit();
        //对于java,运行时间需要减去多余的时间才是运行时间
        if (judgeInfo1.getTime() - 100L > timeLimit) {
            judgeInfoMessageEnum = JudgeInfoMessageEnum.TIME_LIMIT_EXCEEDED;
            judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
            return judgeInfoResponse;
        }
        if (judgeInfo1.getMemory() > memoryLimit) {
            judgeInfoMessageEnum = JudgeInfoMessageEnum.MEMORY_LIMIT_EXCEEDED;
            judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
            return judgeInfoResponse;
        }
        judgeInfoResponse.setMessage(JudgeInfoMessageEnum.ACCEPTED.getValue());
        return judgeInfoResponse;
    }
}
