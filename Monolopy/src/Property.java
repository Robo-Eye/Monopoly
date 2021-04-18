
public abstract class Property extends Space{
    public String name;
    public int cost;
    public int morgage;

/**
 * abstract classes for the morgage and rent of properties
 */

public Property(String name, int cost, int morgage) {
    this.name=name;
    this.cost=cost;
    this.morgage=morgage;
}
abstract void rent();
abstract void morgage();
}

