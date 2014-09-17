-- -----------------------------------------------------
-- Daten für die Tabellen mit default-Werten
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Daten für die Tabelle `rfrmgr_assignment_status_types`
-- -----------------------------------------------------
START TRANSACTION;
UPDATE `rfrmgr_assignment_status_types` SET `title`='nein' WHERE `sid`='no';
UPDATE `rfrmgr_assignment_status_types` SET `title`='evtl.' WHERE `sid`='maybe';
UPDATE `rfrmgr_assignment_status_types` SET `title`='ja' WHERE `sid`='yes';

COMMIT;

-- -----------------------------------------------------
-- Daten für die Tabelle `rfrmgr_league_game_team_types`
-- -----------------------------------------------------
START TRANSACTION;
UPDATE `rfrmgr_league_game_team_types` SET `title`='Heimmannschaft', `abbreviation`='H' WHERE `sid`='home';
UPDATE `rfrmgr_league_game_team_types` SET `title`='Auswärtsmannschaft', `abbreviation`='A' WHERE `sid`='off';

COMMIT;

-- -----------------------------------------------------
-- Daten für die Tabelle `rfrmgr_referee_assignment_types`
-- -----------------------------------------------------
START TRANSACTION;
UPDATE `rfrmgr_referee_assignment_types` SET `title`='Schiedsrichter_in', `abbreviation`='SR' WHERE `sid`='umpire';
UPDATE `rfrmgr_referee_assignment_types` SET `title`='Schiedsrichter_in-Assistent_in', `abbreviation`='SRA' WHERE `sid`='assump';
UPDATE `rfrmgr_referee_assignment_types` SET `title`='Oberschiedsrichter_in', `abbreviation`='OSR' WHERE `sid`='referee';
UPDATE `rfrmgr_referee_assignment_types` SET `title`='Stellvertretende_r Oberschiedsrichter_in', `abbreviation`='St. OSR' WHERE `sid`='standbyref';
UPDATE `rfrmgr_referee_assignment_types` SET `title`='Schlägerkontrolle', `abbreviation`='SK' WHERE `sid`='racketctl';
UPDATE `rfrmgr_referee_assignment_types` SET `title`='Turniermanagement', `abbreviation`='TM' WHERE `sid`='tournmgr';

COMMIT;

-- -----------------------------------------------------
-- Daten für die Tabelle `rfrmgr_referee_relation_types`
-- -----------------------------------------------------
START TRANSACTION;
UPDATE `rfrmgr_referee_relation_types` SET `title`='Mitglied', `remark`='Ist Mitglied des Vereins.' WHERE `sid`='member';
UPDATE `rfrmgr_referee_relation_types` SET `title`='Schiedst für', `remark`='Schiedsrichter_in schiedst für Verein.' WHERE `sid`='reffor';
UPDATE `rfrmgr_referee_relation_types` SET `title`='Bevorzugt schiedsen', `remark`='Vereine, die bevorzugt geschiedst werden.' WHERE `sid`='prefer';
UPDATE `rfrmgr_referee_relation_types` SET `title`='Nicht schiedsen', `remark`='Vereine, die nicht geschiedst werden.' WHERE `sid`='noassignment';

COMMIT;

-- -----------------------------------------------------
-- Daten für die Tabelle `rfrmgr_sex_types`
-- -----------------------------------------------------
START TRANSACTION;
UPDATE `rfrmgr_sex_types` SET `title`='weiblich', `remark`='Mädchen und Frauen.' WHERE `sid`='female';
UPDATE `rfrmgr_sex_types` SET `title`='männlich', `remark`='Jungen und Männer.' WHERE `sid`='male';
UPDATE `rfrmgr_sex_types` SET `title`='andere', `remark`='Menschen anderen Geschlechts.' WHERE `sid`='other';

COMMIT;

-- -----------------------------------------------------
-- Daten für die Tabelle `rfrmgr_status_types`
-- -----------------------------------------------------
START TRANSACTION;
UPDATE `rfrmgr_status_types` SET `title`='viele', `style`='bold', `color`=NULL, `bgcolor`=NULL, `remark`='Schiedsrichter_in ist an besonders vielen Einsätzen interessiert' WHERE `sid`='many';
UPDATE `rfrmgr_status_types` SET `title`='normal', `style`=NULL, `color`=NULL, `bgcolor`=NULL, `remark`='Einsätze ohne spezielle Wünsche' WHERE `sid`='normal';
UPDATE `rfrmgr_status_types` SET `title`='nicht aktiv diese Saison', `style`=NULL, `color`='777777', `bgcolor`=NULL, `remark`='Schiedsrichter_in ist diese Saison nicht aktiv' WHERE `sid`='inactiveseason';
UPDATE `rfrmgr_status_types` SET `title`='nicht aktiv, erhält SR-E-Mails', `style`='italic', `color`='777777', `bgcolor`=NULL, `remark`='Schiedsrichter_in ist nicht aktiv, erhält aber die SR-E-Mails' WHERE `sid`='mailonly';
UPDATE `rfrmgr_status_types` SET `title`='anderes', `style`='italic', `color`=NULL, `bgcolor`=NULL, `remark`='Andere Aktivität' WHERE `sid`='other';

