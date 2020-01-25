package livingcreature;

public class Domba extends FarmAnimal implements MilkProdAnimal, MeatProdAnimal{

	public Domba(int x, int y) {
		// TODO Auto-generated constructor stub
		super(x,y);
        super.setProdReady(false);
    }

    public boolean prodMilk() {return true;}
    public boolean prodMeat() {return true;}

    
    public String render() {
        if (isProdReady()) return("img/sheep_red.png");
        else return("img/sheep.png");}


    public String getSound(){
       return("sheep.wav");
    }

}
