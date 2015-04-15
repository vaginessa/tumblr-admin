<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Registration</title>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="application/javascript">
    function login()
    {
        var win = window.open("${url}", "windowname1", 'width=800, height=600'); // Remove this please

    }


</script>
</head>
<body>
<table style="width:66%">
    <c:forEach items="${users}" var="item">
        <tr>
            <td>${item.username}</td>
            <td><a href="http://${item.username}.tumblr.com" target="_blank"> Visit Site </a> </td>
            <td><a href="../login/admin/${item.id}"> Admin </a> </td>
        </tr>
    </c:forEach>

</table>

<button onclick="login()"><img src="../resources/tumblr_logo/tumblr_logo_white_blue_32.png"></button>
</body>
</html>

