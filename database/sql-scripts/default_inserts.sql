-- -----------------------------------------------------
-- Data for table `rfrmgr_assignment_status_types`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `rfrmgr_assignment_status_types` (`sid`) VALUES ('no');
INSERT INTO `rfrmgr_assignment_status_types` (`sid`) VALUES ('maybe');
INSERT INTO `rfrmgr_assignment_status_types` (`sid`) VALUES ('yes');

COMMIT;

-- -----------------------------------------------------
-- Data for table `rfrmgr_database_columns`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `rfrmgr_database_columns` (`column_name`) VALUES ('start');
INSERT INTO `rfrmgr_database_columns` (`column_name`) VALUES ('end');

COMMIT;

-- -----------------------------------------------------
-- Data for table `rfrmgr_database_tables`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `rfrmgr_database_tables` (`table_name`) VALUES ('assignments');
INSERT INTO `rfrmgr_database_tables` (`table_name`) VALUES ('referee_assignments');

COMMIT;

-- -----------------------------------------------------
-- Data for table `rfrmgr_league_game_team_types`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `rfrmgr_league_game_team_types` (`sid`, `title`, `abbreviation`) VALUES ('home', 'home', 'home');
INSERT INTO `rfrmgr_league_game_team_types` (`sid`, `title`, `abbreviation`) VALUES ('off', 'off', 'off');

COMMIT;

-- -----------------------------------------------------
-- Data for table `rfrmgr_referee_assignment_types`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `rfrmgr_referee_assignment_types` (`sid`, `title`, `abbreviation`) VALUES ('umpire', 'umpire', 'umpire');
INSERT INTO `rfrmgr_referee_assignment_types` (`sid`, `title`, `abbreviation`) VALUES ('assump', 'assump', 'assump');
INSERT INTO `rfrmgr_referee_assignment_types` (`sid`, `title`, `abbreviation`) VALUES ('referee', 'referee', 'referee');
INSERT INTO `rfrmgr_referee_assignment_types` (`sid`, `title`, `abbreviation`) VALUES ('standbyref', 'standbyref', 'standbyref');
INSERT INTO `rfrmgr_referee_assignment_types` (`sid`, `title`, `abbreviation`) VALUES ('racketctl', 'racketctl', 'racketctl');
INSERT INTO `rfrmgr_referee_assignment_types` (`sid`, `title`, `abbreviation`) VALUES ('tournmgr', 'tournmgr', 'tournmgr');

COMMIT;

-- -----------------------------------------------------
-- Data for table `rfrmgr_referee_relation_types`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `rfrmgr_referee_relation_types` (`sid`, `title`) VALUES ('member', 'member');
INSERT INTO `rfrmgr_referee_relation_types` (`sid`, `title`) VALUES ('reffor', 'reffor');
INSERT INTO `rfrmgr_referee_relation_types` (`sid`, `title`) VALUES ('prefer', 'prefer');
INSERT INTO `rfrmgr_referee_relation_types` (`sid`, `title`) VALUES ('noassignment', 'noassignment');

COMMIT;

-- -----------------------------------------------------
-- Data for table `rfrmgr_sex_types`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `rfrmgr_sex_types` (`sid`) VALUES ('female');
INSERT INTO `rfrmgr_sex_types` (`sid`) VALUES ('male');
INSERT INTO `rfrmgr_sex_types` (`sid`) VALUES ('other');

COMMIT;

-- -----------------------------------------------------
-- Data for table `rfrmgr_status_types`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `rfrmgr_status_types` (`sid`, `title`) VALUES ('many', 'many');
INSERT INTO `rfrmgr_status_types` (`sid`, `title`) VALUES ('normal', 'normal');
INSERT INTO `rfrmgr_status_types` (`sid`, `title`) VALUES ('inactiveseason', 'inactiveseason');
INSERT INTO `rfrmgr_status_types` (`sid`, `title`) VALUES ('mailonly', 'mailonly');
INSERT INTO `rfrmgr_status_types` (`sid`, `title`) VALUES ('other', 'other');

COMMIT;

-- -----------------------------------------------------
-- Data for table `rfrmgr_training_level_types`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `rfrmgr_training_level_types` (`sid`, `rank`, `title`) VALUES ('assump', 1, 'assump');
INSERT INTO `rfrmgr_training_level_types` (`sid`, `rank`, `title`) VALUES ('natump', 2, 'natump');
INSERT INTO `rfrmgr_training_level_types` (`sid`, `rank`, `title`) VALUES ('natref', 3, 'natref');
INSERT INTO `rfrmgr_training_level_types` (`sid`, `rank`, `title`) VALUES ('intump', 4, 'intump');

COMMIT;

-- -----------------------------------------------------
-- Data for table `rfrmgr_user_roles`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `rfrmgr_user_roles` (`sid`) VALUES ('referee');
INSERT INTO `rfrmgr_user_roles` (`sid`) VALUES ('statistician');
INSERT INTO `rfrmgr_user_roles` (`sid`) VALUES ('editor');
INSERT INTO `rfrmgr_user_roles` (`sid`) VALUES ('administrator');

COMMIT;

