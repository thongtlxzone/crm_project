package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;

            //urlPatterns: Nhung link khi nguoi dung goi se tinh filter
@WebFilter(urlPatterns = {"/roles"})
public class CustomFilter implements Filter {
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
//    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        System.out.println("Day la Filter");
//
//        //cho phep truy cap vao servlet duoc chi dinh o urlPatterns
//        filterChain.doFilter(servletRequest,servletResponse);

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String loginURI = request.getContextPath() + "/login";
        HttpSession session = request.getSession(false);
        boolean loggedIn = session != null && session.getAttribute("user") != null;
        boolean loginRequest = request.getRequestURI().equals(loginURI);

        if(loggedIn || loginRequest){
            filterChain.doFilter(request,response);
        }else {
            response.sendRedirect(loginURI);
        }


//        -----Filter cookies-----
//        Cookie[] cookies = request.getCookies();
//        if(cookies != null && cookies.length > 0){
//            boolean loggedIn = false;
//            for (Cookie cookie : cookies) {
//                if("username".equals(cookie.getName())){
//                    loggedIn=true;
//                    break;
//                }else{
//                    loggedIn=false;
//                }
//            }
//            if(loggedIn){
//                filterChain.doFilter(request,response);
//            }else{
//                response.sendRedirect(loginURI);
//            }
//        }else {
//            response.sendRedirect(loginURI);
//        }
    }
}
