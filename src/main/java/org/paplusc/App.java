package org.paplusc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Bot balancer
 *
 */
public class App {

    private static final String inputResource = "input.txt";

    public static void main( String[] args ) {

        final BotEngine botEngine = new BotEngine();
        final MicrochipFactoryProcessor microchipFactoryProcessor = new MicrochipFactoryProcessor(botEngine);

        InputStream source = App.class.getClassLoader().getResourceAsStream(inputResource);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(source))) {

            reader.lines().forEach(microchipFactoryProcessor::process);

            // QUESTION 1
            final Bot result1 = botEngine.bots().values().stream()
                    .filter(bot -> bot.values().containsAll(List.of(61,17))).findFirst()
                    .orElse(null);
            System.out.println(result1);

            // QUESTION 2
            int counter = 0;
            for (int i = 0; i <= 2; i++) {
                List<Integer> values = botEngine.output().get(i);
                int result2 = 0;
                for (Integer value : values) {
                    if (result2 == 0) result2 = value;
                    result2 = result2*value;
                }
                System.out.println("output " + counter + " = " + result2);
                counter++;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
