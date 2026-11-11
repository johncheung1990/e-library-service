package com.zzj.dto;

import com.zzj.entity.BookDO;
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
public class BookDetailResp {

    private Long id;

    private String title;

    private String author;

    private String isbn;

    private String description;

    private String category;

    private Integer totalNum;

    private Integer availableNum;

    // 0- 可借阅/ 1- 全部借出/ 2-下架
    private String status;

    private String createdBy;

    private Date createdDate;

    private String updatedBy;

    private Date updatedDate;
}
