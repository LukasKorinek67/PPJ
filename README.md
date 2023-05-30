# PPJ
Lukáš Kořínek
V tomto repozitáři se nacházejí jednotlivé úkoly na PPJ a semestrální projekt


**Semestrální projekt -> složka MeteorologicalDataApp**

  
  
## MeteorologicalDataApp
Spring Boot webová aplikace dle zadání s frontendem React.


### Databáze:
SQL - PostgreSQL - pro otestování prosím změnit datasource k PostrgeSQL v konfiguračním souboru application.yml
NoSQL - Cassandra - použil jsem cloudové řešení DataStax Astra DB


### API Endpointy:
React frontend pokrývá základní funkcionality jako přidání měst a států a zobrazení požadovaných informací. Pro další funkcionality jako import/export CSV nebo kompletní REST API lze využít následující endpointy:


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