package com.shanaptech.bloodbank.Utils;

public class UserInfo {

    String userid ;
    String name;
    String bd ;
    String phone ;
    String last_donate;
    String bloodtype ;
    String city ;
    String governrate ;

    public UserInfo(String userid, String name, String bd, String phone, String last_donate, String bloodtype, String city, String governrate) {
        this.userid = userid;
        this.name = name;
        this.bd = bd;
        this.phone = phone;
        this.last_donate = last_donate;
        this.bloodtype = bloodtype;
        this.city = city;
        this.governrate = governrate;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBd() {
        return bd;
    }

    public void setBd(String bd) {
        this.bd = bd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLast_donate() {
        return last_donate;
    }

    public void setLast_donate(String last_donate) {
        this.last_donate = last_donate;
    }

    public String getBloodtype() {
        return bloodtype;
    }

    public void setBloodtype(String bloodtype) {
        this.bloodtype = bloodtype;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGovernrate() {
        return governrate;
    }

    public void setGovernrate(String governrate) {
        this.governrate = governrate;
    }
}
