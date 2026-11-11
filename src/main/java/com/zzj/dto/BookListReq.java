package com.zzj.dto;

import com.zzj.page.PageRequest;
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
public class BookListReq {

    private String keyword;

    private String category;

    private PageRequest pageRequest;
}
