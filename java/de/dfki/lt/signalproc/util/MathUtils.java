/**
 * Copyright 2004-2006 DFKI GmbH.
 * All Rights Reserved.  Use is subject to license terms.
 * 
 * Permission is hereby granted, free of charge, to use and distribute
 * this software and its documentation without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of this work, and to
 * permit persons to whom this work is furnished to do so, subject to
 * the following conditions:
 * 
 * 1. The code must retain the above copyright notice, this list of
 *    conditions and the following disclaimer.
 * 2. Any modifications must be clearly marked as such.
 * 3. Original authors' names are not deleted.
 * 4. The authors' names are not used to endorse or promote products
 *    derived from this software without specific prior written
 *    permission.
 *
 * DFKI GMBH AND THE CONTRIBUTORS TO THIS WORK DISCLAIM ALL WARRANTIES WITH
 * REGARD TO THIS SOFTWARE, INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS, IN NO EVENT SHALL DFKI GMBH NOR THE
 * CONTRIBUTORS BE LIABLE FOR ANY SPECIAL, INDIRECT OR CONSEQUENTIAL
 * DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR
 * PROFITS, WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS
 * ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE OR PERFORMANCE OF
 * THIS SOFTWARE.
 */

package de.dfki.lt.signalproc.util;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;


/**
 * @author Marc Schr&ouml;der
 * 
 * An uninstantiable class, containing static utility methods in the Math domain.
 *
 */
public class MathUtils {
    protected static final double PASCAL = 2E-5;
    protected static final double PASCALSQUARE = 4E-10;
    protected static final double LOG10 = Math.log(10);

    public static final double TWOPI = 2*Math.PI;

    public static final int EQUALS = 0;
    public static final int GREATER_THAN = 1;
    public static final int GREATER_THAN_OR_EQUALS = 2;
    public static final int LESS_THAN = 3;
    public static final int LESS_THAN_OR_EQUALS = 4;
    public static final int NOT_EQUALS = 5;

    public static boolean isPowerOfTwo(int N)
    {
        final int maxBits = 32;
        int n=2;
        for (int i=2; i<=maxBits; i++) {
            if (n==N) return true;
            n<<=1;
        }
        return false;
    }

    public static int closestPowerOfTwoAbove(int N)
    {
        return 1<<(int) Math.ceil(Math.log(N)/Math.log(2));
    }

    public static int findNextValleyLocation(double[] data, int startIndex)
    {
        for (int i=startIndex+1; i<data.length; i++) {
            if (data[i-1]<data[i]) return i-1;
        }
        return data.length-1;
    }

    public static int findNextPeakLocation(double[] data, int startIndex)
    {
        for (int i=startIndex+1; i<data.length; i++) {
            if (data[i-1]>data[i]) return i-1;
        }
        return data.length-1;
    }

    /**
     * Find the maximum of all elements in the array, ignoring elements that are NaN.
     * @param data
     * @return the index number of the maximum element
     */
    public static int findGlobalPeakLocation(double[] data)
    {
        double max = Double.NaN;
        int imax = -1;
        for (int i=0; i<data.length; i++) {
            if (Double.isNaN(data[i])) continue;
            if (Double.isNaN(max)|| data[i] > max) {
                max = data[i];
                imax = i;
            }
        }
        return imax;
    }

    /**
     * Find the minimum of all elements in the array, ignoring elements that are NaN.
     * @param data
     * @return the index number of the minimum element
     */
    public static int findGlobalValleyLocation(double[] data)
    {
        double min = Double.NaN;
        int imin = -1;
        for (int i=0; i<data.length; i++) {
            if (Double.isNaN(data[i])) continue;
            if (Double.isNaN(min)|| data[i] < min) {
                min = data[i];
                imin = i;
            }
        }
        return imin;
    }

    /**
     * Build the sum of all elements in the array, ignoring elements that are NaN.
     * @param data
     * @return
     */
    public static double sum(double[] data)
    {
        double sum = 0.0;
        for (int i=0; i<data.length; i++) {
            if (Double.isNaN(data[i])) continue; 
            sum += data[i];
        }
        return sum;
    }

    //Computes sum_i=0^data.length-1 (data[i]+term)^2
    public static double sumSquared(double[] data, double term)
    {
        double sum = 0.0;
        for (int i=0; i<data.length; i++) {
            if (Double.isNaN(data[i])) continue; 
            sum += (data[i]+term)*(data[i]+term);
        }
        return sum;
    }

    /**
     * Find the maximum of all elements in the array, ignoring elements that are NaN.
     * @param data
     * @return
     */
    public static double max(double[] data)
    {
        double max = Double.NaN;
        for (int i=0; i<data.length; i++) {
            if (Double.isNaN(data[i])) continue;
            if (Double.isNaN(max)|| data[i] > max) max = data[i];
        }
        return max;
    }

    public static int max(int[] data)
    {
        int max = data[0];
        for (int i=1; i<data.length; i++) {
            if (data[i] > max) 
                max = data[i];
        }
        return max;
    }

    /**
     * Find the maximum of the absolute values of all elements in the array, ignoring elements that are NaN.
     * @param data
     * @return
     */
    public static double absMax(double[] data)
    {
        double max = Double.NaN;
        for (int i=0; i<data.length; i++) {
            if (Double.isNaN(data[i])) continue;
            double abs = Math.abs(data[i]);
            if (Double.isNaN(max)|| abs > max) max = abs;
        }
        return max;
    }

    /**
     * Find the minimum of all elements in the array, ignoring elements that are NaN.
     * @param data
     * @return
     */
    public static double min(double[] data)
    {
        double min = Double.NaN;
        for (int i=0; i<data.length; i++) {
            if (Double.isNaN(data[i])) continue;
            if (Double.isNaN(min)|| data[i] < min) min = data[i];
        }
        return min;
    }

    public static int min(int[] data)
    {
        int min = data[0];
        for (int i=1; i<data.length; i++) {
            if (data[i] < min) 
                min = data[i];
        }
        return min;
    }

    public static double mean(double[] data)
    {
        return mean(data, 0, data.length-1);
    }

    /**
     * Compute the mean of all elements in the array. No missing values (NaN) are allowed.
     * @throws IllegalArgumentException if the array contains NaN values. 
     */
    public static double mean(double[] data, int startIndex, int endIndex)
    {
        double mean = 0;
        int total = 0;
        startIndex = Math.max(startIndex, 0);
        startIndex = Math.min(startIndex, data.length-1);
        endIndex = Math.max(endIndex, 0);
        endIndex = Math.min(endIndex, data.length-1);

        if (startIndex>endIndex)
            startIndex = endIndex;

        for (int i=startIndex; i<=endIndex; i++) {
            if (Double.isNaN(data[i]))
                throw new IllegalArgumentException("NaN not allowed in mean calculation");
            mean += data[i];
            total++;
        }
        mean /= total;
        return mean;
    }

    /**
     * Compute the mean of all elements in the array with given indices. No missing values (NaN) are allowed.
     * @throws IllegalArgumentException if the array contains NaN values. 
     */
    public static double mean(double[] data, int [] inds)
    {
        double mean = 0;
        for (int i=0; i<inds.length; i++) {
            if (Double.isNaN(data[inds[i]]))
                throw new IllegalArgumentException("NaN not allowed in mean calculation");

            mean += data[inds[i]];
        }
        mean /= inds.length;
        return mean;
    }

    /**
     * Compute the mean of all elements in the array. No missing values (NaN) are allowed.
     * @throws IllegalArgumentException if the array contains NaN values. 
     */
    public static float mean(float[] data, int startIndex, int endIndex)
    {
        float mean = 0;
        int total = 0;
        startIndex = Math.max(startIndex, 0);
        startIndex = Math.min(startIndex, data.length-1);
        endIndex = Math.max(endIndex, 0);
        endIndex = Math.min(endIndex, data.length-1);

        if (startIndex>endIndex)
            startIndex = endIndex;

        for (int i=startIndex; i<=endIndex; i++) {
            if (Float.isNaN(data[i]))
                throw new IllegalArgumentException("NaN not allowed in mean calculation");
            mean += data[i];
            total++;
        }
        mean /= total;
        return mean;
    }

    public static float mean(float[] data)
    {
        return mean(data, 0, data.length-1);
    }

    /**
     * Compute the mean of all elements in the array with given indices. No missing values (NaN) are allowed.
     * @throws IllegalArgumentException if the array contains NaN values. 
     */
    public static float mean(float[] data, int [] inds)
    {
        float mean = 0;
        for (int i=0; i<inds.length; i++) {
            if (Float.isNaN(data[inds[i]]))
                throw new IllegalArgumentException("NaN not allowed in mean calculation");

            mean += data[inds[i]];
        }
        mean /= inds.length;
        return mean;
    }

    public static double standardDeviation(double[] data)
    {
        return standardDeviation(data, mean(data));
    }

    public static double standardDeviation(double[] data, double meanVal)
    {
        return standardDeviation(data, meanVal, 0, data.length-1);
    }

    public static double standardDeviation(double[] data, double meanVal, int startIndex, int endIndex)
    {
        return Math.sqrt(variance(data, meanVal, startIndex, endIndex));
    }

    public static double variance(double[] data)
    {
        return variance(data, mean(data));
    }

    public static double variance(double[] data, double meanVal)
    {
        return variance(data, meanVal, 0, data.length-1);
    }

    public static double variance(double[] data, double meanVal, int startIndex, int endIndex)
    {
        double var = 0.0;

        if (startIndex<0)
            startIndex=0;
        if (startIndex>data.length-1)
            startIndex=data.length-1;
        if (endIndex<startIndex)
            endIndex=startIndex;
        if (endIndex>data.length-1)
            endIndex=data.length-1;

        for (int i=startIndex; i<=endIndex; i++)
            var += (data[i]-meanVal)*(data[i]-meanVal);

        if (endIndex-startIndex>1)
            var /= (endIndex-startIndex);

        return var;
    }


