package br.com.southsystem.dto.output;

import br.com.southsystem.model.VotingSessionStatus;

public class VotingSessionOutput {

    private Long id;

    private Integer timeSession;

    private VotingSessionStatus votingSessionStatus;

    private Long scheduleId;

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

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }
}
