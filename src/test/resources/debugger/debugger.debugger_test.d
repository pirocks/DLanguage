module debugger.debugger_test;

import std.stdio;

void main(string[] args){
    foreach(arg;args){
        writeln(arg);
    }
    inner_loop();
}


void inner_loop(){
    for(uint i = 0; i < 10;i++){
        writeln(to!string(i));
    }
}
