# M295_LB_Projekt



## Aufgabestellung

Die Applikation soll einen von dir zu definierenden Prozess ermöglichen. Für die Daten einer relationalen Datenbank sollen CRUD-Operationen in einer REST-API zur Verfügung gestellt werden.

### Was brauche ich?

**Normale Anforderungen**
1. Arbeitsplan
2. Projektidee
3. 3 User Stories
4. Klassendiagram
5. Manuelles Testing
6. Dokumentation

<br>

**Technische Anforderungen**
1. Ein Github Repository für meine Projektabgabe
2. Eine Java Springboot Applikation die meine Rest API Endpunkte verwalten und mit einer Datenbank kommuniziert.
3. Eine Datenbank um meine Daten zu speichern.
4. Insomnia File mit all meinen Http requests.
5. Validierung der Daten
6. Unit Tests
7. Personalisierte Exceptions  
8. Javadoc Kommentare


<br>

## Arbeitsplan

|Datum     |Dauer |Thema                                           |
|----------|---------------------|------------------------------------------------|
|31.10.2024|2h         | An einer Projektidee arbeiten + Github-Repository erstellen|
|02.11.2024|2h         | Projektidee festlegen, User Stories erstellen|
|03.11.2024|2h         | Grundgerüst Java Springboot und Datenbank Container erstellen|
|04.11.2024|4h         | Klassen und Controller in Java erstellen, Http Requests mit Insomnia erstellen|
|05.11.2024|4h         | Manuelle Test + JUnit Tests,
|06.11.2024|4h         | Exceptions erstellen, Dokumentation starten
|07.11.2024|7h         | Diverse Arbeiten, Dokumentation abschliessen


## Projektidee

Meine Anwendung soll Daten zu einer Sportliga (online oder offline) speichern und zusätzlich die Möglichkeit bieten, automatisch einen Spielplan für diese Liga zu erstellen. Eine Sportliga besteht aus mehreren Teams, und jedes Team hat mehrere Spieler.

Nachdem alle Spieler ihren Teams zugeordnet wurden und alle Teams einer Liga angehören, kann ein vollständiger Spielplan für die ausgewählte Liga generiert werden. In diesem Plan tritt jedes Team einmal gegen jedes andere Team an. Die Matches finden jeweils in einem Stadion eines der beiden beteiligten Teams statt, und es wird automatisch ein passendes Datum für jedes Spiel festgelegt.

## User Stories

### User Story 1
**Liga**

Als Nutzer muss ich für verschiedene Zwecke eine Liga erstellen und bearbeiten können um die Übersicht zu behalten.

**Akzeptanzkriterien**

    • Der Nutzer kann alle oder genau 1 Liga anzeigen lassen.
    • Der Nutzer kann eine neue Liga erstellen
    • Der Nutzer kann den Namen und das Land der Liga anpassen
    • Der Nutzer kann eine Liga löschen
    
### User Story 2
**Team**

Als Nutzer muss ich Teams für meine Liga erstellen, bearbeiten und löschen können um mit den Daten auf dem neusten Stand zu sein.

**Akzeptanzkriterien**

    • Der Nutzer kann alle Teams anzeigen lassen.
    • Der Nutzer kann ein Spieler mit Hilfe der ID anzeigen lassen.
    • Der Nutzer kann ein neues Team erstellen.
    • Der Nutzer kann ein existierendes Team anpassen.
    • Der Nutzer kann ein existierendes Team löschen.

### User Story 3
**Spieler**

Als Nutzer muss ich Spieler für meine Teams erstellen, bearbeiten und löschen können um mit dem Austausch von neuen Spieler mithalten zu können.

**Akzeptanzkriterien**

    • Der Nutzer kann alle Spieler anzeigen lassen.
    • Der Nutzer kann ein Spieler mit Hilfe der ID anzeigen lassen.
    • Der Nutzer kann ein neuer Spieler erstellen.
    • Der Nutzer kann Werte eine Spieler anpassen.
    • Der Nutzer kann inaktive Spieler wieder löschen.
   

### User Story 4
**Spielplan Generator**

Als Nutzer möchte ich für eine Liga einen Spielplan generieren, sodass jedes Team einmal gegen jedes andere Team spielt.

**Akzeptanzkriterien**

    • Der Nutzer kann eine neuen Spielplan für eine existierende Liga erstellen.


## Klassendiagram

<img src="./Bilder/Klassendiagramm_1.png" width="500">

## Rest Endpunkte

<img src="./Bilder/Rest_Endpoints.png" width="500">



## Manuelles Testing
 
### Testkonzept
 
#### Was ist mein Ziel?
 
Ich will meine Rest API Endpunkte auf verschiedenste weise Testen.
 
