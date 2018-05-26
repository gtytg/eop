package cn.mldn.vo;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class EMPResource implements Serializable {
	private Long eresid ;
	private String eid ;
	private String aeid ;
	private String title ;
	private Date subdate ;
	private Integer amount ;
	private Double money ;
	private Integer status ;
	private String renote ;
	private Date auditdate ;
	public Long getEresid() {
		return eresid;
	}
	public void setEresid(Long eresid) {
		this.eresid = eresid;
	}
	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
	}
	public String getAeid() {
		return aeid;
	}
	public void setAeid(String aeid) {
		this.aeid = aeid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getSubdate() {
		return subdate;
	}
	public void setSubdate(Date subdate) {
		this.subdate = subdate;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getRenote() {
		return renote;
	}
	public void setRenote(String renote) {
		this.renote = renote;
	}
	public Date getAuditdate() {
		return auditdate;
	}
	public void setAuditdate(Date auditdate) {
		this.auditdate = auditdate;
	}
	
	
}
