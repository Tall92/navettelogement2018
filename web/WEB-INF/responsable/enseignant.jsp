<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Liste Enseignant de la formation</title>
        <link rel="stylesheet" href="bootstrap-4.1.2/css/bootstrap.min.css" />
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="font-awesome-4.7.0/css/font-awesome.min.css" />

    </head>
    <body>
        <div id="principal" class="container">

            <jsp:include page="menures.jsp" />

            <div class="row">

                <div class="col-lg-12">


                    <div class="card">
                        <div class="card-header bg-primary">LISTE DES ENSEIGNANTS DE LA FORMATION ${formation.nomForm}</div>
                        <div class="card-body table-responsive">
                            <table class="table table-bordered table-striped table-hover">
                                <tr>                            
                                    <th>PRENOM</th>
                                    <th>NOM</th>
                                    <th>ADRESSE</th>
                                    <th>TELEPHONE</th>
                                    <th>UFR</th>
                                    <th>DEPARTEMENT</th>
                                    <th>PROFIL</th>
                                    <th colspan="2">ACTION</th>
                                </tr>
                                <c:forEach items="${enseignants}" var="e">
                                    <tr>
                                        <td><c:out value="${e.prenom}" /></td>
                                        <td><c:out value="${e.nom}" /></td>
                                        <td><c:out value="${e.adresse}" /></td>
                                        <td><c:out value="${e.tel}" /></td>
                                        <td><c:out value="${e.ufr.nomUfr}" /></td>
                                        <td><c:out value="${e.dept.nomDept}" /></td>
                                        <td><c:out value="${e.profil}" /></td>
                                        <td>
                                            <a class="btn btn-primary" href="responsable?action=reserver_navette&iduser=${e.idUser}">Réserver Navette</a>
                                        </td>
                                        <td>
                                            <a class="btn btn-primary" href="responsable?action=reserver_logement&iduser=${e.idUser}">Réserver Logement</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <script src="bootstrap-4.1.2/js/jquery-3.3.1.min.js"></script>
<script src="bootstrap-4.1.2/js/bootstrap.min.js"></script>
    </body>
</html>
