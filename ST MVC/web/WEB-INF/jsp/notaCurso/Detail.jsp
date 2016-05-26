<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>NotaCurso Detail</title>
            <link rel="stylesheet" type="text/css" href="/ST_MVC/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>NotaCurso Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="NotaCurso:"/>
                    <h:outputText value="#{notaCurso.notaCurso.notaCurso}" title="NotaCurso" />
                    <h:outputText value="Tutor:"/>
                    <h:panelGroup>
                        <h:outputText value="#{notaCurso.notaCurso.tutor}"/>
                        <h:panelGroup rendered="#{notaCurso.notaCurso.tutor != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{tutor.detailSetup}">
                                <f:param name="jsfcrud.currentNotaCurso" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notaCurso.notaCurso][notaCurso.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notaCurso.notaCurso.tutor][tutor.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="notaCurso"/>
                                <f:param name="jsfcrud.relatedControllerType" value="View.jsf.NotaCursoController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{tutor.editSetup}">
                                <f:param name="jsfcrud.currentNotaCurso" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notaCurso.notaCurso][notaCurso.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notaCurso.notaCurso.tutor][tutor.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="notaCurso"/>
                                <f:param name="jsfcrud.relatedControllerType" value="View.jsf.NotaCursoController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{tutor.destroy}">
                                <f:param name="jsfcrud.currentNotaCurso" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notaCurso.notaCurso][notaCurso.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notaCurso.notaCurso.tutor][tutor.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="notaCurso"/>
                                <f:param name="jsfcrud.relatedControllerType" value="View.jsf.NotaCursoController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>
                    <h:outputText value="CursoTe:"/>
                    <h:panelGroup>
                        <h:outputText value="#{notaCurso.notaCurso.cursoTe}"/>
                        <h:panelGroup rendered="#{notaCurso.notaCurso.cursoTe != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{cursoTe.detailSetup}">
                                <f:param name="jsfcrud.currentNotaCurso" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notaCurso.notaCurso][notaCurso.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentCursoTe" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notaCurso.notaCurso.cursoTe][cursoTe.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="notaCurso"/>
                                <f:param name="jsfcrud.relatedControllerType" value="View.jsf.NotaCursoController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{cursoTe.editSetup}">
                                <f:param name="jsfcrud.currentNotaCurso" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notaCurso.notaCurso][notaCurso.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentCursoTe" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notaCurso.notaCurso.cursoTe][cursoTe.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="notaCurso"/>
                                <f:param name="jsfcrud.relatedControllerType" value="View.jsf.NotaCursoController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{cursoTe.destroy}">
                                <f:param name="jsfcrud.currentNotaCurso" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notaCurso.notaCurso][notaCurso.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentCursoTe" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notaCurso.notaCurso.cursoTe][cursoTe.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="notaCurso"/>
                                <f:param name="jsfcrud.relatedControllerType" value="View.jsf.NotaCursoController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>


                </h:panelGrid>
                <br />
                <h:commandLink action="#{notaCurso.remove}" value="Destroy">
                    <f:param name="jsfcrud.currentNotaCurso" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notaCurso.notaCurso][notaCurso.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{notaCurso.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentNotaCurso" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][notaCurso.notaCurso][notaCurso.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{notaCurso.createSetup}" value="New NotaCurso" />
                <br />
                <h:commandLink action="#{notaCurso.listSetup}" value="Show All NotaCurso Items"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
