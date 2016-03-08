import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

public class MainMenuBar implements ActionListener{
	JMenuBar MenuBar;
	JMenu menuFile, menuRecording;
	JMenuItem itemOpen, itemNew, itemSaveAs, itemQuite;
	JMenuItem itemNewTrack, itemCreateRecording, itemPreview, itemExport;
	MainMenuBar(TrackList trackList){
		MenuBar=new JMenuBar();

		menuFile=new JMenu("menuFile");

		itemOpen=new JMenuItem("itemOpen");
		itemOpen.addActionListener(this);
		menuFile.add(itemOpen);
		itemNew=new JMenuItem("itemNew");
		itemNew.addActionListener(this);
		menuFile.add(itemNew);
		itemSaveAs=new JMenuItem("itemSaveAs");
		itemSaveAs.addActionListener(this);
		menuFile.add(itemSaveAs);
		itemQuite=new JMenuItem("itemQuite");
		itemQuite.addActionListener(this);
		menuFile.add(itemQuite);

		menuRecording=new JMenu("menuRecording");

		itemNewTrack=new JMenuItem("itemNewTrack");
		itemNewTrack.addActionListener(this);
		menuRecording.add(itemNewTrack);
		itemCreateRecording=new JMenuItem("itemCreateRecording");
		itemCreateRecording.addActionListener(this);
		menuRecording.add(itemCreateRecording);
		itemPreview=new JMenuItem("itemPreview");
		itemPreview.addActionListener(this);
		menuRecording.add(itemPreview);
		itemExport=new JMenuItem("itemExport");
		itemExport.addActionListener(this);
		menuRecording.add(itemExport);		
	}
	public void disableButtons(){
		itemSaveAs.setEnabled(false);
		itemNewTrack.setEnabled(false);
		itemCreateRecording.setEnabled(false);
		itemPreview.setEnabled(false);
		itemExport.setEnabled(false);
	}
	public void enableButtons(){
		itemSaveAs.setEnabled(true);
		itemNewTrack.setEnabled(true);
		itemCreateRecording.setEnabled(true);
		itemPreview.setEnabled(true);
		itemExport.setEnabled(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == itemOpen){
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showDialog(itemOpen, "Open");
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				tracklist.setFileName(file.getName());
			}
			itemSaveAs.setEnabled(true);
			itemNewTrack.setEnabled(true);
		}
		if(e.getSource() == itemNew){
			JOptionPane.showMessageDialog(null, "Selected Item: " + e.getActionCommand());
		}

	}

}
