package com.indexlabs.utils;

import org.springframework.test.context.ActiveProfilesResolver;
import org.springframework.test.context.support.DefaultActiveProfilesResolver;

public class ActiveProfileResolver implements ActiveProfilesResolver {
    private final DefaultActiveProfilesResolver defaultActiveProfilesResolver = new DefaultActiveProfilesResolver();

    @Override
    public String[] resolve(Class<?> aClass) {
        final String springProfileKey = "spring.profiles.active";
        return System.getProperties().containsKey(springProfileKey)
                ? System.getProperty(springProfileKey).split("\\s*,\\s*")
                : defaultActiveProfilesResolver.resolve(aClass);
    }
}
