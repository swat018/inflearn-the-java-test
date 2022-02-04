package me.swat018.inflearnthejavatest.study;

import me.swat018.inflearnthejavatest.domain.Study;
import me.swat018.inflearnthejavatest.domain.Member;
import me.swat018.inflearnthejavatest.member.MemberService;

import java.util.Optional;


public class StudyService {

    private final MemberService memberService;

    private final StudyRepository repository;

    public StudyService(MemberService memberService, StudyRepository repository) {
        assert memberService != null;
        assert repository != null;
        this.memberService = memberService;
        this.repository = repository;
    }

    public Study createNewStudy(Long memberId, Study study) {
        Optional<Member> member = memberService.findById(memberId);
        study.setOwner(member.orElseThrow( () -> new IllegalArgumentException("Member doesn`t exist id : '" + memberId + "'")));
        return repository.save(study);
    }
}
