<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing NotaCurso Items</title>
            <link rel="stylesheet" type="text/css" href="/ST_MVC/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing NotaCurso Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No NotaCurso Items Found)<br />" rendered="#{notaCurso.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{notaCurso.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{notaCurso.pagingInfo.firstItem + 1}..#{notaCurso.pagingInfo.lastItem} of #{notaCurso.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{notaCurso.prev}" value="Previous #{notaCurso.pagingInfo.batchSize}" rendered="#{notaCurso.pagingInfo.firstItem >= notaCurso.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{notaCurso.next}" value="Next #{notaCurso.pagingInfo.batchSize}" rendered="#{notaCurso.pagingInfo.lastItem + notaCurso.pagingInfo.batchSize <= notaCurso.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{notaCurso.next}" value="Remaining #{notaCurso.pagingInfo.itemCount - notaCurso.pagingInfo.lastItem}"
                                   rendered="#{notaCurso.pagingInfo.lastItem < notaCurso.pagingInfo.itemCount && notaCurso.pagingInfo.lastItem + notaCurso.pagingInfo.batchSize > notaCurso.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{notaCurso.notaCursoItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
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
                                <f:param name="jsfcrud.currentNotaCurso" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][notaCurso.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{notaCurso.editSetup}">
                                <f:param name="jsfcrud.currentNotaCurso" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][notaCurso.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{notaCurso.remove}">
                                <f:param name="jsfcrud.currentNotaCurso" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][notaCurso.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>

                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{notaCurso.createSetup}" value="New NotaCurso"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />


            </h:form>
        </body>
    </html>
</f:view>