    public static double[] variance(double[][]x, double[] meanVector)
    {
        return variance(x, meanVector, true);   
    }

    /**
     * Returns the variance of rows or columns of matrix x
     * @param x the matrix consisting of row vectors
     * @param mean the vector of mean values -- a column vector if row-wise variances are to be
     * computed, or a row vector if column-wise variances are to be calculated.
     * param isAlongRows if true, compute the variance of x[0][0], x[1][0] etc. given mean[0];
     * if false, compute the variances for the vectors x[0], x[1] etc. separately, given the respective mean[0], mean[1] etc.
     */
    public static double[] variance(double[][]x, double[] meanVector, boolean isAlongRows)
    {
        double[] var = null;

        if (x!=null && x[0]!=null && x[0].length>0 && meanVector != null)
        {
            if (isAlongRows) 
            {
                var = new double[x[0].length];
                int j, i;
                for (j=0; j<x[0].length; j++)
                {
                    for (i=0; i<x.length; i++)
                        var[j] += (x[i][j]-meanVector[j])*(x[i][j]-meanVector[j]);

                    var[j] /= (x.length-1);
                }
            } 
            else 
            {
                var = new double[x.length];
                for (int i=0; i<x.length; i++) {
                    var[i] = variance(x[i], meanVector[i]);
                }
            }
        }

        return var;
    }

    public static double[] mean(double[][] x)
    {
        return mean(x, true);
    }

    public static double[] mean(double[][] x, boolean isAlongRows)
    {
        int[] indices = null;
        int i;

        if (isAlongRows)
        {
            indices = new int[x.length];
            for (i=0; i<x.length; i++)
                indices[i] = i;
        }
        else
        {
            indices = new int[x[0].length]; 
            for (i=0; i<x[0].length; i++)
                indices[i] = i;
        }

        return mean(x, isAlongRows, indices);
    }

    //If isAlongRows==true, the observations are row-by-row
    // if isAlongRows==false, they are column-by-column
    public static double[] mean(double[][] x, boolean isAlongRows, int[] indicesOfX)
    {
        double[] meanVector = null;
        int i, j;
        if (isAlongRows)
        {
            meanVector = new double[x[indicesOfX[0]].length];
            Arrays.fill(meanVector, 0.0);

            for (i=0; i<x.length; i++)
            {
                for (j=0; j<x[indicesOfX[0]].length; j++)
                    meanVector[j] += x[indicesOfX[i]][j];
            }

            for (j=0; j<meanVector.length; j++)
                meanVector[j] /= indicesOfX.length;
        }
        else
        {
            meanVector = new double[x.length];
            Arrays.fill(meanVector, 0.0);

            for (i=0; i<indicesOfX.length; i++)
            {
                for (j=0; j<x.length; j++)
                    meanVector[j] += x[j][indicesOfX[i]];
            }

            for (j=0; j<meanVector.length; j++)
                meanVector[j] /= indicesOfX.length;
        }

        return meanVector;
    }

    //The observations are taken row by row
    public static double[][] covariance(double[][] x)
    {
        return covariance(x, true);
    }

    //The observations are taken row by row
    public static double[][] covariance(double[][] x, double[] meanVector)
    {        
        return covariance(x, meanVector, true);
    }

    //If isAlongRows==true, the observations are row-by-row
    // if isAlongRows==false, they are column-by-column
    public static double[][] covariance(double[][] x, boolean isAlongRows)
    {
        double[] meanVector = mean(x, isAlongRows);

        return covariance(x, meanVector, isAlongRows);
    }

    public static double[][] covariance(double[][] x, double[] meanVector, boolean isAlongRows)
    {

        int[] indices = null;
        int i;

        if (isAlongRows)
        {
            indices = new int[x.length];
            for (i=0; i<x.length; i++)
                indices[i] = i;
        }
        else
        {
            indices = new int[x[0].length]; 
            for (i=0; i<x[0].length; i++)
                indices[i] = i;
        }

        return covariance(x, meanVector, isAlongRows, indices);
    }

    //If isAlongRows==true, the observations are row-by-row
    // if isAlongRows==false, they are column-by-column
    public static double[][] covariance(double[][] x, double[] meanVector, boolean isAlongRows, int[] indicesOfX)
    {
        int numObservations;
        int dimension;
        int i, j, p;
        double[][] cov = null;
        double[][] tmpMatrix = null;
        double[][] zeroMean = null;
        double[][] zeroMeanTranspoze = null;

        if (x!=null && meanVector!=null)
        {
            if (isAlongRows)
            {
                for (i=0; i<indicesOfX.length; i++)
                    assert meanVector.length == x[indicesOfX[i]].length;

                numObservations = indicesOfX.length;
                dimension = x[indicesOfX[0]].length;

                cov = new double[dimension][dimension];
                tmpMatrix = new double[dimension][dimension];
                zeroMean = new double[dimension][1];
                double[] tmpVector;

                for (i=0; i<dimension; i++)
                    Arrays.fill(cov[i], 0.0);

                for (i=0; i<numObservations; i++)
                {
                    tmpVector = substract(x[indicesOfX[i]], meanVector);
                    zeroMean = transpoze(tmpVector);
                    zeroMeanTranspoze = transpoze(zeroMean);

                    tmpMatrix = matrixProduct(zeroMean, zeroMeanTranspoze);
                    cov = add(cov, tmpMatrix);
                }

                cov = divide(cov, numObservations-1);
            }
            else
            {
                assert meanVector.length == x.length;
                numObservations = indicesOfX.length;

                for (i=1; i<indicesOfX.length; i++)
                    assert x[indicesOfX[i]].length==x[indicesOfX[0]].length;

                dimension = x.length;

                cov = transpoze(covariance(transpoze(x), meanVector, true, indicesOfX));
            }
        }

        return cov;
    }

    public static double[] diagonal(double[][]x)
    {
        double[] d = null;
        int dim = x.length;
        int i;
        for (i=1; i<dim; i++)
            assert x[i].length==dim;

        if (x!=null)
        {
            d = new double[dim];

            for (i=0; i<x.length; i++)
                d[i] = x[i][i]; 
        }

        return d;
    }

    public static double[][] transpoze(double[] x)
    {
        double[][] y = new double[x.length][1];
        for (int i=0; i<x.length; i++)
            y[i][0] = x[i];

        return y;
    }

    public static double[][] transpoze(double[][] x)
    {
        double[][] y = null;

        if (x!=null)
        {
            int i, j;
            int rowSizex = x.length;
            int colSizex = x[0].length;
            for (i=1; i<rowSizex; i++)
                assert x[i].length==colSizex;

            y = new double[colSizex][rowSizex];
            for (i=0; i<rowSizex; i++)
            {
                for (j=0; j<colSizex; j++)
                    y[j][i] = x[i][j];
            }
        }

        return y;
    }

    public static double[] add(double[] x, double[] y)
    {
        assert x.length==y.length;
        double[] z = new double[x.length];
        for (int i=0; i<x.length; i++)
            z[i] = x[i]+y[i];

        return z;
    }

    public static double[] add(double[] a, double b)
    {
        double[] c = new double[a.length];
        for (int i=0; i<a.length; i++) {
            c[i] = a[i] + b;
        }
        return c;        
    }

    public static double[] substract(double[] a, double[] b)
    {
        if (a.length != b.length) {
            throw new IllegalArgumentException("Arrays must be equal length");
        }
        double[] c = new double[a.length];
        for (int i=0; i<a.length; i++) {
            c[i] = a[i] - b[i];
        }
        return c;
    }

    public static double[] substract(double[] a, double b)
    {
        double[] c = new double[a.length];
        for (int i=0; i<a.length; i++) {
            c[i] = a[i] - b;
        }
        return c;
    }

    public static double[] multiply(double[] a, double[] b)
    {
        if (a.length != b.length) {
            throw new IllegalArgumentException("Arrays must be equal length");
        }
        double[] c = new double[a.length];
        for (int i=0; i<a.length; i++) {
            c[i] = a[i] * b[i];
        }
        return c;        
    }

    public static double[] multiply(double[] a, double b)
    {
        double[] c = new double[a.length];
        for (int i=0; i<a.length; i++) {
            c[i] = a[i] * b;
        }
        return c;        
    }

    public static double[] divide(double[] a, double[] b)
    {
        if (a.length != b.length) {
            throw new IllegalArgumentException("Arrays must be equal length");
        }
        double[] c = new double[a.length];
        for (int i=0; i<a.length; i++) {
            c[i] = a[i] / b[i];
        }
        return c;
    }

    public static double[] divide(double[] a, double b)
    {
        double[] c = new double[a.length];
        for (int i=0; i<a.length; i++) {
            c[i] = a[i] / b;
        }
        return c;
    }

    //Returns the summ of two matrices, i.e. x+y
    //x and y should be of same size
    public static double[][] add(double[][] x, double[][] y)
    {
        double[][] z = null;

        if (x!=null && y!=null)
        {
            int i, j;
            assert x.length==y.length;
            for (i=0; i<x.length; i++)
            {
                assert x[i].length==x[0].length;
                assert x[i].length==y[i].length;
            }

            z = new double[x.length][x[0].length];


            for (i=0; i<x.length; i++)
            {
                for (j=0; j<x[i].length; j++)
                    z[i][j] = x[i][j]+y[i][j];
            }
        }

        return z;
    }

    //Returns the difference of two matrices, i.e. x-y
    //x and y should be of same size
    public static double[][] substract(double[][] x, double[][] y)
    {
        double[][] z = null;

        if (x!=null && y!=null)
        {
            int i, j;
            assert x.length==y.length;
            for (i=0; i<x.length; i++)
            {
                assert x[i].length==x[0].length;
                assert x[i].length==y[i].length;
            }

            z = new double[x.length][x[0].length];


            for (i=0; i<x.length; i++)
            {
                for (j=0; j<x[i].length; j++)
                    z[i][j] = x[i][j]-y[i][j];
            }
        }

        return z;
    }

