package org.paplusc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BotEngine {

    private final Map<Integer, Bot> bots;

    private final Map<Integer, List<Integer>> output;

    private final Map<Integer, List<Integer>> pendingInputs;

    public BotEngine() {
        this.pendingInputs = new HashMap<>();
        this.bots = new HashMap<>();
        this.output = new HashMap<>();
    }

    public void addBot(Bot bot) {
        this.bots.put(bot.number(), bot);
    }

    public void computeValue(int botNumber, Integer value) {

        final Bot bot = this.bots.get(botNumber);

        if (bot != null) {
            bot.values().add(value);

            if (bot.isFull()) {
                Collections.sort(bot.values());
                int low = bot.values().get(0);
                int high = bot.values().get(1);
                if (bot.low() instanceof Output) {
                    sendToOutput(bot.low().number(), low);
                } else {
                    computeValue(bot.low().number(), low);
                }
                if (bot.high() instanceof Output) {
                    sendToOutput(bot.high().number(), high);
                } else {
                    computeValue(bot.high().number(), high);
                }
            }

        } else {
            sendToPendingInputs(botNumber, value);
        }
    }

    public Map<Integer, List<Integer>> pendingInputs() {
        return this.pendingInputs;
    }

    public Map<Integer, Bot> bots() {
        return this.bots;
    }

    public Map<Integer, List<Integer>> output() {
        return this.output;
    }

    private void sendToOutput(int id, int value) {
        this.output.putIfAbsent(id, new ArrayList<>());
        this.output.get(id).add(value);
    }

    private void sendToPendingInputs(int id, int value) {
        this.pendingInputs.putIfAbsent(id, new ArrayList<>());
        this.pendingInputs.get(id).add(value);
    }
}
