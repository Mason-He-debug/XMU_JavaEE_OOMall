package cn.edu.xmu.oomall.controller;

import cn.edu.xmu.oomall.model.bo.Advertisement;
import cn.edu.xmu.oomall.model.vo.AdvertisementAuditReceiveVo;
import cn.edu.xmu.oomall.model.vo.AdvertisementCreateReceiveVo;
import cn.edu.xmu.oomall.model.vo.AdvertisementModifyReceiveVo;
import cn.edu.xmu.oomall.service.AdvertisementService;
import cn.edu.xmu.oomall.util.Common;
import cn.edu.xmu.oomall.util.ResponseCode;
import cn.edu.xmu.oomall.util.ReturnObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdvertisementController {

    @Autowired
    AdvertisementService advertisementService;



    // 获得所有广告状态
    @GetMapping("/advertisement/states")
    public Object getAllStatesOfAdvertisement(){
        return advertisementService.getAllStatesOfAdvertisement();
    }




    // 管理员设置默认广告
    @PutMapping("/advertisement/{id}/default")
    public Object setDefaultAdvertisement(@PathVariable("id")Long advertisementId){
        ReturnObject returnObject = advertisementService.setDefaultAdvertisement(advertisementId);
        if (returnObject.getCode() == ResponseCode.OK) {
            return Common.getRetObject(returnObject);
        } else {
            return Common.decorateReturnObject(returnObject);
        }
    }




    // 修改广告内容
    @PutMapping("/advertisement/{id}")
    public Object upadteAdvertisement(@PathVariable("id")Long advertisementId, @RequestBody AdvertisementModifyReceiveVo vo){
        ReturnObject returnObject =  advertisementService.updateAdvertisement(advertisementId, new Advertisement(vo));
        if (returnObject.getCode() == ResponseCode.OK) {
            return Common.getRetObject(returnObject);
        } else {
            return Common.decorateReturnObject(returnObject);
        }
    }





    // 删除一个广告
    @DeleteMapping("/advertisement/{id}")
    public Object deleteAdvertisement(@PathVariable("id")Long advertisementId){
        ReturnObject returnObject =  advertisementService.deleteAdvertisement(advertisementId);
        if (returnObject.getCode() == ResponseCode.OK) {
            return Common.getRetObject(returnObject);
        } else {
            return Common.decorateReturnObject(returnObject);
        }
    }





    // 获得当前时间段下的所有广告详情列表（不分页）
    @GetMapping("/advertisement/current")
    public Object selectAdvertisementByCurrentTime(){
        ReturnObject returnObject = advertisementService.getAdvertisementByCurrentTimeSegment();
        if (returnObject.getCode() == ResponseCode.OK) {
            return Common.getListRetObject(returnObject);
        } else {
            return Common.decorateReturnObject(returnObject);
        }
    }



//     给广告上传图片
//    @PostMapping("/advertisement/{advertisementId}/uploadImg")
//    public Response uploadAdvertisementImage(@PathVariable("advertisementId")String advertisementId ,@RequestParam("img")MultipartFile file){
//        return advertisementService.saveAdvertisementImage(Long.parseLong(advertisementId),file);
//    }


    // 上架广告
    @PutMapping("/advertisement/{id}/onshelves")
    public Object onshelfAdvertisementByAdvertisementId(@PathVariable("id")Long advertisementId){
        ReturnObject returnObject = advertisementService.onshelfAdvertisementByAdvertisementId(advertisementId);
        if (returnObject.getCode() == ResponseCode.OK) {
            return Common.getListRetObject(returnObject);
        } else {
            return Common.decorateReturnObject(returnObject);
        }
    }


    // 下架广告
    @PutMapping("/advertisement/{id}/offshelves")
    public Object offshelfAdvertisementByAdvertisementId(@PathVariable("id")Long advertisementId){

        ReturnObject returnObject = advertisementService.offshelfAdvertisementByAdvertisementId(advertisementId);
        if (returnObject.getCode() == ResponseCode.OK) {
            return Common.getListRetObject(returnObject);
        } else {
            return Common.decorateReturnObject(returnObject);
        }
    }



    // 审核广告
    @PutMapping("/advertisement/{id}/audit")
    public Object auditAdvertisement(@PathVariable("id")Long advertisementId, @RequestBody AdvertisementAuditReceiveVo vo){
        ReturnObject returnObject = advertisementService.auditAdvertisement(advertisementId, vo.getConclusion(), vo.getMessage());
        if(returnObject.getCode()==ResponseCode.OK){
            return Common.getRetObject(returnObject);
        } else {
            return Common.decorateReturnObject(returnObject);
        }
    }



    // 查看某个时间段下的所有广告详情列表（分页）
    @GetMapping("/timesegments/{timeSegmentId}/advertisement")
    public Object getAdvertisementInPageByTimeSegment(@PathVariable("timeSegmentId")String timeSegmentId, @RequestParam("page")String pageNum, @RequestParam("pageSize")String pageSize){
        ReturnObject returnObject = advertisementService.getAdvertisementInPageByTimeSegmentId(Long.parseLong(timeSegmentId), Integer.parseInt(pageNum), Integer.parseInt(pageSize));
        if (returnObject.getCode() == ResponseCode.OK) {
            return Common.getPageRetObject(returnObject);
        } else {
            return Common.decorateReturnObject(returnObject);
        }
    }



    // 在时段下创建广告
    @PostMapping("/timesegments/{id}/advertisement")
    public Object createAdvertisement(@PathVariable("id")Long timeSegmentId, @RequestBody AdvertisementCreateReceiveVo vo){
        ReturnObject returnObject = advertisementService.createAdvertisement(timeSegmentId,new Advertisement(vo));
        if (returnObject.getCode() == ResponseCode.OK) {
            return Common.getRetObject(returnObject);
        } else {
            return Common.decorateReturnObject(returnObject);
        }
    }



    // 更新广告所属时段
    @PostMapping("/timesegments/{tid}/advertisement/{id}")
    public ReturnObject updateAdvertisementTimeSegment(@PathVariable("tid")Long timeSegmentId, @PathVariable("id")Long advertisementId){
        return advertisementService.updateAdvertisementTimeSegment(timeSegmentId, advertisementId);
    }



}
