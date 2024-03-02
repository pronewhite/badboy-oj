package com.yupi.badboyOJ.model.dto.question;

import lombok.Data;

/**
 * 题目用例
 */
@Data
public class JudgeConfig {

    /**
     * 时间限制
     */
    private Long timeLimit;

    /**
     * 堆栈限制
     */
    private Long stackLimit;

    /**
     * 内存限制
     */
    private Long memoryLimit;
}
