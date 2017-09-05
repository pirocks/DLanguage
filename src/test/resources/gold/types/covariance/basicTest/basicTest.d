module gold.types.covariance.basicTest.basicTest;                                                                     //
//be careful when editing this file. element offsets are hard coded in tests                                          //
import declarations;                                                                                                  //
                                                                                                                      //
void main(string[] args){                                                                                             //
                                                                                                                      //
}                                                                                                                     //
                                                                                                                      //
unittest{//#1                                                                                                         //
    TopLevel a(){return null;}                                                                                        //
    SubClass b(){return null;}                                                                                        //
    static assert(!isCovariantWith!(typeof(a),typeof(b)));                                                            //
}                                                                                                                     //
unittest{//#2                                                                                                         //
    TopLevel a(){return null;}                                                                                        //
    SubClass2 b(){return null;}                                                                                       //
    static assert(!isCovariantWith!(typeof(a),typeof(b)));                                                            //
}                                                                                                                     //
unittest{//#3                                                                                                         //
    TopLevel a(){return null;}                                                                                        //
    SubSubClass b(){return null;}                                                                                     //
    static assert(!isCovariantWith!(typeof(a),typeof(b)));                                                            //
}                                                                                                                     //
unittest{//#4                                                                                                         //
    TopLevel a(){return null;}                                                                                        //
    SubSubClass2 b(){return null;}                                                                                    //
    static assert(!isCovariantWith!(typeof(a),typeof(b)));                                                            //
}                                                                                                                     //
unittest{//#5                                                                                                         //
    TopLevel a(){return null;}                                                                                        //
    TopLevelInterface b(){return null;}                                                                               //
    static assert(isCovariantWith!(typeof(a),typeof(b)));                                                             //
}                                                                                                                     //
unittest{//#6                                                                                                         //
    TopLevel a(){return null;}                                                                                        //
    OtherInterface b(){return null;}                                                                                  //
    static assert(!isCovariantWith!(typeof(a),typeof(b)));                                                            //
}                                                                                                                     //
unittest{//#7                                                                                                         //
    SubClass a(){return null;}                                                                                        //
    TopLevel b(){return null;}                                                                                        //
    static assert(isCovariantWith!(typeof(a),typeof(b)));                                                             //
}                                                                                                                     //
unittest{//#8                                                                                                         //
    SubClass a(){return null;}                                                                                        //
    SubClass2 b(){return null;}                                                                                       //
    static assert(!isCovariantWith!(typeof(a),typeof(b)));                                                            //
}                                                                                                                     //
unittest{//#9                                                                                                         //
    SubClass a(){return null;}                                                                                        //
    SubSubClass b(){return null;}                                                                                     //
    static assert(!isCovariantWith!(typeof(a),typeof(b)));                                                            //
}                                                                                                                     //
unittest{//#10                                                                                                        //
    SubClass a(){return null;}                                                                                        //
    SubSubClass2 b(){return null;}                                                                                    //
    static assert(!isCovariantWith!(typeof(a),typeof(b)));                                                            //
}                                                                                                                     //
unittest{//#11                                                                                                        //
    SubClass a(){return null;}                                                                                        //
    TopLevelInterface b(){return null;}                                                                               //
    static assert(isCovariantWith!(typeof(a),typeof(b)));                                                             //
}                                                                                                                     //
unittest{//#12                                                                                                        //
    SubClass a(){return null;}                                                                                        //
    OtherInterface b(){return null;}                                                                                  //
    static assert(!isCovariantWith!(typeof(a),typeof(b)));                                                            //
}                                                                                                                     //
unittest{//#13                                                                                                        //
    SubClass2 a(){return null;}                                                                                       //
    TopLevel b(){return null;}                                                                                        //
    static assert(isCovariantWith!(typeof(a),typeof(b)));                                                             //
}                                                                                                                     //
unittest{//# 14                                                                                                       //
    SubClass2 a(){return null;}                                                                                       //
    SubClass b(){return null;}                                                                                        //
    static assert(!isCovariantWith!(typeof(a),typeof(b)));                                                            //
}                                                                                                                     //
unittest{//#15                                                                                                        //
    SubClass2 a(){return null;}                                                                                       //
    SubSubClass b(){return null;}                                                                                     //
    static assert(!isCovariantWith!(typeof(a),typeof(b)));                                                            //
}                                                                                                                     //
unittest{//#16                                                                                                        //
    SubClass2 a(){return null;}                                                                                       //
    SubSubClass2 b(){return null;}                                                                                    //
    static assert(!isCovariantWith!(typeof(a),typeof(b)));                                                            //
}                                                                                                                     //
unittest{//#17                                                                                                        //
    SubClass2 a(){return null;}                                                                                       //
    TopLevelInterface b(){return null;}                                                                               //
    static assert(isCovariantWith!(typeof(a),typeof(b)));                                                             //
}                                                                                                                     //
unittest{//#18                                                                                                        //
    SubClass2 a(){return null;}                                                                                       //
    OtherInterface b(){return null;}                                                                                  //
    static assert(!isCovariantWith!(typeof(a),typeof(b)));                                                            //
}                                                                                                                     //
unittest{//#19                                                                                                        //
    SubSubClass a(){return null;}                                                                                     //
    TopLevel b(){return null;}                                                                                        //
    static assert(isCovariantWith!(typeof(a),typeof(b)));                                                             //
}                                                                                                                     //
unittest{//#20                                                                                                        //
    SubSubClass a(){return null;}                                                                                     //
    SubClass b(){return null;}                                                                                        //
    static assert(isCovariantWith!(typeof(a),typeof(b)));                                                             //
}                                                                                                                     //
unittest{//#21                                                                                                        //
    SubSubClass a(){return null;}                                                                                     //
    SubClass2 b(){return null;}                                                                                       //
    static assert(!isCovariantWith!(typeof(a),typeof(b)));                                                            //
}                                                                                                                     //
unittest{//#22                                                                                                        //
    SubSubClass a(){return null;}                                                                                     //
    SubSubClass2 b(){return null;}                                                                                    //
    static assert(!isCovariantWith!(typeof(a),typeof(b)));                                                            //
}                                                                                                                     //
unittest{//#23                                                                                                        //
    SubSubClass a(){return null;}                                                                                     //
    TopLevelInterface b(){return null;}                                                                               //
    static assert(isCovariantWith!(typeof(a),typeof(b)));                                                             //
}                                                                                                                     //
unittest{//#24                                                                                                        //
    SubSubClass a(){return null;}                                                                                     //
    OtherInterface b(){return null;}                                                                                  //
    static assert(!isCovariantWith!(typeof(a),typeof(b)));                                                            //
}                                                                                                                     //
unittest{//#25                                                                                                        //
    SubSubClass2 a(){return null;}                                                                                    //
    TopLevel b(){return null;}                                                                                        //
    static assert(isCovariantWith!(typeof(a),typeof(b)));                                                             //
}                                                                                                                     //
unittest{//#26                                                                                                        //
    SubSubClass2 a(){return null;}                                                                                    //
    SubClass b(){return null;}                                                                                        //
    static assert(!isCovariantWith!(typeof(a),typeof(b)));                                                            //
}                                                                                                                     //
unittest{//#27                                                                                                        //
    SubSubClass2 a(){return null;}                                                                                    //
    SubClass2 b(){return null;}                                                                                       //
    static assert(isCovariantWith!(typeof(a),typeof(b)));                                                             //
}                                                                                                                     //
unittest{//#28                                                                                                        //
    SubSubClass2 a(){return null;}                                                                                    //
    SubSubClass b(){return null;}                                                                                     //
    static assert(!isCovariantWith!(typeof(a),typeof(b)));                                                            //
}                                                                                                                     //
unittest{//#29                                                                                                        //
    SubSubClass2 a(){return null;}                                                                                    //
    TopLevelInterface b(){return null;}                                                                               //
    static assert(isCovariantWith!(typeof(a),typeof(b)));                                                             //
}                                                                                                                     //
unittest{//#30                                                                                                        //
    SubSubClass2 a(){return null;}                                                                                    //
    OtherInterface b(){return null;}                                                                                  //
    static assert(isCovariantWith!(typeof(a),typeof(b)));                                                             //
}                                                                                                                     //
unittest{//#31                                                                                                        //
    TopLevelInterface a(){return null;}                                                                               //
    TopLevel b(){return null;}                                                                                        //
    static assert(!isCovariantWith!(typeof(a),typeof(b)));                                                            //
}                                                                                                                     //
unittest{//#32                                                                                                        //
    TopLevelInterface a(){return null;}                                                                               //
    SubClass b(){return null;}                                                                                        //
    static assert(!isCovariantWith!(typeof(a),typeof(b)));                                                            //
}                                                                                                                     //
unittest{//#33                                                                                                        //
    TopLevelInterface a(){return null;}                                                                               //
    SubClass2 b(){return null;}                                                                                       //
    static assert(!isCovariantWith!(typeof(a),typeof(b)));                                                            //
}                                                                                                                     //
unittest{//#34                                                                                                        //
    TopLevelInterface a(){return null;}                                                                               //
    SubSubClass b(){return null;}                                                                                     //
    static assert(!isCovariantWith!(typeof(a),typeof(b)));                                                            //
}                                                                                                                     //
unittest{//#35                                                                                                        //
    TopLevelInterface a(){return null;}                                                                               //
    SubSubClass2 b(){return null;}                                                                                    //
    static assert(!isCovariantWith!(typeof(a),typeof(b)));                                                            //
}                                                                                                                     //
unittest{//#36                                                                                                        //
    TopLevelInterface a(){return null;}                                                                               //
    OtherInterface b(){return null;}                                                                                  //
    static assert(!isCovariantWith!(typeof(a),typeof(b)));                                                            //
}                                                                                                                     //
unittest{//#37                                                                                                        //
    OtherInterface a(){return null;}                                                                                  //
    TopLevel b(){return null;}                                                                                        //
    static assert(!isCovariantWith!(typeof(a),typeof(b)));                                                            //
}                                                                                                                     //
unittest{//#38                                                                                                        //
    OtherInterface a(){return null;}                                                                                  //
    SubClass b(){return null;}                                                                                        //
    static assert(!isCovariantWith!(typeof(a),typeof(b)));                                                            //
}                                                                                                                     //
unittest{//#39                                                                                                        //
    OtherInterface a(){return null;}                                                                                  //
    SubClass2 b(){return null;}                                                                                       //
    static assert(!isCovariantWith!(typeof(a),typeof(b)));                                                            //
}                                                                                                                     //
unittest{//#40                                                                                                        //
    OtherInterface a(){return null;}                                                                                  //
    SubSubClass b(){return null;}                                                                                     //
    static assert(!isCovariantWith!(typeof(a),typeof(b)));                                                            //
}                                                                                                                     //
unittest{//#41                                                                                                        //
    OtherInterface a(){return null;}                                                                                  //
    SubSubClass2 b(){return null;}                                                                                    //
    static assert(!isCovariantWith!(typeof(a),typeof(b)));                                                            //
}                                                                                                                     //
                                                                                                                      //

class TopLevel : TopLevelInterface{}

class SubClass : TopLevel{}

class SubClass2 : TopLevel{}

class SubSubClass : SubClass{}

class SubSubClass2 : SubClass2, OtherInterface{}

interface TopLevelInterface {}

interface OtherInterface{}
