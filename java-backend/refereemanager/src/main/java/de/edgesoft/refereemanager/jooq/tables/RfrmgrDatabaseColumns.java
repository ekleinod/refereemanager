/**
 * This class is generated by jOOQ
 */
package de.edgesoft.refereemanager.jooq.tables;


import de.edgesoft.refereemanager.jooq.Keys;
import de.edgesoft.refereemanager.jooq.Refereemanager;
import de.edgesoft.refereemanager.jooq.tables.records.RfrmgrDatabaseColumnsRecord;

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
 * All database columns that have to be logged,
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.8.0"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class RfrmgrDatabaseColumns extends TableImpl<RfrmgrDatabaseColumnsRecord> {

    private static final long serialVersionUID = 845044658;

    /**
     * The reference instance of <code>refereemanager.rfrmgr_database_columns</code>
     */
    public static final RfrmgrDatabaseColumns RFRMGR_DATABASE_COLUMNS = new RfrmgrDatabaseColumns();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<RfrmgrDatabaseColumnsRecord> getRecordType() {
        return RfrmgrDatabaseColumnsRecord.class;
    }

    /**
     * The column <code>refereemanager.rfrmgr_database_columns.id</code>.
     */
    public final TableField<RfrmgrDatabaseColumnsRecord, UInteger> ID = createField("id", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>refereemanager.rfrmgr_database_columns.column_name</code>.
     */
    public final TableField<RfrmgrDatabaseColumnsRecord, String> COLUMN_NAME = createField("column_name", org.jooq.impl.SQLDataType.VARCHAR.length(50).nullable(false), this, "");

    /**
     * Create a <code>refereemanager.rfrmgr_database_columns</code> table reference
     */
    public RfrmgrDatabaseColumns() {
        this("rfrmgr_database_columns", null);
    }

    /**
     * Create an aliased <code>refereemanager.rfrmgr_database_columns</code> table reference
     */
    public RfrmgrDatabaseColumns(String alias) {
        this(alias, RFRMGR_DATABASE_COLUMNS);
    }

    private RfrmgrDatabaseColumns(String alias, Table<RfrmgrDatabaseColumnsRecord> aliased) {
        this(alias, aliased, null);
    }

    private RfrmgrDatabaseColumns(String alias, Table<RfrmgrDatabaseColumnsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "All database columns that have to be logged,");
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
    public Identity<RfrmgrDatabaseColumnsRecord, UInteger> getIdentity() {
        return Keys.IDENTITY_RFRMGR_DATABASE_COLUMNS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<RfrmgrDatabaseColumnsRecord> getPrimaryKey() {
        return Keys.KEY_RFRMGR_DATABASE_COLUMNS_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<RfrmgrDatabaseColumnsRecord>> getKeys() {
        return Arrays.<UniqueKey<RfrmgrDatabaseColumnsRecord>>asList(Keys.KEY_RFRMGR_DATABASE_COLUMNS_PRIMARY, Keys.KEY_RFRMGR_DATABASE_COLUMNS_ID_UNIQUE, Keys.KEY_RFRMGR_DATABASE_COLUMNS_SID_UNIQUE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RfrmgrDatabaseColumns as(String alias) {
        return new RfrmgrDatabaseColumns(alias, this);
    }

    /**
     * Rename this table
     */
    public RfrmgrDatabaseColumns rename(String name) {
        return new RfrmgrDatabaseColumns(name, null);
    }
}
