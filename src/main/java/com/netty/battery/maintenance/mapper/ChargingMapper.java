package com.netty.battery.maintenance.mapper;

import com.netty.battery.maintenance.pojo.BasChaPilPojo;
import com.netty.battery.maintenance.shenghong.message.ChargeRecordInfo;
import com.netty.battery.maintenance.shenghong.message.PileStateInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * ***************************************************
 *
 * @Auther: zianY
 * @Descipion: TODO
 * @CreateDate: 2019-11-15
 * ****************************************************
 */


@Mapper
@Repository
public interface ChargingMapper {


    List<Map<String,Object>> getUserInfo();





}
