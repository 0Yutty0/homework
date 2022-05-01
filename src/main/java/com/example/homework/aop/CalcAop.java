package com.example.homework.aop;

import com.example.homework.common.Result;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
@Aspect
@Component
public class CalcAop {

    @Pointcut("execution (public * com.example.homework.service.impl.*.*(..))")
    public void pointcut1(){

    }

    @Pointcut(value = "@annotation(com.example.homework.aop.Abs)")
    public void pointcut2(){

    }

    @Around("pointcut1()")
    public Object doAround(ProceedingJoinPoint pjp){
        try {
            System.out.println("执行前");
            Result result = (Result) pjp.proceed(pjp.getArgs());
            System.out.println("执行后");
            if (result.getCode() != 0)
                return result;

            result.setData(Math.abs((int)result.getData()));
            int r = (int) result.getData();
            return result;

        }catch (Throwable e){
            return Result.fail("AOP处理数据出错："+e.getMessage());
        }


    }

}
