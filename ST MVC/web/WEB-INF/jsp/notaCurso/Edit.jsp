<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing NotaCurso</title>
            <link rel="stylesheet" type="text/css" href="/ST_MVC/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Editing NotaCurso</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="NotaCurso:"/>
                    <h:inputText id="notaCurso" value="#{notaCurso.notaCurso.notaCurso}" title="NotaCurso" required="true" requiredMessage="The notaCurso field is required." />
                    <h:outputText value="Tutor:"/>
                    <h:outputText value=" #{notaCurso.notaCurso.tutor}" title="Tutor" />
                    <h:outputText value="CursoTe:"/>
                    <h:outputText value=" #{notaCurso.notaCurso.cursoTe}" title="CursoTe" />

                </h:panelGrid>
                <br />
                <h:commandLink action="#{notaCurso.edit}" value="Save">
                    <f:param name="jsfcrud.currentNotaCurso" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notaCurso.notaCurso][notaCurso.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{notaCurso.detailSetup}" value="Show" immediate="true">
                    <f:param name="jsfcrud.currentNotaCurso" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notaCurso.notaCurso][notaCurso.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <h:commandLink action="#{notaCurso.listSetup}" value="Show All NotaCurso Items" immediate="true"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
