module super_usage;
import super_resolve_test;

class A : Super{
    this(){

    }
    void foo(){
        <ref>super.something();
    }
}

void main(string[] args){

}
