USE `job_lite`;

/*Table structure for table `fb_user_data` */

DROP TABLE IF EXISTS `fb_user_data`;

CREATE TABLE `fb_user_data` (
  `user_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `first_name` varchar(200) DEFAULT NULL,
  `last_name` varchar(200) DEFAULT NULL,
  `full_name` varchar(200) DEFAULT NULL,
  `email_id` varchar(500) DEFAULT NULL,
   `phone` varchar(50) DEFAULT NULL,
  `locale` varchar(10) DEFAULT NULL,
  `city` varchar(200) DEFAULT NULL,
  `state` varchar(200) DEFAULT NULL,
  `country` varchar(200) DEFAULT NULL,
    `zip` varchar(50) DEFAULT NULL, 
   `user_name` varchar(200) DEFAULT NULL,
   `status` varchar(10) DEFAULT NULL,
   `timezone` varchar(10) DEFAULT NULL,
   `gender` varchar(10) DEFAULT NULL,
   `bio` text DEFAULT NULL,
   `facebookid` varchar(50) DEFAULT NULL, 
   `link` varchar(200) DEFAULT NULL,  
   `lastFriendupdatedDate` date DEFAULT NULL,
   `top_line` varchar(500) DEFAULT NULL,
   `indexdone` smallint(1) unsigned  DEFAULT '0',
   `updatedDate` date DEFAULT NULL,
   PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `fb_user_employer`;

CREATE TABLE `fb_user_employer` (
  `user_emp_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) unsigned  DEFAULT '0', 
  `employer_id` bigint(20) unsigned  DEFAULT '0', 
  `level` smallint(2) DEFAULT '0', 
  `start_date` varchar(20) DEFAULT NULL,
  `end_date` varchar(20) DEFAULT NULL,
  `position_id` bigint(20) DEFAULT '0', 
  `location_id` bigint(20) DEFAULT '0',
  `description` varchar(500) DEFAULT NULL,
   PRIMARY KEY (`user_emp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `fb_employer`;

CREATE TABLE `fb_employer` (
  `employer_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `facebookid` varchar(50) DEFAULT NULL, 
  `name` varchar(500) DEFAULT NULL, 
   PRIMARY KEY (`employer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `fb_positions`;
CREATE TABLE `fb_positions` (                                  
               `position_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,  
               `facebookid` varchar(50) DEFAULT NULL,                      
               `name` varchar(500) DEFAULT NULL,                           
               PRIMARY KEY (`position_id`)                                 
             ) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

DROP TABLE IF EXISTS `fb_schools`;
CREATE TABLE `fb_schools` (                                  
               `school_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,  
               `facebookid` varchar(50) DEFAULT NULL,                      
               `name` varchar(500) DEFAULT NULL,                           
               PRIMARY KEY (`school_id`)                                 
             ) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

CREATE TABLE `fb_concentration` (                                  
               `con_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,  
               `facebookid` varchar(50) DEFAULT NULL,                      
               `name` varchar(500) DEFAULT NULL,                           
               PRIMARY KEY (`con_id`)                                 
             ) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;


DROP TABLE IF EXISTS `fb_user_schools`;

CREATE TABLE `fb_user_schools` (
  `user_sch_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) unsigned  DEFAULT '0', 
  `school_id` bigint(20) unsigned  DEFAULT '0', 
  `level` smallint(2) DEFAULT '0', 
  `year` varchar(10) DEFAULT NULL,
  `type` varchar(200) DEFAULT NULL,
  `con_id` bigint(20) DEFAULT '0',   
  `location_id` bigint(20) DEFAULT '0',
   PRIMARY KEY (`user_sch_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `fb_locations`;
CREATE TABLE `fb_locations` (                                  
               `location_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,  
               `facebookid` varchar(50) DEFAULT NULL,                      
               `name` varchar(500) DEFAULT NULL,                           
               PRIMARY KEY (`location_id`)                                 
             ) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;


DROP TABLE IF EXISTS `fb_user_skills`;

CREATE TABLE `fb_user_skills` (
  `user_skill_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) unsigned  DEFAULT '0', 
   `skill` varchar(200) DEFAULT NULL, 
  `level` smallint(2) DEFAULT '0', 
  `expyear` varchar(10) DEFAULT NULL,
   PRIMARY KEY (`user_skill_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `fb_skill`;

CREATE TABLE `fb_skill` (
  `skill_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL, 
   PRIMARY KEY (`skill_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `fb_endorse_ask`;

CREATE TABLE `fb_endorse_ask` (
  `endorse_ask_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `fromid` varchar(50) DEFAULT NULL, 
  `toid` varchar(50) DEFAULT NULL, 
  `status` varchar(1) DEFAULT NULL, 
   PRIMARY KEY (`endorse_ask_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `fb_jobs`;

CREATE TABLE `fb_jobs` (
  `job_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `jobTitle` varchar(200) DEFAULT NULL,
  `headline` varchar(200) DEFAULT NULL,
  `companyName` varchar(200) DEFAULT NULL,
  `city` varchar(200) DEFAULT NULL,
  `postalcode` varchar(50) DEFAULT NULL,
  `country` varchar(200) DEFAULT NULL,
  `city` varchar(200) DEFAULT NULL,
  `state` varchar(200) DEFAULT NULL,
   `description` varchar(800) DEFAULT NULL,
   `jobcategory` varchar(100) DEFAULT NULL,
   `experience` varchar(50) DEFAULT NULL,
   `tenure` varchar(50) DEFAULT NULL,
   `perks` varchar(500) DEFAULT NULL,
   `applyauto` varchar(1) DEFAULT NULL,
   `appyurl` varchar(500) DEFAULT NULL,
   `appyemail` varchar(500) DEFAULT NULL,
   `veterans` varchar(10) DEFAULT NULL,
   `referencecode` varchar(200) DEFAULT NULL,
   `createdBy` bigint(20) DEFAULT '0',
   `createdDate` date DEFAULT NULL,
   PRIMARY KEY (`job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `fb_endorses`;

CREATE TABLE `fb_endorses` (
  `endorse_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `fromid` varchar(50) DEFAULT NULL, 
  `toid` varchar(50) DEFAULT NULL, 
  `endorse` varchar(500) DEFAULT NULL, 
  `createdDate` date DEFAULT NULL,
   PRIMARY KEY (`endorse_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;




DROP TABLE IF EXISTS `fb_user_achivements`;

CREATE TABLE `fb_user_achivements` (
  `user_achivement_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) unsigned  DEFAULT '0',
  `achivements` varchar(500) DEFAULT NULL, 
  PRIMARY KEY (`user_achivement_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `fb_user_specialties`;

CREATE TABLE `fb_user_specialties` (
  `user_specialties_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) unsigned  DEFAULT '0',
  `specialties` varchar(500) DEFAULT NULL, 
  PRIMARY KEY (`user_specialties_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `fb_user_certifications`;

CREATE TABLE `fb_user_certifications` (
  `user_cert_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) unsigned  DEFAULT '0',
  `certification_name` varchar(300) DEFAULT NULL,
  `certified_org` varchar(300) DEFAULT NULL,
  `year` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`user_cert_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `fb_saved_companies`;

CREATE TABLE `fb_saved_companies` (
  `saved_comp_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) unsigned  DEFAULT '0',
  `facebookid` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`saved_comp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;