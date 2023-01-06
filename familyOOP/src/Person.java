import java.util.ArrayList;
import java.util.Comparator;

public class Person {

    private String name;

    private int birthYear;

    private Person[] parents = new Person[2];
    private ArrayList<Person> children = new ArrayList<>();

    public Person(String name, int birthYear){
        this.name = name;
        this.birthYear = birthYear;
    }

    public String getName(){
        return name;
    }

    public int getBirthYear(){
        return birthYear;
    }

    public String toString(){
        return getName() + " (b. " + getBirthYear() + ")";
    }

    public void registerChild(Person child){
        // Add me as a parent in a blank/null element
        // in the parents array of the child
        child.parents[child.parents[0] == null ? 0 : 1] = this;
        // Add the child to my list of children if not present there
        if(!children.contains(child)){
            children.add(child);
        }
    }

    public Person haveChildWith(Person partner, String childName, int birthYear){
        if(partner.equals(this)) {
            throw new RuntimeException("Can't make children with myself!");
            // or can you? if you adopt as a single parent?
        }
        // Create a child
        Person child = new Person(childName, birthYear);
        // Add as my child
        registerChild(child);
        // Add as my partners child
        partner.registerChild(child);
        return child;
    }

    public ArrayList<Person> getParents(){
        // convert the parents array to a list (easier to work with)
        return Lister.arrayToArrayListNoNull(parents);
    }

    public ArrayList<Person> getChildren(){
        return children;
    }

    public ArrayList<Person> getGrandParents(){
        // loop through my parents and get their parents
        ArrayList<Person> grandParents = new ArrayList<>();
        for(Person parent : getParents()){
            grandParents.addAll(parent.getParents());
        }
        return grandParents;
    }

    public ArrayList<Person> hasChildrenWith(){
        // Get all the parents of my children, remove me and duplicates
        ArrayList<Person> people = new ArrayList<>();
        for(Person child : children){
            people.addAll(child.getParents());
        }
        people = Lister.removeAllOccurrences(this, people);
        return Lister.removeDuplicates(people);
    }

    private ArrayList<Person> getSiblingsWithDuplicates() {
        // join my parents lists of children
        // (full siblings will be duplicates, appear twice in list)
        ArrayList siblings = Lister.joinLists(
                parents[0] == null ? null : parents[0].children,
                parents[1] == null ? null : parents[1].children
        );
        // remove me from the list and return it
        return Lister.removeAllOccurrences(this, siblings);
    }

    public ArrayList<Person> getAllSiblings(){
        return Lister.removeDuplicates(getSiblingsWithDuplicates());
    }

    public ArrayList<Person> getFullSiblings(){
        return Lister.getDuplicates(getSiblingsWithDuplicates());
    }

    public ArrayList<Person> getHalfSiblings(){
        return Lister.getNonDuplicates(getSiblingsWithDuplicates());
    }

    // untie = aunt or uncle
    // note : does not include unties by marriage/partnership
    public ArrayList<Person> getUnties(){
        ArrayList<Person> unties = new ArrayList<>();
        for(Person parent : getParents()){
            unties.addAll(parent.getAllSiblings());
        }
        return unties;
    }

    public ArrayList<Person> getCousins(){
        ArrayList<Person> cousins = new ArrayList<>();
        for(Person untie : getUnties()){
            cousins.addAll(untie.getChildren());
        }
        return cousins;
    }

    public String getInfo(){
        // List info about family relations for this person
        Comparator byName =  Comparator.comparing(Person:: getName);
        Object[] info = {
                "Name: ", this,
                "Parents: ", Lister.sort(getParents(), byName),
                "Grand parents: ", Lister.sort(getGrandParents(), byName),
                "Unties (aunts & uncles): ", Lister.sort(getUnties(), byName),
                "Cousins: ", Lister.sort(getCousins(), byName),
                "Has children with: ", Lister.sort(hasChildrenWith(), byName),
                "Children: ", Lister.sort(getChildren(), byName),
                "Full siblings: ", Lister.sort(getFullSiblings(), byName),
                "Half siblings: ", Lister.sort(getHalfSiblings(), byName),
                "All siblings: ", Lister.sort(getAllSiblings(), byName),
        };
        StringBuilder infoAsString = new StringBuilder();
        for(int i = 0; i <= info.length - 1; i+=2){
            if(info[i+1].toString().equals("[]")){ continue; }
            infoAsString.append(info[i]);
            infoAsString.append(info[i + 1]);
            infoAsString.append("\n");
        }
        infoAsString.append("\n");
        return infoAsString.toString();
    }

}