<%@page contentType="text/html" pageEncoding= "UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SITE</title>
        <jsp:include page="style.jsp" />
    </head>
    <body>
        <div id="principal" class="container">
            <jsp:include page="menu.jsp" />
            <div class="row">

                <div class="col-lg-8">


                    <div class="card">
                        <div class="card-header bg-primary">LISTE DES SITES</div>
                        <div class="card-body">
                            <table class="table table-bordered table-striped table-hover">
                                <tr>                            
                                    <th>Nom du Site</th>
                                    <th>Nom du Gestionnaire</th>
                                    <th>Coordonnées du Gestionnaire</th>
                                    <th colspan="1">Action</th>
                                </tr>
                                <c:forEach items="${sites}" var="s">
                                    <tr>
                                        <td><c:out value="${s.nomSite}" /></td>
                                        <td><c:out value="${s.ges.prenom}" /> <c:out value="${s.ges.nom}" /></td>
                                        <td><i class="fa fa-address-card fa-2x"></i> : <c:out value="${s.ges.adr}" />
                                            <br/><i class="fa fa-phone fa-2x"></i> : <c:out value="${s.ges.tel}" /></td>

                                        <td><a class="btn btn-primary" href="controleursite?action=admin_modifier&idsite=${s.sit}"><i class="fa fa-edit fa-2x"></i></a></td>

                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>


                </div>
                <div class="col-lg-4">
                    <div class="card">
                        <div class="card-header bg-primary">NOUVEL SITE</div>
                        <div class="card-body">
                            <form action="controleursite" method="post">
                                <input type="hidden" name="action" value="${objet != null ? objet : 'ajouter'}" />
                                <input type="hidden" name="site" value="${site.sit}" />
                                <div class="form-group">
                                    <label for="site">NOM DE SITE</label>
                                    <input type="text" name="nomSite" value="${site.nomSite}" class="form-control" id="site" placeholder="Entrer le nom du site">
                                </div>
                                <div class="form-group">
                                    <label for="ges">GESTIONNAIRE</label>
                                    <select name="gestion" class="form-control">
                                        <option></option>
                                        <c:forEach items="${gestions}" var="g">
                                            <option value="${g.ges}">${g.prenom} ${g.nom}</option>
                                        </c:forEach>

                                    </select>
                                </div>

                                <button type="submit" class="btn btn-primary">${objet != null ? 'Modifier' : 'Ajouter'}</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>


