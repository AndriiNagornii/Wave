package core.chain;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value= RetentionPolicy.RUNTIME)
public @interface Stage {

    String ID();
    String chainName() default "main";

}
