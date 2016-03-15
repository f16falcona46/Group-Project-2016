import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;

import java.io.*;

public class TrackList {
	
	private ArrayList<Track> tracks;
	private ArrayList<ActionListener> actionlisteners;
	private String fileName;
	
	TrackList() {
		tracks = new ArrayList<Track>();
		actionlisteners = new ArrayList<ActionListener>();
	}

	TrackList(String name) {
		try {
			File scriptFile = new File(name);
			DocumentBuilderFactory bFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = bFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(scriptFile);
			if (doc.getDocumentElement().getNodeName().toLowerCase().equals("script")) {
				NodeList nList = doc.getElementsByTagName("track");
				for (int i = 0; i < nList.getLength(); ++i) {
					Node currNode = nList.item(i);
					if (currNode.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element)currNode;
						String trackName = element.getElementsByTagName("filename").item(0).getTextContent();
						String relativeTo = element.getElementsByTagName("relativeto").item(0).getTextContent();
						String relativePos = element.getElementsByTagName("relativeposition").item(0).getTextContent();
						String intensity = element.getElementsByTagName("intensity").item(0).getTextContent();
					}
				}
			}
			else {
				
			}
		}
		catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
