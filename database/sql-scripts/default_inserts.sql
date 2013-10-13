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
