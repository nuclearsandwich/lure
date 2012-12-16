Lure
====

Lure is a sweet little expression based language that compiles to Jasmin
assembly.

Lure was written by me for a school project. The semantics of the language were
designed to allow me to be as lazy as possible, but still have something to show
for it.

Neat facts about Lure
---------------------

1. Every thing is an expression and every expression has a return value.

2. Functions are first class but there's no closures, so it's basically
useless.

3. I stole Ruby semantics for tests so all values are truthy except nil and
false.

4. That's it really..

5. It has no compile-time type checking because the rest of my group bailed on
me and I ran out of time.

Building It
----------

Lure consists of a compiler (forkbomb.* and wci.*) and the runtime library
(lure.*) They're a little more interdependent than I would like at the moment
but I wrote this in like a week. All the source is in the src directory so just
build everything in there.

For your convenience an argfile is provided so you can do something like this:

    javac -d bin @argfile

Running It
----------

Lure is a hulking Java thing, which is to say it's *complicated*. Luckily, if
you're on some sort of POSIX-like system There exists a script to make it not
suck. It's called script/lure just pass the name of a .lure source file as your
argument. There are some modifications you can make with environment variables
but you probably shouldn't.

The main class is forkbomb.frontend.LureParser and it takes a single argument,
the path to a source file.  The file will be parsed and compiled into
[Jasmin](http://jasmin.sourceforge.net) Assembly Language the default output
directory is ./out but you can change that by setting the LUREPATH environment
variable.

    java -cp bin forkbomb.frontend.LureParser examples/99bottles.lure

> Aside: If all you care about is the JVM bytecode generator it's in
> forkbomb.backend.bytemarks, enjoy!

Once you've got the jasmin files (there can be quite a few if your program has a
lot of functions.) Compile them with Jasmin into the same output directory as
the standard library.

    jasmin out/* -d bin

Now run the LureMain class and watch the magic happen!

    java -cp bin LureMain

Inside It
---------

The compiler is broken up into a parser, intermediate representation, and
bytecode generator because that was the whole point of the class. Also, it
would make it a lot easier to port to other platforms if I wanted to keep
writing Java.

The parser itself was generated using JavaCC/JJTree, the AST Parser class is
src/forkbomb/frontend/LureASTParser. It gives you an ICode representation of the
program which we then give to src/forkbomb/backend/LureCompiler. If I'd had help
or more time I might even have made it check types in between those phases!

The Mysterious Future
---------------------

I don't know what will happen here. It's not a particularly good idea or
execution of how to make a programming language. And it doesn't even have any
redeemingly crass features like a curse word for a name or a politeness
requirement. It will probably collect dust, or serve as a toy for me to
generate other backends. If you actually want to use this for anything... tell
me why?

