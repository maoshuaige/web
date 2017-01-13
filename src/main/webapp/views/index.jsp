<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">    
<html>    
    <head>    
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">    
        <title>Insert title here</title>    
    </head>    
        
    <body>   
    	<form action="<%=request.getContextPath() %>/action/userinfo/login" method='post'> 
         <input type="text" class="form-control" id="username" placeholder="UserName" name="j_username" >
         <input type="password" class="form-control" id="password" placeholder="Password" name="j_password">
         <input type="submit" value="123" class="btn2"  />
    	</form>
    </body>    
</html>