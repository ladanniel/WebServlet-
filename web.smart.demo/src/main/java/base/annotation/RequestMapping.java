package base.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)//������ע��Ӧ�ñ����೤ʱ��
public @interface RequestMapping {
	public String value();

}
