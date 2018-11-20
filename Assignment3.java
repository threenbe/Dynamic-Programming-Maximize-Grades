import java.util.ArrayList;

/**
 * Header
 * <Raiyan Chowdhury>
 * <rac4444>
 * <16495> (Your section id)
 * Please fill inside < >  and do not remove < >.
 */

public class Assignment3 {

	private int totalClasses;
	private int maxGrade;
	private static int [][]gradearray;
	public Assignment3() {
		this.totalClasses = 0;
		this.maxGrade = 0;
		this.gradearray = null;
	}

	public void initialize(int totalClasses, int maxGrade,  int[][] gradearray) {//These varaibles are set from TestAssignment3.java
		this.totalClasses = totalClasses;
		this.maxGrade = maxGrade;
		this.gradearray = gradearray;
	}


	public result[] compute(int totalHours, result[] studentoutput) {
		//Write your Code For Assignment 3 Here.
		int[][] gradeOutput = new int[totalClasses][10];
		int[][] hoursSpent = new int[totalClasses][10];
		
		//spend h hours on class 0, trivial enough
		//max 9 hours for one class
		for (int h = 0; h < 10; h++) {
			gradeOutput[0][h] = gradearray[0][h];
			hoursSpent[0][h] = h;
		}
		
		//fill out the rest, from classes 1 through totalClasses
		for (int i = 1; i < totalClasses; i++) {
			for (int h = 0; h < totalHours; h++) {
				int maxGrade = 0;
				//spend k hours on this exam, h hours total
				//h-k hours spent on previous exams
				for (int k = 0; (k < h) && (k < 10); k++) {
					int newGrade = gradeOutput[i-1][h-k] + gradearray[i][k];
					if (newGrade > maxGrade) {
						maxGrade = gradeOutput[i][h] = newGrade;
						hoursSpent[i][h] = k;
					}
				}
			}
		}

		int h = totalHours;
		for (int i = totalClasses - 1; i >= 0; i--) {
			studentoutput[i].setHour(hoursSpent[i][h]);
			studentoutput[i].setGrade(gradearray[i][hoursSpent[i][h]]);
		}
		return studentoutput;
	}

}


/*  WRITE YOUR REPORT INSIDE THIS SECTION AS COMMENTED CODE
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */
