package com.app.concerns;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceAspect {
	
	public ServiceAspect() {
		System.out.println("aspect contr");
	}
	@Before(value = "execution(* com.app.service.UserService.*(..))")
	public void loginDetail(JoinPoint jp) {
		System.out.println("inside login Detail");
	}

}
