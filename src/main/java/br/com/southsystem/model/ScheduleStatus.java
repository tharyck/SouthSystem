package br.com.southsystem.model;

public enum ScheduleStatus {
    CREATED(1L, "CREATED"),
    STARTED(2L, "STARTED"),
    APPROVED(3L, "APPROVED"),
    REPROVED(4L, "REPROVED"),
    NO_VOTES(5L, "NO_VOTES"),
    ;

    private Long id;
    private String status;


    ScheduleStatus(Long id) {
        this.id = id;
    }

    ScheduleStatus(Long id, String status) {
        this.id = id;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
