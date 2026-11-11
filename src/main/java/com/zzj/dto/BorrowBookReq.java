package com.zzj.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class BorrowBookReq {

    @NotNull(message = "书籍ID不能为空")
    private Long bookId;

    @NotBlank(message = "用户ID不能为空")
    private String userId;
}
