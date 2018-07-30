package main.java.kr.typebreaker;

import java.awt.Container;
import java.awt.TextField;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MainGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private DropTarget dropTarget;
	private Container mainContainer;
	private JPanel insertPanel;
	private JTextField filePathText;
	private JButton insertBtn;
	
//	private JPanel processPanel;
	private JButton clearBtn;
	private JButton upLoadBtn;
	private JTextArea wordsCount;
	
	
	public MainGUI() {
		setBounds(100, 100, 300, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		init();
		
		setVisible(true);
	}
	
	private void init() {
				
		mainContainer = this.getContentPane();
		insertPanel = new JPanel();
//		processPanel = new JPanel();
		filePathText = new JTextField(10);
		upLoadBtn = new JButton("파일생성");
		insertBtn = new JButton("작업");
		clearBtn = new JButton("초기화");
		
		dropTarget = new DropTarget(mainContainer, new DropTargetListener() {
			
			@Override
			public void dropActionChanged(DropTargetDragEvent dtde) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void drop(DropTargetDropEvent dtde) {
				// TODO Auto-generated method stub
				dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
				try {
					List list = (List) dtde.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
					File file = (File) list.get(0);
					filePathText.setText(file.getAbsolutePath());
				} catch (UnsupportedFlavorException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			@Override
			public void dragOver(DropTargetDragEvent dtde) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void dragExit(DropTargetEvent dte) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void dragEnter(DropTargetDragEvent dtde) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		insertPanel.add(filePathText);
		insertPanel.add(insertBtn);
		
		insertPanel.add(clearBtn);
		insertPanel.add(upLoadBtn);
		mainContainer.add(insertPanel);
//		mainContainer.add(processPanel);
	}

}
