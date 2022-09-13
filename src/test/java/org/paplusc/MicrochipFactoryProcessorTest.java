package org.paplusc;


import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MicrochipFactoryProcessorTest {

    private final BotEngine botEngineTest = new BotEngine();
    private final MicrochipFactoryProcessor processor = new MicrochipFactoryProcessor(botEngineTest);

    @Test
    public void whenValuesEndUpInOutputBins() {
        InputStream inputTest = getClass().getClassLoader().getResourceAsStream("test.txt");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputTest))) {
            reader.lines().forEach(processor::process);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        assertThat(botEngineTest.bots().size()).isEqualTo(3);
        assertThat(botEngineTest.output().size()).isEqualTo(3);
        assertThat(botEngineTest.output().get(0)).isEqualTo(List.of(5));
        assertThat(botEngineTest.output().get(1)).isEqualTo(List.of(2));
        assertThat(botEngineTest.output().get(2)).isEqualTo(List.of(3));

    }
}
