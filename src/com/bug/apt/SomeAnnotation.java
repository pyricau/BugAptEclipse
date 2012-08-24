package com.bug.apt;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A useless annotation that is not processing by the annotation processor. Any
 * annotation can be used instead of this one to reproduce the bug.
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.FIELD)
public @interface SomeAnnotation {

	int value();

}
