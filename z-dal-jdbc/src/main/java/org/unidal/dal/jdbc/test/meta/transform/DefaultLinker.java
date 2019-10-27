package org.unidal.dal.jdbc.test.meta.transform;

import org.unidal.dal.jdbc.test.meta.entity.*;

import java.util.ArrayList;
import java.util.List;

public class DefaultLinker implements ILinker {
    @SuppressWarnings("unused")
    private boolean m_deferrable;

    private List<Runnable> m_deferedJobs = new ArrayList<Runnable>();

    public DefaultLinker(boolean deferrable) {
        m_deferrable = deferrable;
    }

    public void finish() {
        for (Runnable job : m_deferedJobs) {
            job.run();
        }
    }

    @Override
    public boolean onEntity(final EntitiesModel parent, final EntityModel entity) {
        parent.addEntity(entity);
        return true;
    }

    @Override
    public boolean onIndex(final EntityModel parent, final IndexModel index) {
        parent.addIndex(index);
        return true;
    }

    @Override
    public boolean onMember(final EntityModel parent, final MemberModel member) {
        parent.addMember(member);
        return true;
    }

    @Override
    public boolean onParam(final QueryModel parent, final ParamModel param) {
        parent.setParam(param);
        return true;
    }

    @Override
    public boolean onPrimaryKey(final EntityModel parent, final PrimaryKeyModel primaryKey) {
        parent.setPrimaryKey(primaryKey);
        return true;
    }

    @Override
    public boolean onQuery(final EntityModel parent, final QueryModel query) {
        parent.addQuery(query);
        return true;
    }

    @Override
    public boolean onReadset(final ReadsetsModel parent, final ReadsetModel readset) {
        parent.setReadset(readset);
        return true;
    }

    @Override
    public boolean onReadsets(final EntityModel parent, final ReadsetsModel readsets) {
        parent.setReadsets(readsets);
        return true;
    }

    @Override
    public boolean onUpdateset(final UpdatesetsModel parent, final UpdatesetModel updateset) {
        parent.setUpdateset(updateset);
        return true;
    }

    @Override
    public boolean onUpdatesets(final EntityModel parent, final UpdatesetsModel updatesets) {
        parent.setUpdatesets(updatesets);
        return true;
    }

    @Override
    public boolean onVar(final EntityModel parent, final VarModel var) {
        parent.setVar(var);
        return true;
    }
}
