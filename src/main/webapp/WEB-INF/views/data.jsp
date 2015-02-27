<%--
  Created by IntelliJ IDEA.
  User: cedric
  Date: 2/27/15
  Time: 12:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title></title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script type="application/javascript">
        function follow(blogid)
        {
            $.ajax({
                url: "../followers/follow/"+blogid
            })
            .then(function(data)
            {

            });
        }
        function unfollow(blogid)
        {
            $.ajax({
                url: "../followers/unfollow/"+blogid
            })
                    .then(function(data)
                    {

                    });
        }
        function followAll()
        {
            $(".follow-button").each(
                    function( index )
                    {
                        console.log( index + ": " + $( this ).text() );
                        $(this).click();
                    }
            );
        }
    </script>
</head>
<body>
${user} <button onclick="followAll()">Follow All</button>  <br />

<table style="width:66%">
    <c:forEach items="${followers}" var="item">
        <tr>
            <td>${item.name}</td>
            <td><a href="http://${item.name}.tumblr.com" target="_blank"> Visit Site </a> </td>
            <c:choose>
                <c:when test="${empty item.following}">
                    <td>NO</td>
                </c:when>
                <c:otherwise>
                    <td>
                        <c:if test="${!item.following}">
                            <button class="follow-button" onclick="follow('${item.name}')">Follow</button>
                        </c:if>
                        <c:if test="${item.following}">

                        </c:if>
                    </td>
                </c:otherwise>
            </c:choose>

        </tr>
    </c:forEach>
</table>

</body>
</html>
