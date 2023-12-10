package dentalappointment.data;

import java.util.Date;

public class Appointments {
    private String id;
    private String name;
    private String mobile;
    private String address;
    private String date;
    private String time;
    private int patientId;
    private Date createdDate;
    private Date updatedDate;

    private int registrationFee;
    private int totalFee;


    public Appointments() {
    }

    public Appointments(String id, String name, String mobile, String address, String date, String time, int patientId, Date createdDate, Date updatedDate, int registrationFee, int totalFee) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.address = address;
        this.date = date;
        this.time = time;
        this.patientId = patientId;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.registrationFee = registrationFee;
        this.totalFee = totalFee;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public int getRegistrationFee() {
        return registrationFee;
    }

    public void setRegistrationFee(int registrationFee) {
        this.registrationFee = registrationFee;
    }

    public int getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(int totalFee) {
        this.totalFee = totalFee;
    }
}
