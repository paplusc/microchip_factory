package org.paplusc;

public record Output(int id) implements BotTarget {
    @Override
    public Integer number() {
        return id;
    }
}
