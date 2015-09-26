SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';


-- -----------------------------------------------------
-- Table `rfrmgr_sex_types`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_sex_types` ;

CREATE TABLE IF NOT EXISTS `rfrmgr_sex_types` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `sid` VARCHAR(20) NOT NULL,
  `title` VARCHAR(100) NOT NULL,
  `remark` TEXT NULL,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  PRIMARY KEY (`id`),
  UNIQUE INDEX `sid_UNIQUE` (`sid` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Lookup table for sexes.';


-- -----------------------------------------------------
-- Table `rfrmgr_people`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_people` ;

CREATE TABLE IF NOT EXISTS `rfrmgr_people` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `sex_type_id` INT UNSIGNED NOT NULL,
  `title` VARCHAR(50) NULL,
  `first_name` VARCHAR(100) NULL,
  `name` VARCHAR(100) NOT NULL,
  `birthday` DATE NULL,
  `dayofdeath` DATE NULL,
  `remark` TEXT NULL,
  `internal_remark` TEXT NULL,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Alle people go here, specializations, such as referees, have /* comment truncated */ /* their own table.*/';


-- -----------------------------------------------------
-- Table `rfrmgr_league_types`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_league_types` ;

CREATE TABLE IF NOT EXISTS `rfrmgr_league_types` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NOT NULL,
  `sex_type_id` INT UNSIGNED NOT NULL,
  `remark` TEXT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `title_UNIQUE` (`title` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'All league types.';


-- -----------------------------------------------------
-- Table `rfrmgr_leagues`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_leagues` ;

CREATE TABLE IF NOT EXISTS `rfrmgr_leagues` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NOT NULL,
  `abbreviation` VARCHAR(20) NOT NULL,
  `league_type_id` INT UNSIGNED NOT NULL,
  `remark` TEXT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'All leagues.';


-- -----------------------------------------------------
-- Table `rfrmgr_seasons`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_seasons` ;

CREATE TABLE IF NOT EXISTS `rfrmgr_seasons` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `year_start` YEAR NOT NULL,
  `title` VARCHAR(100) NULL,
  `editor_only` TINYINT(1) NULL,
  `remark` TEXT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `year_start_UNIQUE` (`year_start` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'All seasons.';


-- -----------------------------------------------------
-- Table `rfrmgr_referee_reports`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_referee_reports` ;

CREATE TABLE IF NOT EXISTS `rfrmgr_referee_reports` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `league_id` INT UNSIGNED NOT NULL,
  `season_id` INT UNSIGNED NOT NULL,
  `url` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Recipients of umpire reports.';


-- -----------------------------------------------------
-- Table `rfrmgr_referees`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_referees` ;

CREATE TABLE IF NOT EXISTS `rfrmgr_referees` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `person_id` INT UNSIGNED NOT NULL,
  `docs_per_letter` TINYINT(1) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Storage of all referees.';


-- -----------------------------------------------------
-- Table `rfrmgr_status_types`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_status_types` ;

