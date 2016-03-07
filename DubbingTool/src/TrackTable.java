import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;

/* SKELETON CLASS ONLY! DOES NOT DO ANYTHING. */

public class TrackTable extends JTable implements ActionListener, MouseListener {
	TrackTable(TrackList list) {
		
	}
	/*Creates a JTable reflecting the provided TrackList, adding this TrackTable to the TrackList’s ActionListeners. The format of the table is as in the storyboard, except with no Actions column.*/
	
	//TODO: IMPLEMENT
	public void mouseClicked(MouseEvent ev) { 
		/*
		Single click:
		Highlights track and activates edit and delete buttons
		Sets clicked track to selectedTrack
		Double click
		Constructs an EditTrackDialog([selected track]). If no track is selected, nothing happens. If more than one track is selected, nothing happens.
		Right click
		highlights clicked track
		opens right click JPopupMenu with the following JMenuItems: “Edit”, “Delete”
		*/
	}

	public void mouseEntered(MouseEvent ev) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent ev) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent ev) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent ev) {
		// TODO Auto-generated method stub
		
	}
	
	//TODO: IMPLEMENT
	public void actionPerformed(ActionEvent ev) {
		/*
		On receiving updateScript
		Updates the table to reflect the TrackList.
		Track getSelected()
		Returns the selected Track.
		*/
	}
}
