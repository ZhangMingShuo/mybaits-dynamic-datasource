create database tenant;
create database `tenant-one`;
create database `tenant-two`;
use tenant;

CREATE TABLE `tenant` (
                          `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                          `tenant_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '租户名称',
                          `tenant_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '租户详情',
                          `db_info` varchar(2047) COLLATE utf8mb4_general_ci DEFAULT NULL,
                          `redis_info` varchar(2047) COLLATE utf8mb4_general_ci DEFAULT NULL,
                          `version` int NOT NULL DEFAULT '0' COMMENT '版本号',
                          `created_time` datetime NOT NULL COMMENT '创建时间',
                          `created_by` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
                          `modified_time` datetime NOT NULL COMMENT '修改时间',
                          `modified_by` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人',
                          `is_deleted`TINYINT(4) not null DEFAULT 0 COMMENT '是否删除',
                          PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='租户信息';



INSERT INTO `tenant` ( `tenant_name`, `tenant_desc`, `db_info`, `redis_info`, `version`, `created_time`, `created_by`, `modified_time`, `modified_by` )
VALUES
    ( '测试租户1', '租户说明信息', '{\"dbUrl\": \"jdbc:mysql://127.0.0.1:3306/tenant-one?rewriteBatchedStatements=true\",\"dbUsername\": \"root\",\"dbPassword\": \"0c0bb39488e6dbfb\"}', '{\"host\": \"localhost\",\"port\": 6379,\"pwd\": \"123456\",\"db\": 1}', 0, NOW(), '1', NOW(), '1' );


INSERT INTO `tenant` (  `tenant_name`, `tenant_desc`, `db_info`, `redis_info`, `version`, `created_time`, `created_by`, `modified_time`, `modified_by` )
VALUES
    (  '测试租户2', '租户说明信息', '{\"dbUrl\": \"jdbc:mysql://127.0.0.1:3306/tenant-two?rewriteBatchedStatements=true\",\"dbUsername\": \"root\",\"dbPassword\": \"0c0bb39488e6dbfb\"}', '{\"host\": \"localhost\",\"port\": 6379,\"pwd\": \"123456\",\"db\": 1}', 0, NOW(), '1', NOW(), '1' );



use `tenant-one`;
CREATE TABLE IF NOT EXISTS user_info (
                                         id BIGINT NOT NULL PRIMARY KEY COMMENT '主键Id',
                                         user_no VARCHAR(255) NOT NULL DEFAULT '' COMMENT '编号',
    user_name VARCHAR(255) NOT NULL DEFAULT '' COMMENT '姓名',
    description VARCHAR(512) DEFAULT '' COMMENT '备注',
    created_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
    created_by BIGINT NOT NULL DEFAULT 0 COMMENT '记录创建者Id，默认为0',
    modified_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录修改时间',
    modified_by BIGINT DEFAULT NULL COMMENT '记录修改者Id，可以为空',
    is_deleted TINYINT(4) NOT NULL DEFAULT 0 COMMENT '是否删除，默认为0，1表示删除'
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='用户信息';



use `tenant-two`;
CREATE TABLE IF NOT EXISTS user_info (
                                         id BIGINT NOT NULL PRIMARY KEY COMMENT '主键Id',
                                         user_no VARCHAR(255) NOT NULL DEFAULT '' COMMENT '编号',
    user_name VARCHAR(255) NOT NULL DEFAULT '' COMMENT '姓名',
    description VARCHAR(512) DEFAULT '' COMMENT '备注',
    created_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
    created_by BIGINT NOT NULL DEFAULT 0 COMMENT '记录创建者Id，默认为0',
    modified_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录修改时间',
    modified_by BIGINT DEFAULT NULL COMMENT '记录修改者Id，可以为空',
    is_deleted TINYINT(4) NOT NULL DEFAULT 0 COMMENT '是否删除，默认为0，1表示删除'
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='用户信息';


use `tenant-one`;
INSERT INTO `user_info` (`id`, `user_no`, `user_name`, `description`, `created_time`, `created_by`, `modified_time`, `modified_by`, `is_deleted`) VALUES (1, 'test_no', '租户1测试用户', '租户1测试用户', '2024-05-15 03:45:06', 0, '2024-05-15 03:45:06', NULL, 0);

use `tenant-two`;
INSERT INTO `user_info` (`id`, `user_no`, `user_name`, `description`, `created_time`, `created_by`, `modified_time`, `modified_by`, `is_deleted`) VALUES (1, 'test_no', '租户2测试用户', '租户2测试用户', '2024-05-15 03:45:06', 0, '2024-05-15 03:45:06', NULL, 0);
