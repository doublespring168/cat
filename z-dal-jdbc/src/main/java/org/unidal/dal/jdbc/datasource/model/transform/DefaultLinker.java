package org.unidal.dal.jdbc.datasource.model.transform;

import org.unidal.dal.jdbc.datasource.model.entity.DataSourceDef;
import org.unidal.dal.jdbc.datasource.model.entity.DataSourcesDef;
import org.unidal.dal.jdbc.datasource.model.entity.PropertiesDef;

import java.util.ArrayList;
import java.util.List;

public class DefaultLinker implements ILinker {
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
    public boolean onDataSource(final DataSourcesDef parent, final DataSourceDef dataSource) {
        if (m_deferrable) {
            m_deferedJobs.add(new Runnable() {
                @Override
                public void run() {
                    parent.addDataSource(dataSource);
                }
            });
        } else {
            parent.addDataSource(dataSource);
        }

        return true;
    }

    @Override
    public boolean onProperties(final DataSourceDef parent, final PropertiesDef properties) {
        parent.setProperties(properties);
        return true;
    }
}
