package com.netty.battery.maintenance.shenghong.message.battery;

import com.netty.battery.maintenance.shenghong.SHUtils;
import com.netty.battery.maintenance.shenghong.message.PileStateInfo;
import com.netty.battery.maintenance.shenghong.utils.BytesUtil;
import org.junit.Test;

import java.util.Arrays;

public class Monitor {

    private String yuliu1;

    private String yuliu2;

    // 终端编码
    private String number;

    // 模块数
    private String moduleNum;

    // 模块状态
    private String moduleSta;

    //模块 电池电压
    private String moduleVol;

    // 模块 温度1 正极
    private String moduleTemPos;

    // 模块 温度2 负极
    private String moduleTemNeg;

    //软件版本
    private String softwareVersion;

    //脉冲输出电压
    private String pulseOutputVoltage;

    //电池内阻
    private String batteryInternalResstance;

    //养护电流
    private String curingCurrent;

    //SOC
    private String soc;

    //SOH
    private String soh;

    //模块 电池电压系数
    private String batteryVoltageCoefficient;

    // 模块 温度1 正极 系数
    private String moduleTemCoefficientPos;

    // 模块 温度2 负极 系数
    private String moduleTemCoefficientNeg;


    private String yuliu3;
    private String yuliu4;
    private String yuliu5;


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

    public String getModuleNum() {
        return moduleNum;
    }

    public void setModuleNum(String moduleNum) {
        this.moduleNum = moduleNum;
    }

    public String getModuleSta() {
        return moduleSta;
    }

    public void setModuleSta(String moduleSta) {
        this.moduleSta = moduleSta;
    }

    public String getModuleVol() {
        return moduleVol;
    }

    public void setModuleVol(String moduleVol) {
        this.moduleVol = moduleVol;
    }

    public String getModuleTemPos() {
        return moduleTemPos;
    }

    public void setModuleTemPos(String moduleTemPos) {
        this.moduleTemPos = moduleTemPos;
    }

    public String getModuleTemNeg() {
        return moduleTemNeg;
    }

    public void setModuleTemNeg(String moduleTemNeg) {
        this.moduleTemNeg = moduleTemNeg;
    }

    public String getSoftwareVersion() {
        return softwareVersion;
    }

    public void setSoftwareVersion(String softwareVersion) {
        this.softwareVersion = softwareVersion;
    }

    public String getPulseOutputVoltage() {
        return pulseOutputVoltage;
    }

    public void setPulseOutputVoltage(String pulseOutputVoltage) {
        this.pulseOutputVoltage = pulseOutputVoltage;
    }

    public String getBatteryInternalResstance() {
        return batteryInternalResstance;
    }

    public void setBatteryInternalResstance(String batteryInternalResstance) {
        this.batteryInternalResstance = batteryInternalResstance;
    }

    public String getCuringCurrent() {
        return curingCurrent;
    }

    public void setCuringCurrent(String curingCurrent) {
        this.curingCurrent = curingCurrent;
    }

    public String getSoc() {
        return soc;
    }

    public void setSoc(String soc) {
        this.soc = soc;
    }

    public String getSoh() {
        return soh;
    }

    public void setSoh(String soh) {
        this.soh = soh;
    }

    public String getBatteryVoltageCoefficient() {
        return batteryVoltageCoefficient;
    }

    public void setBatteryVoltageCoefficient(String batteryVoltageCoefficient) {
        this.batteryVoltageCoefficient = batteryVoltageCoefficient;
    }

    public String getModuleTemCoefficientPos() {
        return moduleTemCoefficientPos;
    }

    public void setModuleTemCoefficientPos(String moduleTemCoefficientPos) {
        this.moduleTemCoefficientPos = moduleTemCoefficientPos;
    }

    public String getModuleTemCoefficientNeg() {
        return moduleTemCoefficientNeg;
    }

