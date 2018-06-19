package com.example.administrator.wms.entity;

/**
 * Created by Administrator on 2018/2/24 0024.
 */

public class AsnDetail {
    String fitemid;
    String fitemname;
    String fsupplier;
    String fqty;
    String funit;

    public String getFitemname() {
        return fitemname;
    }

    public String getFitemid() {
        return fitemid;
    }

    public void setFitemid(String fitemid) {
        this.fitemid = fitemid;
    }

    public void setFitemname(String fitemname) {
        this.fitemname = fitemname;
    }

    public String getFsupplier() {
        return fsupplier;
    }

    public void setFsupplier(String fsupplier) {
        this.fsupplier = fsupplier;
    }

    public String getFqty() {
        return fqty;
    }

    public void setFqty(String fqty) {
        this.fqty = fqty;
    }

    public String getFunit() {
        return funit;
    }

    public void setFunit(String funit) {
        this.funit = funit;
    }
}
