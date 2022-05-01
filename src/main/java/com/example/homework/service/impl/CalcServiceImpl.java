package com.example.homework.service.impl;

import com.example.homework.aop.Abs;
import com.example.homework.common.Result;
import com.example.homework.service.CalcService;
import org.springframework.stereotype.Service;

@Service
public class CalcServiceImpl implements CalcService {

    @Override
    @Abs
    public Result add(int a, int b) {
        return wrapResult((long)a+b);
    }

    @Override
    public Result minus(int a, int b) {
        return wrapResult((long)a-b);
    }

    @Override
    public Result multi(int a, int b) {
        return wrapResult((long)a*b);
    }

    @Override
    public Result divide(int a, int b) {
        if (b == 0)
            return Result.fail("除数不能为0");
        return wrapResult((long)a/b);

    }

    private Result wrapResult(long c){
        if(c > Integer.MAX_VALUE || c < Integer.MIN_VALUE)
            return Result.fail("结果超出整数范围");
        return Result.ok((int)c);
    }
}
