package com.yupi.badboyOJ.judge.model;

import com.yupi.badboyOJ.model.dto.questionsubmit.JudgeInfo;
import lombok.Data;

import java.util.List;

/**
 * 运行代码返回信息
 */
@Data
public class ExecuteCodeRespose {

    /**
     * 输出
     */
    private List<String> outputList;
    /**
     * 代码运行相关信息
     */
    private JudgeInfo judgeInfo;

    /**
     * 接口信息
     */
    private String message;
}
