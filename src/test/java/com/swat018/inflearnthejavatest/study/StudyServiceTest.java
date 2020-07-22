package com.swat018.inflearnthejavatest.study;

import com.swat018.inflearnthejavatest.domain.Member;
import com.swat018.inflearnthejavatest.domain.Study;
import com.swat018.inflearnthejavatest.member.MemberService;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudyServiceTest {

/*    @Mock
    MemberService memberservice;

    @Mock
    StudyRepository studyRepository;*/

    @Test
    void createNewStudy(@Mock MemberService memberservice,
                            @Mock StudyRepository studyRepository) {
/*        MemberService memberservice = mock(MemberService.class);
        StudyRepository studyRepository = mock(StudyRepository.class);*/
        StudyService studyService = new StudyService(memberservice, studyRepository);
        assertNotNull(studyService);

        Member member = new Member();
        member.setId(1L);
        member.setEmail("swat018@gmail.com");

        // 메소드가 동일한 매개변수로 여러번 호출될 때 각기 다르게 행동호도록 조작할 수도 있다.
        when(memberservice.findById(any()))
                .thenReturn(Optional.of(member))
                .thenThrow(new RuntimeException())
                .thenReturn(Optional.empty());

        Optional<Member> byId = memberservice.findById(1L);
        assertEquals("swat018@gmail.com", byId.get().getEmail());

        assertThrows(RuntimeException.class, () -> {
            memberservice.findById(2L);
        });

        assertEquals(Optional.empty(), memberservice.findById(3L));



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
}