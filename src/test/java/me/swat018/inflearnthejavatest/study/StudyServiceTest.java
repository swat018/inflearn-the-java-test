package me.swat018.inflearnthejavatest.study;

import me.swat018.inflearnthejavatest.domain.Member;
import me.swat018.inflearnthejavatest.domain.Study;
import me.swat018.inflearnthejavatest.domain.StudyStatus;
import me.swat018.inflearnthejavatest.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static me.swat018.inflearnthejavatest.domain.StudyStatus.OPENED;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudyServiceTest {

//    @Mock
//    MemberService memberService;
//
//    @Mock
//    StudyRepository studyRepository;

    @Test
    void createNewStudy(@Mock MemberService memberService,
                            @Mock StudyRepository studyRepository) {
//        MemberService memberService = Mockito.mock(MemberService.class);
//        StudyRepository studyRepository = Mockito.mock(StudyRepository.class);

//        Optional<Member> optional = memberService.findById(1L);
////        assertNull(optional);
//        memberService.validate(2L);

//        StudyService studyService = new StudyService(memberService, studyRepository);
//        assertNotNull(studyService);
//
//        Member member = new Member();
//        member.setId(1L);
//        member.setEmail("swat018@gmail.com");

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

//        when(memberService.findById(any()))
//                .thenReturn(Optional.of(member))
//                .thenThrow(new RuntimeException())
//                .thenReturn(Optional.empty());
//
//        Optional<Member> byId = memberService.findById(1L);
//        assertEquals("swat018@gmail.com", byId.get().getEmail());
//
//        assertThrows(RuntimeException.class, () -> {
//            memberService.findById(2L);
//        });
//
//        assertEquals(Optional.empty(), memberService.findById(3L));

        // Given
        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);

        Member member = new Member();
        member.setId(1L);
        member.setEmail("swat018@gmail.com");

        Study study = new Study(10, "테스트");

//        // TODO memberService 객체에 findById 메소드를 1L 값으로 호출하면 member 객체를 리턴하도록 Stubbing
//        when(memberService.findById(1L)).thenReturn(Optional.of(member));
//
//        // TODO studyRepository 객체에 save 메소드를 study 객체로 호출하면 study 객체 그대로 리턴하도록 Stubbing
//        when(studyRepository.save(study)).thenReturn(study);
        given(memberService.findById(1L)).willReturn(Optional.of(member));
        given(studyRepository.save(study)).willReturn(study);

        // When
        studyService.createNewStudy(1L, study);

        //Then
        assertNotNull(study.getOwner());
        assertEquals(member, study.getOwner());

//        verify(memberService, times(1)).notify(study);
//        verify(memberService, times(1)).notify(member);
//        verify(memberService, never()).validate(any());
//
//        InOrder inOrder = inOrder(memberService);
//        inOrder.verify(memberService).notify(study);
////        inOrder.verify(memberService).notify(member);
//
//        verifyNoMoreInteractions(memberService);

        then(memberService).should().notify(study);
//        then(memberService).shouldHaveNoMoreInteractions();

    }

    @DisplayName("다른 사용자가 볼 수 있도록 스터디를 공개한다.")
    @Test
    void openStudy(@Mock MemberService memberService,
                   @Mock StudyRepository studyRepository) {
        //Given
        StudyService studyService = new StudyService(memberService, studyRepository);
        Study study = new Study(10, "더 자바, 테스트");
        assertNull(study.getOpenedDateTime());
        // TODO studyRepository Mock 객체의 save 메서드를 호출 시 study를 리턴하도록 만들기.
        given(studyRepository.save(study)).willReturn(study);

        // When
        studyService.openStudy(study);

        //Then
        // TODO study의 status가 OPENED로 변경됐는지 확인
        assertEquals(OPENED, study.getStatus());
        // TODO study의 openedDataTime이 null이 아닌지 확인
        assertNotNull(study.getOpenedDateTime());
        // TODO memberService의 notify(study)가 호출 됐는지 확인
        then(memberService).should().notify(study);
    }



}