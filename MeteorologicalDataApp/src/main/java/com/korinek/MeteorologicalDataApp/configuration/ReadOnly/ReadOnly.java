package com.korinek.MeteorologicalDataApp.configuration.ReadOnly;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Component;

@Component
public class ReadOnly implements Condition {

    @Value("${app.read-only-mode}")
    private boolean readonlyMode;

    public boolean isReadOnlyModeOn() {
        return readonlyMode;
    }

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        String property = conditionContext.getEnvironment().getProperty("app.read-only-mode");
        return property.equals("true");
    }
}
