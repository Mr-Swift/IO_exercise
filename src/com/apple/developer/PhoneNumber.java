package com.apple.developer;

public class PhoneNumber {
    private String num;
    private double money;

    public PhoneNumber() {
    }

    public PhoneNumber(String num, double money) {
        this.num = num;
        this.money = money;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void addMoney(double add){
        this.setMoney(this.getMoney()+add);
    }

    @Override
    public String toString() {
        return "电话号码："+this.getNum()+"--->"+"消费金额："+this.getMoney();
    }
}
