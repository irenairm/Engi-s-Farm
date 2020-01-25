package livingcreature;

public class Sapi extends FarmAnimal implements MilkProdAnimal, MeatProdAnimal {

	public Sapi(int x, int y) {
		// TODO Auto-generated constructor stub
		super(x, y);
		super.setProdReady(false);
	}
	
	public boolean prodMilk() {return true;}
    public boolean prodMeat() {return true;}

    
    public String render() {
        if (isProdReady()) return("img/cow_red.png");
        else return("img/cow.png");}


    public String getSound(){
        return("cow.wav");
    }

}
