/**
 * This class is generated by jOOQ
 */
package de.edgesoft.refereemanager.jooq.tables;


import de.edgesoft.refereemanager.jooq.Keys;
import de.edgesoft.refereemanager.jooq.Refereemanager;
import de.edgesoft.refereemanager.jooq.tables.records.RfrmgrPicturesRecord;

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
 * Picture storage (link to pictures, not the pics themselves.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.8.0"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class RfrmgrPictures extends TableImpl<RfrmgrPicturesRecord> {

    private static final long serialVersionUID = 1378839673;

    /**
     * The reference instance of <code>refereemanager.rfrmgr_pictures</code>
     */
    public static final RfrmgrPictures RFRMGR_PICTURES = new RfrmgrPictures();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<RfrmgrPicturesRecord> getRecordType() {
        return RfrmgrPicturesRecord.class;
    }

    /**
     * The column <code>refereemanager.rfrmgr_pictures.id</code>.
     */
    public final TableField<RfrmgrPicturesRecord, UInteger> ID = createField("id", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>refereemanager.rfrmgr_pictures.person_id</code>.
     */
    public final TableField<RfrmgrPicturesRecord, UInteger> PERSON_ID = createField("person_id", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>refereemanager.rfrmgr_pictures.url</code>.
     */
    public final TableField<RfrmgrPicturesRecord, String> URL = createField("url", org.jooq.impl.SQLDataType.VARCHAR.length(200).nullable(false), this, "");

    /**
     * The column <code>refereemanager.rfrmgr_pictures.remark</code>.
     */
    public final TableField<RfrmgrPicturesRecord, String> REMARK = createField("remark", org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * Create a <code>refereemanager.rfrmgr_pictures</code> table reference
     */
    public RfrmgrPictures() {
        this("rfrmgr_pictures", null);
    }

    /**
     * Create an aliased <code>refereemanager.rfrmgr_pictures</code> table reference
     */
    public RfrmgrPictures(String alias) {
        this(alias, RFRMGR_PICTURES);
    }

    private RfrmgrPictures(String alias, Table<RfrmgrPicturesRecord> aliased) {
        this(alias, aliased, null);
    }

    private RfrmgrPictures(String alias, Table<RfrmgrPicturesRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "Picture storage (link to pictures, not the pics themselves.");
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
    public Identity<RfrmgrPicturesRecord, UInteger> getIdentity() {
        return Keys.IDENTITY_RFRMGR_PICTURES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<RfrmgrPicturesRecord> getPrimaryKey() {
        return Keys.KEY_RFRMGR_PICTURES_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<RfrmgrPicturesRecord>> getKeys() {
        return Arrays.<UniqueKey<RfrmgrPicturesRecord>>asList(Keys.KEY_RFRMGR_PICTURES_PRIMARY, Keys.KEY_RFRMGR_PICTURES_ID_UNIQUE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RfrmgrPictures as(String alias) {
        return new RfrmgrPictures(alias, this);
    }

    /**
     * Rename this table
     */
    public RfrmgrPictures rename(String name) {
        return new RfrmgrPictures(name, null);
    }
}
