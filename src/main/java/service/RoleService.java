package service;

import model.RoleModel;
import repository.RoleRepository;

import java.util.List;

public class RoleService {
    public List<RoleModel> getAllRoles(){
        RoleRepository roleRepository = new RoleRepository();
        return roleRepository.getAllRole();
    }
    public boolean deleteRoleById(int id){
        RoleRepository roleRepository = new RoleRepository();
        return roleRepository.deleteRoleById(id) >= 1;
    }
    public boolean addNewRole(String name, String description){
        RoleRepository roleRepository = new RoleRepository();
        return roleRepository.addNewRole(name,description)>=1;
    }
}
