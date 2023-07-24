# ports-adapters-spring-cucumber

## Overview
This is the evolution of the proyect now with cucumber

## Start
Run the example of connecting to OpenFin and creating applications

1. Start the application

2. Data is auto initiated and inserted into H2 db on application start-up

3. Hit the rest api '/pricing/list' with HTTP GET method. passing params : product-id, brand-id and a date (date in ISO format YYYY-MM-DDTHH:mm:SS)

4. Internal logic is to get the matching record from db.

```java
   List<Pricing> findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(Long productId, Long brandId, LocalDateTime startDate );
```

5. If output from db is multiple records there we compare the priority. And the product with highest priority (least number) will be selected.

6. Priority 0 - highest , 5 - lowest.

7. Hence the final output of api is pricing based on all above conditions

## POSTMAN COLLECTION

Postman collection file is in resources folder /zara-adapters/src/main/resources/ZARA DEMO RAFAEL V5 Adapter-port-cucumber.postman_collection.json

```JSON
{
	"info": {
		"_postman_id": "2a621e6e-a415-4e8d-93f4-f98be64457ab",
		"name": "ZARA DEMO RAFAEL V5 Adapter-port-cucumber",
		"schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json",
		"_exporter_id": "8449707"
	},
	"item": [
		{
			"name": "start app",
			"item": [
				{
					"name": "create brand",
					"request": {
						"method": "POST",
						"header": [
							{
								"key": "Content-Type",
								"value": "application/json",
								"type": "text"
							}
						],
						"body": {
							"mode": "raw",
							"raw": "{\n  \n  \"name\": \"Nike\"\n}"
						},
						"url": {
							"raw": "http://localhost:8080/brand",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"brand"
							]
						}
					},
					"response": []
				},
				{
					"name": "create product",
					"request": {
						"method": "POST",
						"header": [
							{
								"key": "Content-Type",
								"value": "application/json",
								"type": "text"
							}
						],
						"body": {
							"mode": "raw",
							"raw": "{\n  \n  \"name\": \"soccer-ball\"\n}"
						},
						"url": {
							"raw": "http://localhost:8080/product",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"product"
							]
						}
					},
					"response": []
				},
				{
					"name": "create pricing",
					"request": {
						"method": "POST",
						"header": [
							{
								"key": "Content-Type",
								"value": "application/json",
								"type": "text"
							}
						],
						"body": {
							"mode": "raw",
							"raw": "{\n  \"startDate\": \"2023-07-01T10:00:00\",\n  \"endDate\": \"2023-07-31T23:59:59\",\n  \"priceList\": \"Summer Sale\",\n  \"priority\": 1,\n  \"price\": 25.99,\n  \"currency\": \"USD\",\n  \"brand\": {\n    \"id\": 2,\n    \"name\": \"nike\"\n  },\n  \"product\": \n    {\n      \"id\": 2,\n      \"name\": \"soccer-ball\"\n    }\n  \n}"
						},
						"url": {
							"raw": "http://localhost:8080/pricing",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"pricing"
							]
						}
					},
					"response": []
				}
			]
		},
		{
			"name": "findpricing",
			"item": [
				{
					"name": "findPrice",
					"protocolProfileBehavior": {
						"disableBodyPruning": true
					},
					"request": {
						"method": "GET",
						"header": [
							{
								"key": "",
								"value": "",
								"type": "text",
								"disabled": true
							}
						],
						"body": {
							"mode": "raw",
							"raw": ""
						},
						"url": {
							"raw": "http://localhost:8080/pricing/list?productId=1&brandId=1&applicationDate=2023-07-14T10:00:00",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"pricing",
								"list"
							],
							"query": [
								{
									"key": "productId",
									"value": "1"
								},
								{
									"key": "brandId",
									"value": "1"
								},
								{
									"key": "applicationDate",
									"value": "2023-07-14T10:00:00"
								}
							]
						}
					},
					"response": []
				}
			]
		},
		{
			"name": "getlist",
			"item": [
				{
					"name": "getallPricing",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "http://localhost:8080/pricing",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"pricing"
							]
						}
					},
					"response": []
				},
				{
					"name": "getallBrands",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "http://localhost:8080/brand",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"brand"
							]
						}
					},
					"response": []
				},
				{
					"name": "getallProducts",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "http://localhost:8080/product",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"product"
							]
						}
					},
					"response": []
				}
			]
		},
		{
			"name": "getbyID",
			"item": [
				{
					"name": "getbyIDPricing",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "http://localhost:8080/pricing/1",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"pricing",
								"1"
							]
						}
					},
					"response": []
				},
				{
					"name": "getbyIDBrands",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "http://localhost:8080/brand/1",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"brand",
								"1"
							]
						}
					},
					"response": []
				},
				{
					"name": "getbyIDProducts",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "http://localhost:8080/product/1",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"product",
								"1"
							]
						}
					},
					"response": []
				}
			]
		},
		{
			"name": "updatebyID",
			"item": [
				{
					"name": "updatePricing",
					"request": {
						"method": "PUT",
						"header": [
							{
								"key": "Content-Type",
								"value": "application/json",
								"type": "text"
							}
						],
						"body": {
							"mode": "raw",
							"raw": "{\n    \"id\": 1,\n    \"brand\": {\n        \"id\": 1,\n        \"name\": \"Nikes\"\n    },\n    \"product\": \n        {\n            \"id\": 1,\n            \"name\": \"Shoes\"\n        }\n    ,\n    \"startDate\": \"2023-07-01T10:00:00\",\n    \"endDate\": \"2023-07-31T23:59:59\",\n    \"priceList\": \"Summer Sale\",\n    \"priority\": 1,\n    \"price\": 25.99,\n    \"currency\": \"EUR\"\n}"
						},
						"url": {
							"raw": "http://localhost:8080/pricing/1?Content-Type=application/json",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"pricing",
								"1"
							],
							"query": [
								{
									"key": "Content-Type",
									"value": "application/json"
								}
							]
						}
					},
					"response": []
				},
				{
					"name": "updateBrand",
					"request": {
						"method": "PUT",
						"header": [
							{
								"key": "Content-Type",
								"value": "application/json",
								"type": "text"
							}
						],
						"body": {
							"mode": "raw",
							"raw": "{\n  \n  \"name\": \"Zara\"\n}"
						},
						"url": {
							"raw": "http://localhost:8080/brand/1?Content-Type=application/json",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"brand",
								"1"
							],
							"query": [
								{
									"key": "Content-Type",
									"value": "application/json"
								}
							]
						}
					},
					"response": []
				},
				{
					"name": "updateProduct",
					"request": {
						"method": "PUT",
						"header": [
							{
								"key": "Content-Type",
								"value": "application/json",
								"type": "text"
							}
						],
						"body": {
							"mode": "raw",
							"raw": "{\n    \"id\": 1,\n    \"name\": \"belt\"\n}"
						},
						"url": {
							"raw": "http://localhost:8080/product/1?Content-Type=application/json",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"product",
								"1"
							],
							"query": [
								{
									"key": "Content-Type",
									"value": "application/json"
								}
							]
						}
					},
					"response": []
				}
			]
		},
		{
			"name": "deletebyID",
			"item": [
				{
					"name": "deletePricing",
					"request": {
						"method": "DELETE",
						"header": [
							{
								"key": "Content-Type",
								"value": "application/json",
								"type": "text"
							}
						],
						"body": {
							"mode": "raw",
							"raw": "{\n    \"id\": 1,\n    \"brand\": {\n        \"id\": 1,\n        \"name\": \"Nike\"\n    },\n    \"product\": \n        {\n            \"id\": 1,\n            \"name\": \"Shoes\"\n        }\n    ,\n    \"startDate\": \"2023-07-01T10:00:00\",\n    \"endDate\": \"2023-07-31T23:59:59\",\n    \"priceList\": \"Summer Sale\",\n    \"priority\": 1,\n    \"price\": 25.99,\n    \"currency\": \"EUR\"\n}"
						},
						"url": {
							"raw": "http://localhost:8080/pricing/1",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"pricing",
								"1"
							]
						}
					},
					"response": []
				},
				{
					"name": "deleteBrand",
					"request": {
						"method": "DELETE",
						"header": [
							{
								"key": "Content-Type",
								"value": "application/json",
								"type": "text"
							}
						],
						"body": {
							"mode": "raw",
							"raw": "{\n  \n  \"name\": \"Zara\"\n}"
						},
						"url": {
							"raw": "http://localhost:8080/brand/1",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"brand",
								"1"
							]
						}
					},
					"response": []
				},
				{
					"name": "deleteProduct",
					"request": {
						"method": "DELETE",
						"header": [
							{
								"key": "Content-Type",
								"value": "application/json",
								"type": "text"
							}
						],
						"body": {
							"mode": "raw",
							"raw": "{\n    \"id\": 1,\n    \"name\": \"belt\"\n}"
						},
						"url": {
							"raw": "http://localhost:8080/product/1",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"product",
								"1"
							]
						}
					},
					"response": []
				}
			]
		}
	]
}
```

