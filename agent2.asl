//itch and converse example
//an agent executes two plans in parallel
//two variants:
// (1) single ! for scratch, think and talk
// (2) double !! for scratch, think, talk
// in (1) talking can happen before thinking!
// in (2) thinking will always be prior to talking
// (3) what would you need to do to alwasy alternative itching and conversing?
// double exclamation marks in run goal .
// (4) note the difference of single vs double exclamation mark for scratch goal in itch:
// with single, scratch is executed last, with double first!

//initial belief

myname(alice).

//initial goal
!run.

+!run <-
 !!itch;
 !!converse
.

+!itch <-
 !!scratch;
 !run
.

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