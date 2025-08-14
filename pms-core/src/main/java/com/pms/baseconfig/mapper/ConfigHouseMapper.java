package com.pms.baseconfig.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pms.baseconfig.entity.ConfigHouseEntity;
import com.pms.baseconfig.model.house.HouseContractListVO;
import com.pms.baseconfig.model.house.HouseContractPagination;

public interface ConfigHouseMapper extends BaseMapper<ConfigHouseEntity> {
	
	//分页查询商铺的租售数据
	List<HouseContractListVO> getHouseContractList(HouseContractPagination page);
	
	//查询商铺租售的总记录
	int getHouseContractCount(HouseContractPagination page);
	
	@Select("SELECT count(1) as count FROM config_house where state = #{state}")
    int getHouseCountByState(@Param("state") String state);
}
