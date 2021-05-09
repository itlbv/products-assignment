# product-service

### Requirements
* Java 11
* Maven

### Getting started
* Open the project (if you use Intellij, just use "open" and then choose `pom.xml`)
* Run the project and access the url: ``http://localhost:8080/product-service/v1/hello`` - then you should see
> "Hello world!"

## The Case - English
### Introduction
To ensure a certain amount of quality on the data and information given to our end users in our app. We wish to create a product library where we have some common, quality assured, products which our customers (restaurants) can use in our app (if they have it in their restaurant of course).

When you visit a restaurant and place an order its common to choose between, what we call, variants. One example is that you can have a big and small coca cola, where a big one is 0,5 litres while a small one is 0,3 litres. The same goes for food, for example if you order a hamburger, this can be ordered in 150 grams and 300 grams.

### Product example
A structure of a product **can** for example be given as the one below as a basis (have a look at `/data/products.zip` for data dump in `json`)
```json
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
    }
```

### Exercise
We wish to make a product library where we should be able to:

- Add new products
- Show all products
- Remove one or more products
- Change a product
- Add variant(s) to a product
- Remove one or more variants from a product
- When a product is changed or a variant of the product, the belonging variant and product needs to be versioned.

These operations does not need to be shown graphical, but we would be glad if its shown through an API.