package cn.mldn.vo;

public class Role {

    private String rid;
    private String title;

    @Override
    public String toString() {
        return "Role [rid=" + rid + ", title=" + title + "]";
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
