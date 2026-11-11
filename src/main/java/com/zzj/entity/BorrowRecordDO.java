package com.zzj.entity;

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
public class BorrowRecordDO {

    private Long id;

    private Long bookId;

    private String userId;

    private Date borrowDate;

    private Date dueDate;

    private Date returnDate;

    // 0- 借阅中/ 1- 已归还/ 2- 逾期
    private Integer status;

    private String createdBy;

    private Date createdDate;

    private String updatedBy;

    private Date updatedDate;
}
