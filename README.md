# PPJ
Lukáš Kořínek
V tomto repozitáři se nacházejí jednotlivé úkoly na PPJ a semestrální projekt


**Semestrální projekt -> složka MeteorologicalDataApp**

  

## MeteorologicalDataApp
Spring Boot webová aplikace dle zadání s frontendem React.


### Databáze:
NoSQL - Cassandra - použil jsem cloudové řešení DataStax Astra DB - připojí se samo k již existující databázi

SQL - PostgreSQL - před spuštěním JAR souboru je potřeba nejprve mít nainstalováno PostgreSQL a vytvořit novou databázi s názvem "meteo_app", tak aby se aplikace mohla připojit k databázi, která je takto definována v konfiguračním souboru:

    spring:
        # PostgreSQL
        datasource:
            url: jdbc:postgresql://localhost:5432/meteo_app
            username:
            password:



### API Endpointy:
React frontend pokrývá základní funkcionality jako přidání měst a států a zobrazení požadovaných informací. Update-rate je defaultně nastavený na 15s (takže po přidání města může trvat až 15s než se zobrazí první stažená data). Pro další funkcionality jako import/export CSV nebo kompletní REST API lze využít následující endpointy:


Zobrazení aktuálního počasí:

/api/latest/{id}

Zobrazení průměru za poslední den:

/api/average/day/{id}

Zobrazení průměru za poslední týden:

/api/average/week/{id}

Zobrazení průměru za posledních 14 dní:

/api/average/14days/{id}

Export měření do CSV:

/api/export/{id}

Import měření z CSV:

/api/import/{id}
  

Jinak klasické REST API endpointy:

/api/city/{id}

/api/cities

/api/state/{id}

/api/states

/api/measurement/{id}

/api/measurements