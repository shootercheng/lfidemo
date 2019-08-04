package com.example.demo.bookexample;

/**
 * @author chengdu
 * @date 2019/8/4.
 */
public class Mule implements IAnimal, IHorse, IDonKey {

    @Override
    public void run(){
        IDonKey.super.run();
    }

    @Override
    public void eat() {
        System.out.println("eating");
    }

    public static void main(String[] args){
        Mule mule = new Mule();
        mule.eat();
        mule.breath();
        mule.run();
    }
}
