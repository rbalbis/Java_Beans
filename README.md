# Java_Beans


## BanqueRomanBalbis
```shell
Admin : 
username : admin
password : root

Conseiller : 
username : cons
password : cons

Client : 
username : client
password : client
```

Action possible pour les differents profils : 

###Admin : 
- Creer utilisateur (admin, conseillers, client)
- Gerer les comptes (Ajout d'argent, retrait d'argent, suppression du compte), pour tous les utilisateurs
- Virements vers tous les clients depuis tous les comptes clients
- Affecter client a un conseiller
- Ajouter client a un compte bancaire deja existant

###Conseiller : 
- Gerer les comptes (Ajout d'argent, retrait d'argent, suppression du compte),de ses clients
- Virements vers tous les clients depuis tous les clients du conseiller
- Affecter client a un conseiller
- Ajouter client a un compte bancaire deja existant

###Client : 
- Gerer ses comptes (Ajout d'argent, retrait d'argent, suppression du compte)
- Faire virements de ses comptes vers tous les comptes de la banque

Le schema de la base de donnée est diponible [ici](https://drive.google.com/file/d/12SRB_i9Ukd1dxsYLdfu1PIPEYO32NuYn/view?usp=sharing)
