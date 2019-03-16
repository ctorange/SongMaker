import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import javax.sound.sampled.AudioFormat; //audio stuff...
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.LineUnavailableException;

public class SongMaker {

	public static void main(String[] args) {
						// 0    1	  2    3    4     5    6     7    8    9     10   11
		String Notes[] = {"a", "a#", "b", "c", "c#", "d", "d#", "e", "f", "f#", "g", "g#"};//all available notes; a is 0
		
		Random random = new Random(); //used for generating notes
		
		String measureOne[] = null;
		String measureTwo[] = null;
		String measureThree[] = null;
		String measureFour[] = null;
		
		Scanner scan = new Scanner (System.in);
		int progType[] = new int[4]; //chord progressions can only have 4 chords for now.
		
		System.out.print("Enter key of song (major only): ");
		String key = scan.next();
		System.out.println("\nEnter chord progression as a list of integers. For instance, '1 5 6 4'");
		progType[0] = scan.nextInt();
		progType[1] = scan.nextInt();
		progType[2] = scan.nextInt();
		progType[3] = scan.nextInt();
		
		scan.close();
		
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
		        SongMaker.createTone((double)frequencies[i], 100); //division of frequencies by 2 lowers everything by an octave.
		    } catch (LineUnavailableException lue) {				 //not by dividing by two will leave it in its "native" form of being based on C4
		        System.out.println(lue);
		    }
		}
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
}