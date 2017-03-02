package com.steven.babyiyo.utlis;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 楠岃瘉宸ュ叿绫�
 * @author Administrator
 *
 */
public class CheckUtils {
	
	
	private CheckUtils()  
    {  
        /* cannot be instantiated */  
        throw new UnsupportedOperationException("cannot be instantiated");  
    } 
	
	// ------------------甯搁噺瀹氫箟
	/**
	 * Email姝ｅ垯琛ㄨ揪寮�
	 * "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$"
	 * ;
	 */
	// public static final String EMAIL =
	// "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";;
	public static final String EMAIL = "\\w+(\\.\\w+)*@\\w+(\\.\\w+)+";
	/**
	 * 鐢佃瘽鍙风爜姝ｅ垯琛ㄨ揪寮�
	 * (^(\d{2,4}[-_锛嶁�]?)?\d{3,8}([-_锛嶁�]?\d{3,8})?([-_锛嶁�]?\d{1,7})?$)|
	 * (^0?1[35]\d{9}$)
	 */
	public static final String PHONE = "(^(\\d{2,4}[-_锛嶁�]?)?\\d{3,8}([-_锛嶁�]?\\d{3,8})?([-_锛嶁�]?\\d{1,7})?$)|(^0?1[35]\\d{9}$)";
	/**
	 * 鎵嬫満鍙风爜姝ｅ垯琛ㄨ揪寮�^(13[0-9]|15[0-9]|18[0-9])\d{8}$
	 */
	public static final String MOBILE = "^(13[0-9]|15[0-9]|18[0-9])\\d{8}$";

	/**
	 * Integer姝ｅ垯琛ㄨ揪寮�^-?(([1-9]\d*$)|0)
	 */
	public static final String INTEGER = "^-?(([1-9]\\d*$)|0)";
	/**
	 * 姝ｆ暣鏁版鍒欒〃杈惧紡 >=0 ^[1-9]\d*|0$
	 */
	public static final String INTEGER_NEGATIVE = "^[1-9]\\d*|0$";
	/**
	 * 璐熸暣鏁版鍒欒〃杈惧紡 <=0 ^-[1-9]\d*|0$
	 */
	public static final String INTEGER_POSITIVE = "^-[1-9]\\d*|0$";
	/**
	 * Double姝ｅ垯琛ㄨ揪寮�^-?([1-9]\d*\.\d*|0\.\d*[1-9]\d*|0?\.0+|0)$
	 */
	public static final String DOUBLE = "^-?([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0)$";
	/**
	 * 姝ouble姝ｅ垯琛ㄨ揪寮�>=0 ^[1-9]\d*\.\d*|0\.\d*[1-9]\d*|0?\.0+|0$銆�
	 */
	public static final String DOUBLE_NEGATIVE = "^[1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0$";
	/**
	 * 璐烡ouble姝ｅ垯琛ㄨ揪寮�<= 0 ^(-([1-9]\d*\.\d*|0\.\d*[1-9]\d*))|0?\.0+|0$
	 */
	public static final String DOUBLE_POSITIVE = "^(-([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*))|0?\\.0+|0$";
	/**
	 * 骞撮緞姝ｅ垯琛ㄨ揪寮�^(?:[1-9][0-9]?|1[01][0-9]|120)$ 鍖归厤0-120宀�
	 */
	public static final String AGE = "^(?:[1-9][0-9]?|1[01][0-9]|120)$";
	/**
	 * 閭紪姝ｅ垯琛ㄨ揪寮�[0-9]\d{5}(?!\d) 鍥藉唴6浣嶉偖缂�
	 */
	public static final String CODE = "[0-9]\\d{5}(?!\\d)";
	/**
	 * 鍖归厤鐢辨暟瀛椼�26涓嫳鏂囧瓧姣嶆垨鑰呬笅鍒掔嚎缁勬垚鐨勫瓧绗︿覆 ^\w+$
	 */
	public static final String STR_ENG_NUM_ = "^\\w+$";
	/**
	 * 鍖归厤鐢辨暟瀛楀拰26涓嫳鏂囧瓧姣嶇粍鎴愮殑瀛楃涓�^[A-Za-z0-9]+$
	 */
	public static final String STR_ENG_NUM = "^[A-Za-z0-9]+";
	/**
	 * 鍖归厤鐢�6涓嫳鏂囧瓧姣嶇粍鎴愮殑瀛楃涓�^[A-Za-z]+$
	 */
	public static final String STR_ENG = "^[A-Za-z]+$";
	/**
	 * 杩囨护鐗规畩瀛楃涓叉鍒�regEx=
	 * "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~锛丂#锟�鈥︹�&*锛堬級鈥斺�+|{}銆愩�鈥橈紱锛氣�鈥溾�銆傦紝銆侊紵]";
	 */
	public static final String STR_SPECIAL = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~锛丂#锟�鈥︹�&*锛堬級鈥斺�+|{}銆愩�鈥橈紱锛氣�鈥溾�銆傦紝銆侊紵]";
	/***
	 * 鏃ユ湡姝ｅ垯 鏀寔锛�YYYY-MM-DD YYYY/MM/DD YYYY_MM_DD YYYYMMDD YYYY.MM.DD鐨勫舰寮�
	 */
	public static final String DATE_ALL = "((^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._]?)(10|12|0?[13578])([-\\/\\._]?)(3[01]|[12][0-9]|0?[1-9])$)"
			+ "|(^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._]?)(11|0?[469])([-\\/\\._]?)(30|[12][0-9]|0?[1-9])$)"
			+ "|(^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._]?)(0?2)([-\\/\\._]?)(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|(^([3579][26]00)"
			+ "([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)"
			+ "|(^([1][89][0][48])([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|(^([2-9][0-9][0][48])([-\\/\\._]?)"
			+ "(0?2)([-\\/\\._]?)(29)$)"
			+ "|(^([1][89][2468][048])([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|(^([2-9][0-9][2468][048])([-\\/\\._]?)(0?2)"
			+ "([-\\/\\._]?)(29)$)|(^([1][89][13579][26])([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|"
			+ "(^([2-9][0-9][13579][26])([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$))";
	/***
	 * 鏃ユ湡姝ｅ垯 鏀寔锛�YYYY-MM-DD
	 */
	public static final String DATE_FORMAT1 = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";

