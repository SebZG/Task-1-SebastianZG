# Number Guessing Game

A console-based Java game that challenges the player to guess a randomly generated
number between 1 and 100.

## How to play

1. Start the program.
2. Choose how many attempts you want per round, from 1 to 10.
3. Enter guesses between 1 and 100.
4. Use the hints to adjust your next guess:
   - **Too high** means the target is lower.
   - **Too low** means the target is higher.
5. After each round, choose whether to play again.

Invalid input and out-of-range guesses do not count as attempts.

## Scoring

Correct guesses earn more points when they are made early:

```Java
points = maximum attempts - attempts used + 1
```

If the player uses all attempts without guessing correctly, the round awards
zero points and reveals the target number. The final score includes points from
all rounds.

## Requirements

- Java 17 or later

## Compile and run

From this directory, run:

```bash
javac Main.java
java Main
```

## Project structure

```text
.
├── Main.java
└── README.md
```
