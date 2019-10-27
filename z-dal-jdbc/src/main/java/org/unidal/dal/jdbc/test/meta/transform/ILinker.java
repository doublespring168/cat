package org.unidal.dal.jdbc.test.meta.transform;

import org.unidal.dal.jdbc.test.meta.entity.*;

public interface ILinker {

    boolean onEntity(EntitiesModel parent, EntityModel entity);

    boolean onIndex(EntityModel parent, IndexModel index);

    boolean onMember(EntityModel parent, MemberModel member);

    boolean onParam(QueryModel parent, ParamModel param);

    boolean onPrimaryKey(EntityModel parent, PrimaryKeyModel primaryKey);

    boolean onQuery(EntityModel parent, QueryModel query);

    boolean onReadset(ReadsetsModel parent, ReadsetModel readset);

    boolean onReadsets(EntityModel parent, ReadsetsModel readsets);

    boolean onUpdateset(UpdatesetsModel parent, UpdatesetModel updateset);

    boolean onUpdatesets(EntityModel parent, UpdatesetsModel updatesets);

    boolean onVar(EntityModel parent, VarModel var);
}
