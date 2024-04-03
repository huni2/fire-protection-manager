CREATE TABLE `users` (
	`id` VARCHAR(50) NOT NULL COLLATE 'utf8mb3_general_ci',
	`username` VARCHAR(50) NOT NULL COLLATE 'utf8mb3_general_ci',
	`email` VARCHAR(50) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
	`password` VARCHAR(255) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
	PRIMARY KEY (`id`) USING BTREE
)
COMMENT='사용자 정보 저장'
COLLATE='utf8mb4_0900_ai_ci'
ENGINE=InnoDB
;
