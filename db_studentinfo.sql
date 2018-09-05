/*
SQLyog Ultimate v8.32 
MySQL - 5.5.56 : Database - db_studentinfo
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_studentinfo` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `db_studentinfo`;

/*Table structure for table `t_course` */

DROP TABLE IF EXISTS `t_course`;

CREATE TABLE `t_course` (
  `couId` int(10) NOT NULL AUTO_INCREMENT,
  `gradeId` int(10) DEFAULT NULL,
  `couName` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`couId`),
  KEY `FK_t_course` (`gradeId`),
  CONSTRAINT `FK_t_course` FOREIGN KEY (`gradeId`) REFERENCES `t_grade` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

/*Data for the table `t_course` */

insert  into `t_course`(`couId`,`gradeId`,`couName`) values (2,15,'数学'),(3,16,'数学'),(4,17,'数学'),(5,18,'数学'),(6,1,'数学'),(7,1,'英语'),(8,1,'体育'),(9,1,'计算机'),(10,1,'毛泽东'),(11,1,'马克思'),(12,15,'英语'),(13,15,'体育'),(14,15,'计算机'),(15,15,'毛泽东'),(16,15,'马克思'),(17,16,'英语'),(18,16,'体育'),(19,16,'计算机'),(20,16,'毛泽东'),(21,16,'马克思'),(22,17,'英语'),(23,17,'体育'),(24,17,'计算机'),(25,17,'毛泽东'),(26,17,'马克思'),(27,18,'英语'),(28,18,'体育'),(29,18,'毛泽东'),(30,18,'马克思');

/*Table structure for table `t_grade` */

DROP TABLE IF EXISTS `t_grade`;

CREATE TABLE `t_grade` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `gradeName` varchar(20) DEFAULT NULL,
  `gradeDesc` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

/*Data for the table `t_grade` */

insert  into `t_grade`(`id`,`gradeName`,`gradeDesc`) values (1,'15应数','15级应用数学'),(15,'16信计','16级信息与计算科学'),(16,'14软工','14级软件工程'),(17,'15软工','15级软件工程'),(18,'15应数','15级应用数学');

/*Table structure for table `t_score` */

DROP TABLE IF EXISTS `t_score`;

CREATE TABLE `t_score` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `stuId` int(10) DEFAULT NULL,
  `math` int(10) DEFAULT NULL,
  `english` int(10) DEFAULT NULL,
  `it` int(10) DEFAULT NULL,
  `mao` int(10) DEFAULT NULL,
  `ma` int(10) DEFAULT NULL,
  `sport` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_t_score` (`stuId`),
  CONSTRAINT `FK_t_score` FOREIGN KEY (`stuId`) REFERENCES `t_student` (`stuId`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

/*Data for the table `t_score` */

insert  into `t_score`(`id`,`stuId`,`math`,`english`,`it`,`mao`,`ma`,`sport`) values (3,1,80,80,80,80,80,80),(5,3,90,70,80,88,91,75),(6,4,75,90,70,70,91,91),(7,5,70,91,80,80,90,88),(8,6,90,70,90,90,80,75),(9,7,80,90,90,91,90,91),(10,8,88,70,75,80,70,90),(11,9,70,90,88,90,88,72),(12,10,90,88,68,90,72,95),(15,15,88,67,89,92,65,87),(16,11,66,88,66,66,66,88),(17,12,NULL,NULL,NULL,NULL,NULL,NULL),(18,13,99,99,99,99,99,99);

/*Table structure for table `t_student` */

DROP TABLE IF EXISTS `t_student`;

CREATE TABLE `t_student` (
  `stuId` int(10) NOT NULL AUTO_INCREMENT,
  `stuNo` varchar(20) DEFAULT NULL,
  `stuName` varchar(10) DEFAULT NULL,
  `sex` varchar(5) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `gradeId` int(11) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `stuDesc` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`stuId`),
  KEY `FK_t_student` (`gradeId`),
  CONSTRAINT `FK_t_student` FOREIGN KEY (`gradeId`) REFERENCES `t_grade` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `t_student` */

insert  into `t_student`(`stuId`,`stuNo`,`stuName`,`sex`,`birthday`,`gradeId`,`email`,`stuDesc`) values (1,'2015051001013','欧文','男','1995-08-16',17,'1515623@qq.com','重庆'),(3,'2016051001005','王晓梅','女','1999-05-16',15,'15154543@qq.com','四川'),(4,'2014051011120','余林','男','1995-08-16',16,'1515623@qq.com','湖南'),(5,'2015051001021','张月','女','1995-08-16',17,'1515623@qq.com','上海'),(6,'2016051011121','马池','男','1995-08-16',15,'151353@qq.com','广东'),(7,'2015051001013','陈乐乐','女','1994-09-26',18,'1515623@qq.com','江苏'),(8,'2015051001023','艾宇','男','1995-08-16',18,'1515623@qq.com','陕西'),(9,'2014051001013','飒飒','女','1995-08-16',16,'154543@qq.com','山东'),(10,'2015051012103','莫玉','女','1995-12-25',17,'1515623@qq.com','重庆'),(11,'2015051002102','赵红','女','1995-08-16',1,'6455623@qq.com','黑龙江'),(12,'2015051001076','江林','男','1995-08-16',18,'1515623@qq.com','哈尔滨'),(13,'2015051001013','孙玉','女','1995-12-16',1,'1515623@qq.com','浙江'),(15,'2015021002010','罗思思','女','1996-12-19',1,'1515151@qq.com','山西');

/*Table structure for table `t_teacher` */

DROP TABLE IF EXISTS `t_teacher`;

CREATE TABLE `t_teacher` (
  `teaId` int(10) NOT NULL AUTO_INCREMENT,
  `teaNo` int(20) DEFAULT NULL,
  `teaName` varchar(20) DEFAULT NULL,
  `teaSex` varchar(10) DEFAULT NULL,
  `teaBirthday` date DEFAULT NULL,
  `gradeId` int(10) DEFAULT NULL,
  `teaEmail` varchar(20) DEFAULT NULL,
  `teaDesc` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`teaId`),
  KEY `FK_t_teacher` (`gradeId`),
  CONSTRAINT `FK_t_teacher` FOREIGN KEY (`gradeId`) REFERENCES `t_grade` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `t_teacher` */

insert  into `t_teacher`(`teaId`,`teaNo`,`teaName`,`teaSex`,`teaBirthday`,`gradeId`,`teaEmail`,`teaDesc`) values (1,20150100,'张三','男','1985-03-01',1,'12121@qq.com','[15信计 计算机]'),(2,201511,'王刚','男','1988-07-21',1,'21212@qq.com','[16信计 英语]'),(3,20111,'李好','女','1991-03-20',16,'2651651@qq.com','14软工 C语言');

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` int(11) DEFAULT NULL,
  `userName` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `type` varchar(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`userName`,`password`,`type`) values (NULL,'123','123','2'),(NULL,'456','456','3'),(NULL,'admin','123','1'),(NULL,'5070','123','2');

/* Trigger structure for table `t_student` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `TTTTT` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `TTTTT` AFTER INSERT ON `t_student` FOR EACH ROW BEGIN
          INSERT INTO `db_studentinfo`.`t_score`(stuId)VALUES(new.stuId);
    END */$$


DELIMITER ;

/* Trigger structure for table `t_student` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `ssss` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `ssss` BEFORE DELETE ON `t_student` FOR EACH ROW BEGIN
	DELETE FROM `db_studentinfo`.`t_score` WHERE stuId=old.stuId;
    END */$$


DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
