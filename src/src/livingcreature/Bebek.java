package livingcreature;

public class Bebek extends FarmAnimal implements EggProdAnimal, MeatProdAnimal{

	public Bebek(int x, int y) {
		// TODO Auto-generated constructor stub
		super(x,y);
		super.setProdReady(false);
	}
	public boolean prodEgg() {return true;}
    public boolean prodMeat() {return true;}
    
    public String render() {
        if (isProdReady()) return("img/duck_red.png");
        else return("img/duckling.png");}
    
    
    public String getSound(){
        return("duck.wav");
    }
}