COMMIT;

-- -----------------------------------------------------
-- Daten für die Tabelle `rfrmgr_training_level_types`
-- -----------------------------------------------------
START TRANSACTION;
UPDATE `rfrmgr_training_level_types` SET `title`='Verbandsschiedsrichter', `abbreviation`='VSR' WHERE `sid`='assump';
UPDATE `rfrmgr_training_level_types` SET `title`='Nationaler Schiedsrichter', `abbreviation`='NSR' WHERE `sid`='natump';
UPDATE `rfrmgr_training_level_types` SET `title`='Nationaler Oberschiedsrichter', `abbreviation`='NOSR' WHERE `sid`='natref';
UPDATE `rfrmgr_training_level_types` SET `title`='Internationaler Schiedsrichter', `abbreviation`='IU' WHERE `sid`='intump';

COMMIT;

-- -----------------------------------------------------
-- Daten für die Tabelle `rfrmgr_user_roles`
-- -----------------------------------------------------
START TRANSACTION;
UPDATE `rfrmgr_user_roles` SET `title`='Schiedsrichter_in', `remark`='Schiedsrichter_in.' WHERE `sid`='referee';
UPDATE `rfrmgr_user_roles` SET `title`='Statistiker_in', `remark`='Statistiker_in: Schiedsrichter_innen-Rechte + Statistiken.' WHERE `sid`='statistician';
UPDATE `rfrmgr_user_roles` SET `title`='Editor_in', `remark`='Editor_in: Statistiker_innen-Rechte + Editieren.' WHERE `sid`='editor';
UPDATE `rfrmgr_user_roles` SET `title`='Administrator_in', `remark`='Administrator_in: Editor_innen-Rechte + Löschen und Administrieren.' WHERE `sid`='administrator';

COMMIT;


-- -----------------------------------------------------
-- Daten für die anderen Tabellen
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Daten für die Tabelle `rfrmgr_contact_types`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `rfrmgr_contact_types` (`title`, `abbreviation`, `remark`) VALUES ('privat', 'p', 'Privatkontakt.');
INSERT INTO `rfrmgr_contact_types` (`title`, `abbreviation`, `remark`) VALUES ('geschäftlich', 'd', 'Geschäftskontakt.');
INSERT INTO `rfrmgr_contact_types` (`title`, `abbreviation`, `remark`) VALUES ('Halle', 'SO', 'Spielortkontakt.');
INSERT INTO `rfrmgr_contact_types` (`title`, `abbreviation`, `remark`) VALUES ('Geschäftsstelle', 'GS', 'Geschäftsstelle.');
INSERT INTO `rfrmgr_contact_types` (`title`, `abbreviation`, `remark`) VALUES ('andere', 'and.', 'Anderer Kontakt.');

COMMIT;

-- -----------------------------------------------------
-- Daten für die Tabelle `rfrmgr_league_types`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `rfrmgr_league_types` (`title`, `remark`) VALUES ('Frauen', 'Frauenliga.');
INSERT INTO `rfrmgr_league_types` (`title`, `remark`) VALUES ('Herren', 'Herrenliga.');
INSERT INTO `rfrmgr_league_types` (`title`, `remark`) VALUES ('Mädchen', 'Mädchenliga.');
INSERT INTO `rfrmgr_league_types` (`title`, `remark`) VALUES ('Jungen', 'Jungenliga.');
INSERT INTO `rfrmgr_league_types` (`title`, `remark`) VALUES ('Schülerinnen', 'Schülerinnnenliga.');
INSERT INTO `rfrmgr_league_types` (`title`, `remark`) VALUES ('Schüler', 'Schülerliga.');
INSERT INTO `rfrmgr_league_types` (`title`, `remark`) VALUES ('Freizeit', 'Freizeitliga.');
INSERT INTO `rfrmgr_league_types` (`title`, `remark`) VALUES ('Senioren', 'Seniorenliga.');

COMMIT;

-- -----------------------------------------------------
-- Daten für die Tabelle `rfrmgr_assignment_remark_types`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `rfrmgr_assignment_remark_types` (`title`, `remark`) VALUES ('AB', 'Auf Anrufbeantworter gesprochen.');
INSERT INTO `rfrmgr_assignment_remark_types` (`title`, `remark`) VALUES ('Rückruf', 'Schiedsrichter_in möchte zurückgerufen werden.');
INSERT INTO `rfrmgr_assignment_remark_types` (`title`, `remark`) VALUES ('Mail zurück', 'Mail wurde nicht angenommen.');
INSERT INTO `rfrmgr_assignment_remark_types` (`title`, `remark`) VALUES ('anderes', NULL);

COMMIT;


