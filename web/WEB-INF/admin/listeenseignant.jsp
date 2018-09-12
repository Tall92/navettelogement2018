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

            <jsp:include page="menu.jsp" />

            <div class="row">

                <div class="offset-1 col-lg-10">


                    <div class="card">
                        <div class="card-header bg-primary">LISTE DES ENSEIGNANTS DE LA FORMATION ${formation.nomForm}</div>
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
                                    <th colspan="1">ACTION</th>
                                </tr>
                                <c:forEach items="${enseignants}" var="e">
                                    <tr>
                                        <td><c:out value="${e.prenom}" /></td>
                                        <td><c:out value="${e.nom}" /></td>
                                        <td><c:out value="${e.adresse}" /></td>
                                        <td><c:out value="${e.tel}" /></td>
                                        <td><c:out value="${e.login}" /></td>
                                        <td><c:out value="${e.ufr.nomUfr}" /></td>
                                        <td><c:out value="${e.dept.nomDept}" /></td>
                                        <td><c:out value="${e.profil}" /></td>
                                        <td>
                                            <c:if test="${e.profil eq 'Responsable'}">
                                                <a class="btn btn-primary" href="controleurformation?action=admin_attribuer&iduser=${e.idUser}&idform=${formation.idForm}">ATTRIBUER COMMME RESPONSABLE</a>
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                        <div class="card-footer">


                            <!-- Button trigger modal -->
                            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalCenter">
                                Ajouter un professeur dans la formation
                            </button>

                            <!-- Modal -->
                            <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalCenterTitle">Choix de professeur</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <div class="container-fluid">
                                                
                                                <form action="controleurformation" method="post" >
                                                        <input type="hidden" name="idform" value="${formation.idForm}" />
                                                        <input type="hidden" name="action" value="admin_addtoform" />
                                                        
                                                            <div class="form-group">
                                                                <label for="professeur">Professeur : </label>
                                                                <select name="professeur" id="professeur"
                                                                        class="form-control" multiple="multiple">
                                                                    <c:forEach items="${listenotin}" var="n">
                                                                        <option
                                                                            value="${n.idUser}">${n.prenom} ${n.nom}</option>
                                                                    </c:forEach>
                                                                </select>
                                                                
                                                            </div>
                                                        </div>
                                                        
                                                        <div class="form-group">
                                                            <button type="submit" class="pull-right btn btn-primary">Ajouter</button>
                                                        
                                                        </div>
                                                        
                                                    </form>
                                                
                                            </div>
                                            
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>


                    </div>
                </div>

            </div>
            <script src="bootstrap-4.1.2/js/jquery-3.3.1.min.js"></script>
            <script src="bootstrap-4.1.2/js/bootstrap.min.js"></script>
    </body>
</html>
