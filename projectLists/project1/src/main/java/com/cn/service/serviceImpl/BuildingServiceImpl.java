package com.cn.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.dao.BuildingDao;
import com.cn.entity.BuildingEntity;
import com.cn.service.BuildingService;
@Service
public class BuildingServiceImpl implements BuildingService {

	@Autowired
	private BuildingDao buildingDao;
	
	@Override
	public BuildingEntity findById(String id) {
		return buildingDao.findById(id);
	}

}
