package com.netty.battery.maintenance.shenghong.message;

import com.netty.battery.maintenance.shenghong.utils.ASCIIUtil;
import com.netty.battery.maintenance.shenghong.utils.BytesUtil;

/**
 * 心跳响应报文
 * @author za
 *
 */
public class HbResponse extends Message {
	
	private String yuliu1;
	private String yuliu2;
	private String number;
	private String heart;
	
	
	public HbResponse() {
		super();
		yuliu1 = "0000";
		yuliu2 = "0000";
	}


	public HbResponse(int sequence, String number,int heart) {
		this();
		setM_cmd("6500"); // 10进制 101 应答心跳命令码
		// 编码转 32 位
		this.number = ASCIIUtil.ASCII2HexString(number);
		this.heart = BytesUtil.intToHexString(heart);
	}


	@Override
	public void add(StringBuffer sb) {
		sb.append(yuliu1).append(yuliu2).append(number).append(heart);
	}
	

}
