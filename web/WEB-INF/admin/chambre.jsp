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
                        <div class="card-header bg-primary">LISTE DES CHAMBRES</div>
                        <div class="card-body">
                            <table class="table table-bordered table-striped table-hover">
                                <tr>                            
                                    <th>Numéro du Chambre</th>
                                    <th>Nom du Site</th>
                                    <th colspan="1">Action</th>
                                </tr>
                                <c:forEach items="${chambres}" var="c">
                                    <tr>
                                        <td><c:out value="${c.numero}" /></td>
                                        <td><c:out value="${c.site.nomSite}" /></td>
                                        
                                        <td><a class="btn btn-primary" href="controleurchambre?action=admin_modifier&idcham=${c.idCh}"><i class="fa fa-edit fa-2x"></i></a></td>

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
                            <form action="controleurchambre" method="post">
                                <input type="hidden" name="action" value="${objet != null ? objet : 'ajouter'}" />
                                <input type="hidden" name="site" value="${ch.idCh}" />
                                <div class="form-group">
                                    <label for="cham">Numéro de la chambre</label>
                                    <input type="text" name="numero" value="${site.nomSite}" class="form-control" id="cham" placeholder="Entrer le nom du site">
                                </div>
                                <div class="form-group">
                                    <label for="ges">SITE</label>
                                    <select name="nomsite" class="form-control">
                                        <option></option>
                                        <c:forEach items="${sites}" var="s">
                                            <option value="${s.sit}">${s.nomSite}</option>
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


