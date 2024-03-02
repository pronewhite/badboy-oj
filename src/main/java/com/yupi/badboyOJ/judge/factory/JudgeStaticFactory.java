package com.yupi.badboyOJ.judge.factory;

import com.yupi.badboyOJ.judge.codesanbox.ExampleCodeSandBox;
import com.yupi.badboyOJ.judge.codesanbox.RemoteCodeSandBox;
import com.yupi.badboyOJ.judge.codesanbox.ThirdPartCodeSandBox;
import com.yupi.badboyOJ.judge.codesand.CodeSandBox;

/**
 * 静态代理工厂，根据调用者输入的字符串生成对应的代码沙箱，调用者只需要修改配置文件中的code.type的值即可生成对应的代码沙箱
 */
public class JudgeStaticFactory {

    public  static CodeSandBox getCodeSandBoxByType(String type){
        switch (type){
            case "example":
                return new ExampleCodeSandBox();
            case "remote":
                return new RemoteCodeSandBox();
            case "thirdPart":
                return new ThirdPartCodeSandBox();
            default:
                return new ExampleCodeSandBox();
        }
    }
}
