<%@page contentType="text/html" pageEncoding= "UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>NAVETTES</title>
        <jsp:include page="style.jsp" />
    </head>
    <body>
        <div id="principal" class="container">
            <jsp:include page="menu.jsp" />
            <div class="row">

                <div class="col-lg-8">


                    <div class="card">
                        <div class="card-header bg-primary">LISTE DES NAVETTES</div>
                        <div class="card-body">
                            <table class="table table-bordered table-striped table-hover">
                                <tr>                            
                                    <th>MATRICULE</th>
                                    <th>NOMBRES DE PLACE</th>
                                    <th colspan="1">Action</th>
                                </tr>
                                <c:forEach items="${navettes}" var="n">
                                    <tr>
                                        <td><c:out value="${n.matricule}" /></td>
                                        <td><c:out value="${n.nbPlace}" /></td>
                                        
                                        <td><a class="btn btn-primary" href="controleurnavette?action=admin_modifier&idnav=${n.idNav}"><i class="fa fa-edit fa-2x"></i></a></td>

                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>


                </div>
                <div class="col-lg-4">
                    <div class="card">
                        <div class="card-header bg-primary">NOUVELLE NAVETTE</div>
                        <div class="card-body">
                            <form action="controleurnavette" method="post">
                                <input type="hidden" name="action" value="${objet != null ? objet : 'ajouter'}" />
                                <input type="hidden" name="nav" value="${nav.idNav}" />
                                <div class="form-group">
                                    <label for="matricule">Numéro de Matricule</label>
                                    <input type="text" name="matricule" value="${nav.matricule}" class="form-control" id="matricule" placeholder="Entrer le numéro du matricule">
                                </div>
                                <div class="form-group">
                                    <label for="place">Nombres de Place</label>
                                    <input type="text" name="place" value="${nav.nbPlace}" class="form-control" id="place" placeholder="Entrer le nombre de place">
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



