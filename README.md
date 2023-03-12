
# Costumer Statement Processor

Bank receives monthly deliveries of customer statement records. This information is delivered in two formats, CSV and XML. These records need to be validated.

There are two validations:

*all transaction references should be unique

*the end balance needs to be validated

At the end of the processing, a report needs to be created which will display both the transaction reference and description of each of the failed records.

## API Reference

```http
  POST /v1/transactions/reports
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `file` | `Multipart file(CSV)` | **Required**.  |



```http
  POST /v1/transactions/reports
```
| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `records` | `XML file` | **Required**.  |





#### Documentation 

```http
  localhost:8080/documentation 
```



## Tech Stack

**Technologies:** Java 17, Spring Boot, Docker, Junit, Mockito, Integration Test.

