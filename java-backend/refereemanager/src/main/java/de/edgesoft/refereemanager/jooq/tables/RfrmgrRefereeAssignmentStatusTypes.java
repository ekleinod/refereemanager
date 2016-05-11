/**
 * This class is generated by jOOQ
 */
package de.edgesoft.refereemanager.jooq.tables;


import de.edgesoft.refereemanager.jooq.Keys;
import de.edgesoft.refereemanager.jooq.Refereemanager;
import de.edgesoft.refereemanager.jooq.tables.records.RfrmgrRefereeAssignmentStatusTypesRecord;

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
public class RfrmgrRefereeAssignmentStatusTypes extends TableImpl<RfrmgrRefereeAssignmentStatusTypesRecord> {

    private static final long serialVersionUID = 1679384043;

    /**
     * The reference instance of <code>refereemanager.rfrmgr_referee_assignment_status_types</code>
     */
    public static final RfrmgrRefereeAssignmentStatusTypes RFRMGR_REFEREE_ASSIGNMENT_STATUS_TYPES = new RfrmgrRefereeAssignmentStatusTypes();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<RfrmgrRefereeAssignmentStatusTypesRecord> getRecordType() {
        return RfrmgrRefereeAssignmentStatusTypesRecord.class;
    }

    /**
     * The column <code>refereemanager.rfrmgr_referee_assignment_status_types.id</code>.
     */
    public final TableField<RfrmgrRefereeAssignmentStatusTypesRecord, UInteger> ID = createField("id", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>refereemanager.rfrmgr_referee_assignment_status_types.sid</code>.
     */
    public final TableField<RfrmgrRefereeAssignmentStatusTypesRecord, String> SID = createField("sid", org.jooq.impl.SQLDataType.VARCHAR.length(20).nullable(false), this, "");

    /**
     * The column <code>refereemanager.rfrmgr_referee_assignment_status_types.title</code>.
     */
    public final TableField<RfrmgrRefereeAssignmentStatusTypesRecord, String> TITLE = createField("title", org.jooq.impl.SQLDataType.VARCHAR.length(100).nullable(false), this, "");

    /**
     * The column <code>refereemanager.rfrmgr_referee_assignment_status_types.remark</code>.
     */
    public final TableField<RfrmgrRefereeAssignmentStatusTypesRecord, String> REMARK = createField("remark", org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * Create a <code>refereemanager.rfrmgr_referee_assignment_status_types</code> table reference
     */
    public RfrmgrRefereeAssignmentStatusTypes() {
        this("rfrmgr_referee_assignment_status_types", null);
    }

    /**
     * Create an aliased <code>refereemanager.rfrmgr_referee_assignment_status_types</code> table reference
     */
    public RfrmgrRefereeAssignmentStatusTypes(String alias) {
        this(alias, RFRMGR_REFEREE_ASSIGNMENT_STATUS_TYPES);
    }

    private RfrmgrRefereeAssignmentStatusTypes(String alias, Table<RfrmgrRefereeAssignmentStatusTypesRecord> aliased) {
        this(alias, aliased, null);
    }

    private RfrmgrRefereeAssignmentStatusTypes(String alias, Table<RfrmgrRefereeAssignmentStatusTypesRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "Possible assignment status of a referee (yes, no, maybe). Mo /* comment truncated */ /*stly relevant for tournament games.*/");
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
    public Identity<RfrmgrRefereeAssignmentStatusTypesRecord, UInteger> getIdentity() {
        return Keys.IDENTITY_RFRMGR_REFEREE_ASSIGNMENT_STATUS_TYPES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<RfrmgrRefereeAssignmentStatusTypesRecord> getPrimaryKey() {
        return Keys.KEY_RFRMGR_REFEREE_ASSIGNMENT_STATUS_TYPES_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<RfrmgrRefereeAssignmentStatusTypesRecord>> getKeys() {
        return Arrays.<UniqueKey<RfrmgrRefereeAssignmentStatusTypesRecord>>asList(Keys.KEY_RFRMGR_REFEREE_ASSIGNMENT_STATUS_TYPES_PRIMARY, Keys.KEY_RFRMGR_REFEREE_ASSIGNMENT_STATUS_TYPES_ID_UNIQUE, Keys.KEY_RFRMGR_REFEREE_ASSIGNMENT_STATUS_TYPES_TITLE_UNIQUE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RfrmgrRefereeAssignmentStatusTypes as(String alias) {
        return new RfrmgrRefereeAssignmentStatusTypes(alias, this);
    }

    /**
     * Rename this table
     */
    public RfrmgrRefereeAssignmentStatusTypes rename(String name) {
        return new RfrmgrRefereeAssignmentStatusTypes(name, null);
    }
}
