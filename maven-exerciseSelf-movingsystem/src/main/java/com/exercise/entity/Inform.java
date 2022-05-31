package com.exercise.entity;

public class Inform {
    private Integer id;
    private String area;
    private String car;
    private String movingdate;
    private String contact;
    private String telephone;
    private String status;

    public String getMovingdate() {
        return movingdate;
    }

    public void setMovingdate(String movingdate) {
        this.movingdate = movingdate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
