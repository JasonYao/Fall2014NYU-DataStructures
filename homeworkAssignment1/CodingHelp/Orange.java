
/**
 * Write a description of class Orange here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Orange extends Citrus
{
    public int orangeness;
    /**
     * Constructor for objects of class Orange
     */
    public Orange(int freshness, int taste, int sourness, int orangeness)
    {
        super(freshness, taste, sourness);
        this.orangeness = orangeness;
        System.out.println("Orange constructor is called.");
    }
}
