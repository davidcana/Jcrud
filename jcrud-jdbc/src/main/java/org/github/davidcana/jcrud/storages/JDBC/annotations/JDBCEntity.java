package org.github.davidcana.jcrud.storages.JDBC.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(TYPE)
public @interface JDBCEntity {
	String table();
	//String masterKeyField() default "";
}
