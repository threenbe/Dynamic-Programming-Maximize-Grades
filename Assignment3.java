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
		int[][] gradeOutput = new int[totalClasses][totalHours+1];
		int[][] hoursSpent = new int[totalClasses][totalHours+1];
		
		//spend h hours on class 0, trivial enough
		//max 9 hours for one class
		for (int h = 0; h < 10; h++) {
			gradeOutput[0][h] = gradearray[0][h];
			hoursSpent[0][h] = h;
		}
		
		//fill out the rest, from classes 1 through totalClasses
		for (int i = 1; i < totalClasses; i++) {
			for (int h = 0; h <= totalHours; h++) {
				int maxGrade = 0;
				//spend k hours on this class, h hours total
				//h-k hours spent on previous classes
				for (int k = 0; k <= h; k++) {
					if (k < 10) {
						int newGrade = gradeOutput[i-1][h-k] + gradearray[i][k];
						if (newGrade > maxGrade) {
							//can get a better grade, so update hours
							//spent studying and total grade
							maxGrade = gradeOutput[i][h] = newGrade;
							hoursSpent[i][h] = k;
						}
					}
				}
			}
		}

		//we now have optimal solution to entire problem,
		//use to fill out output array
		//work backwards from optimal solution
		int h = totalHours;
		for (int i = totalClasses - 1; i >= 0; i--) {
			int hours = hoursSpent[i][h];
			studentoutput[i].setHour(hours);
			studentoutput[i].setGrade(gradearray[i][hours]);
			h -= hours;
			
		}
		return studentoutput;
	}

}


/*  WRITE YOUR REPORT INSIDE THIS SECTION AS COMMENTED CODE
 * 
 * 	The problem has optimal substructure if you look at the solutions to the
 * 	subproblems in which you spend h hours (h <= H) on the first i classes.
 * 	If we know the max grade that can be gained by distributing h hours over 
 * 	the first i classes, then we can use this to solve for the "next" max grade
 * 	by either spending more hours on these first i classes, or by starting
 * 	to spend time on the next (i+1)th class, seeing whether or not the additional
 * 	time investment with either method yields a better grade. Eventually, building
 * 	upon these (i,h) optimal sub-solutions will give us the optimal solution for
 * 	(n, H). 
 * 	The optimal solution for spending h hours on the first i classes can be found
 * 	recursively by looking at the hours already spent studying for the max grade
 * 	on the first (i-1) classes (solved previously), and then adding the grade
 * 	from the gradearray for spending some k hours on the ith class in addition
 * 	to the hours spent on the previous (for a total of h hours). This should 
 * 	be done for different values of h, recording the grade for each one. Whenever
 * 	a new max grade is achieve by studying more, the k hours spent on the ith class
 * 	to achieve this grade should be recorded/updated and mapped to the relevant (i,h) 
 * 	values.
 * 	In order to achieve an optimal solution, the above code is written as follows.
 * 	The maximum total grade achieved spending h hours on the first i classes is stored
 * 	in gradeOutput[i][h], and the hours spent on the ith class specifically to get this
 * 	grade is stored in hoursSpent[i][h]. First, the (0,h) values are filled in, as it
 * 	is trivial to find the max grade achieved by studying h hours for a single class.
 * 	After this, we use the solutions found for the (0,h) case to fill in the rest.
 * 	We go in order by row (dealing with 1 class, then 2, then 3...). For each subproblem,
 * 	we spend some arbitrary k hours on it (where k <= h, where h is the total hours for every
 * 	class; also k < 10), meaning that the grade achieved is equal to gradearray[i][k] + 
 * 	gradeOutput[i-1][h-k]. For each value of k, we check to see if the newfound grade
 * 	is higher than the previous; if so, then the values in gradeOutput[i][h] and hoursSpent[i][h]
 * 	are updated appropriately. There is no need to average out the sum of the grades, as
 *	it would not affect the comparisons made.
 *  	Once every value has been filled out, we examine the optimal solution to our complete problem,
 *  	which is in hoursSpent[n][H], where n = totalClasses-1 and H = totalHours. This is the time
 *  	spent on class n in order to achieve the maximum possible grade average overall. This means that,
 *  	for the remaining n-1 classes, we spend H-k hours, so we check hoursSpent[n-1][H-k] next, and so on.
 *  	We fill the studentoutput array with the appropriate hours and grades going by this.
 *  	We can verify that the solution found via the above algorithm is indeed optimal by considering
 *  	the contrary. If our solution is not optimal, then some other solution must exist that is, 
 *  	meaning that this other solution distributes hours in such a way that a better grade is 
 *  	attained. This means that, in our algorithm, when we determine the maximum grade possible 
 * 	for every possible h hours spent on the first i classes, there must be some higher grade
 * 	that we overlooked. This cannot be the case, however, as the iterative algorithm
 * 	considers every possible number of hours that can be spent on the first i classes,
 * 	as well as for each individual case concerning the number of hours spent on class i
 * 	specifically, for every class i. This means that no possible sum of grades could
 *  	have been overlooked, contradicting the idea that we could have missed a potentially
 * 	higher grade.
 * 
 */
