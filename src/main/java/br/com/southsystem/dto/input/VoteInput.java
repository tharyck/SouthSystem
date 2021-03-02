package br.com.southsystem.dto.input;

import io.swagger.annotations.ApiModelProperty;

public class VoteInput {

    @ApiModelProperty(example = "1", required = true)
    private Long scheduleId;

    @ApiModelProperty(example = "1", required = true)
    private Long associatedId;

    @ApiModelProperty(example = "true", required = true)
    private Boolean voteInFavor;

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }


    public Long getAssociatedId() {
        return associatedId;
    }

    public void setAssociatedId(Long associatedId) {
        this.associatedId = associatedId;
    }

    public Boolean getVoteInFavor() {
        return voteInFavor;
    }

    public void setVoteInFavor(Boolean voteInFavor) {
        this.voteInFavor = voteInFavor;
    }
}
