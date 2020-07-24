package com.swat018.inflearnthejavatest.member;

import com.swat018.inflearnthejavatest.domain.Member;
import com.swat018.inflearnthejavatest.domain.Study;

import java.util.Optional;

public interface MemberService {
   Optional<Member> findById(Long memberId);

   void validate(Long memberId);

   void notify(Study newstudy);

   void notify(Member member);
}
