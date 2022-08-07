/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Cart;
import model.Customer;
import model.Item;
import model.Order;
import model.Product;
import model.ViewOrder;

/**
 *
 * @author Administrator
 */
public class OrderDAO extends DBContext {

    public List<Order> getAll() {
        List<Order> list = new ArrayList<>();
        String sql = "select * from Orders";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Order o = new Order();
                o.setCusid(rs.getInt("cusId"));
                o.setId(rs.getInt("ordId"));
                o.setDate(rs.getString("date"));
                o.setTotalmoney(rs.getInt("totalmoney"));
                list.add(o);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public void addOrder(Customer c, Cart cart) {
        LocalDate curDate = LocalDate.now();
        String date = curDate.toString();
        OrderDAO or = new OrderDAO();
        List<Order> list = or.getAll();
        try {
            //add order
            String sql = "insert into Orders(cusId,ordId,date,totalmoney) values(?,?,?,?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, c.getId());
            st.setInt(2, list.size() + 1);
            st.setString(3, date);
            st.setInt(4, cart.getTotalMoney());
            st.executeUpdate();
            //lay id cua order vua add
            String sql1 = "select top 1 ordId from Orders order by ordId desc";
            PreparedStatement st1 = connection.prepareStatement(sql1);
            ResultSet rs = st1.executeQuery();
            //add bang OrderDetail
            if (rs.next()) {
                int oid = rs.getInt("ordId");
                for (Item i : cart.getItems()) {
                    String sql2 = "insert into OrderDetails(ordId, proId, totalPrice, totalQuantity) "
                            + "values (?,?,?,?)";
                    PreparedStatement st2 = connection.prepareStatement(sql2);
                    st2.setInt(1, oid);
                    st2.setInt(2, i.getProduct().getProid());
                    st2.setInt(3, i.getPrice());
                    st2.setInt(4, i.getQuantity());
                    st2.executeUpdate();
                }
            }
            //cap nhat lai so luong san pham
            String sql3 = "update Products set quantity=quantity-? where proId=?";
            PreparedStatement st3 = connection.prepareStatement(sql3);
            for (Item i : cart.getItems()) {
                st3.setInt(1, i.getQuantity());
                st3.setInt(2, i.getProduct().getProid());
                st3.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public int getIncome() {
        String sql = "select SUM(totalPrice*totalQuantity) as TotalMoney from OrderDetails";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("TotalMoney");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    public int getTotalQuantity() {
        String sql = "select SUM(totalQuantity) as TotalQuantity from OrderDetails ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("TotalQuantity");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    //select count(ordId) as NumberOfPurchases from Orders
    public int getNumberOfPurchases() {
        String sql = "select count(ordId) as NumberOfPurchases from Orders";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("NumberOfPurchases");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }
    
    public List<Integer> getQuanPurchaseOrderByCatId() {
        List<Integer> list = new ArrayList<>();
        String sql = "select c.catId,c.catName, Sum(totalQuantity) as Quantity from OrderDetails od\n" +
                " inner join Products p on od.proId = p.proId\n" +
                " inner join Category c on c.catId = p.catId\n" +
                " group by c.catId,c.catName\n" +
                " order by c.catId";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
              list.add(rs.getInt("Quantity"));
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }    

    public List<Integer> getIncomeOrderByCatId() {
        List<Integer> list = new ArrayList<>();
        String sql = "select c.catId,c.catName, Sum(totalQuantity*totalPrice) as Income from OrderDetails od\n" +
                    " inner join Products p on od.proId = p.proId\n" +
                    " inner join Category c on c.catId = p.catId\n" +
                    " group by c.catId,c.catName\n" +
                    " order by c.catId";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
              list.add(rs.getInt("Income"));
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    } 
    
    public List<ViewOrder> getOrder(String cusName) {
        List<ViewOrder> list = new ArrayList<>();
        String sql = "select p.proName as name, p.image, p.status,od.totalQuantity as quantity, p.price, \n" +
            " p.price*od.totalQuantity as TheMoneyHasToPay, o.date, c.address\n" +
            " from Products p inner join OrderDetails od on p.proId = od.proId\n" +
            " inner join Orders o on od.ordId = o.ordId\n" +
            " inner join Customers c on c.cusId = o.cusId\n" +
            " where c.cusName = '"+cusName+"'";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ViewOrder vo = new ViewOrder(
                        new Product(rs.getString("name"), rs.getString("image"),
                                    rs.getString("status"), rs.getInt("quantity"),
                                    rs.getInt("price")), 
                        rs.getInt("TheMoneyHasToPay"), 
                        rs.getString("date"), 
                        rs.getString("address"));
                list.add(vo);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }    
    
    public static void main(String[] args) {
        OrderDAO o = new OrderDAO();
        System.out.println(o.getOrder("Danh Chieu").get(0).getProItem().getProname());
    }
}
