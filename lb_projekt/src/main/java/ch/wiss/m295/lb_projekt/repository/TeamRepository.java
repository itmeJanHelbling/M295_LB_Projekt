package ch.wiss.m295.lb_projekt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.wiss.m295.lb_projekt.model.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
    List<Team> findAllByLigaId(int ligaId);

}
