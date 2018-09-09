<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>GESTION_NAVETTE_LOGEMENT</title>
        
        <jsp:include page="style.jsp" />

    </head>
    <body>
        <div class="container">
            <div id="formdiv" class="offset-lg-3 col-lg-6">
                <form action="controleur" method="post">
                    <input type="hidden" name="action" value="Connexion" />
                    <div class="form-group">
                        <label for="exampleInputEmail1">Adresse Mail</label>
                        <input type="email" name="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">

                    </div>
                    <div class="form-group">
                        <label for="exampleInputPassword1">Mot de passe</label>
                        <input type="password" name="motdepasse" class="form-control" id="exampleInputPassword1" placeholder="Password">
                    </div>

                    <button type="submit" class="btn btn-primary">Connexion</button>
                </form>
            </div>
        </div>
        <jsp:include page="script.jsp" />
    </body>
</html>
