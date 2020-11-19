package com.netty.battery.maintenance.netty.server;

import com.netty.battery.maintenance.shenghong.ShConfig;
import com.netty.battery.maintenance.shenghong.decoder.MyDecoder;
import com.netty.battery.maintenance.shenghong.decoder.MyDecoderStr;
import com.netty.battery.maintenance.shenghong.handle.HandleName;
import com.netty.battery.maintenance.shenghong.handle.ShHeartBeatHandler;
import com.netty.battery.maintenance.shenghong.handle.ShServerHandler;
import com.netty.battery.maintenance.util.EhcacheUtil;
import io.netty.channel.ChannelId;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;


import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;

public class NettyServerChannelInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel channel) throws Exception {

        EhcacheUtil ehcache = EhcacheUtil.getInstance();

        // 1----------string 数据------- 对应解码器  MyDecoderStr
        //channel.pipeline().addLast("decoder",new StringDecoder(CharsetUtil.UTF_8));
        //channel.pipeline().addLast("encoder",new StringEncoder(CharsetUtil.UTF_8));
        //----------string 数据-------

        // 2----------byte 数据------- 对应解码器  MyDecoder
        channel.pipeline().addLast("decoder",new ByteArrayDecoder());
        channel.pipeline().addLast("encoder",new ByteArrayEncoder());
        //----------byte 数据-------


        //超时时间 60秒
        channel.pipeline().addLast(new IdleStateHandler(60,0,0));

        // 对应编码解码 2
        channel.pipeline().addLast(new MyDecoder());

        // 对应编码解码 1
        //channel.pipeline().addLast(new MyDecoderStr());


        channel.pipeline().addLast(new NettyServerHandler()); // 心跳- 签到
        //channel.pipeline().addLast("handler",new NettySystemHandler()); //逻辑
        //channel.pipeline().addLast(HandleName.HANDLE_CHARGE,new NettyChargeHandler());
        channel.pipeline().addLast("charge",new NettyChargeHandler());



        InetSocketAddress insocket = (InetSocketAddress) channel.remoteAddress();
        String clientIp = insocket.getAddress().getHostAddress();
        ChannelId channelId = channel.id(); // 获取连接通道唯一标识通道号

        ehcache.put(clientIp+channelId,channel.pipeline().get("charge"));





    }
}
