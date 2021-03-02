package br.com.southsystem.model;

public enum VotingSessionStatus {
    ONGOING(1L, "ONGOING"),
    FINISHED(2L, "FINISHED"),
    ;

    private Long id;
    private String status;

    VotingSessionStatus(Long id) {
        this.id = id;
    }

    VotingSessionStatus(Long id, String status) {
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
