import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.table.AbstractTableModel;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/* SKELETON CLASS ONLY! DOES NOT DO ANYTHING. */

public class TrackTable extends JPanel implements ActionListener, MouseListener {
	private TrackList list;
	
	TrackTable(TrackList list) {
		super(new GridLayout(1,0));
		
		this.list = list;

		JTable table = new JTable(new TrackTableModel(this.list));
		table.setPreferredScrollableViewportSize(new Dimension(500, 200));
		table.setRowHeight(32);
		table.setFillsViewportHeight(true);

		JScrollPane scrollPane = new JScrollPane(table);
		
		add(scrollPane);
	}
	/*Creates a JTable reflecting the provided TrackList, adding this TrackTable to the TrackList’s ActionListeners. The format of the table is as in the storyboard, except with no Actions column.*/
	
	private void updateTable() {
		
	}
	
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
	
	public static void main(String[] args) {
		TrackTable a = new TrackTable(new TrackList());
		JFrame b = new JFrame();
		b.setContentPane(a);
		b.pack();
		b.setVisible(true);
	}
}

class TrackTableModel extends AbstractTableModel {
	private static final long serialVersionUID = -6017075090147934985L;
	
	private static final String[] columnNames = {
		"File Name",
		"Start Time",
		"Attached To",
		"Intensity"
	};
	
	private TrackList list;
	
	TrackTableModel(TrackList list) {
		super();
		this.list = list;
	}
	
	public String getColumnName(int col) {
		return columnNames[col];
	}
	
	public int getColumnCount() {
		return 4;
	}

	public int getRowCount() {
		return list.numTracks();
	}

	private static String toMMSS(double time) {
		Date date = new Date((long)(time*1000));
		String formattedDate = new SimpleDateFormat("mm:ss").format(date);
		return formattedDate;
	}
	
	private String formatAttachedTo(int row) {
		if (list.get(row).getRelativeID()==0) {
			return "Start";
		}
		else {
			String startend = (list.get(row).getStartEnd()==Track.START)?"[Start] ":"[End] ";
			int ID = list.get(row).getRelativeID();
			for (Track track : list.getTracks()) {
				if (track.getID() == ID) {
					return startend+track.getFileName();
				}
			}
			return "Track not found.";
		}
	}
	
	public Object getValueAt(int row, int col) {
		switch (col) {
		case 0:
			return list.get(row).getFileName();
		case 1:
			return toMMSS(list.get(row).startTime());
		case 2:
			return formatAttachedTo(row);
		case 3:
		default:
			return "Error, column out of range";
		}
	}
}
