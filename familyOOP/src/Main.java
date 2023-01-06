public class Main {
    public static void main(String[] args) {

        // Gen 1
        Person ylva = new Person("Ylva", 1940);
        Person john = new Person("John", 1942);
        Person inger = new Person("Inger", 1948);
        Person bodo = new Person("Bodo", 1944);
        Person renate = new Person("Renate", 1950);
        Person rolf = new Person("Rolf", 1944);

        // Gen 2
        Person dani = new Person("Daniella", 1978);
        Person thomas = john.haveChildWith(ylva, "Thomas", 1972);
        Person cathrin = inger.haveChildWith(bodo, "Cathrin", 1971);
        Person bjoern = renate.haveChildWith(rolf, "Björn", 1977);
        Person brita = rolf.haveChildWith(renate, "Brita", 1979);

        // Gen 3
        Person jacob = cathrin.haveChildWith(thomas, "Jacob", 1996);
        Person julia = brita.haveChildWith(thomas, "Julia", 2002);
        Person louisa = thomas.haveChildWith(brita, "Louisa", 2006);
        Person ella = dani.haveChildWith(bjoern, "Ella", 2012);


        // For easy looping collect all persons in the array people
        Person[] people = {
                ylva, john, inger, bodo, renate, rolf,
                dani, bjoern, brita,  cathrin, thomas,
                jacob, julia, louisa, ella
        };

        // Loop through each person and print information
        System.out.println("\n".repeat(60));
        for(Person person : people){
            System.out.println(person.getInfo());
        }

    }

}