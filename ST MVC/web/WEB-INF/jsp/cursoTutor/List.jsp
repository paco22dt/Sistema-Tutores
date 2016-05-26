<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing CursoTutor Items</title>
            <link rel="stylesheet" type="text/css" href="/ST_MVC/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing CursoTutor Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No CursoTutor Items Found)<br />" rendered="#{cursoTutor.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{cursoTutor.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{cursoTutor.pagingInfo.firstItem + 1}..#{cursoTutor.pagingInfo.lastItem} of #{cursoTutor.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{cursoTutor.prev}" value="Previous #{cursoTutor.pagingInfo.batchSize}" rendered="#{cursoTutor.pagingInfo.firstItem >= cursoTutor.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{cursoTutor.next}" value="Next #{cursoTutor.pagingInfo.batchSize}" rendered="#{cursoTutor.pagingInfo.lastItem + cursoTutor.pagingInfo.batchSize <= cursoTutor.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{cursoTutor.next}" value="Remaining #{cursoTutor.pagingInfo.itemCount - cursoTutor.pagingInfo.lastItem}"
                                   rendered="#{cursoTutor.pagingInfo.lastItem < cursoTutor.pagingInfo.itemCount && cursoTutor.pagingInfo.lastItem + cursoTutor.pagingInfo.batchSize > cursoTutor.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{cursoTutor.cursoTutorItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
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
                                <f:param name="jsfcrud.currentCursoTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][cursoTutor.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{cursoTutor.editSetup}">
                                <f:param name="jsfcrud.currentCursoTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][cursoTutor.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{cursoTutor.remove}">
                                <f:param name="jsfcrud.currentCursoTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][cursoTutor.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>

                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{cursoTutor.createSetup}" value="New CursoTutor"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />


            </h:form>
        </body>
    </html>
</f:view>
