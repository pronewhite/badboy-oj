package com.yupi.badboyOJ.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.badboyOJ.model.dto.question.QuestionQueryRequest;
import com.yupi.badboyOJ.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.yupi.badboyOJ.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.yupi.badboyOJ.model.entity.Question;
import com.yupi.badboyOJ.model.entity.QuestionSubmit;
import com.yupi.badboyOJ.model.entity.QuestionSubmit;
import com.yupi.badboyOJ.model.entity.User;
import com.yupi.badboyOJ.model.vo.QuestionSubmitVO;

import javax.servlet.http.HttpServletRequest;

/**
* @author lenovo
* @description 针对表【question_submit(题目提交)】的数据库操作Service
* @createDate 2023-09-30 14:38:48
*/
public interface QuestionSubmitService extends IService<QuestionSubmit> {

    /**
     * 题目（内部服务）
     *
     * @param questionSubmitAddRequest
     * @param LoginUser
     * @return
     */
    Long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User LoginUser);

    /**
     * 获取提交代码封装
     *
     * @param questionSubmit
     * @param loginUser
     * @return
     */
    QuestionSubmitVO getQuestionSubmitVO(QuestionSubmit questionSubmit, User loginUser);

    /**
     * 分页获取提交代码封装
     *
     * @param questionSubmitPage
     * @param loginUser
     * @return
     */
    Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> questionSubmitPage, User loginUser);

    /**
     * 获取查询条件
     *
     * @param questionSubmitQueryRequest
     * @return
     */
    QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest);
}