1. Eine neue Liga erstellen
2. Eine neues Team neues Team erstellen
3. Ein neuer Spieler erstellen
4. Ein Spieler erstellen der 17 Jahre alt ist
5. Ein Team erstellen dessen Name mehr als 20 Character enthält
6. Ein Team erstellen das eine ungültige liga_id enthält
 
#### Wie wird getest ?
 
Es wird 1 Akzeptanztest für jeden der oben aufgelisteten Punkte durchgeführt.
 
 
#### Benötigte Infrastruktur:
- Docker 
- Einen Mysql Datenbankcontainer 
- Meine Java Springboot Applikation
- Ein Programm um Http- Requests auszuführen. Ich persönliche nutze Insomnia dafür.
- Visual Studio Code
 
 
#### Testdaten
- Testdaten für unsere Liga, Teams und Spieler
- Screenshots
- Bilder
<br>
<br>
<br>
 
 
|Testplan für Test 1 |                 |
|:-------------             |:--------------- |
| ID / Bezeichnung          | T-01       |
| Beschreibung              | Es wird eine neue Liga erstellt|
| Testvoraussetzung         | - Die Java Springboot Applikation muss laufen. <br> - Insomnia muss eingerichtet sein|
| Testschritte              | 1. Es wird ein Post request an die URL(localhost:8080/liga/) vorbereitet. <br> 2. Es wird ein Liga-Testobjekt in den RequestBody eingefügt. <br> 3. Der Post request wird abgesendet|
| Erwartetes Testergebnis   | Das Liga-Testobjekt wird hinzugefügt.|
 
 
 
|Testprotokoll für Test 1 |                 |
|:------------- |:--------------- |
| Tester | Jan Helbling |            |
| Testdatum | 05.11.24 |
| Ergebnis | **Der Post request mit dem Liga-Testobjekt** ![](./Bilder/Testfall_1_before.png "Image") <br> <br>  **Resultat:** <br> Liga wurde hinzugefügt  ![](./Bilder/Testfall_1_after.png "Image")   |
| Kommentar vom Tester |  Alles I.O

<br>

|Testplan für Test 2 |                 |
|:-------------             |:--------------- |
| ID / Bezeichnung          | T-02       |
| Beschreibung              | Es wird ein neues Team erstellt|
| Testvoraussetzung         | - Die Java Springboot Applikation muss laufen. <br> - Insomnia muss eingerichtet sein|
| Testschritte              | 1. Es wird ein Post request an die URL(localhost:8080/team/) vorbereitet. <br> 2. Es wird ein Team-Testobjekt in den RequestBody eingefügt. <br> 3. Der Post request wird abgesendet|
| Erwartetes Testergebnis   | Das Team-Testobjekt wird hinzugefügt.|
 
 
 
|Testprotokoll für Test 2 |                 |
|:------------- |:--------------- |
| Tester | Jan Helbling |            |
| Testdatum | 05.11.24 |
| Ergebnis | **Der Post request mit dem Team-Testobjekt** ![](./Bilder/Testfall_2_before.png "Image") Die liga_id 3 = unsere Testobjekt liga <br> <br>  **Resultat:** <br> Team wurde hinzugefügt  ![](./Bilder/Testfall_2_after.png "Image")   |
| Kommentar vom Tester |  Alles I.O

<br>

|Testplan für Test 3 |                 |
|:-------------             |:--------------- |
| ID / Bezeichnung          | T-03       |
| Beschreibung              | Es wird ein neuer Spieler erstellt|
| Testvoraussetzung         | - Die Java Springboot Applikation muss laufen. <br> - Insomnia muss eingerichtet sein|
| Testschritte              | 1. Es wird ein Post request an die URL(localhost:8080/spieler/) vorbereitet. <br> 2. Es wird ein Spieler-Testobjekt in den RequestBody eingefügt. <br> 3. Der Post request wird abgesendet|
| Erwartetes Testergebnis   | Das Spieler-Testobjekt wird hinzugefügt.|
 
 
 
|Testprotokoll für Test 3 |                 |
|:------------- |:--------------- |
| Tester | Jan Helbling |            |
| Testdatum | 05.11.24 |
| Ergebnis | **Der Post request mit dem Team-Testobjekt** ![](./Bilder/Testfall_3_before.png "Image") Die team_id 8 = unser Testobjekt team <br> <br>  **Resultat:** <br> Team wurde hinzugefügt  ![](./Bilder/Testfall_3_after.png "Image")   |
| Kommentar vom Tester |  Alles I.O

<br>

