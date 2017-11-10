package com.peoplenet.test;

public class StaticBlockTest {

    int x = 0;
    static int a = 10;

    static{
        display();
        a=100;
        display();
    }

    public StaticBlockTest() {

    }

    static void display(){
        System.out.println(a);
    }
}
