###Tables:

   ####USER:
        id int identifier, 
        login nvarchar(50),
        password nvarchar(50),
        username nvarchar(50),
        best_score tinyint

   ####SCORE:
        id int identifier,
        user_id int,
        score tinyint


###Pages:

   ####Login 
        -> Connexion de l'user -> redirection vers la page "home"
   ####home 
        -> Permet à l'user de lancer une partie -> redirection vers la page "calcul"
   ####calcul
        -> affichage des calculs + l'user repond aux calculs + valide les réponses saisies -> redirection vers la page "result"
   ####result 
        -> affiche le score réalisé par l'user + affichage des 10 meilleurs scores tout utilisateur confondu -> redirection vers la page "home"

###Controllers:
    
    Login / home / calcul / result

###Classes:

   ####User:
        contient les infos de la table USER
   ####Score:
        contient les infos de la table SCORE
   ####Operateur:
        contient methodes pour déterminer le type d'operateur + methodes de calculs
   ####Expression:
        contient des methodes pour générer les expressions + des methodes pour evaluer ces expressions

###Models:

   ####LoginBean:
        login String,
        password String,
        authResult String

   ####CalculBean:
        calcul ArrayList<String>,
        response ArrayList<String>,
        score int