    //Returns multiplication of matrix entries with a constant, i.e. ax
    //x and y should be of same size
    public static double[][] multiply(double a, double[][] x)
    {
        double[][] z = null;

        if (x!=null)
        {
            int i, j;
            for (i=1; i<x.length; i++)
                assert x[i].length==x[0].length;

            z = new double[x.length][x[0].length];

            for (i=0; i<x.length; i++)
            {
                for (j=0; j<x[i].length; j++)
                    z[i][j] = a*x[i][j];
            }
        }

        return z;
    }

    //Returns the division of matrix entries with a constant, i.e. x/a
    //x and y should be of same size
    public static double[][] divide(double[][] x, double a)
    {
        return multiply(1.0/a, x);
    }
    
    //Matrix of size NxM multiplied by an appropriate sized vector, i.e. Mx1, returns a vector of size Nx1
    public static double[] matrixProduct(double[][] x, double[] y)
    {
        double[][] y2 = new double[y.length][1];
        int i;
        for (i=0; i<y.length; i++)
            y2[i][0] = y[i];
        
        y2 = matrixProduct(x, y2);
        
        double[] y3 = new double[y2.length];
        for (i=0; i<y2.length; i++)
            y3[i] = y2[i][0];
        
        return y3;
    }
    
    //Vector of size N is multiplied with matrix of size NxM
    // Returns a matrix of size NxM
    public static double[][] matrixProduct(double[] x, double[][] y)
    {
        double[][] x2 = new double[x.length][1];
        int i;
        for (i=0; i<x.length; i++)
            x2[i][0] = x[i];
        
        return matrixProduct(x2, y);
    }

    //This is a "*" product --> should return a matrix provided that the sizes are appropriate
    public static double[][] matrixProduct(double[][] x, double[][] y)
    {
        double[][] z = null;

        if (x!=null && y!=null)
        {
            if (x.length==1 && y.length==1) //Special case -- diagonal matrix multiplication, returns a diagonal matrix
            {
                assert x[0].length==y[0].length;
                z = new double[1][x[0].length];
                for (int i=0; i<x[0].length; i++)
                    z[0][i] = x[0][i]*y[0][i];
            }
            else
            {
                int i, j, m;
                int rowSizex = x.length;
                int colSizex = x[0].length;
                int rowSizey = y.length;
                int colSizey = y[0].length;
                for (i=1; i<x.length; i++)
                    assert x[i].length == colSizex;
                for (i=1; i<y.length; i++)
                    assert y[i].length == colSizey;
                assert colSizex==rowSizey;

                z = new double[rowSizex][colSizey];
                double tmpSum;
                for (i=0; i<rowSizex; i++)
                {
                    for (j=0; j<colSizey; j++)
                    {
                        tmpSum = 0.0;
                        for (m=0; m<x[i].length; m++)
                            tmpSum += x[i][m]*y[m][j];

                        z[i][j] = tmpSum;
                    }
                }
            }
        }

        return z;
    }

    //"x" product of two vectors
    public static double[][] vectorProduct(double[] x, boolean isColumnVectorX, double[] y, boolean isColumnVectorY)
    {
        double[][] xx = null;
        double[][] yy = null;
        int i;
        if (isColumnVectorX)
        {
            xx = new double[x.length][1];
            for (i=0; i<x.length; i++)
                xx[i][0] = x[i];
        }
        else
        {
            xx = new double[1][x.length];
            System.arraycopy(x, 0, xx[0], 0, x.length);
        }

        if (isColumnVectorY)
        {
            yy = new double[y.length][1];
            for (i=0; i<y.length; i++)
                yy[i][0] = y[i];
        }
        else
        {
            yy = new double[1][y.length];
            System.arraycopy(y, 0, yy[0], 0, y.length);
        }

        return matrixProduct(xx, yy);
    }

    public static double dotProduct(double[] x, double[] y)
    {
        assert x.length==y.length;

        double tmpSum = 0.0;
        for (int i=0; i<x.length; i++)
            tmpSum += x[i]*y[i];

        return tmpSum;
    }

    public static double[][] dotProduct(double[][] x, double[][] y)
    {
        double[][] z = null;
        assert x.length==y.length;
        int numRows = x.length;
        int numCols = x[0].length;
        int i;
        for (i=1; i<numRows; i++)
        {
            assert numCols == x[i].length;
            assert numCols == y[i].length;
        }

        if (x!=null)
        {
            int j;
            z = new double[numRows][numCols];
            for (i=0; i<numRows; i++)
            {
                for (j=0; j<numCols; j++)
                    z[i][j] = x[i][j]*y[i][j];
            }
        }

        return z;
    }

    /**
     * Convert energy from linear scale to db SPL scale (comparing energies to  
     * the minimum audible energy, one Pascal squared).
     * @param energy in time or frequency domain, on a linear energy scale
     * @return energy on a db scale, or NaN if energy is less than or equal to 0.
     */
    public static double dbSPL(double energy)
    {
        if (energy <= 0) return Double.NaN;
        else return 10 * log10(energy/PASCALSQUARE);
    }

    public static double[] dbSPL(double[] energies)
    {
        return multiply(log10(divide(energies, PASCALSQUARE)), 10);
    }

    /**
     * Convert energy from linear scale to db scale.
     * @param energy in time or frequency domain, on a linear energy scale
     * @return energy on a db scale, or NaN if energy is less than or equal to 0.
     */
    public static double db(double energy)
    {
        if (energy <= 0) return Double.NaN;
        else return 10 * log10(energy);
    }

    public static double amp2db(double amp)
    {
        if (amp <= 0) return Double.NaN;
        else return 20 * log10(amp);
    }

    public static double[] db(double[] energies)
    {
        return multiply(log10(energies), 10);
    }

    public static double[] amp2db(double[] amps)
    {
        return multiply(log10(amps), 20);
    }

    public static double[] amp2db(Complex c)
    {
        return amp2db(c, 0, c.real.length-1);
    }

    public static double[] amp2db(Complex c, int startInd, int endInd)
    {
        if (startInd<0)
            startInd=0;
        if (startInd>Math.min(c.real.length-1,c.imag.length-1))
            startInd=Math.min(c.real.length-1,c.imag.length-1);
        if (endInd<startInd)
            endInd=startInd;
        if (endInd>Math.min(c.real.length-1,c.imag.length-1))
            endInd=Math.min(c.real.length-1,c.imag.length-1);

        double[] dbs = new double[endInd-startInd+1];
        for (int i=startInd; i<=endInd; i++)
            dbs[i-startInd] = 10*log10(c.real[i]*c.real[i]+c.imag[i]*c.imag[i]);

        return dbs;
    }

    /**
     * Convert energy from db scale to linear scale.
     * @param energy in time or frequency domain, on a db energy scale
     * @return energy on a linear scale.
     */
    public static double db2linear(double dbEnergy)
    {
        if (Double.isNaN(dbEnergy)) return 0.;
        else return exp10(dbEnergy/10);
    }

    public static double[] db2linear(double[] dbEnergies)
    {
        return exp10(divide(dbEnergies, 10));
    }

    public static float db2amplitude(float dbAmplitude)
    {
        if (Float.isNaN(dbAmplitude)) return 0.0f;
        else return (float)(Math.pow(10.0, dbAmplitude/20));
    }

    public static double db2amplitude(double dbAmplitude)
    {
        if (Double.isNaN(dbAmplitude)) return 0.;
        else return Math.pow(10.0, dbAmplitude/20);
    }

    public static float radian2degrees(float rad)
    {
        return (float)((rad/MathUtils.TWOPI)*360.0f);
    }

    public static double radian2degrees(double rad)
    {
        return (rad/MathUtils.TWOPI)*360.0;
    }

    /**
     * Build the sum of the squared difference of all elements 
     * with the same index numbers in the arrays.
     * @param a
     * @param b
     * @return
     */
    public static double sumSquaredError(double[] a, double[] b)
    {
        if (a.length != b.length) {
            throw new IllegalArgumentException("Arrays must be equal length");
        }
        double sum = 0;
        for (int i=0; i<a.length; i++) {
            double delta = a[i] - b[i];
            sum += delta*delta;
        }
        return sum;
    }


    public static double log10(double x)
    {
        return Math.log(x)/LOG10;
    }

    public static double[] log(double[] a)
    {
        double[] c = new double[a.length];
        for (int i=0; i<a.length; i++) {
            c[i] = Math.log(a[i]);
        }
        return c;
    }

    //A special log operation
    //The values smaller than or equal to minimumValue are set to fixedValue
    //The values greater than minimumValue are converted to log
    public static double[] log(double[] a, double minimumValue, double fixedValue)
    {
        double[] c = new double[a.length];
        for (int i=0; i<a.length; i++) 
        {
            if (a[i]>minimumValue)
                c[i] = Math.log(a[i]);
            else
                c[i] = fixedValue;
        }
        return c;
    }

    public static double[] log10(double[] a)
    {
        double[] c = new double[a.length];
        for (int i=0; i<a.length; i++) {
            c[i] = log10(a[i]);
        }
        return c;
    }

    public static double exp10(double x)
    {
        return Math.exp(LOG10*x);
    }

    public static double[] exp(double[] a)
    {
        double[] c = new double[a.length];
        for (int i=0; i<a.length; i++) {
            c[i] = Math.exp(a[i]);
        }
        return c;
    }

    public static double[] exp10(double[] a)
    {
        double[] c = new double[a.length];
        for (int i=0; i<a.length; i++) {
            c[i] = exp10(a[i]);
        }
        return c;
    }


