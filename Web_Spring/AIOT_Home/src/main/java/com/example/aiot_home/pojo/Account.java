package com.example.aiot_home.pojo;

public class Account {
    private int id;
    private String phone;
    private String pwd;
    private String name;
    private String identity;
    private String familyid;
    private String faceid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getFamilyid() {
        return familyid;
    }

    public void setFamilyid(String familyid) {
        this.familyid = familyid;
    }

    public String getFaceid() {
        return faceid;
    }

    public void setFaceid(String faceid) {
        this.faceid = faceid;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", pwd='" + pwd + '\'' +
                ", name='" + name + '\'' +
                ", identity='" + identity + '\'' +
                ", familyid='" + familyid + '\'' +
                ", faceid='" + faceid + '\'' +
                '}';
    }
}