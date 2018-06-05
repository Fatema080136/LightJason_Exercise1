// this example illustrates
// a simple agent implementing the light switch
// play around with ! and !! and check the number of times the switch acts.
// explain your observation

// initial beliefs
light("off").


// initial goal
!switch.

// initial plan (triggered by the initial goal)

+!switch: 
  >>(light(X), bool/equal( X, "on") )  <-
  !off
.
  
+!switch:
  >>(light(X), bool/equal( X, "off") )  <-
  !on
.

//plan for switching light on
+!on <-
 -light("off");
 +light("on");
 generic/print("switch on");
 !switch
.

//plan for switching light off
+!off <-
-light("on");
+light("off");
 generic/print("switch off");
 !switch
.