<nav style="border-radius: 5px" class="navbar navbar-expand-xl navbar-dark bg-primary mb-4">
    <a class="navbar-brand" href="gestionufr?action=index">Gestionnaire UFR</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarCollapse">
        <ul id="menu" class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="gestionufr">UFR</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="controleurutilisateur">Enseignants</a>
            </li>

            <li class="nav-item" style="position: relative; left: 20px">
                <a class="btn btn-dark" href="gestionufr?action=logout"><span class="fa fa-sign-out"></span></a>
                <span class="dropdown">
                    <a class="btn btn-dark dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">

                    </a>
                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
                        <span class="dropdown-item fa fa-user-circle"> ${gu.prenom} ${gu.nom}</span>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item fa fa-key" href="#">Changer mot de passe</a>
                    </div>
                </span>
            </li>

        </ul>

    </div>
</nav>