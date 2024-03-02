package com.yupi.badboyOJ.judge;

import com.yupi.badboyOJ.judge.model.ExecuteCodeRespose;
import com.yupi.badboyOJ.model.entity.QuestionSubmit;
import org.springframework.stereotype.Service;

public interface JudgeService {

    QuestionSubmit doJudge(Long questionSubmitId);
}