|Testplan für Test 4 |                 |
|:-------------             |:--------------- |
| ID / Bezeichnung          | T-04       |
| Beschreibung              | Es wird versucht ein Spieler zu erstellen der 17 Jahre alt ist|
| Testvoraussetzung         | - Die Java Springboot Applikation muss laufen. <br> - Insomnia muss eingerichtet sein|
| Testschritte              | 1. Es wird ein Post request an die URL(localhost:8080/spieler/) vorbereitet. <br> 2. Es wird ein Spieler-Testobjekt mit dem attribut age = 17 in den RequestBody eingefügt. <br> 3. Der Post request wird abgesendet|
| Erwartetes Testergebnis   | Der Spieler wird nicht hinzugefügt weil das Alter mindestens 18 sein muss.|
 
 
 
|Testprotokoll für Test 4 |                 |
|:------------- |:--------------- |
| Tester | Jan Helbling |            |
| Testdatum | 05.11.24 |
| Ergebnis | **Der Post request mit dem Team-Testobjekt** ![](./Bilder/Testfall_4_before.png "Image") <br> <br>  **Resultat:** <br> 400 Error, Bad Request ![](./Bilder/Testfall_4_after.png "Image") <br> Screenshot aus dem Java Springboot Kommandozeile <br> ![](./Bilder/Testfall_4_after_console.png "Image")|
| Kommentar vom Tester |  Alles I.O

<br>

|Testplan für Test 5 |                 |
|:-------------             |:--------------- |
| ID / Bezeichnung          | T-05       |
| Beschreibung              | Es wird versucht ein Team zu erstellen, das einen zu langen Namen hat(mehr als 20 Character).|
| Testvoraussetzung         | - Die Java Springboot Applikation muss laufen. <br> - Insomnia muss eingerichtet sein|
| Testschritte              | 1. Es wird ein Post request an die URL(localhost:8080/team/) vorbereitet. <br> 2. Es wird ein Team-Testobjekt in den RequestBody eingefügt. <br> 3. Der Post request wird abgesendet|
| Erwartetes Testergebnis   | Das Team wird nicht hinzugefügt weil der Name zu lang ist.|
 
 
 
|Testprotokoll für Test 5 |                 |
|:------------- |:--------------- |
| Tester | Jan Helbling |            |
| Testdatum | 05.11.24 |
| Ergebnis | **Der Post request mit dem Team-Testobjekt** ![](./Bilder/Testfall_5_before.png "Image") <br> <br>  **Resultat:** <br> 400 Error, Bad Request ![](./Bilder/Testfall_5_after.png "Image") <br> Screenshot aus dem Java Springboot Kommandozeile <br> ![](./Bilder/Testfall_5_after_console.png "Image")|
| Kommentar vom Tester |  Alles I.O

<br>

|Testplan für Test 6 |                 |
|:-------------             |:--------------- |
| ID / Bezeichnung          | T-06       |
| Beschreibung              | Es wird versucht ein Team zu erstellen, dass eine ungültigen liga_id hat.|
| Testvoraussetzung         | - Die Java Springboot Applikation muss laufen. <br> - Insomnia muss eingerichtet sein|
| Testschritte              | 1. Es wird ein Post request an die URL(localhost:8080/team/) vorbereitet. <br> 2. Es wird ein Team-Testobjekt in den RequestBody eingefügt. <br> 3. Der Post request wird abgesendet|
| Erwartetes Testergebnis   | Das Team wird nicht hinzugefügt weil die liga_id ungültig ist.|
 
 
 
|Testprotokoll für Test 6 |                 |
|:------------- |:--------------- |
| Tester | Jan Helbling |            |
| Testdatum | 05.11.24 |
| Ergebnis | **Der Post request mit dem Team-Testobjekt** ![](./Bilder/Testfall_6_before.png "Image") <br> <br>  **Resultat:** <br> 500 Error, Internal Server Error ![](./Bilder/Testfall_6_after.png "Image")|
| Kommentar vom Tester |  Alles I.O


## Arbeitsjournal

### Arbeitsblock 1

**Donnerstag 31.10.24**

Was wurde gemacht?
- Ich habe ein paar Ideen für mein Projekt gesammelt.
- Ich habe das Grundgerüst von einer Java Springboot Anwendung heruntergeladen 
- Ich habe das docker-compose file für meine MYSQL Datenbank erstellt.

Probleme?
- Ein paar Fehler im docker-compose file
- Verbindung von Java Springboot mit der Datenbank hat auch nicht beim ersten mal funktioniert. Nach dem 10. Versuch und der Hilfe von Fabian ginges dann.

### Arbeitsblock 2

**Samstag 02.11.24**
Was wurde gemacht?
- Github Repository eingerichtet
- Projektidee geschrieben
- User Stories erstellt
- Klassendiagramm erstellt 

Probleme?
- Unsicherheit beim Klassendiagramm

### Arbeitsblock 3

**Sonntag 03.11.24**

