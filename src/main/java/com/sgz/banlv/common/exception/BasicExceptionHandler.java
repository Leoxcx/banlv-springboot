package com.sgz.banlv.common.exception;

import com.sgz.banlv.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class BasicExceptionHandler {


    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result errorHandler(Exception exception) {
        return Result.failure(exception.getMessage());
    }

}
