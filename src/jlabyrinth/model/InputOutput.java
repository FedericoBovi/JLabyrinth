package jlabyrinth.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class InputOutput {
	
	public static void parseLineAndUpdateModel(String[] sSplit, IModel model) throws NumberFormatException, IOException{
		if(sSplit[0].trim().toLowerCase().equals("boarddimentions")){
			model.setBoardDimentions(Integer.parseInt(sSplit[1].trim()));
			System.out.println("boarddimentions");
		}	
		else if(sSplit[0].trim().toLowerCase().equals("setimages")){
			model.setImagesDirectory(Integer.parseInt(sSplit[1].trim()));
			System.out.println("setimages");
		}	
		else if(sSplit[0].trim().toLowerCase().equals("boardarray[0][0]"))
			model.selectBAcell((sSplit[1].trim()), 0, 0);	
		else if(sSplit[0].trim().toLowerCase().equals("rotation[0][0]"))
			model.getSpecificPiece(0, 0).rotate(Integer.parseInt(sSplit[1].trim()));
		else if(sSplit[0].trim().toLowerCase().equals("boardarray[0][1]"))
			model.selectBAcell((sSplit[1].trim()), 0, 1);
		else if(sSplit[0].trim().toLowerCase().equals("rotation[0][1]"))
			model.getSpecificPiece(0, 1).rotate(Integer.parseInt(sSplit[1].trim()));
		else if(sSplit[0].trim().toLowerCase().equals("boardarray[0][2]"))
			model.selectBAcell((sSplit[1].trim()), 0, 2);
		else if(sSplit[0].trim().toLowerCase().equals("rotation[0][2]"))
			model.getSpecificPiece(0, 2).rotate(Integer.parseInt(sSplit[1].trim()));
		else if(sSplit[0].trim().toLowerCase().equals("boardarray[0][3]"))
			model.selectBAcell((sSplit[1].trim()), 0, 3);
		else if(sSplit[0].trim().toLowerCase().equals("rotation[0][3]"))
			model.getSpecificPiece(0, 3).rotate(Integer.parseInt(sSplit[1].trim()));
		else if(sSplit[0].trim().toLowerCase().equals("boardarray[0][4]"))
			model.selectBAcell((sSplit[1].trim()), 0, 4);
		else if(sSplit[0].trim().toLowerCase().equals("rotation[0][4]"))
			model.getSpecificPiece(0, 4).rotate(Integer.parseInt(sSplit[1].trim()));
		else if(sSplit[0].trim().toLowerCase().equals("boardarray[1][0]"))
			model.selectBAcell((sSplit[1].trim()), 1, 0);
		else if(sSplit[0].trim().toLowerCase().equals("rotation[1][0]"))
			model.getSpecificPiece(1, 0).rotate(Integer.parseInt(sSplit[1].trim()));
		else if(sSplit[0].trim().toLowerCase().equals("boardarray[1][1]"))
			model.selectBAcell((sSplit[1].trim()), 1, 1);
		else if(sSplit[0].trim().toLowerCase().equals("rotation[1][1]"))
			model.getSpecificPiece(1, 1).rotate(Integer.parseInt(sSplit[1].trim()));
		else if(sSplit[0].trim().toLowerCase().equals("boardarray[1][2]"))
			model.selectBAcell((sSplit[1].trim()), 1, 2);
		else if(sSplit[0].trim().toLowerCase().equals("rotation[1][2]"))
			model.getSpecificPiece(1, 2).rotate(Integer.parseInt(sSplit[1].trim()));
		else if(sSplit[0].trim().toLowerCase().equals("boardarray[1][3]"))
			model.selectBAcell((sSplit[1].trim()), 1, 3);
		else if(sSplit[0].trim().toLowerCase().equals("rotation[1][3]"))
			model.getSpecificPiece(1, 3).rotate(Integer.parseInt(sSplit[1].trim()));
		else if(sSplit[0].trim().toLowerCase().equals("boardarray[1][4]"))
			model.selectBAcell((sSplit[1].trim()), 1, 4);
		else if(sSplit[0].trim().toLowerCase().equals("rotation[1][4]"))
			model.getSpecificPiece(1, 4).rotate(Integer.parseInt(sSplit[1].trim()));
		else if(sSplit[0].trim().toLowerCase().equals("boardarray[2][0]"))
			model.selectBAcell((sSplit[1].trim()), 2, 0);
		else if(sSplit[0].trim().toLowerCase().equals("rotation[2][0]"))
			model.getSpecificPiece(2, 0).rotate(Integer.parseInt(sSplit[1].trim()));
		else if(sSplit[0].trim().toLowerCase().equals("boardarray[2][1]"))
			model.selectBAcell((sSplit[1].trim()), 2, 1);
		else if(sSplit[0].trim().toLowerCase().equals("rotation[2][1]"))
			model.getSpecificPiece(2, 1).rotate(Integer.parseInt(sSplit[1].trim()));
		else if(sSplit[0].trim().toLowerCase().equals("boardarray[2][2]"))
			model.selectBAcell((sSplit[1].trim()), 2, 2);
		else if(sSplit[0].trim().toLowerCase().equals("rotation[2][2]"))
			model.getSpecificPiece(2, 2).rotate(Integer.parseInt(sSplit[1].trim()));
		else if(sSplit[0].trim().toLowerCase().equals("boardarray[2][3]"))
			model.selectBAcell((sSplit[1].trim()), 2, 3);
		else if(sSplit[0].trim().toLowerCase().equals("rotation[2][3]"))
			model.getSpecificPiece(2, 3).rotate(Integer.parseInt(sSplit[1].trim()));
		else if(sSplit[0].trim().toLowerCase().equals("boardarray[2][4]"))
			model.selectBAcell((sSplit[1].trim()), 2, 4);
		else if(sSplit[0].trim().toLowerCase().equals("rotation[2][4]"))
			model.getSpecificPiece(2, 4).rotate(Integer.parseInt(sSplit[1].trim()));
		else if(sSplit[0].trim().toLowerCase().equals("boardarray[3][0]"))
			model.selectBAcell((sSplit[1].trim()), 3, 0);
		else if(sSplit[0].trim().toLowerCase().equals("rotation[3][0]"))
			model.getSpecificPiece(3, 0).rotate(Integer.parseInt(sSplit[1].trim()));
		else if(sSplit[0].trim().toLowerCase().equals("boardarray[3][1]"))
			model.selectBAcell((sSplit[1].trim()), 3, 1);
		else if(sSplit[0].trim().toLowerCase().equals("rotation[3][1]"))
			model.getSpecificPiece(3, 1).rotate(Integer.parseInt(sSplit[1].trim()));
		else if(sSplit[0].trim().toLowerCase().equals("boardarray[3][2]"))
			model.selectBAcell((sSplit[1].trim()), 3, 2);
		else if(sSplit[0].trim().toLowerCase().equals("rotation[3][2]"))
			model.getSpecificPiece(3, 2).rotate(Integer.parseInt(sSplit[1].trim()));
		else if(sSplit[0].trim().toLowerCase().equals("boardarray[3][3]"))
			model.selectBAcell((sSplit[1].trim()), 3, 3);
		else if(sSplit[0].trim().toLowerCase().equals("rotation[3][3]"))
			model.getSpecificPiece(3, 3).rotate(Integer.parseInt(sSplit[1].trim()));
		else if(sSplit[0].trim().toLowerCase().equals("boardarray[3][4]"))
			model.selectBAcell((sSplit[1].trim()), 3, 4);
		else if(sSplit[0].trim().toLowerCase().equals("rotation[3][4]"))
			model.getSpecificPiece(3, 4).rotate(Integer.parseInt(sSplit[1].trim()));
		else if(sSplit[0].trim().toLowerCase().equals("boardarray[4][0]"))
			model.selectBAcell((sSplit[1].trim()), 4, 0);
		else if(sSplit[0].trim().toLowerCase().equals("rotation[4][0]"))
			model.getSpecificPiece(4, 0).rotate(Integer.parseInt(sSplit[1].trim()));
		else if(sSplit[0].trim().toLowerCase().equals("boardarray[4][1]"))
			model.selectBAcell((sSplit[1].trim()), 4, 1);
		else if(sSplit[0].trim().toLowerCase().equals("rotation[4][1]"))
			model.getSpecificPiece(4, 1).rotate(Integer.parseInt(sSplit[1].trim()));
		else if(sSplit[0].trim().toLowerCase().equals("boardarray[4][2]"))
			model.selectBAcell((sSplit[1].trim()), 4, 2);
		else if(sSplit[0].trim().toLowerCase().equals("rotation[4][2]"))
			model.getSpecificPiece(4, 2).rotate(Integer.parseInt(sSplit[1].trim()));
		else if(sSplit[0].trim().toLowerCase().equals("boardarray[4][3]"))
			model.selectBAcell((sSplit[1].trim()), 4, 3);
		else if(sSplit[0].trim().toLowerCase().equals("rotation[4][3]"))
			model.getSpecificPiece(4, 3).rotate(Integer.parseInt(sSplit[1].trim()));
		else if(sSplit[0].trim().toLowerCase().equals("boardarray[4][4]"))
			model.selectBAcell((sSplit[1].trim()), 4, 4);
		else if(sSplit[0].trim().toLowerCase().equals("rotation[4][4]"))
			model.getSpecificPiece(4, 4).rotate(Integer.parseInt(sSplit[1].trim()));
		if(model.getBoardDimentions()==7){
			if(sSplit[0].trim().toLowerCase().equals("boardarray[0][5]"))
				model.selectBAcell((sSplit[1].trim()), 0, 5);
			else if(sSplit[0].trim().toLowerCase().equals("rotation[0][5]"))
				model.getSpecificPiece(0, 5).rotate(Integer.parseInt(sSplit[1].trim()));
			else if(sSplit[0].trim().toLowerCase().equals("boardarray[0][6]"))
				model.selectBAcell((sSplit[1].trim()), 0, 6);
			else if(sSplit[0].trim().toLowerCase().equals("rotation[0][6]"))
				model.getSpecificPiece(0, 6).rotate(Integer.parseInt(sSplit[1].trim()));
			else if(sSplit[0].trim().toLowerCase().equals("boardarray[1][5]"))
				model.selectBAcell((sSplit[1].trim()), 1, 5);
			else if(sSplit[0].trim().toLowerCase().equals("rotation[1][5]"))
				model.getSpecificPiece(1, 5).rotate(Integer.parseInt(sSplit[1].trim()));
			else if(sSplit[0].trim().toLowerCase().equals("boardarray[1][6]"))
				model.selectBAcell((sSplit[1].trim()), 1, 6);
			else if(sSplit[0].trim().toLowerCase().equals("rotation[1][6]"))
				model.getSpecificPiece(1, 6).rotate(Integer.parseInt(sSplit[1].trim()));
			else if(sSplit[0].trim().toLowerCase().equals("boardarray[2][5]"))
				model.selectBAcell((sSplit[1].trim()), 2, 5);
			else if(sSplit[0].trim().toLowerCase().equals("rotation[2][5]"))
				model.getSpecificPiece(2, 5).rotate(Integer.parseInt(sSplit[1].trim()));
			else if(sSplit[0].trim().toLowerCase().equals("boardarray[2][6]"))
				model.selectBAcell((sSplit[1].trim()), 2, 6);
			else if(sSplit[0].trim().toLowerCase().equals("rotation[2][6]"))
				model.getSpecificPiece(2, 6).rotate(Integer.parseInt(sSplit[1].trim()));
			else if(sSplit[0].trim().toLowerCase().equals("boardarray[3][5]"))
				model.selectBAcell((sSplit[1].trim()), 3, 5);
			else if(sSplit[0].trim().toLowerCase().equals("rotation[3][5]"))
				model.getSpecificPiece(3, 5).rotate(Integer.parseInt(sSplit[1].trim()));
			else if(sSplit[0].trim().toLowerCase().equals("boardarray[3][6]"))
				model.selectBAcell((sSplit[1].trim()), 3, 6);
			else if(sSplit[0].trim().toLowerCase().equals("rotation[3][6]"))
				model.getSpecificPiece(3, 6).rotate(Integer.parseInt(sSplit[1].trim()));
			else if(sSplit[0].trim().toLowerCase().equals("boardarray[4][5]"))
				model.selectBAcell((sSplit[1].trim()), 4, 5);
			else if(sSplit[0].trim().toLowerCase().equals("rotation[4][5]"))
				model.getSpecificPiece(4, 5).rotate(Integer.parseInt(sSplit[1].trim()));
			else if(sSplit[0].trim().toLowerCase().equals("boardarray[4][6]"))
				model.selectBAcell((sSplit[1].trim()), 4, 6);
			else if(sSplit[0].trim().toLowerCase().equals("rotation[4][6]"))
				model.getSpecificPiece(4, 6).rotate(Integer.parseInt(sSplit[1].trim()));
			else if(sSplit[0].trim().toLowerCase().equals("boardarray[5][5]"))
				model.selectBAcell((sSplit[1].trim()), 5, 5);
			else if(sSplit[0].trim().toLowerCase().equals("rotation[5][5]"))
				model.getSpecificPiece(5, 5).rotate(Integer.parseInt(sSplit[1].trim()));
			else if(sSplit[0].trim().toLowerCase().equals("boardarray[5][6]"))
				model.selectBAcell((sSplit[1].trim()), 5, 6);
			else if(sSplit[0].trim().toLowerCase().equals("rotation[5][6]"))
				model.getSpecificPiece(5, 6).rotate(Integer.parseInt(sSplit[1].trim()));
			else if(sSplit[0].trim().toLowerCase().equals("boardarray[6][5]"))
				model.selectBAcell((sSplit[1].trim()), 6, 5);
			else if(sSplit[0].trim().toLowerCase().equals("rotation[6][5]"))
				model.getSpecificPiece(6, 5).rotate(Integer.parseInt(sSplit[1].trim()));
			else if(sSplit[0].trim().toLowerCase().equals("boardarray[6][6]"))
				model.selectBAcell((sSplit[1].trim()), 6, 6);
			else if(sSplit[0].trim().toLowerCase().equals("rotation[6][6]"))
				model.getSpecificPiece(6, 6).rotate(Integer.parseInt(sSplit[1].trim()));
			else if(sSplit[0].trim().toLowerCase().equals("boardarray[5][0]"))
				model.selectBAcell((sSplit[1].trim()), 5, 0);
			else if(sSplit[0].trim().toLowerCase().equals("rotation[5][0]"))
				model.getSpecificPiece(5, 0).rotate(Integer.parseInt(sSplit[1].trim()));
			else if(sSplit[0].trim().toLowerCase().equals("boardarray[5][1]"))
				model.selectBAcell((sSplit[1].trim()), 5, 1);
			else if(sSplit[0].trim().toLowerCase().equals("rotation[5][1]"))
				model.getSpecificPiece(5, 1).rotate(Integer.parseInt(sSplit[1].trim()));
			else if(sSplit[0].trim().toLowerCase().equals("boardarray[5][2]"))
				model.selectBAcell((sSplit[1].trim()), 5, 2);
			else if(sSplit[0].trim().toLowerCase().equals("rotation[5][2]"))
				model.getSpecificPiece(5, 2).rotate(Integer.parseInt(sSplit[1].trim()));
			else if(sSplit[0].trim().toLowerCase().equals("boardarray[5][3]"))
				model.selectBAcell((sSplit[1].trim()), 5, 3);
			else if(sSplit[0].trim().toLowerCase().equals("rotation[5][3]"))
				model.getSpecificPiece(5, 3).rotate(Integer.parseInt(sSplit[1].trim()));
			else if(sSplit[0].trim().toLowerCase().equals("boardarray[5][4]"))
				model.selectBAcell((sSplit[1].trim()), 5, 4);
			else if(sSplit[0].trim().toLowerCase().equals("rotation[5][4]"))
				model.getSpecificPiece(5, 4).rotate(Integer.parseInt(sSplit[1].trim()));
			else if(sSplit[0].trim().toLowerCase().equals("boardarray[6][0]"))
				model.selectBAcell((sSplit[1].trim()), 6, 0);
			else if(sSplit[0].trim().toLowerCase().equals("rotation[6][0]"))
				model.getSpecificPiece(6, 0).rotate(Integer.parseInt(sSplit[1].trim()));
			else if(sSplit[0].trim().toLowerCase().equals("boardarray[6][1]"))
				model.selectBAcell((sSplit[1].trim()), 6, 1);
			else if(sSplit[0].trim().toLowerCase().equals("rotation[6][1]"))
				model.getSpecificPiece(6, 1).rotate(Integer.parseInt(sSplit[1].trim()));
			else if(sSplit[0].trim().toLowerCase().equals("boardarray[6][2]"))
				model.selectBAcell((sSplit[1].trim()), 6, 2);
			else if(sSplit[0].trim().toLowerCase().equals("rotation[6][2]"))
				model.getSpecificPiece(6, 2).rotate(Integer.parseInt(sSplit[1].trim()));
			else if(sSplit[0].trim().toLowerCase().equals("boardarray[6][3]"))
				model.selectBAcell((sSplit[1].trim()), 6, 3);
			else if(sSplit[0].trim().toLowerCase().equals("rotation[6][3]"))
				model.getSpecificPiece(6, 3).rotate(Integer.parseInt(sSplit[1].trim()));
			else if(sSplit[0].trim().toLowerCase().equals("boardarray[6][4]"))
				model.selectBAcell((sSplit[1].trim()), 6, 4);
			else if(sSplit[0].trim().toLowerCase().equals("rotation[6][4]"))
				model.getSpecificPiece(6, 4).rotate(Integer.parseInt(sSplit[1].trim()));
		}
		if(sSplit[0].trim().toLowerCase().equals("p1name")){
			model.setPlayer1Name(sSplit[1].trim());
			System.out.println("P1name settato");
		}	
		else if(sSplit[0].trim().toLowerCase().equals("p2name")){
			model.setPlayer2Name(sSplit[1].trim());
			System.out.println("P2name settato");
		}	
		else if(sSplit[0].trim().toLowerCase().equals("p1score")){
			model.setScore(0, Integer.parseInt(sSplit[1].trim()));
			System.out.println("P1score settato");
		}	
		else if(sSplit[0].trim().toLowerCase().equals("p2score")){
			model.setScore(1, Integer.parseInt(sSplit[1].trim()));
			System.out.println("P2score settato");
		}	
		else if(sSplit[0].trim().toLowerCase().equals("currenttarget")){
			model.setSpecificTarget(sSplit[1].trim());
			System.out.println("CurrentTarget settato");
		}
		else if(sSplit[0].trim().toLowerCase().equals("numbertargets"))
			model.setNumberTargets(Integer.parseInt(sSplit[1].trim()));
		else if(sSplit[0].trim().toLowerCase().equals("targetarray[0]"))
			Target.setTargetOfTargetArray(0, sSplit[1].trim());
		else if(sSplit[0].trim().toLowerCase().equals("targetarray[1]"))
			Target.setTargetOfTargetArray(1, sSplit[1].trim());
		else if(sSplit[0].trim().toLowerCase().equals("targetarray[2]"))
			Target.setTargetOfTargetArray(2, sSplit[1].trim());
		else if(sSplit[0].trim().toLowerCase().equals("targetarray[3]"))
			Target.setTargetOfTargetArray(3, sSplit[1].trim());
		else if(sSplit[0].trim().toLowerCase().equals("targetarray[4]"))
			Target.setTargetOfTargetArray(4, sSplit[1].trim());
		else if(sSplit[0].trim().toLowerCase().equals("targetarray[5]"))
			Target.setTargetOfTargetArray(5, sSplit[1].trim());
		else if(sSplit[0].trim().toLowerCase().equals("targetarray[6]"))
			Target.setTargetOfTargetArray(6, sSplit[1].trim());
		else if(sSplit[0].trim().toLowerCase().equals("turn")){
			model.setTurn(Integer.parseInt(sSplit[1].trim()));
			System.out.println("Turn settato");
		}
		else if(sSplit[0].trim().toLowerCase().equals("p1positionx")){
			model.setPawnCurrentPosition(0, Integer.parseInt(sSplit[1].trim()));
			System.out.println("P1positionX settato");
		}	
		else if(sSplit[0].trim().toLowerCase().equals("p1positiony")){
			model.setPawnCurrentPosition(1, Integer.parseInt(sSplit[1].trim()));
			System.out.println("P1positionY settato");
		}	
		else if(sSplit[0].trim().toLowerCase().equals("p2positionx")){
			model.setPawnCurrentPosition(2, Integer.parseInt(sSplit[1].trim()));
			System.out.println("P2positionx settato");
		}	
		else if(sSplit[0].trim().toLowerCase().equals("p2positiony")){
			model.setPawnCurrentPosition(3, Integer.parseInt(sSplit[1].trim()));
			System.out.println("P2positionY settato");
		}	
		else if(sSplit[0].trim().toLowerCase().equals("ontarget")){
			model.setOnTarget(Boolean.parseBoolean(sSplit[1].trim()));
			System.out.println("OnTarget settato");
		}	
		else if(sSplit[0].trim().toLowerCase().equals("removingpiece")){
			model.selectRemovingPiece(sSplit[1].trim());
			System.out.println("RemovingPiece settato");
		}	
	}
	
	
	public static void loadModelFromFile(String file, IModel model) throws FileNotFoundException, IOException {
		BufferedReader buffRead = new BufferedReader(new InputStreamReader(new FileInputStream(file), "ISO-8859-1"));
		String s = null;
		while ((s = buffRead.readLine()) != null) {
			if (!s.isEmpty() && !s.startsWith("#") && (s.indexOf("=") > 0))
				parseLineAndUpdateModel(s.split("="), model);
		}

		buffRead.close();
	} // end method loadModelFromFile()
	
	public static void saveModelToFile(String file, IModel model) throws IOException{
		PrintWriter printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"ISO-8859-1")));
		printWriter.print(model.toString());
		printWriter.flush();
		printWriter.close();
	}
}
