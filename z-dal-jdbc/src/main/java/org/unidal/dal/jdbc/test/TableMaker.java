package org.unidal.dal.jdbc.test;

import org.unidal.dal.jdbc.raw.RawDao;
import org.unidal.dal.jdbc.test.meta.entity.EntitiesModel;
import org.unidal.dal.jdbc.test.meta.transform.DefaultSaxParser;
import org.unidal.lookup.ContainerHolder;
import org.unidal.lookup.annotation.Inject;
import org.unidal.lookup.annotation.Named;

import java.io.InputStream;

@Named
public class TableMaker extends ContainerHolder {
    @Inject
    private RawDao m_dao;

    public void make(String ds, InputStream in) throws Exception {
        EntitiesModel entities = DefaultSaxParser.parse(in);
        TableSchemaBuilder builder = lookup(TableSchemaBuilder.class);

        entities.accept(builder);

        for (String statement : builder.getStatements()) {
            m_dao.executeUpdate(ds, statement);
        }

        release(builder);
    }
}