    public static float[] add(float[] a, float[] b)
    {
        if (a.length != b.length) {
            throw new IllegalArgumentException("Arrays must be equal length");
        }
        float[] c = new float[a.length];
        for (int i=0; i<a.length; i++) {
            c[i] = a[i] + b[i];
        }
        return c;        
    }

    public static float[] add(float[] a, float b)
    {
        float[] c = new float[a.length];
        for (int i=0; i<a.length; i++) {
            c[i] = a[i] + b;
        }
        return c;        
    }

    public static float[] substract(float[] a, float[] b)
    {
        if (a.length != b.length) {
            throw new IllegalArgumentException("Arrays must be equal length");
        }
        float[] c = new float[a.length];
        for (int i=0; i<a.length; i++) {
            c[i] = a[i] - b[i];
        }
        return c;
    }

    public static float[] substract(float[] a, float b)
    {
        float[] c = new float[a.length];
        for (int i=0; i<a.length; i++) {
            c[i] = a[i] - b;
        }
        return c;
    }


    public static double euclidianLength(float[] a)
    {
        double len = 0.;
        for (int i=0; i<a.length; i++) {
            len += a[i]*a[i];
        }
        return Math.sqrt(len);
    }

    public static double euclidianLength(double[] a)
    {
        double len = 0.;
        for (int i=0; i<a.length; i++) {
            len += a[i]*a[i];
        }
        return Math.sqrt(len);
    }

    /**
     * Convert a pair of arrays from cartesian (x, y) coordinates to 
     * polar (r, phi) coordinates. Phi will be in radians, i.e. a full circle is two pi.
     * @param x as input, the x coordinate; as output, the r coordinate;
     * @param y as input, the y coordinate; as output, the phi coordinate.
     */
    public static void toPolarCoordinates(double[] x, double[] y)
    {
        if (x.length != y.length) {
            throw new IllegalArgumentException("Arrays must be equal length");
        }
        for (int i=0; i<x.length; i++) {
            double r = Math.sqrt(x[i]*x[i] + y[i]*y[i]);
            double phi = Math.atan2(y[i], x[i]);
            x[i] = r;
            y[i] = phi;
        }
    }

    /**
     * Convert a pair of arrays from polar (r, phi) coordinates to
     * cartesian (x, y) coordinates. Phi is in radians, i.e. a whole circle is two pi.
     * @param r as input, the r coordinate; as output, the x coordinate;
     * @param phi as input, the phi coordinate; as output, the y coordinate.
     */
    public static void toCartesianCoordinates(double[] r, double[] phi)
    {
        if (r.length != phi.length) {
            throw new IllegalArgumentException("Arrays must be equal length");
        }
        for (int i=0; i<r.length; i++) {
            double x = r[i] * Math.cos(phi[i]);
            double y = r[i] * Math.sin(phi[i]);
            r[i] = x;
            phi[i] = y;
        }
    }

    /**
     * For a given angle in radians, return the equivalent angle in the range [-PI, PI].
     * @param angle
     * @return
     */
    public static double angleToDefaultAngle(double angle)
    {
        return (angle+Math.PI)%(-TWOPI)+Math.PI;
    }

    /**
     * For each of an array of angles (in radians), return the equivalent angle in the range [-PI, PI].
     * @param angle
     * @return
     */
    public static void angleToDefaultAngle(double[] angle)
    {
        for (int i=0; i<angle.length; i++) {
            angle[i] = angleToDefaultAngle(angle[i]);
        }
    }

    /**
     * This is the Java source code for a Levinson Recursion.
     * from http://www.nauticom.net/www/jdtaft/JavaLevinson.htm
     * @param r contains the autocorrelation lags as input [r(0)...r(m)].
     * @param m
     * @return the array of whitening coefficients
     */
    public static double[] levinson(double[] r, int m)
    {
        // The matrix l is unit lower triangular.
        // It's i-th row contains upon completion the i-th prediction error filter,
        // with the coefficients in reverse order. The vector e contains upon 
        // completion the prediction errors.
        // The last section extracts the maximum length whitening filter
        // coefficients from matrix l.
        int i;
        int j;
        int k;
        double gap;
        double gamma;
        double e[] = new double[m + 1];
        double l[][] = new double[m + 1][m + 1];
        double[] coeffs = new double[m+1];

        /* compute recursion  */
        for (i = 0; i <= m; i++) {
            for (j = i + 1; j <= m; j++) {
                l[i][j] = 0.;
            }
        }
        l[0][0] = 1.;
        l[1][1] = 1.;
        l[1][0] = -r[1] / r[0];
        e[0] = r[0];
        e[1] = e[0] * (1. - l[1][0] * l[1][0]);
        for (i = 2; i <= m; i++) {
            gap = 0.;
            for (k = 0; k <= i - 1; k++) {
                gap += r[k + 1] * l[i - 1][k];
            }
            gamma = gap / e[i - 1];
            l[i][0] = -gamma;
            for (k = 1; k <= i - 1; k++) {
                l[i][k] = l[i - 1][k - 1] - gamma * l[i - 1][i - 1 - k];
            }
            l[i][i] = 1.;
            e[i] = e[i - 1] * (1. - gamma * gamma);
        }
        /* extract length-m whitening filter coefficients  */
        coeffs[0] = 1.;
        for (i = 1; i <= m; i++) {
            coeffs[i] = l[m][m - i];
        }
        /*        double sum = 0.;
        for (i = 0; i < m; i++) {
            sum += coeffs[i];
        }
        for (i = 0; i < m; i++) {
            coeffs[i] = coeffs[i] / sum;
        }
         */
        return coeffs;
    }

    public static class Complex {
        public double [] real;
        public double [] imag;

        public Complex(int len)
        {
            init(len);
        }

        public Complex(Complex c)
        {
            if (c.real!=null && c.imag!=null && c.real.length==c.imag.length)
            {
                init(c.real.length);
                System.arraycopy(c.real, 0, real, 0, c.real.length);
                System.arraycopy(c.imag, 0, imag, 0, c.imag.length);
            }
        }

        public void init(int len)
        {
            if (len>0)
            {
                real = new double[len];
                imag = new double[len];

                Arrays.fill(real, 0.0);
                Arrays.fill(imag, 0.0);
            }
            else
            {
                real = null;
                imag = null;
            }
        }
    }

    /* Performs interpolation to increase or decrease the size of array x
       to newLength*/
    public static double [] interpolate(double [] x, int newLength)
    {
        double [] y = null;
        if (newLength>0)
        {
            int N = x.length;
            if (N==1)
            {
                y = new double[1];
                y[0]=x[0];
                return y;
            }
            else if (newLength==1)
            {
                y = new double[1];
                int ind = (int)Math.floor(N*0.5+0.5);
                ind = Math.max(1, ind);
                ind = Math.min(ind, N);
                y[0]= x[ind-1];
                return y;
            }
            else
            {
                y = new double[newLength];
                double Beta = ((double)newLength)/N;
                double newBeta = 1.0;

                if (newLength>2)
                    newBeta=(N-2.0)/(newLength-2.0);

                y[0] = x[0];
                y[1] = x[1];
                y[newLength-1] = x[N-1];

                double tmp, alpha;
                int i, j;
                for (i=2; i<=newLength-2; i++) 
                {
                    tmp = 1.0+(i-1)*newBeta;
                    j = (int)Math.floor(tmp);
                    alpha = tmp-j;
                    y[i] = (1.0-alpha)*x[Math.max(0,j)] + alpha*x[Math.min(N-1,j+1)];
                }
            }
        }

        return y;
    }

    public static int getMax(int [] x)
    {
        int maxx = x[0];
        for (int i=1; i<x.length; i++)
        {
            if (x[i]>maxx)
                maxx = x[i];
        }

        return maxx;
    }

    public static int getMinIndex(int [] x)
    {
        return getMinIndex(x, 0);
    }

    public static int getMinIndex(int [] x, int startInd)
    {
        return getMinIndex(x, startInd, x.length-1);
    }

    public static int getMinIndex(int [] x, int startInd, int endInd)
    {
        return getExtremaIndex(x, false, startInd, endInd);
    }

    public static int getMaxIndex(int [] x)
    {
        return getMaxIndex(x, 0);
    }

    public static int getMaxIndex(int [] x, int startInd)
    {
        return getMaxIndex(x, startInd, x.length-1);
    }

    public static int getMaxIndex(int [] x, int startInd, int endInd)
    {
        return getExtremaIndex(x, true, startInd, endInd);
    }

    public static int getExtremaIndex(int [] x, boolean isMax)
    {
        return getExtremaIndex(x, isMax, 0);
    }

    public static int getExtremaIndex(int [] x, boolean isMax, int startInd)
    {
        return getExtremaIndex(x, isMax, startInd, x.length-1);
    }

    public static int getExtremaIndex(int [] x, boolean isMax, int startInd, int endInd)
    {
        int extrema = x[0];
        int extremaInd = 0;
        if (startInd<0)
            startInd=0;
        if (endInd>x.length-1)
            endInd = x.length-1;
        if (startInd>endInd)
            startInd=endInd;

        if (isMax)
        {
            for (int i=startInd; i<=endInd; i++)
            {
                if (x[i]>extrema)
                {
                    extrema = x[i];
                    extremaInd = i;
                }
            }
        }
        else
        {
            for (int i=startInd; i<=endInd; i++)
            {
                if (x[i]<extrema)
                {
                    extrema = x[i];
                    extremaInd = i;
                }
            }
        }

        return extremaInd;
    }

    public static int getMinIndex(float [] x)
    {
        return getMinIndex(x, 0);
    }

    public static int getMinIndex(float [] x, int startInd)
    {
        return getMinIndex(x, startInd, x.length-1);
    }

    public static int getMinIndex(float [] x, int startInd, int endInd)
    {
        return getExtremaIndex(x, false, startInd, endInd);
    }

    public static int getMaxIndex(float [] x)
    {
        return getMaxIndex(x, 0);
    }

