package com.jyothiyanapu.csms;

import com.jyothiyanapu.csms.dao.CivilServantDao;
import com.jyothiyanapu.csms.dao.CivilServantDaoImpl;
import com.jyothiyanapu.csms.model.CivilServant;

import java.util.List;

public class CivilServantService {

    private final CivilServantDao civilServantDao;

    public CivilServantService() {
        this.civilServantDao = new CivilServantDaoImpl();
    }

    public void addCivilServant(CivilServant cs){
        CivilServant existingCivilServant = civilServantDao.findById(cs.getId());
        if (existingCivilServant != null) {
            System.out.println("This ID civil servant already exists");
            return;
        }
        civilServantDao.save(cs);
    }

    public List<CivilServant> getCivilServants(){
        return civilServantDao.findAll();
    }

    public CivilServant getCivilServantById(int id){
        return civilServantDao.findById(id);
    }

    public boolean deleteCivilServantById(int id) {
        return civilServantDao.deleteById(id);
    }
}
