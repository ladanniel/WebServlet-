package base.annotation;
//¿ª·¢µÄ×¢ÊÍ
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
	public String value();

}
