
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>UTILISATEUR</title>
        <jsp:include page="style.jsp" />
    </head>
    <body>
        <div id="principal" class="container">
            <jsp:include page="menu.jsp" />
            <div class="row">

                <div class="col-lg-8">


                    <div class="card">
                        <div class="card-header bg-primary">LISTE DES UTILISTATEURS</div>
                        <div class="card-body table-responsive">
                            <table class="table table-bordered table-striped table-hover">
                                <tr>                            
                                    <th>PRENOM</th>
                                    <th>NOM</th>
                                    <th>ADRESSE</th>
                                    <th>TELEPHONE</th>
                                    <th>LOGIN</th>
                                    <th>UFR</th>
                                    <th>DEPARTEMENT</th>
                                    <th>PROFIL</th>
                                    <th colspan="1">Action</th>
                                </tr>
                                <c:forEach items="${users}" var="us">
                                    <tr>
                                        <td><c:out value="${us.prenom}" /></td>
                                        <td><c:out value="${us.nom}" /></td>
                                        <td><c:out value="${us.adresse}" /></td>
                                        <td><c:out value="${us.tel}" /></td>
                                        <td><c:out value="${us.login}" /></td>
                                        <td><c:out value="${us.ufr.nomUfr}" /></td>
                                        <td><c:out value="${us.dept.nomDept}" /></td>
                                        <td><c:out value="${us.profil}" /></td>
                                        <td><a class="btn btn-primary" href="controleurutilisateur?action=admin_modifier&iduser=${us.idUser}"><i class="fa fa-edit fa-2x"></i></a></td>

                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>


                </div>
                <div class="col-lg-4">
                    <div class="card">
                        <div class="card-header bg-primary">NOUVEL UTILISATEUR</div>
                        <div class="card-body">
                            <form action="controleurutilisateur" method="post">

                                <input type="hidden" name="action" value="${objet != null ? objet : 'ajouter'}" />
                                <input type="hidden" name="idUser" value="${user.idUser}" />

                                <div class="form-group">
                                    <label for="user">PRENOM</label>
                                    <input type="text" name="prenom" value="${user.prenom}" class="form-control" id="user" placeholder="Entrer le prénom de l'utilisateur">
                                </div>
                                <div class="form-group">
                                    <label for="user">NOM</label>
                                    <input type="text" name="nom" value="${user.nom}" class="form-control" id="user" placeholder="Entrer le nom de l'utilisateur">
                                </div>
                                <div class="form-group">
                                    <label for="user">ADRESSE</label>
                                    <input type="text" name="adresse" value="${user.adresse}" class="form-control" id="user" placeholder="Entrer l'adresse de l'utilisateur">
                                </div>
                                <div class="form-group">
                                    <label for="user">TELEPHONE</label>
                                    <input type="text" name="tel" value="${user.tel}" class="form-control" id="user" placeholder="Entrer le numéro de téléphone de l'utilisateur">
                                </div>
                                <div class="form-group">
                                    <label for="user">LOGIN</label>
                                    <input type="email" name="email" value="${user.login}" class="form-control" id="user" placeholder="Entrer le login de l'utilisateur">

                                </div>
                                <div class="form-group">
                                    <label for="ufr">UFR</label>
                                    <select name="ufr" class="form-control" id="ufr">
                                        <option value="${user.ufr.idUfr}">${user.ufr.nomUfr}</option>
                                        <c:forEach items="${ufrs}" var="u">
                                            <option value="${u.idUfr}">${u.nomUfr}</option>
                                        </c:forEach>

                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="dept">Département</label>
                                    <select name="dept" class="form-control" id="dept">
                                        <option value="${user.dept.idDept}">${user.dept.nomDept}</option>
                                        <c:forEach items="${depts}" var="d">
                                            <option value="${d.idDept}">${d.nomDept}</option>
                                        </c:forEach>

                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="profil">PROFIL</label>
                                    <select name="profil" class="form-control" id="profil">
                                        <option></option>
                                        <option value="Missionnaire">Missionnaire</option>
                                        <option value="Vacataire">Vacataire</option>
                                        <option value="Responsable">Responsable</option>
                                        <option value="Chef_de_departement">Chef de département</option>
                                        <option value="Directeur_UFR">Directeur d'UFR</option>
                                        <option value="Vice_recteur">Vice-recteur</option>
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


