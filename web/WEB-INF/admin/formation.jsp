<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FORMATION</title>
        <jsp:include page="style.jsp" />
    </head>
    <body>
        <div id="principal" class="container">
            <jsp:include page="menu.jsp" />
            <div class="row">

                <div class="col-lg-8">


                    <div class="card">
                        <div class="card-header bg-primary">LISTE DES FORMATIONS</div>
                        <div class="card-body">
                            <table class="table table-bordered table-striped table-hover">
                                <tr>                            
                                    <th>Nom de la Formation</th>
                                    <th>Nom du Département</th>
                                    <th colspan="1">Action</th>
                                </tr>
                                <c:forEach items="${forms}" var="f">
                                    <tr>
                                        <td><c:out value="${f.nomForm}" /></td>
                                        <td><c:out value="${f.dept.nomDept}" /></td>
                                        <td><a class="btn btn-primary" href="controleurformation?action=admin_modifier&idform=${f.idForm}"><i class="fa fa-edit fa-2x"></i></a></td>

                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>


                </div>
                <div class="col-lg-4">
                    <div class="card">
                        <div class="card-header bg-primary">NOUVELLE FORMATION</div>
                        <div class="card-body">
                            <form action="controleurformation" method="post">
                                
                                <input type="hidden" name="action" value="${objet != null ? objet : 'ajouter'}" />
                                <input type="hidden" name="idform" value="${form.idForm}" />

                                <div class="form-group">
                                    <label for="form">Nom de la Formation</label>
                                    <input type="text" name="nomForm" value="${form.nomForm}" class="form-control" id="form" placeholder="Entrer le nom de Formation">
                                </div>
                                <div class="form-group">
                                    <label for="dept">Nom du Département</label>
                                    <select name="dept" class="form-control" id="dept">
                                        <option></option>
                                        <c:forEach items="${depts}" var="d">
                                            <option value="${d.idDept}">${d.nomDept}</option>
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


