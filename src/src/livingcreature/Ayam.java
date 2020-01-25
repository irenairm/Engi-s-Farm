package livingcreature;

public class Ayam extends FarmAnimal implements EggProdAnimal, MeatProdAnimal{

	public Ayam(int x, int y) {
		// TODO Auto-generated constructor stub
		super(x, y);
		super.setProdReady(false);
	}
	
	public boolean prodEgg() {return true;}
    public boolean prodMeat() {return true;}
    
    public String render() {
        if (isProdReady()) return("img/hen.png");
        else return("img/chick.png");}

    
    public String getSound(){
       return("chicken.wav");
    }

}
