<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
</body>
</div>
<div id="sidebar">
	<table border=1>
		<tr>
			<td><font color=white> Вы авторизировались как 
			
			<c:choose>
				<c:when test="${sessionScope.user!=null}">
                           ${sessionScope.user.email} <br />
					<form action='./login.html' method='post'>
						<input type='hidden' name='logout' /> <input type='submit' value='logout' />
					</form>
						</c:when>

				<c:otherwise>
                    Guest
        		</c:otherwise>
			</c:choose>
					
					 <br>
					 
					  <c:choose>
						<c:when test="${sessionScope.cartSize==null}">
							<div id="emptyCart">Ваша корзина пуста</div>
						</c:when>
						<c:otherwise>
							<div>
								В вашей корзине товаров:
								<p id=footerCart>${sessionScope.cartSize}</p>
							</div>
						</c:otherwise>
					</c:choose>

			</font></td>
		</tr>
	</table>
	<h2>Боковое меню</h2>
	<ul>
		<li><a href='./products.html?category=cognac'>Cognacs</a></li>
		<li><a href='./products.html?category=cigars'>Cigars</a></li>
		<li><a href='./products.html?category=whisky'>Whisky</a></li>
		<li><a href="./registration.html">Регистрация</a></li>
		<li><a href="./login.html">Вход</a></li>
		<li><a href="./cart.html">Корзина</a></li>
	</ul>
</div>
</div>
</div>
</div>
</div>
<div id="footer">
	<p>Copyright (c) by Бондаренко Антон</p>
</div>

</html>