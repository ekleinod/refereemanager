/**
 * This class is generated by jOOQ
 */
package de.edgesoft.refereemanager.jooq;


import de.edgesoft.refereemanager.jooq.tables.RfrmgrActivityLogs;
import de.edgesoft.refereemanager.jooq.tables.RfrmgrAddresses;
import de.edgesoft.refereemanager.jooq.tables.RfrmgrAssignments;
import de.edgesoft.refereemanager.jooq.tables.RfrmgrClubs;
import de.edgesoft.refereemanager.jooq.tables.RfrmgrContactTypes;
import de.edgesoft.refereemanager.jooq.tables.RfrmgrContacts;
import de.edgesoft.refereemanager.jooq.tables.RfrmgrDatabaseColumns;
import de.edgesoft.refereemanager.jooq.tables.RfrmgrDatabaseTables;
import de.edgesoft.refereemanager.jooq.tables.RfrmgrEmails;
import de.edgesoft.refereemanager.jooq.tables.RfrmgrLeagueGameTeamTypes;
import de.edgesoft.refereemanager.jooq.tables.RfrmgrLeagueGameTeams;
import de.edgesoft.refereemanager.jooq.tables.RfrmgrLeagueGames;
import de.edgesoft.refereemanager.jooq.tables.RfrmgrLeaguePlannedReferees;
import de.edgesoft.refereemanager.jooq.tables.RfrmgrLeagueTypes;
import de.edgesoft.refereemanager.jooq.tables.RfrmgrLeagues;
import de.edgesoft.refereemanager.jooq.tables.RfrmgrPeople;
import de.edgesoft.refereemanager.jooq.tables.RfrmgrPersonPreferences;
import de.edgesoft.refereemanager.jooq.tables.RfrmgrPhoneNumbers;
import de.edgesoft.refereemanager.jooq.tables.RfrmgrPictures;
import de.edgesoft.refereemanager.jooq.tables.RfrmgrRefereeAssignmentRemarkTypes;
import de.edgesoft.refereemanager.jooq.tables.RfrmgrRefereeAssignmentStatusTypes;
import de.edgesoft.refereemanager.jooq.tables.RfrmgrRefereeAssignmentTypes;
import de.edgesoft.refereemanager.jooq.tables.RfrmgrRefereeAssignments;
import de.edgesoft.refereemanager.jooq.tables.RfrmgrRefereeRelationTypes;
import de.edgesoft.refereemanager.jooq.tables.RfrmgrRefereeRelations;
import de.edgesoft.refereemanager.jooq.tables.RfrmgrRefereeReportRecipients;
import de.edgesoft.refereemanager.jooq.tables.RfrmgrRefereeReports;
import de.edgesoft.refereemanager.jooq.tables.RfrmgrRefereeStatuses;
import de.edgesoft.refereemanager.jooq.tables.RfrmgrReferees;
import de.edgesoft.refereemanager.jooq.tables.RfrmgrSeasons;
import de.edgesoft.refereemanager.jooq.tables.RfrmgrSexTypes;
import de.edgesoft.refereemanager.jooq.tables.RfrmgrSpokespeople;
import de.edgesoft.refereemanager.jooq.tables.RfrmgrStatusTypes;
import de.edgesoft.refereemanager.jooq.tables.RfrmgrTeamSeasons;
import de.edgesoft.refereemanager.jooq.tables.RfrmgrTeamVenues;
import de.edgesoft.refereemanager.jooq.tables.RfrmgrTeams;
import de.edgesoft.refereemanager.jooq.tables.RfrmgrTournamentGames;
import de.edgesoft.refereemanager.jooq.tables.RfrmgrTournamentVenues;
import de.edgesoft.refereemanager.jooq.tables.RfrmgrTournaments;
import de.edgesoft.refereemanager.jooq.tables.RfrmgrTrainingLevelTypes;
import de.edgesoft.refereemanager.jooq.tables.RfrmgrTrainingLevels;
import de.edgesoft.refereemanager.jooq.tables.RfrmgrTrainingUpdates;
import de.edgesoft.refereemanager.jooq.tables.RfrmgrUrls;
import de.edgesoft.refereemanager.jooq.tables.RfrmgrUserRoles;
import de.edgesoft.refereemanager.jooq.tables.RfrmgrUsers;
import de.edgesoft.refereemanager.jooq.tables.RfrmgrWishTypes;
import de.edgesoft.refereemanager.jooq.tables.RfrmgrWishes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.8.0"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Refereemanager extends SchemaImpl {

    private static final long serialVersionUID = 87212025;

    /**
     * The reference instance of <code>refereemanager</code>
     */
    public static final Refereemanager REFEREEMANAGER = new Refereemanager();

    /**
     * Activity log of selected data tables. Only those tables are  / * comment truncated * / / *logged that are important to the user.* /
     */
    public final RfrmgrActivityLogs RFRMGR_ACTIVITY_LOGS = de.edgesoft.refereemanager.jooq.tables.RfrmgrActivityLogs.RFRMGR_ACTIVITY_LOGS;

    /**
     * All addresses.
     */
    public final RfrmgrAddresses RFRMGR_ADDRESSES = de.edgesoft.refereemanager.jooq.tables.RfrmgrAddresses.RFRMGR_ADDRESSES;

    /**
     * Assignments.
     */
    public final RfrmgrAssignments RFRMGR_ASSIGNMENTS = de.edgesoft.refereemanager.jooq.tables.RfrmgrAssignments.RFRMGR_ASSIGNMENTS;

    /**
     * All clubs.
     */
    public final RfrmgrClubs RFRMGR_CLUBS = de.edgesoft.refereemanager.jooq.tables.RfrmgrClubs.RFRMGR_CLUBS;

    /**
     * Base table for contacts. Specialized to email, phone etc.
     */
    public final RfrmgrContacts RFRMGR_CONTACTS = de.edgesoft.refereemanager.jooq.tables.RfrmgrContacts.RFRMGR_CONTACTS;

    /**
     * Possible types of contacts, such as private, working.
     */
    public final RfrmgrContactTypes RFRMGR_CONTACT_TYPES = de.edgesoft.refereemanager.jooq.tables.RfrmgrContactTypes.RFRMGR_CONTACT_TYPES;

    /**
     * All database columns that have to be logged,
     */
    public final RfrmgrDatabaseColumns RFRMGR_DATABASE_COLUMNS = de.edgesoft.refereemanager.jooq.tables.RfrmgrDatabaseColumns.RFRMGR_DATABASE_COLUMNS;

    /**
     * All database tables that have to be logged,
     */
    public final RfrmgrDatabaseTables RFRMGR_DATABASE_TABLES = de.edgesoft.refereemanager.jooq.tables.RfrmgrDatabaseTables.RFRMGR_DATABASE_TABLES;

    /**
     * All email adresses.
     */
    public final RfrmgrEmails RFRMGR_EMAILS = de.edgesoft.refereemanager.jooq.tables.RfrmgrEmails.RFRMGR_EMAILS;

    /**
     * All leagues.
     */
    public final RfrmgrLeagues RFRMGR_LEAGUES = de.edgesoft.refereemanager.jooq.tables.RfrmgrLeagues.RFRMGR_LEAGUES;

    /**
     * Assignments for league games.
League and season can be deduc / * comment truncated * / / *ed from the associated teams, they are stored because no team could be specified or teams of different seasons/leagues (e.g. relegation games).* /
     */
    public final RfrmgrLeagueGames RFRMGR_LEAGUE_GAMES = de.edgesoft.refereemanager.jooq.tables.RfrmgrLeagueGames.RFRMGR_LEAGUE_GAMES;

    /**
     * Teams of a league game and their role: home or off team.
     */
    public final RfrmgrLeagueGameTeams RFRMGR_LEAGUE_GAME_TEAMS = de.edgesoft.refereemanager.jooq.tables.RfrmgrLeagueGameTeams.RFRMGR_LEAGUE_GAME_TEAMS;

    /**
     * Types of league game teams, such as home team, off team, etc / * comment truncated * / / *.* /
     */
    public final RfrmgrLeagueGameTeamTypes RFRMGR_LEAGUE_GAME_TEAM_TYPES = de.edgesoft.refereemanager.jooq.tables.RfrmgrLeagueGameTeamTypes.RFRMGR_LEAGUE_GAME_TEAM_TYPES;

    /**
     * Planned numbers of referees by assignment type.
     */
    public final RfrmgrLeaguePlannedReferees RFRMGR_LEAGUE_PLANNED_REFEREES = de.edgesoft.refereemanager.jooq.tables.RfrmgrLeaguePlannedReferees.RFRMGR_LEAGUE_PLANNED_REFEREES;

    /**
     * All league types.
     */
    public final RfrmgrLeagueTypes RFRMGR_LEAGUE_TYPES = de.edgesoft.refereemanager.jooq.tables.RfrmgrLeagueTypes.RFRMGR_LEAGUE_TYPES;

    /**
     * Alle people go here, specializations, such as referees, have / * comment truncated * / / * their own table.* /
     */
    public final RfrmgrPeople RFRMGR_PEOPLE = de.edgesoft.refereemanager.jooq.tables.RfrmgrPeople.RFRMGR_PEOPLE;

    /**
     * Preferences for a person.
     */
    public final RfrmgrPersonPreferences RFRMGR_PERSON_PREFERENCES = de.edgesoft.refereemanager.jooq.tables.RfrmgrPersonPreferences.RFRMGR_PERSON_PREFERENCES;

    /**
     * All phone numbers.
     */
    public final RfrmgrPhoneNumbers RFRMGR_PHONE_NUMBERS = de.edgesoft.refereemanager.jooq.tables.RfrmgrPhoneNumbers.RFRMGR_PHONE_NUMBERS;

    /**
     * Picture storage (link to pictures, not the pics themselves.
     */
    public final RfrmgrPictures RFRMGR_PICTURES = de.edgesoft.refereemanager.jooq.tables.RfrmgrPictures.RFRMGR_PICTURES;

    /**
     * Storage of all referees.
     */
    public final RfrmgrReferees RFRMGR_REFEREES = de.edgesoft.refereemanager.jooq.tables.RfrmgrReferees.RFRMGR_REFEREES;

    /**
     * Referee's assignments with their role.
     */
    public final RfrmgrRefereeAssignments RFRMGR_REFEREE_ASSIGNMENTS = de.edgesoft.refereemanager.jooq.tables.RfrmgrRefereeAssignments.RFRMGR_REFEREE_ASSIGNMENTS;

    /**
     * Types of assignment remarks such as answering machine, call  / * comment truncated * / / *back.* /
     */
    public final RfrmgrRefereeAssignmentRemarkTypes RFRMGR_REFEREE_ASSIGNMENT_REMARK_TYPES = de.edgesoft.refereemanager.jooq.tables.RfrmgrRefereeAssignmentRemarkTypes.RFRMGR_REFEREE_ASSIGNMENT_REMARK_TYPES;

    /**
     * Possible assignment status of a referee (yes, no, maybe). Mo / * comment truncated * / / *stly relevant for tournament games.* /
     */
    public final RfrmgrRefereeAssignmentStatusTypes RFRMGR_REFEREE_ASSIGNMENT_STATUS_TYPES = de.edgesoft.refereemanager.jooq.tables.RfrmgrRefereeAssignmentStatusTypes.RFRMGR_REFEREE_ASSIGNMENT_STATUS_TYPES;

    /**
     * Possible types of referee's assignments such as umpire, stan / * comment truncated * / / *dby umpire, referee, racket control.* /
     */
    public final RfrmgrRefereeAssignmentTypes RFRMGR_REFEREE_ASSIGNMENT_TYPES = de.edgesoft.refereemanager.jooq.tables.RfrmgrRefereeAssignmentTypes.RFRMGR_REFEREE_ASSIGNMENT_TYPES;

    /**
     * Relations between referees and clubs: member and/or reffor.
     */
    public final RfrmgrRefereeRelations RFRMGR_REFEREE_RELATIONS = de.edgesoft.refereemanager.jooq.tables.RfrmgrRefereeRelations.RFRMGR_REFEREE_RELATIONS;

    /**
     * Club relations of a referee: member, reffor.
     */
    public final RfrmgrRefereeRelationTypes RFRMGR_REFEREE_RELATION_TYPES = de.edgesoft.refereemanager.jooq.tables.RfrmgrRefereeRelationTypes.RFRMGR_REFEREE_RELATION_TYPES;

    /**
     * Recipients of umpire reports.
     */
    public final RfrmgrRefereeReports RFRMGR_REFEREE_REPORTS = de.edgesoft.refereemanager.jooq.tables.RfrmgrRefereeReports.RFRMGR_REFEREE_REPORTS;

    /**
     * All league types.
     */
    public final RfrmgrRefereeReportRecipients RFRMGR_REFEREE_REPORT_RECIPIENTS = de.edgesoft.refereemanager.jooq.tables.RfrmgrRefereeReportRecipients.RFRMGR_REFEREE_REPORT_RECIPIENTS;

    /**
     * Referee's status.
     */
    public final RfrmgrRefereeStatuses RFRMGR_REFEREE_STATUSES = de.edgesoft.refereemanager.jooq.tables.RfrmgrRefereeStatuses.RFRMGR_REFEREE_STATUSES;

    /**
     * All seasons.
     */
    public final RfrmgrSeasons RFRMGR_SEASONS = de.edgesoft.refereemanager.jooq.tables.RfrmgrSeasons.RFRMGR_SEASONS;

    /**
     * Lookup table for sexes.
     */
    public final RfrmgrSexTypes RFRMGR_SEX_TYPES = de.edgesoft.refereemanager.jooq.tables.RfrmgrSexTypes.RFRMGR_SEX_TYPES;

    /**
     * Spokespeople of teams and clubs.
     */
    public final RfrmgrSpokespeople RFRMGR_SPOKESPEOPLE = de.edgesoft.refereemanager.jooq.tables.RfrmgrSpokespeople.RFRMGR_SPOKESPEOPLE;

    /**
     * Possible referee statuses such as "interested in many assign / * comment truncated * / / *ments", "not active", etc.* /
     */
    public final RfrmgrStatusTypes RFRMGR_STATUS_TYPES = de.edgesoft.refereemanager.jooq.tables.RfrmgrStatusTypes.RFRMGR_STATUS_TYPES;

    /**
     * All teams.
     */
    public final RfrmgrTeams RFRMGR_TEAMS = de.edgesoft.refereemanager.jooq.tables.RfrmgrTeams.RFRMGR_TEAMS;

    /**
     * Association of teams in a specified season.
     */
    public final RfrmgrTeamSeasons RFRMGR_TEAM_SEASONS = de.edgesoft.refereemanager.jooq.tables.RfrmgrTeamSeasons.RFRMGR_TEAM_SEASONS;

    /**
     * Venues of teams in a season.
     */
    public final RfrmgrTeamVenues RFRMGR_TEAM_VENUES = de.edgesoft.refereemanager.jooq.tables.RfrmgrTeamVenues.RFRMGR_TEAM_VENUES;

    /**
     * Tournaments with games.
     */
    public final RfrmgrTournaments RFRMGR_TOURNAMENTS = de.edgesoft.refereemanager.jooq.tables.RfrmgrTournaments.RFRMGR_TOURNAMENTS;

    /**
     * Assignments for tournament games.
     */
    public final RfrmgrTournamentGames RFRMGR_TOURNAMENT_GAMES = de.edgesoft.refereemanager.jooq.tables.RfrmgrTournamentGames.RFRMGR_TOURNAMENT_GAMES;

    /**
     * Venues of tournaments.
     */
    public final RfrmgrTournamentVenues RFRMGR_TOURNAMENT_VENUES = de.edgesoft.refereemanager.jooq.tables.RfrmgrTournamentVenues.RFRMGR_TOURNAMENT_VENUES;

    /**
     * Achieved training levels for referees.
     */
    public final RfrmgrTrainingLevels RFRMGR_TRAINING_LEVELS = de.edgesoft.refereemanager.jooq.tables.RfrmgrTrainingLevels.RFRMGR_TRAINING_LEVELS;

    /**
     * Possible training levels such as international referee, Germ / * comment truncated * / / *an referee.* /
     */
    public final RfrmgrTrainingLevelTypes RFRMGR_TRAINING_LEVEL_TYPES = de.edgesoft.refereemanager.jooq.tables.RfrmgrTrainingLevelTypes.RFRMGR_TRAINING_LEVEL_TYPES;

    /**
     * Updates of the training levels.
     */
    public final RfrmgrTrainingUpdates RFRMGR_TRAINING_UPDATES = de.edgesoft.refereemanager.jooq.tables.RfrmgrTrainingUpdates.RFRMGR_TRAINING_UPDATES;

    /**
     * All urls.
     */
    public final RfrmgrUrls RFRMGR_URLS = de.edgesoft.refereemanager.jooq.tables.RfrmgrUrls.RFRMGR_URLS;

    /**
     * User table for access rights.
     */
    public final RfrmgrUsers RFRMGR_USERS = de.edgesoft.refereemanager.jooq.tables.RfrmgrUsers.RFRMGR_USERS;

    /**
     * Roles of users, such as admin, editor.
     */
    public final RfrmgrUserRoles RFRMGR_USER_ROLES = de.edgesoft.refereemanager.jooq.tables.RfrmgrUserRoles.RFRMGR_USER_ROLES;

    /**
     * Referees' wishes for their assignments.
     */
    public final RfrmgrWishes RFRMGR_WISHES = de.edgesoft.refereemanager.jooq.tables.RfrmgrWishes.RFRMGR_WISHES;

    /**
     * Wish types of a referee: prefer, avoid.
     */
    public final RfrmgrWishTypes RFRMGR_WISH_TYPES = de.edgesoft.refereemanager.jooq.tables.RfrmgrWishTypes.RFRMGR_WISH_TYPES;

    /**
     * No further instances allowed
     */
    private Refereemanager() {
        super("refereemanager", null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            RfrmgrActivityLogs.RFRMGR_ACTIVITY_LOGS,
            RfrmgrAddresses.RFRMGR_ADDRESSES,
            RfrmgrAssignments.RFRMGR_ASSIGNMENTS,
            RfrmgrClubs.RFRMGR_CLUBS,
            RfrmgrContacts.RFRMGR_CONTACTS,
            RfrmgrContactTypes.RFRMGR_CONTACT_TYPES,
            RfrmgrDatabaseColumns.RFRMGR_DATABASE_COLUMNS,
            RfrmgrDatabaseTables.RFRMGR_DATABASE_TABLES,
            RfrmgrEmails.RFRMGR_EMAILS,
            RfrmgrLeagues.RFRMGR_LEAGUES,
            RfrmgrLeagueGames.RFRMGR_LEAGUE_GAMES,
            RfrmgrLeagueGameTeams.RFRMGR_LEAGUE_GAME_TEAMS,
            RfrmgrLeagueGameTeamTypes.RFRMGR_LEAGUE_GAME_TEAM_TYPES,
            RfrmgrLeaguePlannedReferees.RFRMGR_LEAGUE_PLANNED_REFEREES,
            RfrmgrLeagueTypes.RFRMGR_LEAGUE_TYPES,
            RfrmgrPeople.RFRMGR_PEOPLE,
            RfrmgrPersonPreferences.RFRMGR_PERSON_PREFERENCES,
            RfrmgrPhoneNumbers.RFRMGR_PHONE_NUMBERS,
            RfrmgrPictures.RFRMGR_PICTURES,
            RfrmgrReferees.RFRMGR_REFEREES,
            RfrmgrRefereeAssignments.RFRMGR_REFEREE_ASSIGNMENTS,
            RfrmgrRefereeAssignmentRemarkTypes.RFRMGR_REFEREE_ASSIGNMENT_REMARK_TYPES,
            RfrmgrRefereeAssignmentStatusTypes.RFRMGR_REFEREE_ASSIGNMENT_STATUS_TYPES,
            RfrmgrRefereeAssignmentTypes.RFRMGR_REFEREE_ASSIGNMENT_TYPES,
            RfrmgrRefereeRelations.RFRMGR_REFEREE_RELATIONS,
            RfrmgrRefereeRelationTypes.RFRMGR_REFEREE_RELATION_TYPES,
            RfrmgrRefereeReports.RFRMGR_REFEREE_REPORTS,
            RfrmgrRefereeReportRecipients.RFRMGR_REFEREE_REPORT_RECIPIENTS,
            RfrmgrRefereeStatuses.RFRMGR_REFEREE_STATUSES,
            RfrmgrSeasons.RFRMGR_SEASONS,
            RfrmgrSexTypes.RFRMGR_SEX_TYPES,
            RfrmgrSpokespeople.RFRMGR_SPOKESPEOPLE,
            RfrmgrStatusTypes.RFRMGR_STATUS_TYPES,
            RfrmgrTeams.RFRMGR_TEAMS,
            RfrmgrTeamSeasons.RFRMGR_TEAM_SEASONS,
            RfrmgrTeamVenues.RFRMGR_TEAM_VENUES,
            RfrmgrTournaments.RFRMGR_TOURNAMENTS,
            RfrmgrTournamentGames.RFRMGR_TOURNAMENT_GAMES,
            RfrmgrTournamentVenues.RFRMGR_TOURNAMENT_VENUES,
            RfrmgrTrainingLevels.RFRMGR_TRAINING_LEVELS,
            RfrmgrTrainingLevelTypes.RFRMGR_TRAINING_LEVEL_TYPES,
            RfrmgrTrainingUpdates.RFRMGR_TRAINING_UPDATES,
            RfrmgrUrls.RFRMGR_URLS,
            RfrmgrUsers.RFRMGR_USERS,
            RfrmgrUserRoles.RFRMGR_USER_ROLES,
            RfrmgrWishes.RFRMGR_WISHES,
            RfrmgrWishTypes.RFRMGR_WISH_TYPES);
    }
}
