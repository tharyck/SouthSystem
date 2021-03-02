package br.com.southsystem.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Schedule")
@Table(name = "schedule")
public class Schedule implements Serializable, Model<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotEmpty
    @Column(name = "name", nullable = false)
    private String name;

    @NotEmpty
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "status", nullable = false)
    private ScheduleStatus scheduleStatus;

    @ManyToMany
    private Set<Associated> associatedVoters;

    @Column(name = "votes_in_favor")
    private Integer votesInFavor;

    public Schedule() {
    }

    public Schedule(Long id) {
        this.id = id;
    }

    public Schedule(@NotEmpty String name, @NotEmpty String description, ScheduleStatus scheduleStatus) {
        this.name = name;
        this.description = description;
        this.scheduleStatus = scheduleStatus;
        this.setAssociatedVoters(new HashSet<Associated>());
        this.votesInFavor = 0;
    }

    public Schedule(String name, String description, ScheduleStatus scheduleStatus, Set<Associated> associatedVoters, Integer votesInFavor) {
        this.name = name;
        this.description = description;
        this.scheduleStatus = scheduleStatus;
        this.associatedVoters = associatedVoters;
        this.votesInFavor = votesInFavor;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
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
}
