package cn.edu.xmu.oomall.model.bo;

import cn.edu.xmu.oomall.model.enumeration.TimeSegmentType;
import cn.edu.xmu.oomall.model.po.TimeSegmentPo;
import cn.edu.xmu.oomall.model.vo.TimeSegmentReceiveVo;
import cn.edu.xmu.oomall.model.vo.TimeSegmentReturnVo;

import java.time.LocalDateTime;

public class TimeSegment {

    private Long id;

    private LocalDateTime beginTime;

    private LocalDateTime endTime;

    private TimeSegmentType type;

    private LocalDateTime gmtCreated;

    private LocalDateTime gmtModified;

    public TimeSegment() {
    }

    public TimeSegment(Long id, LocalDateTime beginTime, LocalDateTime endTime, TimeSegmentType type, LocalDateTime gmtCreated, LocalDateTime gmtModified) {
        this.id = id;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.type = type;
        this.gmtCreated = gmtCreated;
        this.gmtModified = gmtModified;
    }

    public TimeSegment(TimeSegmentPo po){
        id = po.getId();
        beginTime = po.getBeginTime();
        endTime = po.getEndTime();
        gmtCreated = po.getGmtCreated();
        gmtModified = po.getGmtModified();
        type = TimeSegmentType.parseTypeEnumFromPoValue(po);
    }

    public TimeSegment(TimeSegmentReceiveVo vo, TimeSegmentType type){
        beginTime = vo.getBeginTime();
        endTime = vo.getEndTime();
        gmtCreated = LocalDateTime.now();
        gmtModified = LocalDateTime.now();
        this.type = type;
    }

    public TimeSegmentPo convertToPo(){
        TimeSegmentPo po = new TimeSegmentPo();
        po.setId(id);
        po.setBeginTime(beginTime);
        po.setEndTime(endTime);
        po.setGmtCreated(gmtCreated);
        po.setGmtModified(gmtModified);
        po.setType(TimeSegmentType.findTypeValueForPo(type));
        return po;
    }

    public TimeSegmentReturnVo convertToReturnVo(){
        TimeSegmentReturnVo vo = new TimeSegmentReturnVo();
        vo.setId(id);
        vo.setBeginTime(beginTime);
        vo.setEndTime(endTime);
        vo.setGmtCreated(gmtCreated);
        vo.setGmtModified(gmtModified);
        return vo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(LocalDateTime beginTime) {
        this.beginTime = beginTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public TimeSegmentType getType() {
        return type;
    }

    public void setType(TimeSegmentType type) {
        this.type = type;
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
        return "TimeSegment{" +
                "id=" + id +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                ", type=" + type +
                ", gmtCreated=" + gmtCreated +
                ", gmtModified=" + gmtModified +
                '}';
    }

}
