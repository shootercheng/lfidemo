package com.example.demo.bookexample.code7.akkahelloworld;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import com.example.demo.bookexample.code7.constant.Msg;

/**
 * @author chengdu
 * @date 2019/9/14.
 */
public class HelloWorld extends UntypedActor {

    ActorRef actorRef;

    @Override
    public void preStart() throws Exception {
        actorRef = super.getContext().actorOf(Props.create(Greeter.class), "greeter");
        System.out.println("Greeter Actor Path:" + actorRef.path());
        actorRef.tell(Msg.GREET, super.getSelf());
    }

    @Override
    public void onReceive(Object o) throws Throwable {
        if (o == Msg.DONE) {
            actorRef.tell(Msg.GREET, super.getSelf());
            super.getContext().stop(super.getSelf());
        } else {
            super.unhandled(o);
        }
    }
}
