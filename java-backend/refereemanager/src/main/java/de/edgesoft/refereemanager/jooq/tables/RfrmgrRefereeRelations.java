/**
 * This class is generated by jOOQ
 */
package de.edgesoft.refereemanager.jooq.tables;


import de.edgesoft.refereemanager.jooq.Keys;
import de.edgesoft.refereemanager.jooq.Refereemanager;
import de.edgesoft.refereemanager.jooq.tables.records.RfrmgrRefereeRelationsRecord;

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
 * Relations between referees and clubs: member and/or reffor.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.8.0"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class RfrmgrRefereeRelations extends TableImpl<RfrmgrRefereeRelationsRecord> {

    private static final long serialVersionUID = -1828806197;

    /**
     * The reference instance of <code>refereemanager.rfrmgr_referee_relations</code>
     */
    public static final RfrmgrRefereeRelations RFRMGR_REFEREE_RELATIONS = new RfrmgrRefereeRelations();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<RfrmgrRefereeRelationsRecord> getRecordType() {
        return RfrmgrRefereeRelationsRecord.class;
    }

    /**
     * The column <code>refereemanager.rfrmgr_referee_relations.id</code>.
     */
    public final TableField<RfrmgrRefereeRelationsRecord, UInteger> ID = createField("id", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>refereemanager.rfrmgr_referee_relations.referee_id</code>.
     */
    public final TableField<RfrmgrRefereeRelationsRecord, UInteger> REFEREE_ID = createField("referee_id", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>refereemanager.rfrmgr_referee_relations.referee_relation_type_id</code>.
     */
    public final TableField<RfrmgrRefereeRelationsRecord, UInteger> REFEREE_RELATION_TYPE_ID = createField("referee_relation_type_id", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>refereemanager.rfrmgr_referee_relations.club_id</code>.
     */
    public final TableField<RfrmgrRefereeRelationsRecord, UInteger> CLUB_ID = createField("club_id", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>refereemanager.rfrmgr_referee_relations.season_id</code>.
     */
    public final TableField<RfrmgrRefereeRelationsRecord, UInteger> SEASON_ID = createField("season_id", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>refereemanager.rfrmgr_referee_relations.remark</code>.
     */
    public final TableField<RfrmgrRefereeRelationsRecord, String> REMARK = createField("remark", org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * Create a <code>refereemanager.rfrmgr_referee_relations</code> table reference
     */
    public RfrmgrRefereeRelations() {
        this("rfrmgr_referee_relations", null);
    }

    /**
     * Create an aliased <code>refereemanager.rfrmgr_referee_relations</code> table reference
     */
    public RfrmgrRefereeRelations(String alias) {
        this(alias, RFRMGR_REFEREE_RELATIONS);
    }

    private RfrmgrRefereeRelations(String alias, Table<RfrmgrRefereeRelationsRecord> aliased) {
        this(alias, aliased, null);
    }

    private RfrmgrRefereeRelations(String alias, Table<RfrmgrRefereeRelationsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "Relations between referees and clubs: member and/or reffor.");
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
    public Identity<RfrmgrRefereeRelationsRecord, UInteger> getIdentity() {
        return Keys.IDENTITY_RFRMGR_REFEREE_RELATIONS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<RfrmgrRefereeRelationsRecord> getPrimaryKey() {
        return Keys.KEY_RFRMGR_REFEREE_RELATIONS_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<RfrmgrRefereeRelationsRecord>> getKeys() {
        return Arrays.<UniqueKey<RfrmgrRefereeRelationsRecord>>asList(Keys.KEY_RFRMGR_REFEREE_RELATIONS_PRIMARY, Keys.KEY_RFRMGR_REFEREE_RELATIONS_ID_UNIQUE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RfrmgrRefereeRelations as(String alias) {
        return new RfrmgrRefereeRelations(alias, this);
    }

    /**
     * Rename this table
     */
    public RfrmgrRefereeRelations rename(String name) {
        return new RfrmgrRefereeRelations(name, null);
    }
}
