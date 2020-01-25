package main;

import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.*;

import cell.*;
import livingcreature.*;
import product.*;

public class State{
    ArrayList<ArrayList<Cell>> cell;
    HashMap<Point, FarmAnimal> living_pos;
    Player pemain;
    int tick;
    long money;

    public State(){
        bacaMap();
        bacaPos();
        tick = 0;
        money = 0;
    }

    void bacaMap(){
        File file_map = new File("map.txt");
        try{
            Scanner fmap = new Scanner(file_map);
            char curr_cell;
            String line;
            cell = new ArrayList<ArrayList<Cell>>();
            for (int i=0; i< 10; i++){
                ArrayList<Cell> cell_line = new ArrayList<Cell>();
                line = fmap.nextLine();
                for (int j=0; j<15; j++){
                    curr_cell = line.charAt(j);
                    if (curr_cell == '@' || curr_cell == 'x'){
                        Barn barn = new Barn();
                        if (curr_cell == '@') barn.setBerumput(true);
                        cell_line.add(barn);
                    }
                    else if (curr_cell == '#' || curr_cell == '-'){
                        GrassLand grassLand = new GrassLand();
                        if (curr_cell == '#') grassLand.setBerumput(true);
                        cell_line.add(grassLand);
                    }
                    else if (curr_cell == '*' || curr_cell == 'o'){
                        Coop coop = new Coop();
                        if (curr_cell == '*') coop.setBerumput(true);
                        cell_line.add(coop);
                    }
                    else if (curr_cell == 'T'){
                        Truck truck = new Truck();
                        cell_line.add(truck);
                    }
                    else if (curr_cell == 'W'){
                        Well well= new Well();
                        cell_line.add(well);
                    }
                    else if (curr_cell == 'M'){
                        Mixer mixer = new Mixer();
                        cell_line.add(mixer);
                    }
                }
                cell.add(cell_line);
            }
            fmap.close();
        } catch (IOException e) {e.printStackTrace();}
    }

    void bacaPos(){
        File file_map = new File("livingcreature_pos.txt");
        try{
            Scanner fmap = new Scanner(file_map);
            //String line;
            living_pos = new HashMap<Point, FarmAnimal>();
            while(fmap.hasNextLine()){
                int x = fmap.nextInt();
                int y = fmap.nextInt();
                char type = fmap.nextLine().charAt(1);
                if (type != '.'){
                    if (type == 'P'){
                        pemain = new Player(x,y);
                        //living_pos.put(pemain.getPosition(), pemain);
                    }
                    else if (type == 'R' || type == 'r'){
                        Ayam ayam = new Ayam(x, y);
                        if (type == 'R') ayam.setProdReady(true);
                        living_pos.put(ayam.getPosition(), ayam);
                    }
                    else if (type == 'D' || type == 'd'){
                        Bebek bebek = new Bebek(x, y);
                        if (type == 'D') bebek.setProdReady(true);
                        living_pos.put(bebek.getPosition(), bebek);
                    }
                    else if (type == 'G' || type == 'g'){
                        Angsa angsa = new Angsa(x, y);
                        if (type == 'G') angsa.setProdReady(true);
                        living_pos.put(angsa.getPosition(), angsa);
                    }
                    else if (type == 'H' || type == 'h'){
                        Kuda kuda = new Kuda(x, y);
                        if (type == 'H') kuda.setProdReady(true);
                        living_pos.put(kuda.getPosition(), kuda);
                    }
                    else if (type == 'C' || type == 'c'){
                        Sapi sapi = new Sapi(x, y);
                        if (type == 'C') sapi.setProdReady(true);
                        living_pos.put(sapi.getPosition(), sapi);
                    }
                    else if (type == 'S' || type == 's'){
                        Domba domba = new Domba(x, y);
                        if (type == 'S') domba.setProdReady(true);
                        living_pos.put(domba.getPosition(), domba);
                    }
                    cell.get(x).get(y).setContent(true);
                    //if (cell.get(x).get(y).getContent()) System.out.println("Content set");
                }
                
            }
            fmap.close();
        } catch (IOException e) {e.printStackTrace();}
    }

