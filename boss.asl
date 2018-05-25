// initial-goal
!initial.


// initial plan (triggered by the initial goal)
+!initial <-
    generic/print("Agent Name: Boss");
    NumberData = 5;
    //asked to Dilbert_Agent for calculating factorial of NumberData
    message/send( "dilbert", NumberData )
.



