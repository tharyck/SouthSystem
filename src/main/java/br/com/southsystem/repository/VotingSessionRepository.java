package br.com.southsystem.repository;

import br.com.southsystem.model.VotingSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotingSessionRepository extends JpaRepository<VotingSession, Long> {
    VotingSession findByScheduleId(Long scheduleId);
}