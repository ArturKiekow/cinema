
CREATE TABLE `users` (
  `id` binary(16) NOT NULL,
  `birth_date` date NOT NULL,
  `cpf` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_users_cpf` (`cpf`),
  UNIQUE KEY `UK_users_email` (`email`),
  UNIQUE KEY `UK_users_username` (`username`)
);

CREATE TABLE `roles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255)  NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_roles_name` (`name`)
);

CREATE TABLE `user_roles` (
  `user_id` binary(16) NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `IX_user_roles_role_id` (`role_id`),
  CONSTRAINT `FK_user_roles_role_id` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `FK_user_roles_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
);

CREATE TABLE `movies` (
  `id` binary(16) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `duration_in_minutes` int NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `genres` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_genres_name` (`name`)
);

CREATE TABLE `movie_genres` (
  `movie_id` binary(16) NOT NULL,
  `genre_id` bigint NOT NULL,
  PRIMARY KEY (`movie_id`,`genre_id`),
  KEY `IX_movie_genres_genre_id` (`genre_id`),
  CONSTRAINT `FK_movie_genres_movie_id` FOREIGN KEY (`movie_id`) REFERENCES `movies` (`id`),
  CONSTRAINT `FK_movie_genres_genre_id` FOREIGN KEY (`genre_id`) REFERENCES `genres` (`id`)
);

CREATE TABLE `display_formats` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_display_formats_name` (`name`)
);

CREATE TABLE `rooms` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `number` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_rooms_number` (`number`)
);

CREATE TABLE `room_display_formats` (
  `room_id` bigint NOT NULL,
  `type_id` bigint NOT NULL,
  PRIMARY KEY (`room_id`,`type_id`),
  KEY `IX_room_display_formats_type_id` (`type_id`),
  CONSTRAINT `FK_room_display_formats_type_id` FOREIGN KEY (`type_id`) REFERENCES `display_formats` (`id`),
  CONSTRAINT `FK_room_display_formats_room_id` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`id`)
);

CREATE TABLE `sessions` (
  `session_id` binary(16) NOT NULL,
  `date_time` datetime(6) NOT NULL,
  `movie_id` binary(16) NOT NULL,
  `room_id` bigint NOT NULL,
  PRIMARY KEY (`session_id`),
  KEY `IX_tb_sessions_movie_id` (`movie_id`),
  KEY `IX_tb_sessions_room_id` (`room_id`),
  CONSTRAINT `FK_sessions_room_id` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`id`),
  CONSTRAINT `FK_sessions_movie_id` FOREIGN KEY (`movie_id`) REFERENCES `movies` (`id`)
);
