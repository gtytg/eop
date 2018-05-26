package cn.mldn.util.web.annotation;

import java.lang.annotation.*;

/**
 * 定义控制器的描述注解，该注解定义过的类才可以进行请求的访问
 */
@Documented
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Controller {
	public String value() default "nothing" ;
}
