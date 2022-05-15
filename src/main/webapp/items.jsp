<%@page import="com.Item"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bill Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/items.js"></script>
</head>
<body> 
<div class="container"><div class="row"><div class="col-6"> 
<h1>Service Management V10.1</h1>
<form id="formItem" name="formItem" method="post" action="items.jsp">
 <br>Customer Name: 
 <input id="CustomerName" name="Customer Name" type="text" 
 class="form-control form-control-sm">
 <br> Account No: 
 <input id="AccountNo" name="AccountNo" type="text" 
 class="form-control form-control-sm">
 <br> Bill Date: 
 <input id="BillDate" name="BillDate" type="text" 
 class="form-control form-control-sm">
 <br> Bill Time: 
 <input id="BillTime" name="BillTime" type="text" 
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 <input type="hidden" id="hidBillIDSave" 
 name="hidBillIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divItemsGrid">
 <%
 Item itemObj = new Item(); 
 out.print(itemObj.readItems()); 
 %>
</div>
</div> </div> </div> 
</body>
</html>