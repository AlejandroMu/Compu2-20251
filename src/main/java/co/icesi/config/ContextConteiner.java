package co.icesi.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ContextConteiner {
    private static ApplicationContext context;


    public static ApplicationContext getContext(){
        if(context == null){
            context = new ClassPathXmlApplicationContext("applicationContext.xml");
            
        }
        return context;
    }
}
