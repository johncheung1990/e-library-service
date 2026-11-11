package com.zzj.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class BorrowBookResp {

    private Long bookId;

    private Long BorrowRecordId;

    private String title;

    private Date borrowDate;

    private Date dueDate;
}
