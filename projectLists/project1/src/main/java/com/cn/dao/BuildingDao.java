package com.cn.dao;

import org.springframework.stereotype.Repository;
import com.cn.entity.BuildingEntity;
/**
 * @author xiepanfeng
 *
 */
@Repository
public interface BuildingDao {
	/**
	 * @param id
	 * @return
	 */
	BuildingEntity findById(String id);
	
}
