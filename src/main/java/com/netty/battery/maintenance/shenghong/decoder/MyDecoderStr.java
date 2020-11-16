package com.netty.battery.maintenance.shenghong.decoder;

import com.netty.battery.maintenance.shenghong.utils.BytesUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * 解码器<br>
 * 解决粘包问题
 *  string 数据
 * @author za
 *
 */
public class MyDecoderStr extends MessageToMessageDecoder<String> {

    private static final Logger log = LoggerFactory.getLogger(MyDecoderStr.class);

    protected void decode(ChannelHandlerContext ctx, String msg, List<Object> out) throws Exception {
        try {

            log.info("decodeStr ---> " + msg);

        } catch (Exception e) {
            log.error(" decodeStr Exception : " + e.toString());
        }
    }

    /**
     * 拆包
     */
    private void unpackSH(byte[] msg, List<Object> out) {
        // 一个整包的长度
        int len = BytesUtil.toInt2(msg, 2);
        if (msg.length <= len) { // 一个整包
            out.add(msg);

        } else {
            // 1.取出一个包的数据
            byte[] array = new byte[len];
            System.arraycopy(msg, 0, array, 0, len);
            out.add(array);

            // 2. 多余的数据继续分包
            int other = msg.length - len;
            array = new byte[other];
            System.arraycopy(msg, len, array, 0, array.length);
            unpackSH(array, out);

        }

    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        InetSocketAddress address = (InetSocketAddress) ctx.channel().remoteAddress();
        String ip = address.toString();

        log.info("连接 -->  RamoteAddress : " + ip + " connected ");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error(" exceptionCaught : " + cause.toString() + " ctx = " 
                    + ctx.channel().toString() );
        ctx.close();
    }

}
