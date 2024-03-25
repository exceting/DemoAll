package demo.jdk;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class TT {
    static int number2=getValue();
    static int number1=10;

    static {
        String currentString = null;
              try {
                       BufferedReader reader = new BufferedReader(new FileReader("initconfig.txt"));
                        reader.lines().findFirst().orElse(null);
                  }
             catch (IOException ex) {
                      throw new NoSuchElementException("File initconfig.txt");
                  }
           }
    static int getValue()
    {
        return number1;
    }
     int doSum(){ return number1+number2;}
    static int doMinus(){ return number1-number2;}
    public static void main(String[] args)
    {
        List<String> letters = new ArrayList(Arrays.asList("D","B","A","C","F","G"));
        Predicate<String> p1 = s -> s.compareTo("C") > 0;
        Predicate<String> p2 = s -> s.equals("B");
        letters.removeIf(p1.negate().or(p2));
        letters.sort((s1,s2) -> s1.compareTo(s2));
        System.out.println(letters);
    }
}
