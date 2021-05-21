package com.js;

/**
 * Hello world!
 */
public class SayHelloService implements ISayHelloService {
    @Override
    public String sayHello(String txt) {
        return "Hello " + txt;
    }
}
