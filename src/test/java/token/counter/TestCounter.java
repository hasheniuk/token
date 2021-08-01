package token.counter;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.*;

public class TestCounter {

    @Test
    public void test_init_illegal_args() {
        assertThrows(IllegalArgumentException.class,
                () -> Counter.builder().init(null).build());
        assertThrows(IllegalArgumentException.class,
                () -> Counter.builder().pos(null).build());
        assertThrows(IllegalArgumentException.class,
                () -> Counter.builder().step(-1).build());
    }

    @Test
    public void test_next() {
        Counter counter = Counter.builder()
                .init(BigInteger.ONE)
                .pos(BigInteger.TEN)
                .step(3)
                .build();
        counter.setMaxSize(BigInteger.valueOf(100));
        assertEquals(BigInteger.TEN, counter.getPos());
        assertEquals(BigInteger.valueOf(13), counter.next());
        assertEquals(BigInteger.valueOf(16), counter.next());
        assertEquals(BigInteger.valueOf(16), counter.getPos());
    }

    @Test
    public void test_not_rolling_overflow() {
        Counter counter = Counter.builder()
                .init(BigInteger.valueOf(2))
                .pos(BigInteger.valueOf(8))
                .step(2)
                .rolling(false)
                .build();
        counter.setMaxSize(BigInteger.valueOf(10));
        assertThrows(CounterOverflowException.class, () -> counter.next());
    }

    @Test
    public void test_rolling_overflow() {
        Counter counter = Counter.builder()
                .init(BigInteger.valueOf(2))
                .pos(BigInteger.valueOf(8))
                .step(2)
                .rolling(true)
                .build();
        counter.setMaxSize(BigInteger.valueOf(10));
        assertEquals(BigInteger.valueOf(2), counter.next());
        assertEquals(BigInteger.valueOf(2), counter.getPos());
    }

    @Test
    public void test_init_overflows_max_size() {
        Counter counter = Counter.builder().init(BigInteger.TEN).build();
        assertThrows(CounterOverflowException.class,
                () -> counter.setMaxSize(BigInteger.ONE));
    }
}
