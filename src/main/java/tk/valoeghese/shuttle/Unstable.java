package tk.valoeghese.shuttle;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.CLASS;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Represents a part of the API which is Unstable: this may or may not be implemented in an implementation of Shuttle.<br/>
 * Methods marked with this annotation may be specific to the implementation (e.g. a method in the fabric implementation directly exposes fabric api).
 * However, they do not have to be exclusive to the implementation: another implementation of the API may choose to implement an unstable method from this (or other) implementations,
 * though if they do so, they should similarly mark the method with this annotation in their implementation.
 */
@Retention(CLASS)
@Target(METHOD)
public @interface Unstable {
}
