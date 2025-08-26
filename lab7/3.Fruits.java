public class Fruits {
    public static void main(String[] args) {
        Fruit fruit = new GoldenDelicious();
        Orange orange = new Orange() ;
        System.out.println(fruit);
        System.out.println(orange);

        System.out.println("Is fruit instanceof Orange? " + (fruit instanceof Orange));
        System.out.println("Is fruit instanceof Apple? " + (fruit instanceof Apple));
        System.out.println("Is fruit instanceof GoldenDelicious? " + (fruit instanceof GoldenDelicious));
        System.out.println("Is fruit instanceof Macintosh? " + (fruit instanceof Macintosh));
        System.out.println("Is orange instanceof Orange? " + (orange instanceof Orange));
        System.out.println("Is orange instanceof Fruit? " + (orange instanceof Fruit));
        //System.out.println("Is orange instanceof Apple? " + (orange instanceof Apple));
    }



}
class Fruit{
    public String toString(){
        return "Fruit";
    }
};

class Orange extends Fruit{
    @Override
    public String toString() {
        return "Orange";
    }
}

class Apple extends Fruit{
    @Override
    public String toString() {
        return "Apple";
    }
}

class Macintosh extends Apple{
    @Override
    public String toString() {
        return "Macintosh";
    }
}

class GoldenDelicious extends Apple{
    @Override
    public String toString() {
        return "GoldenDelicious";
    }
}
