package token.random;

import token.counter.Counter;
import token.util.ArrayUtils;

import java.math.BigInteger;
import java.util.*;

public class RandomTree {

    private static final char[] CHARS = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N',      'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',      'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'
    };

    private final char[] chars = new char[CHARS.length];
    private final int[][] wire = new int[CHARS.length][CHARS.length - 1];

    private int depth;

    public RandomTree(int depth) {
        this(System.nanoTime(), depth);
    }

    public RandomTree(long seed, int depth) {
        if (depth <= 0)
            throw new IllegalArgumentException("depth <= 0");
        this.depth = depth;
        System.arraycopy(CHARS, 0, chars, 0, CHARS.length);
        Random random = new Random(seed);
        for (int i = 0; i < chars.length; i++)
            ArrayUtils.swap(chars, i, random.nextInt(chars.length));
        for (int i = 0; i < wire.length; i++)
            for (int j = 0; j < wire.length - 1; j++)
                wire[i][j] = j < i ? j : j + 1;
        for (int[] arr : wire)
            for (int i = 0; i < arr.length; i++)
                ArrayUtils.swap(arr, i, random.nextInt(arr.length));
    }

    public String next(Counter counter) {
        BigInteger pos = counter.next();
        StringBuilder sb = new StringBuilder(depth);
        int idx = pos.mod(BigInteger.valueOf(chars.length)).intValue();
        pos = pos.divide(BigInteger.valueOf(chars.length));
        sb.append(chars[idx]);
        for (int i = 1; i < depth; i++) {
            int[] w = wire[idx];
            idx = w[pos.mod(BigInteger.valueOf(w.length)).intValue()];
            pos = pos.divide(BigInteger.valueOf(w.length));
            sb.append(chars[idx]);
        }
        return sb.toString();
    }

    public String[] range(Counter counter, int amount) {
        String[] range = new String[amount];
        for (int i = 0; i < amount; i++) range[i] = next(counter);
        return range;
    }

    public BigInteger getMaxSize() {
        BigInteger subSize = BigInteger.valueOf(CHARS.length - 1).pow(depth - 1);
        return BigInteger.valueOf(CHARS.length).multiply(subSize);
    }
}
