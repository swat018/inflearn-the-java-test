package me.swat018.inflearnthejavatest.study;

import me.swat018.inflearnthejavatest.domain.Member;
import me.swat018.inflearnthejavatest.domain.Study;
import me.swat018.inflearnthejavatest.member.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudyServiceTest {

//    @Mock
//    MemberService memberService;
//
//    @Mock
//    StudyRepository studyRepository;

    @Test
    void createStudyService(@Mock MemberService memberService,
                            @Mock StudyRepository studyRepository) {
//        MemberService memberService = Mockito.mock(MemberService.class);
//        StudyRepository studyRepository = Mockito.mock(StudyRepository.class);

//        Optional<Member> optional = memberService.findById(1L);
////        assertNull(optional);
//        memberService.validate(2L);

        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);

        Member member = new Member();
        member.setId(1L);
        member.setEmail("swat018@gmail.com");

//        when(memberService.findById(1L)).thenReturn(Optional.of(member));
//
//        Study study = new Study(10, "java");
//
//        Optional<Member> findById = memberService.findById(1L);
//        assertEquals("swat018@gmail.com", findById.get().getEmail());
//
//        studyService.createNewStudy(1L, study);
//
//        when(memberService.findById(any())).thenReturn(Optional.of(member));
//
//        assertEquals("swat018@gmail.com", memberService.findById(1L).get().getEmail());
//        assertEquals("swat018@gmail.com", memberService.findById(2L).get().getEmail());
//
////        when(memberService.findById(1L)).thenThrow(new RuntimeException());
//        doThrow(new IllegalArgumentException()).when(memberService).validate(1L);
//
//        assertThrows(IllegalArgumentException.class, () -> {
//           memberService.validate(1L);
//        });
//
//        memberService.validate(2L);

        when(memberService.findById(any()))
                .thenReturn(Optional.of(member))
                .thenThrow(new RuntimeException())
                .thenReturn(Optional.empty());

        Optional<Member> byId = memberService.findById(1L);
        assertEquals("swat018@gmail.com", byId.get().getEmail());

        assertThrows(RuntimeException.class, () -> {
            memberService.findById(2L);
        });

        assertEquals(Optional.empty(), memberService.findById(3L));
    }


}