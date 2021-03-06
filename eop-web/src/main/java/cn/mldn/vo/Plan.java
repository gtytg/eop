package cn.mldn.vo;

import java.util.Date;

public class Plan {

    private Long pid;
    private String title;
    private String item;
    private Date starttime;
    private Date endtime;
    private String note;
    private Integer status;
    private Integer amount;
    private String eid;

    @Override
    public String toString() {
        return "Plan{" +
                "pid=" + pid +
                ", title='" + title + '\'' +
                ", item='" + item + '\'' +
                ", starttime=" + starttime +
                ", endtime=" + endtime +
                ", note='" + note + '\'' +
                ", status=" + status +
                ", amount=" + amount +
                ", eid='" + eid + '\'' +
                '}';
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }
}
