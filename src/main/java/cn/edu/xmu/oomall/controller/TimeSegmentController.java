package cn.edu.xmu.oomall.controller;

import cn.edu.xmu.oomall.model.bo.TimeSegment;
import cn.edu.xmu.oomall.model.vo.TimeSegmentReceiveVo;
import cn.edu.xmu.oomall.model.vo.TimeSegmentReturnVo;
import cn.edu.xmu.oomall.service.TimeSegmentService;
import cn.edu.xmu.oomall.util.ResponseCode;
import cn.edu.xmu.oomall.util.page.Page;
import cn.edu.xmu.oomall.util.response.PayloadResponse;
import cn.edu.xmu.oomall.util.response.Response;
import cn.edu.xmu.oomall.util.response.SimpleResponse;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TimeSegmentController {

    @Autowired
    TimeSegmentService timeSegmentService;


    @GetMapping("/flashsale/timesegments")
    public Response getFlashSaleTimeSegment(@RequestParam("page")Integer pageNum, @RequestParam("pageSize")Integer pageSize){
        // 获取封装了分页信息的结果集
        Page pageObject = timeSegmentService.getFlashSaleTimeSegmentByPage(pageNum,pageSize);
        // 把列表中的 BO 替换成对应 VO
        List<TimeSegment> boList = pageObject.getList();
        List<TimeSegmentReturnVo> voList = new ArrayList<>(boList.size());
        for(TimeSegment bo:boList){
            voList.add(bo.convertToReturnVo());
        }
        pageObject.setList(voList);
        // 获取封装了分页集合的返回对象
        PayloadResponse response = new PayloadResponse(pageObject);
        // 发回包装后的结果
        return response;
    }


    @PostMapping("/advertisement/timesegments")
    public Response createAdvertisementTimeSegment(@RequestBody TimeSegmentReceiveVo timeSegmentVo){


        TimeSegment bo = timeSegmentService.createAdvertisementTimeSegment(timeSegmentVo);
        TimeSegmentReturnVo vo = bo.convertToReturnVo();
        PayloadResponse response = new PayloadResponse(vo);
        return response;
    }

    @GetMapping("/advertisement/timesegments")
    public Response getAdvertisementTimeSegment(@RequestParam("page")Integer pageIndex, @RequestParam("pageSize")Integer pageSize){
        // 获取封装了分页信息的结果集
        Page pageObject = timeSegmentService.getAdvertisementTimeSegmentByPage(pageIndex,pageSize);
        // 把列表中的 BO 替换成对应 VO
        List<TimeSegment> boList = pageObject.getList();
        List<TimeSegmentReturnVo> voList = new ArrayList<>(boList.size());
        for(TimeSegment bo:boList){
            voList.add(bo.convertToReturnVo());
        }
        pageObject.setList(voList);
        // 获取封装了分页集合的返回对象
        PayloadResponse response = new PayloadResponse(pageObject);
        // 发回包装后的结果
        return response;
    }

    @DeleteMapping("/advertisement/timesegments/{timeSegmentID}")
    public Response deleteAdvertisementTimeSegment(@PathVariable("timeSegmentID") String timeSegmentID){
        Boolean success = timeSegmentService.deleteAdvertisementTimeSegment(timeSegmentID);
        if(success==true){
            return new SimpleResponse();
        } else {
            return new SimpleResponse(ResponseCode.RESOURCE_ID_NOTEXIST);
        }
    }

    @PostMapping("/flashsale/timesegments")
    public Response createFlashSaleTimeSegment(@RequestBody TimeSegmentReceiveVo timeSegmentVo){
        TimeSegment bo = timeSegmentService.createFlashSaleTimeSegment(timeSegmentVo);
        TimeSegmentReturnVo returnVo = bo.convertToReturnVo();
        PayloadResponse response = new PayloadResponse(returnVo);
        return response;
    }

    @DeleteMapping("/flashsale/timesegments/{timeSegmentID}")
    public Response deleteFlashSaleTimeSegment(@PathVariable("timeSegmentID") String timeSegmentID){
        Boolean success = timeSegmentService.deleteFlashSaleTimeSegment(timeSegmentID);
        if(success==true){
            return new SimpleResponse();
        } else {
            return new SimpleResponse(ResponseCode.FIELD_NOTVALID);
        }
    }

}