    public void tambahWaktu(){
        tick++;
    }

    public HashMap<Point, FarmAnimal> getLivingPos(){
        return living_pos;
    }

    public void bicara(){
        Point pos;
        boolean found = false;
        int x = (int) pemain.getPosition().getX();
        int y = (int) pemain.getPosition().getY();
        if (x > 0 && cell.get(x-1).get(y).getContent()){
            pos = new Point(x-1, y);
            pemain.Talk(living_pos.get(pos));
            found = true;
        }
        if (x < 9 && cell.get(x+1).get(y).getContent()){
            pos = new Point(x+1, y);
            pemain.Talk(living_pos.get(pos));
            found = true;
        }
        if (y > 0 && cell.get(x).get(y-1).getContent()){
            pos = new Point(x, y-1);
            pemain.Talk(living_pos.get(pos));
            found = true;
        }
        if (y < 14 && cell.get(x).get(y+1).getContent()){
            pos = new Point(x, y+1);
            pemain.Talk(living_pos.get(pos));
            found = true;
        }
        if (!found) {
			Except e = new Except("Tidak ada hewan di sekitarmu!");
			e.Warning(e.getMessage());
		}//System.out.println("Tidak ada hewan di sekitarmu!");
        else {
            tambahWaktu();
            animalsMove();
        }
    }

    public void playerMove(String direction){
        int x = (int) pemain.getPosition().getX();
        int y = (int) pemain.getPosition().getY();
        if (direction.equals("up") && x > 0 && !cell.get(x-1).get(y).getContent()){
            pemain.Move(direction);
            cell.get(x).get(y).setContent(false);
            cell.get(x-1).get(y).setContent(true);
            tambahWaktu();
            animalsMove();
        }
        else if (direction.equals("down") && x < 9 && !cell.get(x+1).get(y).getContent()){
            pemain.Move(direction);
            cell.get(x).get(y).setContent(false);
            cell.get(x+1).get(y).setContent(true);
            tambahWaktu();
            animalsMove();
        }
        else if (direction.equals("left") && y > 0 && !cell.get(x).get(y-1).getContent()){
            pemain.Move(direction);
            cell.get(x).get(y).setContent(false);
            cell.get(x).get(y-1).setContent(true);
            tambahWaktu();
            animalsMove();
        }
        else if (direction.equals("right") && y < 14 && !cell.get(x).get(y+1).getContent()){
            pemain.Move(direction);
            cell.get(x).get(y).setContent(false);
            cell.get(x).get(y+1).setContent(true);
            tambahWaktu();
            animalsMove();
        }
        else {
			
			Except e = new Except("Tidak dapat bergerak! Cari jalan lain!");
			e.Warning(e.getMessage());
		}
    }

    public void animalsMove(){
        HashMap<Point, FarmAnimal> temp = new HashMap<Point, FarmAnimal>(living_pos);
        for (Map.Entry<Point, FarmAnimal> entry : temp.entrySet()){
            int x = (int) entry.getKey().getX();
            int y = (int) entry.getKey().getY();
            
            boolean prod = entry.getValue().isProdReady();

            if (entry.getValue() instanceof Ayam){
                Ayam animal = new Ayam(x,y);
                animal.setProdReady(prod);
                animalMove(animal);
            }
            else if (entry.getValue() instanceof Angsa){
                Angsa animal = new Angsa(x,y);
                animal.setProdReady(prod);
                animalMove(animal);
            }
            else if (entry.getValue() instanceof Bebek){
                Bebek animal = new Bebek(x,y);
                animal.setProdReady(prod);
                animalMove(animal);
            }
            else if (entry.getValue() instanceof Domba){
                Domba animal = new Domba(x,y);
                animal.setProdReady(prod);
                animalMove(animal);
            }
            else if (entry.getValue() instanceof Kuda){
                Kuda animal = new Kuda(x,y);
                animal.setProdReady(prod);
                animalMove(animal);
            }
            else if (entry.getValue() instanceof Sapi){
                Sapi animal = new Sapi(x,y);
                animal.setProdReady(prod);
                animalMove(animal);
            }
        }
        temp.clear();
    }

