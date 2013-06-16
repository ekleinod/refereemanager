SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';


-- -----------------------------------------------------
-- Table `rfrmgr_user_roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_user_roles` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_user_roles` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `sid` VARCHAR(20) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  UNIQUE INDEX `sid_UNIQUE` (`sid` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Roles of users, such as admin, editor.';


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
-- Table `rfrmgr_sex_types`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_sex_types` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_sex_types` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `title` VARCHAR(100) NOT NULL ,
  `remark` TEXT NULL ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  UNIQUE INDEX `sid_UNIQUE` (`title` ASC) ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Lookup table for sexes.';


-- -----------------------------------------------------
-- Table `rfrmgr_people`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_people` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_people` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `sex_type_id` INT UNSIGNED NOT NULL ,
  `first_name` VARCHAR(100) NULL ,
  `name` VARCHAR(100) NOT NULL ,
  `birthday` DATE NULL ,
  `remark` TEXT NULL ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Alle people go here, specializations, such as referees, have' /* comment truncated */;


-- -----------------------------------------------------
-- Table `rfrmgr_league_types`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_league_types` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_league_types` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `title` VARCHAR(100) NOT NULL ,
  `remark` TEXT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  UNIQUE INDEX `title_UNIQUE` (`title` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'All league types.';


-- -----------------------------------------------------
-- Table `rfrmgr_leagues`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_leagues` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_leagues` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `title` VARCHAR(100) NOT NULL ,
  `abbreviation` VARCHAR(20) NOT NULL ,
  `league_type_id` INT UNSIGNED NOT NULL ,
  `remark` TEXT NULL ,
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
  `remark` TEXT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  UNIQUE INDEX `year_start_UNIQUE` (`year_start` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'All seasons.';


-- -----------------------------------------------------
-- Table `rfrmgr_referee_reports`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_referee_reports` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_referee_reports` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `league_id` INT UNSIGNED NOT NULL ,
  `season_id` INT UNSIGNED NOT NULL ,
  `url` VARCHAR(200) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Recipients of umpire reports.';


-- -----------------------------------------------------
-- Table `rfrmgr_status_types`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_status_types` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_status_types` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `title` VARCHAR(100) NOT NULL ,
  `style` VARCHAR(10) NULL ,
  `color` VARCHAR(6) NULL ,
  `bgcolor` VARCHAR(6) NULL ,
  `remark` TEXT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  UNIQUE INDEX `sid_UNIQUE` (`title` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Possible referee statuses such as \"interested in many assign' /* comment truncated */;


-- -----------------------------------------------------
-- Table `rfrmgr_referees`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_referees` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_referees` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `person_id` INT UNSIGNED NOT NULL ,
  `status_type_id` INT UNSIGNED NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Storage of all referees.';


-- -----------------------------------------------------
-- Table `rfrmgr_contact_types`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_contact_types` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_contact_types` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `title` VARCHAR(100) NOT NULL ,
  `remark` TEXT NULL ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  UNIQUE INDEX `sid_UNIQUE` (`title` ASC) ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Possible types of contacts, such as private, working.';


-- -----------------------------------------------------
-- Table `rfrmgr_contacts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_contacts` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_contacts` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `remark` TEXT NULL ,
  `contact_type_id` INT UNSIGNED NOT NULL ,
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
  `country_code` VARCHAR(10) NOT NULL ,
  `area_code` VARCHAR(10) NOT NULL ,
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
  `abbreviation` VARCHAR(100) NULL ,
  `remark` TEXT NULL ,
  `address_id` INT NOT NULL COMMENT 'standard address of the club. To be used if no address for a team is given.' ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'All clubs.';


-- -----------------------------------------------------
-- Table `rfrmgr_training_level_types`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_training_level_types` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_training_level_types` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `title` VARCHAR(100) NOT NULL ,
  `abbreviation` VARCHAR(20) NULL ,
  `remark` TEXT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  UNIQUE INDEX `sid_UNIQUE` (`title` ASC) )
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
  `club_id` INT UNSIGNED NOT NULL ,
  `address_id` INT UNSIGNED NOT NULL COMMENT 'standard address of the club. To be used if no address for a team is given.' ,
  `number` INT UNSIGNED NULL ,
  `name` VARCHAR(100) NULL ,
  `remark` TEXT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'All teams.';