    public static int getMaxIndex(float [] x, int startInd)
    {
        return getMaxIndex(x, startInd, x.length-1);
    }

    public static int getMaxIndex(float [] x, int startInd, int endInd)
    {
        return getExtremaIndex(x, true, startInd, endInd);
    }

    public static int getExtremaIndex(float [] x, boolean isMax)
    {
        return getExtremaIndex(x, isMax, 0);
    }

    public static int getExtremaIndex(float [] x, boolean isMax, int startInd)
    {
        return getExtremaIndex(x, isMax, startInd, x.length-1);
    }

    public static int getExtremaIndex(float [] x, boolean isMax, int startInd, int endInd)
    {
        float extrema = x[0];
        int extremaInd = 0;
        if (startInd<0)
            startInd=0;
        if (endInd>x.length-1)
            endInd = x.length-1;
        if (startInd>endInd)
            startInd=endInd;

        if (isMax)
        {
            for (int i=startInd; i<=endInd; i++)
            {
                if (x[i]>extrema)
                {
                    extrema = x[i];
                    extremaInd = i;
                }
            }
        }
        else
        {
            for (int i=startInd; i<=endInd; i++)
            {
                if (x[i]<extrema)
                {
                    extrema = x[i];
                    extremaInd = i;
                }
            }
        }

        return extremaInd;
    }

    public static int getMinIndex(double [] x)
    {
        return getMinIndex(x, 0);
    }

    public static int getMinIndex(double [] x, int startInd)
    {
        return getMinIndex(x, startInd, x.length-1);
    }

    public static int getMinIndex(double [] x, int startInd, int endInd)
    {
        return getExtremaIndex(x, false, startInd, endInd);
    }

    public static int getMaxIndex(double [] x)
    {
        return getMaxIndex(x, 0);
    }

    public static int getMaxIndex(double [] x, int startInd)
    {
        return getMaxIndex(x, startInd, x.length-1);
    }

    public static int getMaxIndex(double [] x, int startInd, int endInd)
    {
        return getExtremaIndex(x, true, startInd, endInd);
    }

    public static int getExtremaIndex(double [] x, boolean isMax)
    {
        return getExtremaIndex(x, isMax, 0);
    }

    public static int getExtremaIndex(double [] x, boolean isMax, int startInd)
    {
        return getExtremaIndex(x, isMax, startInd, x.length-1);
    }

    public static int getExtremaIndex(double [] x, boolean isMax, int startInd, int endInd)
    {
        double extrema = x[0];
        int extremaInd = startInd;
        if (startInd<0)
            startInd=0;
        if (endInd>x.length-1)
            endInd = x.length-1;
        if (startInd>endInd)
            startInd=endInd;

        if (isMax)
        {
            for (int i=startInd; i<=endInd; i++)
            {
                if (x[i]>extrema)
                {
                    extrema = x[i];
                    extremaInd = i;
                }
            }
        }
        else
        {
            for (int i=startInd; i<=endInd; i++)
            {
                if (x[i]<extrema)
                {
                    extrema = x[i];
                    extremaInd = i;
                }
            }
        }

        return extremaInd;
    }

    public static double getMax(double [] x)
    {
        double maxx = x[0];
        for (int i=1; i<x.length; i++)
        {
            if (x[i]>maxx)
                maxx = x[i];
        }

        return maxx;
    }

    public static float getMax(float [] x)
    {
        float maxx = x[0];
        for (int i=1; i<x.length; i++)
        {
            if (x[i]>maxx)
                maxx = x[i];
        }

        return maxx;
    }

    public static int getMin(int [] x)
    {
        int minn = x[0];
        for (int i=1; i<x.length; i++)
        {
            if (x[i]<minn)
                minn = x[i];
        }

        return minn;
    }

    public static double getMin(double [] x)
    {
        double minn = x[0];
        for (int i=1; i<x.length; i++)
        {
            if (x[i]<minn)
                minn = x[i];
        }

        return minn;
    }

    public static float getMin(float [] x)
    {
        float maxx = x[0];
        for (int i=1; i<x.length; i++)
        {
            if (x[i]<maxx)
                maxx = x[i];
        }

        return maxx;
    }

    public static double getAbsMax(double [] x)
    {
        return getAbsMax(x, 0, x.length-1);
    }

    public static double getAbsMax(double [] x, int startInd, int endInd)
    {
        double maxx = Math.abs(x[startInd]);
        for (int i=startInd+1; i<=endInd; i++)
        {
            if (Math.abs(x[i])>maxx)
                maxx = Math.abs(x[i]);
        }

        return maxx;
    }

    //Return the local peak index for values x[startInd],x[startInd+1],...,x[endInd]
                                                                             // Note that the returned index is in the range [startInd,endInd]
                                                                                                                              // If there is no local peak, -1 is returned. This means that the peak is either at [startInd] or [endInd].
    // However, it is the responsibility of the calling function to further check this situation as the returned index
    // will be -1 in both cases
    public static int getAbsMaxInd(double [] x, int startInd, int endInd)
    {
        int index = -1;
        double max = x[startInd];

        for (int i=startInd+1; i<endInd-1; i++)
        {
            if (x[i]>max && x[i]>x[i-1] && x[i]>x[i+1])
            {
                max = x[i];
                index = i;
            }
        }

        return index;
    }

    //Return an array where each entry is set to val
    public static double [] filledArray(double val, int len)
    {
        double [] x = null;

        if (len>0)
        {
            x = new double[len];
            for (int i=0; i<len; i++)
                x[i] = val;
        }

        return x;
    }

    //Return an array where each entry is set to val
    public static int [] filledArray(int val, int len)
    {
        int [] x = null;

        if (len>0)
        {
            x = new int[len];
            for (int i=0; i<len; i++)
                x[i] = val;
        }

        return x;
    }

    //Return an array filled with 0´s
    public static double [] zeros(int len)
    {
        return filledArray(0.0, len);
    }

    //Return an array filled with 1´s
    public static double [] ones(int len)
    {
        return filledArray(1.0, len);
    }

//  Return an array filled with 0´s
    public static int [] zerosInt(int len)
    {
        return filledArray(0, len);
    }

    //Return an array filled with 1´s
    public static int [] onesInt(int len)
    {
        return filledArray(1, len);
    }

    public static int [] find(double[] x, int comparator, double val)
    {
        int [] inds = null;
        int totalFound = 0;

        switch (comparator)
        {
        case EQUALS:
            for (int i=0; i<x.length; i++)
            {
                if (x[i]==val)
                    totalFound++;
            }
            break;
        case GREATER_THAN:
            for (int i=0; i<x.length; i++)
            {
                if (x[i]>val)
                    totalFound++;
            }
            break;
        case GREATER_THAN_OR_EQUALS:
            for (int i=0; i<x.length; i++)
            {
                if (x[i]>=val)
                    totalFound++;
            }
            break;
        case LESS_THAN:
            for (int i=0; i<x.length; i++)
            {
                if (x[i]<val)
                    totalFound++;
            }
            break;
        case LESS_THAN_OR_EQUALS:
            for (int i=0; i<x.length; i++)
            {
                if (x[i]<=val)
                    totalFound++;
            }
            break;
        case NOT_EQUALS:
            for (int i=0; i<x.length; i++)
            {
                if (x[i]!=val)
                    totalFound++;
            }
            break;
        }

        if (totalFound>0)
        {
            int currentInd = 0;
            inds = new int[totalFound];

            switch (comparator)
            {
            case EQUALS:
                for (int i=0; i<x.length; i++)
                {
                    if (x[i]==val)
                    {
                        inds[currentInd++] = i;
                        totalFound++;
                    }
                }
                break;
            case GREATER_THAN:
                for (int i=0; i<x.length; i++)
                {
                    if (x[i]>val)
                    {
                        inds[currentInd++] = i;
                        totalFound++;
                    }
                }
                break;
            case GREATER_THAN_OR_EQUALS:
                for (int i=0; i<x.length; i++)
                {
                    if (x[i]>=val)
                    {
                        inds[currentInd++] = i;
                        totalFound++;
                    }
                }
                break;
            case LESS_THAN:
                for (int i=0; i<x.length; i++)
                {
                    if (x[i]<val)
                    {
                        inds[currentInd++] = i;
                        totalFound++;
                    }
                }
                break;
            case LESS_THAN_OR_EQUALS:
                for (int i=0; i<x.length; i++)
                {
                    if (x[i]<=val)
                    {
                        inds[currentInd++] = i;
                        totalFound++;
                    }
                }
                break;
            case NOT_EQUALS:
                for (int i=0; i<x.length; i++)
                {
                    if (x[i]!=val)
                    {
                        inds[currentInd++] = i;
                        totalFound++;
                    }
                }
                break;
            }
        }

        return inds;
    }

    public static double [] interpolate_linear(int [] x, double [] y, int [] xi)
    {
        assert(x.length==y.length);

        double [] yi = new double[xi.length];
        int i, j;
        boolean bFound;
        double alpha;

        for (i=0; i<xi.length; i++)
        {
            bFound = false;
            for (j=0; j<x.length-1; j++)
            {
                if (xi[i]>=x[j] && xi[i]<x[j+1])
                {
                    bFound = true;
                    break;
                }
            }

            if (bFound)
            {
                alpha = (((double)xi[i])-x[j])/(x[j+1]-x[j]);
                yi[i] = (1-alpha)*y[j] + alpha*y[j+1];
            }
        }

        if (xi[xi.length-1]==x[x.length-1])
            yi[xi.length-1] = y[x.length-1];

        return yi;
    }

    public static int CheckLimits(int val, int minVal, int maxVal)
    {
        int ret = val;

        if (ret<minVal)
            ret = minVal;

        if (ret>maxVal)
            ret = maxVal;

        return ret;
    }

    public static double CheckLimits(double val, double minVal, double maxVal)
    {
        double ret = val;

        if (ret<minVal)
            ret = minVal;

        if (ret>maxVal)
            ret = maxVal;

        return ret;
    }

