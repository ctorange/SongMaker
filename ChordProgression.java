
//makes chord progression based on progression type

public class ChordProgression {
	
	String Notes[] = {"a", "a#", "b", "c", "c#", "d", "d#", "e", "f", "f#", "g", "g#"};
	
	String CMajorScale[] = {Notes[3],Notes[5],Notes[7],Notes[8],Notes[10],Notes[0],Notes[2]};
	String DMajorScale[] = {Notes[5],Notes[7],Notes[9],Notes[10],Notes[0],Notes[2],Notes[4]};
	String EMajorScale[] = {Notes[7],Notes[9],Notes[11],Notes[0],Notes[2],Notes[4],Notes[6]};
	String FMajorScale[] = {Notes[8],Notes[10],Notes[0],Notes[1],Notes[3],Notes[5],Notes[7]};
	String GMajorScale[] = {Notes[10],Notes[0],Notes[2],Notes[3],Notes[5],Notes[7],Notes[9]};
	String AMajorScale[] = {Notes[0],Notes[2],Notes[4],Notes[5],Notes[7],Notes[9],Notes[11]};
	String BMajorScale[] = {Notes[2],Notes[4],Notes[6],Notes[7],Notes[9],Notes[11],Notes[1]};
	
	String Measure1NoteSet[];
	String Measure2NoteSet[];
	String Measure3NoteSet[];
	String Measure4NoteSet[];
	
	public ChordProgression (int[] prog, String key) {
		
	String currentKey[] = null;
		
	if (key.equals("C")) {  //sets Key to be whatever the user entered
		currentKey = CMajorScale;
	}
	else if (key.equals("D")) {
		currentKey = DMajorScale;
	}
	else if (key.equals("E")) {
		currentKey = EMajorScale;
	}
	else if (key.equals("F")) {
		currentKey = FMajorScale;
	}
	else if (key.equals("G")) {
		currentKey = GMajorScale;
	}
	else if (key.equals("A")) {
		currentKey = AMajorScale;
	}
	else if (key.equals("B")) {
		currentKey = BMajorScale;
	}
	
	if (prog[0] == 1) { //assigns the measure 1 notes, based on the first chord of the progression
		Measure1NoteSet = new String[] {currentKey[0], currentKey[2], currentKey[4]};
	}
	else if (prog[0] == 2) {
		Measure1NoteSet = new String[] {currentKey[1], currentKey[3], currentKey[6]};
	}
	else if (prog[0] == 3) {
		Measure1NoteSet = new String[] {currentKey[2], currentKey[4], currentKey[6]};
	}
	else if (prog[0] == 4) {
		Measure1NoteSet = new String[] {currentKey[0], currentKey[3], currentKey[5]};
	}
	else if (prog[0] == 5) {
		Measure1NoteSet = new String[] {currentKey[1], currentKey[4], currentKey[6]};
	}
	else if (prog[0] == 6) {
		Measure1NoteSet = new String[] {currentKey[0], currentKey[2], currentKey[5]};
	}
	else if (prog[0] == 7) {
		Measure1NoteSet = new String[] {currentKey[1], currentKey[3], currentKey[6]};
	}
	
	if (prog[1] == 1) { //assigns the measure 2 notes, based on the first chord of the progression
		Measure2NoteSet = new String[] {currentKey[0], currentKey[2], currentKey[4]};
	}
	else if (prog[1] == 2) {
		Measure2NoteSet = new String[] {currentKey[1], currentKey[3], currentKey[6]};
	}
	else if (prog[1] == 3) {
		Measure2NoteSet = new String[] {currentKey[2], currentKey[4], currentKey[6]};
	}
	else if (prog[1] == 4) {
		Measure2NoteSet = new String[] {currentKey[0], currentKey[3], currentKey[5]};
	}
	else if (prog[1] == 5) {
		Measure2NoteSet = new String[] {currentKey[1], currentKey[4], currentKey[6]};
	}
	else if (prog[1] == 6) {
		Measure2NoteSet = new String[] {currentKey[0], currentKey[2], currentKey[5]};
	}
	else if (prog[1] == 7) {
		Measure2NoteSet = new String[] {currentKey[1], currentKey[3], currentKey[6]};
	}
	
	if (prog[2] == 1) { //assigns the measure 3 notes, based on the first chord of the progression
		Measure3NoteSet = new String[] {currentKey[0], currentKey[2], currentKey[4]};
	}
	else if (prog[2] == 2) {
		Measure3NoteSet = new String[] {currentKey[1], currentKey[3], currentKey[6]};
	}
	else if (prog[2] == 3) {
		Measure3NoteSet = new String[] {currentKey[2], currentKey[4], currentKey[6]};
	}
	else if (prog[2] == 4) {
		Measure3NoteSet = new String[] {currentKey[0], currentKey[3], currentKey[5]};
	}
	else if (prog[2] == 5) {
		Measure3NoteSet = new String[] {currentKey[1], currentKey[4], currentKey[6]};
	}
	else if (prog[2] == 6) {
		Measure3NoteSet = new String[] {currentKey[0], currentKey[2], currentKey[5]};
	}
	else if (prog[2] == 7) {
		Measure3NoteSet = new String[] {currentKey[1], currentKey[3], currentKey[6]};
	}
	
	if (prog[3] == 1) { //assigns the measure 4 notes, based on the first chord of the progression
		Measure4NoteSet = new String[] {currentKey[0], currentKey[2], currentKey[4]};
	}
	else if (prog[3] == 2) {
		Measure4NoteSet = new String[] {currentKey[1], currentKey[3], currentKey[6]};
	}
	else if (prog[3] == 3) {
		Measure4NoteSet = new String[] {currentKey[2], currentKey[4], currentKey[6]};
	}
	else if (prog[3] == 4) {
		Measure4NoteSet = new String[] {currentKey[0], currentKey[3], currentKey[5]};
	}
	else if (prog[3] == 5) {
		Measure4NoteSet = new String[] {currentKey[1], currentKey[4], currentKey[6]};
	}
	else if (prog[3] == 6) {
		Measure4NoteSet = new String[] {currentKey[0], currentKey[2], currentKey[5]};
	}
	else if (prog[3] == 7) {
		Measure4NoteSet = new String[] {currentKey[1], currentKey[3], currentKey[6]};
	}
	}

	public String[] getMeasure(int measure) {
		if (measure == 1) {
			return Measure1NoteSet;
		}
		else if (measure == 2) {
			return Measure2NoteSet;
		}
		else if (measure == 3) {
			return Measure3NoteSet;
		}
		else if (measure == 4) {
			return Measure4NoteSet;
		}
		else return null;
	}

}
