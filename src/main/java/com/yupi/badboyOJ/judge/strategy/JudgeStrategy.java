package com.yupi.badboyOJ.judge.strategy;

import com.yupi.badboyOJ.judge.model.JudgeContext;
import com.yupi.badboyOJ.model.dto.questionsubmit.JudgeInfo;

/**
 * 判题策略
 */
public interface JudgeStrategy {

    /**
     * 判题
     * @param judgeContext
     * @return
     */
    JudgeInfo doJudge(JudgeContext judgeContext);
}
