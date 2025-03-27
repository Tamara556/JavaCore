package classworks.classwork15;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CollectionsExample {

    public static void main(String[] args) {
  //  List<String> names = new ArrayList<>();
  //  names.add("poxos");
  //  names.add("petros");
  //  names.add("martiros");
  //  System.out.println(names);
  //  names.remove("martiros");
  //  System.out.println(names.reversed());

  //  for (int i = 0; i < names.size(); i++) {
  //      String name = names.get(i);
  //      System.out.println(name);
  //  }

  //  for (String name : names) {
  //      System.out.println(name);
  //  }

        Set<String> names = new HashSet<>();
        names.add("poxos");
        names.add("Ivan");
        names.add("petros");
        names.add("martiros");
        for (String name : names) {
            System.out.println(name);
        }

    }

}
