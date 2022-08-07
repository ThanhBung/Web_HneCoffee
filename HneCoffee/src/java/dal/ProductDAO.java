package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Category;
import model.Customer;
import model.OrderDetail;
import model.Product;

public class ProductDAO extends DBContext {
    public void update(Product p){
        //update Products set catId=?, proName=? , image=?, status=?, quantity=?, price=? where proId=?
        String sql="update Products set catId=?, proName=? , image=?, status=?, quantity=?, price=? where proId=?";
        try{
            PreparedStatement st=connection.prepareStatement(sql);
            st.setInt(1, p.getCatid());
            st.setString(2, p.getProname());
            st.setString(3, p.getImage());
            st.setString(4, p.getProstatus());
            st.setInt(5, p.getQuantity());
            st.setInt(6, p.getPrice());
            st.setInt(7, p.getProid());
            st.executeUpdate();
        }catch(SQLException e){
            System.out.println(e);
        }
    }    
    
    //delete from Products where proId=4
    public void delete(int id){
        String sql="delete from Products where proId=?";
        try {
            PreparedStatement st=connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();//1 or 0
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void insert(Product p){
        String sql="insert into Products values(?,?,?,?,?,?,?)";
        try{
             PreparedStatement st=connection.prepareStatement(sql);
             st.setInt(1, p.getProid());
             st.setInt(2, p.getCatid());
             st.setString(3, p.getProname());
             st.setString(4, p.getImage());
             st.setString(5, p.getProstatus());
             st.setInt(6, p.getQuantity());
             st.setInt(7, p.getPrice());
             st.executeUpdate();//1 or 0
        }catch(SQLException e){
            System.out.println(e);
        }
    }

    public List<Product> getByCatID(int id) {
        List<Product> list = new ArrayList<>();
        String sql = "select p.proId, p.proName, p.image, p.status, p.quantity, p.price, "
                + " p.catId cid, c.catName cname, c.description cdescription from Products p "
                + " inner join Category c on p.catId = c.catId where c.catId=" + id;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProid(rs.getInt("proId"));
                p.setProname(rs.getString("proName"));
                p.setImage(rs.getString("image"));
                p.setProstatus(rs.getString("status"));
                p.setQuantity(rs.getInt("quantity"));
                p.setPrice(rs.getInt("price"));
                Category c = new Category(rs.getInt("cid"), rs.getString("cname"), rs.getString("cdescription"));
                p.setCate(c);
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Product> searchByName(String name) {
        List<Product> list = new ArrayList<>();
        //select 
        //p.proId, p.proName, p.image, p.status, p.quantity, p.price, 
        //p.catId cid, c.catName cname, c.description cdescription 
        //from Products p inner join Category c on p.catId = c.catId
        //where proName like '%tea%'
        String sql = " select \n"
                + " p.proId, p.proName, p.image, p.status, p.quantity, p.price, \n"
                + " p.catId cid, c.catName cname, c.description cdescription \n"
                + " from Products p inner join Category c on p.catId = c.catId\n"
                + " where proName like '%" + name + "%'";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProid(rs.getInt("proId"));
                p.setProname(rs.getString("proName"));
                p.setImage(rs.getString("image"));
                p.setProstatus(rs.getString("status"));
                p.setQuantity(rs.getInt("quantity"));
                p.setPrice(rs.getInt("price"));
                Category c = new Category(rs.getInt("cid"), rs.getString("cname"), rs.getString("cdescription"));
                p.setCate(c);
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Product> searchByPrice(int price) {
        List<Product> list = new ArrayList<>();
        //Select p.proId, p.proName, p.image, p.status, p.quantity, p.price, 
        //p.catId cid, c.catName cname, c.description cdescription 
        //from Products p inner join Category c on p.catId = c.catId
        //where price between (37-5) and (37+5)
        String sql = "select \n"
                + "p.proId, p.proName, p.image, p.status, p.quantity, p.price, \n"
                + " p.catId cid, c.catName cname, c.description cdescription \n"
                + " from Products p inner join Category c on p.catId = c.catId \n"
                + " where price between (" + price + "-5) and (" + price + "+5)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProid(rs.getInt("proId"));
                p.setProname(rs.getString("proName"));
                p.setImage(rs.getString("image"));
                p.setProstatus(rs.getString("status"));
                p.setQuantity(rs.getInt("quantity"));
                p.setPrice(rs.getInt("price"));
                Category c = new Category(rs.getInt("cid"), rs.getString("cname"), rs.getString("cdescription"));
                p.setCate(c);
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Product> getAll() {
        List<Product> list = new ArrayList<>();
        String sql = "select p.proId, p.proName, p.image, p.status, p.quantity, p.price, "
                + " p.catId cid, c.catName cname, c.description cdescription from Products p "
                + " inner join Category c on p.catId = c.catId";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProid(rs.getInt("proId"));
                p.setProname(rs.getString("proName"));
                p.setImage(rs.getString("image"));
                p.setProstatus(rs.getString("status"));
                p.setQuantity(rs.getInt("quantity"));
                p.setPrice(rs.getInt("price"));
                Category c = new Category(rs.getInt("cid"), rs.getString("cname"), rs.getString("cdescription"));
                p.setCate(c);
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Product> checkProductAndOrderBy(int[] id, String order) {
        List<Product> list = new ArrayList<>();
        String sql = "select p.proId, p.proName, p.image, p.status, p.quantity, p.price, "
                + " p.catId cid, c.catName cname, c.description cdescription from Products p "
                + " inner join Category c on p.catId = c.catId where 1=1 ";
        if (id != null) {
            sql += " and c.catId in (";
            for (int i = 0; i < id.length; i++) {
                sql += id[i] + ",";
            }
            if (sql.endsWith(",")) {
                sql = sql.substring(0, sql.length() - 1);
                sql += ")";
            }
        }
        if(order==null){
            sql += "";
        } else if(order.equals("ascprice")){
            sql += " order by price asc";
        } else if(order.equals("descprice")){
            sql += " order by price desc";
        }else if(order.equals("ascname")){
            sql += " order by proName asc";
        }else if(order.equals("descname")){
            sql += " order by proName desc";
        }
        
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProid(rs.getInt("proId"));
                p.setProname(rs.getString("proName"));
                p.setImage(rs.getString("image"));
                p.setProstatus(rs.getString("status"));
                p.setQuantity(rs.getInt("quantity"));
                p.setPrice(rs.getInt("price"));
                Category c = new Category(rs.getInt("cid"), rs.getString("cname"), rs.getString("cdescription"));
                p.setCate(c);
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }   
    
    public Product getProductById(int id) {
        //select * from Products where proId=3
        String sql = "select * from Products where proId=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Product p = new Product();
                p.setProid(rs.getInt("proId"));
                p.setCatid(rs.getInt("catId"));
                p.setProname(rs.getString("proName"));
                p.setImage(rs.getString("image"));
                p.setProstatus(rs.getString("status"));
                p.setQuantity(rs.getInt("quantity"));
                p.setPrice(rs.getInt("price"));
                return p;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public int lastId() {
        //select top 1 Customers.cusId from Customers order by cusId desc
        String sql = "select top 1 Customers.cusId from Customers order by cusId desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            int kq;
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Product p = new Product();
                kq = rs.getInt("cusID");
                return kq;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    public List<Product> getListProByPage(List<Product> list, int start, int end) {
        ArrayList<Product> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }
    public List<Customer> getListCusByPage(List<Customer> list, int start, int end) {
        ArrayList<Customer> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }

    public List<Product> selectTop4ProBycatId(int catid){
        List<Product> list = new ArrayList<>();
        String sql = "select top 4 * from Products where catId=? order by quantity desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, catid);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                Product p = new Product();
                p.setProid(rs.getInt("proId"));
                p.setCatid(rs.getInt("catId"));
                p.setProname(rs.getString("proName"));
                p.setImage(rs.getString("image"));
                p.setProstatus(rs.getString("status"));
                p.setQuantity(rs.getInt("quantity"));
                p.setPrice(rs.getInt("price")); 
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<OrderDetail> bestSelling(){
        List<OrderDetail> list = new ArrayList<>();
        String sql = "select top 4 od.proId, totalPrice , totalQuantity, "
                     + " proName , COUNT(od.proId) as bestselling\n" +
                    " from OrderDetails od inner join Products p on p.proId = od.proId\n" +
                    " group by od.proId, totalPrice , totalQuantity, proName \n" +
                    " order by count(od.proId) desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                OrderDetail od = new OrderDetail();
                od.setPid(rs.getInt("proId"));
                od.setPrice(rs.getInt("totalPrice"));
                od.setQuantity(rs.getInt("totalQuantity"));
                list.add(od);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Product> bestNew(){
        List<Product> list = new ArrayList<>();
        String sql = "select top 4 * from Products order by proId desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                Product p = new Product();
                p.setProid(rs.getInt("proId"));
                p.setCatid(rs.getInt("catId"));
                p.setProname(rs.getString("proName"));
                p.setImage(rs.getString("image"));
                p.setProstatus(rs.getString("status"));
                p.setQuantity(rs.getInt("quantity"));
                p.setPrice(rs.getInt("price"));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    public static void main(String[] args) {
        ProductDAO pdb = new ProductDAO();
        List<OrderDetail> list = pdb.bestSelling();
        System.out.println(list.get(3).getPid());
    }
}
