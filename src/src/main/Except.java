package main;

import java.io.*;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;


public class Except extends Exception{

	public Except(String exc){
		super(exc);
		// TODO Auto-generated constructor stub
	}
	public String getMessage(){
		return super.getMessage();
	}
	
	public Except(){
	}
	public void Warning(String a) {
			File sound = new File("sound/warning.wav");
               try{ 
				Clip clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(sound));
				clip.start();
			JOptionPane.showMessageDialog(null,a,"ERROR!",JOptionPane.ERROR_MESSAGE);
			Thread.sleep(clip.getMicrosecondLength()/1000);
			 }
				catch (Exception e){
					}
	}
}
