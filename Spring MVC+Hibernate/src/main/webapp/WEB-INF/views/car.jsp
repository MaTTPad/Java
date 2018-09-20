<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
	<title>Car Page</title>
	<style type="text/css">
		.tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
		.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
		.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
		.tg .tg-4eph{background-color:#f9f9f9}
	</style>
</head>
<body>
<h1>
	Add a Car
</h1>

<c:url var="addAction" value="/car/add" ></c:url>

<form:form action="${addAction}" commandName="car">
<table>
	<c:if test="${!empty car.name}">
	<tr>
		<td>
			<form:label path="id">
				<spring:message text="ID"/>
			</form:label>
		</td>
		<td>
			<form:input path="id" readonly="true" size="8"  disabled="true" />
			<form:hidden path="id" />
		</td>
	</tr>
	</c:if>
	<tr>
		<td>
			<form:label path="model">
				<spring:message text="Model"/>
			</form:label>
		</td>
		<td>
			<form:input path="model" />
		</td>
	</tr>
	<tr>
		<td>
			<form:label path="brand">
				<spring:message text="Brand"/>
			</form:label>
		</td>
		<td>
			<form:input path="brand" />
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<c:if test="${!empty car.model}">
				<input type="submit"
					value="<spring:message text="Edit Car"/>" />
			</c:if>
			<c:if test="${empty person.brand}">
				<input type="submit"
					value="<spring:message text="Add Car"/>" />
			</c:if>
		</td>
	</tr>
</table>
</form:form>
<br>
<h3>Cars List</h3>
<c:if test="${!empty listCars}">
	<table class="tg">
	<tr>
		<th width="80">Car ID</th>
		<th width="120">Car Brand</th>
		<th width="120">Car Model</th>
		<th width="60">Edit</th>
		<th width="60">Delete</th>
	</tr>
	<c:forEach items="${listCars}" var="car">
		<tr>
			<td>${car.id}</td>
			<td>${car.model}</td>
			<td>${car.brand}</td>
			<td><a href="<c:url value='/edit/${car.id}' />" >Edit</a></td>
			<td><a href="<c:url value='/remove/${car.id}' />" >Delete</a></td>
		</tr>
	</c:forEach>
	</table>
</c:if>
</body>
</html>