    public void setModuleTemCoefficientNeg(String moduleTemCoefficientNeg) {
        this.moduleTemCoefficientNeg = moduleTemCoefficientNeg;
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

    public String getYuliu5() {
        return yuliu5;
    }

    public void setYuliu5(String yuliu5) {
        this.yuliu5 = yuliu5;
    }

    @Override
    public String toString() {
        return "Monitor{" +
                "yuliu1='" + yuliu1 + '\'' +
                ", yuliu2='" + yuliu2 + '\'' +
                ", number='" + number + '\'' +
                ", moduleNum='" + moduleNum + '\'' +
                ", moduleSta='" + moduleSta + '\'' +
                ", moduleVol='" + moduleVol + '\'' +
                ", moduleTemPos='" + moduleTemPos + '\'' +
                ", moduleTemNeg='" + moduleTemNeg + '\'' +
                ", softwareVersion='" + softwareVersion + '\'' +
                ", pulseOutputVoltage='" + pulseOutputVoltage + '\'' +
                ", batteryInternalResstance='" + batteryInternalResstance + '\'' +
                ", curingCurrent='" + curingCurrent + '\'' +
                ", soc='" + soc + '\'' +
                ", soh='" + soh + '\'' +
                ", batteryVoltageCoefficient='" + batteryVoltageCoefficient + '\'' +
                ", moduleTemCoefficientPos='" + moduleTemCoefficientPos + '\'' +
                ", moduleTemCoefficientNeg='" + moduleTemCoefficientNeg + '\'' +
                ", yuliu3='" + yuliu3 + '\'' +
                ", yuliu4='" + yuliu4 + '\'' +
                ", yuliu5='" + yuliu5 + '\'' +
                '}';
    }

    /**
         * 将电池上传的信息转化为实体
         *
         * @param msg
         * @return
         */
        public static Monitor getMonitorInfo(byte[] msg) {
            if (msg == null || msg.length == 0) {
                return null;
            }

            Monitor info = new Monitor();

            String pileCode = SHUtils.getPileNum(msg);
            info.setNumber(pileCode);

            // 模块数
            byte[] moduleNum = new byte[2];
            //src表示源数组，srcPos表示源数组要复制的起始位置，desc表示目标数组，length表示要复制的长度。
            System.arraycopy(msg, 42, moduleNum, 0, moduleNum.length);
            int mod = BytesUtil.toInt2(moduleNum); //低位在前 2位的byte数组 转 int
            info.setModuleNum(String.valueOf(mod));


            // 模块状态
            byte[] moduleSta = new byte[2];
            System.arraycopy(msg, 44, moduleSta, 0, moduleSta.length);
            int hv = BytesUtil.toInt2(moduleSta); //低位在前 2位的byte数组 转 int
            info.setModuleSta(String.valueOf(hv));



            //模块 电池电压
            byte[] moduleVol = new byte[2];
            System.arraycopy(msg, 46, moduleVol, 0, moduleVol.length);
            double v = BytesUtil.toInt2(moduleVol) * 0.01; //低位在前 2位的byte数组 转 int
            info.setModuleVol(String.valueOf(v));


            // 模块 温度1 正极
            byte[] moduleTemPos = new byte[2];
            System.arraycopy(msg, 48, moduleTemPos, 0, moduleTemPos.length);
            double modultempos = BytesUtil.toInt2(moduleTemPos) * 0.01; //低位在前 2位的byte数组 转 int
            info.setModuleTemPos(String.valueOf(modultempos));


            // 模块 温度2 负极
            byte[] moduleTemNeg = new byte[2];
            System.arraycopy(msg, 50, moduleTemNeg, 0, moduleTemNeg.length);
            double moduletemneg = BytesUtil.toInt2(moduleTemNeg) * 0.01; //低位在前 2位的byte数组 转 int
            info.setModuleTemNeg(String.valueOf(moduletemneg));


            //软件版本
            byte[] softwareVersion = new byte[2];
            System.arraycopy(msg, 52, softwareVersion, 0, softwareVersion.length);
            double softwareversion = BytesUtil.toInt2(softwareVersion) * 0.01; //低位在前 2位的byte数组 转 int
            info.setSoftwareVersion(String.valueOf(softwareversion));


            //脉冲输出电压
            byte[] pulseOutputVoltage = new byte[2];
            System.arraycopy(msg, 54, pulseOutputVoltage, 0, pulseOutputVoltage.length);
            double pulseoutputvoltage = BytesUtil.toInt2(pulseOutputVoltage) * 0.01; //低位在前 2位的byte数组 转 int
            info.setPulseOutputVoltage(String.valueOf(pulseoutputvoltage));

            //电池内阻
            byte[] batteryInternalResstance = new byte[2];
            System.arraycopy(msg, 56, batteryInternalResstance, 0, batteryInternalResstance.length);
            double batteryinternalres = BytesUtil.toInt2(batteryInternalResstance) * 0.01; //低位在前 2位的byte数组 转 int
            info.setBatteryInternalResstance(String.valueOf(batteryinternalres));

            //养护电流
            byte[] curingCurrent = new byte[2];
            System.arraycopy(msg, 58, curingCurrent, 0, curingCurrent.length);
            double curingcurrent = BytesUtil.toInt2(batteryInternalResstance) * 0.01; //低位在前 2位的byte数组 转 int
            info.setCuringCurrent(String.valueOf(curingcurrent));

            //SOC
            byte[] soc = new byte[2];
            System.arraycopy(msg, 60, curingCurrent, 0, soc.length);
            double so = BytesUtil.toInt2(soc) * 0.01; //低位在前 2位的byte数组 转 int
            info.setSoc(String.valueOf(so));

            //SOH
            byte[] soh = new byte[2];
            System.arraycopy(msg, 62, curingCurrent, 0, soh.length);
            double sh = BytesUtil.toInt2(soh) * 0.01; //低位在前 2位的byte数组 转 int
            info.setSoh(String.valueOf(v));

            //模块 电池电压系数
            byte[] batteryVoltageCoefficient = new byte[2];
            System.arraycopy(msg, 64, batteryVoltageCoefficient, 0, batteryVoltageCoefficient.length);
            double batteryvoltagecoeff = BytesUtil.toInt2(batteryVoltageCoefficient) * 0.01; //低位在前 2位的byte数组 转 int
            info.setBatteryVoltageCoefficient(String.valueOf(batteryvoltagecoeff));

            // 模块 温度1 正极 系数
            byte[] moduleTemCoefficientPos = new byte[2];
            System.arraycopy(msg, 66, moduleTemCoefficientPos, 0, moduleTemCoefficientPos.length);
            double moduletemcoeff = BytesUtil.toInt2(moduleTemCoefficientPos) * 0.01; //低位在前 2位的byte数组 转 int
            info.setModuleTemCoefficientPos(String.valueOf(moduletemcoeff));

            // 模块 温度2 负极 系数
            byte[] moduleTemCoefficientNeg = new byte[2];
            System.arraycopy(msg, 68, moduleTemCoefficientNeg, 0, moduleTemCoefficientNeg.length);
            double moduletemcoeffneg = BytesUtil.toInt2(moduleTemCoefficientPos) * 0.01; //低位在前 2位的byte数组 转 int
            info.setModuleTemCoefficientNeg(String.valueOf(moduletemcoeffneg));
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
