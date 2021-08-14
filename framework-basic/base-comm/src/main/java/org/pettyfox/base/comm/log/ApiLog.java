package org.pettyfox.base.comm.log;



import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 启用日志注解
 * @author Petty Fox
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiLog {

    String log() default "";
    ApiLogType.Type type() default ApiLogType.Type.ACCOUNT_OPTION_LOG;
    ApiLogType.OptionType optionType() default ApiLogType.OptionType.OTHER;
    String bizName() default "";
    String key() default "name";
}
