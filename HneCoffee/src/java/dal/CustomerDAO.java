package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Customer;

public class CustomerDAO extends DBContext {

    public Customer check(String phone, String pass) {
        //select * from Customers where phone='0123456789' and password='123456';
        String sql = "select * from Customers where phone=? and password=?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, phone);
            st.setString(2, pass);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Customer(rs.getInt("cusId"), rs.getString("cusName"), phone, pass, rs.getString("address"));
            }
        } catch (SQLException e) {

        }
        return null;
    }

    public List<Customer> getAllCus() {
        List<Customer> list = new ArrayList<>();
        String sql = "select * from Customers";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Customer c = new Customer();
                c.setId(rs.getInt("cusId"));
                c.setName(rs.getString("cusName"));
                c.setPhone(rs.getString("phone"));
                c.setPass(rs.getString("password"));
                c.setAddress(rs.getString("address"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    //insert into Customer
    public void insertCus(Customer c) {
        String sql = "insert into Customers values(?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, c.getId());
            st.setString(2, c.getName());
            st.setString(3, c.getPhone());
            st.setString(4, c.getPass());
            st.setString(5, c.getAddress());
            st.executeUpdate();//1 or 0
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public Customer getCategoryById(int id) {
        String sql = "select * from Customers where cusId=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Customer c = new Customer();
                c.setId(rs.getInt("cusId"));
                c.setName(rs.getString("cusName"));
                c.setPhone(rs.getString("phone"));
                c.setPass(rs.getString("password"));
                c.setAddress(rs.getString("address"));
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    //update Customers set cusName='Thai', phone='011223344', address='Hai Phong' where cusId=4
    public void update(Customer c) {
        String sql = "update Customers set cusName=?, phone=?, address=? where cusId=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, c.getName());
            st.setString(2, c.getPhone());
            st.setString(3, c.getAddress());
            st.setInt(4, c.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //update Customers set password='111111' where cusId=4
    public void updatePass(Customer c) {
        String sql = "update Customers set password=? where cusId=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, c.getPass());
            st.setInt(2, c.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
