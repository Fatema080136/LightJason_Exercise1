// this example illustrates
// a simple agent implementing the light switch
// play around with ! and !! and check the number of times the switch acts.
// explain your observation

// initial beliefs
light("off").
myname("alice").

// initial goal
!switch.

// initial plan (triggered by the initial goal)

+!switch: >>(light(X), bool/equal( X, "on") )  <-
!!off
: >>(light(X), bool/equal( X, "off") )  <-
!!on
.

//plan for switching light on
+!on: >>myname(N) <-
 -light("off");
 +light("on");
 generic/print(N, ": switch on");
 !switch
.

//plan for switching light off
+!off: >>myname(N) <-
-light("on");
+light("off");
 generic/print(N, "switch off");
 !switch
.