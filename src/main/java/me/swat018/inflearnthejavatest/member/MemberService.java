package me.swat018.inflearnthejavatest.member;

import me.swat018.inflearnthejavatest.domain.Member;
import me.swat018.inflearnthejavatest.domain.Study;

import java.util.Optional;

public interface MemberService {

    Optional<Member> findById(Long memberId);

    void validate(Long memberId);
}
