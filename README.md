# JCrud

[ZCrud](https://davidcana.github.io/Zcrud/) is a client side javascript API (also works as jQuery plugin) used to create AJAX based CRUD tables without coding HTML or Javascript. Any server side technology can work with ZCrud. ZCrud sends CRUD requests in JSON format to a server. This server do the CRUD and it responses using JSON.

JCrud is a server side Java API that works beside ZCrud. It contains several modules:
* **JCrud-core**. This is the core component of JCrud. It makes it easy to build Java objects for the requests and for the responses. But it does not do any CRUD.
* **JCrud-jdbc**. It uses the Java objects built using JCrud-core to retrieve and save data from/to a database using [JDBC](https://en.wikipedia.org/wiki/Java_Database_Connectivity)..
* **JCrud-examples**. Example web application using Zcrud, JCrud-core and JCrud-JDBC.

