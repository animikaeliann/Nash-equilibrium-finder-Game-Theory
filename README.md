# Nash Equilibrium Finder (Game Theory)

A Java implementation for finding pure-strategy Nash equilibria in two-player normal-form games.

The program accepts an `n × m` payoff matrix as input, identifies each player's best responses, and computes all strategy profiles that satisfy the Nash equilibrium condition.

## Features

- Supports arbitrary matrix sizes (`n × m`)
- Detects multiple Nash equilibria
- Handles games with no Nash equilibrium
- Uses best-response analysis with efficient set intersection

## How it works

1. Reads an `n × m` payoff matrix from standard input, where each cell holds a `(Player 1 payoff, Player 2 payoff)` pair.
2. For each column, finds Player 1's best response(s) — the row(s) maximizing Player 1's payoff.
3. For each row, finds Player 2's best response(s) — the column(s) maximizing Player 2's payoff.
4. Intersects the two best-response sets: any strategy profile appearing in both is a pure-strategy Nash equilibrium.

## Input format

```
n m
p1_00 p2_00 p1_01 p2_01 ... p1_0(m-1) p2_0(m-1)
p1_10 p2_10 ...
...
```

- `n` = number of rows (Player 1's strategies)
- `m` = number of columns (Player 2's strategies)
- Followed by `n * m * 2` integers: payoffs for each cell, given as `(Player 1 payoff, Player 2 payoff)` pairs, row by row.

### Example

```
2 2
3 3 0 5
5 0 1 1
```

This encodes the classic Prisoner's Dilemma payoff matrix.

## Running

```bash
javac NashEquilibrium.java
java NashEquilibrium
```

Then enter the matrix dimensions and payoffs via standard input (or pipe in a file):

```bash
java NashEquilibrium < input.txt
```

## Project structure

```
.
├── NashEquilibrium.java   # Main class, StrategyProfile, and Matrix implementation
└── README.md
```
