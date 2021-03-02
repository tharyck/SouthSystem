package br.com.southsystem.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity(name = "VotingSession")
@Table(name = "votingsession")
public class VotingSession implements Serializable, Model<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "time_session")
    private Integer timeSession;

    @Column(name = "status", nullable = false)
    private VotingSessionStatus votingSessionStatus;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    public VotingSession() {
    }

    public VotingSession(Long id) {
        this.id = id;
    }

    public VotingSession(Schedule schedule, Integer timeSession) {
        this.timeSession = timeSession;
        this.schedule = schedule;
        this.votingSessionStatus = VotingSessionStatus.ONGOING;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTimeSession() {
        return timeSession;
    }

    public void setTimeSession(Integer timeSession) {
        this.timeSession = timeSession;
    }

    public VotingSessionStatus getVotingSessionStatus() {
        return votingSessionStatus;
    }

    public void setVotingSessionStatus(VotingSessionStatus votingSessionStatus) {
        this.votingSessionStatus = votingSessionStatus;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}
