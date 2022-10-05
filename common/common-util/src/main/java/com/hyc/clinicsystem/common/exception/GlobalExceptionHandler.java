package com.hyc.clinicsystem.common.exception;

import com.hyc.clinicsystem.common.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ControllerAdvice
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    // @ResponseBody
    public Result error(Exception e) {
        e.printStackTrace();
        return Result.fail();
    }

    @ExceptionHandler(ClinicSysException.class)
    // @ResponseBody
    public Result error(ClinicSysException e) {
        e.printStackTrace();
        return Result.fail();
    }
}
