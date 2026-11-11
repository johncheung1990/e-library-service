CREATE TABLE `borrow_record` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '记录ID',
    `book_id` BIGINT NOT NULL COMMENT '书籍ID',
    `user_id` VARCHAR(50) NOT NULL COMMENT '用户ID',
    `borrow_date` DATETIME NOT NULL COMMENT '借阅日期',
    `due_date` DATETIME NOT NULL COMMENT '应还日期',
    `return_date` DATETIME DEFAULT NULL COMMENT '实际归还日期',
    `status` TINYINT NOT NULL DEFAULT 0 COMMENT '状态: 0-借阅中, 1-已归还, 2-逾期',
    `created_by` VARCHAR(255) NOT NULL COMMENT '创建人',
    `created_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_by` VARCHAR(255) COMMENT '更新人',
    `updated_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    INDEX `idx_user_id` (`user_id`),
    INDEX `idx_book_id` (`book_id`),
    INDEX `idx_status` (`status`),
    FOREIGN KEY (`book_id`) REFERENCES `book`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='借阅记录表';