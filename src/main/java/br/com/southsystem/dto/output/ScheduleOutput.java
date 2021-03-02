package br.com.southsystem.dto.output;

import br.com.southsystem.model.Associated;
import br.com.southsystem.model.ScheduleStatus;

import java.util.Set;

public class ScheduleOutput {

    private Long id;

    private String name;

    private String description;

    private ScheduleStatus scheduleStatus;

    private Set<Associated> associatedVoters;

    private Integer votesInFavor;

    private Integer votesAgainst;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ScheduleStatus getScheduleStatus() {
        return scheduleStatus;
    }

    public void setScheduleStatus(ScheduleStatus scheduleStatus) {
        this.scheduleStatus = scheduleStatus;
    }

    public Set<Associated> getAssociatedVoters() {
        return associatedVoters;
    }

    public void setAssociatedVoters(Set<Associated> associatedVoters) {
        this.associatedVoters = associatedVoters;
    }

    public Integer getVotesInFavor() {
        return votesInFavor;
    }

    public void setVotesInFavor(Integer votesInFavor) {
        this.votesInFavor = votesInFavor;
    }

    public Integer getVotesAgainst() {
        if(associatedVoters.size() > 0){
            setVotesAgainst(associatedVoters.size() - getVotesInFavor());
        }
        return votesAgainst;
    }

    public void setVotesAgainst(Integer votesAgainst) {
        this.votesAgainst = votesAgainst;
    }
}
