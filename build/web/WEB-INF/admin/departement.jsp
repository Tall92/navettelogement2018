<%@page contentType="text/html" pageEncoding= "UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>UFR</title>
        <jsp:include page="style.jsp" />
    </head>
    <body>
        <div id="principal" class="container">
            <jsp:include page="menu.jsp" />
            <div class="row">

                <div class="col-lg-8">


                    <div class="card">
                        <div class="card-header bg-primary">LISTE DES DEPARTEMENTS</div>
                        <div class="card-body">
                            <table class="table table-bordered table-striped table-hover">
                                <tr>                            
                                    <th>Nom du Département</th>
                                    <th>Nom de l'UFR</th>
                                    <th colspan="1">Action</th>
                                </tr>
                                <c:forEach items="${depts}" var="d">
                                    <tr>
                                        <td><c:out value="${d.nomDept}" /></td>
                                        <td><c:out value="${d.ufr.nomUfr}" /></td>
                                        <td><a class="btn btn-primary" href="controleurdept?action=admin_modifier&iddep=${d.idDept}"><i class="fa fa-edit fa-2x"></i></a></td>

                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>


                </div>
                <div class="col-lg-4">
                    <div class="card">
                        <div class="card-header bg-primary">NOUVELLE UFR</div>
                        <div class="card-body">
                            <form action="controleurdept" method="post">
                                <input type="hidden" name="action" value="${objet != null ? objet : 'ajouter'}" />
                                <input type="hidden" name="iddep" value="${dept.idDept}" />
                                <div class="form-group">
                                    <label for="dept">NOM DE DEPARTEMENT</label>
                                    <input type="text" name="nomDep" value="${dept.nomDept}" class="form-control" id="dept" placeholder="Entrer le nom de Departement">
                                </div>
                                <div class="form-group">
                                    <label for="ufr">UFR</label>
                                    <select name="ufr" class="form-control">
                                        <option></option>
                                        <c:forEach items="${ufrs}" var="u">
                                            <option value="${u.idUfr}">${u.nomUfr}</option>
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

