package com.netty.battery.maintenance.controller;

import com.netty.battery.maintenance.config.ServerResponse;
import com.netty.battery.maintenance.netty.server.NettyServer;
import com.netty.battery.maintenance.pojo.BasChaPilPojo;
import com.netty.battery.maintenance.service.serviceImpl.ChargingServiceImpl;
import com.netty.battery.maintenance.util.EhcacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.net.InetSocketAddress;
import java.util.List;


@Component
@Order(value = 1)
public class StartServer implements ApplicationRunner {


    @Value("${pile.soket_ip}")
    private String soketIp;

    @Value("${pile.soket_port}")
    private Integer soketPort;

    @Autowired
    private NettyServer nettyServer;



    @Autowired
    private ChargingServiceImpl chargingService;



    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {

        EhcacheUtil ehcache = EhcacheUtil.getInstance();

        //初始化设备编号 需要用设备编号 获取 设备id
        List<BasChaPilPojo> pileList = chargingService.selChaIp(null,null);

        if (pileList.size() > 0){

            for (BasChaPilPojo pojo : pileList){

                ehcache.put(pojo.getChaNum(),pojo.getChaId());

            }

        }

        //System.out.println("023-------------------------------->"+ehcache.get("023"));

        InetSocketAddress address = new InetSocketAddress(soketIp, soketPort);

        int i =  nettyServer.start(address);

        if (i != 0){
           // System.out.println("============================pile start server faile====================");
        }
        //System.out.println("============================pile start server success====================");

    }
}
