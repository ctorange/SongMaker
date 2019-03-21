//Christopher Butler
//This program makes a simple, semi-random song based on a chord progression and key, using the ChordProgression class.

import java.io.*;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import javax.sound.sampled.AudioFormat; //audio stuff...
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.LineUnavailableException;

public class SongMaker {
public static String key;
	public static void main(String[] args) {						
		
		boolean exitStatus;
		Random random = new Random(); //used for generating notes
		
		String measureOne[] = null;
		String measureTwo[] = null;
		String measureThree[] = null;
		String measureFour[] = null;
		
		Scanner scan = new Scanner (System.in);
		int progType[] = new int[4]; //chord progressions can only have 4 chords for now.
		
		do {
		System.out.print("Enter key of song (major only): ");
		key = scan.next();
		System.out.println("\nEnter chord progression as a list of integers. For instance, '1 5 6 4'");
		progType[0] = scan.nextInt();
		progType[1] = scan.nextInt();
		progType[2] = scan.nextInt();
		progType[3] = scan.nextInt();
		
		ChordProgression prog = new ChordProgression (progType, key); //new chord progression called "prog"
		
		System.out.println("\nChord Progression: "+Arrays.toString(progType));
		
		System.out.println("Notes available for first measure: "+Arrays.toString(prog.getMeasure(1)));
		System.out.println("Notes available for second measure: "+Arrays.toString(prog.getMeasure(2)));
		System.out.println("Notes available for third measure: "+Arrays.toString(prog.getMeasure(3)));
		System.out.println("Notes available for fourth measure: "+Arrays.toString(prog.getMeasure(4)));
		
		int M1note1 = random.nextInt(prog.getMeasure(1).length); //makes random notes from available notes
		int M1note2 = random.nextInt(prog.getMeasure(2).length);
		int M1note3 = random.nextInt(prog.getMeasure(3).length);
		int M1note4 = random.nextInt(prog.getMeasure(4).length);
		
		//currently each measure uses the same "pattern"; would have to make 16 random notes to make the whole song random
		measureOne = new String[] {prog.getMeasure(1)[M1note1],prog.getMeasure(1)[M1note2],prog.getMeasure(1)[M1note3],prog.getMeasure(1)[M1note4]};
		measureTwo = new String[] {prog.getMeasure(2)[M1note1],prog.getMeasure(2)[M1note2],prog.getMeasure(2)[M1note3],prog.getMeasure(2)[M1note4]};
		measureThree = new String[] {prog.getMeasure(3)[M1note1],prog.getMeasure(3)[M1note2],prog.getMeasure(3)[M1note3],prog.getMeasure(3)[M1note4]};
		measureFour = new String[] {prog.getMeasure(4)[M1note1],prog.getMeasure(4)[M1note2],prog.getMeasure(4)[M1note3],prog.getMeasure(4)[M1note4]};
		
		System.out.println("\nSong:");
		System.out.print(Arrays.toString(measureOne));
		System.out.print(Arrays.toString(measureTwo));
		System.out.print(Arrays.toString(measureThree));
		System.out.println(Arrays.toString(measureFour));
		
		String all_song_notes[]; //each measure concatenated into a single array of all the songs notes
		all_song_notes = new String[] {measureOne[0],measureOne[1],measureOne[2],measureOne[3],measureTwo[0],measureTwo[1],measureTwo[2],measureTwo[3],measureThree[0],measureThree[1],measureThree[2],measureThree[3],measureFour[0],measureFour[1],measureFour[2],measureFour[3]};
		
		
		int frequencies[]; //Array of frequencies of each note of the song; to be used in actually playing the song.
		frequencies = new int[all_song_notes.length];
		
		//initialize the frequencies appropriately based on what note they represent
		for (int i = 0; i < frequencies.length; i++) {
			if (all_song_notes[i].equals("c")) { //starting at middle c = 262Hz
				frequencies[i] = 262;
			}
			else if (all_song_notes[i].equals("c#")) {
				frequencies[i] = 277;
			}
			else if (all_song_notes[i].equals("d")) {
				frequencies[i] = 294;
			}
			else if (all_song_notes[i].equals("d#")) {
				frequencies[i] = 311;
			}
			else if (all_song_notes[i].equals("e")) {
				frequencies[i] = 330;
			}
			else if (all_song_notes[i].equals("f")) {
				frequencies[i] = 349;
			}
			else if (all_song_notes[i].equals("f#")) {
				frequencies[i] = 370;
			}
			else if (all_song_notes[i].equals("g")) {
				frequencies[i] = 392;
			}
			else if (all_song_notes[i].equals("g#")) {
				frequencies[i] = 415;
			}
			else if (all_song_notes[i].equals("a")) { //A 440
				frequencies[i] = 440;
			}
			else if (all_song_notes[i].equals("a#")) {
				frequencies[i] = 466;
			}
			else if (all_song_notes[i].equals("b")) {
				frequencies[i] = 494;
			}
		}
		for (int i = 0; i < frequencies.length; i++) {//plays notes using "createTone" method
			try {
		        SongMaker.createSquareWave((double)frequencies[i], 100); //division of frequencies by 2 lowers everything by an octave.
		    } catch (LineUnavailableException lue) {				 //not by dividing by two will leave it in its "native" form of being based on C4
		        System.out.println(lue);
		    }
		}
		System.out.println("\nWould you like to save this song? (y/n): ");
		String saveOrNot = scan.next();
		scan.nextLine();
		if (saveOrNot.equalsIgnoreCase("y")) {
			System.out.println("What would you like to name it?: ");
			String songName = scan.nextLine();
			saveToLilypond(songName,all_song_notes);
			System.out.println("Saved as "+songName+".ly.\nTo generate a pdf of the score, download Lilypond from lilypond.org/download.html,\nthen right-click on the .ly file and click \"generate pdf\".");
		}
		
		System.out.println("\nWould you like to make another song? (y/n): ");
		String exitOrNot = scan.next();
		if (exitOrNot.equalsIgnoreCase("y")) {
			exitStatus = false;
		}
		else {
			exitStatus = true;
			System.exit(0);
		}
	}
	while (exitStatus == false);
	scan.close();
	
	}
	public static void createTone(double Hertz, int volume) //Source of this method: http://digitalsoundandmusic.com/2-3-13-modeling-sound-in-java/
		throws LineUnavailableException {
		/** Exception is thrown when line cannot be opened */
			 //44100
		float rate = 44100;
		byte[] buf;
		AudioFormat audioF;
		
	    buf = new byte[1];
	    audioF = new AudioFormat(rate,8,1,true,false);
		//sampleRate, sampleSizeInBits,channels,signed,bigEndian
			 
	    SourceDataLine sourceDL = AudioSystem.getSourceDataLine(audioF);
	    sourceDL = AudioSystem.getSourceDataLine(audioF);
	    sourceDL.open(audioF);
	    sourceDL.start();
		
	    for(int i=0; i<rate; i++){
	      double angle = (i/rate)*Hertz*2.0*Math.PI;
	      buf[0]=(byte)(Math.sin(angle)*volume);
	      sourceDL.write(buf,0,1);
		}			 
	    sourceDL.drain();
	    sourceDL.stop();
	    sourceDL.close();
	}
	
