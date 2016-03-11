import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class Track 
{
	private String fileName;
	private double length;
	private double intensity;
	private long lengthInSamples;
	private int relativeTo;
	private boolean startEnd;
	private int ID;
	private AudioInputStream dataStream;
	private TrackList tracklist;
	private Clip soundClip;
	
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
	}
	
	Track(int mode, TrackList tracklist)
	{
		this.tracklist = tracklist;
		fileName = "";
		length = -1;
		lengthInSamples = -1;
		relativeTo = -1;
		startEnd = START;
		intensity = 100;
//		dataStream = new RecorderStream();
	}
	
	
	public void play()
	{
		loadStream();
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
		loadStream();
	}
	
	public String getFileName()
	{
		return fileName;
	}
	
	public double getLength()
	{
		return length;
	}
	
	public void setIntensity(double intensity)
	{
		this.intensity = intensity;
	}
	
	public double getIntensity()
	{
		return intensity;
	}
	
	public void setRelativeTo(int ID) 
	{
		this.relativeTo = ID;
	}
	
	public int getRelativeID() 
	{
		return relativeTo;
	}
	
	public void setStartEnd(boolean startEnd) 
	{
		this.startEnd = startEnd;
	}
	
	public boolean getStartEnd()
	{
		return startEnd;
	}
	
	public boolean isGood()
	{
		return true;
	}
	
	public double startTime()
{
		if(relativeTo == -1)
			return 0;
		Track relativeTrack = tracklist.get(relativeTo);
		double relativeTime = relativeTrack.startTime();
		if(startEnd == START) //relative to beginning
			return relativeTime;
		else
			return relativeTime + relativeTrack.getLength();
	}
	
	public int getID()
	{
		return ID;
	}
	
	//~~

	private void reloadClip()
	{
		if(soundClip.isOpen())
			soundClip.close();
		try 
		{
			soundClip.open(dataStream);
			FloatControl volumeMod = (FloatControl)soundClip.getControl(FloatControl.Type.MASTER_GAIN);
			float range = volumeMod.getMinimum();
			
			range *= ((100.0 - this.intensity) / 100.0);
			volumeMod.setValue(range);
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
	
	private void loadStream() throws IOException, UnsupportedAudioFileException
	{
		if(dataStream != null)
			dataStream.close();
		dataStream = AudioSystem.getAudioInputStream(new File(fileName));
	}
	
	private AudioInputStream getConvertedInputStream(AudioInputStream s)
	{
		this.lengthInSamples = getSampleLength(s);
		this.length = getLength(s, this.lengthInSamples);
		if(s.getFormat().matches(tracklist.getTrackListFormat()))
			return s;
		if(AudioSystem.isConversionSupported(tracklist.getTrackListFormat(), s.getFormat()))
		{
			AudioInputStream ret = AudioSystem.getAudioInputStream(tracklist.getTrackListFormat(), s);
			return ret;
		}
		return s;//CHANGEME
	}
	
	private double getLength(AudioInputStream s, long samples)
	{
		AudioFormat f = tracklist.getTrackListFormat();
		return ((double)samples / (double)f.getFrameRate());
	}
	
	private long getSampleLength(AudioInputStream s)
	{
		double frameLength = s.getFrameLength();
		double frameRate = (double) s.getFormat().getFrameRate();
		double targetRate = (double) tracklist.getTrackListFormat().getFrameRate();
		double ret = (targetRate / frameRate) * frameLength;
		frameLength *= ((double)tracklist.getTrackListFormat().getChannels() / (double)s.getFormat().getChannels());
		return (long)ret;
	}
}
