<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing CursoTe Items</title>
            <link rel="stylesheet" type="text/css" href="/ST_MVC/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing CursoTe Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No CursoTe Items Found)<br />" rendered="#{cursoTe.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{cursoTe.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{cursoTe.pagingInfo.firstItem + 1}..#{cursoTe.pagingInfo.lastItem} of #{cursoTe.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{cursoTe.prev}" value="Previous #{cursoTe.pagingInfo.batchSize}" rendered="#{cursoTe.pagingInfo.firstItem >= cursoTe.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{cursoTe.next}" value="Next #{cursoTe.pagingInfo.batchSize}" rendered="#{cursoTe.pagingInfo.lastItem + cursoTe.pagingInfo.batchSize <= cursoTe.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{cursoTe.next}" value="Remaining #{cursoTe.pagingInfo.itemCount - cursoTe.pagingInfo.lastItem}"
                                   rendered="#{cursoTe.pagingInfo.lastItem < cursoTe.pagingInfo.itemCount && cursoTe.pagingInfo.lastItem + cursoTe.pagingInfo.batchSize > cursoTe.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{cursoTe.cursoTeItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="IdCurso"/>
                            </f:facet>
                            <h:outputText value="#{item.idCurso}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Nombre"/>
                            </f:facet>
                            <h:outputText value="#{item.nombre}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Seccion"/>
                            </f:facet>
                            <h:outputText value="#{item.seccion}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText escape="false" value="&nbsp;"/>
                            </f:facet>
                            <h:commandLink value="Show" action="#{cursoTe.detailSetup}">
                                <f:param name="jsfcrud.currentCursoTe" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][cursoTe.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{cursoTe.editSetup}">
                                <f:param name="jsfcrud.currentCursoTe" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][cursoTe.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{cursoTe.remove}">
                                <f:param name="jsfcrud.currentCursoTe" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][cursoTe.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>

                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{cursoTe.createSetup}" value="New CursoTe"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />


            </h:form>
        </body>
    </html>
</f:view>
