SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';


-- -----------------------------------------------------
-- Table `rfrmgr_user_roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_user_roles` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_user_roles` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `title` VARCHAR(100) NOT NULL ,
  `description` TEXT NULL ,
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
  `password` VARCHAR(128) NOT NULL ,
  `salt` VARCHAR(128) NOT NULL ,
  `user_role_id` INT NOT NULL ,
  `person_id` INT NULL ,
  `created` DATETIME NOT NULL ,
  `modified` DATETIME NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'User table for access rights.';


-- -----------------------------------------------------
-- Table `frmgr_sexes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `frmgr_sexes` ;

CREATE  TABLE IF NOT EXISTS `frmgr_sexes` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `sid` VARCHAR(10) NOT NULL ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  UNIQUE INDEX `sid_UNIQUE` (`sid` ASC) ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
COMMENT = 'Lookup table for sexes.\nThere is no description here in orde' /* comment truncated */;


-- -----------------------------------------------------
-- Table `rfrmgr_people`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_people` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_people` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `sex_id` INT UNSIGNED NOT NULL ,
  `first_name` VARCHAR(100) NULL ,
  `name` VARCHAR(100) NOT NULL ,
  `birthday` DATE NULL ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  PRIMARY KEY (`id`) )
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
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Recipients of umpire reports.';


