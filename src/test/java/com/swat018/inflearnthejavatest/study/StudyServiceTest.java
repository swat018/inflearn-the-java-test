package com.swat018.inflearnthejavatest.study;

import com.swat018.inflearnthejavatest.domain.Member;
import com.swat018.inflearnthejavatest.domain.Study;
import com.swat018.inflearnthejavatest.domain.StudyStatus;
import com.swat018.inflearnthejavatest.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@Testcontainers
@Slf4j
@ContextConfiguration(initializers = StudyServiceTest.ContainerPropertyInitializer.class)
class StudyServiceTest {

    @Mock
    MemberService memberService;

    @Autowired
    StudyRepository studyRepository;

//    @Autowired
//    Environment environment;

    @Value("${container.port}")
    int port;

/*    @Container
    static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer()
            .withDatabaseName("studytest");*/
/*    @Container
    static GenericContainer postgreSQLContainer = new GenericContainer("postgres")
//        .withExposedPorts(5432)
        .withEnv("POSTGRES_DB", "studytest");*/
    @Container
    static DockerComposeContainer composeContainer =
        new DockerComposeContainer(new File("src/test/resources/docker-compose.yml"))
        .withExposedService("study-db",5432);

 /*   @BeforeAll
    static void beforeAll() {
        Slf4jLogConsumer logConsumer = new Slf4jLogConsumer(log);
        postgreSQLContainer.followOutput(logConsumer);
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("===================");
        System.out.println(port);
//        System.out.println(postgreSQLContainer.getMappedPort(5432));
//        System.out.println(environment.getProperty("container.port"));
//        System.out.println(postgreSQLContainer.getLogs());
        studyRepository.deleteAll();
    }*/

/*    @BeforeAll
    static void beforeAll() {
        postgreSQLContainer.start();
    }

    @AfterAll
    static void afterAll() {
        postgreSQLContainer.stop();
    }*/


    @Test
    void createNewStudy() {
/*        MemberService memberservice = mock(MemberService.class);
        StudyRepository studyRepository = mock(StudyRepository.class);*/
        // Given
        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);

        Member member = new Member();
        member.setId(1L);
        member.setEmail("swat018@gmail.com");

/*        // 메소드가 동일한 매개변수로 여러번 호출될 때 각기 다르게 행동호도록 조작할 수도 있다.
        when(memberService.findById(any()))
                .thenReturn(Optional.of(member))
                .thenThrow(new RuntimeException())
                .thenReturn(Optional.empty());

        Optional<Member> byId = memberService.findById(1L);
        assertEquals("swat018@gmail.com", byId.get().getEmail());

        assertThrows(RuntimeException.class, () -> {
            memberService.findById(2L);
        });

        assertEquals(Optional.empty(), memberService.findById(3L));*/
        Study study = new Study(10, "테스트");
//        Study study = new Study(10, "테스트");
        // TODO memberService 객체에 findById 메소드를 1L 값으로 호출하면 Optional.of(member) 객체를 리턴하도록 Stubbing
//        when(memberservice.findById(1L)).thenReturn(Optional.of(member));
        // TODO studyRepository 객체에 save 메소드를 study 객체로 호출하면 study 객체 그대로 리턴하도록 Stubbing
//        when(studyRepository.save(study)).thenReturn(study);

        given(memberService.findById(1L)).willReturn(Optional.of(member));
//        given(studyRepository.save(study)).willReturn(study);

        // When
        studyService.createNewStudy(1L, study);

        // Then
//
//        assertEquals(member, study.getOwner());
          assertEquals(1L, study.getOwnerId());


//        verify(memberservice, times(1)).notify(study);
//        verifyNoMoreInteractions(memberservice);

        then(memberService).should(times(1)).notify(study);
        then(memberService).shouldHaveNoMoreInteractions();

/*        verify(memberservice, never()).validate(any());

        InOrder inOrder = inOrder(memberservice);
        inOrder.verify(memberservice).notify(study);
        inOrder.verify(memberservice).notify(member);*/

/*
        Study study = new Study(10, "java");

        Optional<Member> findById = memberservice.findById(1L);
        // 특정한 매개변수를 받은 경우 특정한 값을 리턴하거나 예뢰를 던지도록 만들 수 있다.
        assertEquals("swat018@gmail.com", memberservice.findById(1L).get().getEmail());
        assertEquals("swat018@gmail.com", memberservice.findById(2L).get().getEmail());

//        when(memberservice.findById(1L)).thenThrow(new RuntimeException());

        // Void메소드특정매개변수를받거나호출된경우예외를발생시킬수있다.
        doThrow(new IllegalArgumentException()).when(memberservice).validate(1L);

        assertThrows(IllegalArgumentException.class, () -> {
            memberservice.validate(1L);
        });

        memberservice.validate(2L);

//        studyService.createNewStudy(1L, study);

//        Optional<Member> optional = memberservice.findById(1L);
//        memberservice.validate(2L);
*/
    }

    @DisplayName("다른 사용자가 볼 수 있도록 스터디를 공개한다.")
    @Test
    void openStudy() {
        // Given
        StudyService studyService = new StudyService(memberService, studyRepository);
        Study study = new Study(10, "더 자바, 테스트");
        assertNull(study.getOpenedDateTime());
/*        // TODO studyRepository Mock 객체의 save 메소드를 호출 시 study를 리턱하도록 만들기.
        given(studyRepository.save(study)).willReturn(study);*/

        // When
        studyService.openStudy(study);

        // Then
        // TODO study의 status가 OPENED로 변경했는지 확인
        assertEquals(StudyStatus.OPENED, study.getStatus());
        // TODO study의 openedDataTime이 null이 아닌지 확인
        assertNotNull(study.getOpenedDateTime());
        // TODO memberService의 notify(study)가 호출 됐는지 확인
        then(memberService).should().notify(study);
    }

    static class ContainerPropertyInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext context) {
//            TestPropertyValues.of("container.port=" + postgreSQLContainer.getMappedPort(5432))
            TestPropertyValues.of("container.port=" + composeContainer.getServicePort("study-db", 5432))
                    .applyTo(context.getEnvironment());
        }
    }
}