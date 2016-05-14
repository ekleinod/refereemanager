/**
 * This class is generated by jOOQ
 */
package de.edgesoft.refereemanager.jooq;


import de.edgesoft.refereemanager.jooq.tables.ActivityLogs;
import de.edgesoft.refereemanager.jooq.tables.Addresses;
import de.edgesoft.refereemanager.jooq.tables.Assignments;
import de.edgesoft.refereemanager.jooq.tables.Clubs;
import de.edgesoft.refereemanager.jooq.tables.ContactTypes;
import de.edgesoft.refereemanager.jooq.tables.Contacts;
import de.edgesoft.refereemanager.jooq.tables.DatabaseColumns;
import de.edgesoft.refereemanager.jooq.tables.DatabaseTables;
import de.edgesoft.refereemanager.jooq.tables.Emails;
import de.edgesoft.refereemanager.jooq.tables.LeagueGameTeamTypes;
import de.edgesoft.refereemanager.jooq.tables.LeagueGameTeams;
import de.edgesoft.refereemanager.jooq.tables.LeagueGames;
import de.edgesoft.refereemanager.jooq.tables.LeaguePlannedReferees;
import de.edgesoft.refereemanager.jooq.tables.LeagueTypes;
import de.edgesoft.refereemanager.jooq.tables.Leagues;
import de.edgesoft.refereemanager.jooq.tables.People;
import de.edgesoft.refereemanager.jooq.tables.PersonPreferences;
import de.edgesoft.refereemanager.jooq.tables.PhoneNumbers;
import de.edgesoft.refereemanager.jooq.tables.Pictures;
import de.edgesoft.refereemanager.jooq.tables.RefereeAssignmentRemarkTypes;
import de.edgesoft.refereemanager.jooq.tables.RefereeAssignmentStatusTypes;
import de.edgesoft.refereemanager.jooq.tables.RefereeAssignmentTypes;
import de.edgesoft.refereemanager.jooq.tables.RefereeAssignments;
import de.edgesoft.refereemanager.jooq.tables.RefereeRelationTypes;
import de.edgesoft.refereemanager.jooq.tables.RefereeRelations;
import de.edgesoft.refereemanager.jooq.tables.RefereeReportRecipients;
import de.edgesoft.refereemanager.jooq.tables.RefereeReports;
import de.edgesoft.refereemanager.jooq.tables.RefereeStatuses;
import de.edgesoft.refereemanager.jooq.tables.Referees;
import de.edgesoft.refereemanager.jooq.tables.Seasons;
import de.edgesoft.refereemanager.jooq.tables.SexTypes;
import de.edgesoft.refereemanager.jooq.tables.Spokespeople;
import de.edgesoft.refereemanager.jooq.tables.StatusTypes;
import de.edgesoft.refereemanager.jooq.tables.TeamSeasons;
import de.edgesoft.refereemanager.jooq.tables.TeamVenues;
import de.edgesoft.refereemanager.jooq.tables.Teams;
import de.edgesoft.refereemanager.jooq.tables.TournamentGames;
import de.edgesoft.refereemanager.jooq.tables.TournamentVenues;
import de.edgesoft.refereemanager.jooq.tables.Tournaments;
import de.edgesoft.refereemanager.jooq.tables.TrainingLevelTypes;
import de.edgesoft.refereemanager.jooq.tables.TrainingLevels;
import de.edgesoft.refereemanager.jooq.tables.TrainingUpdates;
import de.edgesoft.refereemanager.jooq.tables.Urls;
import de.edgesoft.refereemanager.jooq.tables.UserRoles;
import de.edgesoft.refereemanager.jooq.tables.Users;
import de.edgesoft.refereemanager.jooq.tables.WishTypes;
import de.edgesoft.refereemanager.jooq.tables.Wishes;

import javax.annotation.Generated;


