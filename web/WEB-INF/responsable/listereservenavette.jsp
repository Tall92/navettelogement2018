<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="now" value="<%= new java.util.Date()%>" />

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
                        <div class="card-header bg-primary">Liste des réservations de navette</div>
                        <div class="card-body table-responsive">
                            <table class="table table-bordered table-striped table-hover">
                                <tr>                            
                                    <th>PRENOM</th>
                                    <th>NOM</th>
                                    <th>ADRESSE</th>
                                    <th>TELEPHONE</th>
                                    <th>NAVETTE</th>
                                    <th>NATURE</th>
                                    <th>DATE</th>
                                    <th colspan="1">ACTION</th>
                                </tr>

                                <c:forEach items="${rns}" var="e">
                                    <tr>
                                        <td><c:out value="${e.utilisateur.prenom}" /></td>
                                        <td><c:out value="${e.utilisateur.nom}" /></td>
                                        <td><c:out value="${e.utilisateur.adresse}" /></td>
                                        <td><c:out value="${e.utilisateur.tel}" /></td>
                                        <td><c:out value="${e.navette.matricule}" /></td>
                                        <td><c:out value="${e.nature}" /></td>
                                        <td><fmt:formatDate type="date" pattern="EEE, d MMM yyyy" value="${e.date}" /></td>
                                        <td>
                                            <c:if test="${e.date ge now}">
                                                <a class="btn btn-primary" href="responsable?action=annuler_res_nav&iduser=${e.utilisateur.idUser}&idnav=${e.navette.idNav}&nature=${e.nature}&date=<fmt:formatDate type="date" pattern="yyyy-MM-dd" value="${e.date}" />">Annuler</a>
                                            </c:if>
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
