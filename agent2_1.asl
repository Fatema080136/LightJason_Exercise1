//itch and converse example
//an agent executes two plans in parallel
//Variant one:
// (1) single ! for scratch, think and talk
// Note that here  
// - talking can happen before thinking
//
//initial belief

myname("alice").

//initial goal
!run.

+!run <-
 !itch;
 !converse
.

+!itch <-
 !scratch;
 !run
.

+!converse <-
 !think;
 !talk;
 !run
.

+!scratch: >>myname(N) <-
 generic/print(N, " scratch scratch")
.

+!think: >>myname(N) <-
 generic/print(N, " think")
.

+!talk: >>myname(N) <-
 generic/print(N, " blah blah")
.