package com.example.calsys.api27nov;

/**
 * Created by Vish on 11/27/2017.
 */

public class Inventory {
    private String iid;
    private String itemcode;
    private String item;
    private String unit;

    public Inventory(String iid, String itemcode, String item,String unit) {
        this.iid = iid;
        this.itemcode = itemcode;
        this.item = item;
        this.unit = unit;
    }

    public String iid() {
        return iid;
    }
    public String itemcode() {
        return itemcode;
    }
    public String item() {
        return item;
    }
    public String unit() {
        return unit;
    }

}
