package reflection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Test {
//Retention ����������ע��ģ�Ԫע�⣨������������ע���ע�⣩runtime ע�Ᵽ�ֵ����е�ʱ�� policy ���ߡ�����
	public String value();

}
