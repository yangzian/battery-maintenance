package com.netty.battery.maintenance.shenghong.message.battery;

import com.netty.battery.maintenance.shenghong.SHUtils;
import com.netty.battery.maintenance.shenghong.utils.BytesUtil;

public class SystemInfo {


    private String yuliu1;

    private String yuliu2;

    // 终端编码
    private String number;

    // 系统状态
    private String systemSta;

    // 模块总数量
    private String moduleTotalNumber;

     // 在线模块数量
    private String onlineModuleNumber;

     // 软件版本
    private String softVersion;

    // 时间 YYMM
    private String timeym;

    // 时间 DDhh
    private String timedh;

    // 时间 mmss
    private String timems;

    //  电池组状态
    private String batteryState;

    //端电压
    private String terminaloltage;

    //电流
    private String current;

    //环境温度
    private String environmentTemp;

    //供电电压
    private String supplyVoltage;

    //输出电压
    private String outPutVoltage;

    //soc
    private String soc;

    //soh
    private String soh;


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

    public String getSystemSta() {
        return systemSta;
    }

    public void setSystemSta(String systemSta) {
        this.systemSta = systemSta;
    }

    public String getModuleTotalNumber() {
        return moduleTotalNumber;
    }

    public void setModuleTotalNumber(String moduleTotalNumber) {
        this.moduleTotalNumber = moduleTotalNumber;
    }

    public String getOnlineModuleNumber() {
        return onlineModuleNumber;
    }

    public void setOnlineModuleNumber(String onlineModuleNumber) {
        this.onlineModuleNumber = onlineModuleNumber;
    }

    public String getSoftVersion() {
        return softVersion;
    }

    public void setSoftVersion(String softVersion) {
        this.softVersion = softVersion;
    }

    public String getTimeym() {
        return timeym;
    }

    public void setTimeym(String timeym) {
        this.timeym = timeym;
    }

    public String getTimedh() {
        return timedh;
    }

    public void setTimedh(String timedh) {
        this.timedh = timedh;
    }

    public String getTimems() {
        return timems;
    }

    public void setTimems(String timems) {
        this.timems = timems;
    }

    public String getBatteryState() {
        return batteryState;
    }

    public void setBatteryState(String batteryState) {
        this.batteryState = batteryState;
    }

    public String getTerminaloltage() {
        return terminaloltage;
    }

