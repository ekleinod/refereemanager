/**
 * This class is generated by jOOQ
 */
package de.edgesoft.refereemanager.jooq.tables;


import de.edgesoft.refereemanager.jooq.Keys;
import de.edgesoft.refereemanager.jooq.Refereemanager;
import de.edgesoft.refereemanager.jooq.tables.records.TeamSeasonsRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Identity;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;
import org.jooq.types.UInteger;


/**
 * Association of teams in a specified season.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.8.0"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TeamSeasons extends TableImpl<TeamSeasonsRecord> {

    private static final long serialVersionUID = -1816259232;

    /**
     * The reference instance of <code>refereemanager.rfrmgr_team_seasons</code>
     */
    public static final TeamSeasons TEAM_SEASONS = new TeamSeasons();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TeamSeasonsRecord> getRecordType() {
        return TeamSeasonsRecord.class;
    }

    /**
     * The column <code>refereemanager.rfrmgr_team_seasons.id</code>.
     */
    public final TableField<TeamSeasonsRecord, UInteger> ID = createField("id", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>refereemanager.rfrmgr_team_seasons.team_id</code>.
     */
    public final TableField<TeamSeasonsRecord, UInteger> TEAM_ID = createField("team_id", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>refereemanager.rfrmgr_team_seasons.season_id</code>.
     */
    public final TableField<TeamSeasonsRecord, UInteger> SEASON_ID = createField("season_id", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>refereemanager.rfrmgr_team_seasons.league_id</code>.
     */
    public final TableField<TeamSeasonsRecord, UInteger> LEAGUE_ID = createField("league_id", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false), this, "");

    /**
     * Create a <code>refereemanager.rfrmgr_team_seasons</code> table reference
     */
    public TeamSeasons() {
        this("rfrmgr_team_seasons", null);
    }

    /**
     * Create an aliased <code>refereemanager.rfrmgr_team_seasons</code> table reference
     */
    public TeamSeasons(String alias) {
        this(alias, TEAM_SEASONS);
    }

    private TeamSeasons(String alias, Table<TeamSeasonsRecord> aliased) {
        this(alias, aliased, null);
    }

    private TeamSeasons(String alias, Table<TeamSeasonsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "Association of teams in a specified season.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Refereemanager.REFEREEMANAGER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<TeamSeasonsRecord, UInteger> getIdentity() {
        return Keys.IDENTITY_TEAM_SEASONS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<TeamSeasonsRecord> getPrimaryKey() {
        return Keys.KEY_RFRMGR_TEAM_SEASONS_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<TeamSeasonsRecord>> getKeys() {
        return Arrays.<UniqueKey<TeamSeasonsRecord>>asList(Keys.KEY_RFRMGR_TEAM_SEASONS_PRIMARY, Keys.KEY_RFRMGR_TEAM_SEASONS_ID_UNIQUE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TeamSeasons as(String alias) {
        return new TeamSeasons(alias, this);
    }

    /**
     * Rename this table
     */
    public TeamSeasons rename(String name) {
        return new TeamSeasons(name, null);
    }
}
