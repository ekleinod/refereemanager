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
  `zip_code` VARCHAR(100) NULL ,
  `city` VARCHAR(100) NOT NULL ,
  `description` TEXT NULL ,
  `created` DATETIME NOT NULL ,
  `modified` DATETIME NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'All addresses.';


-- -----------------------------------------------------
-- Table `rfrmgr_user_roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_user_roles` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_user_roles` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `title` VARCHAR(100) NOT NULL ,
  `description` TEXT NULL ,
  `created` DATETIME NOT NULL ,
  `modified` DATETIME NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Roles of users, such as admin, reader, editor.';


-- -----------------------------------------------------
-- Table `rfrmgr_users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_users` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_users` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `username` VARCHAR(100) NOT NULL ,
  `password` VARCHAR(100) NOT NULL ,
  `user_role_id` INT NOT NULL ,
  `person_id` INT NULL ,
  `created` DATETIME NOT NULL ,
  `modified` DATETIME NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'User table for access rights.';


-- -----------------------------------------------------
-- Table `rfrmgr_people`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_people` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_people` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(100) NOT NULL ,
  `first_name` VARCHAR(100) NULL ,
  `birthday` DATE NULL ,
  `address_id` INT UNSIGNED NOT NULL ,
  `created` DATETIME NOT NULL ,
  `modified` DATETIME NOT NULL ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
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
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'All leagues.';


-- -----------------------------------------------------
-- Table `rfrmgr_seasons`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_seasons` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_seasons` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `year_start` YEAR NOT NULL ,
  `description` TEXT NULL ,
  `created` DATETIME NOT NULL ,
  `modified` DATETIME NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'All seasons.';


-- -----------------------------------------------------
-- Table `rfrmgr_umpire_report_recipients`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_umpire_report_recipients` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_umpire_report_recipients` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `league_id` INT NOT NULL ,
  `person_id` INT NOT NULL ,
  `season_id` INT NOT NULL ,
  `created` DATETIME NOT NULL ,
  `modified` DATETIME NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Recipients of umpire reports.';


-- -----------------------------------------------------
-- Table `rfrmgr_referees`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_referees` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_referees` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `license` DATE NULL ,
  `last_referee_training` DATE NULL ,
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
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
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
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
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
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
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
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
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
  `person_id` INT NOT NULL ,
  `created` DATETIME NOT NULL ,
  `modified` DATETIME NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
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
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Possible kinds of telephone numbers, such as private, workin' /* comment truncated */;


-- -----------------------------------------------------
-- Table `rfrmgr_clubs`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_clubs` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_clubs` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(100) NOT NULL ,
  `description` TEXT NULL ,
  `address_id` INT NULL COMMENT 'standard address of the club. To be used if no address for a team is given.' ,
  `created` DATETIME NOT NULL ,
  `modified` DATETIME NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
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
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Possible kinds of referees such as international referee, Ge' /* comment truncated */;


-- -----------------------------------------------------
-- Table `rfrmgr_teams`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_teams` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_teams` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `number` INT UNSIGNED NOT NULL ,
  `name` VARCHAR(100) NULL ,
  `description` TEXT NULL ,
  `club_id` INT UNSIGNED NOT NULL ,
  `address_id` INT UNSIGNED NULL COMMENT 'standard address of the club. To be used if no address for a team is given.' ,
  `league_id` INT UNSIGNED NOT NULL ,
  `created` DATETIME NOT NULL ,
  `modified` DATETIME NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'All teams.';


-- -----------------------------------------------------
-- Table `rfrmgr_team_spokespeople`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_team_spokespeople` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_team_spokespeople` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `team_id` INT NOT NULL ,
  `person_id` INT NOT NULL ,
  `season_id` INT NOT NULL ,
  `created` DATETIME NOT NULL ,
  `modified` DATETIME NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Spokepersons for the teams.';


-- -----------------------------------------------------
-- Table `rfrmgr_activity_logs`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_activity_logs` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_activity_logs` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `table_name` VARCHAR(100) NOT NULL ,
  `row_id` INT NOT NULL ,
  `column_name` VARCHAR(100) NOT NULL ,
  `old_value` TEXT NULL ,
  `new_value` TEXT NOT NULL ,
  `user_id` INT NOT NULL COMMENT '\n' ,
  `description` TEXT NULL ,
  `created` DATETIME NOT NULL ,
  `modified` DATETIME NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Activity log of selected data tables. Only those tables are ' /* comment truncated */;


-- -----------------------------------------------------
-- Table `rfrmgr_assignments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_assignments` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_assignments` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `game_number` INT UNSIGNED NOT NULL ,
  `datetime` DATETIME NOT NULL ,
  `season_id` INT NOT NULL ,
  `league_id` INT NOT NULL ,
  `created` DATETIME NOT NULL ,
  `modified` DATETIME NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
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
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
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
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
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
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Team\'s assignments: home or road team.';



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
