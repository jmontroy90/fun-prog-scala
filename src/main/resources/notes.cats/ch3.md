## Functors

Functors are just any data type with a Map function on it. `List`, `Option`, `Try`, so on. Functors aren't that great themselves,
but are an integral part of _monads_ and _applicative functors_.
 
Functors can be considered the process of applying some function to the contents of a given box (ie. context). For a list, **DON'T** think of it
as applying a function to each element - think of it as transforming the contents all at once, but leaving the context unchanged.
You can also think of it as appending on transformations to a context, which is useful for chaining functions without actually applying them.
 
Two laws functors must obey:
1) Composition - `fa.map(f(g(a))) == fa.map(g).map(f)`
2) Identity - `fa.map(ident) == fa`

#### Contramap
This represents prepending an operation, and feels kind of like a "get from what you have to what you need" type class. The 
example worked shows how a new type class instance for any context `F` can be generated via the `contramap` method that allows
us to get from `F[A]` to just `A`, where we have a previously existing type class on `A`. 
