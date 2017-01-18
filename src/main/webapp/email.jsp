<%-- 
    Document   : email
    Created on : Jan 19, 2017, 1:37:06 AM
    Author     : JayeshB
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Email</title>
    </head>
    <body>
        <h1>Email Form</h1>
        <form method="post">
            <s:textarea label="To" readonly="true" name="toAddresses" cols="40" rows="3" value="%{selectedEmailIds}"/><br/>
            <s:textfield label="Subject" name="subject"/><br/><br/>
            <s:textarea label="Message" name="body" cols="40" rows="3"/><br/>
            <button type="submit">Send Mails</button>
        </form>
    </body>
</html>
