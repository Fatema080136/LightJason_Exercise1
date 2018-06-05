// this example illustrates that 
// (1) the two agents get executed concurrently
// (2) the subgoals in foo(X) get executed concurrently
// you will see this if the program is run multiple time
// trying a version of foo() which uses !! for the calls of foo1
// should show sequential execution within the two instances
// this basically shows the same things as agent0(ab) and will not be included in the SS18 lecture.

// initial-goal
!init.

// initial plan (triggered by the initial goal)
+!init <-
 generic/print("Dummy is born");
 Id=math/statistic/randomsimple;
 +myid(Id);
 !foo(5)
 .

// show effect of two plans for the same goal

/*
+!foo(X) : >>myid(I) <- 
 !foo1(I,X,1);
 !foo1(I,X,2);
 !foo1(I,X,3);
 !foo1(I,X,4);
 !foo1(I,X,5)
.
*/

+!foo1(I,X,N) <- 
  generic/print(I, "perform ", X, " run ", N)
 .
 
 // alternative sequential implementation of foo
 +!foo(X) : >> myid(I) <- 
 !!foo1(I,X,1);
 !!foo1(I,X,2);
 !!foo1(I,X,3);
 !!foo1(I,X,4);
 !!foo1(I,X,5)
 .