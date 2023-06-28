package com.beans;

public class ConcreteReception implements Reception{

    @Override
    public void serveCustomer(String customerName) {
        System.out.println("我正在为客户服务："+customerName+"(接待中)");
    }
}
