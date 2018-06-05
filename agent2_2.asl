//itch and converse example
//An agent executes two plans in parallel
//This is the second variant using  double !! for scratch, think, talk
// 
// In this version, thinking will always be prior to talking
// (3) what would you need to do to alwasy alternative itching and conversing?
// double exclamation marks in run goal .
// (4) note the difference of single vs double exclamation mark for scratch goal in itch:
// with single, scratch is executed last, with double first!

//initial belief

myname("alice").

//initial goal
!run.

+!run <-
 !itch;
 !converse
.

+!itch <-
  L=math/statistic/randomsimple;
  L>0.4; 
  !scratch;
  !run
.

// repair plan for itch - don't scratch
-!itch <-
  !run.

+!converse <-
 !!think;
 !!talk;
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