    public static float CheckLimits(float val, float minVal, float maxVal)
    {
        float ret = val;

        if (ret<minVal)
            ret = minVal;

        if (ret>maxVal)
            ret = maxVal;

        return ret;
    }

    //Find the extremum points that are larger/smaller than numLefNs and numRightNs neighbours and larger/smaller than the given th value
    public static int [] getExtrema(double [] x, int numLeftN, int numRightN, boolean isMaxima)
    {
        return getExtrema(x, numLeftN, numRightN, isMaxima, 0); 
    }

    public static int [] getExtrema(double [] x, int numLeftN, int numRightN, boolean isMaxima, int startInd)
    {
        return getExtrema(x, numLeftN, numRightN, isMaxima, startInd, x.length-1);
    }

    public static int [] getExtrema(double [] x, int numLeftN, int numRightN, boolean isMaxima, int startInd, int endInd)
    {
        double th;

        if (isMaxima)
            th = MathUtils.getMin(x)-1.0;
        else
            th = MathUtils.getMax(x)+1.0;

        return getExtrema(x, numLeftN, numRightN, isMaxima, startInd, endInd, th);
    }

    public static int [] getExtrema(double [] x, int numLeftN, int numRightN, boolean isMaxima, int startInd, int endInd, double th)
    {
        int [] numLeftNs = new int[x.length];
        int [] numRightNs = new int[x.length];
        Arrays.fill(numLeftNs, numLeftN);
        Arrays.fill(numRightNs, numRightN);

        return getExtrema(x, numLeftNs, numRightNs, isMaxima, startInd, endInd, th); 
    }

    public static int [] getExtrema(double [] x, int [] numLeftNs, int [] numRightNs, boolean isMaxima)
    { 
        return getExtrema(x, numLeftNs, numRightNs, isMaxima, 0);
    }

    public static int [] getExtrema(double [] x, int [] numLeftNs, int [] numRightNs, boolean isMaxima, int startInd)
    {
        return getExtrema(x, numLeftNs, numRightNs, isMaxima, startInd, x.length-1);
    }

    public static int [] getExtrema(double [] x, int [] numLeftNs, int [] numRightNs, boolean isMaxima, int startInd, int endInd)
    {   
        double th;

        if (isMaxima)
            th = MathUtils.getMin(x)-1.0;
        else
            th = MathUtils.getMax(x)+1.0;

        return getExtrema(x, numLeftNs, numRightNs, isMaxima, startInd, endInd, th);
    }

    public static int [] getExtrema(double [] x, int [] numLeftNs, int [] numRightNs, boolean isMaxima, int startInd, int endInd, double th)
    {
        int [] tmpInds = new int[x.length];
        int [] inds = null;
        int total = 0;

        int i, j;
        boolean bExtremum;

        if (startInd<0)
            startInd=0;
        if (endInd>x.length-1)
            endInd=x.length-1;
        if (startInd>endInd)
            startInd=endInd;

        if (isMaxima) //Search for maxima
        {
            for (i=startInd; i<=endInd; i++)
            {
                if (x[i]>th)
                {    
                    bExtremum = true;

                    if (i-numLeftNs[i]>=0)
                    {
                        for (j=i-numLeftNs[i]; j<i; j++)
                        {
                            if (x[i]<x[j])
                            {
                                bExtremum = false;
                                break;
                            }
                        }

                        if (bExtremum)
                        {
                            if (i+numRightNs[i]<x.length)
                            {
                                for (j=i+1; j<=i+numRightNs[i]; j++)
                                {
                                    if (x[i]<x[j])
                                    {
                                        bExtremum = false;
                                        break;
                                    }
                                }
                            }
                            else
                                bExtremum = false;
                        }
                    }
                    else
                        bExtremum = false;
                }
                else
                    bExtremum = false;

                if (bExtremum)
                    tmpInds[total++] = i;
            }
        }
        else //Search for minima
        {
            for (i=startInd; i<=endInd; i++)
            {
                if (x[i]<th)
                {
                    bExtremum = true;
                    if (i-numLeftNs[i]>=0)
                    {
                        for (j=i-numLeftNs[i]; j<i; j++)
                        {
                            if (x[i]>x[j])
                            {
                                bExtremum = false;
                                break;
                            }
                        }

                        if (bExtremum)
                        {
                            if (i+numRightNs[i]<x.length)
                            {
                                for (j=i+1; j<=i+numRightNs[i]; j++)
                                {
                                    if (x[i]>x[j])
                                    {
                                        bExtremum = false;
                                        break;
                                    }
                                }
                            }
                            else
                                bExtremum = false;
                        }
                    }
                    else
                        bExtremum = false;
                }
                else
                    bExtremum = false;

                if (bExtremum)
                    tmpInds[total++] = i;
            }
        }  

        if (total>0)
        {
            inds = new int[total];
            System.arraycopy(tmpInds, 0, inds, 0, total);
        }

        return inds; 
    }

    //Returns an array of values selected randomly in the interval [0.0,1.0)
    public static double [] getRandoms(int len)
    {
        return getRandoms(len, 0.0, 1.0);
    }

    //Returns an array of values selected randomly in the interval minVal and maxVal
    public static double [] getRandoms(int len, double minVal, double maxVal)
    {
        double [] x = null;

        if (len>0)
        {
            x = new double[len];
            if (minVal>maxVal)
            {
                double tmp = minVal;
                minVal = maxVal;
                maxVal = tmp;
            }

            for (int i=0; i<len; i++)
                x[i] = Math.random()*(maxVal-minVal)+minVal;
        }

        return x; 
    }

    //Return the zero-based index of the entry of x which is closest to val
    public static int findClosest(float [] x, float val)
    {
        int ind = -1;
        if (x!=null && x.length>0)
        {
            float minDiff = Math.abs(x[0]-val);
            float tmpDiff;
            ind = 0;

            for (int i=1; i<x.length; i++)
            {
                tmpDiff = Math.abs(x[i]-val);
                if (tmpDiff<minDiff)
                {
                    minDiff = tmpDiff;
                    ind = i;
                }
            }
        }

        return ind;
    }

    //Return the zero-based index of the entry of x which is closest to val
    public static int findClosest(int [] x, int val)
    {
        int ind = -1;
        if (x!=null && x.length>0)
        {
            int minDiff = Math.abs(x[0]-val);
            int tmpDiff;
            ind = 0;

            for (int i=1; i<x.length; i++)
            {
                tmpDiff = Math.abs(x[i]-val);
                if (tmpDiff<minDiff)
                {
                    minDiff = tmpDiff;
                    ind = i;
                }
            }
        }

        return ind;
    }

    public static float unwrap(float phaseInRadians, float prevPhaseInRadians)
    {
        float unwrappedPhaseInRadians = phaseInRadians;

        while (Math.abs(unwrappedPhaseInRadians-prevPhaseInRadians)>0.5*TWOPI)
        {
            if (unwrappedPhaseInRadians>prevPhaseInRadians)
                unwrappedPhaseInRadians -= TWOPI;
            else
                unwrappedPhaseInRadians += TWOPI;
        }

        return unwrappedPhaseInRadians;
    }

    //Unwarps phaseInDegrees to range [lowestDegree, lowestDegree+360.0)
    public static float unwrapToRange(float phaseInDegrees, float lowestDegree)
    {
        float retVal = phaseInDegrees;
        if (retVal<lowestDegree)
        {
            while (retVal<lowestDegree)
                retVal += 360.0f;
        }
        else if (retVal>=lowestDegree+360.0f)
        {
            while (retVal>=lowestDegree+360.0f)
                retVal -= 360.0f;
        }

        return retVal;
    }

    public static double db2neper(double db)
    {
        return 20.0*db/Math.log(10.0);
    }

    public static double [] db2neper(double [] dbs)
    {
        double [] nepers = null;

        if (dbs!=null && dbs.length>0)
        {
            nepers = new double[dbs.length];

            for (int i=0; i<nepers.length; i++)
                nepers[i] = db2neper(dbs[i]);
        }

        return nepers;
    }

    public static double neper2db(double neper)
    {
        return (neper*Math.log(10.0))/20.0;
    }

    public static double [] neper2db(double [] nepers)
    {
        double [] dbs = null;

        if (nepers!=null && nepers.length>0)
        {
            dbs = new double[nepers.length];

            for (int i=0; i<dbs.length; i++)
                dbs[i] = neper2db(nepers[i]);
        }

        return dbs;
    }

    public static double neper2linear(double neper)
    {
        return Math.exp(neper);
    }

    public static double [] neper2linear(double [] nepers)
    {
        double [] lins = null;

        if (nepers!=null && nepers.length>0)
        {
            lins = new double[nepers.length];

            for (int i=0; i<lins.length; i++)
                lins[i] = neper2linear(nepers[i]);
        }

        return lins;
    }

    public static float[] sinc(float [] x, float N)
    {
        float [] y = null;

        if (x.length>0)
        {
            y = new float[x.length];
            for (int i=0; i<y.length; i++)
                y[i] = sinc(x[i], N);
        }

        return y;

    }

    public static float sinc(float x, float N)
    {
        return (float)(Math.sin(N*0.5*x)/(N*Math.sin(0.5*x)));
    }

    public static double sinc(double x, double N)
    {
        return Math.sin(N*0.5*x)/(N*Math.sin(0.5*x));
    }

    public static float[] sinc(float[] x)
    {
        float [] y = null;

        if (x.length>0)
        {
            y = new float[x.length];
            for (int i=0; i<y.length; i++)
                y[i] = sinc(2*x[i], (float)(0.5*MathUtils.TWOPI));
        }

        return y;
    }

    public static double[] sinc(double[] x)
    {
        double [] y = null;

        if (x.length>0)
        {
            y = new double[x.length];
            for (int i=0; i<y.length; i++)
                y[i] = sinc(2*x[i], 0.5*MathUtils.TWOPI);
        }

        return y;
    }

