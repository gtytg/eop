package cn.mldn.util.web.annotation;

import java.lang.annotation.*;

/**
 * 定义具体的映射路径，在Action的每一个方法上都进行路径定义
 * 为了方便进行操作，可以定义一个整合目录，类上定义父路径，方法上定义子路径
 * @author mldn
 */
@Documented
@Target({ElementType.TYPE,ElementType.METHOD}) 
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
	public String value() ; // 映射路径
}
