# Project System design

## Operating a sports arena

### Deploying project in docker

Make the following edit in the script-tag:
```javascript
<!-------------------- CONFIGURATION -------------------------->
		var dockerUrl="http://192.168.99.100:8080";
		var kubernetesUrl="http://localhost:9000";
		var URL = dockerUrl;
<!-------------------------------------------------------------------------->
```		

do this in the following files:

- sportarena-app-ticket-management\src\main\resources\templates\shop.html
- sportarena-app-arena-management\src\main\resources\templates\arena.html
- sportarena-app-gambling-management\src\main\resources\templates\gamblingDummy.html
 
 Afterwards, go to the project's home directory and run the following commands:
```  
bash buildall.sh  
docker-compose up --build  
```  



##  Deploying the project on kubernetes
Make the following edit in the script-tag:
```javascript
<!-------------------- CONFIGURATION -------------------------->
		var dockerUrl="http://192.168.99.100:8080";
		var kubernetesUrl="http://localhost:9000";
		var URL = kubernetesUrl;
<!-------------------------------------------------------------------------->
```		

do this in the following files:

- sportarena-app-ticket-management\src\main\resources\templates\shop.html
- sportarena-app-arena-management\src\main\resources\templates\arena.html
- sportarena-app-gambling-management\src\main\resources\templates\gamblingDummy.html

 ### Deployment
 Copy the contents of the `Kubernetes` directory to the Kubernetes Master. Then run the following commands in that directory:
 ````
  bash kafka.sh
  kubectl replace --force -f .
 ````
 You can request the Kubernetes cluster status with the following command:
 ````
  kubectl get all --all-namespaces
 ````
 The application is correctly deployed if all pods are in `RUNNING` status. To connect with the virtual wall of the university, port forwarding is required. This happens by running the `port-forward.sh` file in the Kubernetes directory on the client device. If all is well, the homepage of the application will be visible on `http://localhost:9000`.

 ### Configurationfiles
 By using configurationfiles and environment variables there is no need to make changes in the frontend. The services are automatically informed about the location of Kafka and other services. 
 
 ### Chaosmonkey
 Chaosmonkey is a tool to test the robustness of the application, it periodically destroys parts of the microservice cluster to see how failure is handled. Its located in the Kubernetes directory and can be copied with the .yaml files to be deployed.

## **Features**

 #### Okta
Okta is used as external authentication service. This is a safe way to handle data. Authentication and authorisation is also handled by Okta, so that users only see parts of the application where the privileges are met.

 #### Registration
A user can register as a member, staff, club or cateringservice. 
There are a couple of constraints:

- The email has to have a valid format Het email adres moet een geldig formaat hebben (met een @ etc.)
- The password for each account is "Password123"

Based on the user type there are specific fields to fill in. The user type determines the role and operations the user will be able to do.

### Functionalities after registration
#### Watch and schedule games
Via de "Arena" knop op de homepage komt men terecht op een tabel van alle ingplande wedstrijden. Bovendien heeft men ook de mogelijkheid om een nieuwe wedstrijd in te plannen. Hierbij moeten geldige start- en einddata worden ingegeven die niet overlappen met reeds ingeplande wedstrijden. Wanneer de gebruiker zo'n inplanning bevestigt, zullen de relevante services op de hoogte worden gebracht. Ook bij het beeindigen van een wedstrijd zullen deze services ingelicht worden.

#### Buying tickets
Eens een wedstrijd gepland staat, zal de Ticket Service hiervoor tickets aanbieden. Deze kan men kopen door via de homepage door te klikken naar "Buy your ticket".
Telkens wanneer een vooropgesteld aantal tickets verkocht werd, stuurt de Ticket Service een update naar de Catering en Staff service die extra jobs en catering zullen nodig hebben voor de wedstrijd. 

##### Betting Service
Voor elke wedstrijd kan een geregistreerde Member weddenschappen aangaan. In de homepage via de knop "XXX" worden deze in een lijst weergegeven samen met de beschikbare weddenschappen. Bovendien kan een Member via "My Wallet" zijn eigen portefeuille bekijken waarin zijn Tokens worden bijhouden. Deze Tokens kunnen eveneens via deze pagina worden aangekocht en uitgecashet. 
Na het beeindigen van een wedstrijd wordt alle geplaatste weddenschappen van een gebruiker gecontroleerd en worden de gewonnen Tokens naar de portefeuille overgedragen.


