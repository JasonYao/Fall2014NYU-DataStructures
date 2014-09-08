
/**
 * Write a description of class Citrus here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Citrus extends Fruit
{
    public int sourness;
    /**
     * Constructor for objects of class Citrus
     */
    public Citrus(int freshness, int taste, int sourness)
    {
        super(freshness, taste);
        this.sourness = sourness;
        System.out.println("Citrus constructor is called.");
    }
}
