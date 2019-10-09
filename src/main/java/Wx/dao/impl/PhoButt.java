package Wx.dao.impl;

import Wx.dao.AbstractButt;


public class PhoButt extends AbstractButt {
    private String type="pic_sysphoto";
    private  String key ;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public PhoButt(String name, String key) {
        super(name);
        this.key = key;
    }
}
