package com.swat018.inflearnthejavatest.member;

import com.swat018.inflearnthejavatest.domain.Member;
import com.swat018.inflearnthejavatest.domain.Study;
import com.swat018.inflearnthejavatest.study.StudyService;

import java.util.Optional;

public class DefaultMemberService implements MemberService{

    StudyService studyService;

    @Override
    public Optional<Member> findById(Long memberId) {
        return Optional.empty();
    }

    @Override
    public void validate(Long memberId) {
        studyService.hi();
    }

    @Override
    public void notify(Study newstudy) {
        studyService.hi();
    }

    @Override
    public void notify(Member member) {

    }
}
