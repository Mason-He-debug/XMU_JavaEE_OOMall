package cn.edu.xmu.oomall.service;

import cn.edu.xmu.oomall.dao.AdvertisementDao;
import cn.edu.xmu.oomall.dao.TimeSegmentDao;
import cn.edu.xmu.oomall.mapper.AdvertisementPoMapper;
import cn.edu.xmu.oomall.mapper.TimeSegmentPoMapper;
import cn.edu.xmu.oomall.model.VoObject;
import cn.edu.xmu.oomall.model.bo.Advertisement;
import cn.edu.xmu.oomall.model.enumeration.AdvertisementStatus;
import cn.edu.xmu.oomall.model.vo.AdvertisementStatusEnumReturnVo;
import cn.edu.xmu.oomall.util.ResponseCode;
import cn.edu.xmu.oomall.util.ReturnObject;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdvertisementService {

    @Autowired
    AdvertisementPoMapper advertisementPoMapper;

    @Autowired
    TimeSegmentPoMapper timeSegmentPoMapper;

    @Autowired
    TimeSegmentService timeSegmentService;

    @Autowired
    AdvertisementDao advertisementDao;

    @Autowired
    TimeSegmentDao timeSegmentDao;

    public ReturnObject<PageInfo<VoObject>> getAdvertisementInPageByTimeSegmentId(long timeSegmentId, int pageNum, int pageSize) {
        return advertisementDao.selectAdvertisementPageByTimeSegmentId(timeSegmentId, pageNum, pageSize);
    }

    public ReturnObject<List<VoObject>> getAdvertisementByCurrentTimeSegment() {
        return advertisementDao.selectAdvertisementListByCurrentTimeSegment();
    }

    public ReturnObject setDefaultAdvertisement(Long advertisementId) {
        return advertisementDao.updateAdvertisementDefault(advertisementId);
    }

    public ReturnObject updateAdvertisement(Long advertisementId, Advertisement advertisement) {
        return advertisementDao.updateAdvertisement(advertisementId, advertisement);
    }

    public ReturnObject deleteAdvertisement(Long advertisementId) {
        return advertisementDao.deleteAdvertisement(advertisementId);
    }

    public ReturnObject onshelfAdvertisementByAdvertisementId(Long advertisementId) {
        return advertisementDao.updateAdvertisementStatus(advertisementId, AdvertisementStatus.ONSHELVED);
    }

    public ReturnObject offshelfAdvertisementByAdvertisementId(Long advertisementId) {
        return advertisementDao.updateAdvertisementStatus(advertisementId, AdvertisementStatus.OFFSHELVED);
    }

    public ReturnObject createAdvertisement(Long timeSegmentId, Advertisement advertisement) {

        // 判断时段ID是否合法
        if(!timeSegmentDao.isTimeSegmentExistByTimeSegmentId(timeSegmentId)){
            return new ReturnObject(ResponseCode.RESOURCE_ID_NOTEXIST);
        }

        // 记录 timeSegmentId 到待插入 bo 中
        advertisement.setSegId(timeSegmentId);

        // 写入新的广告
        return advertisementDao.insertAdvertisement(advertisement);

    }



    public ReturnObject updateAdvertisementTimeSegment(Long timeSegmentId, Long advertisementId) {

        // 判断时段是否存在
        if(!timeSegmentDao.isTimeSegmentExistByTimeSegmentId(timeSegmentId)){
            return new ReturnObject(ResponseCode.RESOURCE_ID_NOTEXIST);
        }

        // 判断广告是否存在
        if(!advertisementDao.isAdvertisementExistByAdvertisementId(advertisementId)){
            return new ReturnObject(ResponseCode.RESOURCE_ID_NOTEXIST);
        }

        // 判断目标时段已存在的广告数量是否已达到上限
        if(advertisementDao.selectAdvertisementPoListByTimeSegmentId(timeSegmentId).size()>=8){
            return new ReturnObject(ResponseCode.ADVERTISEMENT_OUTLIMIT);
        }

        // 更新到新的目标时段
        advertisementDao.updateAdvertisementTimeSegmentId(advertisementId, timeSegmentId);

        return new ReturnObject();

    }

    public ReturnObject auditAdvertisement(Long advertisementId, Boolean auditConclusion, String auditMessage) {

        // 判断广告是否存在
        if(!advertisementDao.isAdvertisementExistByAdvertisementId(advertisementId)){
            return new ReturnObject(ResponseCode.RESOURCE_ID_NOTEXIST);
        }

        // 如果审核通过，那么更新状态为未上架（即已下架）、并更新审核消息
        if(auditConclusion==true){
            advertisementDao.updateAdvertisementStatus(advertisementId, AdvertisementStatus.OFFSHELVED);
            advertisementDao.updateAdvertisementMessage(advertisementId, auditMessage);
        }

        // 返沪结果
        return new ReturnObject();

    }

    public ReturnObject getAllStatesOfAdvertisement() {

        List<AdvertisementStatusEnumReturnVo> result = new ArrayList<>();

        for(AdvertisementStatus statusEnum:AdvertisementStatus.class.getEnumConstants()){
            result.add(new AdvertisementStatusEnumReturnVo(statusEnum));
        }

        return new ReturnObject(result);

    }


//    public Response createAdvertisement(Long timeSegmentId, AdvertisementCreateReceiveVo vo) {
//        Advertisement bo = new Advertisement(vo);
//        bo.setId(timeSegmentId);
//        AdvertisementPo po = bo.createPo();
//        advertisementPoMapper.insert(po);
//        bo.setId(po.getId());
//        PayloadResponse<AdvertisementReturnVo> response = new PayloadResponse<>(bo.createReturnVo());
//        return response;
//    }
//
//    public Response deleteAdvertisement(String advertisementId) {
//        advertisementPoMapper.deleteByPrimaryKey(Long.parseLong(advertisementId));
//        return new SimpleResponse();
//    }
//
//    public Response updateAdvertisementContent(String advertisementId, AdvertisementModifyReceiveVo vo) {
//        advertisementPoMapper.updateByPrimaryKeySelective(new Advertisement(vo).createPo());
//        return new SimpleResponse();
//    }
//
//    public Response selectAllAdvertisementStates() {
//        List<AdvertisementPo> allPoList = advertisementPoMapper.selectByExample(new AdvertisementPoExample());
//        List<Map<String, String>> responseData = new ArrayList<>(allPoList.size());
//        for (AdvertisementPo po : allPoList) {
//            Map<String, String> record = new HashMap<>();
//            record.put("code", String.valueOf(po.getState()));   // 用 String.valueOf 代替 xxxObject.toString 以避免字段为空时报 NullPointerException
//            record.put("name", po.getLink());
//            responseData.add(record);
//        }
//        return new PayloadResponse<>(responseData);
//    }
//
//    public Response setDefaultAdvertisement(String advertisementID) {
//        AdvertisementPo selectiveUpdatePo = new AdvertisementPo();
//        selectiveUpdatePo.setBeDefault((byte) 1);
//        selectiveUpdatePo.setId(Long.parseLong(advertisementID));
//        advertisementPoMapper.updateByPrimaryKeySelective(selectiveUpdatePo);
//        return new SimpleResponse();
//    }
//
//
//
//
//
//    public Response selectAdvertisementOfCurrentTimeSegment(){
//        // 获取当前时段的 timeSegmentId
//        TimeSegment currentTimeSegment = timeSegmentService.getCurrentTimeSegment();
//        Long currentTimeSegmentId = currentTimeSegment.getId();
//        // 创建查询限制条件
//        AdvertisementPoExample example = new AdvertisementPoExample();
//        example.createCriteria().andSegIdEqualTo(currentTimeSegmentId);
//        // 查询结果
//        List<AdvertisementPo> poList = advertisementPoMapper.selectByExample(example);
//        // PO 转 BO 再转 VO
//        List<AdvertisementReturnVo> voList = new ArrayList<>(poList.size());
//        for(AdvertisementPo po:poList){
//            voList.add(new Advertisement(po).createReturnVo());
//        }
//        // 封装并返回
//        return new PayloadResponse<>(voList);
//    }
//
//    public Response saveAdvertisementImage(long advertisementId, MultipartFile file) {
//        // 判断此广告是否存在。如果不存在，那么返回错误
//        AdvertisementPo advertisement = advertisementPoMapper.selectByPrimaryKey(advertisementId);
//        if(advertisement==null){
//            return new SimpleResponse(ResponseCode.RESOURCE_ID_NOTEXIST);
//        }
//        // 保存文件
//        String advertisementImageSaveLocation = LocalPath.advertisementImageSaveLocation;
//        String originalSuffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
//        String filename = advertisementId + originalSuffix;
//        try{
//            Path filePath = Path.of(advertisementImageSaveLocation + "/" + filename);
//            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
//            // 保存成功，更新数据库中的图片链接（如果之前没有）
//            AdvertisementPo updatePo = new AdvertisementPo();
//            updatePo.setId(advertisementId);
//            updatePo.setImageUrl(filePath.toUri().toURL().toString());
//            System.out.println(updatePo);
//            System.out.println(advertisementPoMapper.insertSelective(updatePo));
//            // 返回默认正确响应
//            return new SimpleResponse();
//        } catch (IOException e) {
//            return new SimpleResponse(ResponseCode.LOCAL_FILE_WRITE_NOPRIVILEGE);
//        }
//    }
//
//    public Response updateAdvertisementTimeSegment(Long timeSegmentId, Long advertisementId) {
//        // 判断这个时段是否有效
//
//        // 写入更新
//        AdvertisementPo po = new AdvertisementPo();
//        po.setId(advertisementId);
//        po.setSegId(timeSegmentId);
//        advertisementPoMapper.updateByPrimaryKeySelective(po);
//        return new SimpleResponse();
//    }

}


