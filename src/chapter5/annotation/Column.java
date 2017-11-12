package chapter5.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
	
	public String name() default "";
	
	public boolean nullable() default true;
	
	public boolean unique() default false;
	
	public int length() default 255;

}
