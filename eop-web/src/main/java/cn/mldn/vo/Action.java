package cn.mldn.vo;

public class Action {

    private String actid;
    private String rid;
    private String title;

    @Override
    public String toString() {
        return "Action [actid=" + actid + ", rid=" + rid + ", title=" + title + "]";
    }

    public String getActid() {
        return actid;
    }

    public void setActid(String actid) {
        this.actid = actid;
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
