//asl for boss agent

// initial goal
!run.


// initial plan
+!run <-
    generic/print("Hello, I am the Boss");

    message/send( "dilbert", "delegate", "factorial", 10 )
.



