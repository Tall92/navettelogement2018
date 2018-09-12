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
                        <div class="card-header bg-primary">LES GESTIONNAIRES</div>
                        <div class="card-body table-responsive">
                            <table class="table table-bordered table-striped table-hover">
                                <tr>
                                    <th>PRENOM</th>
                                    <th>NOM</th>
                                    <th>ADRESSE</th>
                                    <th>TELEPHONE</th>
                                    <th colspan="2">Action</th>
                                </tr>
                                <c:forEach items="${gestions}" var="g">
                                    <tr>
                                        <td><c:out value="${g.prenom}" /></td>
                                        <td><c:out value="${g.nom}" /></td>
                                        <td><c:out value="${g.tel}" /></td>
                                        <td><c:out value="${g.adr}" /></td>
                                        <td><a title="Modifier" class="btn btn-primary" href="controleurgestion?action=admin_modifier&idges=${g.ges}"><i class="fa fa-pencil-square-o fa-2x"></i></a></td>
                                        <td><a title="Modifier" class="btn btn-primary" href="controleurgestion?action=admin_supprimer&idges=${g.ges}"><i class="fa fa-remove fa-2x"></i></a></td>
                                        
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>


                </div>

                <div class="col-lg-4">
                    <div class="card border-primary">
                        <div class="card-header bg-primary">NOUVEAU GESTIONNAIRE</div>
                        <div class="card-body">
                            <form action="controleurgestion" method="post">
                                <input type="hidden" name="action" value="${objet != null ? objet : 'ajouter'}" />
                                <input type="hidden" name="idGes" value="${gestion.ges}" />
                                <div class="form-group">
                                    <label for="prenom">Pr�nom</label>
                                    <input type="text" name="prenom" value="${gestion.prenom}" class="form-control" id="prenom" placeholder="Entrer le pr�nom">

                                </div>

                                <div class="form-group">
                                    <label for="nom">Nom</label>
                                    <input type="text" name="nom" value="${gestion.nom}" class="form-control" id="nom" placeholder="Entrer le nom">

                                </div>

                                <div class="form-group">
                                    <label for="tele">T�l�phone</label>
                                    <input type="tel" name="telephone" value="${gestion.tel}" class="form-control" id="tele" placeholder="Entrer le num�ro du t�l�phone">

                                </div>

                                <div class="form-group">
                                    <label for="adr">Adresse</label>
                                    <input type="text" name="adresse" value="${gestion.adr}" class="form-control" id="adr" placeholder="Entrer l'adresse email">

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

