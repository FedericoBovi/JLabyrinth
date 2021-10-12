package jlabyrinth.model;

import ExampleImages.AppResources;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PieceL extends Piece{
	
	PieceL(){
		//System.out.println("Pezzo L creato");
		this.shapeArray = new int[3][3];
		this.pieceName = "PieceL";
		this.isTarget = false;
		this.score=0;
		this.rotationCounter=0;
		this.file= new File("src/"+IMG_STRING+"PieceL.jpg"); 
		try {
			this.img= ImageIO.read(AppResources.class.getResource("PieceL"+IMG_STRING+".jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (int i = 0; i<this.shapeArray.length; i++)
			for (int j = 0; j< this.shapeArray.length; j++)
				shapeArray[i][j]= this.CLOSED_WAY;
		
		shapeArray[2][1] = this.OPEN_WAY;
		shapeArray[1][1] = this.OPEN_WAY;
		shapeArray[1][2] = this.OPEN_WAY;
		
	} 
}//end class
