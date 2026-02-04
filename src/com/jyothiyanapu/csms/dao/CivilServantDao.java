package com.jyothiyanapu.csms.dao;

import com.jyothiyanapu.csms.CivilServant;
import java.util.List;


public interface CivilServantDao {
    void save(CivilServant cs);

    CivilServant findById(int id);

    List<CivilServant> findAll();

    void update(CivilServant cs);

    void deleteById(int id);
}
