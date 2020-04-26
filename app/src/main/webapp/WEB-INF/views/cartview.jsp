<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@include file="../../includes/header.jsp"%>

<a href='./cart.html'>Cart: </a>
<div>
	<p id="cart">${sessionScope.cartSize}</p>
</div>

<c:forEach var="product" items="${productList}">
	<table border='' id='product${product.key.id}'>
		<tr>
			<td>Id</td>
			<td>${product.key.id}</td>
			<td id="tooMuchToDelete${product.key.id}"></td>
		</tr>
		<tr>
			<td>Name</td>
			<td>${product.key.name}</td>
		</tr>
		<tr>
			<td>Description</td>
			<td>${product.key.description}</td>
		</tr>
		<tr>
			<td>Price</td>
			<td id='productPrice'>${product.key.price}</td>
		</tr>
		<tr>
			<td>Picture</td>
			<td><img src='./images/${product.key.image}' width='100'
				height='100' /></td>
		</tr>
		<tr>
			<td>
				<div id="productsleft${product.key.id}">Total Quantity :
					${product.value}</div> <br>
				<div id="totalPrice${product.key.id}">Total Price :
					${product.value*product.key.price}</div>
			</td>
			<td><img src='./images/minus.png'
				onclick="minus(${product.key.id})" width='20' height='20' /> <input
				type="number" min="0" max="${product.value}" required
				id='qnt${product.key.id}' size='2' value='1' /> <img
				src='./images/plus.png'
				onclick="plus(${product.key.id},${product.value})" width='20'
				height='20' /> <input type='button'
				onclick="remove(${product.key.id}, ${product.value} )"
				value='remove' /> <input type='button'
				onclick="removeAll(${product.key.id}, ${product.value})"
				value='remove all' /></td>
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
    
    function plus(id, maxQnt) {
        var qnt = document.getElementById("qnt" + id);
        if (qnt.value < maxQnt){
        qnt.value = +qnt.value + 1;
        }
    }

    function remove(id, qntLeft) {
        var qnt = document.getElementById("qnt" + id);
        if (qnt.value < qntLeft){
	        $.ajax({
	            url: "./cartdelete.html",
	            type: "GET",
	            dataType: "json",
	            data: {deleteId: id, deleteQnt: qnt.value},
	            success:
	                function (data) {
	            	qntLeft = data.productqntleft;
	            	console.log("new Qnt left = "+qntLeft);	            	
	                    document.getElementById("productsleft" + id).innerHTML = "Total Quantity : " + data.productqntleft;
	                    document.getElementById("cart").innerHTML = data.cartSize;
	                    document.getElementById("totalPrice"+id).innerHTML = "Total Price : " + data.totalPrice;
	                    document.getElementById("footerCart").innerHTML = data.cartSize;
	                    
	                    if (qntLeft==0 ){
	                    	document.getElementById("product" + id).innerHTML = "";
	           			 }
	                },
	            error: function () {
	                console.log("Some Error");
	            }
	        });
   		 } else {
   			 document.getElementById("tooMuchToDelete"+id).innerHTML = "Unable to delete more than you have in your cart";
   		 }
    }
        
        function removeAll(id, totalQNT) {
            //var qnt = document.getElementById("qnt" + id);
    	        $.ajax({
    	            url: "./cartdelete.html",
    	            type: "POST",
    	            dataType: "json",
    	            data: {deleteId: id, deleteQnt: totalQNT},
    	            success:
    	                function (data) {
    	                    document.getElementById("product" + id).innerHTML = "";
    	                    document.getElementById("cart").innerHTML = data.cartSize;
    	                    document.getElementById("footerCart").innerHTML = data.cartSize;
    	                },
    	            error: function () {
    	                console.log("Some Error");
    	            }
    	        });
    }
    
</script>

<%@include file="../../includes/footer.jsp"%>