package ch.wiss.m295.lb_projekt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.wiss.m295.lb_projekt.model.Spieler;

public interface SpielerRepository extends JpaRepository<Spieler, Long> {

}
