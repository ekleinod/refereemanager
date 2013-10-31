-- -----------------------------------------------------
-- Data for tables of default inserts
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Data for table `rfrmgr_assignment_status_types`
-- -----------------------------------------------------
START TRANSACTION;
UPDATE `rfrmgr_assignment_status_types` SET `title`='no' WHERE `sid`='no';
UPDATE `rfrmgr_assignment_status_types` SET `title`='maybe' WHERE `sid`='maybe';
UPDATE `rfrmgr_assignment_status_types` SET `title`='yes' WHERE `sid`='yes';

COMMIT;

-- -----------------------------------------------------
-- Data for table `rfrmgr_league_game_team_types`
-- -----------------------------------------------------
START TRANSACTION;
UPDATE `rfrmgr_league_game_team_types` SET `title`='home team', `abbreviation`='home' WHERE `sid`='home';
UPDATE `rfrmgr_league_game_team_types` SET `title`='off team', `abbreviation`='off' WHERE `sid`='off';

COMMIT;

-- -----------------------------------------------------
-- Data for table `rfrmgr_referee_assignment_types`
-- -----------------------------------------------------
START TRANSACTION;
UPDATE `rfrmgr_referee_assignment_types` SET `title`='umpire', `abbreviation`='ump' WHERE `sid`='umpire';
UPDATE `rfrmgr_referee_assignment_types` SET `title`='assistant umpire', `abbreviation`='ass. ump' WHERE `sid`='assump';
UPDATE `rfrmgr_referee_assignment_types` SET `title`='referee', `abbreviation`='ref' WHERE `sid`='referee';
UPDATE `rfrmgr_referee_assignment_types` SET `title`='standby referee', `abbreviation`='sby. ref' WHERE `sid`='standbyref';
UPDATE `rfrmgr_referee_assignment_types` SET `title`='racket control', `abbreviation`='rc' WHERE `sid`='racketctl';
UPDATE `rfrmgr_referee_assignment_types` SET `title`='tournament management', `abbreviation`='mgr' WHERE `sid`='tournmgr';

COMMIT;

-- -----------------------------------------------------
-- Data for table `rfrmgr_referee_relation_types`
-- -----------------------------------------------------
START TRANSACTION;
UPDATE `rfrmgr_referee_relation_types` SET `title`='member', `remark`='Is member of the club.' WHERE `sid`='member';
UPDATE `rfrmgr_referee_relation_types` SET `title`='referee for', `remark`='Is referee for the club.' WHERE `sid`='reffor';
UPDATE `rfrmgr_referee_relation_types` SET `title`='prefer', `remark`='Prefer club for assignments.' WHERE `sid`='prefer';
UPDATE `rfrmgr_referee_relation_types` SET `title`='no assignment', `remark`='Do not assign to this club.' WHERE `sid`='noassignment';

COMMIT;

-- -----------------------------------------------------
-- Data for table `rfrmgr_sex_types`
-- -----------------------------------------------------
START TRANSACTION;
UPDATE `rfrmgr_sex_types` SET `title`='female', `remark`='Female people.' WHERE `sid`='female';
UPDATE `rfrmgr_sex_types` SET `title`='male', `remark`='Male people.' WHERE `sid`='male';
UPDATE `rfrmgr_sex_types` SET `title`='other', `remark`='People of other sexes.' WHERE `sid`='other';

COMMIT;

-- -----------------------------------------------------
-- Data for table `rfrmgr_status_types`
-- -----------------------------------------------------
START TRANSACTION;
UPDATE `rfrmgr_status_types` SET `title`='many assignments', `style`='bold', `color`=NULL, `bgcolor`=NULL, `remark`='The referee is interested in many assigments.' WHERE `sid`='many';
UPDATE `rfrmgr_status_types` SET `title`='normal', `style`=NULL, `color`=NULL, `bgcolor`=NULL, `remark`='No special activity.' WHERE `sid`='normal';
UPDATE `rfrmgr_status_types` SET `title`='not active this season', `style`=NULL, `color`='999999', `bgcolor`='EEEEEE', `remark`='The referee is not active in this season.' WHERE `sid`='inactiveseason';
UPDATE `rfrmgr_status_types` SET `title`='other', `style`='italic', `color`=NULL, `bgcolor`=NULL, `remark`='Other activity.' WHERE `sid`='other';

