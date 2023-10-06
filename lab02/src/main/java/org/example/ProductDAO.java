package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class ProductDAO implements Repository <Product, Integer> {
    Connection connection = null;

    public ProductDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Integer add(Product item) throws SQLException {
        try{
            String sql = "insert into product(name, price, color) values(?, ?, ?)";

            PreparedStatement ptm = connection.prepareStatement(sql);

            ptm.setString(1, item.getName());
            ptm.setDouble(2, item.getPrice());
            ptm.setString(3, item.getColor());
            ptm.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public List<Product> readAll() throws SQLException {
        List<Product> listProduct = new ArrayList<Product>();
        try {
            String sql = "Select * from product";
            PreparedStatement ptm = connection.prepareStatement(sql);
            ResultSet rs = ptm.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                String color = rs.getString("color");
                Product sp = new Product(id, name, price, color);
                listProduct.add(sp);
            }
        } catch (Exception e){
            throw new SQLException();
        }

        return listProduct;
    }
    @Override
    public Product read(Integer id) {
        Product pr = null;
        try {
            String sql = "Select * from product where id = ?";
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setInt(1, id);
            ResultSet rs = ptm.executeQuery();
            if(rs.next()){
                pr = new Product(id, rs.getString("name"), rs.getDouble("price"), rs.getString("color"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return pr;
    }

    @Override
    public boolean update(Product item) {
        try {
            String sql = "Update product set name = ?, price = ?, color = ? where id = ?";
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setString(1, item.getName());
            ptm.setDouble(2, item.getPrice());
            ptm.setString(3, item.getColor());
            ptm.setInt(4, item.getId());
            ptm.executeUpdate();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        try {
            String sql = "Delete from product where id = ?";
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setInt(1, id);
            ptm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
