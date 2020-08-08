package com.swat018.inflearnthejavatest.study;

import com.swat018.inflearnthejavatest.App;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import javax.persistence.Entity;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packagesOf = App.class)
public class ArchClassTests {

    @ArchTest
    ArchRule controllerClassRule = classes().that().haveSimpleNameEndingWith("Controller")
            .should().accessClassesThat().haveSimpleNameEndingWith("Service")
            .orShould().accessClassesThat().haveSimpleNameEndingWith("Repository");

    @ArchTest
    ArchRule repositoryClassRule = classes().that().haveSimpleNameEndingWith("Repository")
            .should().accessClassesThat().haveSimpleNameEndingWith("Service");

    @ArchTest
    ArchRule studyClassRule = classes().that().haveSimpleNameStartingWith("Study")
            .and().areNotEnums()
            .and().areNotAnnotatedWith(Entity.class)
            .should().resideInAPackage("..study..");
}
