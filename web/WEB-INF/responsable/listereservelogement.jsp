<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                        <div class="card-header bg-primary">Liste des réservations de chambre</div>
                        <div class="card-body table-responsive">
                            <table class="table table-bordered table-striped table-hover">
                                <tr>                            
                                    <th>PRENOM</th>
                                    <th>NOM</th>
                                    <th>ADRESSE</th>
                                    <th>TELEPHONE</th>
                                    <th>SITE</th>
                                    <th>CHAMBRE</th>
                                    <th>DATE D'ENTREE</th>
                                    <th>DATE DE SORTIE</th>
                                    <th>ETAT</th>
                                    <th colspan="1">ACTION</th>
                                </tr>

                                <c:forEach items="${rnl}" var="e">
                                    <tr>
                                        <td><c:out value="${e.utilisateur.prenom}" /></td>
                                        <td><c:out value="${e.utilisateur.nom}" /></td>
                                        <td><c:out value="${e.utilisateur.adresse}" /></td>
                                        <td><c:out value="${e.utilisateur.tel}" /></td>
                                        <td><c:out value="${e.chambre.site.nomSite}" /></td>
                                        <td><c:out value="${e.chambre.numero}" /></td>
                                        <td><fmt:formatDate type="date" pattern="EEE, d MMM yyyy" value="${e.entree}" /></td>
                                        <td><fmt:formatDate type="date" pattern="EEE, d MMM yyyy" value="${e.sortie}" /></td>
                                        <td>
                                            <c:if test="${e.etat eq 1}">
                                                VALIDE
                                            </c:if>
                                                <c:if test="${e.etat eq 0}">
                                                NON VALIDE
                                            </c:if>
                                        </td>
                                        <td>
                                            
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
