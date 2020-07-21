package com.swat018.inflearnthejavatest.member;

import com.swat018.inflearnthejavatest.domain.Member;

import java.util.Optional;

public interface MemberService {
   Optional<Member> findById(Long memberId);
}
