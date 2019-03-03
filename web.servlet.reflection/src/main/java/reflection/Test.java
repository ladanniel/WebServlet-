package reflection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Test {
//Retention 保留，描述注解的，元注解（用来描述其他注解的注解）runtime 注解保持到运行的时候 policy 政策、策略
	public String value();

}
