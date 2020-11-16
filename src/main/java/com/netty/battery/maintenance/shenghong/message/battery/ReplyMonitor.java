package com.netty.battery.maintenance.shenghong.message.battery;

import com.netty.battery.maintenance.shenghong.message.Message;
import com.netty.battery.maintenance.shenghong.utils.ASCIIUtil;

public class ReplyMonitor extends Message {

    private String yuliu1;
    private String yuliu2;
    private String number;


    public ReplyMonitor() {


    }

    public ReplyMonitor(int sequence, String number,String cmd) {
        yuliu1 = "0000";
        yuliu2 = "0000";
        setM_cmd(cmd); // 10进制 应答命令码
        // 编码转 32 位
        this.number = ASCIIUtil.ASCII2HexString(number);
    }

    @Override
    public void add(StringBuffer sb) {
        sb.append(yuliu1).append(yuliu2).append(number);
    }
}
