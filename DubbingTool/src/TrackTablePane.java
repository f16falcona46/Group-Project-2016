import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class TrackTablePane extends JPanel implements ActionListener {
	
	TrackTable trackTable;
	JButton edit;
	JButton delete;
	TrackList tracklist;

	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == edit){
			EditTrackDialog etd = new EditTrackDialog();
		}
		
		if(e.getSource() == delete){
			tracklist.remove();
		}

	}
}
