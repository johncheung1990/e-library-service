package com.zzj.service.impl;

import com.zzj.dto.*;
import com.zzj.entity.BookDO;
import com.zzj.entity.BorrowRecordDO;
import com.zzj.exception.enums.ErrorCode;
import com.zzj.exception.exceptions.ELibraryException;
import com.zzj.factory.BookFactory;
import com.zzj.mapper.BookMapper;
import com.zzj.mapper.BorrowRecordMapper;
import com.zzj.page.PageRequest;
import com.zzj.page.PageResult;
import com.zzj.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private BorrowRecordMapper borrowRecordMapper;

    // 最大借阅数量限制
    private static final int MAX_BORROW_LIMIT = 5;
    // 默认借阅天数
    private static final int DEFAULT_BORROW_DAYS = 30;

    @Override
    public PageResult<BookListResp> getAllBooks(PageRequest pageRequest) {

        // 查询总数
        long total = bookMapper.countAllBooks();

        if (total == 0) {
            return PageResult.empty(pageRequest.getPageNum(), pageRequest.getPageSize());
        }

        // 分页查询
        List<BookDO> bookDOList = bookMapper.findAllBooks(pageRequest.getOffset(), pageRequest.getPageSize());

        List<BookListResp> voList = bookDOList.stream()
                .map(BookFactory::convertToBookListResp)
                .collect(Collectors.toList());

        return new PageResult<>(voList, total, pageRequest.getPageNum(), pageRequest.getPageSize());
    }

    @Override
    public BookDetailResp getBookDetail(Long bookId) {

        BookDO book = bookMapper.findBookById(bookId);

        if (book == null) {
            throw new ELibraryException(ErrorCode.BOOK_NOT_FOUND);
        }
        return BookFactory.convertToBookDetailResp(book);
    }

    @Override
    public PageResult<BookListResp> searchBooks(String keyword, String category, PageRequest pageRequest) {

        long total = bookMapper.countSearchBooks(keyword, category);

        if (total == 0) {
            return PageResult.empty(pageRequest.getPageNum(), pageRequest.getPageSize());
        }

        // 分页查询
        List<BookDO> books = bookMapper.searchBooks(keyword, category,
                pageRequest.getOffset(), pageRequest.getPageSize());

        List<BookListResp> voList = books.stream()
                .map(BookFactory::convertToBookListResp)
                .collect(Collectors.toList());

        return new PageResult<>(voList, total, pageRequest.getPageNum(), pageRequest.getPageSize());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BorrowBookResp borrowBook(BorrowBookReq borrowBookReq) {

        // 1. 检查书籍是否存在且可借阅
        BookDO book = bookMapper.findBookById(borrowBookReq.getBookId());

        if (book == null) {
            throw new ELibraryException(ErrorCode.BOOK_NOT_FOUND);
        }

        if (book.getAvailableNum() <= 0) {
            throw new ELibraryException(ErrorCode.BOOK_ALL_BORROWED);
        }

        // 2. 检查用户是否有逾期记录
        int overdueCount = borrowRecordMapper.countUserOverdueRecords(borrowBookReq.getUserId());

        if (overdueCount > 0) {
            throw new ELibraryException(ErrorCode.USER_HAS_OVERDUE,
                    "用户存在逾期未还书籍，请先归还后再借阅");
        }

        // 3. 检查用户借阅数量是否超限
        int activeBorrows = borrowRecordMapper.countUserActiveBorrows(borrowBookReq.getUserId());

        if (activeBorrows >= MAX_BORROW_LIMIT) {
            throw new ELibraryException(ErrorCode.MAX_BORROW_LIMIT,
                    String.format("已达到最大借阅数量限制(%d本)", MAX_BORROW_LIMIT));
        }

        // 4. 创建借阅记录
        BorrowRecordDO record = new BorrowRecordDO();
        record.setBookId(borrowBookReq.getBookId());
        record.setUserId(borrowBookReq.getUserId());

        record.setBorrowDate(new Date());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, DEFAULT_BORROW_DAYS);
        record.setDueDate(calendar.getTime());
        record.setStatus(0); // 借阅中

        //TODO 更新操作图片书馆理员
        record.setCreatedBy("sys");

        borrowRecordMapper.insertBorrowRecord(record);

        // 5. 更新书籍库存
        int newAvailableCopies = book.getAvailableNum() - 1;
        //TODO 更新操作图片书馆理员
        bookMapper.updateBookStock(book.getId(), newAvailableCopies);

        log.info("用户 {} 借阅了书籍 {} ({})", borrowBookReq.getUserId(), book.getTitle(), book.getIsbn());

        return BookFactory.convertToBorrowBookResp(record, book);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ReturnBookResp returnBook(ReturnBookReq returnBookReq) {

        BorrowRecordDO record = borrowRecordMapper.findBorrowRecordById(returnBookReq.getBorrowRecordId());

        if (record == null) {
            throw new ELibraryException(ErrorCode.RECORD_NOT_FOUND);
        }

        if (record.getStatus() == 1) {
            throw new ELibraryException(ErrorCode.ALREADY_RETURNED);
        }

        record.setReturnDate(new Date());
        record.setStatus(1);
        //TODO 更新操作图片书馆理员
        record.setUpdatedBy("sys");
        borrowRecordMapper.updateBorrowRecord(record);

        BookDO book = bookMapper.findBookById(record.getBookId());

        if (book != null) {
            int newAvailableCopies = book.getAvailableNum() + 1;
            bookMapper.updateBookStock(book.getId(), newAvailableCopies);
        }

        log.info("用户 {} 归还了借阅记录 {}", record.getUserId(), record.getId());

        return BookFactory.convertToReturnBookResp(record, book);
    }

    @Override
    public List<BorrowRecordQueryResp> getUserBorrowRecords(String userId) {

        List<BorrowRecordDO> records = borrowRecordMapper.findUserBorrowRecords(userId);

        List<BorrowRecordQueryResp> result = new ArrayList<>();

        for (BorrowRecordDO record : records) {
            BookDO book = bookMapper.findBookById(record.getBookId());
            result.add(BookFactory.convertToBorrowRecordQueryResp(record, book));
        }

        return result;
    }
}
