module declarations;

class TopLevel : TopLevelInterface{}

class SubClass : TopLevel{}

class SubClass2 : TopLevel{}

class SubSubClass : SubClass{}

class SubSubClass2 : SubClass2, OtherInterface{}

interface TopLevelInterface {}

interface OtherInterface{}
