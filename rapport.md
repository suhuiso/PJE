Quentin Baert  
Thomas Bernard

# Rapport PJE

## Description générale du projet

### Description de la problématique

Le but du projet était de créer une application capable de donner le sentiment général sur Twitter à propos d'un sujet en particulier.  
Le sentiment d'un tweet peut être représenté par trois classes : positif, négatif, neutre.

Afin de determiner le sentiment à propose d'un sujet, une requête à l'API Twitter est faite avec le sujet en tant que mot clef.  
Un certain nombre de tweets à propos du sujet sont ainsi récupérés.

Une fois les tweets récupérés, il faut alors les analyser pour les répartir dans les différentes classes et ainsi en déduire le sentiment dominant à propos du sujet.

### Description générale de l'architecture de notre application

Les sources de l'application ont été divisées en plusieurs packages :

* **feeling** : contient l'ensemble des classes relatives aux sentiments et à l'attribution d'un sentiment à un tweet,

* **utils** : contient l'ensemble des classes utilitaires de l'application. Ces classes permettent d'ajouter une abstraction aux objets proposés par la librairie **twitter4j**. Par exemple, la classe *Tweet* fournie une abstraction d'un tweet représenté par une instance de la classe *Status* dans **twitter4j**. Ainsi une instance de *Tweet* ne contiendra que les informations nécéssaires au bon fonctionnement de l'application.

* **statistics** : contient l'ensemble des classes qui permettent de faire des statistiques depuis les données de l'application. Ce package contient la classe *CrossValidation* qui permet de faire une évaluation d'un classifieur en usant de la méthode de cross validation. Il contient également la classe *PieChartBuilder* qui permet de générer les diagrammes circulaires concernant les classifications d'un classieurs sur une liste de tweets.

Les packages suivants contiennent les différents composants d'une architecture en MVC.

* **model** : contient la classe *AppModel*, modèle de l'application.

* **controller** : contient la classe *AppController*, contrôleur de l'application.

* **view** : contient l'ensemble des classes constituant la vue de l'application.

* **app** : contient la classe *Main* de l'application.

## Détails des différents travaux réalisés

### API Twitter

Pour pouvoir interroger l'API Twitter, nous avons utilisés la librairie **twitter4j**. Cette librairie donne accès au singleton *TwitterFactory*, qui permet d'accéder à l'API Twitter ainsi qu'aux classes suivantes :
* *Query* : requête
* *QueryResult* : résultat d'une requête (ensemble de *Status*)
* *Status* : tweet et toutes les informations associées

Ces classes sont uniquement utilisées dans la méthode *model.AppModel.search*. Une fois la recherche effectuée, les instances de *Status* deviennent des instances de *Tweet*. Ce sont ces instances de *Tweet* qui seront manipulées par la suite.

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

Afin de nettoyer les tweets sauvegardés dans la base d'apprentissage, un singleton *MessageCleaner* est utilisé.  
Ce dernier est constitué de plusieurs méthodes qui prennent une chaine de caractères et renvoient cette dernière nettoyée. Les chaines de caractères sont nettoyées à l'aide d'expressions régulières et de la méthode *String.replaceAll*.  
Nettoyer le message d'un tweet revient donc à le faire passer dans chacune des méthodes du *MessageCleaner*.

Afin de pouvoir facilement ajouter une méthode de nettoyage dans le *MessageCleaner*, la réfléxivité est utilisée lors de sa construction afin de construire une liste de fonctions à appliquer pour nettoyer totalement le message d'un tweet. Pour cela, une méthode chargée du nettoyage doit commencer par `delete` pour être considérée comme telle et être utilisée.  

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

Notre base d'apprentissage est abstraite dans une instance de la classe *TweetPool* qui correspond à un ensemble de tweets. Cette dernière est créée à l'ouverture de l'application et remplie à partir d'un fichier **tweetPool.csv** si ce dernier existe. Une fois la *TweetPool* créée, toutes les manipulations seront faites sur cette abstraction. Le fichier **tweetPool.csv** est réécrit à la fermeture de l'application pour sauvegarder le nouvel état de la base d'apprentissage.

Nous avons décidé de construire la base d'apprentissage "à la main" afin que celle-ci soit la plus précise possible.  
Pour cela, nous avons utilisé l'écran **Apprentissage** de notre application qui affiche des tweets obtenus depuis une requête et propose de leur associer un sentiment. Une fois ce sentiment indiqué, ces tweets sont sauvegardés dans la *TweetPool* du modèle et servent immédiatement aux classifications demandées par la suite.

### Algorithmes de classification

*Hierarchie de classe des Classifiers*

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
