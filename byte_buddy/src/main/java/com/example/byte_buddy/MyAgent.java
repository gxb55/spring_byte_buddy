package com.example.byte_buddy;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;

public class MyAgent {

    //优先进入该方法
    public static void premain(String agrs, Instrumentation inst) {
        System.out.println("进入premain");
        System.out.println("isRedefineClassesSupported: " + inst.isRedefineClassesSupported());
        AgentBuilder.Transformer transformer = (builder, typeDescription, classLoader, javaModule, y) -> {
            return builder
//                    .method(ElementMatchers.named("executeInternal")) // 拦截指定的方法executeInternal
//                    .method(ElementMatchers.any()) // 拦截任意方法
                    .method(ElementMatchers.not(ElementMatchers.isStatic())) // 拦截非静态方法
                    .intercept(MethodDelegation.to(AdviceInterceptor.class)); // 将拦截到的方法委托给目标类处理
        };
        new AgentBuilder
                .Default()
//                .type(ElementMatchers.nameStartsWith("com.mysql.cj.jdbc.ClientPreparedStatement"))
                .type(ElementMatchers.nameStartsWith("com.example.spring"))
                // .type(ElementMatchers.nameStartsWith("com.example.spring"))
//                .type(ElementMatchers.any())
                .transform(transformer)
//                .with(listener)
                .installOn(inst);


    }

    //当上面方法不存在时，才会进入此方法
    public static void premain(String agrs) {
        System.out.println("进入premain2");
    }
}