package storyworlds.example;

public class Wheel implements CarElement {

    private String name;
    
    public void accept(CarElementVisitor visitor) {
        visitor.visit(this);
    }
    
    public Wheel(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