    public void animalMove(FarmAnimal animal){
        Random rand = new Random();
        int n = rand.nextInt(4);

        boolean moved = false;

        int x = (int) animal.getPosition().getX();
        int y = (int) animal.getPosition().getY();
        Point init_pos = new Point(x,y);

        if ( n == 0 && x > 0 && !cell.get(x-1).get(y).getContent()){
            if (cell.get(x-1).get(y) instanceof Barn){
                if (animal.prodMeat()) moved = true;
            }
            else if (cell.get(x-1).get(y) instanceof Coop){
                if (animal.prodEgg()) moved = true;
            }
            else if (cell.get(x-1).get(y) instanceof GrassLand){
                if (animal.prodMilk()) moved = true;
            }
            if (moved) animal.Move("up");
            
        }
        else if (n == 1 && x < 9 && !cell.get(x+1).get(y).getContent()){
            if (cell.get(x+1).get(y) instanceof Barn){
                if (animal.prodMeat()) moved = true;
            }
            else if (cell.get(x+1).get(y) instanceof Coop){
                if (animal.prodEgg()) moved = true;
            }
            else if (cell.get(x+1).get(y) instanceof GrassLand){
                if (animal.prodMilk()) moved = true;
            }
            if (moved) animal.Move("down");
            //new_pos.setLocation(x+1, y);
        }
        else if (n == 2 && y > 0 && !cell.get(x).get(y-1).getContent()){
            if (cell.get(x).get(y-1) instanceof Barn){
                if (animal.prodMeat()) moved = true;
            }
            else if (cell.get(x).get(y-1) instanceof Coop){
                if (animal.prodEgg()) moved = true;
            }
            else if (cell.get(x).get(y-1) instanceof GrassLand){
                if (animal.prodMilk()) moved = true;
            }
            if (moved) animal.Move("left");
            //new_pos.setLocation(x, y-1);
        }
        else if (n == 3 && y < 14 && !cell.get(x).get(y+1).getContent()){
            if (cell.get(x).get(y+1) instanceof Barn){
                if (animal.prodMeat()) moved = true;
            }
            else if (cell.get(x).get(y+1) instanceof Coop){
                if (animal.prodEgg()) moved = true;
            }
            else if (cell.get(x).get(y+1) instanceof GrassLand){
                if (animal.prodMilk()) moved = true;
            }
            if (moved) animal.Move("right");
            //new_pos.setLocation(x, y+1);
        }

        if (moved) {
            cell.get(x).get(y).setContent(false);
            cell.get((int) animal.getPosition().getX()).get((int)animal.getPosition().getY()).setContent(true);
            //temp.remove(init_pos);
            living_pos.remove(init_pos);
            living_pos.put(animal.getPosition(), animal);
        }

    }

    public void tumbuh(){
        int x = (int) pemain.getPosition().getX();
        int y = (int) pemain.getPosition().getY();
        if (cell.get(x).get(y).getBerumput()) {
			//System.out.println("Di tempatmu berdiri, telah tumbuh rumput. Tidak perlu disiram!");
			Except e = new Except("Di tempatmu berdiri, telah tumbuh rumput. Tidak perlu disiram!");
			e.Warning(e.getMessage());
		}
        else if (pemain.Grow()){
            cell.get(x).get(y).setBerumput(true);
            tambahWaktu();
            animalsMove();
        }
    }

