<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Users Detail</title>
            <link rel="stylesheet" type="text/css" href="/ST_MVC/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Users Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="IdUser:"/>
                    <h:outputText value="#{users.users.idUser}" title="IdUser" />
                    <h:outputText value="Password:"/>
                    <h:outputText value="#{users.users.password}" title="Password" />


                </h:panelGrid>
                <br />
                <h:commandLink action="#{users.remove}" value="Destroy">
                    <f:param name="jsfcrud.currentUsers" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][users.users][users.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{users.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentUsers" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][users.users][users.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{users.createSetup}" value="New Users" />
                <br />
                <h:commandLink action="#{users.listSetup}" value="Show All Users Items"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
