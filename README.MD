Pseudo random tree to generate string tokens.
Token contains upper-case and lower-case latin characters and digits.
Characters 'l', 'o' and 'O' excluded.
No same adjacent characters.

**Tree**
- `seed` random tree seed
- `depth` represents com.beepbell.token length

**Counter** (optional)
- `pos` (default `0`) id of the com.beepbell.token. Two trees with same `seed` and `depth` returns same `com.beepbell.token`, when counter is in the same `pos`
- `step` (default `1`) increment pos each time `TokenService` returns com.beepbell.token
- `rolling` (default `false`) when set to `true`, reset `pos` in case it overflows tree size. When `false` throws exception
- `init` (default `0`) `pos` new value after overflow 