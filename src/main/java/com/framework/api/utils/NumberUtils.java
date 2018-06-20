package com.framework.api.utils;

import java.util.Random;

public class NumberUtils {

	public static long getTotalNumberOfPages(long size, long perPage) {
		long totalPages = size/perPage;
		if(size%perPage!=0) {
			totalPages++;
		}
		return totalPages;
	}

	public static Long getCurrentNumberOfPages(long perPage, long offset) {
		long currentPage = offset/perPage;
		if(offset%perPage!=0) {
			currentPage++;
		}
		return currentPage;
	}
	public static Long getNextNumberOfPages(long size,long perPage, long offset) {
		long currentPage = getCurrentNumberOfPages(perPage, offset);
		long totalPages = getTotalNumberOfPages(size, perPage);
		long nextPage = totalPages-(currentPage+1);
		if(nextPage>0) {
			return nextPage;
		}
		return null;
	}
	public static Long getPreviousNumberOfPages(long size,long perPage, long offset) {
		long currentPage = getCurrentNumberOfPages(perPage, offset);
		long previousPage = currentPage-1;
		if(previousPage>=0) {
			return previousPage;
		}
		return null;
	}
	public static int randInt(int min, int max) {
	    Random rand=new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
//	    return randomNum;
	    return 123456;
	}
	public static void main(String[] args) {
		System.out.println("Random number:"+randInt(100000, 999999));
	}
}
