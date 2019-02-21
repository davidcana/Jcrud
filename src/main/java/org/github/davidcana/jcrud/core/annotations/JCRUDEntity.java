package org.github.davidcana.jcrud.core.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.github.davidcana.jcrud.storages.Storage;

@Retention(RUNTIME)
@Target(TYPE)
public @interface JCRUDEntity {
	Class<? extends Storage> storage();
	String urlParameter() default "";
}
