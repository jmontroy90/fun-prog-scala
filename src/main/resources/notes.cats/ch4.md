## Monads

Monads are tricky for two reasons:
* They sequence computations much like Functors, but Functors have control only over initial context and content transformation.
A monad can sequence computations by transforming not only their contents, but their contexts as well! In other words, monads can work with
_intermediate_ contexts and **repackage** the contents based on those intermediate results! 
   * In short - a monad is more powerful because it allows you to make decisions about both contents and contexts!
* They can handle individual values (`Option`) and many values (`List`) - when they handle many values, they frequently end up
 up permuting the contents of different monads (on functions like `sequence` and `traverse` - keep your eyes open!)
 
The behavior of permuting the contents of non-atomic monads is more a byproduct of nesting computations, rather than anything about Monads.
 Think - how else would you combine the contents of two Monads? With atomic Monads like `Option`, the passed-in function corresponds directly
 to the "multi-valued-ness" of the contents, but for more allowing contexts, some operation has to be defined for the `combine` operation.
 This is _almost_ like an implicit type class instance in scope for all non-atomic monadic operations - for any multi-valued Monad contents,
 define a `flatMap` as a Cartesian product of operations defined within the function. When the function being applied is multi-valued,
 that almost seems like multiple operations! You can imagine, though, a different type class instance for combining multi-valued
 Monad contents, that instead reduces the contents to some singular value and THEN combines the two contents. But that would just be a 
 `Foldable` or whatever the hell, and then a `flatMap` on top of the reduced `Foldable`. Different.
 

Whatever, these are rambling thoughts, but they feel in the vicinity. Let's get into some specific monads.

##### Either

Eithers are good for fail-fast pattern-match-friendly semantics, and Cats offers helpful functions on top of `Either` for swapping, casting, fully qualifying, etc.
   
##### Eval

The `Eval` monad allows you to define evaluation semantics in terms of laziness and memoization. Remember:
* A Scala `val` is eager and memoized
* A Scala `lazy val` is lazy and memoized
* A Scala `def` is lazy and not memoized.

These correspond to Cats' `now`, `later`, and `always` Eval types.
 
 