/**
 * Convenience access to all tables in refereemanager
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.8.0"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

    /**
     * Activity log of selected data tables. Only those tables are  / * comment truncated * / / *logged that are important to the user.* /
     */
    public static final ActivityLogs ACTIVITY_LOGS = de.edgesoft.refereemanager.jooq.tables.ActivityLogs.ACTIVITY_LOGS;

    /**
     * All addresses.
     */
    public static final Addresses ADDRESSES = de.edgesoft.refereemanager.jooq.tables.Addresses.ADDRESSES;

    /**
     * Assignments.
     */
    public static final Assignments ASSIGNMENTS = de.edgesoft.refereemanager.jooq.tables.Assignments.ASSIGNMENTS;

    /**
     * All clubs.
     */
    public static final Clubs CLUBS = de.edgesoft.refereemanager.jooq.tables.Clubs.CLUBS;

    /**
     * Base table for contacts. Specialized to email, phone etc.
     */
    public static final Contacts CONTACTS = de.edgesoft.refereemanager.jooq.tables.Contacts.CONTACTS;

    /**
     * Possible types of contacts, such as private, working.
     */
    public static final ContactTypes CONTACT_TYPES = de.edgesoft.refereemanager.jooq.tables.ContactTypes.CONTACT_TYPES;

    /**
     * All database columns that have to be logged,
     */
    public static final DatabaseColumns DATABASE_COLUMNS = de.edgesoft.refereemanager.jooq.tables.DatabaseColumns.DATABASE_COLUMNS;

    /**
     * All database tables that have to be logged,
     */
    public static final DatabaseTables DATABASE_TABLES = de.edgesoft.refereemanager.jooq.tables.DatabaseTables.DATABASE_TABLES;

    /**
     * All email adresses.
     */
    public static final Emails EMAILS = de.edgesoft.refereemanager.jooq.tables.Emails.EMAILS;

    /**
     * All leagues.
     */
    public static final Leagues LEAGUES = de.edgesoft.refereemanager.jooq.tables.Leagues.LEAGUES;

    /**
     * Assignments for league games.
League and season can be deduc / * comment truncated * / / *ed from the associated teams, they are stored because no team could be specified or teams of different seasons/leagues (e.g. relegation games).* /
     */
    public static final LeagueGames LEAGUE_GAMES = de.edgesoft.refereemanager.jooq.tables.LeagueGames.LEAGUE_GAMES;

    /**
     * Teams of a league game and their role: home or off team.
     */
    public static final LeagueGameTeams LEAGUE_GAME_TEAMS = de.edgesoft.refereemanager.jooq.tables.LeagueGameTeams.LEAGUE_GAME_TEAMS;

    /**
     * Types of league game teams, such as home team, off team, etc / * comment truncated * / / *.* /
     */
    public static final LeagueGameTeamTypes LEAGUE_GAME_TEAM_TYPES = de.edgesoft.refereemanager.jooq.tables.LeagueGameTeamTypes.LEAGUE_GAME_TEAM_TYPES;

    /**
     * Planned numbers of referees by assignment type.
     */
    public static final LeaguePlannedReferees LEAGUE_PLANNED_REFEREES = de.edgesoft.refereemanager.jooq.tables.LeaguePlannedReferees.LEAGUE_PLANNED_REFEREES;

    /**
     * All league types.
     */
    public static final LeagueTypes LEAGUE_TYPES = de.edgesoft.refereemanager.jooq.tables.LeagueTypes.LEAGUE_TYPES;

    /**
     * Alle people go here, specializations, such as referees, have / * comment truncated * / / * their own table.* /
     */
    public static final People PEOPLE = de.edgesoft.refereemanager.jooq.tables.People.PEOPLE;

    /**
     * Preferences for a person.
     */
    public static final PersonPreferences PERSON_PREFERENCES = de.edgesoft.refereemanager.jooq.tables.PersonPreferences.PERSON_PREFERENCES;

    /**
     * All phone numbers.
     */
    public static final PhoneNumbers PHONE_NUMBERS = de.edgesoft.refereemanager.jooq.tables.PhoneNumbers.PHONE_NUMBERS;

    /**
     * Picture storage (link to pictures, not the pics themselves.
     */
    public static final Pictures PICTURES = de.edgesoft.refereemanager.jooq.tables.Pictures.PICTURES;

    /**
     * Storage of all referees.
     */
    public static final Referees REFEREES = de.edgesoft.refereemanager.jooq.tables.Referees.REFEREES;

    /**
     * Referee's assignments with their role.
     */
    public static final RefereeAssignments REFEREE_ASSIGNMENTS = de.edgesoft.refereemanager.jooq.tables.RefereeAssignments.REFEREE_ASSIGNMENTS;

    /**
     * Types of assignment remarks such as answering machine, call  / * comment truncated * / / *back.* /
     */
    public static final RefereeAssignmentRemarkTypes REFEREE_ASSIGNMENT_REMARK_TYPES = de.edgesoft.refereemanager.jooq.tables.RefereeAssignmentRemarkTypes.REFEREE_ASSIGNMENT_REMARK_TYPES;

    /**
     * Possible assignment status of a referee (yes, no, maybe). Mo / * comment truncated * / / *stly relevant for tournament games.* /
     */
    public static final RefereeAssignmentStatusTypes REFEREE_ASSIGNMENT_STATUS_TYPES = de.edgesoft.refereemanager.jooq.tables.RefereeAssignmentStatusTypes.REFEREE_ASSIGNMENT_STATUS_TYPES;

    /**
     * Possible types of referee's assignments such as umpire, stan / * comment truncated * / / *dby umpire, referee, racket control.* /
     */
    public static final RefereeAssignmentTypes REFEREE_ASSIGNMENT_TYPES = de.edgesoft.refereemanager.jooq.tables.RefereeAssignmentTypes.REFEREE_ASSIGNMENT_TYPES;

    /**
     * Relations between referees and clubs: member and/or reffor.
     */
    public static final RefereeRelations REFEREE_RELATIONS = de.edgesoft.refereemanager.jooq.tables.RefereeRelations.REFEREE_RELATIONS;

    /**
     * Club relations of a referee: member, reffor.
     */
    public static final RefereeRelationTypes REFEREE_RELATION_TYPES = de.edgesoft.refereemanager.jooq.tables.RefereeRelationTypes.REFEREE_RELATION_TYPES;

    /**
     * Recipients of umpire reports.
     */
    public static final RefereeReports REFEREE_REPORTS = de.edgesoft.refereemanager.jooq.tables.RefereeReports.REFEREE_REPORTS;

    /**
     * All league types.
     */
    public static final RefereeReportRecipients REFEREE_REPORT_RECIPIENTS = de.edgesoft.refereemanager.jooq.tables.RefereeReportRecipients.REFEREE_REPORT_RECIPIENTS;

    /**
     * Referee's status.
     */
    public static final RefereeStatuses REFEREE_STATUSES = de.edgesoft.refereemanager.jooq.tables.RefereeStatuses.REFEREE_STATUSES;

    /**
     * All seasons.
     */
    public static final Seasons SEASONS = de.edgesoft.refereemanager.jooq.tables.Seasons.SEASONS;

    /**
     * Lookup table for sexes.
     */
    public static final SexTypes SEX_TYPES = de.edgesoft.refereemanager.jooq.tables.SexTypes.SEX_TYPES;

    /**
     * Spokespeople of teams and clubs.
     */
    public static final Spokespeople SPOKESPEOPLE = de.edgesoft.refereemanager.jooq.tables.Spokespeople.SPOKESPEOPLE;

    /**
     * Possible referee statuses such as "interested in many assign / * comment truncated * / / *ments", "not active", etc.* /
     */
    public static final StatusTypes STATUS_TYPES = de.edgesoft.refereemanager.jooq.tables.StatusTypes.STATUS_TYPES;

    /**
     * All teams.
     */
    public static final Teams TEAMS = de.edgesoft.refereemanager.jooq.tables.Teams.TEAMS;

    /**
     * Association of teams in a specified season.
     */
    public static final TeamSeasons TEAM_SEASONS = de.edgesoft.refereemanager.jooq.tables.TeamSeasons.TEAM_SEASONS;

    /**
     * Venues of teams in a season.
     */
    public static final TeamVenues TEAM_VENUES = de.edgesoft.refereemanager.jooq.tables.TeamVenues.TEAM_VENUES;

    /**
     * Tournaments with games.
     */
    public static final Tournaments TOURNAMENTS = de.edgesoft.refereemanager.jooq.tables.Tournaments.TOURNAMENTS;

    /**
     * Assignments for tournament games.
     */
    public static final TournamentGames TOURNAMENT_GAMES = de.edgesoft.refereemanager.jooq.tables.TournamentGames.TOURNAMENT_GAMES;

    /**
     * Venues of tournaments.
     */
    public static final TournamentVenues TOURNAMENT_VENUES = de.edgesoft.refereemanager.jooq.tables.TournamentVenues.TOURNAMENT_VENUES;

    /**
     * Achieved training levels for referees.
     */
    public static final TrainingLevels TRAINING_LEVELS = de.edgesoft.refereemanager.jooq.tables.TrainingLevels.TRAINING_LEVELS;

    /**
     * Possible training levels such as international referee, Germ / * comment truncated * / / *an referee.* /
     */
    public static final TrainingLevelTypes TRAINING_LEVEL_TYPES = de.edgesoft.refereemanager.jooq.tables.TrainingLevelTypes.TRAINING_LEVEL_TYPES;

    /**
     * Updates of the training levels.
     */
    public static final TrainingUpdates TRAINING_UPDATES = de.edgesoft.refereemanager.jooq.tables.TrainingUpdates.TRAINING_UPDATES;

    /**
     * All urls.
     */
    public static final Urls URLS = de.edgesoft.refereemanager.jooq.tables.Urls.URLS;

    /**
     * User table for access rights.
     */
    public static final Users USERS = de.edgesoft.refereemanager.jooq.tables.Users.USERS;

    /**
     * Roles of users, such as admin, editor.
     */
    public static final UserRoles USER_ROLES = de.edgesoft.refereemanager.jooq.tables.UserRoles.USER_ROLES;

    /**
     * Referees' wishes for their assignments.
     */
    public static final Wishes WISHES = de.edgesoft.refereemanager.jooq.tables.Wishes.WISHES;

    /**
     * Wish types of a referee: prefer, avoid.
     */
    public static final WishTypes WISH_TYPES = de.edgesoft.refereemanager.jooq.tables.WishTypes.WISH_TYPES;
}
