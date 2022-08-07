/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Administrator
 */
public class ViewOrder {
    Product proItem;
    private int totalMoney;
    private String dateOrder, cusAddress;

    public ViewOrder() {
    }

    public ViewOrder(Product proItem, int totalMoney, String dateOrder, String cusAddress) {
        this.proItem = proItem;
        this.totalMoney = totalMoney;
        this.dateOrder = dateOrder;
        this.cusAddress = cusAddress;
    }

    public Product getProItem() {
        return proItem;
    }

    public void setProItem(Product proItem) {
        this.proItem = proItem;
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(String dateOrder) {
        this.dateOrder = dateOrder;
    }

    public String getCusAddress() {
        return cusAddress;
    }

    public void setCusAddress(String cusAddress) {
        this.cusAddress = cusAddress;
    }
    
    
}
