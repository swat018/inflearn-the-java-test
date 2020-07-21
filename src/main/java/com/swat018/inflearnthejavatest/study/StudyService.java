package com.swat018.inflearnthejavatest.study;

import com.swat018.inflearnthejavatest.domain.Study;

public class StudyService {

    private final MemberService memberService;

    private final StudyRepository repository;

    public StudyService(MemberService memberService, StudyRepository repository) {
        this.memberService = memberService;
        this.repository = repository;
    }

    public Study createNewStudy(Long memberId, Study study) {
        Member member = memberService.findById(memberId);
        if (member == null) {
            throw new IllegalArgumentException("Member doesn't exist for id: '" + member);
        }
        study.setOwner(member);
        return repository.save(study);
    }
}
