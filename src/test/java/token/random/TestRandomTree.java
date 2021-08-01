package token.random;

import org.junit.*;

import java.math.BigInteger;

import static org.junit.Assert.*;

public class TestRandomTree {

    @Test
    public void test_init_illegal_args() {
        assertThrows(IllegalArgumentException.class,
                () -> new RandomTree(0));
        assertThrows(IllegalArgumentException.class,
                () -> new RandomTree(-5));
    }

    @Test
    public void test_max_depth() {
        RandomTree tree = new RandomTree(1);
        assertEquals(BigInteger.valueOf(60), tree.getMaxSize());
        tree = new RandomTree(8);
        assertEquals(new BigInteger("149319089089140"), tree.getMaxSize());
    }
}
