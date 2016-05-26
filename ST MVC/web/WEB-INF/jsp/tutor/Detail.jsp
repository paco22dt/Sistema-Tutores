<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Tutor Detail</title>
            <link rel="stylesheet" type="text/css" href="/ST_MVC/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Tutor Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="Carnet:"/>
                    <h:outputText value="#{tutor.tutor.carnet}" title="Carnet" />
                    <h:outputText value="Nombre:"/>
                    <h:outputText value="#{tutor.tutor.nombre}" title="Nombre" />
                    <h:outputText value="Apellido:"/>
                    <h:outputText value="#{tutor.tutor.apellido}" title="Apellido" />
                    <h:outputText value="Carrera:"/>
                    <h:outputText value="#{tutor.tutor.carrera}" title="Carrera" />
                    <h:outputText value="Porcentaje:"/>
                    <h:outputText value="#{tutor.tutor.porcentaje}" title="Porcentaje" />
                    <h:outputText value="TipoAyudaEconomica:"/>
                    <h:outputText value="#{tutor.tutor.tipoAyudaEconomica}" title="TipoAyudaEconomica" />
                    <h:outputText value="Correo:"/>
                    <h:outputText value="#{tutor.tutor.correo}" title="Correo" />
                    <h:outputText value="CicloActual:"/>
                    <h:outputText value="#{tutor.tutor.cicloActual}" title="CicloActual" />
                    <h:outputText value="Promedio:"/>
                    <h:outputText value="#{tutor.tutor.promedio}" title="Promedio" />
                    <h:outputText value="Telefono:"/>
                    <h:outputText value="#{tutor.tutor.telefono}" title="Telefono" />

                    <h:outputText value="HistorialTutorCollection:" />
                    <h:panelGroup>
                        <h:outputText rendered="#{empty tutor.tutor.historialTutorCollection}" value="(No Items)"/>
                        <h:dataTable value="#{tutor.tutor.historialTutorCollection}" var="item" 
                                     border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                     rendered="#{not empty tutor.tutor.historialTutorCollection}">
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="IdHistorialTutor"/>
                                </f:facet>
                                <h:outputText value="#{item.idHistorialTutor}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Curso"/>
                                </f:facet>
                                <h:outputText value="#{item.curso}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Seccion"/>
                                </f:facet>
                                <h:outputText value="#{item.seccion}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="NombreDocente"/>
                                </f:facet>
                                <h:outputText value="#{item.nombreDocente}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="ApellidoDocente"/>
                                </f:facet>
                                <h:outputText value="#{item.apellidoDocente}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="NotaEvaluacion"/>
                                </f:facet>
                                <h:outputText value="#{item.notaEvaluacion}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="TUTORcarnet"/>
                                </f:facet>
                                <h:outputText value="#{item.TUTORcarnet}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText escape="false" value="&nbsp;"/>
                                </f:facet>
                                <h:commandLink value="Show" action="#{historialTutor.detailSetup}">
                                    <f:param name="jsfcrud.currentTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tutor.tutor][tutor.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentHistorialTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][historialTutor.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="tutor" />
                                    <f:param name="jsfcrud.relatedControllerType" value="View.jsf.TutorController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Edit" action="#{historialTutor.editSetup}">
                                    <f:param name="jsfcrud.currentTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tutor.tutor][tutor.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentHistorialTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][historialTutor.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="tutor" />
                                    <f:param name="jsfcrud.relatedControllerType" value="View.jsf.TutorController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Destroy" action="#{historialTutor.destroy}">
                                    <f:param name="jsfcrud.currentTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tutor.tutor][tutor.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentHistorialTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][historialTutor.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="tutor" />
                                    <f:param name="jsfcrud.relatedControllerType" value="View.jsf.TutorController" />
                                </h:commandLink>
                            </h:column>
                        </h:dataTable>
                    </h:panelGroup>
                    <h:outputText value="NotaCursoCollection:" />
                    <h:panelGroup>
                        <h:outputText rendered="#{empty tutor.tutor.notaCursoCollection}" value="(No Items)"/>
                        <h:dataTable value="#{tutor.tutor.notaCursoCollection}" var="item" 
                                     border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                     rendered="#{not empty tutor.tutor.notaCursoCollection}">
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
                                    <f:param name="jsfcrud.currentTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tutor.tutor][tutor.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentNotaCurso" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][notaCurso.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="tutor" />
                                    <f:param name="jsfcrud.relatedControllerType" value="View.jsf.TutorController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Edit" action="#{notaCurso.editSetup}">
                                    <f:param name="jsfcrud.currentTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tutor.tutor][tutor.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentNotaCurso" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][notaCurso.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="tutor" />
                                    <f:param name="jsfcrud.relatedControllerType" value="View.jsf.TutorController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Destroy" action="#{notaCurso.destroy}">
                                    <f:param name="jsfcrud.currentTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tutor.tutor][tutor.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentNotaCurso" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][notaCurso.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="tutor" />
                                    <f:param name="jsfcrud.relatedControllerType" value="View.jsf.TutorController" />
                                </h:commandLink>
                            </h:column>
                        </h:dataTable>
                    </h:panelGroup>
                    <h:outputText value="CursoTutorCollection:" />
                    <h:panelGroup>
                        <h:outputText rendered="#{empty tutor.tutor.cursoTutorCollection}" value="(No Items)"/>
                        <h:dataTable value="#{tutor.tutor.cursoTutorCollection}" var="item" 
                                     border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                     rendered="#{not empty tutor.tutor.cursoTutorCollection}">
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
                                    <f:param name="jsfcrud.currentTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tutor.tutor][tutor.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentCursoTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][cursoTutor.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="tutor" />
                                    <f:param name="jsfcrud.relatedControllerType" value="View.jsf.TutorController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Edit" action="#{cursoTutor.editSetup}">
                                    <f:param name="jsfcrud.currentTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tutor.tutor][tutor.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentCursoTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][cursoTutor.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="tutor" />
                                    <f:param name="jsfcrud.relatedControllerType" value="View.jsf.TutorController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Destroy" action="#{cursoTutor.destroy}">
                                    <f:param name="jsfcrud.currentTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tutor.tutor][tutor.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentCursoTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][cursoTutor.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="tutor" />
                                    <f:param name="jsfcrud.relatedControllerType" value="View.jsf.TutorController" />
                                </h:commandLink>
                            </h:column>
                        </h:dataTable>
                    </h:panelGroup>

                </h:panelGrid>
                <br />
                <h:commandLink action="#{tutor.remove}" value="Destroy">
                    <f:param name="jsfcrud.currentTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tutor.tutor][tutor.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{tutor.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tutor.tutor][tutor.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{tutor.createSetup}" value="New Tutor" />
                <br />
                <h:commandLink action="#{tutor.listSetup}" value="Show All Tutor Items"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
