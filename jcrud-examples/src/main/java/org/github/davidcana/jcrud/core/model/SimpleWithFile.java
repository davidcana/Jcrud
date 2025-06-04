package org.github.davidcana.jcrud.core.model;

import java.util.Objects;

import org.github.davidcana.jcrud.core.ZCrudEntity;
import org.github.davidcana.jcrud.core.annotations.JCRUDEntity;
import org.github.davidcana.jcrud.core.model.storages.SimpleWithFileJDBCStorage;
import org.github.davidcana.jcrud.storages.JDBC.annotations.JDBCEntity;
import org.github.davidcana.jcrud.storages.JDBC.annotations.JDBCId;
import org.github.davidcana.jcrud.storages.JDBC.annotations.JDBCOrderedByDefault;

@JCRUDEntity(
		storage = SimpleWithFileJDBCStorage.class, 
		jsFilePath="target/jcrud-examples-0.1/javascript/options/simpleWithFile.js",
		jsFileVarName="constants.options.simple"
)
@JDBCEntity(table = "simple_with_file")
public class SimpleWithFile implements ZCrudEntity {
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
	
	private File file;
	
	public SimpleWithFile(){}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, file, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SimpleWithFile other = (SimpleWithFile) obj;
		return Objects.equals(description, other.description) && Objects.equals(file, other.file)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "SimpleWithFile [id=" + id + ", name=" + name + ", description=" + description + ", file=" + file + "]";
	}

}
