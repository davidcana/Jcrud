package org.github.davidcana.jcrud.storages.JDBC.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.github.davidcana.jcrud.storages.JDBC.AbstractJDBCStorage;

@Retention(RUNTIME)
@Target(FIELD)
public @interface JDBCOneToMany {
	Class<? extends AbstractJDBCStorage> storage();
}
