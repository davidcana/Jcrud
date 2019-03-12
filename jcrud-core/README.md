# JCrud-core

[ZCrud](https://davidcana.github.io/Zcrud/) is a javascript API (also works as jQuery plugin) used to create AJAX based CRUD tables without coding HTML or Javascript. Any server side technology can work with ZCrud.

JCrud is a Java API that works beside ZCrud:
* ZCrud is a client side API. Written in Javascript.
* JCrud is a server side API. Written in Java.

ZCrud sends CRUD requests in JSON format to a server. This server do the CRUD and responses using JSON.     

JCrud-core is the core component of JCrud. It makes it easy to build Java objects for the requests and for the responses. But it does not do any CRUD.

This can be done using [JCrud-JDBC](https://github.com/davidcana/Jcrud-jdbc/), a component that use the Java objects built using JCrud-core to retrieve and save data from/to a database using [JDBC](https://en.wikipedia.org/wiki/Java_Database_Connectivity).
