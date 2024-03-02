package com.yupi.badboyOJ.model.dto.questionsubmit;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.yupi.badboyOJ.common.PageRequest;
import com.yupi.badboyOJ.model.dto.question.JudgeCase;
import com.yupi.badboyOJ.model.dto.question.JudgeConfig;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * 查询请求
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QuestionSubmitQueryRequest extends PageRequest implements Serializable {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 题目答案
     */
    private String  answer;

    /**
     * 标签
     */
    private List<String> tags;


    /**
     * 创建用户 id
     */
    private Long userId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}