    public void berinteraksi(){
        int x = (int) pemain.getPosition().getX();
        int y = (int) pemain.getPosition().getY();
        Point pos = new Point();
        boolean found = false;
        Object o = new Object();
        //String type = "nothing";
        //String objType = "nothing";
        if (x > 0 && cell.get(x-1).get(y).getContent()){
            //type = cell.get(x-1).get(y).getType();
            //objType = cell.get(x-1).get(y).getObjType();
            found = true;
            o = cell.get(x-1).get(y);
            pos.setLocation(x-1, y);
            
        }else if (x < 9 && cell.get(x+1).get(y).getContent()){
            //type = cell.get(x+1).get(y).getType();
            //objType = cell.get(x+1).get(y).getObjType();
            found = true;
            o = cell.get(x+1).get(y);
            pos.setLocation(x+1, y);
        }else if (y > 0 && cell.get(x).get(y-1).getContent()){
            //type = cell.get(x).get(y-1).getType();
            //objType = cell.get(x).get(y-1).getObjType();
            found = true;
            o = cell.get(x).get(y-1);
            pos.setLocation(x, y-1);
        }else if (y < 14 && cell.get(x).get(y+1).getContent()){
            //type = cell.get(x).get(y+1).getType();
            //objType = cell.get(x).get(y+1).getObjType();
            found = true;
            o = cell.get(x).get(y+1);
            pos.setLocation(x, y+1);
        }
        //System.out.println(type);
        //System.out.println(objType);
        if (found) {
            //System.out.println("masuksini");
            if (!(o instanceof Facility)){
                //type = living_pos.get(pos).getType();
                //System.out.println(type);
                if (living_pos.get(pos).isProdReady()) {
                    pemain.interact(living_pos.get(pos));
                    tambahWaktu();
                    //System.out.println("masuksini");        
                    }
                else  {
					String str = o.getClass().toString();
					String prin = str + " belum dapat menghasilkan produk!";
					Except e = new Except(prin);
					e.Warning(e.getMessage());
					//System.out.println(o.getClass() + " belum dapat menghasilkan produk!");
				}
                //System.out.println("masuksini");
                living_pos.get(pos).setProdReady(false);
                //System.out.println("masuksini");
            }
            else if (o instanceof Truck){
                if (pemain.getInventory().size() > 1) pemain.interact(o);
                if (!pemain.getInventory().isEmpty()) {
                    money += pemain.getInventory().removeFirst().getHarga();
                    tambahWaktu();}
                else {
					Except e = new Except("Inventory masih kosong!");
					e.Warning(e.getMessage());
					//System.out.println("Inventory masih kosong!");
				}
            } 
            else pemain.interact(o);
            animalsMove();
        }
    }

    public void bunuh(){
        int x = (int) pemain.getPosition().getX();
        int y = (int) pemain.getPosition().getY();
        Point pos = new Point(-1, -1);
        if (x > 0 && cell.get(x-1).get(y).getContent() && !(cell.get(x-1).get(y) instanceof Facility)){
            pos.setLocation(x-1, y);
        }else if (x < 9 && cell.get(x+1).get(y).getContent() && !(cell.get(x+1).get(y) instanceof Facility)){
            pos.setLocation(x+1, y);
        }else if (y > 0 && cell.get(x).get(y-1).getContent() && !(cell.get(x).get(y-1) instanceof Facility)){
            pos.setLocation(x, y-1);
        }else if (y < 14 && cell.get(x).get(y+1).getContent() && !(cell.get(x).get(y+1) instanceof Facility)){
            pos.setLocation(x, y+1);
        }

        if (pos.getX() != -1 && pos.getY() != -1){
            //type = living_pos.get(pos).getType();
            pemain.Kill(living_pos.get(pos));
            living_pos.remove(pos);
            cell.get((int)pos.getX()).get((int)pos.getY()).setContent(false);   
            tambahWaktu();
            animalsMove();
        }
    }

