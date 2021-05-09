# product-service

### Requirements
* Java 11
* Maven

### Getting started
* Open the project (if you use Intellij, just use "open" and then choose `pom.xml`)
* Run the project and access the url: ``http://localhost:8080/product-service/v1/hello`` - then you should see
> "Hello world!"


## The Case
### Introduksjon

For å kunne sikre informasjonskvalitet hos våre kunder (restauranter) ønsker vi å ha en produktkatalog som inneholder et gitt sett med etterhvert kvalitetsikrede produkter som kundene våre kan legge til i menyen. Gitt da at de selger dette produktet.

Når man går på en restaurant og bestiller så er det vanlig å kunne velge mellom varianter av et produkt. Et eksempel er at man kan ha stor og liten cola, hvor en stor cola er 0,5L mens en liten er 0,3L. Og det samme har man for mat, for eksempel en hamburger kan man få i 150 gram, 200 gram osv.

### Produkteksempel

En produktstruktur kan **feks** se slik ut for å ha et utgangspunkt (se `products.json` i katalogen `products/src/main/data` for data dump)

```jsx
{
        "id": "b7e85065-944d-4226-b153-bcde0c559a4f",
        "name": "Small Granite Salad",
        "description": "Adipisci alias et minus nihil aliquam quis quis.",
        "version": 93677,
        "image": "http://lorempixel.com/640/480/food",
        "variants": [
            {
                "id": "402fa2e8-5b66-4402-8938-d3f27d49bd55",
                "variantName": "Fundamental",
                "quantity": 71086,
                "unit": "litres",
                "price": "703.75",
                "vat": "0.64",
                "currency": "NOK",
                "version": 97979,
                "created": "2020-03-17T08:47:15.226Z",
                "updated": "2020-07-27T20:14:44.983Z"
            },
            {
                "id": "424fa6fe-220d-43da-8a3f-2df0898159d0",
                "variantName": "out-of-the-box",
                "quantity": 37985,
                "unit": "litres",
                "price": "553.41",
                "vat": "0.69",
                "currency": "NOK",
                "version": 20549,
                "created": "2020-04-10T17:32:53.775Z",
                "updated": "2020-07-28T04:46:48.338Z"
            }
        ],
        "created": "2019-12-29T13:01:31.473Z",
        "updated": "2020-07-28T14:04:32.685Z"
    },
```

### Oppgave

Vi ønsker å lage en produktkatalog hvor man skal kunne:

- Legge til nye produkter
- Vise alle produkter
- Fjerne ett eller flere produkter
- Endre på et produkt
- Legge til variant på et produkt
- Fjerne en eller flere varianter fra et produkt
- Når man endrer på et produkt eller en variant tilhørende produktet så må produktet og evt varianten versjoneres
- Et produkt har en eier (den som legger inn produktet), og det er bare eieren (de som har rettigheter) som skal kunne endre på produktet. Feks vil produktet "Frydenlund fatøl" bli lagt inn av Rignes som da vil være eier av produktet og dermed ha rettigheter til å endre navn, beskrivelse osv.

Disse operasjonene trenger ikke å vises grafisk, men gjerne igjennom et API hvis det er mulig.

