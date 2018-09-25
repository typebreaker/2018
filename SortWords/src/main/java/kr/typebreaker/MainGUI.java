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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import main.java.kr.typebreaker.common.Utils;

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
	private JTextArea wordsCountText;
	
	private Map<String, Integer> resultMap = null;
	
	public MainGUI() {
		setBounds(100, 100, 300, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		init();
		
		setVisible(true);
	}
	
	private void init() {
		resultMap = new HashMap<>();
		mainContainer = this.getContentPane();
		insertPanel = new JPanel();
//		processPanel = new JPanel();
		filePathText = new JTextField(15);
		upLoadBtn = new JButton("파일생성");
		insertBtn = new JButton("작업");
		clearBtn = new JButton("초기화");
		wordsCountText = new JTextArea("총 단어수 : "+resultMap.size()+"개 입니다.");
		
		insertBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String result = Utils.fileRead(filePathText.getText());
				resultMap.putAll(Utils.parsingToHashMap(result)); 
				wordsCountText.setText("총 단어수 : "+resultMap.size()+"개 입니다.");
			}
		});
		
		clearBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				resultMap.clear();
				wordsCountText.setText("총 단어수 : "+resultMap.size()+"개 입니다.");
			}
		});
		
		upLoadBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Utils.makeFile(resultMap, "D:/result.txt");
				wordsCountText.setText("파일 생성이 완료되었습니다.");
			}
		});
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
		insertPanel.add(wordsCountText);
		insertPanel.add(clearBtn);
		insertPanel.add(upLoadBtn);
		mainContainer.add(insertPanel);
//		mainContainer.add(processPanel);
	}

}
