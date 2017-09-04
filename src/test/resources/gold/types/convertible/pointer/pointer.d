module gold.types.convertible.pointer.pointer;

import gold.types.convertible.pointer.declarations;
import std.traits;

void main(string[] args){

}

static assert(isImplicitlyConvertible(TopLevel*, SubClass*));
static assert(isImplicitlyConvertible(TopLevel*, SubClass2*));
static assert(isImplicitlyConvertible(TopLevel*, SubSubClass*));
static assert(isImplicitlyConvertible(TopLevel*, SubSubClass2*));
static assert(isImplicitlyConvertible(TopLevel*, TopLevelInterface*));
static assert(isImplicitlyConvertible(TopLevel*, OtherInterface*));
static assert(isImplicitlyConvertible(SubClass*, TopLevel*));
static assert(isImplicitlyConvertible(SubClass*, SubClass2*));
static assert(isImplicitlyConvertible(SubClass*, SubSubClass*));
static assert(isImplicitlyConvertible(SubClass*, SubSubClass2*));
static assert(isImplicitlyConvertible(SubClass*, TopLevelInterface*));
static assert(isImplicitlyConvertible(SubClass*, OtherInterface*));
static assert(isImplicitlyConvertible(SubClass2*, TopLevel*));
static assert(isImplicitlyConvertible(SubClass2*, SubClass*));
static assert(isImplicitlyConvertible(SubClass2*, SubSubClass*));
static assert(isImplicitlyConvertible(SubClass2*, SubSubClass2*));
static assert(isImplicitlyConvertible(SubClass2*, TopLevelInterface*));
static assert(isImplicitlyConvertible(SubClass2*, OtherInterface*));
static assert(isImplicitlyConvertible(SubSubClass*, TopLevel*));
static assert(isImplicitlyConvertible(SubSubClass*, SubClass*));
static assert(isImplicitlyConvertible(SubSubClass*, SubClass2*));
static assert(isImplicitlyConvertible(SubSubClass*, SubSubClass2*));
static assert(isImplicitlyConvertible(SubSubClass*, TopLevelInterface*));
static assert(isImplicitlyConvertible(SubSubClass*, OtherInterface*));
static assert(isImplicitlyConvertible(SubSubClass2*, TopLevel*));
static assert(isImplicitlyConvertible(SubSubClass2*, SubClass*));
static assert(isImplicitlyConvertible(SubSubClass2*, SubClass2*));
static assert(isImplicitlyConvertible(SubSubClass2*, SubSubClass*));
static assert(isImplicitlyConvertible(SubSubClass2*, TopLevelInterface*));
static assert(isImplicitlyConvertible(SubSubClass2*, OtherInterface*));
static assert(isImplicitlyConvertible(TopLevelInterface*, TopLevel*));
static assert(isImplicitlyConvertible(TopLevelInterface*, SubClass*));
static assert(isImplicitlyConvertible(TopLevelInterface*, SubClass2*));
static assert(isImplicitlyConvertible(TopLevelInterface*, SubSubClass*));
static assert(isImplicitlyConvertible(TopLevelInterface*, SubSubClass2*));
static assert(isImplicitlyConvertible(TopLevelInterface*, OtherInterface*));
static assert(isImplicitlyConvertible(OtherInterface*, TopLevel*));
static assert(isImplicitlyConvertible(OtherInterface*, SubClass*));
static assert(isImplicitlyConvertible(OtherInterface*, SubClass2*));
static assert(isImplicitlyConvertible(OtherInterface*, SubSubClass*));
static assert(isImplicitlyConvertible(OtherInterface*, SubSubClass2*));
static assert(isImplicitlyConvertible(OtherInterface*, TopLevelInterface*));
