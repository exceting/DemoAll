package demo.spring.selectors;

import demo.spring.configurations.HelloWorldConfiguration;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class HelloWorldImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        System.out.println("ppppp    >   " + HelloWorldConfiguration.class.getName());
        return new String[]{HelloWorldConfiguration.class.getName()};
    }
}
