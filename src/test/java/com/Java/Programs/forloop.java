package com.Java.Programs;

public class forloop {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//madam
		String str="Javalearning";
		//char [] arr=str.toCharArray();
		System.out.println(str.length());
		for (int i=0;i<str.length();i++){
			System.out.println(str.charAt(i));
			System.out.println(str.substring(4));
		}
		System.out.println("----------------------");
		/*System.out.println(arr.length-1);
		for (int j=arr.length-1;j>=0;j--){
			System.out.println(arr[j]);
		}*/
	}

}
