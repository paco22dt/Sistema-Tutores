<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>HistorialTutor Detail</title>
            <link rel="stylesheet" type="text/css" href="/ST_MVC/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>HistorialTutor Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="IdHistorialTutor:"/>
                    <h:outputText value="#{historialTutor.historialTutor.idHistorialTutor}" title="IdHistorialTutor" />
                    <h:outputText value="Curso:"/>
                    <h:outputText value="#{historialTutor.historialTutor.curso}" title="Curso" />
                    <h:outputText value="Seccion:"/>
                    <h:outputText value="#{historialTutor.historialTutor.seccion}" title="Seccion" />
                    <h:outputText value="NombreDocente:"/>
                    <h:outputText value="#{historialTutor.historialTutor.nombreDocente}" title="NombreDocente" />
                    <h:outputText value="ApellidoDocente:"/>
                    <h:outputText value="#{historialTutor.historialTutor.apellidoDocente}" title="ApellidoDocente" />
                    <h:outputText value="NotaEvaluacion:"/>
                    <h:outputText value="#{historialTutor.historialTutor.notaEvaluacion}" title="NotaEvaluacion" />
                    <h:outputText value="TUTORcarnet:"/>
                    <h:panelGroup>
                        <h:outputText value="#{historialTutor.historialTutor.TUTORcarnet}"/>
                        <h:panelGroup rendered="#{historialTutor.historialTutor.TUTORcarnet != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{tutor.detailSetup}">
                                <f:param name="jsfcrud.currentHistorialTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][historialTutor.historialTutor][historialTutor.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][historialTutor.historialTutor.TUTORcarnet][tutor.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="historialTutor"/>
                                <f:param name="jsfcrud.relatedControllerType" value="View.jsf.HistorialTutorController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{tutor.editSetup}">
                                <f:param name="jsfcrud.currentHistorialTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][historialTutor.historialTutor][historialTutor.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][historialTutor.historialTutor.TUTORcarnet][tutor.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="historialTutor"/>
                                <f:param name="jsfcrud.relatedControllerType" value="View.jsf.HistorialTutorController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{tutor.destroy}">
                                <f:param name="jsfcrud.currentHistorialTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][historialTutor.historialTutor][historialTutor.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][historialTutor.historialTutor.TUTORcarnet][tutor.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="historialTutor"/>
                                <f:param name="jsfcrud.relatedControllerType" value="View.jsf.HistorialTutorController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>


                </h:panelGrid>
                <br />
                <h:commandLink action="#{historialTutor.remove}" value="Destroy">
                    <f:param name="jsfcrud.currentHistorialTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][historialTutor.historialTutor][historialTutor.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{historialTutor.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentHistorialTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][historialTutor.historialTutor][historialTutor.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{historialTutor.createSetup}" value="New HistorialTutor" />
                <br />
                <h:commandLink action="#{historialTutor.listSetup}" value="Show All HistorialTutor Items"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
