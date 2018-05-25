Exercise 1

Run the program to create the boss agent based on boss.asl and one dilbert agent based on dilbert.asl, and the agents will run 5 cycles:

java -jar target\myagentapp-0.0.1-SNAPSHOT.jar boss.asl dilbert.asl 5

Here, the name of the agents, generated as instance of "boss.asl" and "dilbert.asl" are "boss" and "dilbert" respectively.
Only one instance for each asl file can be generated, but the number of cycles is flexible.