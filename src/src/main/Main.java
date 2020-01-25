package main;

import java.awt.event.KeyListener;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.border.LineBorder;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.Point;


import main.*;


public class Main extends JFrame implements KeyListener{

	private State permainan = new State();

	private JPanel panelStat;
	private JPanel panelMap;
	private JPanel panelInventory;
	private JTextField tf;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
					try {
						Main window = new Main();
						window.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}	
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	private class AreYouSure extends WindowAdapter {  
        public void windowClosing( WindowEvent e ) {  
        	int result = JOptionPane.showConfirmDialog(null,"Are you sure?","Exiting Engi's Rich Farm...",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        		       if(result == JOptionPane.YES_OPTION){
        		               System.exit(0);
        		       }else{
        		               //Do nothing
        		       }
        }  
		}  
		
		public void keyPressed(KeyEvent e) {} 

		public void keyReleased(KeyEvent e){
			int keyCode = e.getKeyCode();
			switch( keyCode ) { 
					case KeyEvent.VK_UP:
							permainan.playerMove("up");
							refreshFrame();
							break;
					case KeyEvent.VK_DOWN:
							permainan.playerMove("down");
							refreshFrame();
							break;
					case KeyEvent.VK_LEFT:
							permainan.playerMove("left");
							refreshFrame();
							break;
					case KeyEvent.VK_RIGHT :
							permainan.playerMove("right");
							refreshFrame();
							break;
					case KeyEvent.VK_SHIFT :
							tf.requestFocus();
							break;
			 }
		}

		public void keyTyped(KeyEvent e){}


	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		this.getContentPane().setForeground(new Color(0, 0, 0));
		this.setSize(730,504);
		this.setTitle("Engi's Rich Farm");
		this.setLocationRelativeTo(null); 
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new AreYouSure());  
		
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		
		addMouseListener(new MouseAdapter() {
            
			public void mouseClicked(MouseEvent e) {
					super.mouseReleased(e);
					requestFocus();
			}
		});
		
