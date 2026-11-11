CREATE TABLE `book` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '书籍ID',
    `title` VARCHAR(255) NOT NULL COMMENT '书名',
    `author` VARCHAR(255) NOT NULL COMMENT '作者',
    `isbn` VARCHAR(50) UNIQUE NOT NULL COMMENT 'ISBN编号',
    `description` TEXT COMMENT '描述',
    `category` VARCHAR(255) COMMENT '分类',
    `total_num` INT NOT NULL DEFAULT 1 COMMENT '总共本数',
    `available_num` INT NOT NULL DEFAULT 1 COMMENT '可用本数',
    `status` TINYINT NOT NULL DEFAULT 0 COMMENT '状态: 0-可借阅, 1-全部借出, 2-下架',
    `created_by` VARCHAR(255) NOT NULL COMMENT '创建人',
    `created_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_by` VARCHAR(255) COMMENT '更新人',
    `updated_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    INDEX `idx_isbn` (`isbn`),
    INDEX `idx_category` (`category`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='书籍表' AUTO_INCREMENT = 100086;