<%-- 
    Document   : drivefiles
    Created on : Jan 18, 2017, 5:48:34 PM
    Author     : JayeshB
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>GDrive Spreadsheets</title>
    </head>
    <body>
        <form action="loadSheetsData" method="post">
            <s:select label="Select a File" list="%{fileNames}" name="fileId" headerKey="" disabled="disabled" headerValue="Select File" required="true"/><br>
            <button type="submit">Get MailIds</button>
        </form>
            <h3><s:property value="msg"/></h3>
    </body>
</html>
