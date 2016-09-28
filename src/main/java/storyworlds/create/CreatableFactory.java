package storyworlds.create;

public class CreatableFactory {

    public static Createables get(String input) {
        Createables createables = parse(input);
//        switch (createables) {
//            default:
//                return new Error("Unhandled createables: " + input);
//        }
        return createables;
    }

    public static Createables parse(String input) {

        Createables c = Createables.ERROR;

        for (Createables createables : Createables.values()) {
            if (createables.toString().equalsIgnoreCase(input)) {
                c = createables;
            }
        }

        return c;
    }

}
