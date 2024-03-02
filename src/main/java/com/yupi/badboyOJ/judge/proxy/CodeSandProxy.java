package com.yupi.badboyOJ.judge.proxy;

import com.yupi.badboyOJ.judge.codesand.CodeSandBox;
import com.yupi.badboyOJ.judge.model.ExecuteCodeRequest;
import com.yupi.badboyOJ.judge.model.ExecuteCodeRespose;
import lombok.extern.slf4j.Slf4j;

/**
 * 代码沙箱代理类，增强沙箱功能，比如打印日志等
 */
@Slf4j
public class CodeSandProxy implements CodeSandBox {

    private CodeSandBox codeSandBox;

    public CodeSandProxy(CodeSandBox codeSandBox){
        this.codeSandBox = codeSandBox;
    }
    @Override
    public ExecuteCodeRespose executeCode(ExecuteCodeRequest executeCodeRequest) {
        log.info("沙箱执行前....");
        ExecuteCodeRespose executeCodeRespose = codeSandBox.executeCode(executeCodeRequest);
        log.info("沙箱执行后...");
        return executeCodeRespose;
    }
}
