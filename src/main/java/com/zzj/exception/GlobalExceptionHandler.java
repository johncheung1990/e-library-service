package com.zzj.exception;

import com.zzj.exception.enums.ErrorCode;
import com.zzj.exception.exceptions.ELibraryException;
import com.zzj.response.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ELibraryException.class)
    public ResponseData handleBusinessException(ELibraryException e) {
        log.warn("业务异常: {}", e.getMessage());
        return ResponseData.fail(e.getErrorCode(), e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseData handleValidationException(MethodArgumentNotValidException e) {
        return ResponseData.fail(ErrorCode.INVALID_PARAM.getCode(),
                e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseData<Map<String, Object>> handleException(Exception e) {
        log.error("系统异常", e);
        return ResponseData.fail(500, "服务器内部错误");
    }
}