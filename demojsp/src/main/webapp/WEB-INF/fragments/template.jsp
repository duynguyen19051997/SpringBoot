<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title><tiles:getAsString name="title"/></title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/css/app.css" rel="stylesheet"/>
</head>

<body>
<header id="header">
    <tiles:insertAttribute name="header"/>
</header>

<section id="aside">
    <tiles:insertAttribute name="aside"/>
</section>

<section id="site-content">
    <tiles:insertAttribute name="main"/>
</section>

<footer id="footer">
    <tiles:insertAttribute name="footer"/>
</footer>
</body>
</html>