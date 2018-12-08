package net.thumbtack.school.matrix;

import java.util.*;

public class MatrixNonSimilarRows {
  //  private ArrayList<int[]> matrix;
  private int[][] matrix;
    public MatrixNonSimilarRows(int[][] matrix){
        this.matrix= matrix;
        //this.matrix= new ArrayList<>();
        //this.matrix.addAll(Arrays.asList(matrix));
    }

    public Set<int[]> getNonSimilarRows(){
        ArrayList<int[]> Unique=new ArrayList<int[]>();
        Set<int[]> result = new HashSet<int[]>();

        for (int[] aMatrix : matrix) {
            int[] temp = makeUniqueSet(aMatrix).clone();
            if (!equalsArr(Unique, makeUniqueSet(aMatrix))) {
                Unique.add(temp);
                result.add(aMatrix);
            }
        }

    return result;

    }
   private boolean equalsArr(ArrayList<int[]> Unique,int[] row){
        for (int[] arr: Unique){
            if(Arrays.equals(arr,row))
                return true;
        }
        return false;
   }

    private static int[] makeUniqueSet(int[] values)
    {
        return Arrays.stream(values).distinct().sorted().toArray();
    }

}
