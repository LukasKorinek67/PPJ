public class SealedClasses {

    /* Sealed Classes jsou novým typem tříd představeným v Javě 15. Umožňují omezit počet potomků třídy a tím zlepšit
    bezpečnost a čitelnost kódu. Sealed Classes také umožňují definovat pouze třídy final nebo non-sealed jako potomky,
    což umožňuje snadnější správu a úpravy kódu. */

    public sealed class Transport permits Truck, Train, Ship {
        private final double capacity;

        public Transport(double capacity) {
            this.capacity = capacity;
        }

        public double getCapacity() {
            return capacity;
        }
    }

    public final class Truck extends Transport {
        public Truck(double capacity) {
            super(capacity);
        }
    }

    public final class Train extends Transport {
        public Train(double capacity) {
            super(capacity);
        }
    }

    public final class Ship extends Transport {
        public Ship(double capacity) {
            super(capacity);
        }
    }


    /* V této hierarchii tříd je třída Transport třídou označenou jako Sealed, tzn. může být rozšířena pouze třídami Truck, Train a Ship.
    To znamená, že žádná jiná třída nemůže být děděna z třídy Transport.
    Dále jsou třídy Truck, Train a Ship třídami označenými jako final, což znamená, že nemohou být dále rozšiřovány dalšími třídami.

    Nyní můžeme vytvořit metodu pro výběr správného dopravního prostředku na základě hmotnosti zboží: */

    public Transport selectTransport(double weight) {
        if (weight <= 20) {
            return new Truck(20);
        } else if (weight <= 200) {
            return new Train(200);
        } else {
            return new Ship(1000);
        }
    }

    /* Tato metoda vytvoří instanci třídy Truck, Train nebo Ship na základě hmotnosti zboží.
    Výsledná instance je typu Transport, ale může být buď Truck, Train nebo Ship, protože ty jsou jediné povolené typy v hierarchii tříd Transport.
    Díky Sealed Classes se zajistí, že pouze tyto třídy mohou být použity pro přepravu zboží, což zlepšuje bezpečnost a přehlednost kódu. */

}
