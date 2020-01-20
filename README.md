# Uitbaten van een sportarena 
## Project runnen via docker  
  
### Uitvoeren in Home folder  
```  
bash buildall.sh  
docker-compose up --build  
```  


  
### Hiervoor moet:  
- Alle localhosts vervangen naar de corresponderende dockernaam bv: _membermanagement_:  
    `http://localhost:2226 -> http://bettingmanagement:2226`  
  
- Kafka, Zookeeper en Mongodb properties op corresponderende dockernaam zetten:  
    ```  
    In plaats van localhost:  
        spring.data.mongodb.host=gamblingdb  
        spring.cloud.stream.kafka.binder.zkNodes=zookeeper  
        spring.cloud.stream.kafka.binder.brokers=kafka  
    ``` 
##  Project deployen op kubernetes
kubernetes shizzle!!

## **Features**

 #### Okta

 #### Registreren
 Een gebruiker kan zich registreren als member, staff, club of cateringservice. Naargelang de keuze verschijnen er extra velden die ingevuld kunnen worden. Deze keuze bepaalt de gebruiker zijn rol en dus welke functies hij kan uitvoeren.


#### Wedstrijd inplannen
Via de "Arena" knop op de homepage komt u terecht op een overzicht van alle wedstrijden en heeft u ook de mogelijkheid om een nieuwe wedstrijd in te plannen. Voor deze wedstrijd zullen dan automatisch tickets gecreëerd worden.

#### Tickets kopen
Eens een wedstrijd gepland staat zullen er tickets beschikbaar zijn. Deze kan men kopen door via de homepage door te klikken naar "Buy your ticket".
Na een vooropgesteld aantal tickets stuurt de Ticket service een update naar de catering en staff services.
 
## **Design keuzes**
