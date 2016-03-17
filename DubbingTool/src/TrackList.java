import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.sound.sampled.AudioFormat;
import javax.xml.parsers.*;

import java.io.*;

public class TrackList {
	
	private ArrayList<Track> tracks;
	private ArrayList<ActionListener> actionlisteners;
	private String fileName;
	
	private AudioFormat format;
	private static int currentID = 1;
	
	TrackList() {
		format = null;
		tracks = new ArrayList<Track>();
		actionlisteners = new ArrayList<ActionListener>();
	}

	TrackList(String name) throws BadFileException, BadPathException {
		tracks = new ArrayList<Track>();
		actionlisteners = new ArrayList<ActionListener>();
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
						String id = element.getAttribute("id");
						String trackName = element.getElementsByTagName("filename").item(0).getTextContent();
						String relativeTo = element.getElementsByTagName("relativeto").item(0).getTextContent();
						String relativePos = element.getElementsByTagName("relativeposition").item(0).getTextContent();
						String intensity = element.getElementsByTagName("intensity").item(0).getTextContent();
						
						boolean startend = false;
						if (relativePos.equals("start")) {
							startend = Track.START;
						}
						else {
							startend = Track.END;
						}
						Track track = new Track(
								trackName,
								Double.parseDouble(intensity),
								Integer.parseInt(relativeTo),
								startend,
								Integer.parseInt(id),
								this);
						this.add(track);
					}
				}
			}
			else {
				throw new BadFileException();
			}
		}
		catch (ParserConfigurationException e) {
			throw new BadFileException();
		}
		catch (SAXException e) {
			throw new BadFileException();
		}
		catch (IOException e) {
			throw new BadPathException();
		}
		format = getHighestQualityFormat();
		
		int highestID = 1;
		
		for(Track t : tracks)
		{
			if(t.getID() > highestID)
				highestID = t.getID();
		}
		
		currentID = highestID;
	}
	
	public void add(Track newTrack) {
		tracks.add(newTrack);
		updateActionListeners();
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
	
	public double totalLength(){
		double end = 0;
		for(Track t : tracks)
		{
			if(t.startTime() + t.getLength() > end)
				end = t.startTime() + t.getLength();
		}
		return end;
	}
	
	public void play() 
	{
		if(failedTracks().size() > 0)
			return;
		long currentTime = System.currentTimeMillis();
		
		
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
	
	public void save(String fileName) {
		try {
			
		}
	}
	
	public void setFileName(String fileName) throws BadFileException, BadPathException {
		tracks = new ArrayList<Track>();
		try {
			this.fileName = fileName;
			File scriptFile = new File(fileName);
			DocumentBuilderFactory bFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = bFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(scriptFile);
			if (doc.getDocumentElement().getNodeName().toLowerCase().equals("script")) {
				NodeList nList = doc.getElementsByTagName("track");
				for (int i = 0; i < nList.getLength(); ++i) {
					Node currNode = nList.item(i);
					if (currNode.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element)currNode;
						String id = element.getAttribute("id");
						String trackName = element.getElementsByTagName("filename").item(0).getTextContent();
						String relativeTo = element.getElementsByTagName("relativeto").item(0).getTextContent();
						String relativePos = element.getElementsByTagName("relativeposition").item(0).getTextContent();
						String intensity = element.getElementsByTagName("intensity").item(0).getTextContent();
						
						boolean startend = false;
						if (relativePos.equals("start")) {
							startend = Track.START;
						}
						else {
							startend = Track.END;
						}
						Track track = new Track(
								trackName,
								Double.parseDouble(intensity),
								Integer.parseInt(relativeTo),
								startend,
								Integer.parseInt(id),
								this);
						this.add(track);
					}
				}
			}
			else {
				throw new BadFileException();
			}
		}
		catch (ParserConfigurationException e) {
			throw new BadFileException();
		}
		catch (SAXException e) {
			throw new BadFileException();
		}
		catch (IOException e) {
			throw new BadPathException();
		}
		updateActionListeners();
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public AudioFormat getTrackListFormat()
	{
		return format;
	}
	
	public static int nextID()
	{
		return currentID++;
	}
	
	private AudioFormat getHighestQualityFormat()
	{
		
		
	}
}
