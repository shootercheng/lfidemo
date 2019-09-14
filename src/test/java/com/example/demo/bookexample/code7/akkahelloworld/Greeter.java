package com.example.demo.bookexample.code7.akkahelloworld;

import akka.actor.UntypedActor;
import com.example.demo.bookexample.code7.constant.Msg;

/**
 * @author chengdu
 * @date 2019/9/14.
 */
public class Greeter extends UntypedActor {

    @Override
    public void onReceive(Object o) throws Throwable {
        if (o == Msg.GREET){
            System.out.println("Hello World!");
            super.getSender().tell(Msg.DONE, super.getSelf());
        } else {
            super.unhandled(o);
        }
    }
}
