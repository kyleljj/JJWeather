package com.lujj.jjweather.util;

import android.text.TextUtils;

import com.lujj.jjweather.db.JJWeatherDB;
import com.lujj.jjweather.model.City;
import com.lujj.jjweather.model.County;
import com.lujj.jjweather.model.Province;

/**
 * Created by lujj on 16/6/30.
 */
public class Utility {

    /**
     * 解析和处理服务器返回的省级数据
     */
    
    public synchronized static boolean handleProvincesResponse(JJWeatherDB jjWeatherDB, String response){
        if (!TextUtils.isEmpty(response)){
            String[] allProvinces = response.split(",");
            if (allProvinces != null && allProvinces.length > 0){
                for (String p : allProvinces){
                    String[] array = p.split("\\|");
                    Province province = new Province();
                    province.setProvinceName(array[1]);
                    province.setProvinceCode(array[0]);
                    
                    //将解析出来的数据存储到Province表
                    jjWeatherDB.saveProvince(province);
                }
                
                return true;
            }
        }
        
        return false;
    }

    /**
     * 解析和处理服务器返回的市级数据
     */
    public static boolean handleCitiesResponse(JJWeatherDB jjWeatherDB, String response, int provinceId){
        
        if (!TextUtils.isEmpty(response)){
            String[] allCities = response.split(",");
            if (allCities != null && allCities.length > 0){
                for (String c : allCities){
                    String[] array = c.split("\\|");
                    City city = new City();
                    city.setCityCode(array[0]);
                    city.setCityName(array[1]);
                    city.setProvinceId(provinceId);
                    
                    jjWeatherDB.saveCity(city);
                }
                
                return true;
            }
        }
        
        return false;
    }

    /**
     * 解析和处理服务器返回的县级数据
     */
    public static boolean handleCountiesResponse(JJWeatherDB jjWeatherDB, String response, int cityId){

        if (!TextUtils.isEmpty(response)){
            String[] allCounties = response.split(",");
            if (allCounties != null && allCounties.length > 0){
                for (String c : allCounties){
                    String[] array = c.split("\\|");
                    County country = new County();
                    country.setCountyCode(array[0]);
                    country.setCountyName(array[1]);
                    country.setCityId(cityId);

                    jjWeatherDB.saveCounty(country);
                }

                return true;
            }
        }

        return false;
    }
    
}
