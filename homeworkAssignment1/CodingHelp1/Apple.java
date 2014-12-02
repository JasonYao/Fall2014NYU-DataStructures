
/**
 * Write a description of class Apple here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Apple extends Fruit
{
    public int sweetness;
    /**
     * Constructor for objects of class Apple
     */
    public Apple(int freshness, int taste, int sweetness)
    {
        super(freshness, taste);
        this.sweetness = sweetness;
        System.out.println("Apple constructor is called.");
    }
}