CREATE TABLE IF NOT EXISTS `rfrmgr_status_types` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `sid` VARCHAR(20) NOT NULL,
  `title` VARCHAR(100) NOT NULL,
  `remark` TEXT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `sid_UNIQUE` (`sid` ASC),
  UNIQUE INDEX `title_UNIQUE` (`title` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Possible referee statuses such as \"interested in many assign /* comment truncated */ /*ments", "not active", etc.*/';


-- -----------------------------------------------------
-- Table `rfrmgr_contact_types`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_contact_types` ;

CREATE TABLE IF NOT EXISTS `rfrmgr_contact_types` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NOT NULL,
  `abbreviation` VARCHAR(10) NOT NULL,
  `remark` TEXT NULL,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `sid_UNIQUE` (`title` ASC),
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Possible types of contacts, such as private, working.';


-- -----------------------------------------------------
-- Table `rfrmgr_clubs`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_clubs` ;

CREATE TABLE IF NOT EXISTS `rfrmgr_clubs` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `abbreviation` VARCHAR(100) NULL,
  `remark` TEXT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'All clubs.';


-- -----------------------------------------------------
-- Table `rfrmgr_contacts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_contacts` ;

CREATE TABLE IF NOT EXISTS `rfrmgr_contacts` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `contact_type_id` INT UNSIGNED NOT NULL,
  `title` VARCHAR(100) NULL,
  `is_primary` TINYINT(1) NULL,
  `editor_only` TINYINT(1) NULL,
  `person_id` INT UNSIGNED NULL,
  `club_id` INT UNSIGNED NULL,
  `remark` TEXT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Base table for contacts. Specialized to email, phone etc.';


-- -----------------------------------------------------
-- Table `rfrmgr_addresses`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_addresses` ;

CREATE TABLE IF NOT EXISTS `rfrmgr_addresses` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `contact_id` INT UNSIGNED NOT NULL,
  `street` VARCHAR(100) NOT NULL,
  `number` VARCHAR(100) NULL,
  `zip_code` VARCHAR(100) NULL,
  `city` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'All addresses.';


-- -----------------------------------------------------
-- Table `rfrmgr_phone_numbers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_phone_numbers` ;

CREATE TABLE IF NOT EXISTS `rfrmgr_phone_numbers` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `contact_id` INT UNSIGNED NOT NULL,
  `country_code` VARCHAR(10) NOT NULL,
  `area_code` VARCHAR(10) NOT NULL,
  `number` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'All phone numbers.';


-- -----------------------------------------------------
-- Table `rfrmgr_emails`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_emails` ;

CREATE TABLE IF NOT EXISTS `rfrmgr_emails` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `contact_id` INT UNSIGNED NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'All email adresses.';


-- -----------------------------------------------------
-- Table `rfrmgr_training_level_types`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_training_level_types` ;

CREATE TABLE IF NOT EXISTS `rfrmgr_training_level_types` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `sid` VARCHAR(20) NOT NULL,
  `rank` INT UNSIGNED NOT NULL,
  `title` VARCHAR(100) NOT NULL,
  `abbreviation` VARCHAR(20) NOT NULL,
  `update_interval` INT UNSIGNED NOT NULL,
  `remark` TEXT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `rank_UNIQUE` (`rank` ASC),
  UNIQUE INDEX `sid_UNIQUE` (`sid` ASC),
  UNIQUE INDEX `title_UNIQUE` (`title` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Possible training levels such as international referee, Germ /* comment truncated */ /*an referee.*/';


-- -----------------------------------------------------
-- Table `rfrmgr_teams`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_teams` ;

CREATE TABLE IF NOT EXISTS `rfrmgr_teams` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `club_id` INT UNSIGNED NOT NULL,
  `league_type_id` INT UNSIGNED NOT NULL,
  `number` INT UNSIGNED NULL,
  `name` VARCHAR(100) NULL,
  `remark` TEXT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'All teams.';


-- -----------------------------------------------------
-- Table `rfrmgr_user_roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_user_roles` ;

CREATE TABLE IF NOT EXISTS `rfrmgr_user_roles` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `sid` VARCHAR(20) NOT NULL,
  `title` VARCHAR(100) NOT NULL,
  `remark` TEXT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `sid_UNIQUE` (`sid` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Roles of users, such as admin, editor.';


-- -----------------------------------------------------
-- Table `rfrmgr_users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_users` ;

CREATE TABLE IF NOT EXISTS `rfrmgr_users` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(100) NOT NULL,
  `password` VARCHAR(128) NOT NULL,
  `salt` VARCHAR(128) NOT NULL,
  `user_role_id` INT NOT NULL,
  `person_id` INT NULL,
  `created` DATETIME NOT NULL,
  `modified` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'User table for access rights.';


-- -----------------------------------------------------
-- Table `rfrmgr_database_tables`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_database_tables` ;

CREATE TABLE IF NOT EXISTS `rfrmgr_database_tables` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `table_name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `sid_UNIQUE` (`table_name` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'All database tables that have to be logged,';


-- -----------------------------------------------------
-- Table `rfrmgr_database_columns`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_database_columns` ;

CREATE TABLE IF NOT EXISTS `rfrmgr_database_columns` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `column_name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `sid_UNIQUE` (`column_name` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'All database columns that have to be logged,';


-- -----------------------------------------------------
-- Table `rfrmgr_activity_logs`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_activity_logs` ;

CREATE TABLE IF NOT EXISTS `rfrmgr_activity_logs` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `database_table_id` INT UNSIGNED NOT NULL,
  `database_column_id` INT UNSIGNED NOT NULL,
  `row_id` INT NOT NULL,
  `old_value` TEXT NULL,
  `new_value` TEXT NOT NULL,
  `user_id` INT NOT NULL COMMENT '\n',
  `remark` TEXT NULL,
  `created` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Activity log of selected data tables. Only those tables are  /* comment truncated */ /*logged that are important to the user.*/';


-- -----------------------------------------------------
-- Table `rfrmgr_assignments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_assignments` ;

CREATE TABLE IF NOT EXISTS `rfrmgr_assignments` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `start` DATETIME NOT NULL,
  `end` DATETIME NULL,
  `remark` TEXT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Assignments.';


-- -----------------------------------------------------
-- Table `rfrmgr_referee_assignment_types`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_referee_assignment_types` ;

CREATE TABLE IF NOT EXISTS `rfrmgr_referee_assignment_types` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `sid` VARCHAR(20) NOT NULL,
  `title` VARCHAR(100) NOT NULL,
  `abbreviation` VARCHAR(10) NOT NULL,
  `remark` TEXT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `abbreviation_UNIQUE` (`abbreviation` ASC),
  UNIQUE INDEX `title_UNIQUE` (`title` ASC),
  UNIQUE INDEX `sid_UNIQUE` (`sid` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Possible types of referee\'s assignments such as umpire, stan /* comment truncated */ /*dby umpire, referee, racket control.*/';


-- -----------------------------------------------------
-- Table `rfrmgr_referee_assignment_status_types`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_referee_assignment_status_types` ;

CREATE TABLE IF NOT EXISTS `rfrmgr_referee_assignment_status_types` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `sid` VARCHAR(20) NOT NULL,
  `title` VARCHAR(100) NOT NULL,
  `remark` TEXT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `title_UNIQUE` (`sid` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Possible assignment status of a referee (yes, no, maybe). Mo /* comment truncated */ /*stly relevant for tournament games.*/';


-- -----------------------------------------------------
-- Table `rfrmgr_referee_assignment_remark_types`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_referee_assignment_remark_types` ;

CREATE TABLE IF NOT EXISTS `rfrmgr_referee_assignment_remark_types` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NOT NULL,
  `remark` TEXT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `title_UNIQUE` (`title` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Types of assignment remarks such as answering machine, call  /* comment truncated */ /*back.*/';


-- -----------------------------------------------------
-- Table `rfrmgr_referee_assignments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_referee_assignments` ;

CREATE TABLE IF NOT EXISTS `rfrmgr_referee_assignments` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `assignment_id` INT UNSIGNED NOT NULL,
  `referee_assignment_type_id` INT UNSIGNED NOT NULL,
  `referee_id` INT UNSIGNED NOT NULL,
  `referee_assignment_status_type_id` INT UNSIGNED NULL,
  `referee_assignment_remark_type_id` INT UNSIGNED NULL,
  `remark` TEXT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Referee\'s assignments with their role.';


-- -----------------------------------------------------
-- Table `rfrmgr_team_seasons`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_team_seasons` ;

CREATE TABLE IF NOT EXISTS `rfrmgr_team_seasons` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `team_id` INT UNSIGNED NOT NULL,
  `season_id` INT UNSIGNED NOT NULL,
  `league_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Association of teams in a specified season.';


-- -----------------------------------------------------
-- Table `rfrmgr_team_venues`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_team_venues` ;

CREATE TABLE IF NOT EXISTS `rfrmgr_team_venues` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `team_season_id` INT UNSIGNED NOT NULL,
  `contact_id` INT UNSIGNED NOT NULL,
  `number` INT UNSIGNED NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Venues of teams in a season.';


-- -----------------------------------------------------
-- Table `rfrmgr_league_games`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_league_games` ;

CREATE TABLE IF NOT EXISTS `rfrmgr_league_games` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `assignment_id` INT UNSIGNED NOT NULL,
  `game_number` INT UNSIGNED NOT NULL,
  `season_id` INT UNSIGNED NOT NULL,
  `league_id` INT UNSIGNED NOT NULL,
  `team_venue_id` INT UNSIGNED NULL COMMENT 'Only filled if team plays outside their normal location.',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Assignments for league games.\nLeague and season can be deduc /* comment truncated */ /*ed from the associated teams, they are stored because no team could be specified or teams of different seasons/leagues (e.g. relegation games).*/';


-- -----------------------------------------------------
-- Table `rfrmgr_league_game_team_types`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_league_game_team_types` ;

CREATE TABLE IF NOT EXISTS `rfrmgr_league_game_team_types` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `sid` VARCHAR(20) NOT NULL,
  `title` VARCHAR(100) NOT NULL,
  `abbreviation` VARCHAR(10) NOT NULL,
  `remark` TEXT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `abbreviation_UNIQUE` (`abbreviation` ASC),
  UNIQUE INDEX `title_UNIQUE` (`title` ASC),
  UNIQUE INDEX `sid_UNIQUE` (`sid` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Types of league game teams, such as home team, off team, etc /* comment truncated */ /*.*/';


-- -----------------------------------------------------
-- Table `rfrmgr_league_game_teams`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_league_game_teams` ;

CREATE TABLE IF NOT EXISTS `rfrmgr_league_game_teams` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `league_game_id` INT UNSIGNED NOT NULL,
  `league_game_team_type_id` INT UNSIGNED NOT NULL,
  `team_season_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Teams of a league game and their role: home or off team.';


-- -----------------------------------------------------
-- Table `rfrmgr_spokespeople`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_spokespeople` ;

CREATE TABLE IF NOT EXISTS `rfrmgr_spokespeople` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `person_id` INT UNSIGNED NOT NULL,
  `team_season_id` INT UNSIGNED NULL,
  `club_id` INT UNSIGNED NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Spokespeople of teams and clubs.';


-- -----------------------------------------------------
-- Table `rfrmgr_pictures`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_pictures` ;

CREATE TABLE IF NOT EXISTS `rfrmgr_pictures` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `person_id` INT UNSIGNED NOT NULL,
  `url` VARCHAR(200) NOT NULL,
  `remark` TEXT NULL,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Picture storage (link to pictures, not the pics themselves.';


-- -----------------------------------------------------
-- Table `rfrmgr_training_levels`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_training_levels` ;

CREATE TABLE IF NOT EXISTS `rfrmgr_training_levels` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `referee_id` INT UNSIGNED NOT NULL,
  `training_level_type_id` INT UNSIGNED NOT NULL,
  `since` DATE NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Achieved training levels for referees.';


-- -----------------------------------------------------
-- Table `rfrmgr_training_updates`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_training_updates` ;

CREATE TABLE IF NOT EXISTS `rfrmgr_training_updates` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `training_level_id` INT UNSIGNED NOT NULL,
  `update` DATE NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Updates of the training levels.';


-- -----------------------------------------------------
-- Table `rfrmgr_referee_relation_types`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_referee_relation_types` ;

CREATE TABLE IF NOT EXISTS `rfrmgr_referee_relation_types` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `sid` VARCHAR(20) NOT NULL,
  `title` VARCHAR(100) NOT NULL,
  `remark` TEXT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `title_UNIQUE` (`title` ASC),
  UNIQUE INDEX `sid_UNIQUE` (`sid` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Club relations of a referee: member, reffor.';


-- -----------------------------------------------------
-- Table `rfrmgr_referee_relations`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_referee_relations` ;

CREATE TABLE IF NOT EXISTS `rfrmgr_referee_relations` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `referee_id` INT UNSIGNED NOT NULL,
  `referee_relation_type_id` INT UNSIGNED NOT NULL,
  `club_id` INT UNSIGNED NOT NULL,
  `season_id` INT UNSIGNED NOT NULL,
  `remark` TEXT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Relations between referees and clubs: member and/or reffor.';


-- -----------------------------------------------------
-- Table `rfrmgr_league_planned_referees`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_league_planned_referees` ;

CREATE TABLE IF NOT EXISTS `rfrmgr_league_planned_referees` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `league_id` INT UNSIGNED NOT NULL,
  `season_id` INT UNSIGNED NOT NULL,
  `referee_assignment_type_id` INT UNSIGNED NOT NULL,
  `quantity` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Planned numbers of referees by assignment type.';


-- -----------------------------------------------------
-- Table `rfrmgr_urls`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_urls` ;

CREATE TABLE IF NOT EXISTS `rfrmgr_urls` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `contact_id` INT UNSIGNED NOT NULL,
  `url` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'All urls.';


-- -----------------------------------------------------
-- Table `rfrmgr_referee_report_recipients`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_referee_report_recipients` ;

CREATE TABLE IF NOT EXISTS `rfrmgr_referee_report_recipients` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `referee_report_id` INT NOT NULL,
  `person_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `title_UNIQUE` (`referee_report_id` ASC),
  UNIQUE INDEX `person_id_UNIQUE` (`person_id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'All league types.';


-- -----------------------------------------------------
-- Table `rfrmgr_tournaments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_tournaments` ;

CREATE TABLE IF NOT EXISTS `rfrmgr_tournaments` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(10) NOT NULL,
  `start` DATETIME NOT NULL,
  `end` DATETIME NOT NULL,
  `announcement_url` VARCHAR(200) NULL,
  `information_url` VARCHAR(200) NULL,
  `club_id` INT UNSIGNED NULL,
  `person_id` INT UNSIGNED NULL,
  `remark` TEXT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `abbreviation_UNIQUE` (`name` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Tournaments with games.';


-- -----------------------------------------------------
-- Table `rfrmgr_tournament_games`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_tournament_games` ;

CREATE TABLE IF NOT EXISTS `rfrmgr_tournament_games` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `assignment_id` INT UNSIGNED NOT NULL,
  `tournament_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Assignments for tournament games.';


-- -----------------------------------------------------
-- Table `rfrmgr_referee_statuses`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_referee_statuses` ;

CREATE TABLE IF NOT EXISTS `rfrmgr_referee_statuses` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `referee_id` INT UNSIGNED NOT NULL,
  `status_type_id` INT UNSIGNED NOT NULL,
  `season_id` INT UNSIGNED NOT NULL,
  `remark` TEXT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Referee\'s status.';


-- -----------------------------------------------------
-- Table `rfrmgr_person_preferences`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_person_preferences` ;

CREATE TABLE IF NOT EXISTS `rfrmgr_person_preferences` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `person_id` INT UNSIGNED NOT NULL,
  `language` VARCHAR(10) NULL,
  `assignment_notification_interval` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `sid_UNIQUE` (`person_id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Preferences for a person.';


-- -----------------------------------------------------
-- Table `rfrmgr_tournament_venues`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_tournament_venues` ;

CREATE TABLE IF NOT EXISTS `rfrmgr_tournament_venues` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `tournament_id` INT UNSIGNED NOT NULL,
  `contact_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Venues of tournaments.';


-- -----------------------------------------------------
-- Table `rfrmgr_wish_types`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_wish_types` ;

CREATE TABLE IF NOT EXISTS `rfrmgr_wish_types` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `sid` VARCHAR(20) NOT NULL,
  `title` VARCHAR(100) NOT NULL,
  `remark` TEXT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `title_UNIQUE` (`title` ASC),
  UNIQUE INDEX `sid_UNIQUE` (`sid` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Wish types of a referee: prefer, avoid.';


-- -----------------------------------------------------
-- Table `rfrmgr_wishes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rfrmgr_wishes` ;

CREATE TABLE IF NOT EXISTS `rfrmgr_wishes` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `referee_id` INT UNSIGNED NOT NULL,
  `wish_type_id` INT UNSIGNED NOT NULL,
  `club_id` INT UNSIGNED NULL,
  `league_id` INT UNSIGNED NULL,
  `sex_type_id` INT UNSIGNED NULL,
  `saturday` TINYINT(1) NULL,
  `sunday` TINYINT(1) NULL,
  `tournament` TINYINT(1) NULL,
  `remark` TEXT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Referees\' wishes for their assignments.';


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
