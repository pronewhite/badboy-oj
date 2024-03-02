package com.yupi.badboyOJ.judge.strategy;

import com.yupi.badboyOJ.judge.model.JudgeContext;
import com.yupi.badboyOJ.model.dto.questionsubmit.JudgeInfo;
import com.yupi.badboyOJ.model.entity.QuestionSubmit;

/**
 * 简化判题调用
 */
public class JudgeManage {

    /**
     * 判题
     * @param judgeContext
     * @return
     */
    public JudgeInfo doJudge(JudgeContext judgeContext){
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        if(language.equals("java")){
            judgeStrategy = new JavaJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);
    }
}
