/**
 * This class is generated by jOOQ
 */
package de.edgesoft.refereemanager.jooq.tables.records;


import de.edgesoft.refereemanager.jooq.tables.RfrmgrRefereeAssignmentStatusTypes;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.types.UInteger;


/**
 * Possible assignment status of a referee (yes, no, maybe). Mo / * comment 
 * truncated * / / *stly relevant for tournament games.* /
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.8.0"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class RfrmgrRefereeAssignmentStatusTypesRecord extends UpdatableRecordImpl<RfrmgrRefereeAssignmentStatusTypesRecord> implements Record4<UInteger, String, String, String> {

    private static final long serialVersionUID = 1823363042;

    /**
     * Setter for <code>refereemanager.rfrmgr_referee_assignment_status_types.id</code>.
     */
    public void setId(UInteger value) {
        set(0, value);
    }

    /**
     * Getter for <code>refereemanager.rfrmgr_referee_assignment_status_types.id</code>.
     */
    public UInteger getId() {
        return (UInteger) get(0);
    }

    /**
     * Setter for <code>refereemanager.rfrmgr_referee_assignment_status_types.sid</code>.
     */
    public void setSid(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>refereemanager.rfrmgr_referee_assignment_status_types.sid</code>.
     */
    public String getSid() {
        return (String) get(1);
    }

    /**
     * Setter for <code>refereemanager.rfrmgr_referee_assignment_status_types.title</code>.
     */
    public void setTitle(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>refereemanager.rfrmgr_referee_assignment_status_types.title</code>.
     */
    public String getTitle() {
        return (String) get(2);
    }

    /**
     * Setter for <code>refereemanager.rfrmgr_referee_assignment_status_types.remark</code>.
     */
    public void setRemark(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>refereemanager.rfrmgr_referee_assignment_status_types.remark</code>.
     */
    public String getRemark() {
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
    public Row4<UInteger, String, String, String> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row4<UInteger, String, String, String> valuesRow() {
        return (Row4) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field1() {
        return RfrmgrRefereeAssignmentStatusTypes.RFRMGR_REFEREE_ASSIGNMENT_STATUS_TYPES.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return RfrmgrRefereeAssignmentStatusTypes.RFRMGR_REFEREE_ASSIGNMENT_STATUS_TYPES.SID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return RfrmgrRefereeAssignmentStatusTypes.RFRMGR_REFEREE_ASSIGNMENT_STATUS_TYPES.TITLE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return RfrmgrRefereeAssignmentStatusTypes.RFRMGR_REFEREE_ASSIGNMENT_STATUS_TYPES.REMARK;
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
        return getSid();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getTitle();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getRemark();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RfrmgrRefereeAssignmentStatusTypesRecord value1(UInteger value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RfrmgrRefereeAssignmentStatusTypesRecord value2(String value) {
        setSid(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RfrmgrRefereeAssignmentStatusTypesRecord value3(String value) {
        setTitle(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RfrmgrRefereeAssignmentStatusTypesRecord value4(String value) {
        setRemark(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RfrmgrRefereeAssignmentStatusTypesRecord values(UInteger value1, String value2, String value3, String value4) {
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
     * Create a detached RfrmgrRefereeAssignmentStatusTypesRecord
     */
    public RfrmgrRefereeAssignmentStatusTypesRecord() {
        super(RfrmgrRefereeAssignmentStatusTypes.RFRMGR_REFEREE_ASSIGNMENT_STATUS_TYPES);
    }

    /**
     * Create a detached, initialised RfrmgrRefereeAssignmentStatusTypesRecord
     */
    public RfrmgrRefereeAssignmentStatusTypesRecord(UInteger id, String sid, String title, String remark) {
        super(RfrmgrRefereeAssignmentStatusTypes.RFRMGR_REFEREE_ASSIGNMENT_STATUS_TYPES);

        set(0, id);
        set(1, sid);
        set(2, title);
        set(3, remark);
    }
}
