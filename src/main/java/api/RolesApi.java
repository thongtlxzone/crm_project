package api;

import com.google.gson.Gson;
import model.RoleColumn;
import model.RoleModel;
import payload.BasicResponse;
import service.RoleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "RolesApi", urlPatterns = {"/api/roles","/api/roles/delete","/api/roles/add"})
public class RolesApi extends HttpServlet {
    //GET : Select. Dung khi lay du lieu
    //POST : Insert, Update, Delete...
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String url = req.getServletPath(); // Tra ra urlPattern ma user dang goi
//       BasicResponse basicResponse = new BasicResponse();
//
//        switch (url){
//            case "/api/roles":
//                basicResponse = getAllRole();
//                break;
//            case "/api/roles/delete":
//                int id = Integer.parseInt(req.getParameter(RoleColumn.ID.getValue()));
//                basicResponse = deleteRoleById(id);
//                break;
//            default:
//                basicResponse.setStatusCode(404);
//                basicResponse.setMessage("duong dan khong ton tai");
//                break;
//        }
//
//        Gson gson = new Gson();
//        String dataJson = gson.toJson(basicResponse); //Convert doi tuong hoac mang ve Json tuong ung
//
//        resp.setContentType("application/json");
//        resp.setCharacterEncoding("UTF-8");
//        PrintWriter printWriter = resp.getWriter();
//        printWriter.print(dataJson);
//        printWriter.flush();
//        printWriter.close();
    }

    private BasicResponse deleteRoleById(int id){
        BasicResponse response = new BasicResponse();
        RoleService roleService = new RoleService();
        response.setStatusCode(200);
        response.setMessage("xoa thanh cong");
        response.setData(roleService.deleteRoleById(id));
        return response;
    }

    private BasicResponse getAllRole(){
        BasicResponse response = new BasicResponse();
        RoleService roleService = new RoleService();
        List<RoleModel> list = roleService.getAllRoles();
        response.setStatusCode(200);
        response.setData(list);
        return response;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getServletPath(); // Tra ra urlPattern ma user dang goi
        BasicResponse basicResponse = new BasicResponse();

        switch (url){
            case "/api/roles/add":
                String name = req.getParameter("name");
                String description = req.getParameter("description");
                basicResponse = addNewRole(name,description);
                break;
            default:
                basicResponse.setStatusCode(404);
                basicResponse.setMessage("duong dan khong ton tai");
                break;
        }

        Gson gson = new Gson();
        String dataJson = gson.toJson(basicResponse); //Convert doi tuong hoac mang ve Json tuong ung

        resp.addHeader("Access-Control-Allow-Origin","*");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = resp.getWriter();
        printWriter.print(dataJson);
        printWriter.flush();
        printWriter.close();
    }

    private BasicResponse addNewRole(String name, String description) {
        BasicResponse response = new BasicResponse();
        RoleService roleService = new RoleService();
        response.setStatusCode(200);
        response.setMessage("them thanh cong");
        response.setData(roleService.addNewRole(name,description));
        return response;
    }
}
