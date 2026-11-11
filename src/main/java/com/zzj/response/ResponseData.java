package com.zzj.response;

import com.zzj.exception.exceptions.ELibraryException;
import com.zzj.page.PageRequest;
import com.zzj.page.PageResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseData<T> {

    private int code;

    private String message;

    private T data;

    private Long timestamp;

    public static <T> ResponseData<T> success() {
        return ResponseData.<T>builder()
                .code(200)
                .message("success")
                .timestamp(System.currentTimeMillis())
                .build();
    }

    public static <T> ResponseData<T> success(T data) {
        return ResponseData.<T>builder()
                .code(200)
                .message("success")
                .data(data)
                .timestamp(System.currentTimeMillis())
                .build();
    }

    public static <T> ResponseData<T> success(String message, T data) {
        return ResponseData.<T>builder()
                .code(200)
                .message(message)
                .data(data)
                .timestamp(System.currentTimeMillis())
                .build();
    }

    public static <T> ResponseData<PageResult<T>> page(List<T> list, long total, PageRequest pageRequest) {

        PageResult<T> pageResult = new PageResult<>(list, total, pageRequest.getPageNum(), pageRequest.getPageSize());

        return ResponseData.<PageResult<T>>builder()
                .code(200)
                .message("success")
                .data(pageResult)
                .timestamp(System.currentTimeMillis())
                .build();
    }


    public static <T> ResponseData<T> fail(int code, String message) {
        return ResponseData.<T>builder()
                .code(code)
                .message(message)
                .timestamp(System.currentTimeMillis())
                .build();
    }

    public static <T> ResponseData<T> fail(String message) {
        return ResponseData.<T>builder()
                .code(500)
                .message(message)
                .timestamp(System.currentTimeMillis())
                .build();
    }


    public static <T> ResponseData<T> error(ELibraryException e) {
        return ResponseData.<T>builder()
                .code(e.getErrorCode())
                .message(e.getMessage())
                .timestamp(System.currentTimeMillis())
                .build();
    }


    public boolean isSuccess() {
        return this.code == 200;
    }
}