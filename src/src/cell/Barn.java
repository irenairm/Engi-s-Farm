package cell;

public class Barn extends Land {

	public Barn() {
		// TODO Auto-generated constructor stub
		super();
		
	}
	
	public String render(){
        if (berumput) return("img/lahan_daging_rumput.png");
        else return("img/lahan_daging_no.png");
    }

}