## example calls
Find Pricing [GET]
```
http://localhost:8080/pricing?applicationDate=2020-06-14 00.00.00&productId=35455&brandId=1
```
Get all Pricing [GET]
```
http://localhost:8080/pricing
```
## Test scenarios

1. Test case for file PricingController is written in PricingControllerTest class where we have covered below scenrios

a. request at 10:00 on day 14 of product 35455 for brand.
b. request at 4:00 p.m. on day 14 of product 35455 for brand 1.
c. request at 9:00 p.m. on day 14 of product 35455 for brand 1.
d. request at 10:00 on day 15 of product 35455 for brand 1.
e. request at 9:00 p.m. on the 16th of product 35455 for brand 1.

2. Test case for repository class is written in PricingRepositoryTest class. Where we are inserting data in h2 db and retrieving it using our query.

3. Tests cases for the get, delete, update, create operations



```java
   public List<Pricing> getPriceListBaseOnDate(Long productId, Long brandId, LocalDateTime inputDate);
```
this query was optimized
5. If output from db is multiple records there we compare the priority. And the product with highest priority (least number) will be selected.

6. Priority 0 - highest , 5 - lowest.

7. Hence the final output of api is pricing based on all above conditions
8. Desing of the objects Brand, Product, Pricing by JPA
9. Posibility to create Brand, Product, Pring
   
