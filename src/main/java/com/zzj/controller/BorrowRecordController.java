package com.zzj.controller;

import com.zzj.dto.*;
import com.zzj.response.ResponseData;
import com.zzj.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/borrowRecord")
public class BorrowRecordController {

    @Autowired
    private BookService bookService;

    /**
     * 借阅书籍
     */
    @PostMapping("/borrow")
    public ResponseData<BorrowBookResp> borrowBook(@Valid @RequestBody BorrowBookReq borrowBookReq) {
        BorrowBookResp record = bookService.borrowBook(borrowBookReq);
        return ResponseData.success("借阅成功", record);
    }

    /**
     * 归还书籍
     */
    @PostMapping("/return")
    public ResponseData<ReturnBookResp> returnBook(@Valid @RequestBody ReturnBookReq returnBookReq) {
        ReturnBookResp record = bookService.returnBook(returnBookReq);
        return ResponseData.success("归还成功", record);
    }

    /**
     * 查看用户借阅记录
     */
    @GetMapping("/records/{userId}")
    public ResponseData<List<BorrowRecordQueryResp>> getUserRecords(@PathVariable String userId) {
        List<BorrowRecordQueryResp> records = bookService.getUserBorrowRecords(userId);
        return ResponseData.success(records);
    }
}
