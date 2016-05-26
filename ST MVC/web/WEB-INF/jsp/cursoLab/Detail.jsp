<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>CursoLab Detail</title>
            <link rel="stylesheet" type="text/css" href="/ST_MVC/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>CursoLab Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="Seccion:"/>
                    <h:outputText value="#{cursoLab.cursoLab.seccion}" title="Seccion" />
                    <h:outputText value="CURSOTEidcurso:"/>
                    <h:panelGroup>
                        <h:outputText value="#{cursoLab.cursoLab.CURSOTEidcurso}"/>
                        <h:panelGroup rendered="#{cursoLab.cursoLab.CURSOTEidcurso != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{cursoTutor.detailSetup}">
                                <f:param name="jsfcrud.currentCursoLab" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cursoLab.cursoLab][cursoLab.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentCursoTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cursoLab.cursoLab.CURSOTEidcurso][cursoTutor.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="cursoLab"/>
                                <f:param name="jsfcrud.relatedControllerType" value="View.jsf.CursoLabController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{cursoTutor.editSetup}">
                                <f:param name="jsfcrud.currentCursoLab" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cursoLab.cursoLab][cursoLab.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentCursoTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cursoLab.cursoLab.CURSOTEidcurso][cursoTutor.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="cursoLab"/>
                                <f:param name="jsfcrud.relatedControllerType" value="View.jsf.CursoLabController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{cursoTutor.destroy}">
                                <f:param name="jsfcrud.currentCursoLab" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cursoLab.cursoLab][cursoLab.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentCursoTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cursoLab.cursoLab.CURSOTEidcurso][cursoTutor.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="cursoLab"/>
                                <f:param name="jsfcrud.relatedControllerType" value="View.jsf.CursoLabController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>

                    <h:outputText value="CursoTutorCollection:" />
                    <h:panelGroup>
                        <h:outputText rendered="#{empty cursoLab.cursoLab.cursoTutorCollection}" value="(No Items)"/>
                        <h:dataTable value="#{cursoLab.cursoLab.cursoTutorCollection}" var="item" 
                                     border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                     rendered="#{not empty cursoLab.cursoLab.cursoTutorCollection}">
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
                                    <f:param name="jsfcrud.currentCursoLab" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cursoLab.cursoLab][cursoLab.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentCursoTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][cursoTutor.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="cursoLab" />
                                    <f:param name="jsfcrud.relatedControllerType" value="View.jsf.CursoLabController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Edit" action="#{cursoTutor.editSetup}">
                                    <f:param name="jsfcrud.currentCursoLab" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cursoLab.cursoLab][cursoLab.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentCursoTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][cursoTutor.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="cursoLab" />
                                    <f:param name="jsfcrud.relatedControllerType" value="View.jsf.CursoLabController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Destroy" action="#{cursoTutor.destroy}">
                                    <f:param name="jsfcrud.currentCursoLab" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cursoLab.cursoLab][cursoLab.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentCursoTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][cursoTutor.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="cursoLab" />
                                    <f:param name="jsfcrud.relatedControllerType" value="View.jsf.CursoLabController" />
                                </h:commandLink>
                            </h:column>
                        </h:dataTable>
                    </h:panelGroup>

                </h:panelGrid>
                <br />
                <h:commandLink action="#{cursoLab.remove}" value="Destroy">
                    <f:param name="jsfcrud.currentCursoLab" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cursoLab.cursoLab][cursoLab.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{cursoLab.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentCursoLab" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cursoLab.cursoLab][cursoLab.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{cursoLab.createSetup}" value="New CursoLab" />
                <br />
                <h:commandLink action="#{cursoLab.listSetup}" value="Show All CursoLab Items"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
