package dev.norris;

    //Java primitives are one of the few things java has that aren't objects

public class Primitives {
    public static void main(String[] args) {
        boolean b = true;
        int i = 10;
        long l = 10;
        byte o = 10;
        short s = 10;
        double dd = 10.0;
        char c = 'c';

        //Strings are not primitives, they are special objects
        //Strings are "immutable," they cannot truly be changes

        String potato = "potato";
        potato = potato.concat("s");
        System.out.println(potato);

        //the reason you have to re-define a string is because of memory
        //strings are special in memory and makes java more memory efficient doing big computations
        //strings don't have a standardized place in memory
        //strings reside in string pools, which is memory made up of "unused memory"
        //the string pool will gather up all your allocated but unused memory, and actually use it, so that things don't go to waste

        //the mutable form of string is StringBuilder and StringBuffer
        String dog = "dog";
        StringBuilder doggo = new StringBuilder();
    }
}
