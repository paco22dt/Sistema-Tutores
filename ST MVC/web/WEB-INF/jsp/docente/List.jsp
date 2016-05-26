<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing Docente Items</title>
            <link rel="stylesheet" type="text/css" href="/ST_MVC/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing Docente Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No Docente Items Found)<br />" rendered="#{docente.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{docente.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{docente.pagingInfo.firstItem + 1}..#{docente.pagingInfo.lastItem} of #{docente.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{docente.prev}" value="Previous #{docente.pagingInfo.batchSize}" rendered="#{docente.pagingInfo.firstItem >= docente.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{docente.next}" value="Next #{docente.pagingInfo.batchSize}" rendered="#{docente.pagingInfo.lastItem + docente.pagingInfo.batchSize <= docente.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{docente.next}" value="Remaining #{docente.pagingInfo.itemCount - docente.pagingInfo.lastItem}"
                                   rendered="#{docente.pagingInfo.lastItem < docente.pagingInfo.itemCount && docente.pagingInfo.lastItem + docente.pagingInfo.batchSize > docente.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{docente.docenteItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
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
                                <h:outputText value="IdDocente"/>
                            </f:facet>
                            <h:outputText value="#{item.idDocente}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText escape="false" value="&nbsp;"/>
                            </f:facet>
                            <h:commandLink value="Show" action="#{docente.detailSetup}">
                                <f:param name="jsfcrud.currentDocente" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][docente.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{docente.editSetup}">
                                <f:param name="jsfcrud.currentDocente" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][docente.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{docente.remove}">
                                <f:param name="jsfcrud.currentDocente" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][docente.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>

                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{docente.createSetup}" value="New Docente"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />


            </h:form>
        </body>
    </html>
</f:view>
