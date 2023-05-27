## Projet Redis - Réseau

### Introduction
Ce projet consiste à implémenter un serveur Redis en Java 11 ou supérieur, accompagné d'un dossier explicatif décrivant le travail réalisé par l'équipe. Redis est un système de stockage de données en mémoire permettant de manipuler des structures de données structurées telles que des chaînes, des listes et des ensembles de manière atomique. Il est connu pour sa rapidité et est couramment utilisé pour la mise en cache de données volatiles, la gestion de messages et la gestion de verrous distribués.

### Travail à réaliser
Le projet doit être réalisé en groupe de 4 personnes. Les principales tâches à accomplir sont les suivantes :

1. **Réseau** : Le serveur Redis doit être capable de gérer plusieurs clients simultanément en utilisant la communication via TCP.

2. **Commandes** : L'objectif n'est pas d'implémenter l'intégralité des commandes Redis, mais de se limiter à un sous-ensemble qui opère sur les chaînes. Les commandes à implémenter sont les suivantes :
   - SET key value / SETNX key value
   - GET key
   - STRLEN key
   - APPEND key "chaîne à concaténer"
   - INCR key / DECR key
   - EXISTS key
   - DEL key
   - EXPIRE key duration

3. **Publish/Subscribe** : Implémenter le design pattern "publish/subscribe" en utilisant les commandes SUBSCRIBE, PUBLISH et UNSUBSCRIBE. Vous devrez vous limiter à un seul topic par appel et ne pas gérer les souscriptions globales.

4. **Réplication** : Implémenter un mode maître/esclave activable à la demande. Le mode maître/slave permet la réplication des données entre un maître et un ou plusieurs esclaves pour assurer la continuité du service en cas de panne. Le slave doit se synchroniser avec le maître en répliquant toutes les données initiales puis en écoutant les mises à jour en temps réel.

5. **Cluster** (groupes de 5) : Implémenter un mode cluster dans lequel le client peut se connecter à n'importe quelle instance et envoyer des commandes de lecture ou d'écriture. Le cluster doit être constitué d'au moins deux machines, mais peut en compter un nombre illimité. Les données doivent être répliquées à l'identique sur toutes les instances.

### Critères d'évaluation
Le projet sera évalué en fonction des critères suivants :

- Le serveur Redis doit être écrit en Java 11 ou supérieur.
- Il doit pouvoir gérer efficacement plusieurs clients simultanés.
- La communication avec le serveur doit se faire via des sockets sur le port 6379.
- Le projet doit être publié sur GitHub ou GitLab et doit inclure un fichier README expliquant brièvement ce qu'il fait et comment le lancer.
- Le projet doit être facile à installer pour un utilisateur lambda, en fournissant des scripts de lancement, par exemple.
- Tous les membres du groupe doivent participer activement au projet.
- Le serveur ne doit pas utiliser de bibliothèques tierces autres que le JDK.
- Les requêtes doivent être analysées et les réponses générées manuellement, sans utiliser de bibliothèques externes.
- Le code doit être bien document

é, maintenable et évolutif.
- Le serveur doit être suffisamment robuste pour être déployé en production.

### Ressources supplémentaires
Si vous avez des questions ou besoin de précisions supplémentaires sur le sujet, n'hésitez pas à me contacter. Bonne chance pour votre projet !
