
import java.io.File;
import java.io.IOException;
import java.util.*;


public class TextAnalyzer {

    public HashMap<String, Integer> map;
//Main---------------------------------------------------------------------------------------------
    public static void main(String[] args) throws IOException {
        String file = args[0];
        TextAnalyzer txtA = new TextAnalyzer(file);

        System.out.println ("Number of words found = " + txtA.wordCount());
        System.out.println ("Word and frequencies sorted by word frequencies");
        txtA.FreqSort();
        System.out.println ("Word and frequencies sorted lexicographically by words");
        txtA.LexiSort();
    }
//Constructor---------------------------------------------------------------------------------------
    public TextAnalyzer(String fileName) throws IOException {
        String input = new Scanner(new File(fileName)).useDelimiter("\\A").next();
        StringTokenizer st = new StringTokenizer(input);
        HashMap<String, Integer> map = new HashMap<>();
        while (st.hasMoreTokens()) {
            String token = st.nextToken().toLowerCase();
            if (map.containsKey(token) == false)
                map.put(token, 1);
            else {
                Integer value = map.get(token);
                value++;
                map.put(token, value);
            }
        }
            this.map = map;

    }
//WordCount Code----------------------------------------------------------------------
    public int wordCount(){
        int value = 0;
        for (String name: map.keySet()){
            value++;
            }
    return value;
    }
//Frequency Sort Code-----------------------------------------------------------------

    class freqComparator implements Comparator<node> {

        public int compare(node a, node b) {
            return a.value.compareTo(b.value);
        }
    }

    public void FreqSort()
    {
        Comparator<node> comparator = new freqComparator().reversed();
        PriorityQueue<node> pq = new PriorityQueue<>(wordCount(),comparator);
        for (String name: map.keySet()){
            pq.add(new node(name,map.get(name)));
        }
        while (!pq.isEmpty())
            System.out.println(pq.peek().key + "-->" + pq.poll().value);
    }

//Lexical Sort Code-------------------------------------------------------------------
    class lexComparator implements Comparator<node> {

        public int compare(node a, node b) {
            return a.key.compareTo(b.key);
        }
    }

    public void LexiSort()
    {
        Comparator<node> comparator = new lexComparator();
        PriorityQueue<node> pq = new PriorityQueue<>(wordCount(),comparator);
        for (String name: map.keySet()){
            pq.add(new node(name,map.get(name)));
        }
        while (!pq.isEmpty())
            System.out.println(pq.peek().key + "-->" + pq.poll().value);
    }
}

class node {
    public String key;
    public Integer value;

    public node(String key, Integer value) {
        this.key = key;
        this.value = value;
    }
}

