package cell;
import product.*;

public class Mixer extends Facility {

	public Mixer() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public String render(){
        return("img/mixer.png");
    }

    public SideProduct Mix(Product p1, Product p2){
        SideProduct produk;
        if ((p1.getNama().equals("Telur Ayam") && p2.getNama().equals("Daging Sapi")) || (p2.getNama().equals("Telur Ayam") && p1.getNama().equals("Daging Sapi"))){
            produk = new BeefRolade();
            return produk;   
        }
        else if ((p1.getNama().equals("Telur Bebek") && p2.getNama().equals("Susu Sapi")) || (p2.getNama().equals("Telur Bebek") && p1.getNama().equals("Susu Sapi"))){
            produk = new Omelette();
            return produk;
        }
        else if (p1.getNama().equals("Susu Domba") && p2.getNama().equals("Susu Domba")){
            produk = new Cheese();
            return produk;
        }
        else {
            produk = new SideProduct("gagal", 0);
            return produk;
        }
        
    }

}
