insert into permission (description) values ('ADMIN'), ('SCAN_ADM'), ('COMMON_READER');
INSERT INTO `users` (`user_name`, `full_name`, `password`, `account_non_expired`, `account_non_locked`, `credentials_non_expired`, `enabled`) VALUES
	('admin', 'Adminstrador Principal', 'c3a7d64cf6bcf3b5bb0870cd138bce7a2523632c804e4765c894eac2cf5d6402e35a5b01de2290c0', b'1', b'1', b'1', b'1');
INSERT INTO `user_permission` (`id_user`, `id_permission`) VALUES (1, 1);