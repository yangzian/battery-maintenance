package com.netty.battery.maintenance.shenghong.message.battery;

import com.netty.battery.maintenance.shenghong.message.Message;
import com.netty.battery.maintenance.shenghong.message.ShengHong;

public class OpenAndClose extends Message{


    public static final String MAINTENANCE = "0100"; // 养护
    public static final String DISCHARGE = "0200"; //放电
    public static final String REBOOT = "0300"; //复位

    private String yuliu1;
    private String yuliu2;
    private String controlCmd;//控制命令 1-养护 2-放电 3-复位 （2字节）
    private String yuliu3;//（2字节）
    private String yuliu4;//（2字节）
    private String carNun;//终端编码（32字节）
    private String parameter;//参数 1字节 默认0


    public static String getMAINTENANCE() {
        return MAINTENANCE;
    }

    public static String getDISCHARGE() {
        return DISCHARGE;
    }

    public static String getREBOOT() {
        return REBOOT;
    }

    public String getYuliu1() {
        return yuliu1;
    }

    public void setYuliu1(String yuliu1) {
        this.yuliu1 = yuliu1;
    }

    public String getYuliu2() {
        return yuliu2;
    }

    public void setYuliu2(String yuliu2) {
        this.yuliu2 = yuliu2;
    }

    public String getControlCmd() {
        return controlCmd;
    }

    public void setControlCmd(String controlCmd) {
        this.controlCmd = controlCmd;
    }

    public String getYuliu3() {
        return yuliu3;
    }

    public void setYuliu3(String yuliu3) {
        this.yuliu3 = yuliu3;
    }

    public String getYuliu4() {
        return yuliu4;
    }

    public void setYuliu4(String yuliu4) {
        this.yuliu4 = yuliu4;
    }

    public String getCarNun() {
        return carNun;
    }

    public void setCarNun(String carNun) {
        this.carNun = carNun;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }


    @Override
    public String toString() {
        return "OpenAndClose{" +
                "yuliu1='" + yuliu1 + '\'' +
                ", yuliu2='" + yuliu2 + '\'' +
                ", controlCmd='" + controlCmd + '\'' +
                ", yuliu3='" + yuliu3 + '\'' +
                ", yuliu4='" + yuliu4 + '\'' +
                ", carNun='" + carNun + '\'' +
                ", parameter='" + parameter + '\'' +
                '}';
    }


    @Override
    public void add(StringBuffer sb) {
        sb.append(yuliu1).append(yuliu2).append(controlCmd)
                .append(yuliu3).append(yuliu4).append(carNun)
                .append(parameter);
    }


    public OpenAndClose() {
        setM_cmd("0500");
        this.yuliu1 = "0000";
        this.yuliu2 = "0000";
        this.controlCmd = controlCmd;
        this.yuliu3 = "0000";
        this.yuliu4 = "0000";
        this.carNun = carNun;
        this.parameter = "00";
    }

    public OpenAndClose(String yuliu1, String yuliu2, String controlCmd, String yuliu3, String yuliu4, String carNun, String parameter) {
        this.yuliu1 = yuliu1;
        this.yuliu2 = yuliu2;
        this.controlCmd = controlCmd;
        this.yuliu3 = yuliu3;
        this.yuliu4 = yuliu4;
        this.carNun = carNun;
        this.parameter = parameter;
    }

}
