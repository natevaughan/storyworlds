package storyworlds.model;

public interface Item extends Persistable, Comparable<Item> {

    String getName();

    String getDescription();

    String getUseText();

    Player getCreator();

}
