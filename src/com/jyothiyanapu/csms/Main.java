package com.jyothiyanapu.csms;

import com.jyothiyanapu.csms.model.CivilServant;
import com.jyothiyanapu.csms.util.SqlGenerator;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Optional utility for SQL preview from annotations.
        SqlGenerator.generateCreateTableSQL(CivilServant.class);

        CivilServantController controller = new CivilServantController();
        controller.start();
    }
}
