package livingcreature;

public class Kuda extends FarmAnimal implements MilkProdAnimal, MeatProdAnimal{

	public Kuda(int x, int y) {
		// TODO Auto-generated constructor stub
		super(x,y);
		super.setProdReady(false);
	}
	
	public boolean prodMilk() {return true;}
    public boolean prodMeat() {return true;}

    
    public String render() {
        if (isProdReady()) return("img/horse_red.png");
        else return("img/horse.png");}


    public String getSound(){
       return("horse.wav");
    }

}
