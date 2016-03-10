import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.*;
import javax.swing.border.*;

public class EditTrackDialog extends JFrame implements ActionListener {
	private Track currentTrack;
	private Track backUpTrack;
	private JButton chooser, preview, save, cancel;
	private JRadioButton beginning, end;
	private JComboBox chooseTrack;
	private JTextField text;
	private JFileChooser fc;
	private JSlider ISlider;
	EditTrackDialog(Track track){
		backUpTrack = track;
		currentTrack = track;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		this.setResizable(false);
		try { 
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); 
		} catch (Exception ex) { 
			ex.printStackTrace(); 
		}
		
		JPanel pane = new JPanel(new GridBagLayout());	
		Border margin = new EmptyBorder(10,10,10,10);
		pane.setBorder(margin);
		GridBagConstraints c = new GridBagConstraints();
		
		JLabel fileName = new JLabel ("File Name");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(10,10,7,7);
		pane.add(fileName, c);
		
		text = new JTextField();
		text.setEditable(false);
		text.setFont(new Font("Tahoma",Font.PLAIN,12));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		pane.add(text, c);
		
		chooser = new JButton("Choose File");
		chooser.addActionListener(this);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 0;
		pane.add(chooser, c);
		
		fc = new JFileChooser();
		
		JLabel relativeTrack = new JLabel ("Relative Track");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		pane.add(relativeTrack, c);
		
		
		String[] tracks = getTrackNames();
		chooseTrack = new JComboBox(); //with tracks
		chooseTrack.addActionListener(this);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		pane.add(chooseTrack, c);
		
		beginning = new JRadioButton("Beginning");
		beginning.addActionListener(this);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 1;
		pane.add(beginning, c);
		
		end = new JRadioButton("End");
		end.addActionListener(this);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 1;
		pane.add(end, c);
		
	    ButtonGroup group = new ButtonGroup();
	    group.add(beginning);
	    group.add(end);
		
		JLabel label = new JLabel ("Intensity");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		pane.add(label, c);
		
		ISlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 100);
		ISlider.setMajorTickSpacing(25);
		ISlider.setPaintTicks(true);
		ISlider.setPaintLabels(true);
		ISlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e){
				JSlider source = (JSlider)e.getSource();
				if(!source.getValueIsAdjusting()){
					double intensity = source.getValue();
					currentTrack.setIntensity(intensity);
				}
			}
		});
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;
		pane.add(ISlider, c);
		
		preview = new JButton("Preview");
		preview.addActionListener(this);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		pane.add(preview, c);
		
		save = new JButton("Save");
		save.addActionListener(this);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 3;
		pane.add(save, c);
		
		cancel = new JButton("Cancel");
		cancel.addActionListener(this);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 3;
		pane.add(cancel, c);
			
		this.add(pane);
		this.pack();
		SwingUtilities.getRootPane(save).setDefaultButton(save);
		this.setVisible(true);
		
		
	}
	private String[] getTrackNames(List<Track> list){
		String[] trackNames = new String[list.size()+1];
		trackNames[0] = "Start of Script";
		for(int i = 1; i<list.size()+1; i++){
			trackNames[i] = list.get(i-1).getFileName();
		}
		return trackNames;
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == chooser){ //FileChooser
			 int returnVal = fc.showOpenDialog(EditTrackDialog.this);
	            if (returnVal == JFileChooser.APPROVE_OPTION) {
	                File file = fc.getSelectedFile();
	                text.setText(file.getName());
	            } else {
	                text.setText("");
	            }
		}
		if(e.getSource() == chooseTrack){ //changes relative track of something..
			int relInd = chooseTrack.getSelectedIndex();
			Track relativeTrack = Tracklist.get(relInd);
			currentTrack.setRelativeTo(relativeTrack.getID());
		}
		if(e.getSource() == beginning){
			System.out.println("beginning");
			currentTrack.setStartEnd(true);
		}
		if(e.getSource() == end){
			currentTrack.setStartEnd(false);
			System.out.println("end");
		}
		if(e.getSource() == preview){
			currentTrack.play();
		}
		if(e.getSource() == save){
			track = currentTrack;
		}
		if(e.getSource() == cancel){
			track = backUpTrack;
			this.setVisible(false);
			this.dispose();
		}
	}


}