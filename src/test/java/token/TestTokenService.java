package token;

import org.junit.Test;
import token.counter.Counter;
import token.random.RandomTree;

import java.math.BigInteger;
import java.util.*;

import static org.junit.Assert.*;

public class TestTokenService {
    private static final Random RAND = new Random();

    @Test
    public void test_string_length() {
        int len = RAND.nextInt(100) + 1;
        TokenService tokenService = new TokenService(new RandomTree(len));
        assertEquals(len, tokenService.next().length());
    }

    @Test
    public void test_no_same_adjacent_characters() {
        int len = RAND.nextInt(10) + 10;
        TokenService tokenService = new TokenService(new RandomTree(len));
        String token = tokenService.next();
        boolean hasSame = false;
        for (int i = 1; i < token.length(); i++)
            if (token.charAt(i - 1) == token.charAt(i)) {
                hasSame = true;
                break;
            }
        assertFalse(hasSame);
    }

    @Test
    public void test_deterministic() {
        long seed = RAND.nextLong();
        int len = RAND.nextInt(32) + 8;
        RandomTree randomTree1 = new RandomTree(seed, len);
        RandomTree randomTree2 = new RandomTree(seed, len);
        Counter counter1 = Counter.builder().pos(BigInteger.TEN).build();
        Counter counter2 = Counter.builder().pos(BigInteger.TEN).build();
        TokenService tokenService1 = new TokenService(randomTree1, counter1);
        TokenService tokenService2 = new TokenService(randomTree2, counter2);
        assertEquals(tokenService1.next(), tokenService2.next());
    }

    @Test
    public void test_unique() {
        int n = 1_000;
        int len = RAND.nextInt(10) + 10;
        TokenService tokenService = new TokenService(new RandomTree(len));
        Set<String> strings = new HashSet<>(n);
        for (int i = 0; i < n; i++) strings.add(tokenService.next());
        assertEquals(n, strings.size());
    }

    @Test
    public void test_range_unique() {
        int n = 1000;
        TokenService tokenService = new TokenService(new RandomTree(16));
        String[] range = tokenService.range(n);
        assertEquals(range.length, new HashSet<>(Arrays.asList(range)).size());
    }

    @Test
    public void test_rolling_overflow_consistent() {
        Counter counter = Counter.builder()
                .pos(BigInteger.valueOf(64))
                .step(60 * 59)
                .rolling(true)
                .build();
        TokenService tokenService = new TokenService(new RandomTree(2), counter);
        String s1 = tokenService.next();
        String s2 = tokenService.next();
        assertEquals(s1, s2);
    }
}
