package com.bosovskyi.showstime.di.scopes;

import java.lang.annotation.Retention;

import javax.inject.Scope;
import javax.inject.Singleton;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by boss1088 on 2/27/17.
 */

@Scope
@Retention(RUNTIME)
@Singleton
public @interface AppScope {
}
