// initial-goal
!main.


// initial plan (triggered by the initial goal)
+!main <-
    generic/print("Agent Name: Bose");
    NumberData = 5;
    //asked to Dilbert_Agent for calculating factorial of NumberData
    message/send( "dilbert", NumberData )
.



