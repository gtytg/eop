package cn.mldn.util.web.annotation;

import java.lang.annotation.*;

/**
 * 定义业务层的注解
 */
@Documented
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Repository {
	// 定义的是类的标记，应该根据标记来获取指定的类对象
	public String value() default "nothing" ;
}
