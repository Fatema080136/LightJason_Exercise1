!main.

+!main <-
    generic/print( "Agent Name: Dilbert" )
.

+!message/receive( message(M), from(F) ) <-
    generic/print( "Message: get request to calculate factorial of", M, "from", F )
.
