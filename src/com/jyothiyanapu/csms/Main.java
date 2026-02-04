package com.jyothiyanapu.csms;

import com.jyothiyanapu.csms.util.SqlGenerator;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
//      //Create the service.
//        CivilServantService service = new CivilServantService();
//        //Add 3 civil servants
//        CivilServant cs1 = new CivilServant(1, "jyo", "administration" ,"ias", 50000);
//        CivilServant cs2 = new CivilServant(2, "Bob", "HR", "Manager", 60000);
//        CivilServant cs3 = new CivilServant(3, "Charlie", "IT", "Developer", 70000);
//
//        service.addCivilServant(cs1);
//        service.addCivilServant(cs2);
//        service.addCivilServant(cs3);
//
//        System.out.println("All Civil Servants:");
//        for (CivilServant cs : s` ervice.getCivilServants()) {
//            System.out.println(cs);
//        }
//
//        //fetch a civil servant by ID
//        int idToFetch = 2;
//        CivilServant fetched = service.getCivilServantById(idToFetch);
//
//        if(fetched != null){
//            System.out.println("Civil servant with ID" +idToFetch + " is found");
//            System.out.println(fetched);
//        }
//        else{
//            System.out.println("Civil servant with ID" + idToFetch + "notfound");
//        }
//


//        CivilServantController controller = new CivilServantController();
        SqlGenerator.generateCreateTableSQL(CivilServant.class);

        // Start the console menu
//        controller.start();
    }
}