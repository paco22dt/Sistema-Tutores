<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>New CursoTutor</title>
            <link rel="stylesheet" type="text/css" href="/ST_MVC/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>New CursoTutor</h1>
            <h:form>
                <h:inputHidden id="validateCreateField" validator="#{cursoTutor.validateCreate}" value="value"/>
                <h:panelGrid columns="2">
                    <h:outputText value="IdCursoTutor:"/>
                    <h:inputText id="idCursoTutor" value="#{cursoTutor.cursoTutor.idCursoTutor}" title="IdCursoTutor" required="true" requiredMessage="The idCursoTutor field is required." />
                    <h:outputText value="TipoCurso:"/>
                    <h:inputText id="tipoCurso" value="#{cursoTutor.cursoTutor.tipoCurso}" title="TipoCurso" required="true" requiredMessage="The tipoCurso field is required." />
                    <h:outputText value="Año:"/>
                    <h:inputText id="año" value="#{cursoTutor.cursoTutor.año}" title="Año" required="true" requiredMessage="The año field is required." />
                    <h:outputText value="Ciclo:"/>
                    <h:inputText id="ciclo" value="#{cursoTutor.cursoTutor.ciclo}" title="Ciclo" required="true" requiredMessage="The ciclo field is required." />
                    <h:outputText value="PeriodoCollection:"/>
                    <h:selectManyListbox id="periodoCollection" value="#{cursoTutor.cursoTutor.jsfcrud_transform[jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method.collectionToArray][jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method.arrayToList].periodoCollection}" title="PeriodoCollection" size="6" converter="#{periodo.converter}" >
                        <f:selectItems value="#{periodo.periodoItemsAvailableSelectMany}"/>
                    </h:selectManyListbox>
                    <h:outputText value="CursoLabCollection:"/>
                    <h:selectManyListbox id="cursoLabCollection" value="#{cursoTutor.cursoTutor.jsfcrud_transform[jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method.collectionToArray][jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method.arrayToList].cursoLabCollection}" title="CursoLabCollection" size="6" converter="#{cursoLab.converter}" >
                        <f:selectItems value="#{cursoLab.cursoLabItemsAvailableSelectMany}"/>
                    </h:selectManyListbox>
                    <h:outputText value="TUTORcarnet:"/>
                    <h:selectOneMenu id="TUTORcarnet" value="#{cursoTutor.cursoTutor.TUTORcarnet}" title="TUTORcarnet" required="true" requiredMessage="The TUTORcarnet field is required." >
                        <f:selectItems value="#{tutor.tutorItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>
                    <h:outputText value="DOCENTEiddocente:"/>
                    <h:selectOneMenu id="DOCENTEiddocente" value="#{cursoTutor.cursoTutor.DOCENTEiddocente}" title="DOCENTEiddocente" required="true" requiredMessage="The DOCENTEiddocente field is required." >
                        <f:selectItems value="#{docente.docenteItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>
                    <h:outputText value="CURSOLABseccion:"/>
                    <h:selectOneMenu id="CURSOLABseccion" value="#{cursoTutor.cursoTutor.CURSOLABseccion}" title="CURSOLABseccion" required="true" requiredMessage="The CURSOLABseccion field is required." >
                        <f:selectItems value="#{cursoLab.cursoLabItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>

                </h:panelGrid>
                <br />
                <h:commandLink action="#{cursoTutor.create}" value="Create"/>
                <br />
                <br />
                <h:commandLink action="#{cursoTutor.listSetup}" value="Show All CursoTutor Items" immediate="true"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
