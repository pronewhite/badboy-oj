package com.yupi.badboyOJ.judge.codesand;

import com.yupi.badboyOJ.judge.model.ExecuteCodeRequest;
import com.yupi.badboyOJ.judge.model.ExecuteCodeRespose;

/**
 * 代码沙箱编译运行代码
 */
public interface CodeSandBox {

    ExecuteCodeRespose executeCode(ExecuteCodeRequest executeCodeRequest);
}