	/**
	 * URL姝ｅ垯琛ㄨ揪寮�鍖归厤 http www ftp
	 */
	public static final String URL = "^(http|www|ftp|)?(://)?(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*((:\\d+)?)(/(\\w+(-\\w+)*))*(\\.?(\\w)*)(\\?)?"
			+ "(((\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*(\\w*%)*(\\w*\\?)*"
			+ "(\\w*:)*(\\w*\\+)*(\\w*\\.)*"
			+ "(\\w*&)*(\\w*-)*(\\w*=)*)*(\\w*)*)$";

	/**
	 * 韬唤璇佹鍒欒〃杈惧紡
	 */
	public static final String IDCARD = "((11|12|13|14|15|21|22|23|31|32|33|34|35|36|37|41|42|43|44|45|46|50|51|52|53|54|61|62|63|64|65)[0-9]{4})"
			+ "(([1|2][0-9]{3}[0|1][0-9][0-3][0-9][0-9]{3}"
			+ "[Xx0-9])|([0-9]{2}[0|1][0-9][0-3][0-9][0-9]{3}))";

	/**
	 * 鏈烘瀯浠ｇ爜
	 */
	public static final String JIGOU_CODE = "^[A-Z0-9]{8}-[A-Z0-9]$";

	/**
	 * 鍖归厤鏁板瓧缁勬垚鐨勫瓧绗︿覆 ^[0-9]+$
	 */
	public static final String STR_NUM = "^[0-9]+$";

	// //------------------楠岃瘉鏂规硶
	/**
	 * 鍒ゆ柇瀛楁鏄惁涓虹┖ 绗﹀悎杩斿洖ture
	 *
	 * @param str
	 * @return boolean
	 */
	public static synchronized boolean StrIsNull(String str) {
		return null == str || str.trim().length() <= 0 ? true : false;
	}

	/**
	 * 鍒ゆ柇瀛楁鏄潪绌�绗﹀悎杩斿洖ture
	 *
	 * @param str
	 * @return boolean
	 */
	public static boolean StrNotNull(String str) {
		return !StrIsNull(str);
	}

