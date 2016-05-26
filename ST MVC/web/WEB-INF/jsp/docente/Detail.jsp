<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Docente Detail</title>
            <link rel="stylesheet" type="text/css" href="/ST_MVC/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Docente Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="Nombre:"/>
                    <h:outputText value="#{docente.docente.nombre}" title="Nombre" />
                    <h:outputText value="Apellido:"/>
                    <h:outputText value="#{docente.docente.apellido}" title="Apellido" />
                    <h:outputText value="IdDocente:"/>
                    <h:outputText value="#{docente.docente.idDocente}" title="IdDocente" />

                    <h:outputText value="CursoTutorCollection:" />
                    <h:panelGroup>
                        <h:outputText rendered="#{empty docente.docente.cursoTutorCollection}" value="(No Items)"/>
                        <h:dataTable value="#{docente.docente.cursoTutorCollection}" var="item" 
                                     border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                     rendered="#{not empty docente.docente.cursoTutorCollection}">
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="IdCursoTutor"/>
                                </f:facet>
                                <h:outputText value="#{item.idCursoTutor}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="TipoCurso"/>
                                </f:facet>
                                <h:outputText value="#{item.tipoCurso}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Año"/>
                                </f:facet>
                                <h:outputText value="#{item.año}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Ciclo"/>
                                </f:facet>
                                <h:outputText value="#{item.ciclo}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="TUTORcarnet"/>
                                </f:facet>
                                <h:outputText value="#{item.TUTORcarnet}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="DOCENTEiddocente"/>
                                </f:facet>
                                <h:outputText value="#{item.DOCENTEiddocente}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="CURSOLABseccion"/>
                                </f:facet>
                                <h:outputText value="#{item.CURSOLABseccion}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText escape="false" value="&nbsp;"/>
                                </f:facet>
                                <h:commandLink value="Show" action="#{cursoTutor.detailSetup}">
                                    <f:param name="jsfcrud.currentDocente" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][docente.docente][docente.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentCursoTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][cursoTutor.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="docente" />
                                    <f:param name="jsfcrud.relatedControllerType" value="View.jsf.DocenteController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Edit" action="#{cursoTutor.editSetup}">
                                    <f:param name="jsfcrud.currentDocente" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][docente.docente][docente.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentCursoTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][cursoTutor.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="docente" />
                                    <f:param name="jsfcrud.relatedControllerType" value="View.jsf.DocenteController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Destroy" action="#{cursoTutor.destroy}">
                                    <f:param name="jsfcrud.currentDocente" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][docente.docente][docente.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentCursoTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][cursoTutor.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="docente" />
                                    <f:param name="jsfcrud.relatedControllerType" value="View.jsf.DocenteController" />
                                </h:commandLink>
                            </h:column>
                        </h:dataTable>
                    </h:panelGroup>

                </h:panelGrid>
                <br />
                <h:commandLink action="#{docente.remove}" value="Destroy">
                    <f:param name="jsfcrud.currentDocente" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][docente.docente][docente.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{docente.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentDocente" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][docente.docente][docente.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{docente.createSetup}" value="New Docente" />
                <br />
                <h:commandLink action="#{docente.listSetup}" value="Show All Docente Items"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
