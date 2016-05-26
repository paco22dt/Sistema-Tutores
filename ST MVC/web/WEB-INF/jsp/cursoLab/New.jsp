<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>New CursoLab</title>
            <link rel="stylesheet" type="text/css" href="/ST_MVC/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>New CursoLab</h1>
            <h:form>
                <h:inputHidden id="validateCreateField" validator="#{cursoLab.validateCreate}" value="value"/>
                <h:panelGrid columns="2">
                    <h:outputText value="Seccion:"/>
                    <h:inputText id="seccion" value="#{cursoLab.cursoLab.seccion}" title="Seccion" required="true" requiredMessage="The seccion field is required." />
                    <h:outputText value="CURSOTEidcurso:"/>
                    <h:selectOneMenu id="CURSOTEidcurso" value="#{cursoLab.cursoLab.CURSOTEidcurso}" title="CURSOTEidcurso" required="true" requiredMessage="The CURSOTEidcurso field is required." >
                        <f:selectItems value="#{cursoTutor.cursoTutorItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>
                    <h:outputText value="CursoTutorCollection:"/>
                    <h:selectManyListbox id="cursoTutorCollection" value="#{cursoLab.cursoLab.jsfcrud_transform[jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method.collectionToArray][jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method.arrayToList].cursoTutorCollection}" title="CursoTutorCollection" size="6" converter="#{cursoTutor.converter}" >
                        <f:selectItems value="#{cursoTutor.cursoTutorItemsAvailableSelectMany}"/>
                    </h:selectManyListbox>

                </h:panelGrid>
                <br />
                <h:commandLink action="#{cursoLab.create}" value="Create"/>
                <br />
                <br />
                <h:commandLink action="#{cursoLab.listSetup}" value="Show All CursoLab Items" immediate="true"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
