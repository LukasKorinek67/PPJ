public class Records {

    /* Records - nový typ pro definici datových tříd.
    Zjednodušuje a zkracuje definici tříd pro ukládání dat a umožňuje
    generovat kód pro equals(), hashCode() a toString() metody automaticky.
    Příklad: */


    public static void main(String[] args) {
        Person person = new Person("Jan Novák", 30);
        // získání jména
        String name = person.name();
        // porovnání dvou instancí
        if (person.equals(new Person("Jan Novák", 30))) {
            System.out.println("Persons are equal");
        }
    }

    public record Person(String name, int age) {}
    // vytvoření instance

}
