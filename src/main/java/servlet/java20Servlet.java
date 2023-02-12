package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//Dinh nghia servlet
public class java20Servlet extends HttpServlet {
    //GET : client goi duong dan tren browser
    //POST : goi ngam bang code hoac form

    @Override               //Request tu ng dung        Response sever tra ra cho nguoi dung
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //phuong thuc GET se duoc thuc thi code o day
        System.out.println("Hello Phuong Thuc GET");
    }
}
