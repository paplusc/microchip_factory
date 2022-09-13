package org.paplusc;

import java.util.List;
import java.util.Objects;

public class Bot implements BotTarget {

    private final int id;
    private final List<Integer> values;
    private final BotTarget low;
    private final BotTarget high;

    public Bot(int id, List<Integer> values, BotTarget low, BotTarget high) {
        this.id = id;
        this.values = values;
        this.low = low;
        this.high = high;
    }

    public Bot(int id) {
        this.id = id;
        this.values = null;
        this.low = null;
        this.high = null;
    }

    public boolean isFull() {
        if (values == null) return false;
        return values.size() == 2;
    }

    @Override
    public Integer number() {
        return this.id;
    }

    public List<Integer> values() {
        return values;
    }

    public BotTarget low() {
        return low;
    }

    public BotTarget high() {
        return high;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bot bot = (Bot) o;
        return id == bot.id && Objects.equals(values, bot.values) && Objects.equals(low, bot.low) && Objects.equals(high, bot.high);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, values, low, high);
    }

    @Override
    public String toString() {
        return "Bot{" +
                "id=" + id +
                ", values=" + values +
                ", low=" + low +
                ", high=" + high +
                '}';
    }
}
