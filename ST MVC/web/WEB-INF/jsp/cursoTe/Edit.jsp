<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing CursoTe</title>
            <link rel="stylesheet" type="text/css" href="/ST_MVC/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Editing CursoTe</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="IdCurso:"/>
                    <h:outputText value="#{cursoTe.cursoTe.idCurso}" title="IdCurso" />
                    <h:outputText value="Nombre:"/>
                    <h:inputText id="nombre" value="#{cursoTe.cursoTe.nombre}" title="Nombre" required="true" requiredMessage="The nombre field is required." />
                    <h:outputText value="Seccion:"/>
                    <h:inputText id="seccion" value="#{cursoTe.cursoTe.seccion}" title="Seccion" required="true" requiredMessage="The seccion field is required." />
                    <h:outputText value="NotaCursoCollection:"/>
                    <h:outputText escape="false" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getCollectionAsString'][cursoTe.cursoTe.notaCursoCollection == null ? jsfcrud_null : cursoTe.cursoTe.notaCursoCollection].jsfcrud_invoke}" title="NotaCursoCollection" />

                </h:panelGrid>
                <br />
                <h:commandLink action="#{cursoTe.edit}" value="Save">
                    <f:param name="jsfcrud.currentCursoTe" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cursoTe.cursoTe][cursoTe.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{cursoTe.detailSetup}" value="Show" immediate="true">
                    <f:param name="jsfcrud.currentCursoTe" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cursoTe.cursoTe][cursoTe.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <h:commandLink action="#{cursoTe.listSetup}" value="Show All CursoTe Items" immediate="true"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
