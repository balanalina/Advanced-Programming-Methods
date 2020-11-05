package Domain;

public class Peppers implements IVegetable {
    private double w;
    //constructor
    public Peppers(double w){ this.w = w; }
    //copy constructr
    public Peppers(Peppers copy) { this.w = copy.w;}
    //getters and setters
    public double getW() { return this.w; }
    public void setW(double weight) { this.w = weight;}
    @Override
    public String toString() { return "Pepper: " + this.w + " kg. \n";}
}
