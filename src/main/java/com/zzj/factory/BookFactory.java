package com.zzj.factory;

import com.zzj.dto.*;
import com.zzj.entity.BookDO;
import com.zzj.entity.BorrowRecordDO;
import com.zzj.enums.BookStatusEnum;

public class BookFactory {

    public static BookListResp convertToBookListResp(BookDO bookDO) {
        return new BookListResp()
                .setAuthor(bookDO.getAuthor())
                .setCategory(bookDO.getCategory())
                .setAvailableNum(bookDO.getAvailableNum())
                .setId(bookDO.getId())
                .setIsbn(bookDO.getIsbn())
                .setTitle(bookDO.getTitle())
                .setStatus(BookStatusEnum.fromCode(bookDO.getStatus()).getDesc())
                .setTotalNum(bookDO.getTotalNum());
    }

    public static BookDetailResp convertToBookDetailResp(BookDO book) {
        return new BookDetailResp()
                .setAuthor(book.getAuthor())
                .setCategory(book.getCategory())
                .setAvailableNum(book.getAvailableNum())
                .setId(book.getId())
                .setIsbn(book.getIsbn())
                .setTitle(book.getTitle())
                .setStatus(BookStatusEnum.fromCode(book.getStatus()).getDesc())
                .setCreatedBy(book.getCreatedBy())
                .setCreatedDate(book.getCreatedDate())
                .setUpdatedBy(book.getUpdatedBy())
                .setUpdatedDate(book.getUpdatedDate())
                .setDescription(book.getDescription())
                .setTotalNum(book.getTotalNum());
    }

    public static BorrowBookResp convertToBorrowBookResp(BorrowRecordDO record, BookDO book) {
        return new BorrowBookResp()
                .setBookId(book.getId())
                .setTitle(book.getTitle())
                .setBorrowDate(record.getBorrowDate())
                .setDueDate(record.getDueDate());
    }

    public static ReturnBookResp convertToReturnBookResp(BorrowRecordDO record, BookDO book) {
        return new ReturnBookResp()
                .setBookId(book.getId())
                .setBorrowRecordId(record.getId())
                .setTitle(book.getTitle());
    }

    public static BorrowRecordQueryResp convertToBorrowRecordQueryResp(BorrowRecordDO record, BookDO book) {
        return new BorrowRecordQueryResp()
                .setBookAuthor(book.getAuthor())
                .setBookTitle(book.getTitle())
                .setBookId(record.getBookId())
                .setBorrowDate(record.getBorrowDate())
                .setBorrowRecordId(record.getId())
                .setDueDate(record.getDueDate())
                .setReturnDate(record.getReturnDate())
                .setUserId(record.getUserId())
                .setStatus(convertBorrowStatus(record.getStatus()));
    }

    public static String convertBorrowStatus(Integer recordStatus) {

        String statusDesc = "未知";

        switch (recordStatus) {
            case 0:
                statusDesc = "借阅中";
                break;
            case 1:
                statusDesc = "已归还";
                break;
            case 2:
                statusDesc = "逾期";
                break;
            default:
                statusDesc = "未知";
        }

        return statusDesc;
    }
}