    public void campur(int a, int b){
        LinkedList<Product> temp = new LinkedList<Product>(pemain.getInventory());
        int x = (int) pemain.getPosition().getX();
        int y = (int) pemain.getPosition().getY();
        if (cell.get(x-1).get(y) instanceof Mixer || cell.get(x).get(y+1) instanceof Mixer){
            Mixer mixer = new Mixer();
            if (a < temp.size() && b < temp.size()){
                if(!mixer.Mix(temp.get(a), temp.get(b)).getNama().equals("gagal")){
                    pemain.getInventory().addLast(mixer.Mix(temp.get(a), temp.get(b)));
                    pemain.getInventory().remove(temp.get(a));
                    pemain.getInventory().remove(temp.get(b));
                    tambahWaktu();
                    animalsMove();
                }
            }
        }
    }

    public void animalsHunger(){
        HashMap<Point, FarmAnimal> temp = new HashMap<Point, FarmAnimal>(living_pos);
        for (Map.Entry<Point, FarmAnimal> entry : temp.entrySet()){
            int x = (int) entry.getKey().getX();
            int y = (int) entry.getKey().getY();
            if (entry.getValue() instanceof Ayam){
                if(tick % 10 == 0){
                    if (!cell.get(x).get(y).getBerumput()){
                        animalHunger(entry.getValue());
                    }
                    else{
                        cell.get(x).get(y).setBerumput(false);
                        living_pos.get(entry.getKey()).setProdReady(true);
                    }
                }
            }
            else if (entry.getValue() instanceof Angsa){
                if(tick % 8 == 0){
                    if (!cell.get(x).get(y).getBerumput()){
                        animalHunger(entry.getValue());
                    }
                    else{
                        cell.get(x).get(y).setBerumput(false);
                        living_pos.get(entry.getKey()).setProdReady(true);
                    }
                }
            }
            else if (entry.getValue() instanceof Bebek){
                if(tick % 11 == 0){
                    if (!cell.get(x).get(y).getBerumput()){
                        animalHunger(entry.getValue());
                    }
                    else{
                        cell.get(x).get(y).setBerumput(false);
                        living_pos.get(entry.getKey()).setProdReady(true);
                    }
                }
            }
            else if (entry.getValue() instanceof Domba){
                if(tick % 13 == 0){
                    if (!cell.get(x).get(y).getBerumput()){
                        animalHunger(entry.getValue());
                    }
                    else{
                        cell.get(x).get(y).setBerumput(false);
                        living_pos.get(entry.getKey()).setProdReady(true);
                    }
                }
            }
            else if (entry.getValue() instanceof Kuda){
                if(tick % 18 == 0){
                    if (!cell.get(x).get(y).getBerumput()){
                        animalHunger(entry.getValue());
                    }
                    else{
                        cell.get(x).get(y).setBerumput(false);
                        living_pos.get(entry.getKey()).setProdReady(true);
                    }
                }
            }
            else if (entry.getValue() instanceof Sapi){
                if(tick % 16 == 0){
                    if (!cell.get(x).get(y).getBerumput()){
                        animalHunger(entry.getValue());
                    }
                    else{
                        cell.get(x).get(y).setBerumput(false);
                        living_pos.get(entry.getKey()).setProdReady(true);
                    }
                }
            }

        }
    }

    void animalHunger(FarmAnimal animal){
        living_pos.remove(animal.getPosition());
        cell.get((int) animal.getPosition().getX()).get((int) animal.getPosition().getY()).setContent(false);
    }

	public String  getTick(){
		return Integer.toString(tick);
	}
	
	public String  getAir(){
		int air = pemain.getWadahAir();
		return Integer.toString(air);
	}
	
	public String getUang(){
		return Long.toString(money);
	}
	public Player getPlayer(){
        return pemain;
    }
    
    public ArrayList<ArrayList<Cell>> getCell(){
		return cell;
	}
}

