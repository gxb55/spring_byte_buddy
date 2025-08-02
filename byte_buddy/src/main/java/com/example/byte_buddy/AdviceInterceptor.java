package com.example.byte_buddy;

import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

import java.util.concurrent.Callable;

public class AdviceInterceptor {

	@RuntimeType
	public static Object intercept(@AllArguments Object[] args, @SuperCall Callable<?> callable) throws Exception {
		System.out.println("before advice");
		if(args.length>0){
			for (Object so : args) {
				System.out.println(so);
			}
		}
		System.out.println(callable.getClass().getName());
		try {
			return callable.call();
		}finally {
			System.out.println("after advice");
		}
	}
}