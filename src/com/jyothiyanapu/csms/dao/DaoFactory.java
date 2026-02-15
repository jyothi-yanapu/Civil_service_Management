package com.jyothiyanapu.csms.dao;

import com.jyothiyanapu.csms.model.CivilServant;

public final class DaoFactory {

    private DaoFactory() {
    }

    public static CivilServantDao getCivilServantDao() {
        return new CivilServantDaoImpl();
    }

    public static <T> GenericDao<T> getGenericDao(Class<T> type) {
        return new JdbcGenericDao<>(type);
    }

    public static GenericDao<CivilServant> getCivilServantGenericDao() {
        return getGenericDao(CivilServant.class);
    }
}
