package com.netty.battery.maintenance.shenghong.message.battery;

import com.netty.battery.maintenance.shenghong.SHUtils;
import com.netty.battery.maintenance.shenghong.utils.BytesUtil;
import org.junit.Test;

public class Alarm {

    private String yuliu1;

    private String yuliu2;

    // 终端编码
    private String number;

    // 告警类型 0系统上报的告警 1模块上报的告警
    private String alaremType;

    //模块号 如系统上报的告警 为0 如模块上报告警则为模块号
    private String moduleNum;

    // 告警位信息
    // 系统告警 0-总故障 1-电池低压 2-电池高压 3-温度1过低 4-温度1过高 5-温度2过低 6-温度2过高
    // 模块告警 0-总故障 1-电池低压 2-电池高压 3-温度1过低 4-温度1过高 5-温度2过低 6-温度2过高
    private String alarmDetail;


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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAlaremType() {
        return alaremType;
    }

    public void setAlaremType(String alaremType) {
        this.alaremType = alaremType;
    }

    public String getModuleNum() {
        return moduleNum;
    }

    public void setModuleNum(String moduleNum) {
        this.moduleNum = moduleNum;
    }

    public String getAlarmDetail() {
        return alarmDetail;
    }

    public void setAlarmDetail(String alarmDetail) {
        this.alarmDetail = alarmDetail;
    }


    @Override
    public String toString() {
        return "Alarm{" +
                "yuliu1='" + yuliu1 + '\'' +
                ", yuliu2='" + yuliu2 + '\'' +
                ", number='" + number + '\'' +
                ", alaremType='" + alaremType + '\'' +
                ", moduleNum='" + moduleNum + '\'' +
                ", alarmDetail='" + alarmDetail + '\'' +
                '}';
    }

    /**
         * 将电池上传的信息转化为实体
         *
         * @param msg
         * @return
         */
        public static Alarm getAlarmInfo(byte[] msg) {
            if (msg == null || msg.length == 0) {
                return null;
            }

            Alarm info = new Alarm();

            String pileCode = SHUtils.getPileNum(msg);
            info.setNumber(pileCode);

            //  告警类型 0系统上报的告警 1模块上报的告警
            byte[] alaremType = new byte[2];
            //src表示源数组，srcPos表示源数组要复制的起始位置，desc表示目标数组，length表示要复制的长度。
            System.arraycopy(msg, 42, alaremType, 0, alaremType.length);
            //int mod = BytesUtil.toInt2(moduleNum); //低位在前 2位的byte数组 转 int
            //info.setModuleNum(String.valueOf(mod));
            String alaremT =BytesUtil.bytesToHexString(alaremType);
            int alarm = Integer.parseInt(alaremT,16);
            info.setAlaremType(String.valueOf(alarm));


             // 模块号 如系统上报的告警 为0 如模块上报告警则为模块号
            byte[] modulNum = new byte[2];
            //src表示源数组，srcPos表示源数组要复制的起始位置，desc表示目标数组，length表示要复制的长度。
            System.arraycopy(msg, 44, modulNum, 0, modulNum.length);
            String modulN =BytesUtil.bytesToHexString(modulNum);
            int modul = Integer.parseInt(modulN,16);
            info.setModuleNum(String.valueOf(modul));


            // 告警位信息
            // 系统告警 0-总故障 1-电池低压 2-电池高压 3-温度1过低 4-温度1过高 5-温度2过低 6-温度2过高
            // 模块告警 0-总故障 1-电池低压 2-电池高压 3-温度1过低 4-温度1过高 5-温度2过低 6-温度2过高
            byte[] alarmDetail = new byte[2];
            //src表示源数组，srcPos表示源数组要复制的起始位置，desc表示目标数组，length表示要复制的长度。
            System.arraycopy(msg, 46, alarmDetail, 0, alarmDetail.length);
            String alarmDet =BytesUtil.bytesToHexString(alarmDetail);
            int alarmD = Integer.parseInt(alarmDet,16);
            info.setAlarmDetail(String.valueOf(alarmD));



            return info;
        }












    @Test
    public void demo(){
        //String msg = "aaf5b500109a6800000000000000000000000000000000000000000000000073000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        String msg = "aa f5 b5 00 10 9a";


        byte[] b = BytesUtil.hexStringToBytes(msg);

        byte[] data = new byte[1];

        System.out.println("b====="+b.length);

        //src表示源数组，srcPos表示源数组要复制的起始位置，desc表示目标数组，length表示要复制的长度。
        System.arraycopy(b, 2, data, 0, data.length);

        System.out.println("data====="+BytesUtil.bytesToHexString(data));


    }
}
