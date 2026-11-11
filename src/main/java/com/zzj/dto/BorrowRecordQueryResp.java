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
public class BorrowRecordQueryResp {

    private Long borrowRecordId;

    private Long bookId;

    private String bookTitle;

    private String bookAuthor;

    private String userId;

    private Date borrowDate;

    private Date dueDate;

    private Date returnDate;

    // 0- 借阅中/ 1- 已归还/ 2- 逾期
    private String status;


}