Was wurde gemacht?
- Ich habe angefange meine Entitäten und Beziehungen aus dem Klassendiagramm in Java zu übernehmen.
- Danach habe ich die Controller und Repository Klassen für meine Entitäten erstellt.
- Ich angefangen meine Http-Requests in Insomnia zu erstellen

Probleme?
Das PUT-Mapping war neu für mich aber ChatGPT hat mir da gut geholfen.

### Arbeitsblock 4

**Montag 04.11.24**

Was wurde gemacht?
- Ich habe versucht dass alle meine Rest Api Endpoint zum funktionieren zu bringen.
- Mit Hilfe vom Lehrer und langer Fehlersuche haben am Schluss alle meine Endpunkte funktioniert.

Probleme? 
- Sehr viele. 
- Der Get-Request für meine Teams hat nicht funktioniert.
- Keine meiner Requests für die Spieler Endpunkte hat funktioniert.

### Arbeitsblock 5

**Dienstag 05.11.24**

Was wurde gemacht?
- 6 manuelle Tests erstelllt und durchgeführt.
- 6 Unit Tests erstellt.
- Mit der Dokumentation begonnen. 

Probleme?
Bis die Unit Tests funktionierten ginge es eine Weile.

### Arbeitsblock 6

**Mittwoch 06.11.24**

Was wurde gemacht?
- Personalisierte Exceptions für meine Controller Klassen erstellt.
- Weiter an der Dokumentation gearbeitet.

Probleme? 
- Exceptions sind schwer zu verstehen. Mann könnte ohne weiteres eine ganzes Modul nur über Exceptions haben.

### Arbeitsblock 7

**Donnerstag 07.11.24**

Was wurde gemacht?
- Spielplan Controller angepasst.
- Insomnia Datei ins Projekt Verzeichnis hinzugefügt
- Dokumentation abgeschlossen.
- Github Repository aktualisiert.


Probleme?

- Keine


## Installationsanleitung

Diese Anleitung führt Schritt für Schritt durch die Installation und den Betrieb der Einkaufsliste-Applikation. Bitte folgen Sie den Anweisungen genau, um die Applikation erfolgreich zu starten.
 
### Voraussetzungen
 
Stellen Sie sicher, dass folgende Programme auf Ihrem Rechner installiert sind:
 
- **Git**
- **Docker**
- **Maven**
- **Insomnia**
 
Optional, falls nicht bereits installiert:
- **Visual Studio Code**: Ein Code-Editor, um Dateien anzuzeigen oder zu bearbeiten: [VSCode herunterladen](https://code.visualstudio.com/)
 
### Schritt 1: Projekt von Github auf lokalen Pc clonen
 
1. Öffnen Sie ein Terminal (oder die Eingabeaufforderung bei Windows).
2. Klonen Sie das "M295_LB_Projekt" Repository:
 
   ```bash
   git clone https://github.com/itmeJanHelbling/M295_LB_Projekt.git
   ```
 
 ### Schritt 2: Mysql Datenbank Container starten

1. Man muss sich versichern das die Docker Engine läuft.
2. Öffnen sie ein Terminal im "M295_LB_Projekt" Verzeichnis
3. Starten sie den Mysql Datenbank:

   ```bash
   docker-compose up -d
   ```
4. Versichern Sie sich das der Datenbank Container läuft mit:

   ```bash
   docker ps
   ```

### Schritt 3: Java Spring-boot Anwendung starten

1. Das Verzeichnis "M295_LB_Projekt im Visual Studio Code öffnen.
2. In das eigntliche Java Springboot Verzeichnis wechseln

   ```bash
   cd lb_projekt
   ```

3. Java Springboot Anwendung starten

   ```bash
   mvn spring-boot:run
   ```

4. Hoffen dass es keine Errors gibt

### Schritt 4: Insomnia einrichten

1. Insomnia starten.
2. Im Insomnia Programm naben dem Scratchpad auf den Pfeil drücken und "Import" wählen
3. Die Datei "M295_LB_Projekt_Insomnia" aus dem Projektordner auswählen 
4. Import abschliessen
5. Jetzt sollten alle Http-Requests vorhanden sein.


## Hilfestellungen

|||
|:------------- |:--------------- |
| Erstellen des docker-comopose file | Fabian Wehrle|
| Erstellen des Java Springboot Boilerplate| Unterlagen von Modul 295|
| Verbindung zwischen Java Springboot und Mysql Datenbank erstellen| Fabian Wehrle|
| Unterstützung beim Erstellen von meinen Controller Klassen| Chat GPT|
|Hilfe bei der Fehlersuche von meinen Http-Requests| Sven Schirmer |
| Unterstützung beim Erstellen von meinen Unit Tests| Unterlagen aus dem Modul 295 <br> ChatGPT|
| Unterstützung beim Erstellen von meinen Exceptions| Unterlagen aus dem Modul 295 <br> ChatGPT|


## Auswertung