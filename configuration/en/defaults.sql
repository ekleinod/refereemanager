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
INSERT INTO `rfrmgr_contact_types` (`id`, `title`, `remark`) VALUES (1, 'private', 'Private contacts.');
INSERT INTO `rfrmgr_contact_types` (`id`, `title`, `remark`) VALUES (2, 'business', 'Business contacts.');
INSERT INTO `rfrmgr_contact_types` (`id`, `title`, `remark`) VALUES (3, 'venue', 'Venue contacts.');
INSERT INTO `rfrmgr_contact_types` (`id`, `title`, `remark`) VALUES (4, 'office', 'Office contacts.');
INSERT INTO `rfrmgr_contact_types` (`id`, `title`, `remark`) VALUES (5, 'other', 'Other contacts.');

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
INSERT INTO `rfrmgr_training_level_types` (`id`, `title`, `abbreviation`, `remark`) VALUES (1, 'association umpire', 'aump', NULL);
INSERT INTO `rfrmgr_training_level_types` (`id`, `title`, `abbreviation`, `remark`) VALUES (2, 'national umpire', 'nump', NULL);
INSERT INTO `rfrmgr_training_level_types` (`id`, `title`, `abbreviation`, `remark`) VALUES (3, 'national referee', 'nref', NULL);
INSERT INTO `rfrmgr_training_level_types` (`id`, `title`, `abbreviation`, `remark`) VALUES (4, 'international umpire', 'iu', NULL);

COMMIT;

-- -----------------------------------------------------
-- Data for table `rfrmgr_status_types`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `rfrmgr_status_types` (`id`, `title`, `remark`) VALUES (1, 'many assignments', 'The referee is interested in many assigments.');
INSERT INTO `rfrmgr_status_types` (`id`, `title`, `remark`) VALUES (2, 'normal', NULL);
INSERT INTO `rfrmgr_status_types` (`id`, `title`, `remark`) VALUES (3, 'not active this season', 'The referee is not active in this season.');
INSERT INTO `rfrmgr_status_types` (`id`, `title`, `remark`) VALUES (4, 'not active', 'The referee is not active.');
INSERT INTO `rfrmgr_status_types` (`id`, `title`, `remark`) VALUES (5, 'other', NULL);

COMMIT;

-- -----------------------------------------------------
-- Data for table `rfrmgr_referee_assignment_types`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `rfrmgr_referee_assignment_types` (`id`, `abbreviation`, `title`, `remark`) VALUES (1, 'ump', 'Umpire.');
INSERT INTO `rfrmgr_referee_assignment_types` (`id`, `abbreviation`, `title`, `remark`) VALUES (2, 'ass. ump', 'Assistant umpire.');
INSERT INTO `rfrmgr_referee_assignment_types` (`id`, `abbreviation`, `title`, `remark`) VALUES (3, 'ref', 'Referee.');
INSERT INTO `rfrmgr_referee_assignment_types` (`id`, `abbreviation`, `title`, `remark`) VALUES (4, 'sby. ref', 'Standby referee.');
INSERT INTO `rfrmgr_referee_assignment_types` (`id`, `abbreviation`, `title`, `remark`) VALUES (5, 'rc', 'Racket control.');
INSERT INTO `rfrmgr_referee_assignment_types` (`id`, `abbreviation`, `title`, `remark`) VALUES (6, 'mgr', 'Tournament management.');

COMMIT;

-- -----------------------------------------------------
-- Data for table `rfrmgr_league_game_team_types`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `rfrmgr_league_game_team_types` (`id`, `abbreviation`, `title`, `remark`) VALUES (1, 'home', 'Home team.');
INSERT INTO `rfrmgr_league_game_team_types` (`id`, `abbreviation`, `title`, `remark`) VALUES (2, 'off', 'Off team.');

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

