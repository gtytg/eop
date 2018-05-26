package cn.mldn.vo;

public class Dictionary {

    private Long dctid;
    private String type;
    private String title;
    private String value;

    @Override
    public String toString() {
        return "Dictionary{" +
                "dctid=" + dctid +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    public Long getDctid() {
        return dctid;
    }

    public void setDctid(Long dctid) {
        this.dctid = dctid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
