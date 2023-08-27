package ru.hogwarts.school1.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school1.service.impl.StudentServiceImpl;

import java.util.logging.Logger;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

@RestController
@RequestMapping("/info")
public class InfoController {

    private final Logger logger = (Logger) LoggerFactory.getLogger(InfoController.class);
    @Value("${server.port}")
    private int port;

    @GetMapping
    public int getPort() {
        return port;
    }

    @GetMapping("/sum-experiments")
    public String sum() {

        //option1
        Stream<Integer> stream1 = Stream.iterate(1, a -> a + 1)
                .limit(10_000_000);
long startTime = System.currentTimeMillis();
        long sum1 = stream1
                .limit(10_000_000)
                .reduce(0, (a, b) -> a + b);
long endTime = System.currentTimeMillis();

logger.info("Experiment 1 - streams with reduce. Sum1 = " + sum1+". Total calculation time = " + (endTime - startTime));
        //option2
        LongStream stream2 = LongStream.iterate(1, a -> a + 1)
                .limit(10_000_000);


        startTime = System.currentTimeMillis();
        long sum2 = stream2
                .limit(10_000_000)
                .sum();
        endTime = System.currentTimeMillis();

        logger.info("Experiment 2 - streams with sum. Sum2 = " + sum2+". Total calculation time = " + (endTime - startTime));

        //option3
        Stream<Integer> stream3 = Stream.iterate(1, a -> a + 1)
                .limit(10_000_000);


        startTime = System.currentTimeMillis();
        long sum3 = stream3
                .limit(10_000_000)
                .parallel()
                .reduce(0, (a, b) -> a + b);
        endTime = System.currentTimeMillis();

        logger.info("Experiment 3 - streams with parellel. Sum3 = " + sum3+". Total calculation time = " + (endTime - startTime));
        //option4
        startTime = System.currentTimeMillis();
        long sum4 = 0;
        for (int i = 0; i <= 10_000_000; i++) {
            sum4 += i;
            endTime = System.currentTimeMillis();

            logger.info("Experiment 4 - for cycle. Sum4 = " + sum4+". Total calculation time = " + (endTime - startTime));
        }

    }
}


