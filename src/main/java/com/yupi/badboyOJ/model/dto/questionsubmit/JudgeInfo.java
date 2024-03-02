package com.yupi.badboyOJ.model.dto.questionsubmit;

import lombok.Data;

@Data
public class JudgeInfo {

    /**
     * 程序运行信息
     */
    private String message;

    /**
     * 运行时间（ms)
     */
    private Long time;

    /**
     * 内存(KB)
     */
    private Long memory;
}
