import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// Some  useful methods you can use with Array Lists
public class Lister {

    // Convert an array to an arrayList
    public static ArrayList arrayToArrayList(Object[] array){
        ArrayList list = new ArrayList();
        Collections.addAll(list, array);
        return list;
    }

    // Convert an array to an arrayList and remove null values
    public static ArrayList arrayToArrayListNoNull(Object[] array){
        var r = removeAllOccurrences(null, arrayToArrayList(array) );
        return r;
    }
    // Check if an item has a duplicate in a list
    public static boolean hasDuplicate(Object item, ArrayList list){
        return list.indexOf(item) != list.lastIndexOf(item);
    }

    // Return a new list with duplicates removed
    public static ArrayList removeDuplicates(ArrayList list){
        ArrayList filteredList = new ArrayList();
        for(Object item : list){
            if(!filteredList.contains(item)){
                filteredList.add(item);
            }
        }
        return filteredList;
    }

    // Return a new list consisting of only
    // the items were duplicates in the original list
    public static ArrayList getDuplicates(ArrayList list){
        ArrayList filteredList = new ArrayList();
        for(Object item : list){
            if(!filteredList.contains(item) && hasDuplicate(item, list)){
                filteredList.add(item);
            }
        }
        return filteredList;
    }

    // Return a new list consisting of only
    // the items weren't duplicates in the original list
    public static ArrayList getNonDuplicates(ArrayList list){
        ArrayList filteredList = new ArrayList();
        for(Object item : list){
            if(!filteredList.contains(item) && !hasDuplicate(item, list)){
                filteredList.add(item);
            }
        }
        return filteredList;
    }

    // Removes all occurrences of an item in a list
    public static ArrayList removeAllOccurrences(Object itemToRemove, ArrayList list){
        ArrayList filteredList = new ArrayList();
        for(Object item : list){
            // note: can't use only !equals for null values...
            if(!(item == null && itemToRemove == null) && !item.equals(itemToRemove)){
                filteredList.add(item);
            }
        }
        return filteredList;
    }

    // Join several lists into one (ignoring null values)
    public static ArrayList joinLists(ArrayList... lists){
        ArrayList joined = new ArrayList();
        for(ArrayList list : lists){
            if(list != null) {
                joined.addAll(list);
            }
        }
        return joined;
    }

    // Sort by a comparator
    public static ArrayList sort(ArrayList list, Comparator comparator){
        // make a copy and sort it
        // (sort is destructive and we don't want to change the original list)
        ArrayList listCopy = new ArrayList(list);
        Collections.sort(listCopy, comparator);
        return listCopy;
    }

}