<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>New Periodo</title>
            <link rel="stylesheet" type="text/css" href="/ST_MVC/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>New Periodo</h1>
            <h:form>
                <h:inputHidden id="validateCreateField" validator="#{periodo.validateCreate}" value="value"/>
                <h:panelGrid columns="2">
                    <h:outputText value="FechaInicio (MM/dd/yyyy):"/>
                    <h:inputText id="fechaInicio" value="#{periodo.periodo.fechaInicio}" title="FechaInicio" required="true" requiredMessage="The fechaInicio field is required." >
                        <f:convertDateTime pattern="MM/dd/yyyy" />
                    </h:inputText>
                    <h:outputText value="FechaFin (MM/dd/yyyy):"/>
                    <h:inputText id="fechaFin" value="#{periodo.periodo.fechaFin}" title="FechaFin" required="true" requiredMessage="The fechaFin field is required." >
                        <f:convertDateTime pattern="MM/dd/yyyy" />
                    </h:inputText>
                    <h:outputText value="CURSOTUTORidcursotutor:"/>
                    <h:selectOneMenu id="CURSOTUTORidcursotutor" value="#{periodo.periodo.CURSOTUTORidcursotutor}" title="CURSOTUTORidcursotutor" required="true" requiredMessage="The CURSOTUTORidcursotutor field is required." >
                        <f:selectItems value="#{cursoTutor.cursoTutorItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>

                </h:panelGrid>
                <br />
                <h:commandLink action="#{periodo.create}" value="Create"/>
                <br />
                <br />
                <h:commandLink action="#{periodo.listSetup}" value="Show All Periodo Items" immediate="true"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
