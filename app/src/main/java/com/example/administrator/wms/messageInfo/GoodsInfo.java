package com.example.administrator.wms.messageInfo;

/**
 * @创建者 AndyYan
 * @创建时间 2018/6/27 14:10
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class GoodsInfo {

    /**
     * fnumber : 01.05.002.002.
     * fname : 工具盒
     * fmodel : 工具盒 MX3.5寸 2.4寸通用  绿扣
     * fname1 : PCS
     * fqty : 6.0000000000
     * fin : 不良原材料仓库
     * fout : 车间仓库
     */

    private String fnumber;
    private String fname;
    private String fmodel;
    private String fname1;
    private String fqty;
    private String fin;
    private String fout;
    /**
     * finterid : 1959
     * fitemid : 2810
     */

    private String finterid;
    private String fitemid;

    public String getFnumber() {
        return fnumber;
    }

    public void setFnumber(String fnumber) {
        this.fnumber = fnumber;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getFmodel() {
        return fmodel;
    }

    public void setFmodel(String fmodel) {
        this.fmodel = fmodel;
    }

    public String getFname1() {
        return fname1;
    }

    public void setFname1(String fname1) {
        this.fname1 = fname1;
    }

    public String getFqty() {
        return fqty;
    }

    public void setFqty(String fqty) {
        this.fqty = fqty;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public String getFout() {
        return fout;
    }

    public void setFout(String fout) {
        this.fout = fout;
    }

    public String getFinterid() {
        return finterid;
    }

    public void setFinterid(String finterid) {
        this.finterid = finterid;
    }

    public String getFitemid() {
        return fitemid;
    }

    public void setFitemid(String fitemid) {
        this.fitemid = fitemid;
    }
}
