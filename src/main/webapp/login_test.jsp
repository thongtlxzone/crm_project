<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<html>
    <head>

    </head>

    <body>

    <%--
        action: link muon goi den
        method: Phuong thuc muon goi la phuong thuc nao
        Thuoc tinh name: la ten tham so truyen toi link khai bao o thuoc tinh action cua the form
    --%>
        <form action= "http://localhost:8080/login" method = "post">
            <input type="texts" name = "taikhoan" placeholder = "Tai khoan" />
            <input type="texts" name = "matkhau" placeholder = "Mat khau" />
            <button>Dang nhap</button>
        </form>
    </body>
</html>