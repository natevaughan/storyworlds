package storyworlds.create;

public class CreatableFactory {

    public static Createable parse(String input) {

        Createable c = Createable.ERROR;

        for (Createable createables : Createable.values()) {
            if (createables.toString().equalsIgnoreCase(input)) {
                c = createables;
            }
        }

        return c;
    }

}
