package cell;

public abstract class Land extends Cell {
	boolean berumput;
	
	public Land() {
		// TODO Auto-generated constructor stub
		super();
		berumput = false;
	}

	
	public void setBerumput(boolean _berumput){
        berumput = _berumput;
    }

    public boolean getBerumput() {return berumput;}
}
