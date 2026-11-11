package com.zzj.service;

import com.zzj.dto.*;
import com.zzj.page.PageRequest;
import com.zzj.page.PageResult;

import java.util.List;

public interface BookService {

    // 浏览所有书籍
    PageResult<BookListResp> getAllBooks(PageRequest pageRequest);

    // 查询书籍详情
    BookDetailResp getBookDetail(Long bookId);

    // 搜索书籍
    PageResult<BookListResp> searchBooks(String keyword, String category, PageRequest pageRequest);

    // 借阅书籍
    BorrowBookResp borrowBook(BorrowBookReq borrowBookReq);

    // 归还书籍
    ReturnBookResp returnBook(ReturnBookReq returnBookReq);

    // 查看当前借阅记录
    List<BorrowRecordQueryResp> getUserBorrowRecords(String userId);
}