10. Posibility to update Brand, Product, Pring
11. Posibility to delete Brand, Product, Pring
12. Handeled exceptions

## extra example calls
This methods shoul be call by POST
[Create Brand]
```
http://localhost:8080/brands
```
[Headers]
```
Content-Type : application/json
```
[Json :body:raw]
```
{
  
  "name": "Zara"
}
```
[Create product]
```
http://localhost:8080/product
```
[Headers]
```
Content-Type : application/json
```
[Json :body:raw]
```
{
  
  "name": "Belt"
}
```

[Create Pricing]
```
http://localhost:8080/pricing
```
[Headers]
```
Content-Type : application/json
```
[Json :body:raw]
```
{
  "startDate": "2023-07-01T10:00:00",
  "endDate": "2023-07-31T23:59:59",
  "priceList": "Summer Sale Man",
  "priority": 1,
  "price": 17.99,
  "currency": "USD",
  "brand": {
    "id": 2,
    "name": "Zara"
  },
  "product": 
    {
      "id": 2,
      "name": "belt"
    }
  
}
```
## Mandatory functios
Get all Pricing [GET]
```
http://localhost:8080/pricing
```
Find pricing [GET]
```
http://localhost:8080/pricing/list?productId=1&brandId=1&applicationDate=2023-07-14T10:00:00
```
## Extra functios
## Get
Get all Products [GET]
```
http://localhost:8080/products
```
Get all Brand [GET]
```
http://localhost:8080/brands
```

## Get by ID
Get Product by id [GET]
```
http://localhost:8080/products/1
```
Get Brand by id [GET]
```
http://localhost:8080/brands/1
```
Get Pricing by id [GET]
```
http://localhost:8080/pricing/1
```

## update by ID
This methods shoul be call by PUT
Update Brand [PUT]
```
http://localhost:8080/brands/1
```
[Headers]
```
Content-Type : application/json
```
[Update :body:raw]
```
{
  
  "name": "Zara"
}
```
Update product [PUT]
```
http://localhost:8080/products
```
[Headers]
```
Content-Type : application/json
```
[Json :body:raw]
```
{
  
  "name": "Belt"
}
```

Update Pricing [PUT]
```
http://localhost:8080/pricing/1
```
[Headers]
```
Content-Type : application/json
```
[Json :body:raw]
```
{
  "startDate": "2023-07-01T10:00:00",
  "endDate": "2023-07-31T23:59:59",
  "priceList": "Summer Sale Man",
  "priority": 1,
  "price": 17.99,
  "currency": "USD",
  "brand": {
    "id": 2,
    "name": "Zara"
  },
  "products": [
    {
      "id": 2,
      "name": "belt"
    }
  ]
}
```

## Delete by ID
Delete  Product [DELETE]
```
http://localhost:8080/products/1
```
Delete all Brand [DELETE]
```
http://localhost:8080/brands/1
```
Delete all Pricing [DELETE]
```
http://localhost:8080/pricing/1
```

