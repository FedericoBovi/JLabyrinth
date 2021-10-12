package jlabyrinth.view;

import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import jlabyrinth.controller.ControllerForModel;
import jlabyrinth.controller.ControllerForView;
import jlabyrinth.view.MainGUI.BoardPanel;


public class View implements IView {

	//---------------------------------------------------------------
	// STATIC FIELDS
	//---------------------------------------------------------------
	
	private static View instance = null;
	
	//---------------------------------------------------------------
	// INSTANCE ATTRIBUTES
	//---------------------------------------------------------------
	protected StartWindow startWindow = null;
	protected NewGameWindow newGameWindow = null;
	protected MainGUI mainGUI = null;
	protected BoardPanel boardPanel = null;
	private JFileChooser jfileChooser=null;

	private View() {
		//TO-DO
	}
	
	//---------------------------------------------------------------
	// INSTANCE METHODS
	//---------------------------------------------------------------
	
	public void openStartWindow() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				if (startWindow == null)
					startWindow = new StartWindow();
				startWindow.setVisible(true);
			}
		});
	}

	public void closeStartWindow() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				if (startWindow != null)
					startWindow.setVisible(false);
			}
		});
	}

	public void openNewGameWindow() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				if (newGameWindow == null)
					newGameWindow = new NewGameWindow();
				newGameWindow.setVisible(true);
			}
		});
	}

	public void closeNewGameWindow() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				if (newGameWindow != null)
					newGameWindow.setVisible(false);
			}
		});
	}

	public void openMainGUI() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				if (mainGUI == null)
					try {
						mainGUI = new MainGUI();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				mainGUI.setVisible(true);
				}
		});
	}
	
	public void updateScoreAndTargetLabel(int score1,int score2, String target) throws IOException {
		this.mainGUI.updateScoreAndTargetLabel(score1, score2, target);
	}
	
	public String openFile() {
		String file = null;
		if (this.jfileChooser == null)
			this.jfileChooser = new JFileChooser();
		int returnVal = jfileChooser.showOpenDialog(startWindow);
		if (returnVal == JFileChooser.APPROVE_OPTION)
			file = jfileChooser.getSelectedFile().getAbsolutePath();
		return file;
	}

	public String saveFile() {
		String file = null;
		if (this.jfileChooser == null)
			this.jfileChooser = new JFileChooser();
		int returnVal = jfileChooser.showSaveDialog(startWindow);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			if (jfileChooser.getSelectedFile().exists()) {
				Object[] options = {"Yes", "No"};
				int n = JOptionPane.showOptionDialog(jfileChooser,
							"The selected file already exists.\n" +
							"Do you want to overwrite it?",
							"Warning",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE,
							null,
							options,
							options[1]);
				if (n == JOptionPane.YES_OPTION)
					file = jfileChooser.getSelectedFile().getAbsolutePath();
			}
			else
				file = jfileChooser.getSelectedFile().getAbsolutePath();
		}
		return file;
	}	
	
	public void showExitMessage() {
		int n = JOptionPane.showConfirmDialog(this.mainGUI,
				"Would you like to save this match?",
				"Confirm Dialog",
				JOptionPane.YES_NO_CANCEL_OPTION);
		if (n == JOptionPane.YES_OPTION)
			ControllerForModel.getInstance().saveAndExit();
		else if (n == JOptionPane.NO_OPTION)
			ControllerForModel.getInstance().exit();		
	}
	
	public void showCannotFindFileMessage(String file) {
		JOptionPane.showMessageDialog(startWindow,
			"Cannot find file: " + file,
			"Input/Output Error",
			JOptionPane.ERROR_MESSAGE);
	}
	
	public void showCannotSaveNowMessage() {
		JOptionPane.showMessageDialog(this.mainGUI,
			"Please complete your turn before leaving!",
			"Saving error",
			JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void showWinnerMessage() throws HeadlessException, IOException{
		if(ControllerForView.getInstance().getScore(0)>ControllerForView.getInstance().getScore(1)){
			JOptionPane.showMessageDialog(this.mainGUI, "   The winner is "+ControllerForView.getInstance().getPlayer1Name(),
					"", JOptionPane.PLAIN_MESSAGE);
		}
		else{
			JOptionPane.showMessageDialog(this.mainGUI, "   The winner is "+ControllerForView.getInstance().getPlayer2Name(),
					"", JOptionPane.PLAIN_MESSAGE);
		}
	}

	public void showCannotReadFileMessage(String file) {
		JOptionPane.showMessageDialog(startWindow,
			"Cannot open file: " + file,
			"Input/Output Error",
			JOptionPane.ERROR_MESSAGE);
	}

	public void showCannotWriteFileMessage(String file) {
		JOptionPane.showMessageDialog(startWindow,
			"Cannot write file: " + file,
			"Input/Output Error",
			JOptionPane.ERROR_MESSAGE);
	}


	
	public static IView getInstance(){
		if(instance == null)
			instance = new View();
		return instance;
	}
		
	
	
}
