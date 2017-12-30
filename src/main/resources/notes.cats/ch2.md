## Monoids and Semigroups
A monoid instance:
* is associative
* follows the rule of applying an identity leaves things unchanged
* comprises an `empty` and a `combine` method.
 
 That's the whole definition. One scenario can have multiple monoid instances depending on the associativity.

Some monoids:
* Integer addition
* List concatenation

NOT monoids:
* Integer subtraction (not associative!)

A **semigroup** is just the `combine` component of a monoid. There are some types that have a combine, but don't have an
empty element. A `List` data type that doesn't accept empty lists would be a semigroup.
 
Most distributed computation involve using Monoid instances to define node-to-node aggregations. Of particular interest is this
idea of a `QTree`, which seems related to a T-Digest and is explored in Twitter's `Algebird` package.

Isn't most of Spark just Monoidal operations with occassionally mandated ordering via shuffles?

Cats' `Semigroup` syntax for Monoidal types (`|+|` for Options, Lists, Maps, Tuples, custom classes) can be super useful:
> (Map("a" -> 1, "b" -> 2) |+| Map("a" -> 3, "b" -> 4, "c" -> 7)) == Map("a" -> 4, "b" -> 6, "c" -> 7)

That's casual element-wise Map addition - nice! And we can define an `addAll` that will take ANY Monoidal type and add instances of it:

> def add[A : cats.Monoid](items: List[A]): A = items.foldLeft(cats.Monoid[A].empty)(_ |+| _)