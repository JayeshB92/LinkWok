<%-- 
    Document   : emailcolumns
    Created on : Jan 19, 2017, 12:29:10 AM
    Author     : JayeshB
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Email Columns</title>
    </head>
    <body>
        <form action="columnSelected" method="post">
            <s:checkboxlist list="columnHead" label="Choose Column" name="columns"/><br/>
            <button type="submit">Submit</button>
        </form>
    </body>
</html>
