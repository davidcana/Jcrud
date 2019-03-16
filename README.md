# JCrud

[ZCrud](https://davidcana.github.io/Zcrud/) is a client side javascript API (also works as jQuery plugin) used to create AJAX based CRUD tables without coding HTML or Javascript. Any server side technology can work with ZCrud. ZCrud sends CRUD requests in JSON format to a server. This server do the CRUD and it responses using JSON.

JCrud is a server side Java API that works beside ZCrud. It contains several modules:
* **JCrud-core**. This is the core component of JCrud. It makes it easy to build Java objects for the requests and for the responses. But it does not do any CRUD.
* **JCrud-jdbc**. It uses the Java objects built using JCrud-core to retrieve and save data from/to a database using [JDBC](https://en.wikipedia.org/wiki/Java_Database_Connectivity).
* **JCrud-examples**. Example web application using Zcrud, JCrud-core and JCrud-JDBC.

## Installation

To use it in your Maven build add the dependencies:

```xml
    <dependency>
        <groupId>org.github.davidcana.jcrud</groupId>
        <artifactId>jcrud-core</artifactId>
        <version>0.1</version>
    </dependency>
    <dependency>
        <groupId>org.github.davidcana.jcrud</groupId>
        <artifactId>jcrud-jdbc</artifactId>
        <version>0.1</version>
    </dependency>
```

## Usage

In this example we are going to mange the CRUD of a java POJO class called Simple:

```java
package org.github.davidcana.jcrud.core.model;

import org.github.davidcana.jcrud.core.ZCrudEntity;
import org.github.davidcana.jcrud.core.annotations.JCRUDEntity;
import org.github.davidcana.jcrud.storages.JDBC.SimpleJDBCStorage;
import org.github.davidcana.jcrud.storages.JDBC.annotations.JDBCEntity;
import org.github.davidcana.jcrud.storages.JDBC.annotations.JDBCId;
import org.github.davidcana.jcrud.storages.JDBC.annotations.JDBCOrderedByDefault;

@JCRUDEntity(
        storage = SimpleJDBCStorage.class,
        jsFilePath="target/jcrud-examples-0.1/javascript/options/simple.js",
        jsFileVarName="constants.options.simple"
)
@JDBCEntity(table = "simple")
public class Simple implements ZCrudEntity {
    /*jcrud-class
    saveUserPreferences: true,

    pageConf: {
        defaultPageConf: {
            updateURL: 'CRUDManager.do?cmd=BATCH_UPDATE&table=simple',
            getRecordURL: 'CRUDManager.do?cmd=GET&table=simple'
        },
        pages: {
            list: {
                getGroupOfRecordsURL: 'CRUDManager.do?cmd=LIST&table=simple',
                fields: [
                    {
                        type: 'fieldsGroup'
                    }
                ],
                components: {
                    paging: {
                        defaultPageSize: 5,
                        pageSizes: [5, 10, 15, 50, 100]
                    },
                    sorting: {
                        isOn: true,
                        default: {
                            fieldId: 'id',
                            type: 'asc'
                        },
                        allowUser: true
                    }
                }
            },
            create: {
                fields: [
                    {
                        type: 'fieldsGroup'
                    }
                ]
            },
            update: {
                fields: [
                    {
                        type: 'fieldsGroup'
                    }
                ]
            },
            delete: {
                fields: [
                    {
                        type: 'fieldsGroup'
                    }
                ]
            }
        }
    },

    validation: {
        modules: 'security, date',
        rules: {}
    },

    i18n: {
        language: 'es',
        files: {
            en: [ 'en-common.json', 'en-simple.json' ],
            es: [ 'es-common.json', 'es-simple.json' ] 
        }
    },

    logging: {
        isOn: false
    },

    ajax: {
        ajaxPreFilter: function( data ){
            return JSON.stringify( data );
        }
    }
    */

    @JDBCId
    @JDBCOrderedByDefault(type = "ASC")
    private Integer id;
    /*jcrud-field
     */
    private String name;
    /*jcrud-field
            attributes:{
                rowHeader: {
                    style: 'width:40%'
                }
            }
     */

    private String description;
    /*jcrud-field
            type: 'textarea'
     */

    public Simple(){}

    [Getters, setters and more methods here]

}

```

## License
[LGPL](http://www.gnu.org/licenses/lgpl.html)

