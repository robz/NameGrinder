import java.util.*;
import java.io.*;

class NameGrinder {
    
    public static ArrayList<String> readNamesFromFile(String filename) throws IOException {
        ArrayList<String> list = new ArrayList<String>();

        BufferedReader reader = new BufferedReader(
            new FileReader(filename)
            );

        String line = null;
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        
        return list;
    }   

    public static ArrayList<String> generateSublist(ArrayList<String> list, int numElements) {
        if (numElements > list.size()) {
            System.out.println("number of elements too big!");
            return null;
        }

        HashSet<Integer> set = new HashSet<Integer>();
        ArrayList<String> newlist = new ArrayList<String>();

        do {
            int index = (int)(Math.random()*list.size());

            if (set.contains(index)) {
                continue;
            }

            set.add(index);
            newlist.add(list.get(index));
        } while (newlist.size() < numElements);

        return newlist;
    }

    public static void displayNames(ArrayList<String> list) {
        String s = "",
               prefix = "";

        for (int i = 0; i < list.size(); i++) {
            s += prefix + list.get(i);
            prefix = ", ";
        }

        System.out.println(s);
    }

    public static ArrayList<String> getUserEnteredList() {
        return null;
    }

    public static boolean listsAreEqual(
        ArrayList<String> list1,
        String[] list2
        ) 
    {
        if (list1.size() != list2.length) {
            return false;
        }

        for (int i = 0; i < list1.size(); i++) {
            if (!list1.get(i).equals(list2[i])) {
                return false;
            }
        }

        return true;
    }
    
    public static void main(String[] args) throws IOException {
        ArrayList<String> sublist, 
            list = readNamesFromFile("names.txt");
        Console console = System.console();

        while (true) {
            sublist = generateSublist(list, 5);
            
            boolean failed = true;
            while (failed) {
                displayNames(sublist);
                
                console.readLine("hit enter");

                // clear screen
                for (int i = 0; i < 100; i++) {
                    System.out.println();
                }

                String[] enteredlist = console.readLine().split(" ");

                failed = !listsAreEqual(sublist, enteredlist);
                
                if (failed) {
                    System.out.println("wrong!");
                } else {
                    System.out.println("that's right!");
                }
            }
        }
    }
}
