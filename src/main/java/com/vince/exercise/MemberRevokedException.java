package com.vince.exercise;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

public class MemberRevokedException extends RuntimeException implements GraphQLError {

    private Map<String, Object> extensions = new HashMap<>();

    public MemberRevokedException(String message, Integer invalidId) {
        super(message);
        extensions.put("invalidId", invalidId);
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public Map<String, Object> getExtensions() {
        return extensions;
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.ValidationError;
    }
}
