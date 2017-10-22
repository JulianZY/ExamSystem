/**
 * 
 */
package com.test.testsys.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 操作工具类
 * @author Julia
 *
 */
public class OperateUtils {
	
	/**
	 * 生成指定大小的随机数序列
	 * @param num 生成随机数的个数
	 * @param maxNum 生成随机数大小范围 [0,maxNum)
	 * */
	public static List<Integer> generateRandomArray(int num,int maxNum){
		if(num < 0 || maxNum < 0 || num > maxNum) {
			return null;
		}
		Set<Integer> result = new HashSet<Integer>();
		int number = 0;
		int capMax = num > 50 ? (int)(num * 0.95) : (int)(num * 0.5); //随机数目达到99%剩下的数字用遍历获取
		while(result.size() < capMax) {
			number = (int)(Math.random()*maxNum);
			if(number < maxNum) {
				result.add(number);
			}			
		}
		int flag = 0;
		for(int pre = maxNum/2,post = maxNum/2 + 1;pre>=0&&post<maxNum&&result.size()<num;) {
			if(flag%2 == 0) {
				while(result.contains(pre)) {
					--pre;
				}
				result.add(pre);
			} else {
				while(result.contains(post)) {
					++post;
				}
				result.add(post);
			}
			++flag;
		}
		for(int i = 0;i<num&&result.size()<num;++i) {
			if(!result.contains(i)) {
				result.add(i);
			}
		}
		return new ArrayList<Integer>(result);
	}
	
}
