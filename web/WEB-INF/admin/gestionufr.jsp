<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>GESTIONNAIRE</title>
        <link rel="stylesheet" href="bootstrap-4.1.2/css/bootstrap.min.css" />
        <link rel="stylesheet" href="css/style.css" />
        <link rel="stylesheet" href="font-awesome-4.7.0/css/font-awesome.min.css" />
    </head>
    <body>
        <div id="principal" class="container">

            <jsp:include page="menu.jsp" />
            <div class="row">

                <div class="col-lg-8">
                    <div class="card border-primary">
                        <div class="card-header bg-primary">LES GESTIONNAIRES d'UFR</div>
                        <div class="card-body table-responsive">
                            <table class="table table-bordered table-striped table-hover">
                                <tr>
                                    <th>PRENOM</th>
                                    <th>NOM</th>
                                    <th>TELEPHONE</th>
                                    <th>UFR</th>
                                    <th colspan="2">Action</th>
                                </tr>
                                <c:forEach items="${gestions}" var="g">
                                    <tr>
                                        <td><c:out value="${g.prenom}" /></td>
                                        <td><c:out value="${g.nom}" /></td>
                                        <td><c:out value="${g.telephone}" /></td>
                                        <td><c:out value="${g.ufr.nomUfr}" /></td>
                                        <td><a title="Modifier" class="btn btn-primary" href="gestionnaireufr?action=admin_modifier&idges=${g.idGesUfr}"><i class="fa fa-pencil-square-o fa-2x"></i></a></td>
                                        <td><a title="Modifier" class="btn btn-primary" href="gestionnaireufr?action=admin_supprimer&idges=${g.idGesUfr}"><i class="fa fa-remove fa-2x"></i></a></td>

                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>


                </div>

                <div class="col-lg-4">
                    <div class="card border-primary">
                        <div class="card-header bg-primary">GESTIONNAIRE UFR</div>
                        <div class="card-body">
                            <form action="gestionnaireufr" method="post">
                                <input type="hidden" name="action" value="${objet != null ? objet : 'ajouter'}" />
                                <input type="hidden" name="idGesUfr" value="${gestion.idGesUfr}" />
                                <div class="form-group">
                                    <label for="prenom">Prénom</label>
                                    <input type="text" name="prenom" value="${gestion.prenom}" class="form-control" id="prenom" placeholder="Entrer le prénom">

                                </div>

                                <div class="form-group">
                                    <label for="nom">Nom</label>
                                    <input type="text" name="nom" value="${gestion.nom}" class="form-control" id="nom" placeholder="Entrer le nom">

                                </div>

                                <div class="form-group">
                                    <label for="telephone">Nom</label>
                                    <input type="text" name="telephone" value="${gestion.telephone}" class="form-control" id="telephone" placeholder="Entrer le numéro de téléphone">

                                </div>

                                <div class="form-group">
                                    <label for="email">Adresse Mail</label>
                                    <input type="email" name="email" value="${gestion.login}" class="form-control" id="email" placeholder="Entrer l'adresse email">

                                </div>

                                <div class="form-group">
                                    <label for="ufr">UFR</label>
                                    <select name="ufr" class="form-control" id="ufr">
                                        <option value="${gestion.ufr.idUfr}">${gestion.ufr.nomUfr}</option>
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
            <script src="bootstrap-4.1.2/js/jquery-3.3.1.min.js"></script>
            <script src="bootstrap-4.1.2/js/bootstrap.min.js"></script>
    </body>
</html>

