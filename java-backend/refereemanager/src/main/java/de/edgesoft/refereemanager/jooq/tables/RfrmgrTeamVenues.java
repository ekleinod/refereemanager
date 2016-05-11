/**
 * This class is generated by jOOQ
 */
package de.edgesoft.refereemanager.jooq.tables;


import de.edgesoft.refereemanager.jooq.Keys;
import de.edgesoft.refereemanager.jooq.Refereemanager;
import de.edgesoft.refereemanager.jooq.tables.records.RfrmgrTeamVenuesRecord;

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
 * Venues of teams in a season.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.8.0"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class RfrmgrTeamVenues extends TableImpl<RfrmgrTeamVenuesRecord> {

    private static final long serialVersionUID = 1133071899;

    /**
     * The reference instance of <code>refereemanager.rfrmgr_team_venues</code>
     */
    public static final RfrmgrTeamVenues RFRMGR_TEAM_VENUES = new RfrmgrTeamVenues();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<RfrmgrTeamVenuesRecord> getRecordType() {
        return RfrmgrTeamVenuesRecord.class;
    }

    /**
     * The column <code>refereemanager.rfrmgr_team_venues.id</code>.
     */
    public final TableField<RfrmgrTeamVenuesRecord, UInteger> ID = createField("id", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>refereemanager.rfrmgr_team_venues.team_season_id</code>.
     */
    public final TableField<RfrmgrTeamVenuesRecord, UInteger> TEAM_SEASON_ID = createField("team_season_id", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>refereemanager.rfrmgr_team_venues.contact_id</code>.
     */
    public final TableField<RfrmgrTeamVenuesRecord, UInteger> CONTACT_ID = createField("contact_id", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>refereemanager.rfrmgr_team_venues.number</code>.
     */
    public final TableField<RfrmgrTeamVenuesRecord, UInteger> NUMBER = createField("number", org.jooq.impl.SQLDataType.INTEGERUNSIGNED, this, "");

    /**
     * Create a <code>refereemanager.rfrmgr_team_venues</code> table reference
     */
    public RfrmgrTeamVenues() {
        this("rfrmgr_team_venues", null);
    }

    /**
     * Create an aliased <code>refereemanager.rfrmgr_team_venues</code> table reference
     */
    public RfrmgrTeamVenues(String alias) {
        this(alias, RFRMGR_TEAM_VENUES);
    }

    private RfrmgrTeamVenues(String alias, Table<RfrmgrTeamVenuesRecord> aliased) {
        this(alias, aliased, null);
    }

    private RfrmgrTeamVenues(String alias, Table<RfrmgrTeamVenuesRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "Venues of teams in a season.");
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
    public Identity<RfrmgrTeamVenuesRecord, UInteger> getIdentity() {
        return Keys.IDENTITY_RFRMGR_TEAM_VENUES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<RfrmgrTeamVenuesRecord> getPrimaryKey() {
        return Keys.KEY_RFRMGR_TEAM_VENUES_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<RfrmgrTeamVenuesRecord>> getKeys() {
        return Arrays.<UniqueKey<RfrmgrTeamVenuesRecord>>asList(Keys.KEY_RFRMGR_TEAM_VENUES_PRIMARY, Keys.KEY_RFRMGR_TEAM_VENUES_ID_UNIQUE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RfrmgrTeamVenues as(String alias) {
        return new RfrmgrTeamVenues(alias, this);
    }

    /**
     * Rename this table
     */
    public RfrmgrTeamVenues rename(String name) {
        return new RfrmgrTeamVenues(name, null);
    }
}
