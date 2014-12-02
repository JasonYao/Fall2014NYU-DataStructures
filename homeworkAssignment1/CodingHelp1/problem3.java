import java.util.ArrayList;
/**
 * Write a description of class problem3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class problem3
{   
    public static void main (String[] args)
    {
        // initialise an array list
        ArrayList<String> stringArray = new ArrayList<String>();

        stringArray.add("Argentina");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Czech Republic");
        stringArray.add("France");
        stringArray.add("Georgia");
        stringArray.add("India");
        stringArray.add("India");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Chile");
        stringArray.add("Poland");
        
        stringArray.add("Romania");
        stringArray.add("Romania");

        if (duplicateRemove(stringArray))
        {
            System.out.println(stringArray);
            System.out.println("The ArrayList was changed");
        }
        else
        {
            System.out.println(stringArray);
            System.out.println("The ArrayList was not changed");
        }

    }
    
    public static boolean duplicateRemove(ArrayList toChange)
    {
        boolean changed = false; // Used as a return value later on, initialized to false, and is changed to true if ArrayList is changed

        // Iterates through the ArrayList, storing a word that will be used to go through the entire ArrayList
        for (int i = 0; i < toChange.size(); ++i)
        {
            String stored = toChange.get(i).toString();

            for (int j = 0; j < toChange.size(); ++j)
            {
                // For each word that was stored, goes through the entire ArrayList to find duplicates
                if (toChange.indexOf(stored) != toChange.lastIndexOf(stored))
                {
                    toChange.remove(toChange.lastIndexOf(stored)); // Removes the duplicates
                    changed = true; // Changes the return value if it is changed
                }
            }
        }
        return changed;
    }

    
}
