package com.atguigu.servicebase.exceptionhandler;



import com.atguigu.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    //全局异常
    @ExceptionHandler(Exception.class)//自定义异常可以作为异常拦截器
    @ResponseBody
    public R error(Exception e){
        e.printStackTrace();
        return R.error().message("执行了全局异常处理");
    }
    //特定异常
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public R error(ArithmeticException e){
        e.printStackTrace();
        return R.error().message("执行了ArithmeticException异常处理");
    }
    //自定义异常
    @ExceptionHandler(GuliException.class)
    @ResponseBody
    public R error(GuliException e){
        e.printStackTrace();
        log.error(e.getMessage());
        return R.error().code(e.getCode()).message(e.getMsg());
    }
}
