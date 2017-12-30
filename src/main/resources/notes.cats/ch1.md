A type class consists of three components:
* **Type class behaviors** - this is typically your trait encoding the type class behavior
* **Type class instances** - these are implicitly resolved instances that serve as encodings for particular types you want to include in the type class
* **Type class interface** - these are the access points your application code takes into a type class
    * Typically they take a type `t` as well as a type class instance for that type `t`

Make sure to differentiate between type class object instances and type class syntax instances (typically implicit classes). Cats provides both!


    