	/**
	 * 瀛楃涓瞡ull杞┖
	 *
	 * @param str
	 * @return boolean
	 */
	public static String doNullStr(String str) {
		return StrIsNull(str) ? "" : str;
	}

	/**
	 * 瀛楃涓瞡ull璧嬪�榛樿鍊�
	 *
	 * @param str
	 *            鐩爣瀛楃涓�
	 * @param defaut
	 *            榛樿鍊�
	 * @return String
	 */
	public static String doNullStr(String str, String defaut) {
		return StrIsNull(str) ? defaut : str;
	}

	/**
	 * 鍒ゆ柇瀛楁鏄惁涓篍mail 绗﹀悎杩斿洖ture
	 *
	 * @param str
	 * @return boolean
	 */
	public static boolean isEmail(String str) {
		return Regular(str, EMAIL);
	}

	/**
	 * 鍒ゆ柇鏄惁涓虹數璇濆彿鐮�绗﹀悎杩斿洖ture
	 *
	 * @param str
	 * @return boolean
	 */
	public static boolean isPhone(String str) {
		return Regular(str, PHONE);
	}

	/**
	 * 鍒ゆ柇鏄惁涓烘墜鏈哄彿鐮�绗﹀悎杩斿洖ture
	 *
	 * @param str
	 * @return boolean
	 */
	public static boolean isMobile(String str) {
		return Regular(str, MOBILE);
	}

	/**
	 * 鍒ゆ柇鏄惁涓篣rl 绗﹀悎杩斿洖ture
	 *
	 * @param str
	 * @return boolean
	 */
	public static boolean isUrl(String str) {
		return Regular(str, URL);
	}

	/**
	 * 鍒ゆ柇瀛楁鏄惁涓烘暟瀛�姝ｈ礋鏁存暟 姝ｈ礋娴偣鏁�绗﹀悎杩斿洖ture
	 *
	 * @param str
	 * @return boolean
	 */
	public static boolean isNumber(String str) {
		return Regular(str, DOUBLE);
	}

	/**
	 * 鍒ゆ柇瀛楁鏄惁涓篒NTEGER 绗﹀悎杩斿洖ture
	 *
	 * @param str
	 * @return boolean
	 */
	public static boolean isInteger(String str) {
		return Regular(str, INTEGER);
	}

	/**
	 * 鍒ゆ柇瀛楁鏄惁涓烘鏁存暟姝ｅ垯琛ㄨ揪寮�>=0 绗﹀悎杩斿洖ture
	 *
	 * @param str
	 * @return boolean
	 */
	public static boolean isINTEGER_NEGATIVE(String str) {
		return Regular(str, INTEGER_NEGATIVE);
	}

	/**
	 * 鍒ゆ柇瀛楁鏄惁涓鸿礋鏁存暟姝ｅ垯琛ㄨ揪寮�<=0 绗﹀悎杩斿洖ture
	 *
	 * @param str
	 * @return boolean
	 */
	public static boolean isINTEGER_POSITIVE(String str) {
		return Regular(str, INTEGER_POSITIVE);
	}

	/**
	 * 鍒ゆ柇瀛楁鏄惁涓篋OUBLE 绗﹀悎杩斿洖ture
	 *
	 * @param str
	 * @return boolean
	 */
	public static boolean isDouble(String str) {
		return Regular(str, DOUBLE);
	}

	/**
	 * 鍒ゆ柇瀛楁鏄惁涓烘娴偣鏁版鍒欒〃杈惧紡 >=0 绗﹀悎杩斿洖ture
	 *
	 * @param str
	 * @return boolean
	 */
	public static boolean isDOUBLE_NEGATIVE(String str) {
		return Regular(str, DOUBLE_NEGATIVE);
	}

	/**
	 * 鍒ゆ柇瀛楁鏄惁涓鸿礋娴偣鏁版鍒欒〃杈惧紡 <=0 绗﹀悎杩斿洖ture
	 *
	 * @param str
	 * @return boolean
	 */
	public static boolean isDOUBLE_POSITIVE(String str) {
		return Regular(str, DOUBLE_POSITIVE);
	}

