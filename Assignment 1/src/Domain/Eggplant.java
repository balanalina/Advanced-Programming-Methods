package Domain;

public class Eggplant implements IVegetable{
    private double w;
    //constructr
    public Eggplant(double w){
        this.w = w;
    }
    //copy constructor
    public Eggplant(Eggplant copy) { this.w = copy.w; }
    // getters and setters
    public double getW(){ return this.w; }
    public void setW(double weight){ this.w = weight;}
    @Override
    public String toString(){ return "Eggplant: " + this.w +  "kg. \n";}
}
