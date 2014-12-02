
/**
 * Write a description of class Lime here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lime extends Citrus
{
    int limeness;
    /**
     * Constructor for objects of class Lime
     */
    public Lime(int freshness, int taste, int sourness, int limeness)
    {
        super(freshness, taste, sourness);
        this.limeness = limeness;
       System.out.println("Lime constructor is called.");
    }
}
