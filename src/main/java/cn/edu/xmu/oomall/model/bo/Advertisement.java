package cn.edu.xmu.oomall.model.bo;

import cn.edu.xmu.oomall.model.VoObject;
import cn.edu.xmu.oomall.model.enumeration.AdvertisementStatus;
import cn.edu.xmu.oomall.model.po.AdvertisementPo;
import cn.edu.xmu.oomall.model.vo.AdvertisementAuditReceiveVo;
import cn.edu.xmu.oomall.model.vo.AdvertisementCreateReceiveVo;
import cn.edu.xmu.oomall.model.vo.AdvertisementModifyReceiveVo;
import cn.edu.xmu.oomall.model.vo.AdvertisementReturnVo;

import java.time.LocalDateTime;

public class Advertisement implements VoObject {

    private Long id;

    private Long segId;

    private String link;

    private String content;

    private String imageUrl;

    private Byte state;

    private Integer weight;

    private LocalDateTime beginDate;

    private LocalDateTime endDate;

    private Byte repeat;

    private String message;

    private Byte beDefault;

    private LocalDateTime gmtCreated;

    private LocalDateTime gmtModified;

    public Advertisement() {
    }
    
    public Advertisement(AdvertisementPo po){
        this.id = po.getId();
        this.segId = po.getSegId();
        this.link = po.getLink();
        this.content = po.getContent();
        this.imageUrl = po.getImageUrl();
        this.state = po.getState();
        this.weight = po.getWeight();
        this.beginDate = po.getBeginDate();
        this.endDate = po.getEndDate();
        this.repeat = po.getRepeat();
        this.message = po.getMessage();
        this.beDefault = po.getBeDefault();
        this.gmtCreated = po.getGmtCreated();
        this.gmtModified = po.getGmtModified();
    }

    public Advertisement(AdvertisementCreateReceiveVo vo){
        this.content = vo.getContent();
        this.weight = vo.getWeight();
        this.link = vo.getLink();
        this.beginDate = vo.getBeginDate();
        this.endDate = vo.getEndDate();
        this.repeat = vo.getRepeat()?(byte)1:(byte)0;
        this.gmtCreated = LocalDateTime.now();
        this.gmtModified = LocalDateTime.now();
        this.state = AdvertisementStatus.UNAUDITED.getCode().byteValue();   // 刚创建的新广告，待审核
    }

    public Advertisement(AdvertisementModifyReceiveVo vo) {
        link = vo.getLink();
        weight = vo.getWeight();
        content = vo.getContent();
        segId = vo.getSeg_id();
    }

    public Advertisement(AdvertisementAuditReceiveVo vo){


    }

    public AdvertisementReturnVo createReturnVo(){
        AdvertisementReturnVo vo = new AdvertisementReturnVo();
        vo.setId(id);
        vo.setSegId(segId);
        vo.setLink(link);
        vo.setContent(content);
        vo.setImageUrl(imageUrl);
        vo.setState(state);
        vo.setWeight(weight);
        vo.setBeginDate(beginDate);
        vo.setEndDate(endDate);
        vo.setRepeat(repeat);
        vo.setMessage(message);
        vo.setBeDefault(beDefault);
        vo.setGmtCreated(gmtCreated);
        vo.setGmtModified(gmtModified);
        return vo;
    }
    
    public AdvertisementPo createPo(){
        AdvertisementPo po = new AdvertisementPo();
        po.setId(id);
        po.setSegId(segId);
        po.setLink(link);
        po.setContent(content);
        po.setImageUrl(imageUrl);
        po.setState(state);
        po.setWeight(weight);
        po.setBeginDate(beginDate);
        po.setEndDate(endDate);
        po.setRepeat(repeat);
        po.setMessage(message);
        po.setBeDefault(beDefault);
        po.setGmtCreated(gmtCreated);
        po.setGmtModified(gmtModified);
        return po;
    }

//    public <T extends Object> T convertTo(Class<T> targetClass) throws Exception {
//
//        if(targetClass.equals(AdvertisementReturnVo.class)) {
//            System.out.println(targetClass.getName());
//        } else {
//            throw new Exception();
//        }
//
//        return null;
//
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSegId() {
        return segId;
    }

    public void setSegId(Long segId) {
        this.segId = segId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link == null ? null : link.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public LocalDateTime getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDateTime beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Byte getRepeat() {
        return repeat;
    }

    public void setRepeat(Byte repeat) {
        this.repeat = repeat;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    public Byte getBeDefault() {
        return beDefault;
    }

    public void setBeDefault(Byte beDefault) {
        this.beDefault = beDefault;
    }

    public LocalDateTime getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(LocalDateTime gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public LocalDateTime getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(LocalDateTime gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    public String toString() {
        return "Advertisement{" +
                "id=" + id +
                ", segId=" + segId +
                ", link='" + link + '\'' +
                ", content='" + content + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", state=" + state +
                ", weight=" + weight +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", repeat=" + repeat +
                ", message='" + message + '\'' +
                ", beDefault=" + beDefault +
                ", gmtCreated=" + gmtCreated +
                ", gmtModified=" + gmtModified +
                '}';
    }

    @Override
    public Object createVo() {
        return createReturnVo();
    }

    @Override
    public Object createSimpleVo() {
        return null;
    }
}
