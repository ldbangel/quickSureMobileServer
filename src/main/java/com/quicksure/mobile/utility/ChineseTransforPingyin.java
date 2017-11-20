package com.quicksure.mobile.utility;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class ChineseTransforPingyin {
	  /**  
	    * 获取汉字串拼音，英文字符不变  
	    *  
	    * @param chinese 汉字串  
	    * @return 汉语拼音  
	    */  
	   public static String chinsesToPinyin(String chinese) {   
	           StringBuffer pybf = new StringBuffer();   
	           char[] arr = chinese.toCharArray();   
	           HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();   
	           defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);   
	           defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);   
	           for (int i = 0; i < arr.length; i++) {   
	                   if (arr[i] > 128) {   
	                           try {   
	                                   pybf.append(PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat)[0]);   
	                           } catch (BadHanyuPinyinOutputFormatCombination e) {   
	                                   e.printStackTrace();   
	                           }   
	                   } else {   
	                           pybf.append(arr[i]);   
	                   }   
	           }   
	           return pybf.toString();   
	   }   
}
