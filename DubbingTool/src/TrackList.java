import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class TrackList {
	
	private ArrayList<Track> tracks;
	private ArrayList<ActionListener> actionlisteners;
	private String fileName;
	
	TrackList() {
		tracks = new ArrayList<Track>();
		actionlisteners = new ArrayList<ActionListener>();
	}

	TrackList(String name) {
		
	}
	
	public void add(Track newTrack) {
		tracks.add(newTrack);
	}
	
	public Track get(int index) {
		return tracks.get(index);
	}
	
	public Track remove(int index) {
		return tracks.remove(index);
	}
	
	public int numTracks() {
		return tracks.size();
	}
	
	public ArrayList<Track> failedTracks() {
		ArrayList<Track> failedTracks = new ArrayList<Track>();
		for (Track track : tracks) {
			if (!track.isGood()) {
				failedTracks.add(track);
			}
		}
		return failedTracks;
	}
	
	public ArrayList<Track> getTracks() {
		return tracks;
	}
	
	public double totalLength() {
		
	}
	
	public void play() {
		
	}
	
	public void addActionListener(ActionListener listener) {
		actionlisteners.add(listener);
	}
	
	public void updateActionListeners() {
		for (ActionListener listener : actionlisteners) {
			ActionEvent ev = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "updateScript");
			listener.actionPerformed(ev);
		}
		this.save();
	}
	
	public void save() {
		
	}
	
	public void setFileName(String fileName) {
		
	}
	
	public String getFileName() {
		return fileName;
	}
}
