package storyworlds.example;

import java.util.Arrays;
import java.util.List;

public class Car implements CarElement {

    List<CarElement> elements;
    
    public Car() {
        this.elements = Arrays.asList(new CarElement[] { new Wheel("front left"),
            new Wheel("front right"), new Wheel("back left") ,
            new Wheel("back right"), new Body(), new Engine() });
    }
    
    public void accept(CarElementVisitor visitor) {
        for (CarElement element : elements) {
            element.accept(visitor);
        }
        visitor.visit(this);
    }
    
}
