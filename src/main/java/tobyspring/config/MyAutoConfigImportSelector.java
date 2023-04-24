package tobyspring.config;

import org.springframework.boot.context.annotation.ImportCandidates;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.List;

public class MyAutoConfigImportSelector implements DeferredImportSelector {
    private final ClassLoader classLoader;

    public MyAutoConfigImportSelector(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        List<String> autoConfigs = new ArrayList<>();

        // load함수는 "resources/META-INF/spring/full-qualified-annotation-name 파일의 형식으로 애노테이션을 찾음
        // MyAutoConfiguration애노테이션에서 imports를 모두 config를 진행
        ImportCandidates.load(MyAutoConfiguration.class,classLoader).forEach(autoConfigs::add);

        return autoConfigs.toArray(new String[0]);
    }
}
