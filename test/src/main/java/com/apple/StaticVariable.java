package com.apple;

public class StaticVariable
{
    static int count=0;
    public void increment()
    {
        count++;
    }
    public static void main(String args[])
    {
        StaticVariable obj1=new StaticVariable();
        StaticVariable obj2=new StaticVariable();
        obj1.increment();
        obj2.increment();
        System.out.println("Obj1: count is="+obj1.count);
        System.out.println("Obj2: count is="+obj2.count);
    }
}
