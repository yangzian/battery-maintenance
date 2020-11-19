package com.netty.battery.maintenance.netty.server;

import com.netty.battery.maintenance.mapper.ChargingMapper;
import com.netty.battery.maintenance.shenghong.SHUtils;
import com.netty.battery.maintenance.shenghong.manager.ClientConnection;
import com.netty.battery.maintenance.shenghong.manager.ClientManager;
import com.netty.battery.maintenance.shenghong.message.*;
import com.netty.battery.maintenance.shenghong.utils.BytesUtil;
import com.netty.battery.maintenance.shenghong.utils.CommonUtil;
import com.netty.battery.maintenance.util.ApplicationContextUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;
import org.springframework.context.ApplicationContext;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;


// 控制  养护 放电 复位
public class NettyChargeHandler extends SimpleChannelInboundHandler<byte[]> {




    private ApplicationContext applicationContext= ApplicationContextUtils.getApplicationContext();

    ChargingMapper chargingMapper = applicationContext.getBean(ChargingMapper.class);



    private int flag;



    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }


    public NettyChargeHandler(){
        super();
    }



    @Override
    protected void channelRead0(ChannelHandlerContext ctx, byte[] msg) throws Exception {

        System.out.println("system-------------------------"+msg);

        if (!SHUtils.isShengHong(msg)){
            ctx.fireChannelRead(msg);
            return;
        }
        byte cmd = msg[6];

        InetSocketAddress address = (InetSocketAddress) ctx.channel().remoteAddress();

        String ip = address.getAddress().getHostAddress();

        String pile = SHUtils.getPileNum(msg);

//
        final ClientConnection client = ClientManager.getClientConnection(ip,pile);



        if (cmd == 0x06) { //命令代码 为 6 开启控制后 电池应答
            int res = BytesUtil.toInt1(msg, 44); // 命令执行结果

            if (res == 0) { // 0-成功 其他失败
                if (client != null) {
                    if (flag == 0) { //

                        //电池当前状态 0-失败 1-养护 2-放电 3-放电
                        System.out.println("开启  -- 成功");

                        client.setPileState(1); //
                    } else {

                        System.out.println("chargeHandler=====开启 -- 成功");

                        client.setPileState(2);// 2

                    }
                }

            } else {
                if (client != null) {

                    client.setPileState(0); //失败
                }

                System.out.println("开启--失败");
            }
        } else if (cmd == 0x08) {

            if (msg[50] == 0) {
                if (client != null) {
                    client.setPileState(ClientConnection.STATE_NORMAL); //0
                    //client.setPileState(ClientConnection.STATE_CHARGE_OVER); //3

                    System.out.println("停止充电--成功");

                }
            } else {
                if (client != null) {

                    client.setPileState(ClientConnection.STATE_START_FAILED);//4
                }
                System.out.println("取消预约/停止充电--失败");
            }
        }





//ctx.flush();
    }



    /**
     * @param ctx
     * @author xiongchuan on 2019/4/28 16:10
     * @DESCRIPTION: 发生异常会触发此函数
     * @return: void
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //ctx.close();

        //super.exceptionCaught(ctx, cause);
       // Channel channel = ctx.channel();
       // if(channel.isActive()){
            //ctx.close();
        ctx.flush();
      //  }

        System.out.println("charge======>>>>>"+cause+"====="+cause.getMessage());

//
         //System.out.println(ctx.channel().id() + " guanbi,此连接被关闭" + "此时连通数量: " + CHANNEL_MAP.size());



    }
}
