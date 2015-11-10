# Serveur "echo" Multi Thread JAVA

## Projet du cours de Prog2 Java LP SIL / Vianey Benjamin - Bodet Cindy


### Config.txt


Exemple de fichier de config : 
```
{
    "Implementation": "1",
    "MaxThread": "50",
    "MaxIdleTime": "120",
    "Port": "5555"
}
```

Parametres et valeurs :

###### Implementation : 
1 = Implémentation de haut niveau
2 = Implémentation de bas niveau
###### MaxThread :
Le nombre de thread maximum du serveur
###### MaxIdleTime :
En seconde, le nombre de seconde d'inactivité d'un client avant que la connexion ne soit fermé
###### Port :
Le port d'écoute du server (au dela de 1024, en dessous de 65536