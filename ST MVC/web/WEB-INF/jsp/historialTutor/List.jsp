<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing HistorialTutor Items</title>
            <link rel="stylesheet" type="text/css" href="/ST_MVC/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing HistorialTutor Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No HistorialTutor Items Found)<br />" rendered="#{historialTutor.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{historialTutor.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{historialTutor.pagingInfo.firstItem + 1}..#{historialTutor.pagingInfo.lastItem} of #{historialTutor.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{historialTutor.prev}" value="Previous #{historialTutor.pagingInfo.batchSize}" rendered="#{historialTutor.pagingInfo.firstItem >= historialTutor.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{historialTutor.next}" value="Next #{historialTutor.pagingInfo.batchSize}" rendered="#{historialTutor.pagingInfo.lastItem + historialTutor.pagingInfo.batchSize <= historialTutor.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{historialTutor.next}" value="Remaining #{historialTutor.pagingInfo.itemCount - historialTutor.pagingInfo.lastItem}"
                                   rendered="#{historialTutor.pagingInfo.lastItem < historialTutor.pagingInfo.itemCount && historialTutor.pagingInfo.lastItem + historialTutor.pagingInfo.batchSize > historialTutor.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{historialTutor.historialTutorItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
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
                                <f:param name="jsfcrud.currentHistorialTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][historialTutor.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{historialTutor.editSetup}">
                                <f:param name="jsfcrud.currentHistorialTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][historialTutor.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{historialTutor.remove}">
                                <f:param name="jsfcrud.currentHistorialTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][historialTutor.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>

                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{historialTutor.createSetup}" value="New HistorialTutor"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />


            </h:form>
        </body>
    </html>
</f:view>