    public static float sinc(float x)
    {
        return sinc(2*x, (float)(0.5*MathUtils.TWOPI));
    }

    public static double sinc(double x)
    {
        return sinc(2*x, 0.5*MathUtils.TWOPI);
    }

    //Returns the index of the smallest element that is larger than %percentSmallerThan of the data in x
    //  It simply sorts the data in x and then finds the smallest value that is larger than 
    //  the [percentSmallerThan/100.0*(x.length-1)]th entry
    public static double getSortedValue(double [] x, double percentSmallerThan)
    {
        int retInd = -1;

        Vector<Double> v = new Vector<Double>();
        for (int i=0; i<x.length; i++)
            v.add(x[i]);

        Collections.sort(v);

        int index = (int)Math.floor(percentSmallerThan/100.0*(x.length-1)+0.5);
        index = Math.max(0, index);
        index = Math.min(index, x.length-1);

        return ((Double)(v.get(index))).doubleValue();
    }

    //Factorial design of all possible paths
    // totalItemsInNodes is a vector containing the total number of element at each node,
    // The output is the zero-based indices of elements in successive nodes covering all possible paths
    // from the first node to the last
    // Note that all elements of totalItemsInNodes should be greater than 0 (otherwise it is assumed that the corresponding element is 1)
    public static int [][] factorialDesign(int [] totalItemsInNodes)
    {
        int totalPaths = 1;

        int i, j;
        for (i=0; i<totalItemsInNodes.length; i++)
        {
            if (totalItemsInNodes[i]>0)
                totalPaths *= totalItemsInNodes[i];
        }

        int [][] pathInds = new int[totalPaths][totalItemsInNodes.length];
        int [] currentPath = new int[totalItemsInNodes.length];

        int count = 0;

        Arrays.fill(currentPath, 0);
        System.arraycopy(currentPath, 0, pathInds[count++], 0, currentPath.length);

        while (count<totalPaths)
        {
            for (i=currentPath.length-1; i>=0; i--)
            {
                if (currentPath[i]+1<Math.max(1, totalItemsInNodes[i]))
                {
                    currentPath[i]++;
                    break;
                }
                else
                    currentPath[i]=0;
            }

            System.arraycopy(currentPath, 0, pathInds[count], 0, currentPath.length);
            count++;
        }

        return pathInds;
    }

    //Returns the linearly mapped version of x which is in range xStart and xEnd in a new range
    // yStart and yEnd
    public static float linearMap(float x, float xStart, float xEnd, float yStart, float yEnd)
    {
        return (x-xStart)/(xEnd-xStart)*(yEnd-yStart)+yStart;
    }

    public static double linearMap(double x, double xStart, double xEnd, double yStart, double yEnd)
    {
        return (x-xStart)/(xEnd-xStart)*(yEnd-yStart)+yStart;
    }
    
    public static int linearMap(int x, int xStart, int xEnd, int yStart, int yEnd)
    {
        return (int)Math.floor(((double)x-xStart)/((double)xEnd-xStart)*(yEnd-yStart)+yStart + 0.5);
    }

    //In place sorting of array x, return value are the sorted 0-based indices 
    //Sorting is from lowest to highest
    public static int[] quickSort(double[] x)
    {
        int[] indices = new int[x.length];
        for (int i=0; i<x.length; i++)
            indices[i] = i;

        quickSort(x, indices);

        return indices;
    }

    //In place sorting of elements of array x between startIndex(included) and endIndex(included)
    //Sorting is from lowest to highest
    public static int[] quickSort(double[] x, int startIndex, int endIndex)
    {
        if (startIndex<0)
            startIndex=0;
        if (startIndex>x.length-1)
            startIndex=x.length-1;
        if (endIndex<startIndex)
            endIndex=startIndex;
        if (endIndex>x.length-1)
            endIndex=x.length-1;

        int[] indices = new int[endIndex-startIndex+1];
        double[] x2 = new double[endIndex-startIndex+1];
        int i;

        for (i=startIndex; i<=endIndex; i++)
        {
            indices[i-startIndex] = i;
            x2[i-startIndex] = x[i];
        }

        quickSort(x2, indices);

        for (i=startIndex; i<=endIndex; i++)
            x[i] = x2[i-startIndex];

        return indices;
    }


    //Sorts x, y is also sorted as x so it can be used to obtain sorted indices
    //Sorting is from lowest to highest
    public static void quickSort(double[] x, int[] y)
    {
        assert x.length==y.length;

        quickSort(x, y, 0, x.length-1);
    }

    //Sorting is from lowest to highest
    public static void quickSort(double[] x, int[] y, int startIndex, int endIndex)
    {   
        if(startIndex<endIndex)
        {
            int j = partition(x, y, startIndex, endIndex);
            quickSort(x, y, startIndex, j-1);
            quickSort(x, y, j+1, endIndex);
        } 
    }

    private static int partition(double[] x, int[] y, int startIndex, int endIndex) 
    {
        int i = startIndex; 
        int j = endIndex+1;
        double t;
        int ty;
        double pivot = x[startIndex];

        while(true)
        {
            do {
                ++i;
            } while(i<=endIndex && x[i]<=pivot);

            do {
                --j;
            } while(x[j]>pivot);

            if(i>=j) 
                break;

            t = x[i]; 
            ty = y[i];

            x[i] = x[j];
            y[i] = y[j];

            x[j] = t;
            y[j] = ty;
        }

        t = x[startIndex]; 
        ty = y[startIndex];

        x[startIndex] = x[j]; 
        y[startIndex] = y[j];

        x[j] = t;
        y[j] = ty;

        return j;
    }

    public static double[] normalizeToSumUpTo(double[] x, double sumUp)
    {
        return normalizeToSumUpTo(x, x.length, sumUp);
    }

    public static double[] normalizeToSumUpTo(double[] x, int len, double sumUp)
    {
        if (len>x.length)
            len=x.length;

        double[] y = new double[len];

        double total = 0.0;
        int i;

        for (i=0; i<len; i++)
            total += x[i];

        for (i=0; i<len; i++)
            y[i] = sumUp*(x[i]/total);

        return y;
    }

    public static double[] normalizeToRange(double[] x, int len, double minVal, double maxVal)
    {
        if (len>x.length)
            len=x.length;

        double[] y = new double[len];

        double xmin = MathUtils.min(x);
        double xmax = MathUtils.max(x);
        int i;

        if (xmax>xmin)
        {
            for (i=0; i<len; i++)
                y[i] = (x[i]-xmin)/(xmax-xmin)*(maxVal-minVal)+minVal;
        }
        else
        {
            for (i=0; i<len; i++)
                y[i] = (x[i]-xmin)+0.5*(minVal+maxVal);
        }   

        return y;
    }

    //Shifts mean value of x
    public static void adjustMean(double[] x, double newMean)
    {
        double currentMean = MathUtils.mean(x);

        for (int i=0; i<x.length; i++)
            x[i] = (x[i]-currentMean) + newMean;
    }
    //

    public static void adjustVariance(double[] x, double newVariance)
    {
        adjustStandardDeviation(x, Math.sqrt(newVariance));
    }

    //Assigns new standard deviation while keeping the mean value of x
    public static void adjustStandardDeviation(double[] x, double newStandardDeviation)
    {
        double currentMean = mean(x);
        double currentStdDev = standardDeviation(x, currentMean);

        for (int i=0; i<x.length; i++)
            x[i] = ((x[i]-currentMean)*newStandardDeviation/currentStdDev) + currentMean;
    }
    //

    public static double median(double[] x)
    {
        quickSort(x);

        int index = (int)Math.floor(0.5*x.length+0.5);
        if (index<0)
            index=0;
        if (index>x.length-1)
            index=x.length-1;

        return x[index];
    }

    //Returns 1/N sum_i=0^N-1(|x[i]|)
    public static double absMean(double[] x)
    {
        double m = 0.0;

        for (int i=0; i<x.length; i++)
            m += Math.abs(x[i]);

        m /= x.length;

        return m;
    }

    //Returns variances for each row
    public static double[] getVarianceRows(double[][] x)
    {   
        double[] variances = null;
        if (x!=null)
        {
            variances = new double[x.length];
            for (int i=0; i<x.length; i++)
                variances[i] = MathUtils.variance(x[i]);
        }

        return variances;
    }

    //Returns variances for each column
    public static double[] getVarianceCols(double[][] x)
    {   
        double[] variances = null;
        if (x!=null)
        {
            variances = new double[x[0].length];
            double[] tmp = new double[x.length];
            int i, j;
            for (j=0; j<x[0].length; j++)
            {
                for (i=0; i<x.length; i++)
                    tmp[i] = x[i][j];

                variances[j] = MathUtils.variance(tmp);
            }
        }

        return variances;
    }

    public static double getGaussianPdfValueConstantTerm(int featureDimension, double detCovarianceMatrix)
    {
        double constantTerm = Math.pow(2*Math.PI, 0.5*featureDimension);
        constantTerm *= Math.sqrt(detCovarianceMatrix);
        constantTerm = 1.0/constantTerm;

        return constantTerm;
    }
    
    public static double getGaussianPdfValueConstantTermLog(int featureDimension, double detCovarianceMatrix)
    {
        double constantTermLog = 0.5*featureDimension*Math.log(2*Math.PI); //double constantTerm = Math.pow(2*Math.PI, 0.5*featureDimension);
        constantTermLog += Math.log(Math.sqrt(detCovarianceMatrix));
        constantTermLog = -constantTermLog;

        return constantTermLog;
    }

    public static double getGaussianPdfValue(double[] x, double[] meanVector, double[] covarianceMatrix)
    {
        double constantTerm = getGaussianPdfValueConstantTerm(x.length, determinant(covarianceMatrix));

        return  getGaussianPdfValue(x, meanVector, covarianceMatrix, constantTerm);
    }

