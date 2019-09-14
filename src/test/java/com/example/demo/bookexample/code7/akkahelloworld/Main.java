package com.example.demo.bookexample.code7.akkahelloworld;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;

/**
 * @author chengdu
 * @date 2019/9/14.
 */
public class Main {

    public static void main(String[] args){
        ActorSystem system = ActorSystem.create("Hello", ConfigFactory.load("actorsys.conf"));
        ActorRef actorRef = system.actorOf(Props.create(HelloWorld.class), "helloworld");
        System.out.println("HelloWorld Actor Path: " + actorRef.path());
    }
}
