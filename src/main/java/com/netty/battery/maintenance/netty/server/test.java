package com.netty.battery.maintenance.netty.server;


import com.netty.battery.maintenance.shenghong.utils.BytesUtil;

public class test {


    public static void main(String[] args) {

        byte[] b =  BytesUtil.hexStringToBytes("aa f5 32 00 10 01" +
                "06 00" +
                "00 00 00 00" +
                "31 31 32 32 33 33 00 00 00 00" +
                "00 00 00 00 00 00 00 00 00 00" +
                "00 00 00 00 00 00 00 00 00 00" +
                "00 00" +
                "01"
        );

        byte[] moduleSta = new byte[1];
        System.arraycopy(b, 6, moduleSta, 0, moduleSta.length);

        String modN =BytesUtil.bytesToHexString(moduleSta);

        System.out.println(modN+"-------原数据----");

        //Integer mod10=BytesUtil.toInt2(moduleSta);
        int mod10 = Integer.parseInt(modN,16);
        System.out.println(mod10+"------转换成16进制-----");

       int a =  BytesUtil.toInt1(moduleSta,0);
        System.out.println(a+"-------数据----");

       // System.out.println(Integer.parseInt(String.valueOf(modN),16));

       // String modN =BytesUtil.toHexString(BytesUtil.bytesToHexString(moduleSta));



        //double mod10 = Integer.parseInt(modN,16) * 0.01;

        //System.out.println(mod10+"------aaa-----");


       // double hv = BytesUtil.toInt2(moduleSta) * 0.01; //低位在前 2位的byte数组 转 int

        //System.out.println(hv+"-------aaa----");
        //System.out.println(v+"-------原数据111----");

/*


        double a = mod10 * 0.001;
        System.out.println(a+"-----结果------");
*/

/*

        byte[] b =  BytesUtil.hexStringToBytes("10");

        byte[] moduleSta = new byte[1];
        System.arraycopy(b, 0, moduleSta, 0, moduleSta.length);
        String modN =BytesUtil.bytesToHexString(moduleSta);

        System.out.println(modN+"-------原数据----");

        Integer mod10=Integer.parseInt(modN,16);

        System.out.println(mod10+"------转换成16进制-----");

*/



       // String strHex1 = String.format("%04x", 101).toUpperCase();//不足两位高位补0
        //System.out.println(strHex1);

    }



}
