// hello world example featuring beliefs

// initial belief
myname("alice").


// initial goal
!tellname.

// initial plan (triggered by the initial goal)

+!tellname: >>(local/myname(X))  <-
 generic/print("My name is ", X)
 : ~>>(myname(_)) <-
 generic/print("I don't know my name");
 fail
 .

