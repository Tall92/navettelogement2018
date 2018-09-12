<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Réservation Navette</title>
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
                        <div class="card-header bg-primary">Résever une place dans la navette pour ${ens.prenom} ${ens.nom}</div>
                        <div class="card-body table-responsive">
                            <form action="responsable" method="post" class="form-inline form-row">
                                <input type="hidden" name="ens" value="${ens.idUser}" />
                                <input type="hidden" name="action" value="addplace" />
                                <div class="form-group col-4">
                                    <label for="date" class="offset-1 col-3">Date</label>
                                    <input type="date" name="date"  class="form-control col-7" id="date" placeholder="Donner la date">

                                </div>
                                <div class="form-group col-3">
                                    <label for="nature" class="offset-1 col-3">Nature</label>
                                    <select name="nature" class="form-control col-7" id="nature">
                                        <option></option>
                                        <option value="aller">Aller</option>
                                        <option value="retour">Retour</option>

                                    </select>
                                </div>
                                <div class="form-group col-3">
                                    <label for="navette" class="offset-1 col-3">Navette</label>
                                    <select name="navette" class="form-control col-7" id="navette">
                                        <option></option>
                                        <c:forEach items="${navs}" var="n">
                                            <option value="${n.idNav}">${n.matricule}</option>
                                        </c:forEach>

                                    </select>
                                </div>
                                <div class="form-group col-2">
                                <button type="submit" class="btn btn-primary form-control">Ajouter</button>
                                </div>
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
