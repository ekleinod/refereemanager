/**
 * This class is generated by jOOQ
 */
package de.edgesoft.refereemanager.jooq.tables;


import de.edgesoft.refereemanager.jooq.Keys;
import de.edgesoft.refereemanager.jooq.Refereemanager;
import de.edgesoft.refereemanager.jooq.tables.records.RfrmgrTrainingUpdatesRecord;

import java.sql.Date;
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
 * Updates of the training levels.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.8.0"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class RfrmgrTrainingUpdates extends TableImpl<RfrmgrTrainingUpdatesRecord> {

    private static final long serialVersionUID = -1198682793;

    /**
     * The reference instance of <code>refereemanager.rfrmgr_training_updates</code>
     */
    public static final RfrmgrTrainingUpdates RFRMGR_TRAINING_UPDATES = new RfrmgrTrainingUpdates();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<RfrmgrTrainingUpdatesRecord> getRecordType() {
        return RfrmgrTrainingUpdatesRecord.class;
    }

    /**
     * The column <code>refereemanager.rfrmgr_training_updates.id</code>.
     */
    public final TableField<RfrmgrTrainingUpdatesRecord, UInteger> ID = createField("id", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>refereemanager.rfrmgr_training_updates.training_level_id</code>.
     */
    public final TableField<RfrmgrTrainingUpdatesRecord, UInteger> TRAINING_LEVEL_ID = createField("training_level_id", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>refereemanager.rfrmgr_training_updates.update</code>.
     */
    public final TableField<RfrmgrTrainingUpdatesRecord, Date> UPDATE = createField("update", org.jooq.impl.SQLDataType.DATE.nullable(false), this, "");

    /**
     * Create a <code>refereemanager.rfrmgr_training_updates</code> table reference
     */
    public RfrmgrTrainingUpdates() {
        this("rfrmgr_training_updates", null);
    }

    /**
     * Create an aliased <code>refereemanager.rfrmgr_training_updates</code> table reference
     */
    public RfrmgrTrainingUpdates(String alias) {
        this(alias, RFRMGR_TRAINING_UPDATES);
    }

    private RfrmgrTrainingUpdates(String alias, Table<RfrmgrTrainingUpdatesRecord> aliased) {
        this(alias, aliased, null);
    }

    private RfrmgrTrainingUpdates(String alias, Table<RfrmgrTrainingUpdatesRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "Updates of the training levels.");
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
    public Identity<RfrmgrTrainingUpdatesRecord, UInteger> getIdentity() {
        return Keys.IDENTITY_RFRMGR_TRAINING_UPDATES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<RfrmgrTrainingUpdatesRecord> getPrimaryKey() {
        return Keys.KEY_RFRMGR_TRAINING_UPDATES_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<RfrmgrTrainingUpdatesRecord>> getKeys() {
        return Arrays.<UniqueKey<RfrmgrTrainingUpdatesRecord>>asList(Keys.KEY_RFRMGR_TRAINING_UPDATES_PRIMARY, Keys.KEY_RFRMGR_TRAINING_UPDATES_ID_UNIQUE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RfrmgrTrainingUpdates as(String alias) {
        return new RfrmgrTrainingUpdates(alias, this);
    }

    /**
     * Rename this table
     */
    public RfrmgrTrainingUpdates rename(String name) {
        return new RfrmgrTrainingUpdates(name, null);
    }
}
