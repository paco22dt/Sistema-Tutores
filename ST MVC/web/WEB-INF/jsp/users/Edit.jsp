<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing Users</title>
            <link rel="stylesheet" type="text/css" href="/ST_MVC/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Editing Users</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="IdUser:"/>
                    <h:outputText value="#{users.users.idUser}" title="IdUser" />
                    <h:outputText value="Password:"/>
                    <h:inputText id="password" value="#{users.users.password}" title="Password" required="true" requiredMessage="The password field is required." />

                </h:panelGrid>
                <br />
                <h:commandLink action="#{users.edit}" value="Save">
                    <f:param name="jsfcrud.currentUsers" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][users.users][users.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{users.detailSetup}" value="Show" immediate="true">
                    <f:param name="jsfcrud.currentUsers" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][users.users][users.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <h:commandLink action="#{users.listSetup}" value="Show All Users Items" immediate="true"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
