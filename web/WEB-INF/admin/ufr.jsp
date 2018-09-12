<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>UFR</title>
        <link rel="stylesheet" href="bootstrap-4.1.2/css/bootstrap.min.css" />
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="font-awesome-4.7.0/css/font-awesome.min.css" />
    </head>
    <body>
        <div id="principal" class="container">
            <jsp:include page="menu.jsp" />
            <div class="row">

                <div class="col-lg-8">


                    <div class="card">
                        <div class="card-header bg-primary">LISTE DES UFRs</div>
                        <div class="card-body table-responsive">
                            <table class="table table-bordered table-striped table-hover">
                                <tr>                            
                                    <th>Nom De l'UFR</th>                            
                                    <th colspan="2">Action</th>
                                </tr>
                                <c:forEach items="${ufrs}" var="u">
                                    <tr>
                                        <td><c:out value="${u.nomUfr}" /></td>

                                        <td><a class="btn btn-primary" href="controleurufr?action=admin_modifier&idufr=${u.idUfr}"><i class="fa fa-edit fa-2x"></i></a></td>
                                        <td><a class="btn btn-primary" href="controleurufr?action=admin_supprimer&idufr=${u.idUfr}"><i class="fa fa-remove fa-2x"></i></a></td>

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
                            <form action="controleurufr" method="post">
                                <input type="hidden" name="action" value="${objet != null ? objet : 'ajouter'}" />
                                <input type="hidden" name="idufr" value="${ufr.idUfr}" />
                                <div class="form-group">
                                    <label for="ufr">Nom de l'UFR</label>
                                    <input type="text" name="nomUfr" value="${ufr.nomUfr}" class="form-control" id="ufr" placeholder="Entrer le nom de l'UFR">
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
