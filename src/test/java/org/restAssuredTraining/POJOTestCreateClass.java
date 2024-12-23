package org.restAssuredTraining;

import java.util.HashMap;

public class POJOTestCreateClass {

    public String getCvv() {
        return cvv;
    }

    public String getType() {
        return type;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public int getExpiry_month() {
        return expiry_month;
    }

    public int getExpiry_year() {
        return expiry_year;
    }

    public HashMap<String, String> getBilling_address() {
        return billing_address;
    }

    public HashMap<String, String> getPhone() {
        return phone;
    }

    String type,number,name,cvv;
    int expiry_month,expiry_year;
    HashMap<String,String> billing_address = new HashMap<>();
    HashMap<String,String> phone = new HashMap<>();

    public void setPhone(HashMap<String, String> phone) {
        this.phone = phone;
    }

    public void setBilling_address(HashMap<String, String> billing_address) {
        this.billing_address = billing_address;
    }

    public void setExpiry_year(int expiry_year) {
        this.expiry_year = expiry_year;
    }

    public void setExpiry_month(int expiry_month) {
        this.expiry_month = expiry_month;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setType(String type) {
        this.type = type;
    }



}
