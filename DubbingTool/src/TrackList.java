import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.*;

public class TrackList {
	
	private ArrayList<Track> tracks;
	private ArrayList<ActionListener> actionlisteners;
	private String fileName;
	
	TrackList() {
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
		this.save(this.getFileName());
	}
	
	public void save(String filename) {
		try {
			DocumentBuilderFactory dBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dBuilderFactory.newDocumentBuilder();
			Document script = dBuilder.newDocument();
			Element rootElement = script.createElement("script");
			script.appendChild(rootElement);
			
			for (Track track : this.getTracks()) {
				Element trackElement = script.createElement("track");
				rootElement.appendChild(trackElement);
				
				Attr id = script.createAttribute("id");
				id.setValue(Integer.toString(track.getID()));
				trackElement.setAttributeNode(id);
				
				Element filename_xml = script.createElement("filename_xml");
				trackElement.appendChild(filename_xml);
				filename_xml.appendChild(script.createTextNode(track.getFileName()));
				trackElement.appendChild(filename_xml);
				
				Element relativeTo = script.createElement("relativeTo");
				trackElement.appendChild(relativeTo);
				String relativeToString = "";
				if (track.getStartEnd() == Track.START) {
					relativeToString = "start";
				}
				else {
					relativeToString = "end";
				}
				relativeTo.appendChild(script.createTextNode(relativeToString));
				trackElement.appendChild(relativeTo);
				
				Element relativePosition = script.createElement("relativePosition");
				trackElement.appendChild(relativePosition);
				relativePosition.appendChild(script.createTextNode(Double.toString(track.getIntensity())));
				trackElement.appendChild(relativePosition);
				
				Element intensity = script.createElement("intensity");
				trackElement.appendChild(intensity);
				intensity.appendChild(script.createTextNode(Double.toString(track.getIntensity())));
				trackElement.appendChild(intensity);
				
				Element length = script.createElement("length");
				trackElement.appendChild(length);
				length.appendChild(script.createTextNode("0"));
				trackElement.appendChild(length);
				
				TransformerFactory tFactory = TransformerFactory.newInstance();
				Transformer transformer = tFactory.newTransformer();
				DOMSource source = new DOMSource(script);
				StreamResult sresult = new StreamResult(new File(filename));
				transformer.transform(source, sresult);
			}
		}
	}
	
	public void setFileName(String fileName) throws BadFileException, BadPathException {
		tracks = new ArrayList<Track>();
		try {
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
	}
	
	public String getFileName() {
		return fileName;
	}
}
