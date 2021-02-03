package com.example.mylab;

import java.util.Arrays;
import java.util.Scanner;

public class loopsMethodsArrays {
	public static void main(String[] args) {
		Scanner keyb = new Scanner(System.in);	
		System.out.println("Enter a string to encrypt: ");
		String str = keyb.next();
		System.out.println("What value do you want to encrypt with?");
		int value = keyb.nextInt();
		String encryption = encrypt(str,value);
		System.out.println(str + " encrypted is " + encryption);
		System.out.println();
		
		String decryption = decrypt(encryption,value);
		System.out.println("Using "+ encryption + "to test the decryption with "+ value + "...");
		System.out.println("The decrypted string is " + decryption );
		System.out.println();

		System.out.println("Type in the length of the array: ");
		int number = keyb.nextInt();
		System.out.println("Creating an int array of length " + number);
		int[] arr = generateArray(number);
		System.out.println("The array is " + Arrays.toString(arr));
		int sum = sumArray(arr);
		System.out.println("The sum of the array is " + sum);
		System.out.println();
		
		System.out.println("Enter a number to search for in the previous array: ");
		int searchvalue = keyb.nextInt();
		boolean bool = searchArray(arr, searchvalue);
		if (bool == true){
			System.out.println("The array contains " + searchvalue);
		}
		else {
			System.out.println("The array does not contain " + searchvalue);
		}
	}
	
	public static String encrypt(String str1, int value1){
		String encryption = "";
		for(int i = 0; i < str1.length(); ++i) {
			char charTemp = str1.charAt(i);
			int intTemp = (int)charTemp;
			intTemp += value1;
			encryption += (char)intTemp;
		}
		return encryption;	
	}
	
	public static String decrypt(String str2, int value2) {
		String decryption = "";
		for(int i = 0; i < str2.length(); ++i) {
			char charTemp = str2.charAt(i);
			int intTemp = (int)charTemp;
			intTemp -= value2;
			decryption += (char)intTemp;
		}
			return decryption;	
	}
	
	public static int[] generateArray(int number) {
		int[] array = new int[number];
		for(int i = 0; i < array.length; ++i) {
			array[i] = (int)(Math.random()*100);
		}
		return array;
	}
	public static int sumArray(int[] array) {
		int sum = 0;
		for(int i = 0; i < array.length; ++i) {
			sum += array[i];
		}		
		return sum;
	}
	
	public static boolean searchArray(int[] array, int searchvalue) {
		boolean isFlag = false;
		for(int i = 0; i < array.length; ++i) {
			if(searchvalue == array[i]) {
				isFlag = true;
		} 		
	}
		return isFlag;
		}		
}
