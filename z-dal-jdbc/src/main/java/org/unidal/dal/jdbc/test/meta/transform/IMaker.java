package org.unidal.dal.jdbc.test.meta.transform;

import org.unidal.dal.jdbc.test.meta.entity.*;

public interface IMaker<T> {

    EntitiesModel buildEntities(T node);

    EntityModel buildEntity(T node);

    IndexModel buildIndex(T node);

    MemberModel buildMember(T node);

    ParamModel buildParam(T node);

    PrimaryKeyModel buildPrimaryKey(T node);

    QueryModel buildQuery(T node);

    ReadsetModel buildReadset(T node);

    ReadsetsModel buildReadsets(T node);

    UpdatesetModel buildUpdateset(T node);

    UpdatesetsModel buildUpdatesets(T node);

    VarModel buildVar(T node);
}
