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
INSERT INTO `rfrmgr_contact_types` (`id`, `title`, `short`, `remark`) VALUES (1, 'privat', 'p', 'Privater Kontakt.');
INSERT INTO `rfrmgr_contact_types` (`id`, `title`, `short`, `remark`) VALUES (2, 'geschäftlich', 'd', 'Geschäftskontakt.');
INSERT INTO `rfrmgr_contact_types` (`id`, `title`, `short`, `remark`) VALUES (3, 'Halle', 'SO', 'Spielortkontakt.');
INSERT INTO `rfrmgr_contact_types` (`id`, `title`, `short`, `remark`) VALUES (4, 'Geschäftsstelle', 'GS', 'Geschäftsstelle.');
INSERT INTO `rfrmgr_contact_types` (`id`, `title`, `short`, `remark`) VALUES (5, 'andere', 'and.', 'Anderer Kontakt.');

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
INSERT INTO `rfrmgr_training_level_types` (`id`, `title`, `abbreviation`, `rank`, `remark`) VALUES (1, 'Verbandsschiedsrichter', 'VSR', 1, NULL);
INSERT INTO `rfrmgr_training_level_types` (`id`, `title`, `abbreviation`, `rank`, `remark`) VALUES (2, 'Nationaler Schiedsrichter', 'NSR', 2, NULL);
INSERT INTO `rfrmgr_training_level_types` (`id`, `title`, `abbreviation`, `rank`, `remark`) VALUES (3, 'Nationaler Oberschiedsrichter ', 'NOSR', 3, NULL);
INSERT INTO `rfrmgr_training_level_types` (`id`, `title`, `abbreviation`, `rank`, `remark`) VALUES (4, 'Internationaler Schiedsrichter', 'IU', 4, NULL);

COMMIT;

-- -----------------------------------------------------
-- Daten für die Tabelle `rfrmgr_status_types`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `rfrmgr_status_types` (`id`, `title`, `is_special`, `style`, `color`, `bgcolor`, `remark`) VALUES (1, 'viele', 1, 'bold', NULL, NULL, 'Schiedsrichter_in ist an besonders vielen Einsätzen interessiert.');
INSERT INTO `rfrmgr_status_types` (`id`, `title`, `is_special`, `style`, `color`, `bgcolor`, `remark`) VALUES (2, 'normal', 0, NULL, NULL, NULL, 'Einsätze ohne spezielle Wünsche.');
INSERT INTO `rfrmgr_status_types` (`id`, `title`, `is_special`, `style`, `color`, `bgcolor`, `remark`) VALUES (3, 'nicht aktiv diese Saison', 1, NULL, '999999', 'EEEEEE', 'Schiedsrichter_in ist diese Saison nicht aktiv.');
INSERT INTO `rfrmgr_status_types` (`id`, `title`, `is_special`, `style`, `color`, `bgcolor`, `remark`) VALUES (4, 'nicht aktiv', 1, NULL, 'BBBBBB', 'EEEEEE', 'Schiedsrichter_in ist nicht aktiv.');
INSERT INTO `rfrmgr_status_types` (`id`, `title`, `is_special`, `style`, `color`, `bgcolor`, `remark`) VALUES (5, 'anderes', 0, 'italic', NULL, NULL, 'Andere Aktivität.');

COMMIT;

-- -----------------------------------------------------
-- Daten für die Tabelle `rfrmgr_referee_assignment_types`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `rfrmgr_referee_assignment_types` (`id`, `abbreviation`, `title`, `remark`) VALUES (1, 'SR', 'Schiedsrichter_in.', NULL);
INSERT INTO `rfrmgr_referee_assignment_types` (`id`, `abbreviation`, `title`, `remark`) VALUES (2, 'SRA', 'Schiedsrichter_in-Assistent_in.', NULL);
INSERT INTO `rfrmgr_referee_assignment_types` (`id`, `abbreviation`, `title`, `remark`) VALUES (3, 'OSR', 'Oberschiedsrichter_in.', NULL);
INSERT INTO `rfrmgr_referee_assignment_types` (`id`, `abbreviation`, `title`, `remark`) VALUES (4, 'St. OSR', 'Stellvertretende_r Oberschiedsrichter_in.', NULL);
INSERT INTO `rfrmgr_referee_assignment_types` (`id`, `abbreviation`, `title`, `remark`) VALUES (5, 'SK', 'Schlägerkontrolle.', NULL);
INSERT INTO `rfrmgr_referee_assignment_types` (`id`, `abbreviation`, `title`, `remark`) VALUES (6, 'TM', 'Turniermanagement.', NULL);

COMMIT;

-- -----------------------------------------------------
-- Daten für die Tabelle `rfrmgr_league_game_team_types`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `rfrmgr_league_game_team_types` (`id`, `abbreviation`, `title`, `remark`) VALUES (1, 'H', 'Heimmannschaft.', NULL);
INSERT INTO `rfrmgr_league_game_team_types` (`id`, `abbreviation`, `title`, `remark`) VALUES (2, 'A', 'Auswärtsmannschaft.', NULL);

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


