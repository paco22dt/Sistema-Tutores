<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing Users Items</title>
            <link rel="stylesheet" type="text/css" href="/ST_MVC/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing Users Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No Users Items Found)<br />" rendered="#{users.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{users.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{users.pagingInfo.firstItem + 1}..#{users.pagingInfo.lastItem} of #{users.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{users.prev}" value="Previous #{users.pagingInfo.batchSize}" rendered="#{users.pagingInfo.firstItem >= users.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{users.next}" value="Next #{users.pagingInfo.batchSize}" rendered="#{users.pagingInfo.lastItem + users.pagingInfo.batchSize <= users.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{users.next}" value="Remaining #{users.pagingInfo.itemCount - users.pagingInfo.lastItem}"
                                   rendered="#{users.pagingInfo.lastItem < users.pagingInfo.itemCount && users.pagingInfo.lastItem + users.pagingInfo.batchSize > users.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{users.usersItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="IdUser"/>
                            </f:facet>
                            <h:outputText value="#{item.idUser}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Password"/>
                            </f:facet>
                            <h:outputText value="#{item.password}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText escape="false" value="&nbsp;"/>
                            </f:facet>
                            <h:commandLink value="Show" action="#{users.detailSetup}">
                                <f:param name="jsfcrud.currentUsers" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][users.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{users.editSetup}">
                                <f:param name="jsfcrud.currentUsers" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][users.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{users.remove}">
                                <f:param name="jsfcrud.currentUsers" value="#{jsfcrud_class['View.jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][users.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>

                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{users.createSetup}" value="New Users"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
            </h:form>
        </body>
    </html>
</f:view>
