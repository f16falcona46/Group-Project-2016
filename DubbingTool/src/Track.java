import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;


public class Track 
{
	private String fileName;
	private double length;
	private double intensity;
	private int relativeTo;
	private boolean startEnd;
	private int ID;
	private AudioInputStream dataStream;
	private TrackList tracklist;
	private Clip soundClip;
	private AudioFormat format;
	
	public static final int RECORD = 1;
	public static final boolean START = true;
	public static final boolean END = false;
	
	public Track(String fileName, double intensity, int relativeTo, boolean startEnd, int ID, TrackList tracklist)
	{
		this.fileName = fileName;
		this.intensity = intensity;
		this.relativeTo = relativeTo;
		this.startEnd = startEnd;
		this.ID = ID;
		this.tracklist = tracklist;
		try 
		{
			soundClip = AudioSystem.getClip();
		} 
		catch(LineUnavailableException e) 
		{
			//TODO: dialog box
			e.printStackTrace();
		}
		this.format = tracklist.getTrackListFormat();
	}
	
	Track(int mode, TrackList tracklist)
	{
		this.tracklist = tracklist;
		fileName = "";
		length = -1;
		intensity = 100;
	}
	
	
	public void play()
	{
		reloadClip();
		soundClip.start();
	}
	
	public Clip getSound()
	{
		reloadClip();
		return soundClip;
	}

	public void setFileName(String fileName)
	{
		this.fileName = fileName;
		reloadStream();
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public void setLength(double length) {
		
	}
	
	public double getLength() {
		return length;
	}
	
	public void setIntensity(double intensity) {
		
	}
	
	public double getIntensity() {
		return intensity;
	}
	
	public void setRelativeTo(int ID) {
		
	}
	
	public int getRelativeID() {
		return relativeTo;
	}
	
	public void setStartEnd(boolean startEnd) {
		
	}
	
	public boolean getStartEnd() {
		return startEnd;
	}
	
	public boolean isGood() {
		return true;
	}
	
	public double startTime() {
		
	}
	
	
	
	//~~
	

	private void reloadClip()
	{
		if(soundClip.isOpen())
			soundClip.close();
		try 
		{
			soundClip.open(dataStream);
		} 
		catch (LineUnavailableException e)
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		soundClip.setFramePosition(0);
	}
	
	private void reloadStream()
	{
		
	}
	
	private AudioInputStream getConvertedInputStream()
	{
		
	}
}
