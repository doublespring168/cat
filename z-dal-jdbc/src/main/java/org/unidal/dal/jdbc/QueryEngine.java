package org.unidal.dal.jdbc;

import java.util.List;

public interface QueryEngine {
    String HINT_QUERY = "QUERY";

    String HINT_DATA_OBJECT = "DATA_OBJECT";

    <T extends DataObject> int[] deleteBatch(QueryDef query, T[] protos) throws DalException;

    <T extends DataObject> int deleteSingle(QueryDef query, T proto) throws DalException;

    <T extends DataObject> int[] insertBatch(QueryDef query, T[] protos) throws DalException;

    <T extends DataObject> int insertSingle(QueryDef query, T proto) throws DalException;

    <T extends DataObject> List<T> queryMultiple(QueryDef query, T proto, Readset<?> readset) throws DalException;

    <T extends DataObject> T querySingle(QueryDef query, T proto, Readset<?> readset) throws DalException;

    <T extends DataObject> int[] updateBatch(QueryDef query, T[] protos, Updateset<?> updateset) throws DalException;

    <T extends DataObject> int updateSingle(QueryDef query, T proto, Updateset<?> updateset) throws DalException;
}
