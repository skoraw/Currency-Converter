# Currency converter

## Concept:

API should take data from  http://api.nbp.pl/ in JSON format.
Should show currency of buying and selling USD from date to date with the difference between days ora dates.

## Running the application

Open project in any IDE.
Run Application.class
Open browse and write address with pattern:

```
http://api.nbp.pl/api/exchangerates/rates/{table}/{code}/{startDate}/{endDate}?format=json
```
where: 
* {table} – typ of table - only "C" for now
* {code} – currency code (standard ISO 4217)
* {startDate}, {endDate} - in format YYYY-MM-DD. Start day must be from 2002.01.02

