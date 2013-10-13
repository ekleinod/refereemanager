-- -----------------------------------------------------
-- Data for table `rfrmgr_assignment_status_types`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `rfrmgr_assignment_status_types` (`id`, `sid`) VALUES (1, 'no');
INSERT INTO `rfrmgr_assignment_status_types` (`id`, `sid`) VALUES (2, 'maybe');
INSERT INTO `rfrmgr_assignment_status_types` (`id`, `sid`) VALUES (3, 'yes');

COMMIT;

-- -----------------------------------------------------
-- Data for table `rfrmgr_database_columns`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `rfrmgr_database_columns` (`id`, `column_name`) VALUES (1, 'start');
INSERT INTO `rfrmgr_database_columns` (`id`, `column_name`) VALUES (2, 'end');

COMMIT;

-- -----------------------------------------------------
-- Data for table `rfrmgr_database_tables`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `rfrmgr_database_tables` (`id`, `table_name`) VALUES (1, 'assignments');
INSERT INTO `rfrmgr_database_tables` (`id`, `table_name`) VALUES (2, 'referee_assignments');

COMMIT;

-- -----------------------------------------------------
-- Data for table `rfrmgr_referee_relation_types`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `rfrmgr_referee_relation_types` (`id`, `sid`) VALUES (1, 'member');
INSERT INTO `rfrmgr_referee_relation_types` (`id`, `sid`) VALUES (2, 'prefer');
INSERT INTO `rfrmgr_referee_relation_types` (`id`, `sid`) VALUES (3, 'noassignment');

COMMIT;

-- -----------------------------------------------------
-- Data for table `rfrmgr_sex_types`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `rfrmgr_sex_types` (`id`, `sid`) VALUES (1, 'female');
INSERT INTO `rfrmgr_sex_types` (`id`, `sid`) VALUES (2, 'male');
INSERT INTO `rfrmgr_sex_types` (`id`, `sid`) VALUES (3, 'other');

COMMIT;

-- -----------------------------------------------------
-- Data for table `rfrmgr_status_types`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `rfrmgr_status_types` (`id`, `sid`) VALUES (1, 'many');
INSERT INTO `rfrmgr_status_types` (`id`, `sid`) VALUES (2, 'normal');
INSERT INTO `rfrmgr_status_types` (`id`, `sid`) VALUES (3, 'inactive');
INSERT INTO `rfrmgr_status_types` (`id`, `sid`) VALUES (4, 'inactiveseason');
INSERT INTO `rfrmgr_status_types` (`id`, `sid`) VALUES (5, 'other');

COMMIT;

-- -----------------------------------------------------
-- Data for table `rfrmgr_user_roles`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `rfrmgr_user_roles` (`id`, `sid`) VALUES (1, 'referee');
INSERT INTO `rfrmgr_user_roles` (`id`, `sid`) VALUES (2, 'statistician');
INSERT INTO `rfrmgr_user_roles` (`id`, `sid`) VALUES (3, 'editor');
INSERT INTO `rfrmgr_user_roles` (`id`, `sid`) VALUES (4, 'administrator');

COMMIT;

