package org.unidal.dal.jdbc.test.meta;

import org.unidal.dal.jdbc.test.meta.entity.*;

public interface IVisitor {

    void visitEntities(EntitiesModel entities);

    void visitEntity(EntityModel entity);

    void visitIndex(IndexModel index);

    void visitMember(MemberModel member);

    void visitParam(ParamModel param);

    void visitPrimaryKey(PrimaryKeyModel primaryKey);

    void visitQuery(QueryModel query);

    void visitReadset(ReadsetModel readset);

    void visitReadsets(ReadsetsModel readsets);

    void visitUpdateset(UpdatesetModel updateset);

    void visitUpdatesets(UpdatesetsModel updatesets);

    void visitVar(VarModel var);
}
