package org.unidal.dal.jdbc.test.meta.transform;

import org.unidal.dal.jdbc.test.meta.entity.*;
import org.xml.sax.Attributes;

import static org.unidal.dal.jdbc.test.meta.Constants.*;

public class DefaultSaxMaker implements IMaker<Attributes> {

    @Override
    public EntitiesModel buildEntities(Attributes attributes) {
        EntitiesModel entities = new EntitiesModel();

        return entities;
    }

    @Override
    public EntityModel buildEntity(Attributes attributes) {
        String name = attributes.getValue(ATTR_NAME);
        String table = attributes.getValue(ATTR_TABLE);
        String alias = attributes.getValue(ATTR_ALIAS);
        EntityModel entity = new EntityModel();

        if (name != null) {
            entity.setName(name);
        }

        if (table != null) {
            entity.setTable(table);
        }

        if (alias != null) {
            entity.setAlias(alias);
        }

        return entity;
    }

    @Override
    public IndexModel buildIndex(Attributes attributes) {
        String name = attributes.getValue(ATTR_NAME);
        String members = attributes.getValue(ATTR_MEMBERS);
        String unique = attributes.getValue(ATTR_UNIQUE);
        IndexModel index = new IndexModel();

        if (name != null) {
            index.setName(name);
        }

        if (members != null) {
            index.setMembers(members);
        }

        if (unique != null) {
            index.setUnique(convert(Boolean.class, unique, null));
        }

        return index;
    }

    @Override
    public MemberModel buildMember(Attributes attributes) {
        String name = attributes.getValue(ATTR_NAME);
        String field = attributes.getValue(ATTR_FIELD);
        String valueType = attributes.getValue(ATTR_VALUE_TYPE);
        String length = attributes.getValue(ATTR_LENGTH);
        String nullable = attributes.getValue(ATTR_NULLABLE);
        String key = attributes.getValue(ATTR_KEY);
        String autoIncrement = attributes.getValue(ATTR_AUTO_INCREMENT);
        String insertExpr = attributes.getValue(ATTR_INSERT_EXPR);
        String updateExpr = attributes.getValue(ATTR_UPDATE_EXPR);
        MemberModel member = new MemberModel();

        if (name != null) {
            member.setName(name);
        }

        if (field != null) {
            member.setField(field);
        }

        if (valueType != null) {
            member.setValueType(valueType);
        }

        if (length != null) {
            member.setLength(convert(Integer.class, length, null));
        }

        if (nullable != null) {
            member.setNullable(convert(Boolean.class, nullable, null));
        }

        if (key != null) {
            member.setKey(convert(Boolean.class, key, null));
        }

        if (autoIncrement != null) {
            member.setAutoIncrement(convert(Boolean.class, autoIncrement, null));
        }

        if (insertExpr != null) {
            member.setInsertExpr(insertExpr);
        }

        if (updateExpr != null) {
            member.setUpdateExpr(updateExpr);
        }

        return member;
    }

    @Override
    public ParamModel buildParam(Attributes attributes) {
        String name = attributes.getValue(ATTR_NAME);
        ParamModel param = new ParamModel();

        if (name != null) {
            param.setName(name);
        }

        return param;
    }

    @Override
    public PrimaryKeyModel buildPrimaryKey(Attributes attributes) {
        String name = attributes.getValue(ATTR_NAME);
        String members = attributes.getValue(ATTR_MEMBERS);
        PrimaryKeyModel primaryKey = new PrimaryKeyModel();

        if (name != null) {
            primaryKey.setName(name);
        }

        if (members != null) {
            primaryKey.setMembers(members);
        }

        return primaryKey;
    }

    @Override
    public QueryModel buildQuery(Attributes attributes) {
        String name = attributes.getValue(ATTR_NAME);
        String type = attributes.getValue(ATTR_TYPE);
        QueryModel query = new QueryModel();

        if (name != null) {
            query.setName(name);
        }

        if (type != null) {
            query.setType(type);
        }

        return query;
    }

    @Override
    public ReadsetModel buildReadset(Attributes attributes) {
        String name = attributes.getValue(ATTR_NAME);
        String all = attributes.getValue(ATTR_ALL);
        ReadsetModel readset = new ReadsetModel();

        if (name != null) {
            readset.setName(name);
        }

        if (all != null) {
            readset.setAll(convert(Boolean.class, all, null));
        }

        return readset;
    }

    @Override
    public ReadsetsModel buildReadsets(Attributes attributes) {
        ReadsetsModel readsets = new ReadsetsModel();

        return readsets;
    }

    @Override
    public UpdatesetModel buildUpdateset(Attributes attributes) {
        String name = attributes.getValue(ATTR_NAME);
        String all = attributes.getValue(ATTR_ALL);
        UpdatesetModel updateset = new UpdatesetModel();

        if (name != null) {
            updateset.setName(name);
        }

        if (all != null) {
            updateset.setAll(convert(Boolean.class, all, null));
        }

        return updateset;
    }

    @Override
    public UpdatesetsModel buildUpdatesets(Attributes attributes) {
        UpdatesetsModel updatesets = new UpdatesetsModel();

        return updatesets;
    }

    @Override
    public VarModel buildVar(Attributes attributes) {
        String name = attributes.getValue(ATTR_NAME);
        String valueType = attributes.getValue(ATTR_VALUE_TYPE);
        String keyMember = attributes.getValue(ATTR_KEY_MEMBER);
        VarModel var = new VarModel();

        if (name != null) {
            var.setName(name);
        }

        if (valueType != null) {
            var.setValueType(valueType);
        }

        if (keyMember != null) {
            var.setKeyMember(keyMember);
        }

        return var;
    }

    @SuppressWarnings("unchecked")
    protected <T> T convert(Class<T> type, String value, T defaultValue) {
        if (value == null) {
            return defaultValue;
        }

        if (type == Boolean.class) {
            return (T) Boolean.valueOf(value);
        } else if (type == Integer.class) {
            return (T) Integer.valueOf(value);
        } else if (type == Long.class) {
            return (T) Long.valueOf(value);
        } else if (type == Short.class) {
            return (T) Short.valueOf(value);
        } else if (type == Float.class) {
            return (T) Float.valueOf(value);
        } else if (type == Double.class) {
            return (T) Double.valueOf(value);
        } else if (type == Byte.class) {
            return (T) Byte.valueOf(value);
        } else if (type == Character.class) {
            return (T) (Character) value.charAt(0);
        } else {
            return (T) value;
        }
    }
}
