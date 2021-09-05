/*
SQLyog Community v13.1.5  (64 bit)
MySQL - 10.4.11-MariaDB : Database - db_system_cuti
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_system_cuti` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `db_system_cuti`;

/*Table structure for table `tbl_detail_pengajuan_cuti` */

DROP TABLE IF EXISTS `tbl_detail_pengajuan_cuti`;

CREATE TABLE `tbl_detail_pengajuan_cuti` (
  `detail_pengajuan_cuti_id` int(11) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `updated_by_date` datetime(6) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `tgl_cuti` datetime(6) DEFAULT NULL,
  `jenis_cuti_id` int(11) DEFAULT NULL,
  `pengajuan_cuti_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`detail_pengajuan_cuti_id`),
  KEY `FKn4uowmrivj4x391ne1obpvvao` (`jenis_cuti_id`),
  KEY `FK6u8a9jqlt42em3ouifq5b2h2n` (`pengajuan_cuti_id`),
  CONSTRAINT `FK6u8a9jqlt42em3ouifq5b2h2n` FOREIGN KEY (`pengajuan_cuti_id`) REFERENCES `tbl_pengajuan_cuti` (`pengajuan_cuti_id`),
  CONSTRAINT `FKn4uowmrivj4x391ne1obpvvao` FOREIGN KEY (`jenis_cuti_id`) REFERENCES `tbl_jenis_cuti` (`jenis_cuti_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `tbl_detail_pengajuan_cuti` */

/*Table structure for table `tbl_employee` */

DROP TABLE IF EXISTS `tbl_employee`;

CREATE TABLE `tbl_employee` (
  `employee_id` int(11) NOT NULL AUTO_INCREMENT,
  `is_deleted` bit(1) DEFAULT NULL,
  `divisi` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `nama_lengkap` varchar(100) DEFAULT NULL,
  `nip` varchar(18) DEFAULT NULL,
  `password` varchar(250) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `account_non_expired` bit(1) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `updated_by_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`employee_id`),
  KEY `FKptw3st7ux2ihrsw2vq1hucedp` (`role_id`),
  CONSTRAINT `FKptw3st7ux2ihrsw2vq1hucedp` FOREIGN KEY (`role_id`) REFERENCES `tbl_role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

/*Data for the table `tbl_employee` */

insert  into `tbl_employee`(`employee_id`,`is_deleted`,`divisi`,`email`,`nama_lengkap`,`nip`,`password`,`username`,`role_id`,`account_non_expired`,`created_by`,`created_date`,`updated_by`,`updated_by_date`) values 
(1,'\0','HRD','iqbalshihab111@gmail.com','iqbal sihabudin','02041911018','$2a$10$RhLWSlvgf3F40OHE0V3EWe.i0/509wFMCxQ.pSNDjXUPN6bZhKCM6','iqbal',1,'',NULL,NULL,'iqbal','2021-09-05 18:43:32.000000'),
(2,'\0','HRD','iqbalsihabudin111@gmail.com','sihabudin','02041911019','$2a$10$.d71gqEhz8BPaS3HaCTGFOxgix31At6eblMDrurRx0YT.DBC964fK','admin',1,'',NULL,NULL,'admin','2021-09-05 18:42:16.000000'),
(3,'\0','IT','dioprasetiyo@gmail.com','dio prasetiyo','02141911019','$2a$10$KHdhxYDeIvZLlHa7l7fnHOM.NQfJtDxrSihnFBxKKzQ60euzw5j.K','dio',2,'','admin','2021-09-05 17:18:56.000000','admin','2021-09-05 17:18:56.000000'),
(4,'\0','programer','halohera1@gmail.com','hera elvira','02141911020','$2a$10$mq4EZL1fdwVjE.atSJ3JPuXnD0P.Z3uwuLymR1vJTOHjSOu5paCB2','hera',2,'','admin','2021-09-05 17:20:14.000000','hera','2021-09-05 17:52:04.000000'),
(5,'\0','programer','putrimelinia2201@gmail.com','putri melenia','02041911022','$2a$10$7toqURMdhwYUXbviiR9ynetkSrh045arE8emEWJEmsdNRvcyW9o..','mele',2,'','admin','2021-09-05 17:21:33.000000','admin','2021-09-05 17:21:33.000000');

/*Table structure for table `tbl_hak_cuti` */

DROP TABLE IF EXISTS `tbl_hak_cuti`;

CREATE TABLE `tbl_hak_cuti` (
  `hak_cuti_id` int(11) NOT NULL AUTO_INCREMENT,
  `is_deleted` bit(1) DEFAULT NULL,
  `sisa_cuti` int(11) DEFAULT NULL,
  `employee_id` int(11) DEFAULT NULL,
  `jenis_cuti_id` int(11) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `updated_by_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`hak_cuti_id`),
  KEY `FKcu709w58at40he3vqrny64s2j` (`employee_id`),
  KEY `FK6fqo7vh2icbj1sqs53nx2w434` (`jenis_cuti_id`),
  CONSTRAINT `FK6fqo7vh2icbj1sqs53nx2w434` FOREIGN KEY (`jenis_cuti_id`) REFERENCES `tbl_jenis_cuti` (`jenis_cuti_id`),
  CONSTRAINT `FKcu709w58at40he3vqrny64s2j` FOREIGN KEY (`employee_id`) REFERENCES `tbl_employee` (`employee_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

/*Data for the table `tbl_hak_cuti` */

insert  into `tbl_hak_cuti`(`hak_cuti_id`,`is_deleted`,`sisa_cuti`,`employee_id`,`jenis_cuti_id`,`created_by`,`created_date`,`updated_by`,`updated_by_date`) values 
(1,'\0',11,3,1,'admin','2021-09-05 17:19:01.000000','admin','2021-09-05 17:38:57.000000'),
(2,'\0',0,3,2,'admin','2021-09-05 17:19:01.000000','admin','2021-09-05 17:19:01.000000'),
(3,'\0',12,4,1,'admin','2021-09-05 17:20:18.000000','admin','2021-09-05 17:20:18.000000'),
(4,'\0',0,4,2,'admin','2021-09-05 17:20:18.000000','admin','2021-09-05 17:20:18.000000'),
(5,'\0',12,5,1,'admin','2021-09-05 17:21:37.000000','admin','2021-09-05 17:21:37.000000'),
(6,'\0',0,5,2,'admin','2021-09-05 17:21:37.000000','admin','2021-09-05 17:21:37.000000');

/*Table structure for table `tbl_jenis_cuti` */

DROP TABLE IF EXISTS `tbl_jenis_cuti`;

CREATE TABLE `tbl_jenis_cuti` (
  `jenis_cuti_id` int(11) NOT NULL AUTO_INCREMENT,
  `is_deleted` bit(1) DEFAULT NULL,
  `deskripsi` varchar(100) DEFAULT NULL,
  `jenis_cuti` varchar(18) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `updated_by_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`jenis_cuti_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Data for the table `tbl_jenis_cuti` */

insert  into `tbl_jenis_cuti`(`jenis_cuti_id`,`is_deleted`,`deskripsi`,`jenis_cuti`,`created_by`,`created_date`,`updated_by`,`updated_by_date`) values 
(1,'\0','libur tahunan','tahunan',NULL,NULL,NULL,NULL),
(2,'\0','libur cuti lintas tahun','cuti lintas tahun',NULL,NULL,NULL,NULL);

/*Table structure for table `tbl_libur` */

DROP TABLE IF EXISTS `tbl_libur`;

CREATE TABLE `tbl_libur` (
  `libur_id` int(11) NOT NULL AUTO_INCREMENT,
  `is_deleted` bit(1) DEFAULT NULL,
  `deskripsi` varchar(255) DEFAULT NULL,
  `nama_libur` varchar(255) DEFAULT NULL,
  `tgl_libur` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `updated_by_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`libur_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `tbl_libur` */

/*Table structure for table `tbl_pengajuan_cuti` */

DROP TABLE IF EXISTS `tbl_pengajuan_cuti`;

CREATE TABLE `tbl_pengajuan_cuti` (
  `pengajuan_cuti_id` int(11) NOT NULL AUTO_INCREMENT,
  `alamat` varchar(18) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `hrd_id` int(11) DEFAULT NULL,
  `keterangan` varchar(100) DEFAULT NULL,
  `lama_cuti` int(11) DEFAULT NULL,
  `no_telp` varchar(100) DEFAULT NULL,
  `employee_id` int(11) DEFAULT NULL,
  `pengganti_id` int(11) DEFAULT NULL,
  `status_cuti_id` int(11) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `updated_by_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`pengajuan_cuti_id`),
  KEY `FKj31vb50io7ccilfdoic7y1mqe` (`employee_id`),
  KEY `FK8lexlwup2644u8h4n5d9q1a70` (`pengganti_id`),
  KEY `FKe7q3icoy4646r7s7a8jw4s1gy` (`status_cuti_id`),
  CONSTRAINT `FK8lexlwup2644u8h4n5d9q1a70` FOREIGN KEY (`pengganti_id`) REFERENCES `tbl_employee` (`employee_id`),
  CONSTRAINT `FKe7q3icoy4646r7s7a8jw4s1gy` FOREIGN KEY (`status_cuti_id`) REFERENCES `tbl_status_cuti` (`status_cuti_id`),
  CONSTRAINT `FKj31vb50io7ccilfdoic7y1mqe` FOREIGN KEY (`employee_id`) REFERENCES `tbl_employee` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `tbl_pengajuan_cuti` */

/*Table structure for table `tbl_role` */

DROP TABLE IF EXISTS `tbl_role`;

CREATE TABLE `tbl_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `is_deleted` bit(1) DEFAULT NULL,
  `name_role` varchar(50) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `updated_by_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Data for the table `tbl_role` */

insert  into `tbl_role`(`role_id`,`is_deleted`,`name_role`,`created_by`,`created_date`,`updated_by`,`updated_by_date`) values 
(1,'\0','HRD',NULL,NULL,NULL,NULL),
(2,'\0','Karyawan',NULL,NULL,NULL,NULL);

/*Table structure for table `tbl_status_cuti` */

DROP TABLE IF EXISTS `tbl_status_cuti`;

CREATE TABLE `tbl_status_cuti` (
  `status_cuti_id` int(11) NOT NULL AUTO_INCREMENT,
  `is_deleted` bit(1) DEFAULT NULL,
  `deskripsi` varchar(100) DEFAULT NULL,
  `status_cuti` varchar(18) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `updated_by_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`status_cuti_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

/*Data for the table `tbl_status_cuti` */

insert  into `tbl_status_cuti`(`status_cuti_id`,`is_deleted`,`deskripsi`,`status_cuti`,`created_by`,`created_date`,`updated_by`,`updated_by_date`) values 
(1,'\0','Draft adalah status cuti dimana pengajuan cuti sudah dibuat tapi belum di ajukan','Draft',NULL,NULL,NULL,NULL),
(2,'\0','Open adalah status cuti dimana pengajuan cuti sudah diajukan kepada hrd','Open',NULL,NULL,NULL,NULL),
(3,'\0','Approved adalah status cuti dimana pengajuan cuti sudah disetujui oleh hrd','Approved',NULL,NULL,NULL,NULL),
(4,'\0','Rejected adalah status cuti dimana pengajuan cuti ditolak oleh hrd','Rejected',NULL,NULL,NULL,NULL),
(5,'\0','Cancelled adalah status cuti dimana pengajuan cuti yang sebelumnya open namun di gagalkan oleh karya','Cancelled',NULL,NULL,NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
