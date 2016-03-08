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
	private static final int IMAGE_HEIGHT = 35;
	
	PicturePane (TrackList List) {
		list = List;
		  
		
	}
	
	public void paintComponent(Graphics g) {
		this.setSize(new Dimension((int)(list.totalLength()*5), list.numTracks()*35));
		for (int i=0; i< list.numTracks(); i++) {
			g.drawImage(leftTrack.getImage(), LEFT_SPACING + (int)(list.get(i).startTime()*5), TOP_SPACING + i*IMAGE_HEIGHT, SIDE_WIDTH, IMAGE_HEIGHT, null);
			g.drawImage(centerTrack.getImage(), LEFT_SPACING + (int)(list.get(i).startTime()*5)+ SIDE_WIDTH, TOP_SPACING + i*IMAGE_HEIGHT, (list.get(i).getLength()*5) - SIDE_WIDTH*2, IMAGE_HEIGHT, null);
			g.drawImage(rightTrack.getImage(), LEFT_SPACING + (int)((list.get(i).startTime()+getLength())*5) - SIDE_WIDTH, TOP_SPACING + i*IMAGE_HEIGHT, SIDE_WIDTH, IMAGE_HEIGHT, null);
		}
		
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}

	
	
}
