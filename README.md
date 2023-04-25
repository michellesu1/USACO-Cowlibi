# USACO-Cowlibi
 
<details>
    <summary> Problem Statement </summary>    
**Note: The time limit for this problem is 4s, two times the default.**

Somebody has been grazing in Farmer John's (1≤G≤105)
 private gardens! Using his expert forensic knowledge, FJ has been able to determine the precise time each garden was grazed. He has also determined that there was a single cow that was responsible for every grazing incident.

In response to these crimes each of FJ's N
 (1≤N≤105)
 cows have provided an alibi that proves the cow was in a specific location at a specific time. Help FJ test whether each of these alibis demonstrates the cow's innocence.

A cow can be determined to be innocent if it is impossible for her to have travelled between all of the grazings and her alibi. Cows travel at a rate of 1 unit distance per unit time.

INPUT FORMAT (input arrives from the terminal / stdin):
The first line of input will contain G
 and N
 separated by a space.
The next G
 lines contain the integers x
, y
, and t
 (−109≤x,y≤109;0≤t≤109)
 separated by a space describing the location and time of the grazing. It will always be possible for a single cow to travel between all grazings.

The next N
 lines contain x
, y
, and t
 (−109≤x,y≤109;0≤t≤109)
 separated by a space describing the location and time of each cow's alibi.

OUTPUT FORMAT (print output to the terminal / stdout):
Output a single integer: the number of cows with alibis that prove their innocence.

</details>

[Full problem statement](http://www.usaco.org/index.php?page=viewproblem2&cpid=1303)

- See Cowlibi2.java for the source code.
- I created a grazing class and implemented a custom comparator to sort all of the grazing events in a TreeSet based on time. I checked whether it was possible for each cow to reach the grazings that occured before and after her alibi.
- My solution has O(N*logN) time complexity because I leveraged the floor() and ceiling() binary search TreeSet methods, which have O(logN) time complexity. This is necessary for the problem's time limit.
- I used Java's BigDecimal class to prevent overflow errors.
