//asl for dilbert agent

!run.

+!run <-
    generic/print( "I am Dilbert" )
.

+!message/receive( message(Type, Op, Val), from(S) ) <-
    generic/print( "received message" );
    !process(Type, Op, Val, S)
.

+!process(Type, Op, Val, Sdr)
: bool/equal(Type, "delegate") &&
  bool/equal(Op, "factorial") &&
  generic/type/isnumeric(Val)
  <- generic/print( "task received" )
: true <-
  generic/print( "can't Handle request" )
.