package com.abc.banking.business.sequencegenerator.impl;

import com.abc.banking.business.sequencegenerator.SequenceGenerator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Srinivasa.
 */
@Component
public class InMemoryDateBasedSequenceGenerator implements SequenceGenerator {

    private AtomicInteger sequence = new AtomicInteger(0);

    @Override
    public int generate() {
        return sequence.incrementAndGet();
    }

    /*
     * Reset the counter at the beginning of each day
     */
    @Scheduled(cron = "0 0 * * *")
    public void reset() {
        sequence.set(0);
    }
}