		setMenu();
		printCommand();
    Panel();    
	}

	/* SETTING MENUBAR */
	void setMenu(){
		JMenuBar menu = new JMenuBar();
		JMenu file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F); // Alt+F
		JMenu help = new JMenu("Help");
		help.setMnemonic(KeyEvent.VK_F); // Alt+F
		menu.add(file);
		menu.add(help);
		
		// Settingan legends
		ImageIcon legends_icon = new ImageIcon("img/legends.png");
		JMenuItem mntmLegends = new JMenuItem("Legends",legends_icon);
		mntmLegends.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				legends();
			}
		});
		help.add(mntmLegends);

		// Settingan recipe
		ImageIcon recipe_icon = new ImageIcon("img/recipe.png");
		ImageIcon recipes = new ImageIcon("img/recipes.png");
		JMenuItem mntmRecipe = new JMenuItem("Recipe",recipe_icon);
		mntmRecipe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, 
			" Beef Rolade : Telur Ayam + Daging Sapi  "    +
			"\n Omelette : Telur Bebek + Susu Sapi       " +      
			"\n Cheese : Susu Domba + Susu Domba ", "RECIPE", JOptionPane.INFORMATION_MESSAGE, recipes);
			}		
		});
		help.add(mntmRecipe);
		
		ImageIcon helpo = new ImageIcon("img/question.png");
		ImageIcon howto = new ImageIcon("img/howto.png");
		JMenuItem mntmHowToPlay = new JMenuItem("How to Play",howto);
		mntmHowToPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, 
			" TALK : Menampilkan suara hewan di samping Player  "    +
			"\n UP ARROW : Menggerakkan Player ke atas       " +      
			"\n DOWN ARROW : Menggerakkan Player ke bawah " +                    
			"\n LEFT ARROW : Menggerakkan Player ke kiri    " +                   
			"\n RIGHT ARROW : Menggerakkan Player ke kanan   " +                   
			"\n GROW : Menumbuhkan rumput pada posisi Player (pastikan wadah air terisi) " +                    
			"\n INTERACT : Berinteraksi dengan objek di samping player  " +
			"\n               > Pada Well mengisi wadah air (+5)      "         +
			"\n               > Pada hewan penghasil telur dan susu menambah inventory Player   " +                            
			"\n               > Pada Truck menjual seluruh isi inventory    " +  
			"\n KILL : Membunuh hewan di samping Player, menambah inventory untuk hewan penghasil daging  " +          
			"\n MIX : Mencampurkan bahan yang terdapat pada inventory  ", "HELP", JOptionPane.INFORMATION_MESSAGE, helpo);
				
			}
		});
		help.add(mntmHowToPlay);
		
		ImageIcon exit_icon = new ImageIcon("img/exit.png");
		JMenuItem menuItem = new JMenuItem("Exit",exit_icon);
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			int result = JOptionPane.showConfirmDialog(null,"Are you sure?","Exiting Engi's Rich Farm...",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
 		       if(result == JOptionPane.YES_OPTION){
 		               System.exit(0);
 		       }else{
 		               //Do nothing
 		       }
			}
		});
		menuItem.setToolTipText("Exit Engi's Rich Farm");
		
		ImageIcon disclaimer = new ImageIcon("img/disclaimer.png");
		JMenuItem menuItem2 = new JMenuItem("Disclaimer",disclaimer);
		menuItem2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, 
			"All icons used are from flaticon.com", "DISCLAIMER", JOptionPane.INFORMATION_MESSAGE, disclaimer);
				
			}
		});
		file.add(menuItem2);
		file.add(menuItem);
		this.getContentPane().add(BorderLayout.NORTH, menu);
	}


	void legends() {
		JPanel frame = new JPanel();
		frame.setLayout(new BoxLayout(frame,BoxLayout.PAGE_AXIS));
		
        ImageIcon ayamsiap = new ImageIcon("img/hen.png");
		JLabel ayam_red = new JLabel("Ayam siap produksi",ayamsiap,0);
		frame.add(ayam_red);
		
		ImageIcon ayam = new ImageIcon("img/chick.png");
		JLabel ayam_bayi = new JLabel("Ayam belum siap produksi",ayam,0);
		frame.add(ayam_bayi);
		
	ImageIcon sapisiap = new ImageIcon("img/cow_red.png");
	JLabel sapisi = new JLabel("Sapi siap produksi",sapisiap,0);
	frame.add(sapisi);
	ImageIcon sapi_bayi = new ImageIcon("img/cow.png");
	JLabel sapi = new JLabel("Sapi belum siap produksi",sapi_bayi,0);
	frame.add(sapi);
		
	ImageIcon kuda_siap = new ImageIcon("img/horse_red.png");
	JLabel kuda = new JLabel("Kuda siap produksi",kuda_siap,0);
	frame.add(kuda);
		
	ImageIcon kuda_bayi = new ImageIcon("img/horse.png");
				JLabel kuda_b = new JLabel("Kuda belum siap produksi",kuda_bayi,0);
				frame.add(kuda_b);
		
	ImageIcon bebeksiap = new ImageIcon("img/duck_red.png");
		JLabel bebek = new JLabel("Bebek siap produksi",bebeksiap,0);
		frame.add(bebek);
		
	ImageIcon bebek_bayi = new ImageIcon("img/duckling.png");
				JLabel bayibebe = new JLabel("Bebek belum siap produksi",bebek_bayi,0);
				frame.add(bayibebe);
	
	ImageIcon dombasiap = new ImageIcon("img/sheep_red.png");
				JLabel domba_red = new JLabel("Domba siap produksi",dombasiap,0);
				frame.add(domba_red);
		
	ImageIcon domba_bayi = new ImageIcon("img/sheep.png");
				JLabel domba = new JLabel("Domba belum siap produksi",domba_bayi,0);
				frame.add(domba);
	
	ImageIcon angsasiap = new ImageIcon("img/swan_red.png");
				JLabel angsa = new JLabel("Angsa siap produksi",angsasiap,0);
				frame.add(angsa);
		
	ImageIcon angsa_bayi = new ImageIcon("img/swan.png");
				JLabel angsa_blm = new JLabel("Angsa belum siap produksi",angsa_bayi,0);
				frame.add(angsa_blm);
	
	ImageIcon mixer = new ImageIcon("img/mixer.png");
				JLabel adukan = new JLabel("Mixer",mixer,0);
				frame.add(adukan);
		
	ImageIcon truck = new ImageIcon("img/truck.png");
				JLabel trucks = new JLabel("Truck",truck,0);
				frame.add(trucks);
		
	ImageIcon well = new ImageIcon("img/well.png");
				JLabel sumur = new JLabel("Well (sumur air)",well,0);
				frame.add(sumur);
	
	ImageIcon daging_no = new ImageIcon("img/lahan_daging_no.png");
				JLabel daging_noo= new JLabel("Lahan hewan penghasil daging (tidak berumput)",daging_no,0);
				frame.add(daging_noo);
		
	ImageIcon daging_rump = new ImageIcon("img/lahan_daging_rumput.png");
				JLabel daging = new JLabel("Lahan hewan penghasil daging (berumput)",daging_rump,0);
				frame.add(daging);
	
	ImageIcon egg_no = new ImageIcon("img/lahan_egg_no.png");
				JLabel egg_noo = new JLabel("Lahan hewan penghasil telur (tidak berumput) ",egg_no,0);
				frame.add(egg_noo);
		
	ImageIcon egg_rump = new ImageIcon("img/lahan_egg_rumput.png");
				JLabel egg= new JLabel("Lahan hewan penghasil telur (berumput) ",egg_rump,0);
				frame.add(egg);
	
	ImageIcon susu_no = new ImageIcon("img/lahan_susu_no.png");
				JLabel susu_noo = new JLabel("Lahan hewan penghasil susu (tidak berumput)",susu_no,0);
				frame.add(susu_noo);
		
	ImageIcon susu_rump = new ImageIcon("img/lahan_susu_rump.png");
				JLabel susu = new JLabel("Lahan hewan penghasil susu (berumput)",susu_rump,0);
				frame.add(susu);
		
	ImageIcon player = new ImageIcon("img/player.png");
				JLabel pemain = new JLabel("Pemain",player,0);
				frame.add(pemain);
		
		
        JOptionPane.showMessageDialog(null, frame, "LEGENDS", JOptionPane.INFORMATION_MESSAGE);
		}



	void printCommand(){
		// Create panel for command
		JPanel panelCommand = new JPanel(new GridLayout(1,11));
		//JPanel[] gridCommand = new JPanel[10];
		panelCommand.setBorder(null);

		JButton talk = new JButton("TALK");
		talk.setFocusable(false);
		talk.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				permainan.bicara();
				refreshFrame();
			}
		});

		JButton kill = new JButton("KILL");
		kill.setFocusable(false);
		kill.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				permainan.bunuh();
				refreshFrame();
			}
		});

		JButton interact = new JButton("INTERACT");
		interact.setFocusable(false);
		interact.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				permainan.berinteraksi();
				refreshFrame();
			}
		});		

		JButton grow = new JButton("GROW");
		grow.setFocusable(false);
		grow.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				permainan.tumbuh();
				refreshFrame();
			}
		});

		tf = new JTextField(10); // maksimal karakter 10
		

		JButton mix = new JButton("MIX");
		mix.setFocusable(false);
		mix.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String[] numbInv = tf.getText().split(" ");
				try{
					permainan.campur(Integer.parseInt(numbInv[0])-1, Integer.parseInt(numbInv[1])-1);
					refreshFrame();
				}catch (NumberFormatException nfe){
					Except exc = new Except("Isi bahan mix dengan nomor inventory dipisahkan spasi!");
					exc.Warning(exc.getMessage());
				}
					
			}
		});

				panelCommand.add(talk);
				panelCommand.add(kill);
				panelCommand.add(interact);
				panelCommand.add(grow);
				panelCommand.add(tf);
					tf.setText("bahan mix");
				panelCommand.add(mix);
				this.getContentPane().add(BorderLayout.SOUTH, panelCommand);

	}

	void refreshFrame(){
				if (Integer.parseInt(permainan.getTick()) > 0) permainan.animalsHunger();
				this.getContentPane().remove(panelMap);
				this.getContentPane().remove(panelStat);
				this.getContentPane().remove(panelInventory);
				printMap();
				printStat();
				printInv();
						
				this.invalidate();
				this.validate();

				if (permainan.getLivingPos().isEmpty()){
					int result = JOptionPane.showConfirmDialog(null,"Do you want to restart?","GAME OVER",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        		       if(result == JOptionPane.YES_OPTION){
										permainan = new State();
										refreshFrame();
        		       }else{
										ImageIcon bye = new ImageIcon("img/over.png");
										 JOptionPane.showMessageDialog(null, "Good bye! Hope you enjoyed the game!", "GAME (REALLY) OVER", JOptionPane.INFORMATION_MESSAGE, bye);
										System.exit(0);
        		       }
				}
	}

	public void Panel() {
       printStat();
			 printMap();
			 printInv();
	}

	void printStat(){
		panelStat = new JPanel();
        this.getContentPane().add(panelStat, BorderLayout.WEST);
        panelStat.setLayout(new GridLayout(3,2,10,4));
        
        JLabel lblNewLabel = new JLabel("Waktu");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setAlignmentX(0);
        lblNewLabel.setAlignmentY(1);
        panelStat.add(lblNewLabel);
        
        JLabel waktu = new JLabel();
        waktu.setHorizontalAlignment(SwingConstants.CENTER);
        waktu.setText(permainan.getTick());
        panelStat.add(waktu);
        
        JLabel lblNewLabel_1 = new JLabel("Uang");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_1.setAlignmentX(1);
        lblNewLabel_1.setAlignmentX(2);
        panelStat.add(lblNewLabel_1);
        
        JLabel uang = new JLabel();
        uang.setHorizontalAlignment(SwingConstants.CENTER);
        uang.setText(permainan.getUang());
        panelStat.add(uang);
        
        JLabel lblNewLabel_2 = new JLabel("Air");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setAlignmentX(Component.RIGHT_ALIGNMENT);
        lblNewLabel_2.setAlignmentY(2);
        panelStat.add(lblNewLabel_2);
        
        JLabel air = new JLabel();
        air.setHorizontalAlignment(SwingConstants.CENTER);
        air.setText(permainan.getAir());
        
        panelStat.add(air);
	}

	void printInv(){
			panelInventory =  new JPanel();
			panelInventory.setLayout(new BoxLayout(panelInventory, BoxLayout.Y_AXIS));
			panelInventory.setPreferredSize(new Dimension(150, 750));
      
       
      JLabel lblNewLabel_3 = new JLabel("                 Inventory");
      lblNewLabel_3.setHorizontalAlignment(JLabel.CENTER);
      lblNewLabel_3.setVerticalTextPosition(JLabel.TOP);
			panelInventory.add(lblNewLabel_3); 

		  int size = permainan.getPlayer().getInventory().size();
		  String[] namaInv = new String[size+1];
		  if (size > 0){
		   for (int i=0; i<size; i++){
				String noInv = Integer.toString(i+1);
		    namaInv[i] = noInv + ". " + permainan.getPlayer().getInventory().get(i).getNama();
		   }
		  }else{
		   namaInv[0] = "Kosong";
		  }
			JList list = new JList(namaInv);
			list.setFocusable(false);
			list.setAlignmentX(0);
			panelInventory.add(list);
			
			
			this.getContentPane().add(panelInventory, BorderLayout.EAST);
		 }

	void printMap(){
		panelMap = new JPanel(new GridLayout(10,15));
       panelMap.setBorder(new LineBorder(new Color(0, 0, 0), 4));
       panelMap.setBackground(Color.LIGHT_GRAY);
			 panelMap.setLayout(new GridLayout(10, 15,4,4));
			 
			 
			 int i = 10;
			 int j = 15;
      
			JPanel[][] panelaja = new JPanel[i][j];
			for(int a=0; a<i; a++){
			  for(int b=0; b<j; b++){
				ImageIcon img;
				Point pos = new Point(a,b);
				if (permainan.getPlayer().getPosition().getX() == a && permainan.getPlayer().getPosition().getY() == b) img = new ImageIcon(permainan.getPlayer().render());
				else if(permainan.getLivingPos().containsKey(pos)) img = new ImageIcon(permainan.getLivingPos().get(pos).render());
				else img = new ImageIcon(permainan.getCell().get(a).get(b).render());

				JLabel picture = new JLabel(img);
				panelaja[a][b] = new JPanel();
				panelaja[a][b].add(picture);
				panelaja[a][b].setBackground(Color.GREEN);
				panelaja[a][b].setBorder(new LineBorder(new Color(0, 0, 0), 0));
				panelMap.add(panelaja[a][b]);
			  }
			}
       this.getContentPane().add(panelMap, BorderLayout.CENTER);
	}
}