    public void setTerminaloltage(String terminaloltage) {
        this.terminaloltage = terminaloltage;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getEnvironmentTemp() {
        return environmentTemp;
    }

    public void setEnvironmentTemp(String environmentTemp) {
        this.environmentTemp = environmentTemp;
    }

    public String getSupplyVoltage() {
        return supplyVoltage;
    }

    public void setSupplyVoltage(String supplyVoltage) {
        this.supplyVoltage = supplyVoltage;
    }

    public String getOutPutVoltage() {
        return outPutVoltage;
    }

    public void setOutPutVoltage(String outPutVoltage) {
        this.outPutVoltage = outPutVoltage;
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


    @Override
    public String toString() {
        return "SystemInfo{" +
                "yuliu1='" + yuliu1 + '\'' +
                ", yuliu2='" + yuliu2 + '\'' +
                ", number='" + number + '\'' +
                ", systemSta='" + systemSta + '\'' +
                ", moduleTotalNumber='" + moduleTotalNumber + '\'' +
                ", onlineModuleNumber='" + onlineModuleNumber + '\'' +
                ", softVersion='" + softVersion + '\'' +
                ", timeym='" + timeym + '\'' +
                ", timedh='" + timedh + '\'' +
                ", timems='" + timems + '\'' +
                ", batteryState='" + batteryState + '\'' +
                ", terminaloltage='" + terminaloltage + '\'' +
                ", current='" + current + '\'' +
                ", environmentTemp='" + environmentTemp + '\'' +
                ", supplyVoltage='" + supplyVoltage + '\'' +
                ", outPutVoltage='" + outPutVoltage + '\'' +
                ", soc='" + soc + '\'' +
                ", soh='" + soh + '\'' +
                '}';
    }

    /**
     * 将电池上传的信息转化为实体
     *
     * @param msg
     * @return
     */
    public static SystemInfo getSystemInfo(byte[] msg) {
        if (msg == null || msg.length == 0) {
            return null;
        }

        SystemInfo info = new SystemInfo();
        String pileCode = SHUtils.getPileNum(msg);
        info.setNumber(pileCode);

        // 系统状态
        byte[] systemSta = new byte[2];
        //src表示源数组，srcPos表示源数组要复制的起始位置，desc表示目标数组，length表示要复制的长度。
        System.arraycopy(msg, 42, systemSta, 0, systemSta.length);
        String syssta =BytesUtil.bytesToHexString(systemSta);
        int sta = Integer.parseInt(syssta,16);
        info.setSystemSta(String.valueOf(sta));

        // 模块总数量
        byte[] moduleTotalNumber = new byte[2];
        //src表示源数组，srcPos表示源数组要复制的起始位置，desc表示目标数组，length表示要复制的长度。
        System.arraycopy(msg, 44, moduleTotalNumber, 0, moduleTotalNumber.length);
        String moduletotal =BytesUtil.bytesToHexString(moduleTotalNumber);
        int moduletotalnum = Integer.parseInt(moduletotal,16);
        info.setModuleTotalNumber(String.valueOf(moduletotalnum));

        // 在线模块数量
        byte[] onlineModuleNumber = new byte[2];
        //src表示源数组，srcPos表示源数组要复制的起始位置，desc表示目标数组，length表示要复制的长度。
        System.arraycopy(msg, 46, onlineModuleNumber, 0, onlineModuleNumber.length);
        String onlineModul =BytesUtil.bytesToHexString(onlineModuleNumber);
        int onlineM = Integer.parseInt(onlineModul,16);
        info.setOnlineModuleNumber(String.valueOf(onlineM));

        // 软件版本
        byte[] softVersion = new byte[2];
        //src表示源数组，srcPos表示源数组要复制的起始位置，desc表示目标数组，length表示要复制的长度。
        System.arraycopy(msg, 48, softVersion, 0, softVersion.length);
        String softver =BytesUtil.bytesToHexString(softVersion);
        int softvers = Integer.parseInt(softver,16);
        info.setSoftVersion(String.valueOf(softvers));

        // 时间 YYMM
        byte[] timeym = new byte[2];
        //src表示源数组，srcPos表示源数组要复制的起始位置，desc表示目标数组，length表示要复制的长度。
        System.arraycopy(msg, 50, timeym, 0, timeym.length);
        String yymm =BytesUtil.bytesToHexString(timeym);
        int ym = Integer.parseInt(yymm,16);
        info.setTimeym(String.valueOf(ym));

        // 时间 DDhh
        byte[] timedh = new byte[2];
        //src表示源数组，srcPos表示源数组要复制的起始位置，desc表示目标数组，length表示要复制的长度。
        System.arraycopy(msg, 52, timedh, 0, timedh.length);
        String ddhh =BytesUtil.bytesToHexString(timedh);
        int dh = Integer.parseInt(ddhh,16);
        info.setTimedh(String.valueOf(dh));

        // 时间 mmss
        byte[] timems = new byte[2];
        //src表示源数组，srcPos表示源数组要复制的起始位置，desc表示目标数组，length表示要复制的长度。
        System.arraycopy(msg, 54, timems, 0, timems.length);
        String mmss =BytesUtil.bytesToHexString(timems);
        int ms = Integer.parseInt(mmss,16);
        info.setTimems(String.valueOf(ms));

        //  电池组状态
        byte[] batteryState = new byte[2];
        //src表示源数组，srcPos表示源数组要复制的起始位置，desc表示目标数组，length表示要复制的长度。
        System.arraycopy(msg, 56, batteryState, 0, batteryState.length);

        //String batterysta =BytesUtil.bytesToHexString(batteryState);
        //int battery = Integer.parseInt(batterysta,16);

        Integer battery=BytesUtil.toInt2(batteryState);
        info.setBatteryState(String.valueOf(battery));

        //端电压
        byte[] terminaloltage = new byte[2];
        //src表示源数组，srcPos表示源数组要复制的起始位置，desc表示目标数组，length表示要复制的长度。
        System.arraycopy(msg, 58, terminaloltage, 0, terminaloltage.length);
        double moduletemneg = BytesUtil.toInt2(terminaloltage) * 0.01; //低位在前 2位的byte数组 转 int
        info.setTerminaloltage(String.valueOf(moduletemneg));

        //电流
        byte[] current = new byte[2];
        //src表示源数组，srcPos表示源数组要复制的起始位置，desc表示目标数组，length表示要复制的长度。
        System.arraycopy(msg, 60, current, 0, current.length);
        double curr = BytesUtil.toInt2(current) * 0.01; //低位在前 2位的byte数组 转 int
        info.setCurrent(String.valueOf(curr));

        //环境温度
        byte[] environmentTemp = new byte[2];
        //src表示源数组，srcPos表示源数组要复制的起始位置，desc表示目标数组，length表示要复制的长度。
        System.arraycopy(msg, 62, environmentTemp, 0, environmentTemp.length);
        double environment = BytesUtil.toInt2(environmentTemp) * 0.01; //低位在前 2位的byte数组 转 int
        info.setEnvironmentTemp(String.valueOf(environment));

        //供电电压
        byte[] supplyVoltage = new byte[2];
        //src表示源数组，srcPos表示源数组要复制的起始位置，desc表示目标数组，length表示要复制的长度。
        System.arraycopy(msg, 64, supplyVoltage, 0, supplyVoltage.length);
        double supplyVol = BytesUtil.toInt2(supplyVoltage) * 0.01; //低位在前 2位的byte数组 转 int
        info.setSupplyVoltage(String.valueOf(supplyVol));

        //输出电压
        byte[] outPutVoltage = new byte[2];
        //src表示源数组，srcPos表示源数组要复制的起始位置，desc表示目标数组，length表示要复制的长度。
        System.arraycopy(msg, 66, outPutVoltage, 0, outPutVoltage.length);
        double outPutVol = BytesUtil.toInt2(outPutVoltage) * 0.01; //低位在前 2位的byte数组 转 int
        info.setOutPutVoltage(String.valueOf(outPutVol));

        //soc
        byte[] soc = new byte[2];
        //src表示源数组，srcPos表示源数组要复制的起始位置，desc表示目标数组，length表示要复制的长度。
        System.arraycopy(msg, 68, soc, 0, soc.length);
        double sc = BytesUtil.toInt2(soc) * 0.01; //低位在前 2位的byte数组 转 int
        info.setSoc(String.valueOf(sc));

        //soh
        byte[] soh = new byte[2];
        //src表示源数组，srcPos表示源数组要复制的起始位置，desc表示目标数组，length表示要复制的长度。
        System.arraycopy(msg, 70, soh, 0, soh.length);
        double sh = BytesUtil.toInt2(soh) * 0.01; //低位在前 2位的byte数组 转 int
        info.setSoh(String.valueOf(sh));

        return info;
    }








}
