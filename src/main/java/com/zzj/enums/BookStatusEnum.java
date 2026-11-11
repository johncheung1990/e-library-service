package com.zzj.enums;

public enum BookStatusEnum {

    AVAILABLE(0, "可借阅"),

    ALL_BORROWED(1, "全部借出"),

    REMOVED(2, "下架");

    private final int code;

    private final String desc;

    BookStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static BookStatusEnum fromCode(int code) {

        for (BookStatusEnum status : values()) {

            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown status code: " + code);
    }
}