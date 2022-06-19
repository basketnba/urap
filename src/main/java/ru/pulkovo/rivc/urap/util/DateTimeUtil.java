package ru.pulkovo.rivc.urap.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.validation.ClockProvider;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateTimeUtil {

    public static final ClockProvider UTC_CLOCK_PROVIDER = Clock::systemUTC;

    public static LocalDateTime getCurrentTimeInUTC() {
        return LocalDateTime.now(UTC_CLOCK_PROVIDER.getClock()).truncatedTo(ChronoUnit.MICROS);
    }
}