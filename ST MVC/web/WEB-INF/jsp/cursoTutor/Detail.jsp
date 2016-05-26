<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>CursoTutor Detail</title>
            <link rel="stylesheet" type="text/css" href="/ST_MVC/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>CursoTutor Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="IdCursoTutor:"/>
                    <h:outputText value="#{cursoTutor.cursoTutor.idCursoTutor}" title="IdCursoTutor" />
                    <h:outputText value="TipoCurso:"/>
                    <h:outputText value="#{cursoTutor.cursoTutor.tipoCurso}" title="TipoCurso" />
                    <h:outputText value="Año:"/>
                    <h:outputText value="#{cursoTutor.cursoTutor.año}" title="Año" />
                    <h:outputText value="Ciclo:"/>
                    <h:outputText value="#{cursoTutor.cursoTutor.ciclo}" title="Ciclo" />
                    <h:outputText value="TUTORcarnet:"/>
                    <h:panelGroup>
                        <h:outputText value="#{cursoTutor.cursoTutor.TUTORcarnet}"/>
                        <h:panelGroup rendered="#{cursoTutor.cursoTutor.TUTORcarnet != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{tutor.detailSetup}">
                                <f:param name="jsfcrud.currentCursoTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cursoTutor.cursoTutor][cursoTutor.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cursoTutor.cursoTutor.TUTORcarnet][tutor.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="cursoTutor"/>
                                <f:param name="jsfcrud.relatedControllerType" value="View.jsf.CursoTutorController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{tutor.editSetup}">
                                <f:param name="jsfcrud.currentCursoTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cursoTutor.cursoTutor][cursoTutor.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cursoTutor.cursoTutor.TUTORcarnet][tutor.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="cursoTutor"/>
                                <f:param name="jsfcrud.relatedControllerType" value="View.jsf.CursoTutorController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{tutor.destroy}">
                                <f:param name="jsfcrud.currentCursoTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cursoTutor.cursoTutor][cursoTutor.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cursoTutor.cursoTutor.TUTORcarnet][tutor.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="cursoTutor"/>
                                <f:param name="jsfcrud.relatedControllerType" value="View.jsf.CursoTutorController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>
                    <h:outputText value="DOCENTEiddocente:"/>
                    <h:panelGroup>
                        <h:outputText value="#{cursoTutor.cursoTutor.DOCENTEiddocente}"/>
                        <h:panelGroup rendered="#{cursoTutor.cursoTutor.DOCENTEiddocente != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{docente.detailSetup}">
                                <f:param name="jsfcrud.currentCursoTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cursoTutor.cursoTutor][cursoTutor.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentDocente" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cursoTutor.cursoTutor.DOCENTEiddocente][docente.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="cursoTutor"/>
                                <f:param name="jsfcrud.relatedControllerType" value="View.jsf.CursoTutorController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{docente.editSetup}">
                                <f:param name="jsfcrud.currentCursoTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cursoTutor.cursoTutor][cursoTutor.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentDocente" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cursoTutor.cursoTutor.DOCENTEiddocente][docente.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="cursoTutor"/>
                                <f:param name="jsfcrud.relatedControllerType" value="View.jsf.CursoTutorController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{docente.destroy}">
                                <f:param name="jsfcrud.currentCursoTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cursoTutor.cursoTutor][cursoTutor.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentDocente" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cursoTutor.cursoTutor.DOCENTEiddocente][docente.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="cursoTutor"/>
                                <f:param name="jsfcrud.relatedControllerType" value="View.jsf.CursoTutorController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>
                    <h:outputText value="CURSOLABseccion:"/>
                    <h:panelGroup>
                        <h:outputText value="#{cursoTutor.cursoTutor.CURSOLABseccion}"/>
                        <h:panelGroup rendered="#{cursoTutor.cursoTutor.CURSOLABseccion != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{cursoLab.detailSetup}">
                                <f:param name="jsfcrud.currentCursoTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cursoTutor.cursoTutor][cursoTutor.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentCursoLab" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cursoTutor.cursoTutor.CURSOLABseccion][cursoLab.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="cursoTutor"/>
                                <f:param name="jsfcrud.relatedControllerType" value="View.jsf.CursoTutorController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{cursoLab.editSetup}">
                                <f:param name="jsfcrud.currentCursoTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cursoTutor.cursoTutor][cursoTutor.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentCursoLab" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cursoTutor.cursoTutor.CURSOLABseccion][cursoLab.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="cursoTutor"/>
                                <f:param name="jsfcrud.relatedControllerType" value="View.jsf.CursoTutorController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{cursoLab.destroy}">
                                <f:param name="jsfcrud.currentCursoTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cursoTutor.cursoTutor][cursoTutor.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentCursoLab" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cursoTutor.cursoTutor.CURSOLABseccion][cursoLab.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="cursoTutor"/>
                                <f:param name="jsfcrud.relatedControllerType" value="View.jsf.CursoTutorController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>

                    <h:outputText value="PeriodoCollection:" />
                    <h:panelGroup>
                        <h:outputText rendered="#{empty cursoTutor.cursoTutor.periodoCollection}" value="(No Items)"/>
                        <h:dataTable value="#{cursoTutor.cursoTutor.periodoCollection}" var="item" 
                                     border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                     rendered="#{not empty cursoTutor.cursoTutor.periodoCollection}">
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="IdPeriodo"/>
                                </f:facet>
                                <h:outputText value="#{item.idPeriodo}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="FechaInicio"/>
                                </f:facet>
                                <h:outputText value="#{item.fechaInicio}">
                                    <f:convertDateTime pattern="MM/dd/yyyy" />
                                </h:outputText>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="FechaFin"/>
                                </f:facet>
                                <h:outputText value="#{item.fechaFin}">
                                    <f:convertDateTime pattern="MM/dd/yyyy" />
                                </h:outputText>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="CURSOTUTORidcursotutor"/>
                                </f:facet>
                                <h:outputText value="#{item.CURSOTUTORidcursotutor}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText escape="false" value="&nbsp;"/>
                                </f:facet>
                                <h:commandLink value="Show" action="#{periodo.detailSetup}">
                                    <f:param name="jsfcrud.currentCursoTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cursoTutor.cursoTutor][cursoTutor.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentPeriodo" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][periodo.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="cursoTutor" />
                                    <f:param name="jsfcrud.relatedControllerType" value="View.jsf.CursoTutorController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Edit" action="#{periodo.editSetup}">
                                    <f:param name="jsfcrud.currentCursoTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cursoTutor.cursoTutor][cursoTutor.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentPeriodo" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][periodo.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="cursoTutor" />
                                    <f:param name="jsfcrud.relatedControllerType" value="View.jsf.CursoTutorController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Destroy" action="#{periodo.destroy}">
                                    <f:param name="jsfcrud.currentCursoTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cursoTutor.cursoTutor][cursoTutor.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentPeriodo" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][periodo.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="cursoTutor" />
                                    <f:param name="jsfcrud.relatedControllerType" value="View.jsf.CursoTutorController" />
                                </h:commandLink>
                            </h:column>
                        </h:dataTable>
                    </h:panelGroup>
                    <h:outputText value="CursoLabCollection:" />
                    <h:panelGroup>
                        <h:outputText rendered="#{empty cursoTutor.cursoTutor.cursoLabCollection}" value="(No Items)"/>
                        <h:dataTable value="#{cursoTutor.cursoTutor.cursoLabCollection}" var="item" 
                                     border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                     rendered="#{not empty cursoTutor.cursoTutor.cursoLabCollection}">
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Seccion"/>
                                </f:facet>
                                <h:outputText value="#{item.seccion}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="CURSOTEidcurso"/>
                                </f:facet>
                                <h:outputText value="#{item.CURSOTEidcurso}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText escape="false" value="&nbsp;"/>
                                </f:facet>
                                <h:commandLink value="Show" action="#{cursoLab.detailSetup}">
                                    <f:param name="jsfcrud.currentCursoTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cursoTutor.cursoTutor][cursoTutor.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentCursoLab" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][cursoLab.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="cursoTutor" />
                                    <f:param name="jsfcrud.relatedControllerType" value="View.jsf.CursoTutorController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Edit" action="#{cursoLab.editSetup}">
                                    <f:param name="jsfcrud.currentCursoTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cursoTutor.cursoTutor][cursoTutor.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentCursoLab" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][cursoLab.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="cursoTutor" />
                                    <f:param name="jsfcrud.relatedControllerType" value="View.jsf.CursoTutorController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Destroy" action="#{cursoLab.destroy}">
                                    <f:param name="jsfcrud.currentCursoTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cursoTutor.cursoTutor][cursoTutor.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentCursoLab" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][cursoLab.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="cursoTutor" />
                                    <f:param name="jsfcrud.relatedControllerType" value="View.jsf.CursoTutorController" />
                                </h:commandLink>
                            </h:column>
                        </h:dataTable>
                    </h:panelGroup>

                </h:panelGrid>
                <br />
                <h:commandLink action="#{cursoTutor.remove}" value="Destroy">
                    <f:param name="jsfcrud.currentCursoTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cursoTutor.cursoTutor][cursoTutor.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{cursoTutor.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentCursoTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cursoTutor.cursoTutor][cursoTutor.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{cursoTutor.createSetup}" value="New CursoTutor" />
                <br />
                <h:commandLink action="#{cursoTutor.listSetup}" value="Show All CursoTutor Items"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
