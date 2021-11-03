import java.util.Random;
//import javax.lang.model.util.ElementScanner14;

public class PeakFinder {

    /**
     * generate random 2D array
     */ 

    public int[][] random2dArray(int n, int m){

        int[][] a = new int[n][];
        Random r = new Random();
        for(int i = 0; i < n; i++){
            a[i] = new int[m];
            for (int j = 0; j < a[i].length; j++){
                a[i][j] = r.nextInt(20);
            }
        }
        return a;   
    }


    static public void print2d(int[][] a){
        for (int[] i: a){
            for (int item: i){
                System.out.print(item+"\t ");
            }
            System.out.println("");        
        }
    }


    /**
     * n = number of rows,
     * m = number of columns
     * rowOrcol -> eger 0 ise sadece satirlari(n) bolecek
     *          eger 1 ise sadece sutunlari(m) bolecek
     *          eger 2 ise bir iterasyonda satirlari, diger iterasyonda sutunlari bolecek
     *              (m ve n degerlerinden buyuk olani bolebilirsiniz, 
     *              bu m ve n degerleri arasaindaki fark fazla oldugunda daha mantikli.)
     */ 
    public int[] peakFinder2D(int[][] a, int n, int m, int rowOrcol){
     
        if(rowOrcol == 0)
           return peakFinder2D_0(a,n,m);//method java58
        else if(rowOrcol == 1)
           return peakFinder2D_1(a,n,m);//method java92
        else if(rowOrcol == 2)
          return peakFinder2D_2(a,n,m);//method java126
        else
          return null;
    }



        // if rowOrcol == 0 
        public int peakFinder2D_0(int[][] a, int n, int m){

            int[] indx = new int[2];
            int startR = 0, endR = n, startC = 0, endC = m; 
            int mid;
            while(startR >= 0 && endR <= n ){
                mid = (startR+endR)/2;
                int max = a[mid][startC];
                int imax = 0;
                for(int i = startC; i < endC; i++){
                    if(max<a[mid][i]){
                        max = a[mid][i];
                        imax = i;
                    }
                } 
              
                if(a[mid][imax] < a[mid+1][imax]){
                    startR = mid+1; 
                }else if(a[mid][imax] < a[mid-1][imax]){
                    endR = mid-1;
                }else {
                    
                    return a[mid][imax];
                }
            }
            return a[mid][imax];
        }
 




        //if rowOrcol == 1
        public int[] peakFinder2D_1(int[][] a,int n,int m){

            int[] indx = new int[2];
            int startR = 0, endR = n, startC = 0, endC = m; 
            int mid;
            while(startC >= 0 && endC <= m ){
                mid = (startC+endC)/2;
                int max = a[startR][mid];
                int imax = 0;
                for(int i = startR; i < endR; i++){
                    if(max<a[i][mid]){
                        max = a[i][mid];
                        imax = i;
                    }
                } 
              
                if(a[imax][mid] < a[imax][mid+1]){
                    startC = mid+1; 
                }else if(a[imax][mid] < a[imax][mid-1]){
                    endC = mid-1;
                }else {
                    indx[0] = imax;
                    indx[1] = mid;
                    return indx;
                }
            }
            return indx;

        }




        //if rowOrcol == 2
        public int[] peakFinder2D_2(int[][] a, int n, int m){

            if(m <= n)
             return peakFinder2D_0(a,n,m);
            else if(m > n)
             return peakFinder2D_1(a,n,m);
            else
             return null;
        }
      
  


    
    public static void main(String[] args){

        PeakFinder pf = new PeakFinder();
        int m = 4, n = 4, rowOrcol = 0;
        int[][] a = pf.random2dArray(n,m);
        long t1 = System.nanoTime();
        pf.peakFinder2D_0(a, n, m);
        long t2 = System.nanoTime();
        
        print2d(a);

        System.out.printf("\n\n\n%d, %d, %d, %d", n,m, rowOrcol, t2-t1);
    }
}