-- -----------------------------------------------------
-- Table `rfrmgr_activities`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_activities` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_activities` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `sid` VARCHAR(10) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  UNIQUE INDEX `sid_UNIQUE` (`sid` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Possible referee activity statuses such as \"interested in ma' /* comment truncated */;


-- -----------------------------------------------------
-- Table `rfrmgr_referees`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_referees` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_referees` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `person_id` INT UNSIGNED NOT NULL ,
  `activity_id` INT UNSIGNED NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Storage of all referees.';


-- -----------------------------------------------------
-- Table `rfrmgr_contact_kinds`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_contact_kinds` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_contact_kinds` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `sid` VARCHAR(10) NOT NULL ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  UNIQUE INDEX `sid_UNIQUE` (`sid` ASC) ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Possible kinds of contacts, such as private, working.';


-- -----------------------------------------------------
-- Table `rfrmgr_contacts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_contacts` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_contacts` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `description` TEXT NULL ,
  `contact_kind_id` INT UNSIGNED NOT NULL ,
  `person_id` INT UNSIGNED NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Base table for contacts. Specialized to email, phone etc.';


-- -----------------------------------------------------
-- Table `rfrmgr_addresses`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_addresses` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_addresses` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `contact_id` INT UNSIGNED NOT NULL ,
  `street` VARCHAR(100) NOT NULL ,
  `number` VARCHAR(100) NOT NULL ,
  `zip_code` VARCHAR(100) NULL ,
  `city` VARCHAR(100) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'All addresses.';


-- -----------------------------------------------------
-- Table `rfrmgr_phone_numbers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_phone_numbers` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_phone_numbers` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `contact_id` INT UNSIGNED NOT NULL ,
  `country code` VARCHAR(10) NOT NULL ,
  `area code` VARCHAR(10) NOT NULL ,
  `number` VARCHAR(20) NOT NULL ,
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
  `contact_id` INT UNSIGNED NOT NULL ,
  `email` VARCHAR(100) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'All email adresses.';


-- -----------------------------------------------------
-- Table `rfrmgr_clubs`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_clubs` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_clubs` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(100) NOT NULL ,
  `shortname` VARCHAR(100) NULL ,
  `description` TEXT NULL ,
  `address_id` INT NOT NULL COMMENT 'standard address of the club. To be used if no address for a team is given.' ,
  PRIMARY KEY (`id`, `address_id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'All clubs.';


-- -----------------------------------------------------
-- Table `rfrmgr_training_levels`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_training_levels` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_training_levels` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `sid` VARCHAR(10) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  UNIQUE INDEX `sid_UNIQUE` (`sid` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Possible training levels such as international referee, Germ' /* comment truncated */;


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
  `address_id` INT UNSIGNED NOT NULL COMMENT 'standard address of the club. To be used if no address for a team is given.' ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'All teams.';


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
  `address_id` INT NULL COMMENT 'Only filled if team plays outside their normal location.' ,
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
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Team\'s assignments: home or road team.';


-- -----------------------------------------------------
-- Table `rfrmgr_team_seasons_leagues_spokespeople`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_team_seasons_leagues_spokespeople` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_team_seasons_leagues_spokespeople` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `team_id` INT NOT NULL ,
  `season_id` INT NOT NULL ,
  `league_id` INT NOT NULL ,
  `person_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Teams are assigned a league, in a season. They have a spokes' /* comment truncated */;


-- -----------------------------------------------------
-- Table `frmgr_pictures`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `frmgr_pictures` ;

CREATE  TABLE IF NOT EXISTS `frmgr_pictures` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `person_id` INT UNSIGNED NOT NULL ,
  `url` VARCHAR(200) NOT NULL ,
  `description` TEXT NULL ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
COMMENT = 'Lookup table for sexes.\nThere is no description here in orde' /* comment truncated */;


-- -----------------------------------------------------
-- Table `rfrmgr_referees_training_levels`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_referees_training_levels` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_referees_training_levels` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `referee_id` INT UNSIGNED NOT NULL ,
  `training_level_id` INT UNSIGNED NOT NULL ,
  `since` DATE NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Achieved training levels for referees.';


-- -----------------------------------------------------
-- Table `rfrmgr_training_updates`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_training_updates` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_training_updates` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `referee_training_level_id` INT UNSIGNED NOT NULL ,
  `update` DATE NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Updates of the training levels.';


-- -----------------------------------------------------
-- Table `rfrmgr_club_relations`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_club_relations` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_club_relations` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `sid` VARCHAR(10) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  UNIQUE INDEX `sid_UNIQUE` (`sid` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Possible relations of a referee to a club (member, prefer, a' /* comment truncated */;


-- -----------------------------------------------------
-- Table `rfrmgr_referees_clubs`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_referees_clubs` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_referees_clubs` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `referee_id` INT UNSIGNED NOT NULL ,
  `club_id` INT UNSIGNED NOT NULL ,
  `club_relation_id` INT UNSIGNED NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Associations between referees and clubs.';



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `frmgr_sexes`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `frmgr_sexes` (`id`, `sid`) VALUES (1, 'f');
INSERT INTO `frmgr_sexes` (`id`, `sid`) VALUES (2, 'm');
INSERT INTO `frmgr_sexes` (`id`, `sid`) VALUES (3, 'o');

COMMIT;

-- -----------------------------------------------------
-- Data for table `rfrmgr_activities`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `rfrmgr_activities` (`id`, `sid`) VALUES (1, 'many');
INSERT INTO `rfrmgr_activities` (`id`, `sid`) VALUES (2, 'normal');
INSERT INTO `rfrmgr_activities` (`id`, `sid`) VALUES (3, 'nathis');
INSERT INTO `rfrmgr_activities` (`id`, `sid`) VALUES (4, 'na');
INSERT INTO `rfrmgr_activities` (`id`, `sid`) VALUES (5, 'o');

COMMIT;

-- -----------------------------------------------------
-- Data for table `rfrmgr_contact_kinds`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `rfrmgr_contact_kinds` (`id`, `sid`) VALUES (1, 'priv');
INSERT INTO `rfrmgr_contact_kinds` (`id`, `sid`) VALUES (2, 'bsn');
INSERT INTO `rfrmgr_contact_kinds` (`id`, `sid`) VALUES (3, 'o');

COMMIT;

-- -----------------------------------------------------
-- Data for table `rfrmgr_training_levels`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `rfrmgr_training_levels` (`id`, `sid`) VALUES (1, 'aump');
INSERT INTO `rfrmgr_training_levels` (`id`, `sid`) VALUES (2, 'nump');
INSERT INTO `rfrmgr_training_levels` (`id`, `sid`) VALUES (3, 'nref');
INSERT INTO `rfrmgr_training_levels` (`id`, `sid`) VALUES (4, 'iump');

COMMIT;

-- -----------------------------------------------------
-- Data for table `rfrmgr_club_relations`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `rfrmgr_club_relations` (`id`, `sid`) VALUES (1, 'member');
INSERT INTO `rfrmgr_club_relations` (`id`, `sid`) VALUES (2, 'prefer');
INSERT INTO `rfrmgr_club_relations` (`id`, `sid`) VALUES (3, 'avoid');

COMMIT;
