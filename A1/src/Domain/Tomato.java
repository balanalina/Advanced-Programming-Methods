package Domain;


public class Tomato implements IVegetable {
    private double w;
    //constructor
    public Tomato(double w)  { this.w = w; }
    //copy constructor
    public Tomato(Tomato copy) { this.w = copy.w; }
    //getters and setters
    public double getW() { return this.w; }
    public void setW(double weight) { this.w = weight; }
    @Override
    public String toString() { return "Tomato: " + this.w + " kg. \n";}
}
