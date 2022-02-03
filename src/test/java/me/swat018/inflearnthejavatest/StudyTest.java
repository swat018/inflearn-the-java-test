package me.swat018.inflearnthejavatest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.*;

import java.time.Duration;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudyTest {

    int value = 1;

//    @Test
    @Order(2)
    @FastTest
    @DisplayName("스터디 만들기 fast")
//    @EnabledOnOs({OS.MAC, OS.LINUX})
//    @EnabledOnJre({JRE.JAVA_8, JRE.JAVA_9, JRE.JAVA_10, JRE.JAVA_11})
//    @EnabledIfEnvironmentVariable(named = "TEST_ENV", matches = "LOCAL")
//    @Tag("fast")
    void create_new_study() {
//        Study study = new Study(-10);
//
//        assertAll(
//                () -> assertNotNull(study),
//                () -> assertEquals(StudyStatus.DRAFT, study.getStatus(),
//                    () -> "스터디를 처음 만들면 " + StudyStatus.DRAFT + " 상태다."),
//                () -> assertTrue(study.getLimit() > 0, "스터디 최대 참석 가능 인원은 0보다 커야 한다.")
//        );

//        IllegalArgumentException exception =
//                assertThrows(IllegalArgumentException.class, () -> new Study(-10));
//        String message = exception.getMessage();
//        assertEquals("limit는 0보다 커야 한다.", message);

//        assertTimeout(Duration.ofMillis(100), () -> {
//            new Study(10);
//            Thread.sleep(300);
//        });
//        // TODO ThreadLocal

//        String test_env = System.getenv("TEST_ENV");
//        System.out.println(test_env);
//        assumeTrue("LOCAL".equalsIgnoreCase(test_env));
//
//        assumingThat("LOCAL".equalsIgnoreCase(test_env), () -> {
//            Study actual = new Study(100);
//            assertThat(actual.getLimit()).isGreaterThan(0);
//        });
//        assumingThat("jinwoo".equalsIgnoreCase(test_env), () -> {
//            Study actual = new Study(10);
//            assertThat(actual.getLimit()).isGreaterThan(0);
//        });
//
//        Study actual = new Study(10);
//        assertThat(actual.getLimit()).isGreaterThan(0);

//        Study actual = new Study(100);
//        assertThat(actual.getLimit()).isGreaterThan(0);
        System.out.println(this);
        System.out.println(value++);
        Study actual = new Study(1);
        assertThat(actual.getLimit()).isGreaterThan(0);

    }

//    @Test
    @Order(1)
    @SlowTest
    @DisplayName("스터디 만들기 slow")
//    @DisabledOnOs(OS.MAC)
//    @EnabledOnJre(JRE.OTHER)
//    @Tag("slow")
    void create_new_study_again() {
        System.out.println(this);
        System.out.println("create1 " + value++);
    }
    @Order(3)
    @DisplayName("스터디 만들기")
    @RepeatedTest(value = 10, name = "{displayName}, {currentRepetition}/{totalRepetitions}")
    void repeatTest(RepetitionInfo repetitionInfo) {
        System.out.println("test " + repetitionInfo.getCurrentRepetition() + "/" +
                repetitionInfo.getTotalRepetitions());
    }

//    @DisplayName("스터디 만들기")
//    @ParameterizedTest(name = "{index} {displayName} message={0}")
////    @ValueSource(strings = {"날씨가", "많이", "추워지고", "있네요"})
////    @NullAndEmptySource
//    @ValueSource(ints = {10, 20, 40})
//    void parameterizedTest(@ConvertWith(StudyConverter.class) Study study) {
//           System.out.println(study.getLimit());
//    }
    static class StudyConverter extends SimpleArgumentConverter {

    @Override
    protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
        assertEquals(Study.class, targetType, "Can only convert to Study");
        return new Study(Integer.parseInt(source.toString()));
        }
    }

//    @DisplayName("스터디 만들기")
//    @ParameterizedTest(name = "{index} {displayName} message={0}")
//    @CsvSource({"10, '자바스터디'","20, '스프링'"})
//    void parameterizedTest(ArgumentsAccessor argumentsAccessor) {
//        Study study = new Study(argumentsAccessor.getInteger(0), argumentsAccessor.getString(1));
//        System.out.println(study);
//    }
    @Order(4)
    @DisplayName("스터디 만들기")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @CsvSource({"10, '자바스터디'","20, '스프링'"})
    void parameterizedTest(@AggregateWith(StudyAggregator.class) Study study) {
        System.out.println(study);
    }

    static class StudyAggregator implements ArgumentsAggregator {

        @Override
        public Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext context) throws ArgumentsAggregationException {
            return new Study(accessor.getInteger(0), accessor.getString(1));
        }
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("before all");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("after all");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("before each");
    }

    @AfterEach
    void afterEach() {
        System.out.println("after each");
    }



}