	public static void createSquareWave(double Hertz, int volume) 
			throws LineUnavailableException {
			/** Exception is thrown when line cannot be opened */
				 //44100
			float rate = 44100;
			byte[] buf;
			AudioFormat audioF;
			
		    buf = new byte[1];
		    audioF = new AudioFormat(rate,8,1,true,false);
			//sampleRate, sampleSizeInBits,channels,signed,bigEndian
				 
		    SourceDataLine sourceDL = AudioSystem.getSourceDataLine(audioF);
		    sourceDL = AudioSystem.getSourceDataLine(audioF);
		    sourceDL.open(audioF);
		    sourceDL.start();
			
		    for(int i=0; i<rate; i++){
		    	double angle1 = i/rate*Hertz*1.0*2.0*Math.PI;
		    	double angle2 = i/rate*Hertz*3.0*2.0*Math.PI;
		    	double angle3 = i/rate*Hertz*5.0*2.0*Math.PI;
		    	double angle4 = i/rate*Hertz*7.0*2.0*Math.PI;
		    	 
		    	buf[0]=(byte)(Math.sin(angle1)*volume+
		    	     Math.sin(angle2)*volume/3+Math.sin(angle3)*volume/5+
		    	     Math.sin(angle4)*volume/7);
		    	sourceDL.write(buf,0,1);
			}			 
		    sourceDL.drain();
		    sourceDL.stop();
		    sourceDL.close();
		}
	
	public static void saveToLilypond (String name, String[] notes) {
		
		for (int i = 0 ; i < notes.length; i++) {
			if (notes[i].length() > 1) {
				notes[i] = notes[i].charAt(0)+"is";
			}
		}
		
		try {
			File f = new File(name+".ly"); //create a Lilypond file with user-defined name
			FileWriter fw = new FileWriter(f,false);
			PrintWriter pw = new PrintWriter(fw);
			pw.println("\\header{");
			pw.println("title = \""+name+"\"");
			pw.println("tagline = \"\"");
			pw.println("}");
			pw.println("\\absolute{");
			pw.println("\\key "+key.toLowerCase()+"\\major");
			pw.println("\\time 4/4");
			pw.println("\\clef treble");
			pw.println(notes[0]+"'' "+notes[1]+"'' "+notes[2]+"'' "+notes[3]+"'' "+notes[4]+"'' "+notes[5]+"'' "+notes[6]+"'' "+notes[7]+"'' "+notes[8]+"'' "+notes[9]+"'' "+notes[10]+"'' "+notes[11]+"'' "+notes[12]+"'' "+notes[13]+"'' "+notes[14]+"'' "+notes[15]+"''");
			pw.println("}");
			pw.close();
		} catch (IOException e) {
			System.out.println("Error: saveToLilypond");
		}
		
	}
}