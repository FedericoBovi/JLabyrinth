package jlabyrinth.view;

import java.awt.HeadlessException;
import java.io.IOException;

public interface IView {

	public void openMainGUI();
	
	public void openStartWindow();
	
	public void closeStartWindow();
	
	public void openNewGameWindow();
	
	public void closeNewGameWindow();
	
	public void updateScoreAndTargetLabel(int score1,int score2, String target) throws IOException;

	public String openFile();
	
	public String saveFile();
	
	public void showExitMessage();
	
	public void showCannotSaveNowMessage();
	
	public void showWinnerMessage() throws HeadlessException, IOException;
	
	public void showCannotFindFileMessage(String file);
	
	public void showCannotReadFileMessage(String file);
	
	public void showCannotWriteFileMessage(String file);
	
}