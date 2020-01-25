package livingcreature;

public abstract class FarmAnimal extends LivingCreature{
	boolean prodReady;
	
	public FarmAnimal(int x, int y) {
		// TODO Auto-generated constructor stub
		super(x,y);
	}
	
	public boolean isProdReady(){
        return prodReady;
    }
    public void setProdReady(boolean _prodReady){
        prodReady = _prodReady;
    }

    public String getSound() {return "";}
    
    public boolean prodMilk() {return false;}
    public boolean prodMeat() {return false;}
    public boolean prodEgg() {return false;}

}
