package base.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)//表名该注释应该保留多长时间
public @interface RequestMapping {
	public String value();

}
