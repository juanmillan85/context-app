/*=================================================================
AmbieSense Context Software GPL Source Code.

Copyright (C) 2005 and onwards by AmbieSense Limited, 
 and/or its subsidiaries and co-founders. AmbieSense Limited
 is an R&D-focused company incorporated in Scotland.

This file is part of the AmbieSense Context Software GPL Source Code.

The AmbieSense Context Software GPL Source Code is free software: 
you can redistribute it and/or modify it under the terms of the 
GNU General Public License Version 3 as published by
the Free Software Foundation.

The AmbieSense Context Software Source Code is distributed in 
the hope that it will be useful, but WITHOUT ANY WARRANTY; 
without even the implied warranty of MERCHANTABILITY or 
FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with The AmbieSense Context Software Source Code. 
If not, see <http://www.gnu.org/licenses/>.

In addition, the The AmbieSense Context Software Source Code is also 
subject to certain additional terms. You should have received a copy 
of these additional terms immediately following the terms and 
conditions of the GNU General Public License which 
accompanied the The AmbieSense Context Software Source Code. 
If not, please request a copy in writing from id Software at the address below.

If you have questions concerning this license or the applicable 
additional terms, you may contact in writing: 
AmbieSense Limited, 7 Queens Gardens, Aberdeen, 
AB25 4YD, Scotland; or by email: 
kontactATambiesense.com (replace "AT" with "@").
============================================================ */

package com.ambiesense.util;


public class Relevance {
	/*
	 * Levenshtein Distance
	 * by Michael Gilleland
	 * http://www.merriampark.com/ld.htm
	 * 
	 * What is Levenshtein Distance?
	 * 
	 * Levenshtein distance (LD) is a measure of the similarity between two strings, which we will refer to as the source string (s) and the target string (t). The distance is the number of deletions, insertions, or substitutions required to transform s into t. For example,

	 * If s is "test" and t is "test", then LD(s,t) = 0, because no transformations are needed. The strings are already identical.
	 * If s is "test" and t is "tent", then LD(s,t) = 1, because one substitution (change "s" to "n") is sufficient to transform s into t. 
	 * 
	 * The greater the Levenshtein distance, the more different the strings are.
	 * 
	 * Levenshtein distance is named after the Russian scientist Vladimir Levenshtein, 
	 * who devised the algorithm in 1965. 
	 * If you can't spell or pronounce Levenshtein, 
	 * the metric is also sometimes called edit distance.
	 * 
	 * The Levenshtein distance algorithm has been used in:
	 * 
	 * - Spell checking
	 * - Speech recognition
	 * - DNA analysis
	 * - Plagiarism detection 
	 * 
	 */
	private static int minimum(int a, int b, int c) {
		int mi;
		mi = a;
		if (b < mi) {
			mi = b;
		}
		if (c < mi) {
			mi = c;
		}
		return mi;
	}
	//*****************************
	// Compute Levenshtein distance
	//*****************************
	public static int getEditDistance(String source, String target) {
		int d[][]; // matrix
		int n; // length of s
		int m; // length of t
		int i; // iterates through s
		int j; // iterates through t
		char s_i; // ith character of s
		char t_j; // jth character of t
		int cost; // cost

		// Step 1:
		// Set n to be the length of s.
		// Set m to be the length of t.
		// If n = 0, return m and exit.
		// If m = 0, return n and exit.
		// Construct a matrix containing 0..m rows and 0..n columns. 

		n = source.length ();
		m = target.length ();
		if (n == 0) {
			return m;
		}
		if (m == 0) {
			return n;
		}
		d = new int[n+1][m+1];

		// Step 2:
		// Initialize the first row to 0..n.
		// Initialize the first column to 0..m.

		for (i = 0; i <= n; i++) {
			d[i][0] = i;
		}
		for (j = 0; j <= m; j++) {
			d[0][j] = j;
		}

		// Step 3:
		// Examine each character of s (i from 1 to n).

		for (i = 1; i <= n; i++) {
			s_i = source.charAt (i - 1);

			// Step 4:
			// Examine each character of t (j from 1 to m).

			for (j = 1; j <= m; j++) {
				t_j = target.charAt (j - 1);

				// Step 5:
				// If s[i] equals t[j], the cost is 0.
				// If s[i] doesn't equal t[j], the cost is 1.

				if (s_i == t_j) {
					cost = 0;
				}
				else {
					cost = 1;
				}

				// Step 6:
				// Set cell d[i,j] of the matrix equal to the minimum of:
				//	a. The cell immediately above plus 1: d[i-1,j] + 1.
				//	b. The cell immediately to the left plus 1: d[i,j-1] + 1.
				//	c. The cell diagonally above and to the left plus the cost: 
				//        d[i-1,j-1] + cost.

				d[i][j] = minimum(d[i-1][j]+1, d[i][j-1]+1, d[i-1][j-1] + cost);

			}
		}

		// Step 7:
		// After the iteration steps (3, 4, 5, 6) are complete, 
		// the distance is found in cell d[n,m].

		return d[n][m];
	}

