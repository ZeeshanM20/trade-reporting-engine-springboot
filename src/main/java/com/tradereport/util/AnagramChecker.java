package com.tradereport.util;

import java.util.Arrays;

public class AnagramChecker {
  
  public static boolean isAnagram(String str1, String str2) {
    // If lengths don't match or any string is null, return false.
    if (str1 == null || str2 == null || str1.length() != str2.length()) {
      return false;
    }

    // Convert strings to char arrays
    char[] arr1 = str1.toCharArray();
    char[] arr2 = str2.toCharArray();

    // Sort the arrays
    Arrays.sort(arr1);
    Arrays.sort(arr2);

    // Check if the sorted arrays are equal
    return Arrays.equals(arr1, arr2);
  }

}
