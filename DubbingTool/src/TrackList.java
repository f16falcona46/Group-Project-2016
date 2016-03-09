import java.awt.List;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class TrackList {
	
	private ArrayList<Track> tracks;
	private String fileName;
	
	TrackList() {
		
	}

	TrackList(String name) {
		
	}
	
	public void add(Track newTrack) {
		tracks.add(newTrack);
	}
	
	public Track get(int index) {
		tracks.get(index);
	}
	
	public Track remove(int index) {
		track.remove(index);
	}
	
	public int numTracks() {
		return tracks.size();
	}
	
	public List<Track> failedTracks() {
		
	}
	
	public List<Track> getTracks() {
		
	}
	
	public double totalLength() {
		
	}
	
	public void play() {
		
	}
	
	public void addActionListener(ActionListener listener) {
		
	}
	
	public void updateActionListeners() {
		
	}
	
	public void save() {
		
	}
	
	public void setFileName(String fileName) {
		
	}
	
	public String getFileName() {
		
	}
}
