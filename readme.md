Overview of LJ demos shown in Lecture:

* agent00.asl: basic agent that can write its name (slide 8)
* agent0.asl: light switch agent from slide 15 (with single !s); agent0_v2 shows the variant with two separate plans for !switch (slide 20)
* agent0_a and agent0_b: Use agent0_a and agent0_b to run the two agent-case of the light switch example (slide 18);  they are asl instances of agent0 that get their names (alice and bob) as initial beliefs. The beliefs are then used in the print ops to better identify  the agents in the traces
* agent2_1.asl: nervous guy 1 on slide 23
* agent2_2.asl: nervous guy 2 on slide 24
* agent2_3.asl: nervous guy 3 with explicit plan repair
* boss.asl and dilbert.asl: communication example on slide 29
* empty.asl: an empty file, should no longer be required with the app that can take a variable number of arguments

Template for running MAS
java -jar target\myagentapp-0.0.1-SNAPSHOT.jar agent1.asl agent2.asl 5

This can be started with
* 1 argument: an asl file, default number of cycles: one instance of agent is started and run the default number of cycles
* 2 arguments:
  * 2 asl files, one instance of each is started and run the default number of cycles or
  * 1 asl file and a number, one instance of agent is started and run the number of cycles given in arg 2
* 3 argument: the first two are asl file names, the third a number denoting the number of agent cycles run

Exercise 1:

Run the program to create the boss agent based on boss.asl and one dilbert agent based on dilbert.asl, and the agents will run 5 cycles:

java -jar target\myagentapp-0.0.1-SNAPSHOT.jar boss.asl dilbert.asl 5

Here, the name of the agents, generated as instance of "boss.asl" and "dilbert.asl" are "boss" and "dilbert" respectively.
Only one instance for each asl file can be generated, but the number of cycles is flexible.