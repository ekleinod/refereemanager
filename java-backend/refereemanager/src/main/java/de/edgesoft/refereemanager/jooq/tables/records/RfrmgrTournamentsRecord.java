/**
 * This class is generated by jOOQ
 */
package de.edgesoft.refereemanager.jooq.tables.records;


import de.edgesoft.refereemanager.jooq.tables.RfrmgrTournaments;

import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record9;
import org.jooq.Row9;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.types.UInteger;


/**
 * Tournaments with games.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.8.0"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class RfrmgrTournamentsRecord extends UpdatableRecordImpl<RfrmgrTournamentsRecord> implements Record9<UInteger, String, Timestamp, Timestamp, String, String, UInteger, UInteger, String> {

    private static final long serialVersionUID = -1406594047;

    /**
     * Setter for <code>refereemanager.rfrmgr_tournaments.id</code>.
     */
    public void setId(UInteger value) {
        set(0, value);
    }

    /**
     * Getter for <code>refereemanager.rfrmgr_tournaments.id</code>.
     */
    public UInteger getId() {
        return (UInteger) get(0);
    }

    /**
     * Setter for <code>refereemanager.rfrmgr_tournaments.name</code>.
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>refereemanager.rfrmgr_tournaments.name</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>refereemanager.rfrmgr_tournaments.start</code>.
     */
    public void setStart(Timestamp value) {
        set(2, value);
    }

    /**
     * Getter for <code>refereemanager.rfrmgr_tournaments.start</code>.
     */
    public Timestamp getStart() {
        return (Timestamp) get(2);
    }

    /**
     * Setter for <code>refereemanager.rfrmgr_tournaments.end</code>.
     */
    public void setEnd(Timestamp value) {
        set(3, value);
    }

    /**
     * Getter for <code>refereemanager.rfrmgr_tournaments.end</code>.
     */
    public Timestamp getEnd() {
        return (Timestamp) get(3);
    }

    /**
     * Setter for <code>refereemanager.rfrmgr_tournaments.announcement_url</code>.
     */
    public void setAnnouncementUrl(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>refereemanager.rfrmgr_tournaments.announcement_url</code>.
     */
    public String getAnnouncementUrl() {
        return (String) get(4);
    }

    /**
     * Setter for <code>refereemanager.rfrmgr_tournaments.information_url</code>.
     */
    public void setInformationUrl(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>refereemanager.rfrmgr_tournaments.information_url</code>.
     */
    public String getInformationUrl() {
        return (String) get(5);
    }

    /**
     * Setter for <code>refereemanager.rfrmgr_tournaments.club_id</code>.
     */
    public void setClubId(UInteger value) {
        set(6, value);
    }

    /**
     * Getter for <code>refereemanager.rfrmgr_tournaments.club_id</code>.
     */
    public UInteger getClubId() {
        return (UInteger) get(6);
    }

    /**
     * Setter for <code>refereemanager.rfrmgr_tournaments.person_id</code>.
     */
    public void setPersonId(UInteger value) {
        set(7, value);
    }

    /**
     * Getter for <code>refereemanager.rfrmgr_tournaments.person_id</code>.
     */
    public UInteger getPersonId() {
        return (UInteger) get(7);
    }

    /**
     * Setter for <code>refereemanager.rfrmgr_tournaments.remark</code>.
     */
    public void setRemark(String value) {
        set(8, value);
    }

    /**
     * Getter for <code>refereemanager.rfrmgr_tournaments.remark</code>.
     */
    public String getRemark() {
        return (String) get(8);
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
    // Record9 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row9<UInteger, String, Timestamp, Timestamp, String, String, UInteger, UInteger, String> fieldsRow() {
        return (Row9) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row9<UInteger, String, Timestamp, Timestamp, String, String, UInteger, UInteger, String> valuesRow() {
        return (Row9) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field1() {
        return RfrmgrTournaments.RFRMGR_TOURNAMENTS.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return RfrmgrTournaments.RFRMGR_TOURNAMENTS.NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field3() {
        return RfrmgrTournaments.RFRMGR_TOURNAMENTS.START;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field4() {
        return RfrmgrTournaments.RFRMGR_TOURNAMENTS.END;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return RfrmgrTournaments.RFRMGR_TOURNAMENTS.ANNOUNCEMENT_URL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field6() {
        return RfrmgrTournaments.RFRMGR_TOURNAMENTS.INFORMATION_URL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field7() {
        return RfrmgrTournaments.RFRMGR_TOURNAMENTS.CLUB_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field8() {
        return RfrmgrTournaments.RFRMGR_TOURNAMENTS.PERSON_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field9() {
        return RfrmgrTournaments.RFRMGR_TOURNAMENTS.REMARK;
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
    public String value2() {
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value3() {
        return getStart();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value4() {
        return getEnd();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value5() {
        return getAnnouncementUrl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value6() {
        return getInformationUrl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger value7() {
        return getClubId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger value8() {
        return getPersonId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value9() {
        return getRemark();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RfrmgrTournamentsRecord value1(UInteger value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RfrmgrTournamentsRecord value2(String value) {
        setName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RfrmgrTournamentsRecord value3(Timestamp value) {
        setStart(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RfrmgrTournamentsRecord value4(Timestamp value) {
        setEnd(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RfrmgrTournamentsRecord value5(String value) {
        setAnnouncementUrl(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RfrmgrTournamentsRecord value6(String value) {
        setInformationUrl(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RfrmgrTournamentsRecord value7(UInteger value) {
        setClubId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RfrmgrTournamentsRecord value8(UInteger value) {
        setPersonId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RfrmgrTournamentsRecord value9(String value) {
        setRemark(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RfrmgrTournamentsRecord values(UInteger value1, String value2, Timestamp value3, Timestamp value4, String value5, String value6, UInteger value7, UInteger value8, String value9) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached RfrmgrTournamentsRecord
     */
    public RfrmgrTournamentsRecord() {
        super(RfrmgrTournaments.RFRMGR_TOURNAMENTS);
    }

    /**
     * Create a detached, initialised RfrmgrTournamentsRecord
     */
    public RfrmgrTournamentsRecord(UInteger id, String name, Timestamp start, Timestamp end, String announcementUrl, String informationUrl, UInteger clubId, UInteger personId, String remark) {
        super(RfrmgrTournaments.RFRMGR_TOURNAMENTS);

        set(0, id);
        set(1, name);
        set(2, start);
        set(3, end);
        set(4, announcementUrl);
        set(5, informationUrl);
        set(6, clubId);
        set(7, personId);
        set(8, remark);
    }
}
