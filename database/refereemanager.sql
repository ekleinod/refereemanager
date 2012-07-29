SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';


-- -----------------------------------------------------
-- Table `rfrmgr_addresses`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_addresses` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_addresses` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `street` VARCHAR(100) NOT NULL ,
  `number` VARCHAR(100) NOT NULL ,
  `zip_code` VARCHAR(100) NOT NULL ,
  `city` VARCHAR(100) NOT NULL ,
  `description` TEXT NULL ,
  `created` DATETIME NOT NULL ,
  `modified` DATETIME NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
COMMENT = 'All addresses.';


-- -----------------------------------------------------
-- Table `rfrmgr_people`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_people` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_people` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(100) NOT NULL ,
  `first_name` VARCHAR(100) NULL ,
  `birthday` DATE NOT NULL ,
  `address_id` INT UNSIGNED NOT NULL ,
  `created` DATETIME NOT NULL ,
  `modified` DATETIME NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
COMMENT = 'Alle people go here, specializations, such as referees, have' /* comment truncated */;


-- -----------------------------------------------------
-- Table `rfrmgr_leagues`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_leagues` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_leagues` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `title` VARCHAR(100) NOT NULL ,
  `description` TEXT NULL ,
  `code` VARCHAR(20) NOT NULL ,
  `women` TINYINT(1) NOT NULL ,
  `umpire_report_link` VARCHAR(100) NOT NULL COMMENT '\n' ,
  `created` DATETIME NOT NULL ,
  `modified` DATETIME NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
COMMENT = 'All leagues.';


-- -----------------------------------------------------
-- Table `rfrmgr_seasons`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_seasons` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_seasons` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `year_start` DATE NOT NULL ,
  `description` TEXT NULL ,
  `created` DATETIME NOT NULL ,
  `modified` DATETIME NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
COMMENT = 'All seasons.';


-- -----------------------------------------------------
-- Table `rfrmgr_leagues_people_seasons`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_leagues_people_seasons` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_leagues_people_seasons` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `league_id` INT NOT NULL ,
  `person_id` INT NOT NULL ,
  `season_id` INT NOT NULL ,
  `created` DATETIME NOT NULL ,
  `modified` DATETIME NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
COMMENT = 'Recipients of umpire reports.';


-- -----------------------------------------------------
-- Table `rfrmgr_referees`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_referees` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_referees` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `license` DATE NOT NULL ,
  `last_referee_training` DATE NOT NULL ,
  `assignment_quantity_id` INT NOT NULL ,
  `status_id` INT NOT NULL ,
  `club_id` INT NOT NULL ,
  `person_id` INT NOT NULL ,
  `referee_kind_id` INT NOT NULL ,
  `created` DATETIME NOT NULL ,
  `modified` DATETIME NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
COMMENT = 'Storage of all referees.';


-- -----------------------------------------------------
-- Table `rfrmgr_statuses`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_statuses` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_statuses` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `title` VARCHAR(100) NOT NULL ,
  `description` TEXT NULL ,
  `created` DATETIME NOT NULL ,
  `modified` DATETIME NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
COMMENT = 'Possible referee statuses, such as active, not active, not a' /* comment truncated */;


-- -----------------------------------------------------
-- Table `rfrmgr_assignment_quantities`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_assignment_quantities` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_assignment_quantities` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `title` VARCHAR(100) NOT NULL ,
  `description` TEXT NULL ,
  `created` DATETIME NOT NULL ,
  `modified` DATETIME NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
COMMENT = 'Possible assignment quantities, such as normal, many, less.';


-- -----------------------------------------------------
-- Table `rfrmgr_phone_numbers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_phone_numbers` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_phone_numbers` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `number` VARCHAR(20) NOT NULL ,
  `description` TEXT NULL ,
  `contact_kind_id` INT NOT NULL ,
  `person_id` INT UNSIGNED NOT NULL ,
  `created` DATETIME NOT NULL ,
  `modified` DATETIME NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
COMMENT = 'All phone numbers.';


-- -----------------------------------------------------
-- Table `rfrmgr_emails`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_emails` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_emails` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `email` VARCHAR(100) NOT NULL ,
  `description` TEXT NULL ,
  `contact_kind_id` INT NOT NULL ,
  `person_id` INT UNSIGNED NOT NULL ,
  `created` DATETIME NOT NULL ,
  `modified` DATETIME NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
COMMENT = 'All email adresses.';


-- -----------------------------------------------------
-- Table `rfrmgr_contact_kinds`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_contact_kinds` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_contact_kinds` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `title` VARCHAR(100) NOT NULL ,
  `description` TEXT NULL ,
  `created` DATETIME NOT NULL ,
  `modified` DATETIME NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
COMMENT = 'Possible kinds of telephone numbers, such as private, workin' /* comment truncated */;


-- -----------------------------------------------------
-- Table `rfrmgr_clubs`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_clubs` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_clubs` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(100) NOT NULL ,
  `description` TEXT NULL ,
  `address_id` INT UNSIGNED NOT NULL COMMENT 'standard address of the club. To be used if no address for a team is given.' ,
  `created` DATETIME NOT NULL ,
  `modified` DATETIME NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
COMMENT = 'All clubs.';


-- -----------------------------------------------------
-- Table `rfrmgr_referee_kinds`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_referee_kinds` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_referee_kinds` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `code` VARCHAR(20) NOT NULL ,
  `title` VARCHAR(100) NOT NULL ,
  `description` TEXT NULL ,
  `created` DATETIME NOT NULL ,
  `modified` DATETIME NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
COMMENT = 'Possible kinds of referees such as international referee, Ge' /* comment truncated */;


-- -----------------------------------------------------
-- Table `rfrmgr_teams`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_teams` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_teams` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `number` INT UNSIGNED NOT NULL ,
  `name` VARCHAR(100) NOT NULL ,
  `description` TEXT NULL ,
  `address_id` INT UNSIGNED NULL COMMENT 'standard address of the club. To be used if no address for a team is given.' ,
  `league_id` INT NOT NULL ,
  `created` DATETIME NOT NULL ,
  `modified` DATETIME NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
COMMENT = 'All teams.';


-- -----------------------------------------------------
-- Table `rfrmgr_people_seasons_teams`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_people_seasons_teams` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_people_seasons_teams` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `team_id` INT NOT NULL ,
  `person_id` INT NOT NULL ,
  `season_id` INT NOT NULL ,
  `created` DATETIME NOT NULL ,
  `modified` DATETIME NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
COMMENT = 'Spokepersons for the teams.';


-- -----------------------------------------------------
-- Table `rfrmgr_users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_users` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_users` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `username` VARCHAR(100) NOT NULL ,
  `password` VARCHAR(100) NOT NULL ,
  `role` VARCHAR(100) NOT NULL ,
  `created` DATETIME NOT NULL ,
  `modified` DATETIME NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
