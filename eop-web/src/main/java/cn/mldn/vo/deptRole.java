package cn.mldn.vo;

public class deptRole {
    private Long did;
    private String rid;

    public Long getDid() {
        return did;
    }

    public void setDid(Long did) {
        this.did = did;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    @Override
    public String toString() {
        return "dept_role [did=" + did + ", rid=" + rid + "]";
    }
}
