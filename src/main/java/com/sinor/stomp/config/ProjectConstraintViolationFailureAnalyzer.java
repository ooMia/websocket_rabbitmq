package com.sinor.stomp.config;

import java.util.NoSuchElementException;
import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConstraintViolationFailureAnalyzer extends AbstractFailureAnalyzer<NoSuchElementException> {

    @Override
    protected FailureAnalysis analyze(Throwable rootFailure, NoSuchElementException cause) {
        return new FailureAnalysis("rootFailure.toString()", cause.getCause().toString(), cause);
    }
}
