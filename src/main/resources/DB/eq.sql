CREATE TABLE `eq` (
	`EQ_SEQ` INT(10) NOT NULL,
	`EQ_NAME` VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8mb3_general_ci',
	`EQ_TYPE` VARCHAR(10) NULL DEFAULT NULL COLLATE 'utf8mb3_general_ci',
	`EQ_INPUT_DATE` DATETIME NULL DEFAULT NULL,
	`EQ_USER` VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8mb3_general_ci',
	`EQ_STRESS_NUM` INT(10) NULL DEFAULT NULL,
	`EQ_DISUSE` DATETIME NULL DEFAULT NULL,
	`REG_DATE` DATETIME NULL DEFAULT NULL,
	`UserId` VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8mb3_general_ci',
	PRIMARY KEY (`EQ_SEQ`) USING BTREE,
	INDEX `UserId` (`UserId`) USING BTREE,
	CONSTRAINT `UserId` FOREIGN KEY (`UserId`) REFERENCES `users` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION
)
COMMENT='장비 마스터 테이블'
COLLATE='armscii8_bin'
ENGINE=InnoDB
;


