import java.util.*;
import java.io.*;
import java.math.BigDecimal;
// BigDecimal needed to prevent overflow error, squared values can reach up to 10^18

class Cowlibi2 {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(new File("cowlibi.in"));
        //Scanner input = new Scanner(System.in);

        int G = input.nextInt();
        int N = input.nextInt();
        TreeSet<Grazing> grazings = new TreeSet<>();
        for (int i = 0; i < G; i++) {
            grazings.add(new Grazing(input.nextInt(), input.nextInt(), input.nextInt()));
            // stores x, y, and time for each grazing (see Grazing class under main class)
        }

        int total = 0;
        for (int i = 0; i < N; i++) {
            int x = input.nextInt();
            int y = input.nextInt();
            int t = input.nextInt();
            Grazing alibi = new Grazing(x,y,t);
            boolean before_works = false;
            boolean after_works = false;
            // grazings.floor() and grazings.ceiling() have O(log N) time complexity because they uses a binary search tree
            if (alibi.time<=grazings.first().time) {
                // cow's alibi occured before all grazings
        
                before_works = true;
            } else {
                // finds distance between alibi location and the grazing before that alibi
                // determines whether cow could have commuted from grazing to alibi location in time
                Grazing before = grazings.floor(alibi);
                BigDecimal diff_x=BigDecimal.valueOf(alibi.x-before.x);
                BigDecimal diff_xs=diff_x.multiply(diff_x);
                BigDecimal diff_y=BigDecimal.valueOf(alibi.y-before.y);
                BigDecimal diff_ys=diff_y.multiply(diff_y);
                BigDecimal dist = diff_xs.add(diff_ys);
                BigDecimal time=BigDecimal.valueOf(alibi.time-before.time);
                BigDecimal times =time.multiply(time);
               
                int result = dist.compareTo(times);
                if (result<=0){
                    before_works = true;
                }
            }
            if (alibi.time>=grazings.last().time) {
                after_works = true;
                // cow's alibi occured after all grazings
            } else {
                // finds distance between alibi location nd the grazing after that alibi
                // determines whether cow could have commuted from alibi location to grazing
                Grazing after = grazings.ceiling(alibi);
                BigDecimal diff_x=BigDecimal.valueOf(alibi.x-after.x);
                BigDecimal diff_xs=diff_x.multiply(diff_x);
                BigDecimal diff_y=BigDecimal.valueOf(alibi.y-after.y);
                BigDecimal diff_ys=diff_y.multiply(diff_y);
                BigDecimal dist = diff_xs.add(diff_ys);
                BigDecimal time=BigDecimal.valueOf(alibi.time-after.time);
                BigDecimal times =time.multiply(time);
                
               int result = dist.compareTo(times);
                if (result <=0){
                    after_works = true;
                }
            }
            if (!before_works || !after_works) {
                total++;
            }
        }
        System.out.println(total);
    }

    static class Grazing implements Comparable<Grazing> {
        // implements custom comparator to allow sorting of grazings in TreeSet based on time
        int x;
        int y;
        int time;

        public Grazing (int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time=time;
        }
        public int compareTo(Grazing g) {
            return time-g.time;
        }
    }

}