package com.netty.battery.maintenance.shenghong.handle;

import com.netty.battery.maintenance.shenghong.SHUtils;
import com.netty.battery.maintenance.shenghong.manager.ClientManager;
import com.netty.battery.maintenance.shenghong.message.HbResponse;
import com.netty.battery.maintenance.shenghong.message.ShengHong;
import com.netty.battery.maintenance.shenghong.message.SignResponse;
import com.netty.battery.maintenance.shenghong.utils.BytesUtil;
import com.netty.battery.maintenance.shenghong.utils.CommonUtil;

import org.springframework.beans.factory.annotation.Autowired;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * 盛宏心跳
 */
public class ShHeartBeatHandler extends ChannelInboundHandlerAdapter {
    
    //private static final Logger log = LogManager.getLogger(ClientManager.class);

    private String pileCode = "";
    
    
    @Autowired
    //private AlarmDao alarmDao;

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) { // 2
            IdleStateEvent event = (IdleStateEvent) evt;
            String type = "";
            if (event.state() == IdleState.READER_IDLE) {
                type = "read idle";
            } else if (event.state() == IdleState.WRITER_IDLE) {
                type = "write idle";
            } else if (event.state() == IdleState.ALL_IDLE) {
                type = "all idle";
            }


            //System.out.println(ctx.channel().remoteAddress() + " --> 超时类型：" + type);


            if (CommonUtil.isEmpty(pileCode)) {
                ctx.fireUserEventTriggered(evt);
                return;
            }

            //log.info("pipleCode = " + pileCode);
            //System.out.println("pipleCode = " + pileCode);
          //  alarmDao.idle(ctx, pileCode);

        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    @Override
    public void channelRead(final ChannelHandlerContext ctx, Object msg) throws Exception {
        byte[] msg1 = (byte[]) msg;
        if (!SHUtils.isShengHong(msg1)) {
            ctx.fireChannelRead(msg);
            return;
        }

        String cmd = BytesUtil.getMsgCmd(msg1);
        // 桩编码
        pileCode = SHUtils.getPileNum(msg1);
       // log.info( "pipleCode = "+ pileCode +", cmd = "+ BytesUtil.toInt2(msg1, 6) );

        //System.out.println("pipleCode = "+ pileCode +", cmd = "+ BytesUtil.toInt2(msg1, 6) );


        if (cmd.equalsIgnoreCase(ShengHong.HB_MAST)) { //充电桩上传心跳包 cmd=102
            //log.info(" pileCode =  " + pileCode + ", 心跳   ");
           // System.out.println(" pileCode =  " + pileCode + ", 心跳   ");

            ClientManager.addClientConnection(ctx, pileCode);

            ctx.executor().execute(new Runnable() {
                @Override
                public void run() {
                    HbResponse hs = new HbResponse(1,"1111", 2);
                    byte[] hbSlave = hs.getMsgByte(1);
                    ctx.writeAndFlush(hbSlave);
                 
                }
            });
        } else if (cmd.equalsIgnoreCase(ShengHong.SIGN)) { //充电桩签到 cmd=106

            ClientManager.addClientConnection(ctx, pileCode);

            ctx.executor().execute(new Runnable() {
                @Override
                public void run() {
                    SignResponse sr = new SignResponse();
                    byte[] signResp = sr.getMsgByte(1);
                    ctx.writeAndFlush(signResp);
                   
                }
            });
        } else {
            ctx.fireChannelRead(msg);
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
      //  log.error("exceptionCaught ---> " + cause.toString());

        //System.out.println("exceptionCaught --> " + cause.toString());
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
       // alarmDao = SpringContextUtil.getBean("alarmDao");
    }
}
