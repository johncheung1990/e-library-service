package com.zzj.mapper;

import com.zzj.entity.BorrowRecordDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BorrowRecordMapper {

    // 创建借阅记录
    int insertBorrowRecord(BorrowRecordDO record);

    // 更新借阅记录（归还）
    int updateBorrowRecord(BorrowRecordDO record);

    // 查询用户的借阅记录
    List<BorrowRecordDO> findUserBorrowRecords(@Param("userId") String userId);

    // 根据ID查询借阅记录
    BorrowRecordDO findBorrowRecordById(@Param("id") Long id);

    // 查询用户当前借阅中的记录数
    int countUserActiveBorrows(@Param("userId") String userId);

    // 查询用户是否有逾期记录
    int countUserOverdueRecords(@Param("userId") String userId);
}
