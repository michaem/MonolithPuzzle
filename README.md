# MonolithPuzzle
App to solve monoliths puzzles from the game Mass Effect 3

## Task
Necessary to decrypt code from relicts monoliths for saving time :)

### Description
Decrypt the relictes code by correctly placing the numbers on the grid (matrix).
Duplicate digits should not be in any row, column or highlighted form.
Digits from 1 to 5 on input matrix shouldn't be change, they're "broken". 

## Sample

### Input data
#### 1. Numbers of unique glyphs:
0 - empty value

1 2 3 4 5 - unique glyphs values (types)

#### 2. Input matrix with broken values (5x5)
0 0 4 0 3

0 5 0 0 0

0 4 3 1 0

0 2 0 3 0

0 0 0 0 5

#### 3. Highlighted form
x x x y y

x x # y y

z # # # y

z z # u u

z z u u u

### Expecting result
2 1 4 5 3

3 5 2 4 1

5 4 3 1 2

1 2 5 3 4

4 3 1 2 5