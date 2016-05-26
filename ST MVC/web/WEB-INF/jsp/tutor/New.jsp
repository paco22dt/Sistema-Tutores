<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>New Tutor</title>
            <link rel="stylesheet" type="text/css" href="/ST_MVC/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>New Tutor</h1>
            <h:form>
                <h:inputHidden id="validateCreateField" validator="#{tutor.validateCreate}" value="value"/>
                <h:panelGrid columns="2">
                    <h:outputText value="Carnet:"/>
                    <h:inputText id="carnet" value="#{tutor.tutor.carnet}" title="Carnet" required="true" requiredMessage="The carnet field is required." />
                    <h:outputText value="Nombre:"/>
                    <h:inputText id="nombre" value="#{tutor.tutor.nombre}" title="Nombre" required="true" requiredMessage="The nombre field is required." />
                    <h:outputText value="Apellido:"/>
                    <h:inputText id="apellido" value="#{tutor.tutor.apellido}" title="Apellido" required="true" requiredMessage="The apellido field is required." />
                    <h:outputText value="Carrera:"/>
                    <h:inputText id="carrera" value="#{tutor.tutor.carrera}" title="Carrera" required="true" requiredMessage="The carrera field is required." />
                    <h:outputText value="Porcentaje:"/>
                    <h:inputText id="porcentaje" value="#{tutor.tutor.porcentaje}" title="Porcentaje" />
                    <h:outputText value="TipoAyudaEconomica:"/>
                    <h:inputText id="tipoAyudaEconomica" value="#{tutor.tutor.tipoAyudaEconomica}" title="TipoAyudaEconomica" />
                    <h:outputText value="Correo:"/>
                    <h:inputText id="correo" value="#{tutor.tutor.correo}" title="Correo" required="true" requiredMessage="The correo field is required." />
                    <h:outputText value="CicloActual:"/>
                    <h:inputText id="cicloActual" value="#{tutor.tutor.cicloActual}" title="CicloActual" required="true" requiredMessage="The cicloActual field is required." />
                    <h:outputText value="Promedio:"/>
                    <h:inputText id="promedio" value="#{tutor.tutor.promedio}" title="Promedio" required="true" requiredMessage="The promedio field is required." />
                    <h:outputText value="Telefono:"/>
                    <h:inputText id="telefono" value="#{tutor.tutor.telefono}" title="Telefono" required="true" requiredMessage="The telefono field is required." />
                    <h:outputText value="HistorialTutorCollection:"/>
                    <h:selectManyListbox id="historialTutorCollection" value="#{tutor.tutor.jsfcrud_transform[jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method.collectionToArray][jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method.arrayToList].historialTutorCollection}" title="HistorialTutorCollection" size="6" converter="#{historialTutor.converter}" >
                        <f:selectItems value="#{historialTutor.historialTutorItemsAvailableSelectMany}"/>
                    </h:selectManyListbox>
                    <h:outputText value="NotaCursoCollection:"/>
                    <h:outputText escape="false" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getCollectionAsString'][tutor.tutor.notaCursoCollection == null ? jsfcrud_null : tutor.tutor.notaCursoCollection].jsfcrud_invoke}" title="NotaCursoCollection" />
                    <h:outputText value="CursoTutorCollection:"/>
                    <h:selectManyListbox id="cursoTutorCollection" value="#{tutor.tutor.jsfcrud_transform[jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method.collectionToArray][jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method.arrayToList].cursoTutorCollection}" title="CursoTutorCollection" size="6" converter="#{cursoTutor.converter}" >
                        <f:selectItems value="#{cursoTutor.cursoTutorItemsAvailableSelectMany}"/>
                    </h:selectManyListbox>

                </h:panelGrid>
                <br />
                <h:commandLink action="#{tutor.create}" value="Create"/>
                <br />
                <br />
                <h:commandLink action="#{tutor.listSetup}" value="Show All Tutor Items" immediate="true"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
