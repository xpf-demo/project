package com.cn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cn.entity.BuildingEntity;
import com.cn.service.BuildingService;

@Controller
@RequestMapping("/building")
public class BuildingController {
	
	@Autowired
	private BuildingService buildingService;

	@RequestMapping("/test")
	public Object test() throws ClassNotFoundException{
		System.out.println("22222");
		BuildingEntity entity = buildingService.findById("0f9ee63b433849faa4233a396997a096");
		//Class<?> clazz = Class.forName("com.cn.entity.BuildingEntity");
		//Class<?> clazz1 = BuildingEntity.class;
		System.out.println(entity);
		return "hh";
	}
	
	
}
