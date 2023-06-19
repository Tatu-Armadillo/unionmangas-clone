insert into permission (description) values ('ADMIN'), ('SCAN_ADM'), ('COMMON_READER');
INSERT INTO `users` (`user_name`, `full_name`, `password`, `account_non_expired`, `account_non_locked`, `credentials_non_expired`, `enabled`) VALUES
	('admin', 'Adminstrador Principal', '$2a$12$y.lFLRl9u0/Dk5jtBMRZLOYe0uiKVYqPOX32bin.Rm.WvsFmEIncW', b'1', b'1', b'1', b'1');
INSERT INTO `user_permission` (`id_user`, `id_permission`) VALUES (1, 1);