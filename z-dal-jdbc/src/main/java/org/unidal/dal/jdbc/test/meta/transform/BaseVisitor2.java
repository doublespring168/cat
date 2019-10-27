package org.unidal.dal.jdbc.test.meta.transform;

import org.unidal.dal.jdbc.test.meta.IVisitor;
import org.unidal.dal.jdbc.test.meta.entity.*;

import java.util.Stack;

public abstract class BaseVisitor2 implements IVisitor {

    private Stack<Object> m_parents = new Stack<Object>();

    @SuppressWarnings("unchecked")
    protected final <T> T getParent() {
        return (T) getAncestor(1);
    }

    /**
     * Get parent object if have.
     *
     * @param backLevels 1 means parent, 2 means parent of parent, and so on.
     * @return parent object, null if not exists.
     */
    @SuppressWarnings("unchecked")
    protected final <T> T getAncestor(int backLevels) {
        if (m_parents.isEmpty()) {
            return null;
        } else if (backLevels == 1) {
            return (T) m_parents.peek();
        } else {
            int size = m_parents.size();

            if (backLevels <= size) {
                return (T) m_parents.get(size - backLevels);
            } else {
                return null;
            }
        }
    }

    protected final Stack<Object> getStack() {
        return m_parents;
    }

    @Override
    public final void visitEntities(EntitiesModel entities) {
        m_parents.push(entities);
        visitEntitiesChildren(entities);
        m_parents.pop();
    }

    protected void visitEntitiesChildren(EntitiesModel entities) {
        for (EntityModel entity : entities.getEntities()) {
            visitEntity(entity);
        }
    }

    protected void visitEntityChildren(EntityModel entity) {
        for (MemberModel member : entity.getMembers()) {
            visitMember(member);
        }

        if (entity.getVar() != null) {
            visitVar(entity.getVar());
        }

        if (entity.getPrimaryKey() != null) {
            visitPrimaryKey(entity.getPrimaryKey());
        }

        if (entity.getReadsets() != null) {
            visitReadsets(entity.getReadsets());
        }

        if (entity.getUpdatesets() != null) {
            visitUpdatesets(entity.getUpdatesets());
        }

        for (QueryModel query : entity.getQueryDefs()) {
            visitQuery(query);
        }

        for (IndexModel index : entity.getIndexs()) {
            visitIndex(index);
        }
    }

    protected void visitMemberChildren(MemberModel member) {
    }

    protected void visitVarChildren(VarModel var) {
    }

    protected void visitPrimaryKeyChildren(PrimaryKeyModel primaryKey) {
    }

    protected void visitReadsetsChildren(ReadsetsModel readsets) {
        if (readsets.getReadset() != null) {
            visitReadset(readsets.getReadset());
        }
    }

    protected void visitUpdatesetsChildren(UpdatesetsModel updatesets) {
        if (updatesets.getUpdateset() != null) {
            visitUpdateset(updatesets.getUpdateset());
        }
    }

    protected void visitQueryChildren(QueryModel query) {
        if (query.getParam() != null) {
            visitParam(query.getParam());
        }
    }

    protected void visitIndexChildren(IndexModel index) {
    }

    protected void visitReadsetChildren(ReadsetModel readset) {
    }

    protected void visitUpdatesetChildren(UpdatesetModel updateset) {
    }

    protected void visitParamChildren(ParamModel param) {
    }

    @Override
    public final void visitEntity(EntityModel entity) {
        m_parents.push(entity);
        visitEntityChildren(entity);
        m_parents.pop();
    }

    @Override
    public final void visitIndex(IndexModel index) {
        m_parents.push(index);
        visitIndexChildren(index);
        m_parents.pop();
    }

    @Override
    public final void visitMember(MemberModel member) {
        m_parents.push(member);
        visitMemberChildren(member);
        m_parents.pop();
    }

    @Override
    public final void visitParam(ParamModel param) {
        m_parents.push(param);
        visitParamChildren(param);
        m_parents.pop();
    }

    @Override
    public final void visitPrimaryKey(PrimaryKeyModel primaryKey) {
        m_parents.push(primaryKey);
        visitPrimaryKeyChildren(primaryKey);
        m_parents.pop();
    }

    @Override
    public final void visitQuery(QueryModel query) {
        m_parents.push(query);
        visitQueryChildren(query);
        m_parents.pop();
    }

    @Override
    public final void visitReadset(ReadsetModel readset) {
        m_parents.push(readset);
        visitReadsetChildren(readset);
        m_parents.pop();
    }

    @Override
    public final void visitReadsets(ReadsetsModel readsets) {
        m_parents.push(readsets);
        visitReadsetsChildren(readsets);
        m_parents.pop();
    }

    @Override
    public final void visitUpdateset(UpdatesetModel updateset) {
        m_parents.push(updateset);
        visitUpdatesetChildren(updateset);
        m_parents.pop();
    }

    @Override
    public final void visitUpdatesets(UpdatesetsModel updatesets) {
        m_parents.push(updatesets);
        visitUpdatesetsChildren(updatesets);
        m_parents.pop();
    }

    @Override
    public final void visitVar(VarModel var) {
        m_parents.push(var);
        visitVarChildren(var);
        m_parents.pop();
    }
}
