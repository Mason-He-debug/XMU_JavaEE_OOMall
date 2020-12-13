package cn.edu.xmu.oomall.mapper;

import cn.edu.xmu.oomall.model.po.TimeSegmentPo;
import cn.edu.xmu.oomall.model.po.TimeSegmentPoExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TimeSegmentPoMapper {

    long countByExample(TimeSegmentPoExample example);

    int deleteByExample(TimeSegmentPoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TimeSegmentPo record);

    int insertSelective(TimeSegmentPo record);

    List<TimeSegmentPo> selectByExample(TimeSegmentPoExample example);

    TimeSegmentPo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TimeSegmentPo record, @Param("example") TimeSegmentPoExample example);

    int updateByExample(@Param("record") TimeSegmentPo record, @Param("example") TimeSegmentPoExample example);

    int updateByPrimaryKeySelective(TimeSegmentPo record);

    int updateByPrimaryKey(TimeSegmentPo record);
}