<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing HistorialTutor</title>
            <link rel="stylesheet" type="text/css" href="/ST_MVC/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Editing HistorialTutor</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="IdHistorialTutor:"/>
                    <h:outputText value="#{historialTutor.historialTutor.idHistorialTutor}" title="IdHistorialTutor" />
                    <h:outputText value="Curso:"/>
                    <h:inputText id="curso" value="#{historialTutor.historialTutor.curso}" title="Curso" required="true" requiredMessage="The curso field is required." />
                    <h:outputText value="Seccion:"/>
                    <h:inputText id="seccion" value="#{historialTutor.historialTutor.seccion}" title="Seccion" required="true" requiredMessage="The seccion field is required." />
                    <h:outputText value="NombreDocente:"/>
                    <h:inputText id="nombreDocente" value="#{historialTutor.historialTutor.nombreDocente}" title="NombreDocente" required="true" requiredMessage="The nombreDocente field is required." />
                    <h:outputText value="ApellidoDocente:"/>
                    <h:inputText id="apellidoDocente" value="#{historialTutor.historialTutor.apellidoDocente}" title="ApellidoDocente" required="true" requiredMessage="The apellidoDocente field is required." />
                    <h:outputText value="NotaEvaluacion:"/>
                    <h:inputText id="notaEvaluacion" value="#{historialTutor.historialTutor.notaEvaluacion}" title="NotaEvaluacion" required="true" requiredMessage="The notaEvaluacion field is required." />
                    <h:outputText value="TUTORcarnet:"/>
                    <h:selectOneMenu id="TUTORcarnet" value="#{historialTutor.historialTutor.TUTORcarnet}" title="TUTORcarnet" required="true" requiredMessage="The TUTORcarnet field is required." >
                        <f:selectItems value="#{tutor.tutorItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>

                </h:panelGrid>
                <br />
                <h:commandLink action="#{historialTutor.edit}" value="Save">
                    <f:param name="jsfcrud.currentHistorialTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][historialTutor.historialTutor][historialTutor.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{historialTutor.detailSetup}" value="Show" immediate="true">
                    <f:param name="jsfcrud.currentHistorialTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][historialTutor.historialTutor][historialTutor.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <h:commandLink action="#{historialTutor.listSetup}" value="Show All HistorialTutor Items" immediate="true"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
