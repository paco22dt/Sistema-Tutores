<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing CursoLab Items</title>
            <link rel="stylesheet" type="text/css" href="/ST_MVC/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing CursoLab Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No CursoLab Items Found)<br />" rendered="#{cursoLab.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{cursoLab.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{cursoLab.pagingInfo.firstItem + 1}..#{cursoLab.pagingInfo.lastItem} of #{cursoLab.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{cursoLab.prev}" value="Previous #{cursoLab.pagingInfo.batchSize}" rendered="#{cursoLab.pagingInfo.firstItem >= cursoLab.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{cursoLab.next}" value="Next #{cursoLab.pagingInfo.batchSize}" rendered="#{cursoLab.pagingInfo.lastItem + cursoLab.pagingInfo.batchSize <= cursoLab.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{cursoLab.next}" value="Remaining #{cursoLab.pagingInfo.itemCount - cursoLab.pagingInfo.lastItem}"
                                   rendered="#{cursoLab.pagingInfo.lastItem < cursoLab.pagingInfo.itemCount && cursoLab.pagingInfo.lastItem + cursoLab.pagingInfo.batchSize > cursoLab.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{cursoLab.cursoLabItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
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
                                <f:param name="jsfcrud.currentCursoLab" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][cursoLab.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{cursoLab.editSetup}">
                                <f:param name="jsfcrud.currentCursoLab" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][cursoLab.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{cursoLab.remove}">
                                <f:param name="jsfcrud.currentCursoLab" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][cursoLab.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>

                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{cursoLab.createSetup}" value="New CursoLab"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />


            </h:form>
        </body>
    </html>
</f:view>
