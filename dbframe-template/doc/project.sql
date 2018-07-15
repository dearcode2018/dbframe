

/* 若存在先删除 */
DROP TABLE IF EXISTS `T_DB_CONFIG`;
/* 创建 数据库配置表 */
CREATE TABLE `T_DB_CONFIG` (
	`ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键-自增长',
	`UNION_CODE` varchar(64) NOT NULL comment '统一编号，标识数据库的归属',
	`USERNAME` varchar(128) comment '用户名',
	`PASSWORD` varchar(16) comment '密码',
	`DRIVER` varchar(64) COMMENT 'JDBC连接驱动',	
	`URL` varchar(128) COMMENT 'JDBC连接地址',
  	`INITIAL_POOL_SIZE` smallint(4) COMMENT '连接池-连接数初始化值',
  	`MIN_POOL_SIZE` smallint(4) COMMENT '连接池-连接数最小值',		
  	`MAX_POOL_SIZE` smallint(4) COMMENT '连接池-连接数最大值',		  	  			
  	`STATUS` tinyint(4) DEFAULT '1' COMMENT '状态, 0-无效，1-有效',	
	`DESCRIPTION` varchar(128) COMMENT '描述',		
	`CREATE_DT` timestamp null COMMENT '创建时间',
	PRIMARY KEY (`ID`)
) ENGINE=InnoDB COMMENT '数据库配置' DEFAULT CHARSET=UTF8;

























