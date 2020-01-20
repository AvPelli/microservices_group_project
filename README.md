# Project Systeemontwerp

## Uitbating van een sportarena

### Project deployen in docker

Pas volgende aanpassingen toe bovenaan in de script-tag:
``` javascript
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
 Wanneer hier alle aparte pods op status `RUNNING` staan, is de applicatie gedeployed. Om te connecteren via de voorziene IMec Wall, dient u eerst een port forwarding op te zetten. Dit gebeurt door het bestand `portforward.sh` in de Kubernetes map op het client toestel te uit te voeren. Vervolgens surft u in een browser naar `http://localhost:9000` en komt u terecht op de homepagina.

 ### Configuratiebestanden
 Door gebruik te maken van configuratiebestanden en omgevingsvariabelen hoeft er buiten bovenstaande frontend aanpassingen niets gewijzigd te worden. De services zijn dus automatisch op de hoogte van de locatie van Kafka en de andere services.

## **Features**

 #### Okta

 #### Registreren
 Een gebruiker kan zich registreren als member, staff, club of cateringservice. 
Hierbij zijn er enkele belangrijke zaken:

- Het email adres moet een geldig formaat hebben (met een @ etc.)
- Het wachtwoord voor elk account wordt standaard ingesteld op "Password123"

Naargelang de keuze verschijnen er extra velden die ingevuld kunnen worden. Deze keuze bepaalt de gebruiker zijn rol en dus welke functies hij kan uitvoeren.


#### Wedstrijd inplannen
Via de "Arena" knop op de homepage komt u terecht op een overzicht van alle wedstrijden en heeft u ook de mogelijkheid om een nieuwe wedstrijd in te plannen. Voor deze wedstrijd zullen dan automatisch tickets gecreëerd worden.

#### Tickets kopen
Eens een wedstrijd gepland staat zullen er tickets beschikbaar zijn. Deze kan men kopen door via de homepage door te klikken naar "Buy your ticket".
Na een vooropgesteld aantal tickets stuurt de Ticket service een update naar de catering en staff services.
