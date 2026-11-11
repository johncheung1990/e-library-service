package com.zzj.exception.exceptions;

import com.zzj.exception.enums.ErrorCode;

public class ELibraryException extends RuntimeException {

    private final int errorCode;

    public ELibraryException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode.getCode();
    }

    public ELibraryException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode.getCode();
    }

    public int getErrorCode() { return errorCode; }
}