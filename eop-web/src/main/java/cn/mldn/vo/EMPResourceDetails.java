package cn.mldn.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class EMPResourceDetails implements Serializable {
	private Long eredid ;
	private Long eresid ;
	private Long resid ;
	private Integer amount ;
	public Long getEredid() {
		return eredid;
	}
	public void setEredid(Long eredid) {
		this.eredid = eredid;
	}
	public Long getEresid() {
		return eresid;
	}
	public void setEresid(Long eresid) {
		this.eresid = eresid;
	}
	public Long getResid() {
		return resid;
	}
	public void setResid(Long resid) {
		this.resid = resid;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	
}
