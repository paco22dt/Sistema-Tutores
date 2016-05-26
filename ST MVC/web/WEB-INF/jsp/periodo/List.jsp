<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing Periodo Items</title>
            <link rel="stylesheet" type="text/css" href="/ST_MVC/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing Periodo Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No Periodo Items Found)<br />" rendered="#{periodo.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{periodo.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{periodo.pagingInfo.firstItem + 1}..#{periodo.pagingInfo.lastItem} of #{periodo.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{periodo.prev}" value="Previous #{periodo.pagingInfo.batchSize}" rendered="#{periodo.pagingInfo.firstItem >= periodo.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{periodo.next}" value="Next #{periodo.pagingInfo.batchSize}" rendered="#{periodo.pagingInfo.lastItem + periodo.pagingInfo.batchSize <= periodo.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{periodo.next}" value="Remaining #{periodo.pagingInfo.itemCount - periodo.pagingInfo.lastItem}"
                                   rendered="#{periodo.pagingInfo.lastItem < periodo.pagingInfo.itemCount && periodo.pagingInfo.lastItem + periodo.pagingInfo.batchSize > periodo.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{periodo.periodoItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
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
                                <f:param name="jsfcrud.currentPeriodo" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][periodo.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{periodo.editSetup}">
                                <f:param name="jsfcrud.currentPeriodo" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][periodo.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{periodo.remove}">
                                <f:param name="jsfcrud.currentPeriodo" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][periodo.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>

                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{periodo.createSetup}" value="New Periodo"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />


            </h:form>
        </body>
    </html>
</f:view>