	public static float relevance(Object source, Object target, double maxDistance){
		if (source instanceof String && target instanceof String){
			return relevance((String)source, (String)target, maxDistance);
		}
		else if (source instanceof Double && target instanceof Double){
			return relevance(((Double)source).doubleValue(), ((Double)target).doubleValue(), maxDistance);		
		}
		else if (source instanceof Long && target instanceof Long){
			return relevance(((Long)source).doubleValue(), ((Long)target).doubleValue(), maxDistance);		
		}
		else if (source instanceof Boolean && target instanceof Boolean){
			return relevance(((Boolean)source).booleanValue(), ((Boolean)target).booleanValue());			
		}
		return 0;
	}
	
	public static float relevance(double source, double target, double maxDistance){
		double d = Math.abs(source - target);
		double md = Math.abs(maxDistance);
		if (md == 0){
			// No distance will be accepted, i.e. distance must be zero
			// in order to return 1. Otherwise 0 will be returned
			if (d == 0){
				return 1;
			} else{
				return 0;
			}
		} else{
			// A max distance has been specified, i.e. if the 
			// distance between the values are less than the max distance allowed,
			// then a number between 1 and 0.5 will be returned, otherwise
			// 0 will be returned.
			if (d <= md){
				return (float) Math.abs((md - (d / 2)) / md);					
			} else{
				return 0;
			}
		}
	}

	public static float relevance(boolean source, boolean target){
		if (source == target){
			return 1;
		} else{
			return 0;
		}
	}

	public static float relevance(long source, long target, double maxDistance){
		double d = Math.abs(source - target);
		double md = Math.abs(maxDistance);
		if (md == 0){
			// No distance will be accepted, i.e. distance must be zero
			// in order to return 1. Otherwise 0 will be returned
			if (d == 0){
				return 1;
			} else{
				return 0;
			}
		} else{
			// A max distance has been specified, i.e. if the 
			// distance between the values are less than the max distance allowed,
			// then a number between 1 and 0.5 will be returned, otherwise
			// 0 will be returned.
			if (d <= md){
				return (float) Math.abs((md - (d / 2)) / md);					
			} else{
				return 0;
			}
		}
	}

	public static float relevance(String source, String target, double maxDistance){
		double d = (double) getEditDistance(source, target);
		double md = (double) Math.abs(maxDistance);
		if (md == 0){
			// No distance will be accepted, i.e. distance must be zero
			// in order to return 1. Otherwise 0 will be returned
			if (d == 0){
				return 1;
			} else{
				return 0;
			}
		} else{
			// A max distance has been specified, i.e. if the 
			// distance between the values are less than the max distance allowed,
			// then a number between 1 and 0.5 will be returned, otherwise
			// 0 will be returned.
			if (d <= md){
				return (float) Math.abs((md - (d / 2)) / md);					
			} else{
				return 0;
			}
		}
	}
}