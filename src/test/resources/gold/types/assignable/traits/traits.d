static assert( isAssignable!(long, int));
static assert(!isAssignable!(int, long));
static assert( isAssignable!(const(char)[], string));
static assert(!isAssignable!(string, char[]));
