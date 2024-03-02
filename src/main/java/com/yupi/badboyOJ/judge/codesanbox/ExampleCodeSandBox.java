package com.yupi.badboyOJ.judge.codesanbox;

import com.yupi.badboyOJ.judge.codesand.CodeSandBox;
import com.yupi.badboyOJ.judge.model.ExecuteCodeRequest;
import com.yupi.badboyOJ.judge.model.ExecuteCodeRespose;

/**
 * 示例代码沙箱
 */
public class ExampleCodeSandBox implements CodeSandBox {
    @Override
    public ExecuteCodeRespose executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("这是示例代码沙箱");
        return null;
    }
}
