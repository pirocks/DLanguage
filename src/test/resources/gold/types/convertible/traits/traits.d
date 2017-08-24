//static assert( isImplicitlyConvertible!(immutable(char), char));
//static assert( isImplicitlyConvertible!(const(char), char));
static assert( isImplicitlyConvertible!(char, wchar));
static assert(!isImplicitlyConvertible!(wchar, char));
//static assert(!isImplicitlyConvertible!(const(ushort), ubyte));
//static assert(!isImplicitlyConvertible!(const(uint), ubyte));
//static assert(!isImplicitlyConvertible!(const(ulong), ubyte));
//static assert(!isImplicitlyConvertible!(const(char)[], string));
//static assert( isImplicitlyConvertible!(string, const(char)[]));
