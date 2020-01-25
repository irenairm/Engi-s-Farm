package livingcreature;

import java.util.LinkedList;
import product.*;
import cell.Well;
import cell.Truck;
import cell.Mixer;
import main.Except;

import java.io.*;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Player extends LivingCreature{
	int wadahAir;
    LinkedList<Product> inventory;

	public Player(int x, int y) {
		// TODO Auto-generated constructor stub
		super(x,y);
		wadahAir = 15;
        inventory = new LinkedList<Product>();
        inventory.clear();
	}

	public String render(){
        return("img/player.png");
	}
	
	public LinkedList<Product> getInventory(){
        return inventory;
    }

    public void Talk(FarmAnimal animal){
		File sound = new File("sound/" + animal.getSound());
		try{ 
				Clip clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(sound));
				clip.start();
				
				Thread.sleep(clip.getMicrosecondLength()/1000);
				 }
				catch (Exception e){}
    }

    public int getWadahAir(){
        return wadahAir;
    }

    public boolean Grow(){
		   if (wadahAir>0){
            wadahAir--;
			}
		   else{
			Except e = new Except("Wadah airmu telah kosong! Isi ulang!");
			e.Warning(e.getMessage());
		} 
       return wadahAir>0;
		
        
    }

    public void interact(Object o){
        if (o instanceof Well) {
            wadahAir += 5;
            if (wadahAir > 15) wadahAir = 15;
        }
        else if (o instanceof Truck) {
            if (inventory.size() > 1) {
                Product total = new Product("Produk Total", 0);
                while (!inventory.isEmpty()){
                    total.addHarga(inventory.removeLast().getHarga());
                }
                inventory.add(total);
            }
            else if (!inventory.isEmpty()) {
				inventory.clear();
				Except e = new Except("Inventory telah dikosongkan!");
				e.Warning(e.getMessage());
				
			}
        }
        else if (o instanceof Mixer) {
			Except e = new Except("Gunakan command MIX untuk mencampur bahan makanan");
				e.Warning(e.getMessage());
            
        }
        else if (o instanceof Ayam) {
            TelurAyam telurAyam = new TelurAyam();
            inventory.add(telurAyam);
        }
        else if (o instanceof Bebek) {
            TelurBebek telurBebek = new TelurBebek();
            inventory.add(telurBebek);
        }
        else if (o instanceof Angsa) {
            TelurAngsa telurAngsa = new TelurAngsa();
            inventory.add(telurAngsa);
        }
        else if (o instanceof Domba) {
            SusuDomba susuDomba = new SusuDomba();
            inventory.add(susuDomba);
        }
        else if (o instanceof Kuda) {
            SusuKuda susuKuda = new SusuKuda();
            inventory.add(susuKuda);
        }
        else if (o instanceof Sapi) {
            SusuSapi susuSapi = new SusuSapi();
            inventory.add(susuSapi);
        }        

    }

    public void Kill(Object o){
        if (o instanceof Ayam){
            DagingAyam d = new DagingAyam();
            inventory.add(d);
        }
        else if (o instanceof Angsa){
            DagingAngsa d = new DagingAngsa();
            inventory.add(d);
        }
        else if (o instanceof Bebek){
            DagingBebek d = new DagingBebek();
            inventory.add(d);
        }
        else if (o instanceof Domba){
            DagingDomba d = new DagingDomba();
            inventory.add(d);
        }
        else if (o instanceof Kuda){
            DagingKuda d = new DagingKuda();
            inventory.add(d);
        }
        else if (o instanceof Sapi){
            DagingSapi d = new DagingSapi();
            inventory.add(d);
        }
    }
}
