package org.github.davidcana.jcrud.storages.JDBC.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(FIELD)
public @interface JDBCOrderedByDefault {
	String type() default "ASC";
}
