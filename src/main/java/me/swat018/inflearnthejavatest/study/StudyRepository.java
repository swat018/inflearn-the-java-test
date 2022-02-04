package me.swat018.inflearnthejavatest.study;

import me.swat018.inflearnthejavatest.domain.Study;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyRepository extends JpaRepository<Study, Long> {
}
