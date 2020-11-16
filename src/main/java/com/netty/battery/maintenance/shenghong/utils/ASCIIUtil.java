package com.netty.battery.maintenance.shenghong.utils;

import org.hibernate.validator.constraints.br.TituloEleitoral;
import org.junit.Test;

public class ASCIIUtil {


    /**
     *  32 位 转成原来的 string
     * @param hex
     * @return
     */
    public static String convertHexToString(String hex) {

        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder();

        // 49204c6f7665204a617661 split into two characters 49, 20, 4c...
        for (int i = 0; i < hex.length() - 1; i += 2) {

            // grab the hex in pairs
            String output = hex.substring(i, (i + 2));
            // convert hex to decimal
            int decimal = Integer.parseInt(output, 16);
            // convert the decimal to character
            sb.append((char) decimal);

            temp.append(decimal);
        }

        return sb.toString();
    }

    /**
     * Convert ASCII byte[] to int string<br>
     * 解析返回的卡号
     * 
     * @param m byte[]
     * @param q 起始位置
     * @param j 停止位置(不包括j)
     * @return
     */
    public static String ASCII2Int(byte[] m, int q, int j) {
       
        String string = BytesUtil.bytesToHexString(m, q, j-q);
        
        string = convertHexToString(string);
        
        return string.trim();
    }

    /**
     * Convert ASCII string to hex string
     * 10进制的转换成ascii 16进制 （补齐32位，不足补’\0’ ）
     *  比如 112233 转成 ascii 16进制为 313132323333 然后再补位 3131323233330000000000000000000000000000000000000000000000000000
     * @param ascii
     * @return
     */
    public static String ASCII2HexString(String ascii) {
        StringBuilder builder = new StringBuilder("");
        if (ascii == null || ascii.isEmpty()) {
            return null;
        }
        char[] res = ascii.toCharArray();
        for (int i = 0; i < res.length; i++) {
            int s = res[i]; // to ascii value(10进制)
            builder.append(Integer.toHexString(s));
        }
        // 补齐32位，不足补’\0’
        while (builder.length() < 64) {
            builder.append("0");
        }

        return builder.toString();
    }

    /**
     * 10进制转换为相应的ascii码16进制形式
     *  比如 112233  转成 313132323333
     * @param ascii
     * @return
     */
    public static String int2AsciiHex(String ascii) {
        StringBuilder builder = new StringBuilder("");
        if (ascii == null || ascii.isEmpty()) {
            return null;
        }
        char[] res = ascii.toCharArray();
        for (int i = 0; i < res.length; i++) {
            int s = res[i]; // to ascii value(10进制)
            builder.append(Integer.toHexString(s));
        }

        return builder.toString();
    }



    @Test
    public void demo123(){
        String a = int2AsciiHex("112233");
        System.out.println(a);

 String b = ASCII2HexString("112233");
        System.out.println(b);


       String c = convertHexToString(b);

        System.out.println(c);

    }

}
