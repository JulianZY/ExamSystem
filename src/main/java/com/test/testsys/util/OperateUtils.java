/**
 * 
 */
package com.test.testsys.util;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
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
		Random random = new Random(System.currentTimeMillis());
		Set<Integer> result = new LinkedHashSet<Integer>();
		int number = 0;
		int capMax = num > 50 ? (int)(num * 0.95) : (int)(num * 0.5); //随机数目达到99%剩下的数字用遍历获取
		if(num * 1.0 / maxNum < 0.4 ) {  // 要抽取的数目占总数比例低于40%，直接全部随机生成
			capMax = num;
		}
		while(result.size() < capMax) {
			number = random.nextInt(maxNum);
			if(number < maxNum) {
				result.add(number);
			}			
		}
		int flag = 0;
		for(int pre = maxNum/2,post = maxNum/2 + 1;result.size()<num;) {
			if(flag%2 == 0) {
				while(result.contains(pre) && pre >=0 ) {
					--pre;
				}
				if(pre >= 0) {
					result.add(pre);
				}
			} else {
				while(result.contains(post) && post < maxNum) {
					++post;
				}
				if(post < maxNum) {
					result.add(post);
				}
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
