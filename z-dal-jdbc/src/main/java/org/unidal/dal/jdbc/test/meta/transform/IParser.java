package org.unidal.dal.jdbc.test.meta.transform;

import org.unidal.dal.jdbc.test.meta.entity.*;

public interface IParser<T> {
    EntitiesModel parse(IMaker<T> maker, ILinker linker, T node);

    void parseForEntityModel(IMaker<T> maker, ILinker linker, EntityModel parent, T node);

    void parseForIndexModel(IMaker<T> maker, ILinker linker, IndexModel parent, T node);

    void parseForMemberModel(IMaker<T> maker, ILinker linker, MemberModel parent, T node);

    void parseForParamModel(IMaker<T> maker, ILinker linker, ParamModel parent, T node);

    void parseForPrimaryKeyModel(IMaker<T> maker, ILinker linker, PrimaryKeyModel parent, T node);

    void parseForQueryModel(IMaker<T> maker, ILinker linker, QueryModel parent, T node);

    void parseForReadsetModel(IMaker<T> maker, ILinker linker, ReadsetModel parent, T node);

    void parseForReadsetsModel(IMaker<T> maker, ILinker linker, ReadsetsModel parent, T node);

    void parseForUpdatesetModel(IMaker<T> maker, ILinker linker, UpdatesetModel parent, T node);

    void parseForUpdatesetsModel(IMaker<T> maker, ILinker linker, UpdatesetsModel parent, T node);

    void parseForVarModel(IMaker<T> maker, ILinker linker, VarModel parent, T node);
}
