package utils;

import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GeodeticCurve;
import org.gavaghan.geodesy.GlobalCoordinates;

public class DistanceUtils {
	
	/*
	 * 102.740557	25.115057
102.652344	25.022150
	 */
	public static void main(String[] args) {
		GlobalCoordinates source = new GlobalCoordinates(25.115057, 102.740557);
	    GlobalCoordinates target = new GlobalCoordinates(25.022150, 102.652344);
	 
	    double meter1 = getDistanceMeter(source, target, Ellipsoid.Sphere);
	    double meter2 = getDistanceMeter(source, target, Ellipsoid.WGS84);
	 
	    System.out.println("Sphere坐标系计算结果："+meter1 + "米");
	    System.out.println("WGS84坐标系计算结果："+meter2 + "米");
	}
	
	public static double getDistanceMeter(GlobalCoordinates gpsFrom, GlobalCoordinates gpsTo, Ellipsoid ellipsoid){
	    //创建GeodeticCalculator，调用计算方法，传入坐标系、经纬度用于计算距离
	    GeodeticCurve geoCurve = new GeodeticCalculator().calculateGeodeticCurve(ellipsoid, gpsFrom, gpsTo);
	    return geoCurve.getEllipsoidalDistance();
	}

}
