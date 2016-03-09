import javax.swing.*;
public class MainScreen extends JFrame{
	private TrackList tracklist;
	MainScreen(TrackList tracklist){
		this.tracklist = tracklist;
		MainMenuBar menuBar = new MainMenuBar(tracklist);
		TrackTablePane trackTablePane = new TrackTablePane(tracklist);
		PicturePane picturPane = new PicturePane(tracklist);
		JScrollPane rightPane = new JScrollPane(picturePane.contentPane);
		JSplitPane splitPane = new JSplitPane(TrackTablePane, rightPane);
	}
	public static void main(String[] a){
		TrackList list = new TrackList();
		MainScreen screen = new MainScreen(list);
	}
}
