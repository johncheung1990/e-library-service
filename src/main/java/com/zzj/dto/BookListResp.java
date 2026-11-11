package com.zzj.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class BookListResp {

    private Long id;

    private String title;

    private String author;

    private String isbn;

    private String category;

    private Integer totalNum;

    private Integer availableNum;

    private String status;
}