COMMENT = 'User table for access rights.';


-- -----------------------------------------------------
-- Table `rfrmgr_activity_logs`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_activity_logs` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_activity_logs` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `table_name` VARCHAR(100) NOT NULL ,
  `column_name` VARCHAR(100) NOT NULL ,
  `old_value` TEXT NOT NULL ,
  `new_value` TEXT NOT NULL ,
  `user_id` INT NOT NULL COMMENT '\n' ,
  `created` DATETIME NOT NULL ,
  `modified` DATETIME NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
COMMENT = 'Activity log of the database.';


-- -----------------------------------------------------
-- Table `rfrmgr_assignments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_assignments` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_assignments` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `game_number` INT UNSIGNED NOT NULL ,
  `time` DATETIME NOT NULL ,
  `home_team_id` INT NOT NULL ,
  `road_team_id` INT NOT NULL ,
  `season_id` INT NOT NULL ,
  `created` DATETIME NOT NULL ,
  `modified` DATETIME NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
COMMENT = 'Assignments for referees.';


-- -----------------------------------------------------
-- Table `rfrmgr_referee_assignment_roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_referee_assignment_roles` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_referee_assignment_roles` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `code` VARCHAR(20) NOT NULL ,
  `title` VARCHAR(100) NOT NULL ,
  `description` TEXT NULL ,
  `created` DATETIME NOT NULL ,
  `modified` DATETIME NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
COMMENT = 'Possible roles of referee\'s assignments such as umpire, stan' /* comment truncated */;


-- -----------------------------------------------------
-- Table `rfrmgr_referee_assignments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_referee_assignments` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_referee_assignments` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `assignment_id` INT NOT NULL ,
  `referee_assignment_role_id` INT NOT NULL ,
  `referee_id` INT NOT NULL ,
  `created` DATETIME NOT NULL ,
  `modified` DATETIME NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
COMMENT = 'Referee\'s assignments with their role.';


-- -----------------------------------------------------
-- Table `rfrmgr_team_assignments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_team_assignments` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_team_assignments` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `home` TINYINT(1) NOT NULL ,
  `assignment_id` INT NOT NULL ,
  `team_id` INT NOT NULL ,
  `created` DATETIME NOT NULL ,
  `modified` DATETIME NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
COMMENT = 'Team\'s assignments: home or road team.';



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `rfrmgr_leagues`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `rfrmgr_leagues` (`id`, `title`, `description`, `code`, `women`, `umpire_report_link`, `created`, `modified`) VALUES (1, 'Oberliga Damen', NULL, 'OLD', 1, 'http://something/', NULL, NULL);

COMMIT;

-- -----------------------------------------------------
-- Data for table `rfrmgr_referee_assignment_roles`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `rfrmgr_referee_assignment_roles` (`id`, `code`, `title`, `description`, `created`, `modified`) VALUES (1, 'OSR', 'Oberschiedsrichter', NULL, '2012-07-17 00:43:00', '2012-07-17 00:43:00');
INSERT INTO `rfrmgr_referee_assignment_roles` (`id`, `code`, `title`, `description`, `created`, `modified`) VALUES (2, 'Stellv. OSR', 'Stellvertretender Oberschiedsrichter', NULL, '2012-07-17 00:43:00', '2012-07-17 00:43:00');
INSERT INTO `rfrmgr_referee_assignment_roles` (`id`, `code`, `title`, `description`, `created`, `modified`) VALUES (3, 'SR', 'Schiedsrichter', NULL, '2012-07-17 00:43:00', '2012-07-17 00:43:00');

COMMIT;
