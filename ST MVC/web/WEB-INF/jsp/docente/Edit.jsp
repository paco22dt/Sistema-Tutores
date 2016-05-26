<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing Docente</title>
            <link rel="stylesheet" type="text/css" href="/ST_MVC/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Editing Docente</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="Nombre:"/>
                    <h:inputText id="nombre" value="#{docente.docente.nombre}" title="Nombre" required="true" requiredMessage="The nombre field is required." />
                    <h:outputText value="Apellido:"/>
                    <h:inputText id="apellido" value="#{docente.docente.apellido}" title="Apellido" required="true" requiredMessage="The apellido field is required." />
                    <h:outputText value="IdDocente:"/>
                    <h:outputText value="#{docente.docente.idDocente}" title="IdDocente" />
                    <h:outputText value="CursoTutorCollection:"/>
                    <h:selectManyListbox id="cursoTutorCollection" value="#{docente.docente.jsfcrud_transform[jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method.collectionToArray][jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method.arrayToList].cursoTutorCollection}" title="CursoTutorCollection" size="6" converter="#{cursoTutor.converter}" >
                        <f:selectItems value="#{cursoTutor.cursoTutorItemsAvailableSelectMany}"/>
                    </h:selectManyListbox>

                </h:panelGrid>
                <br />
                <h:commandLink action="#{docente.edit}" value="Save">
                    <f:param name="jsfcrud.currentDocente" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][docente.docente][docente.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{docente.detailSetup}" value="Show" immediate="true">
                    <f:param name="jsfcrud.currentDocente" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][docente.docente][docente.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <h:commandLink action="#{docente.listSetup}" value="Show All Docente Items" immediate="true"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
