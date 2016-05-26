<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>New NotaCurso</title>
            <link rel="stylesheet" type="text/css" href="/ST_MVC/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>New NotaCurso</h1>
            <h:form>
                <h:inputHidden id="validateCreateField" validator="#{notaCurso.validateCreate}" value="value"/>
                <h:panelGrid columns="2">
                    <h:outputText value="NotaCurso:"/>
                    <h:inputText id="notaCurso" value="#{notaCurso.notaCurso.notaCurso}" title="NotaCurso" required="true" requiredMessage="The notaCurso field is required." />
                    <h:outputText value="Tutor:"/>
                    <h:selectOneMenu id="tutor" value="#{notaCurso.notaCurso.tutor}" title="Tutor" required="true" requiredMessage="The tutor field is required." >
                        <f:selectItems value="#{tutor.tutorItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>
                    <h:outputText value="CursoTe:"/>
                    <h:selectOneMenu id="cursoTe" value="#{notaCurso.notaCurso.cursoTe}" title="CursoTe" required="true" requiredMessage="The cursoTe field is required." >
                        <f:selectItems value="#{cursoTe.cursoTeItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>

                </h:panelGrid>
                <br />
                <h:commandLink action="#{notaCurso.create}" value="Create"/>
                <br />
                <br />
                <h:commandLink action="#{notaCurso.listSetup}" value="Show All NotaCurso Items" immediate="true"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
