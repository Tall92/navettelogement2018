<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Espace Administrateur</title>
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
                        <div class="card-header bg-primary">LES ADMINISTRATEURS</div>
                        <div class="card-body table-responsive">
                            <table class="table table-bordered table-striped table-hover">
                                <tr>
                                    <th>Pr�nom</th>
                                    <th>Nom</th>
                                    <th>T�l�phone</th>
                                    <th>Email</th>
                                    <th colspan="2">Action</th>
                                </tr>
                                <c:forEach items="${administrateurs}" var="a">
                                    <tr>
                                        <td><c:out value="${a.prenom}" /></td>
                                        <td><c:out value="${a.nom}" /></td>
                                        <td><c:out value="${a.telephone}" /></td>
                                        <td><c:out value="${a.login}" /></td>
                                        <td><a title="Modifier" class="btn btn-primary" href="controleuradminis?action=admin_modifier&iduser=${a.idUser}"><i class="fa fa-pencil-square-o fa-2x"></i></a></td>
                                        <td>
                                            <c:if test="${a.statut eq '1'}">
                                                <a class="btn btn-primary" title="D�sactiver" href="controleuradminis?action=admin_desactiver&iduser=${a.idUser}"><i class="fa fa-toggle-off fa-2x"></i></a>
                                                </c:if>

                                            <c:if test="${a.statut eq '0'}">
                                                <a class="btn btn-primary" title="Activer" href="controleuradminis?action=admin_activer&iduser=${a.idUser}"><i class="fa fa-toggle-on fa-2x"></i></a>
                                                </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>


                </div>

                <div class="col-lg-4">
                    <div class="card border-primary">
                        <div class="card-header bg-primary">NOUVEL ADMINISTRATEUR</div>
                        <div class="card-body">
                            <form action="controleuradminis" method="post">
                                <input type="hidden" name="action" value="${objet != null ? objet : 'ajouter'}" />
                                <input type="hidden" name="iduser" value="${ad.idUser}" />
                                <div class="form-group">
                                    <label for="prenom">Pr�nom</label>
                                    <input type="text" name="prenom" value="${ad.prenom}" class="form-control" id="prenom" placeholder="Entrer le pr�nom">

                                </div>

                                <div class="form-group">
                                    <label for="nom">Nom</label>
                                    <input type="text" name="nom" value="${ad.nom}" class="form-control" id="nom" placeholder="Entrer le nom">

                                </div>

                                <div class="form-group">
                                    <label for="telephone">T�l�phone</label>
                                    <input type="tel" name="telephone" value="${ad.telephone}" class="form-control" id="telephone" placeholder="Entrer le num�ro du t�l�phone">

                                </div>

                                <div class="form-group">
                                    <label for="email">Adresse Mail</label>
                                    <input type="email" name="email" value="${ad.login}" class="form-control" id="email" placeholder="Entrer l'adresse email">

                                </div>

                                <button type="submit" class="btn btn-primary">${objet != null ? 'Modifier' : 'Ajouter'}</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="bootstrap-4.1.2/js/jquery-3.3.1.min.js"></script>
<script src="bootstrap-4.1.2/js/bootstrap.min.js"></script>

    </body>
</html>
