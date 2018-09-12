<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Réservation Logement</title>
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
                        <div class="card-header bg-primary">Réserver un chambre pour ${ens.prenom} ${ens.nom}</div>
                        <div class="card-body offset-2 col-lg-8">

                            <form action="responsable" method="post">
                                <input type="hidden" name="ens" value="${ens.idUser}" />
                                <input type="hidden" name="action" value="addloge" />
                                <div class="form-group row">
                                    <label for="site" class="col-sm-4 col-form-label">Site</label>
                                    <div class="col-sm-8">
                                        <select name="site" id="site" class="form-control">
                                            <option></option>
                                            <c:forEach items="${sites}" var="s">
                                                <option value="${s.sit}">${s.nomSite}</option>
                                            </c:forEach>
                                            
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="chambre" class="col-sm-4 col-form-label">Chambre</label>
                                    <div class="col-sm-8">
                                        <select name="chambre" id="chambre" class="form-control">
                                            
                                        </select>
                                    </div>
                                </div>
                                
                                <div class="form-group row">
                                    <label for="entree" class="col-sm-4 col-form-label">Date d'entrée</label>
                                    <div class="col-sm-8">
                                        <input type="date" name="entree" class="form-control" id="entree" placeholder="">
                                    </div>
                                </div>
                                
                                <div class="form-group row">
                                    <label for="sortie" class="col-sm-4 col-form-label">Date de sortie</label>
                                    <div class="col-sm-8">
                                        <input type="date" name="sortie" class="form-control" id="sortie" placeholder="">
                                    </div>
                                </div>
                                
                                <div class="form-group row">
                                    <div class="col-sm-10">
                                        <button type="submit" class="btn btn-primary">Valider</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <script src="bootstrap-4.1.2/js/jquery-3.3.1.min.js"></script>
        <script src="bootstrap-4.1.2/js/bootstrap.min.js"></script>
        <script src="js/script.js"></script>
    </body>
</html>
