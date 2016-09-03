package storyworlds.example;

public class VisitorMain {
    public static void main(String[] args) {
        Car car = new Car();
        CarElementDoVisitor visitor = new CarElementDoVisitor();
        car.accept(visitor);
    }
}
