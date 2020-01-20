# Project Systeemontwerp

## Uitbating van een sportarena

### Project deployen in docker

Pas volgende aanpassingen toe bovenaan in de script-tag:
```javascript
<!-------------------- AAN TE PASSEN CONFIGURATIE -------------------------->
		var dockerUrl="http://192.168.99.100:8080";
		var kubernetesUrl="http://localhost:9000";
		var URL = dockerUrl;
<!-------------------------------------------------------------------------->
```		

in volgende bestanden:

- sportarena-app-ticket-management\src\main\resources\templates\shop.html
- sportarena-app-arena-management\src\main\resources\templates\arena.html
- sportarena-app-gambling-management\src\main\resources\templates\gamblingDummy.html
 
 Vervolgens ga je naar de home directory van het project en voer volgende commando's uit:
```  
bash buildall.sh  
docker-compose up --build  
```  



##  Project deployen op kubernetes
Pas volgende aanpassingen toe bovenaan in de script-tag:
```javascript
<!-------------------- AAN TE PASSEN CONFIGURATIE -------------------------->
		var dockerUrl="http://192.168.99.100:8080";
		var kubernetesUrl="http://localhost:9000";
		var URL = kubernetesUrl;
<!-------------------------------------------------------------------------->
```		

in volgende bestanden:

- sportarena-app-ticket-management\src\main\resources\templates\shop.html
- sportarena-app-arena-management\src\main\resources\templates\arena.html
- sportarena-app-gambling-management\src\main\resources\templates\gamblingDummy.html

 ### Deployment
 Kopieer de inhoud van de `Kubernetes` map naar de Kubernetes Master. Run hier vervolgens volgende commando's uit in de map waar de bestanden staan:
 ````
  bash kafka.sh
  kubectl replace --force -f .
 ````
 Met volgend commando kunt u de status van de Kubernetes cluster opvragen:
 ````
  kubectl get all --all-namespaces
 ````
 Wanneer hier alle aparte pods op status `RUNNING` staan, is de applicatie gedeployed. Om te connecteren via de voorziene IMec Wall, dient u eerst een port forwarding op te zetten. Dit gebeurt door het bestand `port-forward.sh` in de Kubernetes map op het client toestel te uit te voeren. Vervolgens surft u in een browser naar `http://localhost:9000` en komt u terecht op de homepagina.

 ### Configuratiebestanden
 Door gebruik te maken van configuratiebestanden en omgevingsvariabelen hoeft er buiten bovenstaande frontend aanpassingen niets gewijzigd te worden. De services zijn dus automatisch op de hoogte van de locatie van Kafka en de andere services.
 
 ### Chaosmonkey
 Chaosmonkey is aan de Kubernetes map toegevoegd en kan mee met de .yaml files gekopieerd worden en vervolgens gerund worden om de robuustheid van de applicatie te testen.

## **Features**

 #### Okta
Okta wordt gebruikt als externe service die onze users bijhoudt. Dit is een veilige manier om met gegevensdata om te gaan. Alsook wordt via Okta authenticatie en authorisatie afgehandeld, zodat een gebruiker enkel data kan zien waar hij/zij rechten toe heeft.

 #### Registreren
Een gebruiker kan zich registreren als member, staff, club of cateringservice. 
Hierbij zijn er enkele belangrijke zaken:

- Het email adres moet een geldig formaat hebben (met een @ etc.)
- Het wachtwoord voor elk account wordt standaard ingesteld op "Password123"

Naargelang de keuze verschijnen er extra velden die ingevuld kunnen worden. Deze keuze bepaalt de rol van de gebruiker en dus welke functies hij kan uitvoeren.

### Mogelijkheden na het registreren
#### Wedstrijd bekijken en inplannen
Via de "Arena" knop op de homepage komt men terecht op een tabel van alle ingplande wedstrijden. Bovendien heeft men ook de mogelijkheid om een nieuwe wedstrijd in te plannen. Hierbij moeten geldige start- en einddata worden ingegeven die niet overlappen met reeds ingeplande wedstrijden. Wanneer de gebruiker zo'n inplanning bevestigt, zullen de relevante services op de hoogte worden gebracht. Ook bij het beeindigen van een wedstrijd zullen deze services ingelicht worden.

#### Aankopen van tickets
Eens een wedstrijd gepland staat, zal de Ticket Service hiervoor tickets aanbieden. Deze kan men kopen door via de homepage door te klikken naar "Buy your ticket".
Telkens wanneer een vooropgesteld aantal tickets verkocht werd, stuurt de Ticket Service een update naar de Catering en Staff service die extra jobs en catering zullen nodig hebben voor de wedstrijd. 

##### Betting Service
Voor elke wedstrijd kan een geregistreerde Member weddenschappen aangaan. In de homepage via de knop "XXX" worden deze in een lijst weergegeven samen met de beschikbare weddenschappen. Bovendien kan een Member via "My Wallet" zijn eigen portefeuille bekijken waarin zijn Tokens worden bijhouden. Deze Tokens kunnen eveneens via deze pagina worden aangekocht en uitgecashet. 
Na het beeindigen van een wedstrijd wordt alle geplaatste weddenschappen van een gebruiker gecontroleerd en worden de gewonnen Tokens naar de portefeuille overgedragen.