COMMIT;

-- -----------------------------------------------------
-- Data for table `rfrmgr_training_level_types`
-- -----------------------------------------------------
START TRANSACTION;
UPDATE `rfrmgr_training_level_types` SET `title`='association umpire', `abbreviation`='AU' WHERE `sid`='assump';
UPDATE `rfrmgr_training_level_types` SET `title`='national umpire', `abbreviation`='NU' WHERE `sid`='natump';
UPDATE `rfrmgr_training_level_types` SET `title`='national referee', `abbreviation`='NR' WHERE `sid`='natref';
UPDATE `rfrmgr_training_level_types` SET `title`='international umpire', `abbreviation`='IU' WHERE `sid`='intump';

COMMIT;

-- -----------------------------------------------------
-- Data for table `rfrmgr_user_roles`
-- -----------------------------------------------------
START TRANSACTION;
UPDATE `rfrmgr_user_roles` SET `title`='referee', `remark`='Referee.' WHERE `sid`='referee';
UPDATE `rfrmgr_user_roles` SET `title`='statistician', `remark`='Statistician: referee''s rights + statistics.' WHERE `sid`='statistician';
UPDATE `rfrmgr_user_roles` SET `title`='editor', `remark`='Editor: statistician''s rights + editing entries.' WHERE `sid`='editor';
UPDATE `rfrmgr_user_roles` SET `title`='administrator', `remark`='Administrator: editor''s rights + deletion and administration.' WHERE `sid`='administrator';

COMMIT;


-- -----------------------------------------------------
-- Other default content
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Data for table `rfrmgr_contact_types`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `rfrmgr_contact_types` (`title`, `abbreviation`, `remark`) VALUES ('private', 'p', 'Private contacts.');
INSERT INTO `rfrmgr_contact_types` (`title`, `abbreviation`, `remark`) VALUES ('business', 'b', 'Business contacts.');
INSERT INTO `rfrmgr_contact_types` (`title`, `abbreviation`, `remark`) VALUES ('venue', 'ven', 'Venue contacts.');
INSERT INTO `rfrmgr_contact_types` (`title`, `abbreviation`, `remark`) VALUES ('office', 'off', 'Office contacts.');
INSERT INTO `rfrmgr_contact_types` (`title`, `abbreviation`, `remark`) VALUES ('other', 'oth', 'Other contacts.');

COMMIT;

-- -----------------------------------------------------
-- Data for table `rfrmgr_league_types`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `rfrmgr_league_types` (`title`, `remark`) VALUES ('women', 'Women''s leagues.');
INSERT INTO `rfrmgr_league_types` (`title`, `remark`) VALUES ('men', 'Men''s leagues.');
INSERT INTO `rfrmgr_league_types` (`title`, `remark`) VALUES ('girls', 'Girls'' leagues.');
INSERT INTO `rfrmgr_league_types` (`title`, `remark`) VALUES ('boys', 'Boys'' leagues.');
INSERT INTO `rfrmgr_league_types` (`title`, `remark`) VALUES ('girl students', 'Girl students'' leagues.');
INSERT INTO `rfrmgr_league_types` (`title`, `remark`) VALUES ('boy students', 'Boy students'' leagues.');
INSERT INTO `rfrmgr_league_types` (`title`, `remark`) VALUES ('recreational', 'Recreational leagues.');
INSERT INTO `rfrmgr_league_types` (`title`, `remark`) VALUES ('seniors', 'Senior''s leagues.');

COMMIT;

-- -----------------------------------------------------
-- Data for table `rfrmgr_assignment_remark_types`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `rfrmgr_assignment_remark_types` (`title`, `remark`) VALUES ('answering machine', 'Spoke to the answering machine.');
INSERT INTO `rfrmgr_assignment_remark_types` (`title`, `remark`) VALUES ('call back', 'Referee wants to be called back.');
INSERT INTO `rfrmgr_assignment_remark_types` (`title`, `remark`) VALUES ('mail returned', 'Mail was returned.');
INSERT INTO `rfrmgr_assignment_remark_types` (`title`, `remark`) VALUES ('other', NULL);

COMMIT;

