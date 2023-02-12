package repository;

import config.MysqlConfig;
import model.RoleColumn;
import model.RoleModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoleRepository {
    public List<RoleModel> getAllRole(){
        List<RoleModel> listRoles = new ArrayList<>();
        Connection connection = MysqlConfig.getConnection();
        String query = "SELECT * FROM roles";

        try {
            ResultSet resultSet = connection.prepareStatement(query).executeQuery();
            while (resultSet.next()){
                RoleModel roleModel = new RoleModel();
                roleModel.setId(resultSet.getInt(RoleColumn.ID.getValue()));
                roleModel.setRoleName(resultSet.getString(RoleColumn.NAME.getValue()));
                roleModel.setDescription(resultSet.getString(RoleColumn.DESCRIPTION.getValue()));

                listRoles.add(roleModel);
            }
        }catch (Exception e){
            System.out.println("Loi cau query getAllRoles");
        }

        return listRoles;
    }

    public int deleteRoleById(int id){
        int isDeleteSuccess = 0;
        Connection connection = MysqlConfig.getConnection();
        String query = "DELETE FROM roles r WHERE r.id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,id);

            isDeleteSuccess = statement.executeUpdate();
        }catch (Exception e){
            System.out.println("Loi detele role by id" + e.getMessage());
        }finally {
            try {
                connection.close();
            }catch (Exception e){

            }
        }
        return isDeleteSuccess;
    }

    public int addRole(String name,String description){
        int isAddSuccess = 0;
        Connection connection = MysqlConfig.getConnection();
        String query = "INSERT INTO roles(name,description) VALUE(?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,RoleColumn.NAME.getValue());
            statement.setString(2,RoleColumn.DESCRIPTION.getValue());

            isAddSuccess = statement.executeUpdate();
        }catch (Exception e){
            System.out.println("Loi add role" + e.getMessage());
        }finally {
            try {
                connection.close();
            }catch (Exception e){

            }
        }
        return isAddSuccess;
    }
}
