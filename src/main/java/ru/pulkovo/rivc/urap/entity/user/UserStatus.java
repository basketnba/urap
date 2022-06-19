package ru.pulkovo.rivc.urap.entity.user;

public enum UserStatus {
    OFFLINE("OFFLINE"),
    ONLINE("ONLINE");

    private final String code;

    UserStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}