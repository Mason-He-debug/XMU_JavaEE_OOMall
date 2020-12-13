package cn.edu.xmu.oomall.service;

import cn.edu.xmu.oomall.mapper.TimeSegmentPoMapper;
import cn.edu.xmu.oomall.model.bo.TimeSegment;
import cn.edu.xmu.oomall.model.enumeration.TimeSegmentType;
import cn.edu.xmu.oomall.model.po.TimeSegmentPoExample;
import cn.edu.xmu.oomall.model.vo.TimeSegmentReceiveVo;
import cn.edu.xmu.oomall.util.page.Page;
import cn.edu.xmu.oomall.model.po.TimeSegmentPo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TimeSegmentService {

    @Autowired
    TimeSegmentPoMapper timeSegmentPoMapper;

    public TimeSegment createAdvertisementTimeSegment(TimeSegmentReceiveVo timeSegmentVo){
        TimeSegment bo = new TimeSegment(timeSegmentVo, TimeSegmentType.ADVERTISEMENT);
        TimeSegmentPo po = bo.convertToPo();
        timeSegmentPoMapper.insert(po);
        // 自增主键值从注入的 PO 回写到 BO
        bo.setId(po.getId());
        return bo;
    }

    public Page<TimeSegment> getAdvertisementTimeSegmentByPage(Integer pageIndex, Integer pageSize) {
//         查询得到 PO 列表与分页信息
        PageHelper.startPage(pageIndex,pageSize);
        TimeSegmentPoExample example = new TimeSegmentPoExample();
        example.createCriteria().andTypeEqualTo(TimeSegmentType.findTypeValueForPo(TimeSegmentType.ADVERTISEMENT));
        PageInfo<TimeSegmentPo> pageInfo = new PageInfo<>(timeSegmentPoMapper.selectByExample(example));
        // 用 PageInfo 生成 PageObject
        Page pageObject = new Page();
        pageObject.setPage(pageInfo.getPageNum());
        pageObject.setPageSize(pageInfo.getPageSize());
        pageObject.setTotal(pageInfo.getTotal());
        pageObject.setPages(pageInfo.getPages());
        // 过程中，把列表里的 PO 替换成 BO
        List<TimeSegmentPo> poList = pageInfo.getList();
        List<TimeSegment> boList = new ArrayList<>(poList.size());
        for(TimeSegmentPo po:poList){
            boList.add(new TimeSegment(po));
        }
        pageObject.setList(boList);
        // 返回封装好的 PageObject。列表中是 BO
        return pageObject;
    }


    public Boolean deleteAdvertisementTimeSegment(String timeSegmentID) {
        return timeSegmentPoMapper.deleteByPrimaryKey(Long.parseLong(timeSegmentID))==1 ? true : false;
    }

    public TimeSegment createFlashSaleTimeSegment(TimeSegmentReceiveVo timeSegmentVo){
        TimeSegment bo = new TimeSegment(timeSegmentVo, TimeSegmentType.FLASHSALE);
        TimeSegmentPo po = bo.convertToPo();
        timeSegmentPoMapper.insert(po);
        bo.setId(po.getId());
        return bo;
    }


    public Page<TimeSegment> getFlashSaleTimeSegmentByPage(Integer pageNum, Integer pageSize) {
        //         查询得到 PO 列表与分页信息
        PageHelper.startPage(pageNum,pageSize);
        TimeSegmentPoExample example = new TimeSegmentPoExample();
        example.createCriteria().andTypeEqualTo(TimeSegmentType.findTypeValueForPo(TimeSegmentType.FLASHSALE));
        PageInfo<TimeSegmentPo> pageInfo = new PageInfo<>(timeSegmentPoMapper.selectByExample(example));
        // 用 PageInfo 生成 PageObject
        Page pageObject = new Page();
        pageObject.setPage(pageInfo.getPageNum());
        pageObject.setPageSize(pageInfo.getPageSize());
        pageObject.setTotal(pageInfo.getTotal());
        pageObject.setPages(pageInfo.getPages());
        // 过程中，把列表里的 PO 替换成 BO
        List<TimeSegmentPo> poList = pageInfo.getList();
        List<TimeSegment> boList = new ArrayList<>(poList.size());
        for(TimeSegmentPo po:poList){
            boList.add(new TimeSegment(po));
        }
        pageObject.setList(boList);
        // 返回封装好的 PageObject。列表中是 BO
        return pageObject;
    }

    public Boolean deleteFlashSaleTimeSegment(String timeSegmentID) {
        return timeSegmentPoMapper.deleteByPrimaryKey(Long.parseLong(timeSegmentID))==1 ? true : false;
    }

    public TimeSegment getCurrentTimeSegment(){
        TimeSegmentPoExample example = new TimeSegmentPoExample();
        Date current = new Date();
        example.createCriteria().andBeginTimeLessThan(LocalDateTime.now()).andEndTimeGreaterThan(LocalDateTime.now());
        List<TimeSegmentPo> timeSegmentPos = timeSegmentPoMapper.selectByExample(example);
        if(timeSegmentPos.size()==1){
            return new TimeSegment(timeSegmentPos.get(0));
        } else {
            return null;
        }
    }

}
