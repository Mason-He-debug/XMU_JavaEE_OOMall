package cn.edu.xmu.oomall.dao;

import cn.edu.xmu.oomall.mapper.AdvertisementPoMapper;
import cn.edu.xmu.oomall.model.VoObject;
import cn.edu.xmu.oomall.model.bo.Advertisement;
import cn.edu.xmu.oomall.model.enumeration.AdvertisementStatus;
import cn.edu.xmu.oomall.model.po.AdvertisementPo;
import cn.edu.xmu.oomall.model.po.AdvertisementPoExample;
import cn.edu.xmu.oomall.util.ResponseCode;
import cn.edu.xmu.oomall.util.ReturnObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class AdvertisementDao {

    @Autowired
    AdvertisementPoMapper advertisementPoMapper;


    public ReturnObject<VoObject> insertAdvertisement(Advertisement advertisement){

        AdvertisementPo advertisementPo = advertisement.createPo();

        advertisementPoMapper.insert(advertisementPo);

        return new ReturnObject<VoObject>(new Advertisement(advertisementPo));

    }




    public ReturnObject<PageInfo<VoObject>> selectAdvertisementPageByTimeSegmentId(Long timeSegmentId, Integer pageNum, Integer pageSize) {

        // 设置查询条件，按照要求的 timeSegmentId 进行查询
        AdvertisementPoExample example = new AdvertisementPoExample();
        example.createCriteria().andSegIdEqualTo(timeSegmentId);

        // 分页查询
        PageHelper.startPage(pageNum,pageSize);
        List<AdvertisementPo> poList = advertisementPoMapper.selectByExample(example);
        PageInfo<AdvertisementPo> poPage = new PageInfo<>(poList);

        // 转换成 BO 的 Page
        List<VoObject> boList = new ArrayList<>(poList.size());
        for(AdvertisementPo po:poList){
            boList.add(new Advertisement(po));
        }
        PageInfo<VoObject> boPage = new PageInfo<>(boList);
        boPage.setPageNum(poPage.getPageNum());
        boPage.setPageSize(poPage.getPageSize());
        boPage.setPages(poPage.getPages());
        boPage.setTotal(poPage.getTotal());

        // 返回结果
        return new ReturnObject<>(boPage);

    }



    public ReturnObject<List<VoObject>> selectAdvertisementListByCurrentTimeSegment() {

        // 先获取当前时段的 timeSegmentId
        Long currentTimeSegmentId = 8L; // DEBUG

        // 设置 Example 查询条件
        AdvertisementPoExample example = new AdvertisementPoExample();
        example.createCriteria().andSegIdEqualTo(currentTimeSegmentId);

        // 执行查询
        List<AdvertisementPo> poList = advertisementPoMapper.selectByExample(example);

        // PO 转换 BO
        List<VoObject> boList = new ArrayList<>(poList.size());
        for(AdvertisementPo po:poList){
            boList.add(new Advertisement(po));
        }

        return new ReturnObject<List<VoObject>>(boList);

    }







    public AdvertisementPo selectAdvertisementPoByAdvertisementId(Long advertisementId){
        return advertisementPoMapper.selectByPrimaryKey(advertisementId);
    }



    public List<AdvertisementPo> selectAdvertisementPoListByTimeSegmentId(Long timeSegmentId){
        AdvertisementPoExample example = new AdvertisementPoExample();
        example.createCriteria().andSegIdEqualTo(timeSegmentId);
        return advertisementPoMapper.selectByExample(example);
    }


    public ReturnObject updateAdvertisement(Long advertisementId, Advertisement bo) {

        // 不存在时返回错误
        if(this.selectAdvertisementPoByAdvertisementId(advertisementId)==null){
            return new ReturnObject(ResponseCode.RESOURCE_ID_NOTEXIST);
        }

        // 写入更新内容
        AdvertisementPo updatePo = bo.createPo();
        updatePo.setId(advertisementId);
        advertisementPoMapper.updateByPrimaryKeySelective(updatePo);

        return new ReturnObject();

    }





    public ReturnObject updateAdvertisementDefault(Long advertisementId) {

        AdvertisementPo targetPo = selectAdvertisementPoByAdvertisementId(advertisementId);

        if(targetPo==null){
            return new ReturnObject(ResponseCode.RESOURCE_ID_NOTEXIST);
        }

        targetPo.setBeDefault((byte)1);

        advertisementPoMapper.updateByPrimaryKeySelective(targetPo);

        return new ReturnObject();

    }




    public ReturnObject updateAdvertisementTimeSegmentId(Long advertisementId, Long timeSegmentId){

        AdvertisementPo updatePo = new AdvertisementPo();
        updatePo.setId(advertisementId);
        updatePo.setSegId(timeSegmentId);
        advertisementPoMapper.updateByPrimaryKeySelective(updatePo);
        return new ReturnObject();

    }



    public ReturnObject updateAdvertisementStatus(Long advertisementId, AdvertisementStatus updateStatus) {

        if(this.selectAdvertisementPoByAdvertisementId(advertisementId)==null){
            return new ReturnObject(ResponseCode.RESOURCE_ID_NOTEXIST);
        }

        AdvertisementPo updatePo = new AdvertisementPo();
        updatePo.setId(advertisementId);
        updatePo.setState(updateStatus.getCode().byteValue());         // !TODO: 状态值

        advertisementPoMapper.updateByPrimaryKeySelective(updatePo);

        return new ReturnObject();

    }



    public ReturnObject updateAdvertisementMessage(Long advertisementId, String updateMessage){

        if(this.selectAdvertisementPoByAdvertisementId(advertisementId)==null){
            return new ReturnObject(ResponseCode.RESOURCE_ID_NOTEXIST);
        }

        AdvertisementPo updatePo = new AdvertisementPo();
        updatePo.setId(advertisementId);
        updatePo.setMessage(updateMessage);

        advertisementPoMapper.updateByPrimaryKeySelective(updatePo);

        return new ReturnObject();

    }



    public ReturnObject deleteAdvertisement(Long advertisementId) {

        if(this.selectAdvertisementPoByAdvertisementId(advertisementId)==null){
            return new ReturnObject(ResponseCode.RESOURCE_ID_NOTEXIST);
        }

        advertisementPoMapper.deleteByPrimaryKey(advertisementId);

        return new ReturnObject();

    }




    public boolean isAdvertisementExistByAdvertisementId(Long advertisementId){
        return this.selectAdvertisementPoByAdvertisementId(advertisementId)!=null;
    }



}
