<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%--
    This file is an entry point for JavaServer Faces application.
--%>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>JSP Page</title>
<link rel="stylesheet" type="text/css" href="/ST_MVC/faces/jsfcrud.css" />
        </head>
        <body>
            <h1><h:outputText value="JavaServer Faces"/></h1>
                <h:form>
                    <h:commandLink action="#{tutor.listSetup}" value="Show All Tutor Items"/>
                </h:form>

                <h:form>
                    <h:commandLink action="#{periodo.listSetup}" value="Show All Periodo Items"/>
                </h:form>

                <h:form>
                    <h:commandLink action="#{notaCurso.listSetup}" value="Show All NotaCurso Items"/>
                </h:form>

                <h:form>
                    <h:commandLink action="#{historialTutor.listSetup}" value="Show All HistorialTutor Items"/>
                </h:form>

                <h:form>
                    <h:commandLink action="#{docente.listSetup}" value="Show All Docente Items"/>
                </h:form>

                <h:form>
                    <h:commandLink action="#{cursoTutor.listSetup}" value="Show All CursoTutor Items"/>
                </h:form>

                <h:form>
                    <h:commandLink action="#{cursoTe.listSetup}" value="Show All CursoTe Items"/>
                </h:form>

                <h:form>
                    <h:commandLink action="#{cursoLab.listSetup}" value="Show All CursoLab Items"/>
                </h:form>

                <h:form>
                    <h:commandLink action="#{users.listSetup}" value="Show All Users Items"/>
                </h:form>

        </body>
    </html>
</f:view>
