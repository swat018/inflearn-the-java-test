package com.swat018.inflearnthejavatest.study;

import com.swat018.inflearnthejavatest.domain.Member;
import com.swat018.inflearnthejavatest.member.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class StudyServiceTest {

/*    @Mock
    MemberService memberservice;

    @Mock
    StudyRepository studyRepository;*/

    @Test
    void createStudyService(@Mock MemberService memberservice,
                            @Mock StudyRepository studyRepository) {
/*        MemberService memberservice = mock(MemberService.class);
        StudyRepository studyRepository = mock(StudyRepository.class);*/
        StudyService studyService = new StudyService(memberservice, studyRepository);
        assertNotNull(studyService);
    }
}