package token;

import token.counter.Counter;
import token.random.RandomTree;

import java.math.BigInteger;

/**
 Pseudo random tree to generate string tokens.
 Token contains upper-case and lower-case latin characters and digits.
 Characters 'l', 'o' and 'O' excluded.
 No same adjacent characters.
 */
public class TokenService {
    private RandomTree randomTree;
    private Counter counter;

    public TokenService(RandomTree randomTree) {
        this(randomTree, Counter.builder().build());
    }

    public TokenService(RandomTree randomTree, Counter counter) {
        this.randomTree = randomTree;
        this.counter = counter;
        this.counter.setMaxSize(randomTree.getMaxSize());
    }

    public String next() {
        return randomTree.next(counter);
    }

    public String[] range(int amount) {
        return randomTree.range(counter, amount);
    }

    public BigInteger getCounter() {
        return counter.getPos();
    }
}
