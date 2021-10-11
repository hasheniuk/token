package com.beepbell.token.counter;

import java.math.BigInteger;

public class Counter {
    private BigInteger init;
    private BigInteger pos;
    private int step;
    private boolean rolling;
    private BigInteger maxSize;

    private Counter(BigInteger init, BigInteger pos, int step, boolean rolling) {
        this.init = init;
        this.pos = pos;
        this.step = step;
        this.rolling = rolling;
    }

    public BigInteger getPos() {
        return pos;
    }

    public void setMaxSize(BigInteger maxSize) {
        this.maxSize = maxSize;
        if (init.compareTo(maxSize) >= 0)
            throw new CounterOverflowException(init, maxSize);
    }

    public BigInteger next() {
        if (maxSize == null)
            throw new IllegalStateException("Max size required");
        pos = pos.add(BigInteger.valueOf(step));
        if (pos.compareTo(maxSize) >= 0) {
            if (!rolling) throw new CounterOverflowException(pos, maxSize);
            pos = init;
        }
        return pos;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private BigInteger init = BigInteger.ZERO;
        private BigInteger pos = BigInteger.ZERO;
        private int step = 1;
        private boolean rolling;

        public Builder init(BigInteger init) {
            this.init = init;
            return this;
        }

        public Builder pos(BigInteger pos) {
            this.pos = pos;
            return this;
        }

        public Builder step(int step) {
            this.step = step;
            return this;
        }

        public Builder rolling(boolean rolling) {
            this.rolling = rolling;
            return this;
        }

        public Counter build() {
            if (init == null || pos == null || step <= 0)
                throw new IllegalArgumentException("Init fail");
            return new Counter(init, pos, step, rolling);
        }
    }
}
