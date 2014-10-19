
/**
 * Write a description of class Lemon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lemon extends Citrus
{
    public int lemonyness;
    /**
     * Constructor for objects of class Lemon
     */
    public Lemon(int freshness, int taste, int sourness, int lemonyness)
    {
        super(freshness, taste, sourness);
        this.lemonyness = lemonyness;
        System.out.println("Lemon constructor is called.");
    }
}
