package com.netty.battery.maintenance.netty.client;


public class demo {


    public static void main(String[] args) {
        System.out.println(123);


        System.out.println(111);

        System.out.println(222);
        System.out.println(333);

        NettyClient nettyClient = new NettyClient("1");
        nettyClient.run();

    }
}
