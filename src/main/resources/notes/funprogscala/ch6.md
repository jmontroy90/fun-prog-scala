6.1. GENERATING RANDOM NUMBERS USING SIDE EFFECTS
* The normal Scala random `nextInt` implementation carries invisible state - hard to test, parallelize, etc.
* This invisible state is essentially updated as a side effect
* Let's eschew invisible state and side effects, make it explicit!

6.2. PURELY FUNCTIONAL RANDOM NUMBER GENERATION
* Return updated state to the user in addition to next integer
* "separate the concern of computing what the next state is from the concern of communicating the new state to the rest of the program"

6.3. MAKING STATEFUL APIS PURE / 6.4. A BETTER API FOR STATE ACTIONS / 6.5. A GENERAL STATE ACTION DATA TYPE
* Lots of solved problems with a state + next value interface involve clunky boiler plate - can we generalize?
* Most stateful problems involve functions with signature `State => (A, State)`, so use this as common API
* For example, map ends up as `def map[S,A,B](a: S => (A,S))(f: A => B): S => (B,S)`
    * In short, get the next state and transformed value in a referentially transparent way
* Much like the other themes, these constructs allow you to describe and compose your functions without losing information
  via side effects, and also delays computation by dealing with functions that evolve state rather than doing it in place.

6.6. PURELY FUNCTIONAL IMPERATIVE PROGRAMMING
* Imperative program - list of instructions to be evaluated immediately
* This is like the implementations for evolving and using state - update, use next value, update, etc.
* So use functional constructs - flatMap / for comprehensions - to work reasonably with State transformations
* But here, evaluation is delayed by returning functions that evolve the state, rather than updating in place