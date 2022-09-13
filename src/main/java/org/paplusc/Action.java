package org.paplusc;

public enum Action {

    CREATE_BOT,
    COMPUTE_VALUE;

    public static Action of(String value) {
        return switch (value) {
            case "bot" -> Action.CREATE_BOT;
            case "value" -> Action.COMPUTE_VALUE;
            default -> null;
        };
    }
}
