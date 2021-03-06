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
        System.out.println("============");
        System.out.println(port);
        // Given
        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);

        Member member = new Member();
        member.setId(1L);
        member.setEmail("swat018@gmail.com");

/*        // ???????????? ????????? ??????????????? ????????? ????????? ??? ?????? ????????? ??????????????? ????????? ?????? ??????.
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
        Study study = new Study(10, "?????????");
//        Study study = new Study(10, "?????????");
        // TODO memberService ????????? findById ???????????? 1L ????????? ???????????? Optional.of(member) ????????? ??????????????? Stubbing
//        when(memberservice.findById(1L)).thenReturn(Optional.of(member));
        // TODO studyRepository ????????? save ???????????? study ????????? ???????????? study ?????? ????????? ??????????????? Stubbing
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
        // ????????? ??????????????? ?????? ?????? ????????? ?????? ??????????????? ????????? ???????????? ?????? ??? ??????.
        assertEquals("swat018@gmail.com", memberservice.findById(1L).get().getEmail());
        assertEquals("swat018@gmail.com", memberservice.findById(2L).get().getEmail());

//        when(memberservice.findById(1L)).thenThrow(new RuntimeException());

        // Void????????????????????????????????????????????????????????????????????????????????????.
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

    @DisplayName("?????? ???????????? ??? ??? ????????? ???????????? ????????????.")
    @Test
    void openStudy() {
        // Given
        StudyService studyService = new StudyService(memberService, studyRepository);
        Study study = new Study(10, "??? ??????, ?????????");
        assertNull(study.getOpenedDateTime());
/*        // TODO studyRepository Mock ????????? save ???????????? ?????? ??? study??? ??????????????? ?????????.
        given(studyRepository.save(study)).willReturn(study);*/

        // When
        studyService.openStudy(study);

        // Then
        // TODO study??? status??? OPENED??? ??????????????? ??????
        assertEquals(StudyStatus.OPENED, study.getStatus());
        // TODO study??? openedDataTime??? null??? ????????? ??????
        assertNotNull(study.getOpenedDateTime());
        // TODO memberService??? notify(study)??? ?????? ????????? ??????
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