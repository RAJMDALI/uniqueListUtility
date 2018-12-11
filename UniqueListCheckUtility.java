/**
 * 
 */
package com.molecular.connection;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author RAJMDALI
 *
 */
public class UniqueListCheckUtility {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		BufferedReader bufferedReader = null;
		String filePath = "D:\\friendList.txt";
		Map<String, String> friendMap = new HashMap<>();
		Map<String, Integer> uniqueFriendMap = new HashMap<>();
		try {
			bufferedReader = new BufferedReader(new FileReader(filePath));
			String readLine = bufferedReader.readLine();
			while (readLine != null) {
				String[] friendGroup = readLine.split(" ");
				if(friendGroup.length==2){
					friendMap.put(friendGroup[0], friendGroup[1]);
					if (friendMap.containsValue(friendGroup[0])) {
						populateUniqueFriendGroup(uniqueFriendMap, generateFriendGroupPair(friendGroup));
					}
				}
				readLine = bufferedReader.readLine();
			}
			
			System.out.println("Friend group in the File: " + friendMap);
			System.out.println("Unique list of the friend group," + uniqueFriendMap);
			
		} catch (IOException e) {
			String errorId = UUID.randomUUID().toString();
			System.out.println("IOException occured while reading data from file location:" + filePath	+ " with errorId:" + errorId + " due to:" + e.getMessage());
			e.printStackTrace();
		} finally {
			if (null != bufferedReader) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					String errorId = UUID.randomUUID().toString();
					System.out.println("IOException occured while closing file reader for file:" + filePath	+ " with errorId:" + errorId + " due to:" + e.getMessage());
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * @param friendGroup
	 * @return
	 */
	public static String generateFriendGroupPair(String[] friendGroup) {
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append(friendGroup[1]);
		stringBuilder.append("#");
		stringBuilder.append(friendGroup[0]);
		
		return stringBuilder.toString();
	}

	/**
	 * @param uniqueFriendMap
	 * @param pair
	 */
	public static void populateUniqueFriendGroup(Map<String, Integer> uniqueFriendMap, String pair) {
		Integer totalCount = uniqueFriendMap.get(pair);
		if (null == totalCount) {
			totalCount = 1;
		}
		uniqueFriendMap.put(pair, ++totalCount);
	}

}
