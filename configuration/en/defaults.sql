-- -----------------------------------------------------
-- Data for table `rfrmgr_sex_types`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `rfrmgr_sex_types` (`id`, `title`, `remark`) VALUES (1, 'female', 'Female people.');
INSERT INTO `rfrmgr_sex_types` (`id`, `title`, `remark`) VALUES (2, 'male', 'Male people.');
INSERT INTO `rfrmgr_sex_types` (`id`, `title`, `remark`) VALUES (3, 'other', 'People of other sexes.');

COMMIT;

-- -----------------------------------------------------
-- Data for table `rfrmgr_contact_types`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `rfrmgr_contact_types` (`id`, `title`, `short`, `remark`) VALUES (1, 'private', 'p', 'Private contacts.');
INSERT INTO `rfrmgr_contact_types` (`id`, `title`, `short`, `remark`) VALUES (2, 'business', 'b', 'Business contacts.');
INSERT INTO `rfrmgr_contact_types` (`id`, `title`, `short`, `remark`) VALUES (3, 'venue', 'ven', 'Venue contacts.');
INSERT INTO `rfrmgr_contact_types` (`id`, `title`, `short`, `remark`) VALUES (4, 'office', 'off', 'Office contacts.');
INSERT INTO `rfrmgr_contact_types` (`id`, `title`, `short`, `remark`) VALUES (5, 'other', 'oth', 'Other contacts.');

COMMIT;

-- -----------------------------------------------------
-- Data for table `rfrmgr_league_types`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `rfrmgr_league_types` (`id`, `title`, `remark`) VALUES (1, 'women', 'Women''s leagues.');
INSERT INTO `rfrmgr_league_types` (`id`, `title`, `remark`) VALUES (2, 'men', 'Men''s leagues.');
INSERT INTO `rfrmgr_league_types` (`id`, `title`, `remark`) VALUES (3, 'girls', 'Girls'' leagues.');
INSERT INTO `rfrmgr_league_types` (`id`, `title`, `remark`) VALUES (4, 'boys', 'Boys'' leagues.');
INSERT INTO `rfrmgr_league_types` (`id`, `title`, `remark`) VALUES (5, 'girl students', 'Girl students'' leagues.');
INSERT INTO `rfrmgr_league_types` (`id`, `title`, `remark`) VALUES (6, 'boy students', 'Boy students'' leagues.');
INSERT INTO `rfrmgr_league_types` (`id`, `title`, `remark`) VALUES (7, 'recreational', 'Recreational leagues.');
INSERT INTO `rfrmgr_league_types` (`id`, `title`, `remark`) VALUES (8, 'seniors', 'Senior''s leagues.');

COMMIT;

-- -----------------------------------------------------
-- Data for table `rfrmgr_training_level_types`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `rfrmgr_training_level_types` (`id`, `title`, `abbreviation`, `rank`, `remark`) VALUES (1, 'association umpire', 'aump', 1, NULL);
INSERT INTO `rfrmgr_training_level_types` (`id`, `title`, `abbreviation`, `rank`, `remark`) VALUES (2, 'national umpire', 'nump', 2, NULL);
INSERT INTO `rfrmgr_training_level_types` (`id`, `title`, `abbreviation`, `rank`, `remark`) VALUES (3, 'national referee', 'nref', 3, NULL);
INSERT INTO `rfrmgr_training_level_types` (`id`, `title`, `abbreviation`, `rank`, `remark`) VALUES (4, 'international umpire', 'iu', 4, NULL);

COMMIT;

-- -----------------------------------------------------
-- Data for table `rfrmgr_status_types`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `rfrmgr_status_types` (`id`, `title`, `style`, `color`, `remark`) VALUES (1, 'many assignments', 'bold', NULL, 'The referee is interested in many assigments.');
INSERT INTO `rfrmgr_status_types` (`id`, `title`, `style`, `color`, `remark`) VALUES (2, 'normal', NULL, NULL, 'No special activity.');
INSERT INTO `rfrmgr_status_types` (`id`, `title`, `style`, `color`, `remark`) VALUES (3, 'not active this season', NULL, 'FFD373', 'The referee is not active in this season.');
INSERT INTO `rfrmgr_status_types` (`id`, `title`, `style`, `color`, `remark`) VALUES (4, 'not active', NULL, 'FFE573', 'The referee is not active.');
INSERT INTO `rfrmgr_status_types` (`id`, `title`, `style`, `color`, `remark`) VALUES (5, 'other', 'italic', NULL, 'Other activity.');

COMMIT;

-- -----------------------------------------------------
-- Data for table `rfrmgr_referee_assignment_types`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `rfrmgr_referee_assignment_types` (`id`, `abbreviation`, `title`, `remark`) VALUES (1, 'ump', 'Umpire.', NULL);
INSERT INTO `rfrmgr_referee_assignment_types` (`id`, `abbreviation`, `title`, `remark`) VALUES (2, 'ass. ump', 'Assistant umpire.', NULL);
INSERT INTO `rfrmgr_referee_assignment_types` (`id`, `abbreviation`, `title`, `remark`) VALUES (3, 'ref', 'Referee.', NULL);
INSERT INTO `rfrmgr_referee_assignment_types` (`id`, `abbreviation`, `title`, `remark`) VALUES (4, 'sby. ref', 'Standby referee.', NULL);
INSERT INTO `rfrmgr_referee_assignment_types` (`id`, `abbreviation`, `title`, `remark`) VALUES (5, 'rc', 'Racket control.', NULL);
INSERT INTO `rfrmgr_referee_assignment_types` (`id`, `abbreviation`, `title`, `remark`) VALUES (6, 'mgr', 'Tournament management.', NULL);

COMMIT;

-- -----------------------------------------------------
-- Data for table `rfrmgr_league_game_team_types`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `rfrmgr_league_game_team_types` (`id`, `abbreviation`, `title`, `remark`) VALUES (1, 'home', 'Home team.', NULL);
INSERT INTO `rfrmgr_league_game_team_types` (`id`, `abbreviation`, `title`, `remark`) VALUES (2, 'off', 'Off team.', NULL);

COMMIT;

-- -----------------------------------------------------
-- Data for table `rfrmgr_assignment_remark_types`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `rfrmgr_assignment_remark_types` (`id`, `title`, `remark`) VALUES (1, 'answering machine', 'Spoke to the answering machine.');
INSERT INTO `rfrmgr_assignment_remark_types` (`id`, `title`, `remark`) VALUES (2, 'call back', 'Referee wants to be called back.');
INSERT INTO `rfrmgr_assignment_remark_types` (`id`, `title`, `remark`) VALUES (3, 'mail returned', 'Mail was returned.');
INSERT INTO `rfrmgr_assignment_remark_types` (`id`, `title`, `remark`) VALUES (4, 'other', NULL);

COMMIT;

