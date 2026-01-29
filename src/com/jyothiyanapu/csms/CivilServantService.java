package com.jyothiyanapu.csms;

import java.util.ArrayList;
import java.util.List;

//Store civil servants in memory and Perform operations
public class CivilServantService {

    List<CivilServant> CivilServants = new ArrayList<>();

    public void addCivilServant(CivilServant cs){
        CivilServants.add(cs);
    }

    public List<CivilServant> getCivilServants(){
        return CivilServants;
    }

    public CivilServant getCivilServantById(int id){

//        for(int i = 0 ; i < CivilServants.size() ; i++){
//            if(CivilServants.get(i).getId() == id){
//                return CivilServants.get(i);
//            }
//        }
//improved code for the above.
        for(CivilServant cs : CivilServants){
            if(cs.getId() == id) {
                return cs;
            }
        }

        return null;
    }


    public boolean deleteCivilServantById(int id) {
        for (CivilServant cs : CivilServants) {
            if (cs.getId() == id) {
                CivilServants.remove(cs);
                return true;
            }
        }
        return false;
    }
}
