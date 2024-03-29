package top.xuxuzhaozhao.demo.core.aop;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AnnotationLog {

    String remark() default "";
}
