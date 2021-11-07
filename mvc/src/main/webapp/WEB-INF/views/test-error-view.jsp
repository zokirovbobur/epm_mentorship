<%@ page language="java"
         contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<html>
<body>
<h3>Test Error View</h3>
<p>Request Uri: <b>${requestUri}</b></p>
<p>Exception: <b>${exception['class'].name}</b></p>
<p>Message: <b>${exception.message}</b></p>
<p>Response status: <b>${statusValue} (${statusStr})</b></p>
</body>
</html>