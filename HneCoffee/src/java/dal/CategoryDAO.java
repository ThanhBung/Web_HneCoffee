package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Category;

public class CategoryDAO extends DBContext{
    public List<Category> getAllCat(){
        List<Category> list = new ArrayList<>();
        String sql="select * from Category";
        try{
            PreparedStatement st=connection.prepareStatement(sql);
            ResultSet rs=st.executeQuery();
            while(rs.next()){
                Category c = new Category();
                c.setId(rs.getInt("catId"));
                c.setName(rs.getString("catName"));
                c.setDescription(rs.getString("description"));
                list.add(c);
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        return list;
    }
    
    public Category getProByCatId(int cid){
        //select * from Category where catId=?
        String sql="select * from Category where catId="+cid;
        try{
            PreparedStatement st=connection.prepareStatement(sql);
            ResultSet rs=st.executeQuery();
            if(rs.next()){
                Category c = new Category();
                c.setId(rs.getInt("catId"));
                c.setName(rs.getString("catName"));
                c.setDescription(rs.getString("description"));
                return c;
            }
        }catch(SQLException e){
            System.out.println(e);
        }        
        return null;        
    }

    public void insert(Category c){
        String sql="insert into Category values(?,?,?)";
        try{
             PreparedStatement st=connection.prepareStatement(sql);
             st.setInt(1, c.getId());
             st.setString(2, c.getName());
             st.setString(3, c.getDescription());
             st.executeUpdate();//1 or 0
        }catch(SQLException e){
            System.out.println(e);
        }
    }        
        
    public void delete(int cid){
        String sql="delete from Category where catId=?";
        try {
            PreparedStatement st=connection.prepareStatement(sql);
            st.setInt(1, cid);
            st.executeUpdate();//1 or 0
        } catch (SQLException e) {
            System.out.println(e);
        }
    }    
    
    public static void main(String[] args) {
        CategoryDAO c = new CategoryDAO();
        System.out.println(c.getProByCatId(1).getId());
    }
    
}