    //Diagonal covariance case
    // This version enables using pre-computed constant term
    public static double getGaussianPdfValue(double[] x, double[] meanVector, double[] covarianceMatrix, double constantTerm)
    {
        double P = 0.0;
        int i;

        for (i=0; i<x.length; i++)
            P += (x[i]-meanVector[i])*(x[i]-meanVector[i])/covarianceMatrix[i];

        P *= -0.5;

        P = constantTerm*Math.exp(P);

        return P;
    }
    
    //Log domain version
    public static double getGaussianPdfValueLog(double[] x, double[] meanVector, double[] covarianceMatrix, double constantTermLog)
    {
        double P = Double.MIN_VALUE;
        int i;
        double z;
        
        for (i=0; i<x.length; i++)
        {
            z = (x[i]-meanVector[i])*(x[i]-meanVector[i]);
            P = logAdd(P, Math.log(z)-Math.log(covarianceMatrix[i]));
        }

        P *= -0.5;

        P = constantTermLog + P;

        return P;
    }

    public static double getGaussianPdfValue(double[] x, double[] meanVector, double detCovarianceMatrix, double[][] inverseCovarianceMatrix)
    {
        double constantTerm = Math.pow(2*Math.PI, 0.5*x.length);
        constantTerm *= Math.sqrt(detCovarianceMatrix);
        constantTerm = 1.0/constantTerm;

        return getGaussianPdfValue(x, meanVector, inverseCovarianceMatrix, constantTerm);
    }

    //Full covariance case
    // This version enables using pre-computed constant term
    public static double getGaussianPdfValue(double[] x, double[] meanVector, double[][] inverseCovarianceMatrix, double constantTerm)
    {
        double[][] z = new double[1][x.length];
        z[0] = MathUtils.substract(x, meanVector);
        double[][] zT = MathUtils.transpoze(z);

        double[][] prod = MathUtils.matrixProduct(MathUtils.matrixProduct(z, inverseCovarianceMatrix), zT);

        double P = -0.5*prod[0][0];

        P = constantTerm*Math.exp(P);

        return P;
    }

    //Computes the determinant of a diagonal matrix
    public static double determinant(double[] diagonal) 
    { 
        double det = 1.0;
        for (int i=0; i<diagonal.length; i++)
            det *= diagonal[i];

        return det;
    }

    //Computes the determinant of a square matrix
    public static double determinant(double[][] matrix) 
    { 
        double result = 0;
        
        if (matrix.length==1)
            return determinant(matrix[0]);

        if (matrix.length==1 && matrix[0].length==1)
            return matrix[0][0];

        if(matrix.length == 2) 
        { 
            result = matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0]; 
            return result; 
        }

        for(int i = 0; i < matrix[0].length; i++) 
        { 
            double temp[][] = new double[matrix.length - 1][matrix[0].length - 1]; 
            for(int j = 1; j < matrix.length; j++) 
            {
                for(int k = 0; k < matrix[0].length; k++) 
                {
                    if(k < i) 
                        temp[j-1][k] = matrix[j][k];
                    else if(k>i) 
                        temp[j-1][k-1] = matrix[j][k];
                }
            }

            result += matrix[0][i]*Math.pow(-1,(double)i)*determinant(temp); 
        }

        return result;
    } 

    public static double[] random(int numSamples)
    {
        double[] x = null;

        if (numSamples>0)
        {
            x = new double[numSamples];
            for (int i=0; i<numSamples; i++)
                x[i] = Math.random();
        }

        return x;
    }

    //Returns inverse of diagonal vector
    public static double[] inverse(double[] x)
    {
        double[] invx = new double[x.length];

        for (int i=0; i<x.length; i++)
            invx[i] = 1.0/(x.length*x[i]);

        return invx;
    }
    
    //Square matrix inversion using LU decomposition
    public static double[][] inverse(double[][] matrix)
    {
        double[][] invMatrix = null;
        if (matrix.length==1) //Diagonal matrix
        {
            invMatrix = new double[1][matrix[0].length];
            invMatrix[0] = inverse(matrix[0]);
        }
        else //Full square matrix
        {
            invMatrix = new double[matrix.length][matrix.length];
            for (int i=0; i<matrix.length; i++)            
                System.arraycopy(matrix[i], 0, invMatrix[i], 0, matrix[i].length);

            inverseInPlace(invMatrix);
        }
        
        return invMatrix;
    }
    
    public static void inverseInPlace(double[][] matrix)
    {
        int dim = matrix.length;
        int i,j;
        
        double[][] y;
        double[] d = new double[1];
        double[] col;

        y = new double[dim][dim];

        int[] indices = new int[dim];
        col = new double[dim];

        luDecompose(matrix, dim, indices, d);
        for (j=0;j<dim;j++)
        {
            for (i=0;i<dim;i++) 
                col[i] = 0.0;
            col[j] = 1.0;
            luSubstitute(matrix, indices, col);
            for (i=0;i<dim;i++) 
                y[i][j] = col[i];
        }

        for (i=0;i<dim;i++)
            System.arraycopy(y[i], 0, matrix[i], 0, dim);
    }
    
    public static void luDecompose(double [][]a, int n, int[] indx, double[] d)
    {
        double TINY = 1e-20;
        int i,imax,j,k;
        double big,dum,sum,temp;
        double[] vv;
        imax=0;

        vv=new double[n];
        d[0]=1.0;
        
        for (i=1;i<=n;i++) 
        {
            big=0.0;
            for (j=1;j<=n;j++)
                if ((temp=Math.abs(a[i-1][j-1])) > big) 
                    big=temp;
            if (big == 0.0) 
                System.out.println("Singular matrix in routine ludcmp");
            vv[i-1]=1.0/big;
        }
        
        for (j=1;j<=n;j++) 
        {
            for (i=1;i<j;i++) 
            {
                sum=a[i-1][j-1];
                for (k=1;k<i;k++) 
                    sum -= a[i-1][k-1]*a[k-1][j-1];
                a[i-1][j-1]=sum;
            }
            
            big=0.0;
            
            for (i=j;i<=n;i++) 
            {
                sum=a[i-1][j-1];
                for (k=1;k<j;k++)
                    sum -= a[i-1][k-1]*a[k-1][j-1];
                a[i-1][j-1]=sum;
                if ( (dum=vv[i-1]*Math.abs(sum)) >= big) 
                {
                    big=dum;
                    imax=i;
                }
            }
            
            if (j != imax) 
            {
                for (k=1;k<=n;k++) 
                {
                    dum=a[imax-1][k-1];
                    a[imax-1][k-1]=a[j-1][k-1];
                    a[j-1][k-1]=dum;
                }
                d[0] = -d[0];
                vv[imax-1]=vv[j-1];
            }
            indx[j-1]=imax;
            if (a[j-1][j-1] == 0.0) 
                a[j-1][j-1]=TINY;
            if (j != n) 
            {
                dum=1.0/(a[j-1][j-1]);
                for (i=j+1;i<=n;i++) 
                    a[i-1][j-1] *= dum;
            }
        }
    }
    
    public static void luSubstitute(double[][] a, int[] indx, double b[])
    {
        int n = a.length;
        int i=0;
        int ii=0;
        int ip,j;
        double sum;

        for (i=1;i<=n;i++) 
        {
            ip=indx[i-1];
            sum=b[ip-1];
            b[ip-1]=b[i-1];
            if (ii!=0)
            {
                for (j=ii;j<=i-1;j++) 
                    sum -= a[i-1][j-1]*b[j-1];
            }
            else if (sum!=0.0) 
                ii=i;
            b[i-1]=sum;
        }
        for (i=n;i>=1;i--) 
        {
            sum=b[i-1];
            for (j=i+1;j<=n;j++) 
                sum -= a[i-1][j-1]*b[j-1];
            
            b[i-1]=sum/a[i-1][i-1];
        }
    }
    //
    
    public static double logAdd(double x, double y) 
    {
        if (y > x) 
        {
            double temp = x;
            x = y;
            y = temp;
        }

        if (x==Double.NEGATIVE_INFINITY)
            return x;

        double negDiff = y - x;
        if (negDiff < -20)
            return x;

        return x + Math.log(1.0 + Math.exp(negDiff));
    }

    public static void main(String[] args)
    {
        double[] x = new double[4];
        double[] y = new double[4];
        int i, j;
        for (i=0; i<x.length; i++)
            x[i] = i;
        for (j=0; j<y.length; j++)
            y[j] = j+1;
        double[] z1 = MathUtils.add(x, y);
        double[] z2 = MathUtils.substract(x, y);
        double[] z3 = MathUtils.add(x, 0.5);
        double[] z4 = MathUtils.substract(y, -10);
        double[][] x1= new double[x.length][1];
        double[][] y1= new double[1][y.length];
        for (i=0; i<x.length; i++)
            x1[i][0] = x[i];
        System.arraycopy(y, 0, y1[0], 0, y.length);
        double[][] z5 = MathUtils.matrixProduct(x1, y1);
        double[][] z6 = MathUtils.vectorProduct(x, true, y, false);

        double[][] xx = new double[4][4];
        xx[0][0] = 1;
        xx[0][1] = 2;
        xx[0][2] = 3;
        xx[0][3] = 4;
        xx[1][0] = 2;
        xx[1][1] = 5;
        xx[1][2] = 7;
        xx[1][3] = 9;
        xx[2][0] = 0;
        xx[2][1] = -1;
        xx[2][2] = -4;
        xx[2][3] = 2;
        xx[3][0] = -10;
        xx[3][1] = 11;
        xx[3][2] = -11;
        xx[3][3] = 10;
        
        double m=mean(xx[0]);
        
        double[][] z7 = inverse(xx);
        double[][] z8 = matrixProduct(xx, z7);

        System.out.println("Test completed...");
    }
}