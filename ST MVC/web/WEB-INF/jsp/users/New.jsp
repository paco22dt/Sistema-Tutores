<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>New Users</title>
            <link rel="stylesheet" type="text/css" href="/ST_MVC/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>New Users</h1>
            <h:form>
                <h:inputHidden id="validateCreateField" validator="#{users.validateCreate}" value="value"/>
                <h:panelGrid columns="2">
                    <h:outputText value="IdUser:"/>
                    <h:inputText id="idUser" value="#{users.users.idUser}" title="IdUser" required="true" requiredMessage="The idUser field is required." />
                    <h:outputText value="Password:"/>
                    <h:inputText id="password" value="#{users.users.password}" title="Password" required="true" requiredMessage="The password field is required." />

                </h:panelGrid>
                <br />
                <h:commandLink action="#{users.create}" value="Create"/>
                <br />
                <br />
                <h:commandLink action="#{users.listSetup}" value="Show All Users Items" immediate="true"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
