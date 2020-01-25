package livingcreature;

public class Angsa extends FarmAnimal implements EggProdAnimal, MeatProdAnimal{

	public Angsa(int x, int y) {
		// TODO Auto-generated constructor stub
		super(x,y);
		super.setProdReady(false);
	}
	public boolean prodEgg() {return true;}
    public boolean prodMeat() {return true;}
    
    public String render() {
        if (isProdReady()) return("img/swan_red.png");
        else return("img/swan.png");}

    public String getSound(){
        return("swan.wav");
        
    }
}
