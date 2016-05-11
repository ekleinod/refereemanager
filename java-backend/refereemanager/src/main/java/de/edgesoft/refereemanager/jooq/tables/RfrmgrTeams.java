/**
 * This class is generated by jOOQ
 */
package de.edgesoft.refereemanager.jooq.tables;


import de.edgesoft.refereemanager.jooq.Keys;
import de.edgesoft.refereemanager.jooq.Refereemanager;
import de.edgesoft.refereemanager.jooq.tables.records.RfrmgrTeamsRecord;

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
 * All teams.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.8.0"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class RfrmgrTeams extends TableImpl<RfrmgrTeamsRecord> {

    private static final long serialVersionUID = 1625881223;

    /**
     * The reference instance of <code>refereemanager.rfrmgr_teams</code>
     */
    public static final RfrmgrTeams RFRMGR_TEAMS = new RfrmgrTeams();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<RfrmgrTeamsRecord> getRecordType() {
        return RfrmgrTeamsRecord.class;
    }

    /**
     * The column <code>refereemanager.rfrmgr_teams.id</code>.
     */
    public final TableField<RfrmgrTeamsRecord, UInteger> ID = createField("id", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>refereemanager.rfrmgr_teams.club_id</code>.
     */
    public final TableField<RfrmgrTeamsRecord, UInteger> CLUB_ID = createField("club_id", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>refereemanager.rfrmgr_teams.league_type_id</code>.
     */
    public final TableField<RfrmgrTeamsRecord, UInteger> LEAGUE_TYPE_ID = createField("league_type_id", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>refereemanager.rfrmgr_teams.number</code>.
     */
    public final TableField<RfrmgrTeamsRecord, UInteger> NUMBER = createField("number", org.jooq.impl.SQLDataType.INTEGERUNSIGNED, this, "");

    /**
     * The column <code>refereemanager.rfrmgr_teams.name</code>.
     */
    public final TableField<RfrmgrTeamsRecord, String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR.length(100), this, "");

    /**
     * The column <code>refereemanager.rfrmgr_teams.remark</code>.
     */
    public final TableField<RfrmgrTeamsRecord, String> REMARK = createField("remark", org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * Create a <code>refereemanager.rfrmgr_teams</code> table reference
     */
    public RfrmgrTeams() {
        this("rfrmgr_teams", null);
    }

    /**
     * Create an aliased <code>refereemanager.rfrmgr_teams</code> table reference
     */
    public RfrmgrTeams(String alias) {
        this(alias, RFRMGR_TEAMS);
    }

    private RfrmgrTeams(String alias, Table<RfrmgrTeamsRecord> aliased) {
        this(alias, aliased, null);
    }

    private RfrmgrTeams(String alias, Table<RfrmgrTeamsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "All teams.");
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
    public Identity<RfrmgrTeamsRecord, UInteger> getIdentity() {
        return Keys.IDENTITY_RFRMGR_TEAMS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<RfrmgrTeamsRecord> getPrimaryKey() {
        return Keys.KEY_RFRMGR_TEAMS_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<RfrmgrTeamsRecord>> getKeys() {
        return Arrays.<UniqueKey<RfrmgrTeamsRecord>>asList(Keys.KEY_RFRMGR_TEAMS_PRIMARY, Keys.KEY_RFRMGR_TEAMS_ID_UNIQUE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RfrmgrTeams as(String alias) {
        return new RfrmgrTeams(alias, this);
    }

    /**
     * Rename this table
     */
    public RfrmgrTeams rename(String name) {
        return new RfrmgrTeams(name, null);
    }
}