-- -----------------------------------------------------
-- Table `rfrmgr_database_tables`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_database_tables` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_database_tables` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `sid` VARCHAR(30) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  UNIQUE INDEX `sid_UNIQUE` (`sid` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'All database tables that have to be logged,';


-- -----------------------------------------------------
-- Table `rfrmgr_activity_logs`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_activity_logs` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_activity_logs` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `database_table_id` INT UNSIGNED NOT NULL ,
  `row_id` INT NOT NULL ,
  `column_name` VARCHAR(100) NOT NULL ,
  `old_value` TEXT NULL ,
  `new_value` TEXT NOT NULL ,
  `user_id` INT NOT NULL COMMENT '\n' ,
  `remark` TEXT NULL ,
  `created` DATETIME NOT NULL ,
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
  `start` DATETIME NOT NULL ,
  `end` DATETIME NULL ,
  `remark` TEXT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Assignments.';


-- -----------------------------------------------------
-- Table `rfrmgr_referee_assignment_types`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_referee_assignment_types` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_referee_assignment_types` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `abbreviation` VARCHAR(10) NOT NULL ,
  `title` VARCHAR(100) NOT NULL ,
  `remark` TEXT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  UNIQUE INDEX `abbreviation_UNIQUE` (`abbreviation` ASC) ,
  UNIQUE INDEX `title_UNIQUE` (`title` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Possible types of referee\'s assignments such as umpire, stan' /* comment truncated */;


-- -----------------------------------------------------
-- Table `rfrmgr_assignment_status_types`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_assignment_status_types` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_assignment_status_types` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `sid` VARCHAR(10) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  UNIQUE INDEX `title_UNIQUE` (`sid` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Possible assignment status of a referee (yes, no, maybe). Mo' /* comment truncated */;


-- -----------------------------------------------------
-- Table `rfrmgr_referee_assignments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_referee_assignments` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_referee_assignments` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `assignment_id` INT UNSIGNED NOT NULL ,
  `referee_assignment_type_id` INT UNSIGNED NOT NULL ,
  `referee_id` INT UNSIGNED NOT NULL ,
  `assignment_status_type_id` INT UNSIGNED NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Referee\'s assignments with their role.';


-- -----------------------------------------------------
-- Table `rfrmgr_league_games`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_league_games` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_league_games` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `assignment_id` INT UNSIGNED NOT NULL ,
  `game_number` INT UNSIGNED NOT NULL ,
  `season_id` INT UNSIGNED NOT NULL ,
  `league_id` INT UNSIGNED NOT NULL ,
  `address_id` INT UNSIGNED NULL COMMENT 'Only filled if team plays outside their normal location.' ,
  `filled_referee_report_url` VARCHAR(200) NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Assignments for league games.\nLeague and season can be deduc' /* comment truncated */;


-- -----------------------------------------------------
-- Table `rfrmgr_league_game_team_types`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_league_game_team_types` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_league_game_team_types` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `abbreviation` VARCHAR(10) NOT NULL ,
  `title` VARCHAR(100) NOT NULL ,
  `remark` TEXT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  UNIQUE INDEX `abbreviation_UNIQUE` (`abbreviation` ASC) ,
  UNIQUE INDEX `title_UNIQUE` (`title` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Types of league game teams, such as home team, off team, etc' /* comment truncated */;


-- -----------------------------------------------------
-- Table `rfrmgr_team_seasons`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_team_seasons` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_team_seasons` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `team_id` INT UNSIGNED NOT NULL ,
  `season_id` INT UNSIGNED NOT NULL ,
  `league_id` INT UNSIGNED NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Association of teams in a specified season.';


-- -----------------------------------------------------
-- Table `rfrmgr_league_game_teams`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_league_game_teams` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_league_game_teams` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `league_game_id` INT UNSIGNED NOT NULL ,
  `league_game_team_type_id` INT UNSIGNED NOT NULL ,
  `team_season_id` INT UNSIGNED NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Teams of a league game and their role: home or off team.';


-- -----------------------------------------------------
-- Table `rfrmgr_spokespeople`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_spokespeople` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_spokespeople` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `person_id` INT UNSIGNED NOT NULL ,
  `team_season_id` INT UNSIGNED NULL ,
  `club_id` INT UNSIGNED NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Spokespeople of teams and clubs.';


-- -----------------------------------------------------
-- Table `rfrmgr_pictures`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_pictures` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_pictures` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `person_id` INT UNSIGNED NOT NULL ,
  `url` VARCHAR(200) NOT NULL ,
  `remark` TEXT NULL ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Picture storage (link to pictures, not the pics themselves.';


-- -----------------------------------------------------
-- Table `rfrmgr_training_levels`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_training_levels` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_training_levels` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `referee_id` INT UNSIGNED NOT NULL ,
  `training_level_type_id` INT UNSIGNED NOT NULL ,
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
  `training_level_id` INT UNSIGNED NOT NULL ,
  `update` DATE NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Updates of the training levels.';


-- -----------------------------------------------------
-- Table `rfrmgr_referee_relation_types`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_referee_relation_types` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_referee_relation_types` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `sid` VARCHAR(10) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  UNIQUE INDEX `title_UNIQUE` (`sid` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Possible relations of a referee to a club or league (member,' /* comment truncated */;


-- -----------------------------------------------------
-- Table `rfrmgr_referee_relations`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_referee_relations` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_referee_relations` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `referee_id` INT UNSIGNED NOT NULL ,
  `referee_relation_type_id` INT UNSIGNED NOT NULL ,
  `club_id` INT UNSIGNED NULL ,
  `league_id` INT UNSIGNED NULL ,
  `sex_type_id` INT UNSIGNED NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Relations between referees and clubs resp. leagues.\nClubs: m' /* comment truncated */;


-- -----------------------------------------------------
-- Table `rfrmgr_league_planned_referees`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_league_planned_referees` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_league_planned_referees` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `league_id` INT UNSIGNED NOT NULL ,
  `season_id` INT UNSIGNED NOT NULL ,
  `assignment_type_id` INT UNSIGNED NOT NULL ,
  `quantity` INT UNSIGNED NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Planned numbers of referees by assignment type.';


-- -----------------------------------------------------
-- Table `rfrmgr_urls`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_urls` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_urls` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `contact_id` INT UNSIGNED NOT NULL ,
  `url` VARCHAR(200) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'All urls.';


-- -----------------------------------------------------
-- Table `rfrmgr_referee_report_recipients`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_referee_report_recipients` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_referee_report_recipients` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `referee_report_id` INT NOT NULL ,
  `person_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  UNIQUE INDEX `title_UNIQUE` (`referee_report_id` ASC) ,
  UNIQUE INDEX `person_id_UNIQUE` (`person_id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'All league types.';


-- -----------------------------------------------------
-- Table `rfrmgr_tournaments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_tournaments` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_tournaments` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(10) NOT NULL ,
  `start` DATETIME NOT NULL ,
  `end` DATETIME NOT NULL ,
  `address_id` INT UNSIGNED NOT NULL ,
  `announcement_url` VARCHAR(200) NULL ,
  `information_url` VARCHAR(200) NULL ,
  `club_id` INT UNSIGNED NULL ,
  `person_id` INT UNSIGNED NULL ,
  `remark` TEXT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  UNIQUE INDEX `abbreviation_UNIQUE` (`name` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Tournaments with games.';


-- -----------------------------------------------------
-- Table `rfrmgr_tournament_games`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_tournament_games` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_tournament_games` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `assignment_id` INT UNSIGNED NOT NULL ,
  `tournament_id` INT UNSIGNED NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Assignments for tournament games.';


-- -----------------------------------------------------
-- Table `rfrmgr_assignment_remark_types`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_assignment_remark_types` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_assignment_remark_types` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `title` VARCHAR(100) NOT NULL ,
  `remark` TEXT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  UNIQUE INDEX `title_UNIQUE` (`title` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Types of assignment remarks such as answering machine, call ' /* comment truncated */;


-- -----------------------------------------------------
-- Table `rfrmgr_assignment_remark`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_assignment_remark` ;

CREATE  TABLE IF NOT EXISTS `rfrmgr_assignment_remark` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `referee_assignment_id` INT UNSIGNED NOT NULL ,
  `assignment_remark_type_id` INT UNSIGNED NOT NULL ,
  `remark` TEXT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Remark to a referee assignment.';



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `rfrmgr_user_roles`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `rfrmgr_user_roles` (`id`, `sid`) VALUES (1, 'referee');
INSERT INTO `rfrmgr_user_roles` (`id`, `sid`) VALUES (2, 'statistician');
INSERT INTO `rfrmgr_user_roles` (`id`, `sid`) VALUES (3, 'editor');
INSERT INTO `rfrmgr_user_roles` (`id`, `sid`) VALUES (4, 'administrator');

COMMIT;

-- -----------------------------------------------------
-- Data for table `rfrmgr_database_tables`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `rfrmgr_database_tables` (`id`, `sid`) VALUES (1, 'people');
INSERT INTO `rfrmgr_database_tables` (`id`, `sid`) VALUES (2, 'pictures');

COMMIT;

-- -----------------------------------------------------
-- Data for table `rfrmgr_assignment_status_types`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `rfrmgr_assignment_status_types` (`id`, `sid`) VALUES (1, 'no');
INSERT INTO `rfrmgr_assignment_status_types` (`id`, `sid`) VALUES (2, 'maybe');
INSERT INTO `rfrmgr_assignment_status_types` (`id`, `sid`) VALUES (3, 'yes');

COMMIT;

-- -----------------------------------------------------
-- Data for table `rfrmgr_referee_relation_types`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `rfrmgr_referee_relation_types` (`id`, `sid`) VALUES (1, 'member');
INSERT INTO `rfrmgr_referee_relation_types` (`id`, `sid`) VALUES (2, 'prefer');
INSERT INTO `rfrmgr_referee_relation_types` (`id`, `sid`) VALUES (3, 'avoid');

COMMIT;
