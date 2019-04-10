CREATE DATABASE `banking_token_system` /*!40100 DEFAULT CHARACTER SET utf8 */;

drop table if exists token_service_mapping;
drop table if exists token;
drop table if exists service_counter_mapping;
drop table if exists counter;
drop table if exists service;
drop table if exists customer;
drop table if exists address;
drop table if exists user_role;
drop table if exists user;
drop table if exists role;


CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `active` int(11) DEFAULT NULL,
  `lastname` varchar(255) NOT NULL,
  `firstname` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

CREATE TABLE `role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  UNIQUE KEY `UK_it77eq964jhfqtu54081ebtio` (`role_id`),
  CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address_line1` varchar(255) NOT NULL,
  `address_line2` varchar(255) DEFAULT NULL,
  `city` varchar(255) NOT NULL,
  `created` datetime NOT NULL,
  `state` varchar(255) NOT NULL,
  `zip_code` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `counter` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `number` int(11) DEFAULT NULL,
  `priority` varchar(255) DEFAULT NULL,
  `queue_size` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_9cbmxs60hte68vmecjj4kxtd3` (`number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime DEFAULT NULL,
  `mobile` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `address_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_4h6a5iro7ibjn1v8g2n7pktiw` (`mobile`),
  KEY `FK_r8whbd0mf9er6vwfr0sclsxkd` (`address_id`),
  CONSTRAINT `FK_r8whbd0mf9er6vwfr0sclsxkd` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `service` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `next_service_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_adgojnrwwx9c3y3qa2q08uuqp` (`name`)
) ENGINE=InnoDB;

CREATE TABLE `service_counter_mapping` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) NOT NULL,
  `counter_id` bigint(20) NOT NULL,
  `service_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_314830mu1xe58o4qfdtucy32v` (`counter_id`),
  KEY `FK_e0wwr522o2s2nhan2o9qrcdiy` (`service_id`),
  CONSTRAINT `FK_314830mu1xe58o4qfdtucy32v` FOREIGN KEY (`counter_id`) REFERENCES `counter` (`id`),
  CONSTRAINT `FK_e0wwr522o2s2nhan2o9qrcdiy` FOREIGN KEY (`service_id`) REFERENCES `service` (`id`)
) ENGINE=InnoDB ;

CREATE TABLE `token` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `number` int(11) NOT NULL,
  `status_code` varchar(255) NOT NULL,
  `current_counter_id` bigint(20) DEFAULT NULL,
  `current_service_id` bigint(20) DEFAULT NULL,
  `customer_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_k2vcufjuxpo2dwlcpy15x603o` (`current_counter_id`),
  KEY `FK_ekepo04wk5ebj6e18ulob054b` (`current_service_id`),
  KEY `FK_ajap6h5dshvelx7dx2l5utf72` (`customer_id`),
  CONSTRAINT `FK_ajap6h5dshvelx7dx2l5utf72` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `FK_ekepo04wk5ebj6e18ulob054b` FOREIGN KEY (`current_service_id`) REFERENCES `service` (`id`),
  CONSTRAINT `FK_k2vcufjuxpo2dwlcpy15x603o` FOREIGN KEY (`current_counter_id`) REFERENCES `counter` (`id`)
) ENGINE=InnoDB ;

CREATE TABLE `token_service_mapping` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `comments` varchar(255) DEFAULT NULL,
  `service_id` bigint(20) NOT NULL,
  `token_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_lkrljkvc7g9lm5itn70ullq88` (`service_id`),
  KEY `FK_tnaur3gcarua7he418wdytlnb` (`token_id`),
  CONSTRAINT `FK_lkrljkvc7g9lm5itn70ullq88` FOREIGN KEY (`service_id`) REFERENCES `service` (`id`),
  CONSTRAINT `FK_tnaur3gcarua7he418wdytlnb` FOREIGN KEY (`token_id`) REFERENCES `token` (`id`)
) ENGINE=InnoDB;

insert into user values(1,1,'Srini','sriniadmin','12345');
insert into user values(2,1,'Srini','sriniuser','12345');

insert into role values(1,'ADMIN');
insert into role values(2,'USER');

insert into user_role values(1,1);
insert into user_role values(2,2);

insert into counter values (null, 1, 'HIGH', 0);
insert into counter values (null, 2, 'HIGH', 0);
insert into counter values (null, 3, 'HIGH', 0);
insert into counter values (null, 4, 'NORMAL', 0);
insert into counter values (null, 5, 'NORMAL', 0);
insert into counter values (null, 6, 'NORMAL', 0);

insert into service values (null, 'A', 'PREMIUM', null);
insert into service values (null, 'B', 'PREMIUM', null);
insert into service values (null, 'C', 'REGULAR', null);
insert into service values (null, 'D', 'REGULAR', null);
insert into service values (null, 'E', 'REGULAR', null);
insert into service values (null, 'F', 'REGULAR', null);

update service set next_service_id = 3 where id = 2;

insert into service_counter_mapping values (null, 'PREMIUM', 1, 1);
insert into service_counter_mapping values (null, 'PREMIUM', 2, 2);
insert into service_counter_mapping values (null, 'PREMIUM', 2, 2);
insert into service_counter_mapping values (null, 'PREMIUM', 3, 3);

insert into service_counter_mapping values (null, 'REGULAR', 4, 1);
insert into service_counter_mapping values (null, 'REGULAR', 5, 2);
insert into service_counter_mapping values (null, 'REGULAR', 5, 2);
insert into service_counter_mapping values (null, 'REGULAR', 6, 3);
