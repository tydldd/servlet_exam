<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件上传</title>
</head>
<body>
	<form action="upload.do" method="post" enctype="multipart/form-data">
		<input type="file" size="30" name="file01" /> <br />
		<!-- input type="file" size="30" name="file02" /> <br /-->
		<input name="up123" type="submit" value="上传" />
	</form>
</body>
</html>