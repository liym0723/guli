package com.liym.servicebase.handler;

import com.liym.commonutils.R;
import com.liym.servicebase.exception.MyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

// 全部的异常都会进入到这个方法
@ControllerAdvice
@Slf4j //
public class ExceptionHandler {

    // 指定出现了什么异常会执行
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e){
        e.printStackTrace();
        return R.err().message("执行了全局异常处理");
    }

    // 特定异常
    @org.springframework.web.bind.annotation.ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public R errorArithmeticException(ArithmeticException e){
        e.printStackTrace();
        return R.err().message("by zero");
    }

    // 自定义异常
    @org.springframework.web.bind.annotation.ExceptionHandler(MyException.class)
    @ResponseBody
    public R errMyException(MyException e){
//        log.error(e.getMessage()); // 写入错误信息进去
        e.printStackTrace();
        return R.err().message("自定义异常报错");
    }
}
