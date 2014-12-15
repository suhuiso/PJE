Quentin Baert  
Thomas Bernard

# Rapport PJE

## Description générale du projet

### Description de la problématique

Le but du projet était de créer une application capable de donner le sentiment général sur twitter à propos d'un sujet en particulier.  
Le sentiments peut être représenté par trois classes : positif, négatif, neutre.

Afin de determiner le sentiment à propose d'un sujet, une requête à l'API Twitter est faite avec le sujet en tant que mot clef.  
Un certains nombres de tweets à propos du sujet sont ainsi récupérer.

Une fois les tweets récupérés, il faut alors les analyser pour les répartir dans les différentes classes et ainsi en déduire le sentiment dominant à propos du sujet.

### Description générale de l'architecture de votre application

Les sources de l'application ont été divisées en plusieurs packages :

* **feeling** : contient l'ensemble des classes relatives aux sentiments et à l'attribution d'un sentiment à un tweet,

* **utils** : contient l'ensemble des classes utilitaires de l'application. Ces classes permettent d'ajouter une abstraction aux objets proposés par la librairie **twitter4j**. Par exemple, la classe *Tweet* fournis une abstraction d'un tweet représenté par une instance de la classe *Status* dans **twitter4j**. Ainsi une instance de *Tweet* ne contiendra que les informations nécéssaires au bon fonctionnement de l'application.

* **statistics** : contient l'ensemble des classes qui permettent de faire des statistiques depuis les données de l'application. Ce package contient la classe *CrossValidation* qui permet de faire une évaluation d'un classifieur en usant de la méthode de cross validation. Il contient également la classe *PieChartBuilder* qui permet de générer un graphique en "camembert" concernant les classifications d'un classieurs sur une liste de tweets.

Les packages suivants contiennent les différents composant d'une architecture en MVC.

* **model** : contient la classe *AppModel*, model de l'application.

* **controller** : contient la classe *AppController*, controller de l'application.

* **view** : contient l'ensemble des classes constituant la vue de l'application.

* **app** : contient la classe *Main* de l'application.

## Détails des différents travaux réalisés

### API Twitter

Pour pouvoir intérroger l'API Twitter, nous avons utilisé la librairie **twitter4j**. Cette librairie donne accès à au singleton *TwitterFactory*, qui permet d'accéder à l'API Twitter ainsi qu'aux classes suivantes :
* *Query* : requête
* *QueryResult* : résultat d'une requête (ensemble de *Status*)
* *Status* : tweet et toutes les informations associées

Ces classes sont uniquement utilisé dans la méthode *model.AppModel.search*. Une fois la recherche effectuée, les instances de *Status* deviennent des instances de *Tweet*. Ce sont ces instances de *Tweet* qui seront manipulés par la suite.

Afin de fixer le nombre de tweets récupérés et de s'assurer que ces tweets soient écris en alphabet latin, les opérations suivantes sont effectuées sur l'instance de *Query* utilisée pour la recherche depuis une chaine de caractères *searchQuery* :  
```
Query query = new Query( searchQuery );

/* Assure le fait que les tweets récupérés soient en alphabet latin */
query.setLang( "fr" );

/* Fixe le nombre de tweets récupérés lors de la requête */
query.setCount( this.tweetsNb );
```

### Préparation de la base d'apprentissage

##### Nettoyage des données

Afin de nettoyer les tweets sauvegardés dans base d'apprentissage, un singleton *MessageCleaner* est utilisé.  
Ce dernier est constitué de plusieurs méthodes qui prennent une chaine de caractères et renvoient cette dernière néttoyée. Les chaines de caractères sont néttoyées à l'aide d'expréssion régulière et de la méthode *String.replaceAll*.  
Nettoyer le message d'un tweet revient donc à le faire passer dans chacunes des méthodes du *MessageCleaner*.


Afin de pouvoir facilement ajouter une méthode de nettoyage dans le *MessageCleaner*, la réfléxivité est utilisée lors de sa construction afin de construire une liste de ses fonctions qu'il faut appliquer pour nettoyer totalement le message d'un tweet. Pour cela, la convention qu'une méthode de nettoyage doit commencer par `delete` pour être considérée est utilisée.  
```
/* Constructeur de MessageCleaner */
private MessageCleaner () {
  this.methods = new ArrayList< Method >();

  for ( Method m : this.getClass().getMethods() ) {
    if ( m.getName().startsWith( "delete" ) ) {
      this.methods.add( m );
    }
  }
}
```

##### Construction de la base

Notre base d'apprentissage est abstraite dans une instance de la classe *TweetPool* qui correspond à un ensemble de tweets. Cette dernière est crée à l'ouverture de l'application et est complétée depuis un fichier **tweetPool.csv** si ce dernier existe. Une fois la *TweetPool* crée, toutes les manipulations seront faites sur cette instance. Le fichier **tweetPool.csv** est réécrit à la fermeture de l'application pour sauvegarder le nouvel état de la base d'apprentissage.

Nous avons décidé de construire la base d'apprentissage "à la main" afin que celle-ci soit la plus précise possible.  
Pour cela, nous avons utilisé l'écran **Apprentissage** de notre application qui affiche des tweets obtenus depuis une requête et propose de leur associer un sentiments. Une fois ce sentiments indiqués, ces tweets sont sauvegardés dans la *TweetPool* du modèle et serve immédiatement aux classifications demandées par la suite.

*Base abstraite dès la construction : classe TweetPool*  
*Utilisation de la TweetPool dans toute l'application, écriture de cette dernière à la fermeture de l'application*  
*Onglet Apprentissage de l'application*  
*Annotation à la main des tweets afin d'avoir une base d'apprentissage la plus précise possible*

### Algorithmes de classification

*Hierarchie de classe des Classifier*

##### Mots clefs

*DictionnaryClassifier*

##### KNN

*KNNClassifier*

##### Bayes

*BayesClassifier + toute la sous hierarchie*

### Interface graphique

##### Copies d'écrans

##### Manuel d'utilisation

## Résultat de la classification avec les différentes méthodes et analyses

*Tester les différents classifiers ?*

## Conclusion

*Bah c'était cool...*
