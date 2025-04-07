# Registration System

## Popis projektu
Jednoduchý registrační systém pro správu uživatelů pomocí REST API. Aplikace umožňuje vytvářet nové uživatele, upravovat jejich údaje, vyhledávat uživatele (včetně detailních informací) a odstraňovat uživatelské záznamy. Projekt je postaven na technologii Spring Boot a využívá MySQL databázi pro ukládání dat. Slouží především k demonstraci základních CRUD operací nad uživateli a k testování pomocí připravené Postman kolekce.

## Použité technologie
- **Spring Boot** – Framework pro tvorbu samostatně spustitelných Java aplikací.
- **Spring Web (MVC)** – Modul Spring Boot pro vytváření RESTful webových služeb.
- **Spring Data JPA** – Modul pro snadnou práci s databází pomocí Java Persistence API (Hibernate).
- **MySQL** – Relační databázový systém použitý pro persistenci dat aplikace.

## Spuštění aplikace
1. **Nastavení databáze:** Ujistěte se, že máte nainstalovaný a spuštěný MySQL server. Vytvořte novou databázi pro tuto aplikaci (např. s názvem `registration_system`). Pro vytvoření tabulek a inicializačních dat použijte přiložený SQL skript (viz poznámka níže).
2. **Konfigurace připojení (volitelně):** Pokud je potřeba, upravte v souboru `src/main/resources/application.properties` přihlašovací údaje k databázi (uživatelské jméno, heslo, název hostitele/databáze), aby odpovídaly vaší lokální konfiguraci MySQL.
3. **Spuštění aplikace:** V kořenovém adresáři projektu spusťte aplikaci pomocí Maven příkazu:  
   ```bash
   mvn spring-boot:run
   ``` 
   Tímto se spustí embedded server (Tomcat) na adrese `http://localhost:8080`. Alternativně můžete aplikaci sestavit pomocí `mvn clean package` a poté spustit vzniklý `.jar` soubor příkazem `java -jar nazev-souboru.jar`.

> **Poznámka:** SQL skript pro inicializaci databáze (`my_db.sql`) je uložen ve složce `src/main/resources`. Před prvním spuštěním aplikace ho spusťte na vaší MySQL databázi – skript vytvoří potřebné tabulky (a případně testovací data) pro správný běh aplikace.

## Postman kolekce
Pro snadné testování API je k dispozici připravená Postman kolekce. Tuto kolekci lze naimportovat do aplikace Postman a ihned využít předdefinované požadavky na jednotlivé koncové body API:
- **Umístění souboru:** Soubor s kolekcí `registration-system.postman_collection.json` naleznete ve složce `postman` v kořenovém adresáři projektu.
- **Import do Postman:** V Postman aplikaci klikněte na tlačítko **Import**, vyberte uvedený JSON soubor kolekce a potvrďte import. Po importu se v Postmanu objeví kolekce „Registration System API“ obsahující požadavky pro vytvoření uživatele, úpravu uživatele, získání jednoho/všech uživatelů (včetně detailního režimu) a smazání uživatele.
- **Odkaz na kolekci:** Pro rychlý přístup můžete také otevřít přímo soubor kolekce: [registration-system.postman_collection.json](postman/registration-system.postman_collection.json).

Nyní můžete pomocí Postmanu odesílat HTTP požadavky na `http://localhost:8080/api/v1/users` (dle importované kolekce) a testovat funkcionalitu registračního systému. Příklady požadavků a očekávaných odpovědí jsou již součástí kolekce. Hodně štěstí při testování!
