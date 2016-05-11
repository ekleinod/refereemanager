/**
 * This class is generated by jOOQ
 */
package de.edgesoft.refereemanager.jooq.tables.records;


import de.edgesoft.refereemanager.jooq.tables.RfrmgrRefereeReports;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.types.UInteger;


/**
 * Recipients of umpire reports.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.8.0"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class RfrmgrRefereeReportsRecord extends UpdatableRecordImpl<RfrmgrRefereeReportsRecord> implements Record4<UInteger, UInteger, UInteger, String> {

    private static final long serialVersionUID = 2041156041;

    /**
     * Setter for <code>refereemanager.rfrmgr_referee_reports.id</code>.
     */
    public void setId(UInteger value) {
        set(0, value);
    }

    /**
     * Getter for <code>refereemanager.rfrmgr_referee_reports.id</code>.
     */
    public UInteger getId() {
        return (UInteger) get(0);
    }

    /**
     * Setter for <code>refereemanager.rfrmgr_referee_reports.league_id</code>.
     */
    public void setLeagueId(UInteger value) {
        set(1, value);
    }

    /**
     * Getter for <code>refereemanager.rfrmgr_referee_reports.league_id</code>.
     */
    public UInteger getLeagueId() {
        return (UInteger) get(1);
    }

    /**
     * Setter for <code>refereemanager.rfrmgr_referee_reports.season_id</code>.
     */
    public void setSeasonId(UInteger value) {
        set(2, value);
    }

    /**
     * Getter for <code>refereemanager.rfrmgr_referee_reports.season_id</code>.
     */
    public UInteger getSeasonId() {
        return (UInteger) get(2);
    }

    /**
     * Setter for <code>refereemanager.rfrmgr_referee_reports.url</code>.
     */
    public void setUrl(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>refereemanager.rfrmgr_referee_reports.url</code>.
     */
    public String getUrl() {
        return (String) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<UInteger> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row4<UInteger, UInteger, UInteger, String> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row4<UInteger, UInteger, UInteger, String> valuesRow() {
        return (Row4) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field1() {
        return RfrmgrRefereeReports.RFRMGR_REFEREE_REPORTS.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field2() {
        return RfrmgrRefereeReports.RFRMGR_REFEREE_REPORTS.LEAGUE_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field3() {
        return RfrmgrRefereeReports.RFRMGR_REFEREE_REPORTS.SEASON_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return RfrmgrRefereeReports.RFRMGR_REFEREE_REPORTS.URL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger value2() {
        return getLeagueId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger value3() {
        return getSeasonId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getUrl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RfrmgrRefereeReportsRecord value1(UInteger value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RfrmgrRefereeReportsRecord value2(UInteger value) {
        setLeagueId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RfrmgrRefereeReportsRecord value3(UInteger value) {
        setSeasonId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RfrmgrRefereeReportsRecord value4(String value) {
        setUrl(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RfrmgrRefereeReportsRecord values(UInteger value1, UInteger value2, UInteger value3, String value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached RfrmgrRefereeReportsRecord
     */
    public RfrmgrRefereeReportsRecord() {
        super(RfrmgrRefereeReports.RFRMGR_REFEREE_REPORTS);
    }

    /**
     * Create a detached, initialised RfrmgrRefereeReportsRecord
     */
    public RfrmgrRefereeReportsRecord(UInteger id, UInteger leagueId, UInteger seasonId, String url) {
        super(RfrmgrRefereeReports.RFRMGR_REFEREE_REPORTS);

        set(0, id);
        set(1, leagueId);
        set(2, seasonId);
        set(3, url);
    }
}
