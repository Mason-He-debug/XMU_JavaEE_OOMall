package cn.edu.xmu.oomall.dao;

import cn.edu.xmu.oomall.mapper.TimeSegmentPoMapper;
import cn.edu.xmu.oomall.model.po.TimeSegmentPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TimeSegmentDao {

    @Autowired
    TimeSegmentPoMapper timeSegmentPoMapper;

    public TimeSegmentPo getTimeSegmentPoByTimeSegmentId(Long timeSegmentId){
        return timeSegmentPoMapper.selectByPrimaryKey(timeSegmentId);
    }

    public Boolean isTimeSegmentExistByTimeSegmentId(Long timeSegmentId){
        if(this.getTimeSegmentPoByTimeSegmentId(timeSegmentId)!=null){
            return true;
        } else {
            return false;
        }
    }

}
