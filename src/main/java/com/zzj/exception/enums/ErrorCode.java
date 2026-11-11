package com.zzj.exception.enums;

public enum ErrorCode {
    BOOK_NOT_FOUND(1001, "书籍不存在"),
    BOOK_UNAVAILABLE(1002, "书籍不可借阅"),
    BOOK_ALL_BORROWED(1003, "书籍已全部借出"),
    RECORD_NOT_FOUND(1004, "借阅记录不存在"),
    ALREADY_RETURNED(1005, "该书已归还"),
    USER_HAS_OVERDUE(1006, "用户有逾期未还书籍"),
    MAX_BORROW_LIMIT(1007, "已达到最大借阅数量限制"),
    INVALID_PARAM(1008, "参数错误");

    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}