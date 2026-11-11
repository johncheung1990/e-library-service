package com.zzj.controller;

import com.zzj.dto.BookDetailResp;
import com.zzj.dto.BookListReq;
import com.zzj.dto.BookListResp;
import com.zzj.page.PageRequest;
import com.zzj.page.PageResult;
import com.zzj.response.ResponseData;
import com.zzj.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    /**
     * 获取所有书籍（分页）
     */
    @PostMapping(value = "getAllBooks")
    public ResponseData<PageResult<BookListResp>> getAllBooks(@RequestBody PageRequest pageRequest) {
        PageResult<BookListResp> pageResult = bookService.getAllBooks(pageRequest);
        return ResponseData.page(pageResult.getList(), pageResult.getTotal(), pageRequest);
    }

    /**
     * 查询书籍详情
     */
    @GetMapping("/{bookId}")
    public ResponseData<BookDetailResp> getBookDetail(@PathVariable Long bookId) {
        BookDetailResp book = bookService.getBookDetail(bookId);
        return ResponseData.success(book);
    }

    /**
     * 搜索书籍（分页）
     */
    @PostMapping("/search")
    public ResponseData<PageResult<BookListResp>> searchBooks(@RequestBody BookListReq bookListReq) {

        PageResult<BookListResp> pageResult = bookService.searchBooks(bookListReq.getKeyword(),
                bookListReq.getCategory(), bookListReq.getPageRequest());

        return ResponseData.page(pageResult.getList(), pageResult.getTotal(), bookListReq.getPageRequest());
    }


}