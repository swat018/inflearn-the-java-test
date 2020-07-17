package com.swat018.inflearnthejavatest;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Test
@Tag("slow")
//    @DisabledOnOs(OS.MAC)
//    @EnabledOnJre(JRE.OTHER)
//    @EnabledIfEnvironmentVariable(named = "TEST_ENV", matches = "jinwoo")
public @interface SlowTest {
}
