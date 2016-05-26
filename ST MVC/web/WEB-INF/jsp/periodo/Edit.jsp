<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing Periodo</title>
            <link rel="stylesheet" type="text/css" href="/ST_MVC/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Editing Periodo</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="IdPeriodo:"/>
                    <h:outputText value="#{periodo.periodo.idPeriodo}" title="IdPeriodo" />
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
                <h:commandLink action="#{periodo.edit}" value="Save">
                    <f:param name="jsfcrud.currentPeriodo" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][periodo.periodo][periodo.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{periodo.detailSetup}" value="Show" immediate="true">
                    <f:param name="jsfcrud.currentPeriodo" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][periodo.periodo][periodo.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <h:commandLink action="#{periodo.listSetup}" value="Show All Periodo Items" immediate="true"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
