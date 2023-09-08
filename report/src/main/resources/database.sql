CREATE DATABASE `db_reportmanagement` WITH OWNER = postgres ENCODING = 'UTF8' TABLESPACE = pg_default LC_COLLATE = 'English_United States.1252' LC_CTYPE = 'English_United States.1252' CONNECTION LIMIT = -1;
```

## 1. Create a new people

```sql
-- Path: src\main\resources\database.sql
CREATE TABLE `tbl_people` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `birth_date` date NOT NULL,
  `gender` varchar(1) DEFAULT "N" NOT NULL,
  `itin` varchar(12) DEFAULT "N" NOT NULL,
  `created_date` datetime NOT NULL,
  `updated_date` datetime NOT NULL,
  CONSTRAINT ck_people CHECK (gender IN ("F", "M", "N")),
  CONSTRAINT pk_people PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
```

## 3. Create a new email

```sql
-- Path: src\main\resources\database.sql
CREATE TABLE `tbl_emails` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `person_id` int(16) NOT NULL,
  `email_address` varchar(100) NOT NULL,
  `verified` tinyint(1) NOT NULL,
  `primary` tinyint(1) NOT NULL,
  `visibility` tinyint(1) NOT NULL,
  `created_date` datetime NOT NULL,
  `updated_date` datetime NOT NULL,
  CONSTRAINT pk_emails PRIMARY KEY (`id`),
  KEY `fk_emails_persons_idx` (`person_id`),
  CONSTRAINT `fk_emails_persons` FOREIGN KEY (`person_id`) REFERENCES `tbl_people` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2001 DEFAULT CHARSET=utf8;
```

## 4. Create a new address

```sql
-- Path: src\main\resources\database.sql
CREATE TABLE `tbl_addresses` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `person_id` int(16) NOT NULL,
  `address` varchar(100) NOT NULL,
  `city` varchar(100) NOT NULL,
  `state` varchar(100) NOT NULL,
  `country` varchar(100) NOT NULL,
  `postalCode` varchar(100) NOT NULL,
  `primary` tinyint(1) NOT NULL,
  `visibility` tinyint(1) NOT NULL,
  `created_date` datetime NOT NULL,
  `updated_date` datetime NOT NULL,
  CONSTRAINT pk_address PRIMARY KEY (`id`),
  KEY `fk_addresses_persons_idx` (`person_id`),
  CONSTRAINT `fk_addresses_persons` FOREIGN KEY (`person_id`) REFERENCES `tbl_people` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2001 DEFAULT CHARSET=utf8;
```

## 4. Create a new phone

```sql
-- Path: src\main\resources\database.sql
CREATE TABLE `tbl_phones` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `person_id` int(16) NOT NULL,
  `phone_number` varchar(14) NOT NULL,
  `verified` tinyint(1) NOT NULL,
  `primary` tinyint(1) NOT NULL,
  `visibility` tinyint(1) NOT NULL,
  `created_date` datetime NOT NULL,
  `updated_date` datetime NOT NULL,
  CONSTRAINT pk_phone PRIMARY KEY (`id`),
  KEY `fk_phones_persons_idx` (`person_id`),
  CONSTRAINT `fk_phones_persons` FOREIGN KEY (`person_id`) REFERENCES `tbl_people` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2001 DEFAULT CHARSET=utf8;
```

## 5. Create a new user

```sql
-- Path: src\main\resources\database.sql
CREATE TABLE `tbl_users` (
  `id` int(16) NOT NULL,
  `avatar` LONGBLOB,
  `theme_image` LONGBLOB,
  `user_identifier` varchar(45) NOT NULL,
  `active` tinyint(1) NOT NULL,
  `created_date` datetime NOT NULL,
  `updated_date` datetime NOT NULL,
  CONSTRAINT pk_users PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`user_identifier`),
  CONSTRAINT `fk_users_people` FOREIGN KEY (`id`) REFERENCES `tbl_people` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
```

