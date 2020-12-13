package cn.edu.xmu.oomall.model.vo;

public class AdvertisementModifyReceiveVo {

    String content;

    Integer weight;

    String link;

    Long seg_id;

    public AdvertisementModifyReceiveVo() {
    }

    public AdvertisementModifyReceiveVo(String content, Integer weight, String link, Long seg_id) {
        this.content = content;
        this.weight = weight;
        this.link = link;
        this.seg_id = seg_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Long getSeg_id() {
        return seg_id;
    }

    public void setSeg_id(Long seg_id) {
        this.seg_id = seg_id;
    }

    @Override
    public String toString() {
        return "AdvertisementModifyReceiveVo{" +
                "content='" + content + '\'' +
                ", weight=" + weight +
                ", link='" + link + '\'' +
                ", seg_id='" + seg_id + '\'' +
                '}';
    }

}
