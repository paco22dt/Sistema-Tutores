<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>CursoTe Detail</title>
            <link rel="stylesheet" type="text/css" href="/ST_MVC/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>CursoTe Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="IdCurso:"/>
                    <h:outputText value="#{cursoTe.cursoTe.idCurso}" title="IdCurso" />
                    <h:outputText value="Nombre:"/>
                    <h:outputText value="#{cursoTe.cursoTe.nombre}" title="Nombre" />
                    <h:outputText value="Seccion:"/>
                    <h:outputText value="#{cursoTe.cursoTe.seccion}" title="Seccion" />

                    <h:outputText value="NotaCursoCollection:" />
                    <h:panelGroup>
                        <h:outputText rendered="#{empty cursoTe.cursoTe.notaCursoCollection}" value="(No Items)"/>
                        <h:dataTable value="#{cursoTe.cursoTe.notaCursoCollection}" var="item" 
                                     border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                     rendered="#{not empty cursoTe.cursoTe.notaCursoCollection}">
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="NotaCurso"/>
                                </f:facet>
                                <h:outputText value="#{item.notaCurso}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Tutor"/>
                                </f:facet>
                                <h:outputText value="#{item.tutor}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="CursoTe"/>
                                </f:facet>
                                <h:outputText value="#{item.cursoTe}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText escape="false" value="&nbsp;"/>
                                </f:facet>
                                <h:commandLink value="Show" action="#{notaCurso.detailSetup}">
                                    <f:param name="jsfcrud.currentCursoTe" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cursoTe.cursoTe][cursoTe.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentNotaCurso" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][notaCurso.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="cursoTe" />
                                    <f:param name="jsfcrud.relatedControllerType" value="View.jsf.CursoTeController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Edit" action="#{notaCurso.editSetup}">
                                    <f:param name="jsfcrud.currentCursoTe" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cursoTe.cursoTe][cursoTe.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentNotaCurso" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][notaCurso.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="cursoTe" />
                                    <f:param name="jsfcrud.relatedControllerType" value="View.jsf.CursoTeController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Destroy" action="#{notaCurso.destroy}">
                                    <f:param name="jsfcrud.currentCursoTe" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cursoTe.cursoTe][cursoTe.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentNotaCurso" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][notaCurso.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="cursoTe" />
                                    <f:param name="jsfcrud.relatedControllerType" value="View.jsf.CursoTeController" />
                                </h:commandLink>
                            </h:column>
                        </h:dataTable>
                    </h:panelGroup>

                </h:panelGrid>
                <br />
                <h:commandLink action="#{cursoTe.remove}" value="Destroy">
                    <f:param name="jsfcrud.currentCursoTe" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cursoTe.cursoTe][cursoTe.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{cursoTe.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentCursoTe" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cursoTe.cursoTe][cursoTe.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{cursoTe.createSetup}" value="New CursoTe" />
                <br />
                <h:commandLink action="#{cursoTe.listSetup}" value="Show All CursoTe Items"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
