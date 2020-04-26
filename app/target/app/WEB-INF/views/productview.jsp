<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<%@include file="../../includes/header.jsp" %>

<a href='./cart.html'>Cart: </a>
<div>
    <p id="cart">${sessionScope.cartSize}</p>
</div>
<hr>
<a href='./products.html?category=cognac'>Cognacs</a>
/
<a href='./products.html?category=cigars'>Cigars</a>
/
<a href='./products.html?category=whisky'>Whisky</a>

<hr>

<c:forEach var="product" items="${productList}">
    <table border='1'>
        <tr>
            <td>Id</td>
            <td>${product.id}</td>
        </tr>
        <tr>
            <td>Name</td>
            <td>${product.name}</td>
        </tr>
        <tr>
            <td>Description</td>
            <td>${product.description}</td>
        </tr>
        <tr>
            <td>Price</td>
            <td>${product.price}</td>
        </tr>
        <tr>
            <td>Picture</td>
            <td><img src='./images/${product.image}' width='100'
                     height='100'/></td>
        </tr>
        <tr>
            <td>
                <img src='./images/minus.png' onclick="minus(${product.id})" width='20' height='20'/>
                <input type='text' id='qnt${product.id}' size='2' value='1'/>
                <img src='./images/plus.png' onclick="plus(${product.id})" width='20' height='20'/>
            </td>
            <td>
                <input type='button' onclick="buy(${product.id})" value='Buy'/>
            </td>
        </tr>
    </table>
    <hr>
</c:forEach>

<script>
    function minus(id) {
        var qnt = document.getElementById("qnt" + id);
        if (qnt.value >= +2) {
            qnt.value -= 1;
        }
    }

    function plus(id) {
        var qnt = document.getElementById("qnt" + id);
        qnt.value = +qnt.value + 1;
    }

    function buy(id) {
        var qnt = document.getElementById("qnt" + id);
        var cart = document.getElementById("cart");
        var footerDivCart = document.getElementById("footerCart");

        $.ajax({
            url: "./cart.html",
            type: "POST",
            dataType: "json",
            data: {id: id, qnt: qnt.value},
            success:
                function (data) {
                    document.getElementById("cart").innerHTML = data.cartSize;
                    if (footerDivCart != null) {
                        document.getElementById("footerCart").innerHTML = data.cartSize;
                    } else {
                        document.getElementById("emptyCart").innerHTML = "В вашей корзине товаров: "+data.cartSize;
                    }
                },
            error: function () {
                console.log("Error");
            }
        });
    }
</script>

<%@include file="../../includes/footer.jsp" %>