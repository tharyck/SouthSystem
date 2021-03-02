package br.com.southsystem.dto.input;

import io.swagger.annotations.ApiModelProperty;

public class VotingSessionInput {

    @ApiModelProperty(example = "30")
    private Integer timeSession;

    @ApiModelProperty(example = "1", required = true)
    private Long scheduleId;

    public Integer getTimeSession() {
        return timeSession;
    }

    public void setTimeSession(Integer timeSession) {
        this.timeSession = timeSession;
    }

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }
}
