# Pawn Tour

The idea here is to make the pawn move along every available(non visited) square on the board.

## Algorithm

I have started with an algorithm that for every square, I looked at the available next moves,
and then for every next move, I looked at again(recursively) the options I had.
The problem with this first approach was that I was always getting the last available move, no matter what.

After some research, I found the Warnsdorff idea, that looked for squares with the fewer moves available.
This enables the pawn to move around the borders, where you have fewer moves available, instead of going
to the middle and possibly not covering all the board.

Basically what I'm doing in a few steps is the following:

- Start on a position
- Find available moves for this position
- For every available move, find what has fewer moves
- Move for that position and start over again

I tried to write a good commentary on the PawnTour class, explaining what I'm doing on every method.

### Performance

I have tested it on a board 24x24 and it seems to be fast enough. Internally I'm using tail recursion techniques
to walk on the graph.

### Tests?

Yes, I have created a PawnTourSpec class, that runs tests for boards with size 8, 10, 12 and 24, also it checks
the possible moves for a given position on a board 10x10.

### Logging?

Since I'm printing the graph at the end of the execution, I thought it was unnecessary to log anything

### Problems

My algorithm is generic to cover any board of any size, however, during my tests, it's possible to see that some
combinations of board size with initial start position, can lead to some non visited squares.

## Requirements

- Docker

## Running the project

In order to run this project, the only requirement as pointed in the above section is Docker.
This makes life easier, allowing anyone with Scala or not installed, to be able to run the project.

### Running published Docker image

The easiest way to run it, is running the following command:

```
docker run -it thiagoandrade6/pawn-tour:latest
```

This will download an image I have uploaded to my own docker hub.

You will be asked to give some information, like board size and initial position on row and column, e.g:

```
Enter the Board size
10
Enter the Row start position in the Board
6
Enter the Column start position in the Board
3
Here is the Pawn Tour for a board size of 10 and position 6,3
6	52	49	5	53	48	34	54	47	33
64	27	8	67	30	9	68	31	10	69
50	4	58	51	90	59	56	95	35	55
7	76	65	28	75	66	29	70	46	32
63	26	87	82	57	98	89	60	11	96
78	3	38	77	91	37	74	94	36	71
22	81	62	1	88	61	18	97	45	15
39	25	86	83	41	99	92	42	12	93
79	2	21	80	17	20	73	16	19	72
23	84	40	24	85	43	13	100	44	14
```

This will create a board of size 10x10, starting on position 6x3. This board goes from 0x0 to 9x9 position.

### Ok, I want to build an image myself and run it

Great, you just need to run one command for it:

```
make image: build
```

This will generate a fat jar will all dependencies and build a docker image on your local computer.
You can run it the same way as the first one.

### Is the any other way to run it?

Yes, sure! You can always run it from sbt, byt typing:

```
sbt run
```

## How to test

Just run:

```
sbt test
```

or using the makefile:

```
make test
```

## Makefile

Makefile helps you by providing almost zero setup in order to run or test the program.
