
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--thêm JSTL vào JSP để tiện sử dụng--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>List Student</h1>
<a href="/students?action=create">
    <button>Create new product</button>
</a>
<a href="/categories">
    <button>List category</button>
</a>
<table>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Point</th>
        <th>Class</th>
        <th colspan="2">Action</th>
    </tr>
    <%--    dùng c:forEach để render dữ liệu của list--%>
    <c:forEach var="p" items="${products}" varStatus="status">
        <tr>
            <td>${status.index + 1}</td>
            <td>${p.name}</td>
            <td>${p.point}</td>
<%--            <td>${p.class.className}</td>--%>
            <td><a href="/students?action=update&id=${p.id}">
                <button>Update</button>
            </a></td>
            <td><a href="/students?action=delete&id=${p.id}">
                <button>Delete</button>
            </a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>