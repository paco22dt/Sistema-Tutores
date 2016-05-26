<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing Tutor Items</title>
            <link rel="stylesheet" type="text/css" href="/ST_MVC/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing Tutor Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No Tutor Items Found)<br />" rendered="#{tutor.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{tutor.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{tutor.pagingInfo.firstItem + 1}..#{tutor.pagingInfo.lastItem} of #{tutor.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{tutor.prev}" value="Previous #{tutor.pagingInfo.batchSize}" rendered="#{tutor.pagingInfo.firstItem >= tutor.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{tutor.next}" value="Next #{tutor.pagingInfo.batchSize}" rendered="#{tutor.pagingInfo.lastItem + tutor.pagingInfo.batchSize <= tutor.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{tutor.next}" value="Remaining #{tutor.pagingInfo.itemCount - tutor.pagingInfo.lastItem}"
                                   rendered="#{tutor.pagingInfo.lastItem < tutor.pagingInfo.itemCount && tutor.pagingInfo.lastItem + tutor.pagingInfo.batchSize > tutor.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{tutor.tutorItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Carnet"/>
                            </f:facet>
                            <h:outputText value="#{item.carnet}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Nombre"/>
                            </f:facet>
                            <h:outputText value="#{item.nombre}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Apellido"/>
                            </f:facet>
                            <h:outputText value="#{item.apellido}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Carrera"/>
                            </f:facet>
                            <h:outputText value="#{item.carrera}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Porcentaje"/>
                            </f:facet>
                            <h:outputText value="#{item.porcentaje}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="TipoAyudaEconomica"/>
                            </f:facet>
                            <h:outputText value="#{item.tipoAyudaEconomica}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Correo"/>
                            </f:facet>
                            <h:outputText value="#{item.correo}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="CicloActual"/>
                            </f:facet>
                            <h:outputText value="#{item.cicloActual}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Promedio"/>
                            </f:facet>
                            <h:outputText value="#{item.promedio}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Telefono"/>
                            </f:facet>
                            <h:outputText value="#{item.telefono}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText escape="false" value="&nbsp;"/>
                            </f:facet>
                            <h:commandLink value="Show" action="#{tutor.detailSetup}">
                                <f:param name="jsfcrud.currentTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][tutor.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{tutor.editSetup}">
                                <f:param name="jsfcrud.currentTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][tutor.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{tutor.remove}">
                                <f:param name="jsfcrud.currentTutor" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][tutor.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>

                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{tutor.createSetup}" value="New Tutor"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />


            </h:form>
        </body>
    </html>
</f:view>
