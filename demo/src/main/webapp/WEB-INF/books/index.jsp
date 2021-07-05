<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script>
	var bookId = <c:out value="${book.id}"/>;
</script>
</head>
<body>

		<h1>All Books</h1>
		<table>
		    <thead>
		        <tr>
		            <th>Title</th>
		            <th>Description</th>
		            <th>Language</th>
		            <th>Number of Pages</th>
		        </tr>
		    </thead>
		    <tbody>
		        <c:forEach items="${books}" var="book">
		        <tr>
		            <td> <c:out value="${book.title}"/></td>
		            <td> <c:out value="${book.description}"/></td>
		            <td> <c:out value="${book.language}"/></td>
		            <td> <c:out value="${book.numberOfPages}"/></td>
		            <td><a href = "<c:url value = "/books/${book.id}"/>">Show</a></td>

		           	
		        </tr>
		        </c:forEach>
		    </tbody>
		</table>
		<a href="/books/new">New Book</a>
		
</body>
</html>