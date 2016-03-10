import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Image;

public class PicturePane extends JPanel implements ActionListener, MouseListener {

	private TrackList list;
	private ImageIcon leftTrack = new ImageIcon(this.getClass().getResource("GraphicsElementLeft"));
	private ImageIcon rightTrack = new ImageIcon(this.getClass().getResource("GraphicsElementRight"));
	private ImageIcon centerTrack = new ImageIcon(this.getClass().getResource("GraphicsElementCenter"));
	private static final int LEFT_SPACING = 10;
	private static final int TOP_SPACING = 5;
	private static final int SIDE_WIDTH = 10;
	private static final int IMAGE_HEIGHT = 32;
	
	PicturePane (TrackList List) {
		list = List;
	}
	
	public void paintComponent(Graphics g) {
		this.setSize(new Dimension((int)(list.totalLength()*5), list.numTracks()*IMAGE_HEIGHT));
		
		for (int i=0; i< list.numTracks(); i++) {
			//Draws the left section of the track
			g.drawImage(leftTrack.getImage(), 
					LEFT_SPACING + (int)(list.get(i).startTime()*5),
					TOP_SPACING + i*IMAGE_HEIGHT, 
					SIDE_WIDTH, 
					IMAGE_HEIGHT,
					null);
			//Draws the center section of the track
			g.drawImage(centerTrack.getImage(), 
					LEFT_SPACING + (int)(list.get(i).startTime()*5)+ SIDE_WIDTH,
					TOP_SPACING + i*IMAGE_HEIGHT, 
					(list.get(i).getLength()*5) - SIDE_WIDTH*2, 
					IMAGE_HEIGHT, 
					null);
			//Draws the right section of the track
			g.drawImage(rightTrack.getImage(),
					LEFT_SPACING + (int)((list.get(i).startTime()+getLength())*5) - SIDE_WIDTH,
					TOP_SPACING + i*IMAGE_HEIGHT,
					SIDE_WIDTH,
					IMAGE_HEIGHT,
					null);
		}
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount == 2){
			int y = e.getY();
			int tracknum = y/IMAGE_HEIGHT;
			if(tracknum!=0){
				EditTrackDialogue eTD= new EditTrackDialogue(trackNum); 
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		repaint();
	}

}
