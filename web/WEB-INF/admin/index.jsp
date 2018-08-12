<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Espace Administrateur</title>
        <link rel="stylesheet" href="bootstrap-4.1.2/css/bootstrap.min.css" />
        <link rel="stylesheet" href="css/style.css" />


    </head>
    <body>
        <div id="principal" class="container">

            <jsp:include page="menu.jsp" />
            <!--            <div id="formdiv" class="offset-lg-3 col-lg-6"> 
            
                            <form action="controleurutilisateur" method="post">
                                <input type="hidden" name="action" value="ajouter" />
                                <div class="form-group">
                                    <label for="exampleInputNom">Nom</label>
                                    <input type="text" name="nom"value="${utlisateur.nom}" class="form-control" id="exampleInputEmail1" aria-describedby="nomHelp" placeholder="Enter nom">
            
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputPrenom">Prenom</label>
                                    <input type="text" name="prenom" value="${utlisateur.prenom}" class="form-control" id="exampleInputPrenom" aria-describedby="prenomHelp" placeholder="Enter prenom">
            
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputPrenom">Telephone</label>
                                    <input type="number" name="telephone"value="${utlisateur.telephone}" class="form-control" id="exampleInputPrenom" aria-describedby="telephoneHelp" placeholder="Enter telephone">
            
                                </div>
            
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Adresse Mail</label>
                                    <input type="email" name="email"value="${utlisateur.email}" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">
            
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputPassword1">Mot de passe</label>
                                    <input type="password" name="motdepasse"value="${utlisateur.motdepass}" class="form-control" id="exampleInputPassword1" placeholder="Password">
                                </div>
            
                                <button type="submit" class="btn btn-primary">Ajouter</button>
                                <button type="reset" class="btn btn-primary"><center>Annuler</center></button>
                            </form>
                        </div>
                    </div>-->
    </body>
</html>
