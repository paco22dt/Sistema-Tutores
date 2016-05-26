<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Periodo Detail</title>
            <link rel="stylesheet" type="text/css" href="/ST_MVC/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Periodo Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="IdPeriodo:"/>
                    <h:outputText value="#{periodo.periodo.idPeriodo}" title="IdPeriodo" />
                    <h:outputText value="FechaInicio:"/>
                    <h:outputText value="#{periodo.periodo.fechaInicio}" title="FechaInicio" >
                        <f:convertDateTime pattern="MM/dd/yyyy" />
                    </h:outputText>
                    <h:outputText value="FechaFin:"/>
                    <h:outputText value="#{periodo.periodo.fechaFin}" title="FechaFin" >
                        <f:convertDateTime pattern="MM/dd/yyyy" />
                    </h:outputText>
                    <h:outputText value="CURSOTUTORidcursotutor:"/>
                    <h:panelGroup>
                        <h:outputText value="#{periodo.periodo.CURSOTUTORidcursotutor}"/>
                        <h:panelGroup rendered="#{periodo.periodo.CURSOTUTORidcursotutor != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{cursoTutor.detailSetup}">
                                <f:param name="jsfcrud.currentPeriodo" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][periodo.periodo][periodo.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentCursoTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][periodo.periodo.CURSOTUTORidcursotutor][cursoTutor.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="periodo"/>
                                <f:param name="jsfcrud.relatedControllerType" value="View.jsf.PeriodoController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{cursoTutor.editSetup}">
                                <f:param name="jsfcrud.currentPeriodo" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][periodo.periodo][periodo.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentCursoTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][periodo.periodo.CURSOTUTORidcursotutor][cursoTutor.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="periodo"/>
                                <f:param name="jsfcrud.relatedControllerType" value="View.jsf.PeriodoController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{cursoTutor.destroy}">
                                <f:param name="jsfcrud.currentPeriodo" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][periodo.periodo][periodo.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentCursoTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][periodo.periodo.CURSOTUTORidcursotutor][cursoTutor.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="periodo"/>
                                <f:param name="jsfcrud.relatedControllerType" value="View.jsf.PeriodoController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>


                </h:panelGrid>
                <br />
                <h:commandLink action="#{periodo.remove}" value="Destroy">
                    <f:param name="jsfcrud.currentPeriodo" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][periodo.periodo][periodo.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{periodo.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentPeriodo" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][periodo.periodo][periodo.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{periodo.createSetup}" value="New Periodo" />
                <br />
                <h:commandLink action="#{periodo.listSetup}" value="Show All Periodo Items"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
