-- -----------------------------------------------------
-- Daten für die Tabelle `rfrmgr_sex_types`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `rfrmgr_sex_types` (`id`, `title`, `remark`) VALUES (1, 'weiblich', 'Mädchen und Frauen.');
INSERT INTO `rfrmgr_sex_types` (`id`, `title`, `remark`) VALUES (2, 'männlich', 'Jungen und Männer.');
INSERT INTO `rfrmgr_sex_types` (`id`, `title`, `remark`) VALUES (3, 'andere', 'Menschen anderen Geschlechts.');

COMMIT;

-- -----------------------------------------------------
-- Daten für die Tabelle `rfrmgr_contact_types`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `rfrmgr_contact_types` (`id`, `title`, `remark`) VALUES (1, 'privat', 'Privater Kontakt.');
INSERT INTO `rfrmgr_contact_types` (`id`, `title`, `remark`) VALUES (2, 'geschäftlich', 'Geschäftskontakt.');
INSERT INTO `rfrmgr_contact_types` (`id`, `title`, `remark`) VALUES (3, 'andere', 'Anderer Kontakt.');

COMMIT;

-- -----------------------------------------------------
-- Daten für die Tabelle `rfrmgr_league_types`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `rfrmgr_league_types` (`id`, `title`, `remark`) VALUES (1, 'Frauen', 'Frauenliga.');
INSERT INTO `rfrmgr_league_types` (`id`, `title`, `remark`) VALUES (2, 'Herren', 'Herrenliga.');
INSERT INTO `rfrmgr_league_types` (`id`, `title`, `remark`) VALUES (3, 'Mädchen', 'Mädchenliga.');
INSERT INTO `rfrmgr_league_types` (`id`, `title`, `remark`) VALUES (4, 'Jungen', 'Jungenliga.');
INSERT INTO `rfrmgr_league_types` (`id`, `title`, `remark`) VALUES (5, 'Schülerinnen', 'Schülerinnnenliga.');
INSERT INTO `rfrmgr_league_types` (`id`, `title`, `remark`) VALUES (6, 'Schüler', 'Schülerliga.');
INSERT INTO `rfrmgr_league_types` (`id`, `title`, `remark`) VALUES (7, 'Freizeit', 'Freizeitliga.');
INSERT INTO `rfrmgr_league_types` (`id`, `title`, `remark`) VALUES (8, 'Senioren', 'Seniorenliga.');

COMMIT;

-- -----------------------------------------------------
-- Daten für die Tabelle `rfrmgr_training_level_types`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `rfrmgr_training_level_types` (`id`, `title`, `abbreviation`, `remark`) VALUES (1, 'association umpire', 'aump', NULL);
INSERT INTO `rfrmgr_training_level_types` (`id`, `title`, `abbreviation`, `remark`) VALUES (2, 'national umpire', 'nump', NULL);
INSERT INTO `rfrmgr_training_level_types` (`id`, `title`, `abbreviation`, `remark`) VALUES (3, 'national referee', 'nref', NULL);
INSERT INTO `rfrmgr_training_level_types` (`id`, `title`, `abbreviation`, `remark`) VALUES (4, 'international umpire', 'iu', NULL);

COMMIT;

-- -----------------------------------------------------
-- Daten für die Tabelle `rfrmgr_status_types`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `rfrmgr_status_types` (`id`, `title`, `remark`) VALUES (1, 'viele', 'Schiedsrichter_in ist an besonders vielen Einsätzen interessiert.');
INSERT INTO `rfrmgr_status_types` (`id`, `title`, `remark`) VALUES (2, 'normal', NULL);
INSERT INTO `rfrmgr_status_types` (`id`, `title`, `remark`) VALUES (3, 'nicht aktiv diese Saison', 'Schiedsrichter_in ist diese Saison nicht aktiv.');
INSERT INTO `rfrmgr_status_types` (`id`, `title`, `remark`) VALUES (4, 'nicht aktiv', 'Schiedsrichter_in ist nicht aktiv.');
INSERT INTO `rfrmgr_status_types` (`id`, `title`, `remark`) VALUES (5, 'anderes', NULL);

COMMIT;

-- -----------------------------------------------------
-- Daten für die Tabelle `rfrmgr_referee_assignment_types`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `rfrmgr_referee_assignment_types` (`id`, `abbreviation`, `title`, `remark`) VALUES (1, 'SR', 'Schiedsrichter_in.');
INSERT INTO `rfrmgr_referee_assignment_types` (`id`, `abbreviation`, `title`, `remark`) VALUES (2, 'SRA', 'Schiedsrichter_in-Assistent_in.');
INSERT INTO `rfrmgr_referee_assignment_types` (`id`, `abbreviation`, `title`, `remark`) VALUES (3, 'OSR', 'Oberschiedsrichter_in.');
INSERT INTO `rfrmgr_referee_assignment_types` (`id`, `abbreviation`, `title`, `remark`) VALUES (4, 'St. OSR', 'Stellvertretende_r Oberschiedsrichter_in.');
INSERT INTO `rfrmgr_referee_assignment_types` (`id`, `abbreviation`, `title`, `remark`) VALUES (5, 'SK', 'Schlägerkontrolle.');
INSERT INTO `rfrmgr_referee_assignment_types` (`id`, `abbreviation`, `title`, `remark`) VALUES (6, 'TM', 'Turniermanagement.');

COMMIT;

-- -----------------------------------------------------
-- Daten für die Tabelle `rfrmgr_league_game_team_types`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `rfrmgr_league_game_team_types` (`id`, `abbreviation`, `title`, `remark`) VALUES (1, 'H', 'Heimmannschaft.');
INSERT INTO `rfrmgr_league_game_team_types` (`id`, `abbreviation`, `title`, `remark`) VALUES (2, 'A', 'Auswärtsmannschaft.');

COMMIT;

-- -----------------------------------------------------
-- Daten für die Tabelle `rfrmgr_assignment_remark_types`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `rfrmgr_assignment_remark_types` (`id`, `title`, `remark`) VALUES (1, 'AB', 'Auf Anrufbeantworter gesprochen.');
INSERT INTO `rfrmgr_assignment_remark_types` (`id`, `title`, `remark`) VALUES (2, 'Rückruf', 'Schiedsrichter_in möchte zurückgerufen werden.');
INSERT INTO `rfrmgr_assignment_remark_types` (`id`, `title`, `remark`) VALUES (3, 'Mail zurück', 'Mail wurde nicht angenommen.');
INSERT INTO `rfrmgr_assignment_remark_types` (`id`, `title`, `remark`) VALUES (4, 'anderes', NULL);

COMMIT;


