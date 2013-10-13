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
INSERT INTO `rfrmgr_league_game_team_types` (`sid`) VALUES ('home');
INSERT INTO `rfrmgr_league_game_team_types` (`sid`) VALUES ('off');

COMMIT;

-- -----------------------------------------------------
-- Data for table `rfrmgr_referee_assignment_types`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `rfrmgr_referee_assignment_types` (`sid`) VALUES ('umpire');
INSERT INTO `rfrmgr_referee_assignment_types` (`sid`) VALUES ('assump');
INSERT INTO `rfrmgr_referee_assignment_types` (`sid`) VALUES ('referee');
INSERT INTO `rfrmgr_referee_assignment_types` (`sid`) VALUES ('standbyref');
INSERT INTO `rfrmgr_referee_assignment_types` (`sid`) VALUES ('racketctl');
INSERT INTO `rfrmgr_referee_assignment_types` (`sid`) VALUES ('tournmgr');

COMMIT;

-- -----------------------------------------------------
-- Data for table `rfrmgr_referee_relation_types`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `rfrmgr_referee_relation_types` (`sid`) VALUES ('member');
INSERT INTO `rfrmgr_referee_relation_types` (`sid`) VALUES ('prefer');
INSERT INTO `rfrmgr_referee_relation_types` (`sid`) VALUES ('noassignment');

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
INSERT INTO `rfrmgr_status_types` (`sid`) VALUES ('many');
INSERT INTO `rfrmgr_status_types` (`sid`) VALUES ('normal');
INSERT INTO `rfrmgr_status_types` (`sid`) VALUES ('inactiveseason');
INSERT INTO `rfrmgr_status_types` (`sid`) VALUES ('inactive');
INSERT INTO `rfrmgr_status_types` (`sid`) VALUES ('other');

COMMIT;

-- -----------------------------------------------------
-- Data for table `rfrmgr_training_level_types`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `rfrmgr_training_level_types` (`sid`, `rank`) VALUES ('assump', 1);
INSERT INTO `rfrmgr_training_level_types` (`sid`, `rank`) VALUES ('natump', 2);
INSERT INTO `rfrmgr_training_level_types` (`sid`, `rank`) VALUES ('natref', 3);
INSERT INTO `rfrmgr_training_level_types` (`sid`, `rank`) VALUES ('intump', 4);

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

