
/**
 * Write a description of class Fruit here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Fruit
{
    public int freshness;
    public int taste;
    /**
     * Constructor for objects of class Fruit
     */
    public Fruit(int startFreshness, int startTaste)
    {
        freshness = startFreshness;
        taste = startTaste;
        
        System.out.println("Fruit constructor is called.");
    }
}
