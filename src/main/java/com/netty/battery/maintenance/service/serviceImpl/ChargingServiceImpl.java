package com.netty.battery.maintenance.service.serviceImpl;

import com.netty.battery.maintenance.config.ServerResponse;
import com.netty.battery.maintenance.constans.DefaultConstans;
import com.netty.battery.maintenance.mapper.ChargingMapper;
import com.netty.battery.maintenance.netty.server.NettyServer;
import com.netty.battery.maintenance.pojo.BasChaPilPojo;
import com.netty.battery.maintenance.service.ChargingService;
import com.netty.battery.maintenance.shenghong.manager.ClientConnection;
import com.netty.battery.maintenance.shenghong.manager.ClientManager;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;
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

@Service
public class ChargingServiceImpl implements ChargingService {





    @Value("${pile.soket_ip}")
    private String soketIp;

    @Value("${pile.soket_port}")
    private Integer soketPort;



    @Autowired
    private NettyServer nettyServer;


    @Autowired
    private ChargingMapper chargingMapper;



    /**
     * 测试
     * @return
     */
    public ServerResponse getUserInfo(){

        List<Map<String,Object>> userList = chargingMapper.getUserInfo();

        return ServerResponse.createBySuccess("查询成功",userList);
    }











    public ServerResponse startService(){

            // 根据服务ip 和 端口号 开启服务
            //
          //InetSocketAddress address = new InetSocketAddress(DefaultConstans.SOKET_IP, DefaultConstans.SOKET_PORT);
          InetSocketAddress address = new InetSocketAddress(soketIp, soketPort);

        System.out.println("address1-------------"+address);

            int i =  nettyServer.start(address);

           if (i != 0){

               startService();

               //return ServerResponse.createByErrorMessage("shibai。");
           }
 /*
        SHServer shServer = new SHServer(9999);
           shServer.start();;
           */

        return ServerResponse.createBySuccess("启动成功",0);


    }


















    //  停止服务  0成功 1失败
    public Integer stopService(String chaIp,String chaNum){

        //try {


            //获取 客户端 连接
            ClientConnection conn = ClientManager.getClientConnection(chaIp,chaNum);

            if (conn != null){

                InetSocketAddress insocket = (InetSocketAddress) conn.getCtx().channel().remoteAddress();

                nettyServer.stop();

                return 0;

            }

        //} catch (Exception e) {

          //  e.printStackTrace();
            //return 0;
        //}

        return 1;

    }





    public List<BasChaPilPojo> selChaIp(String chaId,String chaNum){

       // return chargingMapper.selChaIp(chaId,chaNum);


        return null;
    }


}
