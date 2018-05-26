package cn.mldn.vo;

import java.util.Date;

public class Emp {

    private String eid;
    private Integer lid;
    private Long did;
    private String ename;
    private Double salary;
    private String phone;
    private String password;
    private String photo;
    private String note;
    private Date hiredate;
    private String ineid;
    private Integer status;
    private Date lastDate;

    @Override
    public String toString() {
        return "Emp{" +
                "eid='" + eid + '\'' +
                ", lid=" + lid +
                ", did=" + did +
                ", ename='" + ename + '\'' +
                ", salary=" + salary +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", photo='" + photo + '\'' +
                ", note='" + note + '\'' +
                ", hiredate=" + hiredate +
                ", ineid='" + ineid + '\'' +
                ", status=" + status +
                ", lastDate=" + lastDate +
                '}';
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public Integer getLid() {
        return lid;
    }

    public void setLid(Integer lid) {
        this.lid = lid;
    }

    public Long getDid() {
        return did;
    }

    public void setDid(Long did) {
        this.did = did;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public String getIneid() {
        return ineid;
    }

    public void setIneid(String ineid) {
        this.ineid = ineid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getLastDate() {
        return lastDate;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }
}
