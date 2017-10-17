package com.peoplenet.test;

import org.junit.Test;

public class RunTest {


    @Test
    public void runMe(){
        StaticBlockTest sbt = new StaticBlockTest();
        System.out.println("method call "+StaticBlockTest.a);
    }


}
