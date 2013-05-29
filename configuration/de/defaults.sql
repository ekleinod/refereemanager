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
INSERT INTO `rfrmgr_contact_types` (`id`, `title`, `remark`) VALUES (3, 'Halle', 'Spielortkontakt.');
INSERT INTO `rfrmgr_contact_types` (`id`, `title`, `remark`) VALUES (4, 'Geschäftsstelle', 'Geschäftsstelle.');
INSERT INTO `rfrmgr_contact_types` (`id`, `title`, `remark`) VALUES (5, 'andere', 'Anderer Kontakt.');

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
INSERT INTO `rfrmgr_training_level_types` (`id`, `title`, `abbreviation`, `remark`) VALUES (1, 'Verbandsschiedsrichter', 'VSR', NULL);
INSERT INTO `rfrmgr_training_level_types` (`id`, `title`, `abbreviation`, `remark`) VALUES (2, 'Nationaler Schiedsrichter', 'NSR', NULL);
INSERT INTO `rfrmgr_training_level_types` (`id`, `title`, `abbreviation`, `remark`) VALUES (3, 'Nationaler Oberschiedsrichter ', 'NOSR', NULL);
INSERT INTO `rfrmgr_training_level_types` (`id`, `title`, `abbreviation`, `remark`) VALUES (4, 'Internationaler Schiedsrichter', 'IU', NULL);

COMMIT;

-- -----------------------------------------------------
-- Daten für die Tabelle `rfrmgr_status_types`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `rfrmgr_status_types` (`id`, `title`, `style`, `color`, `remark`) VALUES (1, 'viele', 'bold', NULL, 'Schiedsrichter_in ist an besonders vielen Einsätzen interessiert.');
INSERT INTO `rfrmgr_status_types` (`id`, `title`, `style`, `color`, `remark`) VALUES (2, 'normal', NULL, NULL, 'Keine spezielle Angabe.');
INSERT INTO `rfrmgr_status_types` (`id`, `title`, `style`, `color`, `remark`) VALUES (3, 'nicht aktiv diese Saison', NULL, 'FFD373', 'Schiedsrichter_in ist diese Saison nicht aktiv.');
INSERT INTO `rfrmgr_status_types` (`id`, `title`, `style`, `color`, `remark`) VALUES (4, 'nicht aktiv', NULL, 'FFE573', 'Schiedsrichter_in ist nicht aktiv.');
INSERT INTO `rfrmgr_status_types` (`id`, `title`, `style`, `color`, `remark`) VALUES (5, 'anderes', 'italic', NULL, 'Andere Aktivität.');

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


