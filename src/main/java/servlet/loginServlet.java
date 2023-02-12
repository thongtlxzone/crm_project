package servlet;

import com.mysql.cj.xdevapi.PreparableStatement;
import config.MysqlConfig;
import model.User;
import service.LoginService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

//Anotation [Ten dinh danh]
//Ky hieu: @
            //ten cua servlet       //duong dan cua servlet
@WebServlet(name = "loginServlet", urlPatterns = {"/login"})
public class loginServlet extends HttpServlet {

    //excuteQuery : Select
    //excuteUpdate : Create, Update....
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // khoi tai cookie               ten cookie     gia tri cookie
//        Cookie cookie = new Cookie("username","nguyenvana");
//        cookie.setMaxAge(60*60*8); //set thoi gian het han cua cookie
//        resp.addCookie(cookie); //yeu cau trinh duyet phia client tao cookie

        //Lay toan bo cookie o browser nguoi dung
//        Cookie[] cookies = req.getCookies();
//        for(Cookie cookie : cookies){
//            String name = cookie.getName();
//            if(name.equals("username")){
//                System.out.println("Gia tri " + cookie.getValue());
//            }
//        }

        //Lay Session tuong ung tu tren server
//        HttpSession session = req.getSession();
//        session.setAttribute("username","TranVanA");
//        session.setMaxInactiveInterval(60*60*8); // set thoi gian het han cho session
//        String data = String.valueOf(session.getAttribute("username"));
//        System.out.println("gia tri session: " + data);

        Connection connection = MysqlConfig.getConnection();
        String query = "SELECT * FROM users";
        try {
            //chuan bi cau truy van cho prepare statement
            PreparedStatement statement = connection.prepareStatement(query);
            //ket qua cau truy vab sse luu vao resultSet
            ResultSet resultSet = statement.executeQuery();
            //Duyet gia tri trong resultSet va lay ra thong tin mong muon
            while (resultSet.next()){
                String email = resultSet.getString("email");
                int roleId = resultSet.getInt("role_id");

                System.out.println("Email la: " + email);
            }
        } catch (SQLException e) {
            System.out.println("Loi thuc thi cau truy van login SQL " + e.getMessage());
        }finally {
            if(connection!=null){
                try {
                    connection.close();
                }catch (SQLException e){
                    throw new RuntimeException(e);
                }
            }
        }

        // Yeu cau hien thi noi dung trong file login_test.jsp
        req.getRequestDispatcher("login.jsp").forward(req,resp);
    }

    // Phuong thuc POST: Tham so se duoc truyen ngam, khong gioi han ve mat ky tu
    //
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        LoginService loginService = new LoginService();
        boolean isSuccess = loginService.checkLogin(email,password);

        if(isSuccess){
//            -----Session-----
            req.getSession().setAttribute("user",email);
//            -----Cookie-----
//            Cookie cookie = new Cookie("username",email);
//            cookie.setMaxAge(60*60);
//            resp.addCookie(cookie);

            resp.sendRedirect(req.getContextPath() + "/roles");
        }else {
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }

        System.out.println("Kiem tra " + isSuccess);
    }
}
