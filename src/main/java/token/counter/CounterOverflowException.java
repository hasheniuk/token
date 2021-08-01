package token.counter;

import java.math.BigInteger;

public class CounterOverflowException extends RuntimeException {

    public CounterOverflowException(BigInteger counter, BigInteger maxLen) {
        super(String.format("Counter %d overflow max len %d", counter, maxLen));
    }
}
