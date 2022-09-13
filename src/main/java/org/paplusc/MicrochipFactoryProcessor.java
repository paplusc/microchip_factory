package org.paplusc;

import java.util.ArrayList;

public class MicrochipFactoryProcessor {

    private final BotEngine botEngine;

    public MicrochipFactoryProcessor(BotEngine botEngine) {
        this.botEngine = botEngine;
    }

    public void process(String input) {

        String[] inputArray = input.split(" ");

        switch (Action.of(inputArray[0])) {

            case CREATE_BOT -> {
                int botNumber = Integer.parseInt(inputArray[1]);
                final BotTarget low = inputArray[5].equals("bot") ?
                        new Bot(Integer.parseInt(inputArray[6])) :
                        new Output(Integer.parseInt(inputArray[6]));

                final BotTarget high = inputArray[10].equals("bot") ?
                        new Bot(Integer.parseInt(inputArray[11])) :
                        new Output(Integer.parseInt(inputArray[11]));

                botEngine.addBot(new Bot(botNumber, new ArrayList<>(), low, high));

                if (botEngine.pendingInputs().get(botNumber) != null) {
                    botEngine.pendingInputs().get(botNumber).forEach((v) -> botEngine.computeValue(botNumber, v));
                    botEngine.pendingInputs().remove(botNumber);
                }
            }

            case COMPUTE_VALUE -> {
                int botNumber = Integer.parseInt(inputArray[5]);
                int value = Integer.parseInt(inputArray[1]);
                botEngine.computeValue(botNumber, value);
            }
        }
    }
}
