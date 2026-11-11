package com.zzj.mapper;

import com.zzj.entity.BookDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookMapper {

    long countAllBooks();

    // 查询所有书籍
    List<BookDO> findAllBooks(@Param("offset") int offset, @Param("limit") int limit);

    // 根据ID查询书籍
    BookDO findBookById(@Param("id") Long id);

    // 根据ISBN查询书籍
    BookDO findBookByIsbn(@Param("isbn") String isbn);

    // 条件查询书籍
    List<BookDO> searchBooks(@Param("keyword") String keyword,
                             @Param("category") String category,
                             @Param("offset") int offset,
                             @Param("limit") int limit);

    long countSearchBooks(@Param("keyword") String keyword,
                          @Param("category") String category);

    // 更新书籍库存
    int updateBookStock(@Param("id") Long id,
                        @Param("availableNum") Integer availableNum);


}