	/**
	 * 鍒ゆ柇瀛楁鏄惁涓烘棩鏈�绗﹀悎杩斿洖ture
	 *
	 * @param str
	 * @return boolean
	 */
	public static boolean isDate(String str) {
		return Regular(str, DATE_ALL);
	}

	/**
	 * 楠岃瘉2010-12-10
	 *
	 * @param str
	 * @return
	 */
	public static boolean isDate1(String str) {
		return Regular(str, DATE_FORMAT1);
	}

	/**
	 * 鍒ゆ柇瀛楁鏄惁涓哄勾榫�绗﹀悎杩斿洖ture
	 *
	 * @param str
	 * @return boolean
	 */
	public static boolean isAge(String str) {
		return Regular(str, AGE);
	}

	/**
	 * 鍒ゆ柇瀛楁鏄惁瓒呴暱 瀛椾覆涓虹┖杩斿洖fasle, 瓒呰繃闀垮害{leng}杩斿洖ture 鍙嶄箣杩斿洖false
	 *
	 * @param str
	 * @param leng
	 * @return boolean
	 */
	public static boolean isLengOut(String str, int leng) {
		return StrIsNull(str) ? false : str.trim().length() > leng;
	}

	/**
	 * 鍒ゆ柇瀛楁鏄惁涓鸿韩浠借瘉 绗﹀悎杩斿洖ture
	 *
	 * @param str
	 * @return boolean
	 */
	public static boolean isIdCard(String str) {
		if (StrIsNull(str))
			return false;
		if (str.trim().length() == 15 || str.trim().length() == 18) {
			return Regular(str, IDCARD);
		} else {
			return false;
		}

	}

	/**
	 * 鍒ゆ柇瀛楁鏄惁涓洪偖缂�绗﹀悎杩斿洖ture
	 *
	 * @param str
	 * @return boolean
	 */
	public static boolean isCode(String str) {
		return Regular(str, CODE);
	}

	/**
	 * 鍒ゆ柇瀛楃涓叉槸涓嶆槸鍏ㄩ儴鏄嫳鏂囧瓧姣�
	 *
	 * @param str
	 * @return boolean
	 */
	public static boolean isEnglish(String str) {
		return Regular(str, STR_ENG);
	}

	/**
	 * 鍒ゆ柇瀛楃涓叉槸涓嶆槸鍏ㄩ儴鏄嫳鏂囧瓧姣�鏁板瓧
	 *
	 * @param str
	 * @return boolean
	 */
	public static boolean isENG_NUM(String str) {
		return Regular(str, STR_ENG_NUM);
	}

	/**
	 * 鍒ゆ柇瀛楃涓叉槸涓嶆槸鍏ㄩ儴鏄嫳鏂囧瓧姣�鏁板瓧+涓嬪垝绾�
	 *
	 * @param str
	 * @return boolean
	 */
	public static boolean isENG_NUM_(String str) {
		return Regular(str, STR_ENG_NUM_);
	}

	/**
	 * 杩囨护鐗规畩瀛楃涓�杩斿洖杩囨护鍚庣殑瀛楃涓�
	 *
	 * @param str
	 * @return boolean
	 */
	public static String filterStr(String str) {
		Pattern p = Pattern.compile(STR_SPECIAL);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}

	/**
	 * 鏍￠獙鏈烘瀯浠ｇ爜鏍煎紡
	 *
	 * @return
	 */
	public static boolean isJigouCode(String str) {
		return Regular(str, JIGOU_CODE);
	}

	/**
	 * 鍒ゆ柇瀛楃涓叉槸涓嶆槸鏁板瓧缁勬垚
	 *
	 * @param str
	 * @return boolean
	 */
	public static boolean isSTR_NUM(String str) {
		return Regular(str, STR_NUM);
	}

	/**
	 * 鍖归厤鏄惁绗﹀悎姝ｅ垯琛ㄨ揪寮弍attern 鍖归厤杩斿洖true
	 *
	 * @param str
	 *            鍖归厤鐨勫瓧绗︿覆
	 * @param pattern
	 *            鍖归厤妯″紡
	 * @return boolean
	 */
	private static boolean Regular(String str, String pattern) {
		if (null == str || str.trim().length() <= 0)
			return false;
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(str);
		return m.matches();
	}
}
