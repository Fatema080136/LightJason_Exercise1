//itch and converse example
//an agent executes two plans in parallel
//This variant uses double exclamation marks in the run goal and hence
//ensures alternative itching and conversing.
// the repair action is no longer necessary, because when run is called, itch is always called first, so
// it is called until it succeeds  before converse is called  
// note the difference of single vs double exclamation mark for scratch goal in itch for this case:
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