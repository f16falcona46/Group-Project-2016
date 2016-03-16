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

		menuFile=new JMenu("File");

		itemOpen=new JMenuItem("Open");
		itemOpen.addActionListener(this);
		menuFile.add(itemOpen);
		itemNew=new JMenuItem("New");
		itemNew.addActionListener(this);
		menuFile.add(itemNew);
		itemSaveAs=new JMenuItem("Save As");
		itemSaveAs.addActionListener(this);
		menuFile.add(itemSaveAs); 
		itemQuite=new JMenuItem("Quit");
		itemQuite.addActionListener(this);
		menuFile.add(itemQuite);

		menuRecording=new JMenu("Recording");

		itemNewTrack=new JMenuItem("New Track");
		itemNewTrack.addActionListener(this);
		menuRecording.add(itemNewTrack);
		itemCreateRecording=new JMenuItem("Create Recording");
		itemCreateRecording.addActionListener(this);
		menuRecording.add(itemCreateRecording);
		itemPreview=new JMenuItem("Preview");
		itemPreview.addActionListener(this);
		menuRecording.add(itemPreview);
		itemExport=new JMenuItem("Export");
		itemExport.addActionListener(this);
		menuRecording.add(itemExport);
		
		MenuBar.add(menuFile);
		MenuBar.add(menuRecording);
		
		
		
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
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showDialog(itemOpen, "Open");
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
			}
			itemRecording.setEnabled(true);
			itemSaveAs.setEnabled(true);
		}
		if(e.getSource() == itemSaveAs0){
			if(e.getSource() == itemNew){
				JFileChooser fc = new JFileChooser();
				int returnVal = fc.showDialog(itemOpen, "Open");
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					tracklist.setFileName(file.getName());
					tracklist.save(file);
				}
		}
		if(e.getSource() == itemQuite){
			System.exit(0);
		}
		if(e.getSource() == itemNewTrack){
			Track newtrack = new Track(track.RECORD, tracklist);
			EditTrackDialog diag = new EditTRackdialog(newtrack);
		}
		if(e.getSource() == itemCreateRecording){
			Track newtrack = new Track(track.RECORD, tracklist);
		}
		if(e.getSource() == itemPreview){
			tracklist.play();
		}
		if(e.getSource() == itemExport){
			if(e.getSource() == itemNew){
				JFileChooser fc = new JFileChooser();
				int returnVal = fc.showDialog(itemOpen, "Open");
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					tracklist.setFileName(file.getName());
					tracklist.export(file);
				}
		}
	}
}
