package com.netty.battery.maintenance.netty.server;

import com.netty.battery.maintenance.shenghong.SHUtils;
import com.netty.battery.maintenance.shenghong.manager.ClientConnection;
import com.netty.battery.maintenance.shenghong.manager.ClientManager;
import com.netty.battery.maintenance.shenghong.message.*;
import com.netty.battery.maintenance.shenghong.message.battery.Alarm;
import com.netty.battery.maintenance.shenghong.message.battery.Monitor;
import com.netty.battery.maintenance.shenghong.message.battery.ReplyMonitor;
import com.netty.battery.maintenance.shenghong.message.battery.SystemInfo;
import com.netty.battery.maintenance.shenghong.utils.ASCIIUtil;
import com.netty.battery.maintenance.shenghong.utils.BytesUtil;
import com.netty.battery.maintenance.shenghong.utils.CommonUtil;
import com.netty.battery.maintenance.util.EhcacheUtil;
import io.netty.channel.*;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {



    /**
     * 管理一个全局map，保存连接进服务端的通道数量
     */
    private static final ConcurrentHashMap<ChannelId, ChannelHandlerContext> CHANNEL_MAP = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, ChannelHandlerContext> CLIENT_MAP = new ConcurrentHashMap<>();



    /**
     * @param ctx
     * @author
     * @DESCRIPTION: 有客户端连接服务器会触发此函数
     * @return: void
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {


        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();

        String clientIp = insocket.getAddress().getHostAddress();

        int clientPort = insocket.getPort();

        //获取连接通道唯一标识
        ChannelId channelId = ctx.channel().id();

        if (CLIENT_MAP.containsKey(clientIp)) {

            System.out.println("客户端【" + channelId + "】是连接状态，连接通道数量: " + CLIENT_MAP.size());


        } else {
            //保存连接
            CLIENT_MAP.put(clientIp, ctx);

            System.out.println("客户端【" + channelId + "】连接netty服务器[IP:" + clientIp + "--->PORT:" + clientPort + "]");
            //System.out.println("连接通道数量: " + CHANNEL_MAP.size());

        }

    }



    /**
     * 获取客户端连接
     *
     * @param ip
     *            ip地址
     * @return
     */
    public static ChannelHandlerContext getClientConnection(String ip) {

        ChannelHandlerContext conn = CLIENT_MAP.get(ip);
        if (conn != null)
            return conn;
        else {
//            logger.error("ClientConenction not found in allClientMap, ip = "
//                + ip + ", pileCode = " + pileCode);

            //System.out.println("ClientConenction not found in allClientMap, ip = "+ ip + ", pileCode = " + pileCode);

        }
        return null;
    }




    /**
     * @param ctx
     * @DESCRIPTION: 有客户端终止连接服务器会触发此函数
     * @return: void
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {

        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();

        String clientIp = insocket.getAddress().getHostAddress();

        ChannelId channelId = ctx.channel().id();

        //包含此客户端才去删除
        if (CHANNEL_MAP.containsKey(channelId)) {
            //删除连接
            CHANNEL_MAP.remove(channelId);

            System.out.println("客户端【" + channelId + "】退出netty服务器[IP:" + clientIp + "--->PORT:" + insocket.getPort() + "]");
           // System.out.println("连接通道数量: " + CHANNEL_MAP.size());


        }
    }






    /**
     * @param ctx
     * @DESCRIPTION: 有客户端发消息会触发此函数
     * @return: void
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        byte[] msg1 = (byte[]) msg;
        if (!SHUtils.isShengHong(msg1)){
            ctx.fireChannelRead(msg1);
        }

        // 获取 cmd 命令
        String cmd = BytesUtil.getMsgCmd(msg1);

        String pileCode = SHUtils.getPileNum(msg1);

        ClientManager.addClientConnection(ctx,pileCode);

        if (cmd.equalsIgnoreCase("6a00")){ //告警 cmd=106

            System.out.println("接收告警 cmd=106----------"+BytesUtil.bytesToHexString2(msg1));

            Alarm alarm = Alarm.getAlarmInfo(msg1);

            System.out.println(alarm+"---告警信息------");


            ReplyMonitor replyMonitor = new ReplyMonitor(1,pileCode,"6900"); //应答 105
            byte[] reply = replyMonitor.getMsgByte(1);

            System.out.println("回复告警-cmd=105----------"+BytesUtil.bytesToHexString2(reply));;
            //响应客户端
            ctx.writeAndFlush(reply);


        } else if (cmd.equalsIgnoreCase("6600")){ //电池上传心跳包数据 cmd=102

            System.out.println("接收心跳 cmd=102----------"+BytesUtil.bytesToHexString2(msg1));

            HbResponse hs = new HbResponse(1,pileCode, 2);
            byte[] hbSlave = hs.getMsgByte(1);

            System.out.println("回复心跳-cmd=101----------"+BytesUtil.bytesToHexString2(hbSlave));;
            //响应客户端
            ctx.writeAndFlush(hbSlave);

        }else if(cmd.equalsIgnoreCase("6800")){// 104 监控信息上报
            System.out.println("接收监控 cmd=104----------"+BytesUtil.bytesToHexString2(msg1));

            Monitor monitor = Monitor.getMonitorInfo(msg1);

            System.out.println(monitor+"---监控信息上报------");

            ReplyMonitor replyMonitor = new ReplyMonitor(1,pileCode,"6700"); //应答 103
            byte[] reply = replyMonitor.getMsgByte(1);

            System.out.println("回复监控-cmd=103----------"+BytesUtil.bytesToHexString2(reply));;
            //响应客户端
            ctx.writeAndFlush(reply);


        }else if(cmd.equalsIgnoreCase("ca00")){// 202 系统信息上报
            System.out.println("接收系统信息 cmd=202----------"+BytesUtil.bytesToHexString2(msg1));

            SystemInfo monitor = SystemInfo.getSystemInfo(msg1);

            System.out.println(monitor+"---接收系统信息解析------");

            ReplyMonitor replyMonitor = new ReplyMonitor(1,pileCode,"c900"); // 应答 201
            byte[] reply = replyMonitor.getMsgByte(1);
            System.out.println("回复系统信息-cmd=201----------"+BytesUtil.bytesToHexString2(reply));;
            //响应客户端
            ctx.writeAndFlush(reply);


        }else {

            ctx.fireChannelRead(msg);
        }




            //响应客户端
        //this.channelWrite(ctx.channel().id(), msg);
    }




    /**
     * @param msg        需要发送的消息内容
     * @param channelId 连接通道唯一id
     * @author xiongchuan on 2019/4/28 16:10
     * @DESCRIPTION: 服务端给客户端发送消息
     * @return: void
     */
    public void channelWrite(ChannelId channelId, Object msg) throws Exception {

       /* ChannelHandlerContext ctx = CHANNEL_MAP.get(channelId);

        if (ctx == null) {

            //System.out.println("通道【" + channelId + "】不存在");
            return;
        }

        if (msg == null && msg == "") {
           // System.out.println("服务端响应空的消息");
            return;
        }

        //将客户端的信息直接返回写入ctx
        ctx.write(msg);
        //刷新缓存区
        ctx.flush();
        */
    }


private String pileCode = "";



    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

        String socketString = ctx.channel().remoteAddress().toString();

        String type ="";

        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.READER_IDLE) {
                type = "读超时";
            } else if (event.state() == IdleState.WRITER_IDLE) {
                type = "写超时";
            } else if (event.state() == IdleState.ALL_IDLE) {
                type = "总超时";
            }

            if (CommonUtil.isEmpty(pileCode)){
                ctx.fireUserEventTriggered(evt);
                return;

            }
            //超时
            //System.out.println(pileCode+"连接超时了");
        }else {
            super.userEventTriggered(ctx,evt);
        }

    }




    /**
     * @param ctx
     * @author xiongchuan on 2019/4/28 16:10
     * @DESCRIPTION: 发生异常会触发此函数
     * @return: void
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        ctx.close();
//
       // System.out.println(ctx.channel().id() + " guanbi,此连接被关闭" + "此时连通数量: " + CHANNEL_MAP.size());



        }

}
