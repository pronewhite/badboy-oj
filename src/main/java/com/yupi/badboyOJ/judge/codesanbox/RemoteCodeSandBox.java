package com.yupi.badboyOJ.judge.codesanbox;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.yupi.badboyOJ.common.ErrorCode;
import com.yupi.badboyOJ.exception.BusinessException;
import com.yupi.badboyOJ.judge.codesand.CodeSandBox;
import com.yupi.badboyOJ.judge.model.ExecuteCodeRequest;
import com.yupi.badboyOJ.judge.model.ExecuteCodeRespose;
import org.springframework.util.StringUtils;

/**
 * 示例代码沙箱
 */
public class RemoteCodeSandBox implements CodeSandBox {

    private static final String AUTH_REQUEST_HEADER = "auth";
    private static final String AUTH_REQUEST_SECRET_KEY = "secretKey";
    @Override
    public ExecuteCodeRespose executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("这是远程代码沙箱");
        String url = "http://localhost:8090/executeCode";
        String json = JSONUtil.toJsonStr(executeCodeRequest);
        String responseStr = HttpUtil.createPost(url)
                .header(AUTH_REQUEST_HEADER, AUTH_REQUEST_SECRET_KEY)
                .body(json)
                .execute()
                .body();
        if(StrUtil.isBlank(responseStr)){
            throw new BusinessException(ErrorCode.API_REQUEST_ERROR,"message:" + responseStr);
        }
        return JSONUtil.toBean(responseStr, ExecuteCodeRespose.class);
    }
}
