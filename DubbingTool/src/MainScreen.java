import java.awt.Dimension;

import javax.swing.*;
public class MainScreen extends JFrame{
	private TrackList tracklist;
	MainScreen(TrackList tracklist){
		
		JFrame mainFrame = new JFrame("[No file opened] - Dubbing Tool");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		this.tracklist = tracklist;
		MainMenuBar menuBar = new MainMenuBar(tracklist);
		//TrackTablePane trackTablePane = new TrackTablePane(tracklist);
		PicturePane picturPane = new PicturePane(tracklist);
		//JScrollPane rightPane = new JScrollPane(picturePane.contentPane);
		//JSplitPane splitPane = new JSplitPane(TrackTablePane, rightPane);
		
		mainFrame.pack();
		mainFrame.setVisible(true);
		mainFrame.setExtendedState(mainFrame.getExtendedState()|JFrame.MAXIMIZED_BOTH);
	}
	public static void main(String[] a){
		TrackList list = new TrackList();
		MainScreen screen = new MainScreen(list);
	}
}