package me.swat018.inflearnthejavatest;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import javax.persistence.Entity;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packagesOf = InflearnTheJavaTestApplication.class)
public class ArchClassTest {

    @ArchTest
    ArchRule controllerClassRule = classes().that().haveSimpleNameEndingWith("Controller")
            .should().accessClassesThat().haveSimpleNameEndingWith("Service")
            .orShould().accessClassesThat().haveSimpleNameEndingWith("Repository");

    @ArchTest
    ArchRule repositoryClassRule = noClasses().that().haveSimpleNameEndingWith("Repository")
            .should().accessClassesThat().haveSimpleNameEndingWith("Service");

    @ArchTest
    ArchRule studyClassRule = classes().that().haveSimpleNameStartingWith("Study")
            .and().areNotEnums()
            .and().areNotAnnotatedWith(Entity.class)
            .should().resideInAnyPackage("..study..");
}
