
public class Track {
	
	private String fileName;
	private double length;
	private double intensity;
	private int relativeTo;
	private boolean startEnd;
	private int ID;
	private byte[] soundBuffer;
	private Clip soundClip;
	private TrackList tracklist;
	
	public static final int RECORD;
	public static final boolean START;
	public static final boolean END;
	
	Track(String newFileName, double newLength, double newIntensity, int newRelativeTo, boolean newStartEnd, int newID, TrackList newTracklist) {
		fileName = newFileName;
		length = newLength;
		intensity = newIntensity;
		relativeTo = newRelativeTo;
		startEnd = newStartEnd;
		ID = newID;
		tracklist = newTracklist;
		
	}
	
	Track(int mode, TrackList trackList) {
		
	}
	
	public void play() {
		
	}
	
	public Clip getSound() {
		
	}
	
	public void setFileName(String fileName) {
		
	}
	
	public String getFileName() {
		
	}
	
	public void setLength(double length) {
		
	}
	
	public double getLength() {
		
	}
	
	public void setIntensity(double intensity) {
		
	}
	
	public double getIntensity() {
		
	}
	
	public void setRelativeTo(int ID) {
		
	}
	
	public int getRelativeID() {
		
	}
	
	public void setStartEnd(boolean startEnd) {
		
	}
	
	public boolean getStartEnd() {
		
	}
	
	public boolean isGood() {
		
	}
	
	public double startTime() {
		
	}

}
