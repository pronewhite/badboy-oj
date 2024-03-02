package com.yupi.badboyOJ.judge.model;

import com.yupi.badboyOJ.model.dto.question.JudgeCase;
import com.yupi.badboyOJ.model.entity.Question;
import com.yupi.badboyOJ.model.entity.QuestionSubmit;
import lombok.Data;

import java.util.List;

/**
 * 判题上下文
 */
@Data
public class JudgeContext {

    /**
     * 代码提交信息
     */
    private QuestionSubmit questionSubmit;

    /**
     * 代码沙箱执行结果
     */
    private ExecuteCodeRespose executeCodeRespose;

    /**
     * 输入示例
     */
    private List<String> inputList;

    /**
     * 判题示例列表
     */
    private List<JudgeCase> judgeCaseList;

    /**
     *题目信息
     */
    private Question question;



}
