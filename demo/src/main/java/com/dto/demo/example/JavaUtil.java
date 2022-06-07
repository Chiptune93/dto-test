package com.dto.demo.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.UUID;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import syworks.lib.myMap.MyCamelMap;
import syworks.lib.myMap.MyMap;

public class JavaUtil {
    /**
     * <pre>
     * 문자형에 포함된 HTML 태그제거
     * </pre>
     *
     * @param String
     * @return String
     */
    public static String array2Comma(String[] str) {
        String returnStr = "";
        for (int i = 0; i < str.length; i++) {
            returnStr += str[i] + ",";
        }
        if (returnStr.indexOf(",") != -1) {
            returnStr = returnStr.substring(0, returnStr.lastIndexOf(","));
        }
        return returnStr;
    }

    /**
     * <pre>
     * 가격을 , 로 포맷화 시킨다
     * 10000 -&gt; 10,000
     * </pre>
     *
     * @param price
     *              가격 ( <code>String</code> )
     * @return 3자리단위로 , 로 포맷화된 문자열
     */
    public static String priceToComma(long var) {
        return priceToComma(toString(var));
    }

    public static String priceToComma(double var) {
        return priceToComma(toString(var));
    }

    public static String priceToComma(int var) {
        return priceToComma(toString(var));
    }

    public static String priceToSign(String var) {
        return (JavaUtil.toInt(var) >= 0 ? "+" : "") + JavaUtil.priceToComma(var);
    }

    public static String priceToComma(String price) {
        String str = price.trim();
        boolean containsMinus = false;
        if (empty(str))
            return "0";
        if (str.indexOf(".") != -1) {
            str = str.substring(0, str.indexOf("."));
        }
        if (empty(str))
            return "0";
        if (str.indexOf("-") == 0) {
            containsMinus = true;
            str = str.substring(1);
        }
        int len = str.length();
        StringBuffer sbuf = new StringBuffer();
        for (int i = 0; i < len; i++) {
            if (i != 0 && i % 3 == 0)
                sbuf.append(",");
            sbuf.append(str.charAt((len - 1) - i));
        }
        if (containsMinus)
            sbuf.append("-");
        return sbuf.reverse().toString();
    }

    /**
     * <pre>
     * 문자형에 포함된 HTML 태그제거
     * </pre>
     *
     * @param String
     * @return String
     */
    public static String removeHtmlTags(String str) {
        str = str.replaceAll("<(\"[^\"]*\"|\'[^\']*\'|[^\'\">])*>", "");
        str = str.replaceAll("<(no)?script[^>]*>.*?</(no)?script>", "");
        str = str.replaceAll("<style[^>]*>.*</style>", "");
        return str;
    }

    /**
     * <pre>
     * 문자형 str에 포함된 pattern값을 문자형 replace값으로 변경
     * </pre>
     *
     * @param 원래
     *           문자, 찾고자하는 문자, 변경하고자 하는 문자
     * @return String
     */
    public static String replace(String str, String pattern, String replace) {
        int s = 0;
        int e = 0;
        StringBuffer result = new StringBuffer();
        while ((e = str.indexOf(pattern, s)) >= 0) {
            result.append(str.substring(s, e));
            result.append(replace);
            s = e + pattern.length();
        }
        result.append(str.substring(s));
        return result.toString();
    }

    public static String unescapeHTML(String values) {
        if (values == null)
            return "";
        values = values.replaceAll("&lt;", "<");
        values = values.replaceAll("&gt;", ">");
        values = values.replaceAll("&amp;", "&");
        values = values.replaceAll("&#38;", "&");
        values = values.replaceAll("&quot;", "\"");
        values = values.replaceAll("&#34;", "\"");
        values = values.replaceAll("&#39;", "'");
        values = values.replaceAll("&#36;", "\\$");
        return values;
    }

    public static String escapeHtml(String values) {
        if (values == null)
            return "";
        values = values.replaceAll("<(no)?script[^>]*>.*?</(no)?script>", "");
        values = values.replaceAll("<style[^>]*>.*</style>", "");
        values = values.replaceAll("&(?![#]?[a-z0-9]+;)", "&#38;");
        values = values.replaceAll("<", "&lt;");
        values = values.replaceAll(">", "&gt;");
        values = values.replaceAll("\"", "&#34;");
        values = values.replaceAll("'", "&#39;");
        values = values.replaceAll("\\$", "&#36;");
        return values;
    }

    /**
     * <pre>
     * 문자형이 null값이거나 공백일시 지정된 값으로 리턴
     * </pre>
     *
     * @param 문자형
     *            본래값,조건불일치 문자 def
     * @return String
     */
    public static String NVL(String str, String def) {
        str = str == null ? "" : str;
        return (str.equals("") ? def : str.trim());
    }

    public static String NVL(Object object, String def) {
        String str = object == null ? "" : object.toString();
        return (str.equals("") ? def : str.trim());
    }

    public static String NVL(int object, String def) {
        return object == 0 ? def : JavaUtil.toString(object);
    }

    public static Object NVL(Object object, Object def) {
        return object == null ? def : object;
    }

    /**
     * <pre>
     * 숫자형이 i가 0 일때 def 값을 리턴함
     * </pre>
     *
     * @param int 본래값 i,int 기본값 def
     * @return int
     */
    public static int NVL(String i, int def) {
        return (i == null ? def : JavaUtil.toInt(i));
    }

    public static int NVL(Object i, int def) {
        return (i == null ? def : JavaUtil.toInt(i));
    }

    public static int NVL(int i, int def) {
        return (i == 0 ? def : i);
    }

    public static long NVL(long i, long def) {
        return (i == 0 ? def : i);
    }

    /**
     * <pre>
     * 인트형 배열 sNum에 인트형 num 형이 포함되어있는지 확인하여 인덱스값을 리턴함
     * </pre>
     *
     * @param int i
     * @return int i
     */
    public static int inArray(Object[] arr1, Object arr2) {
        if (arr2 == null)
            return -1;
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i].toString().trim().equals(arr2.toString().trim()))
                return i;
        }
        return -1;
    }

    public static int inArray(int[] arr1, int arr2) {
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] == arr2)
                return i;
        }
        return -1;
    }

    public static int inArray(String[] arr1, String arr2) {
        if (arr2 == null)
            return -1;
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i].trim().equals(arr2.trim()))
                return i;
        }
        return -1;
    }

    public static int inArray(Object[] arr1, Object[] arr2) {
        for (int i = 0; i < arr2.length; i++) {
            if (inArray(arr1, arr2[i]) != -1)
                return i;
        }
        return -1;
    }

    public static int inArray(int[] arr1, int[] arr2) {
        for (int i = 0; i < arr2.length; i++) {
            if (inArray(arr1, arr2[i]) != -1)
                return i;
        }
        return -1;
    }

    public static int inArray(String[] arr1, String[] arr2) {
        for (int i = 0; i < arr2.length; i++) {
            if (inArray(arr1, arr2[i]) != -1)
                return i;
        }
        return -1;
    }

    public static int inArray(JSONArray arr1, String arr2) {
        for (int i = 0; i < arr1.size(); i++) {
            if (JavaUtil.NVL(arr1.get(i), "").equals(arr2))
                return i;
        }
        return -1;
    }

    /**
     * <pre>
     * 인트형을 문자형으로 변화
     * </pre>
     *
     * @param int i
     * @return String i
     */
    public static String toString(Object i) {
        try {
            return String.valueOf(i);
        } catch (Exception e) {
            return "";
        }
    }

    public static String toString(Object i, String def) {
        try {
            return String.valueOf(i);
        } catch (Exception e) {
            return def;
        }
    }

    /**
     * <pre>
     * 문자형을 인트형으로 변화
     * </pre>
     *
     * @param int i
     * @return String i
     */
    public static int toInt(String s) {
        try {
            s = JavaUtil.NVL(s, "0");
            return Integer.parseInt(s);
        } catch (Exception e) {
            return -1;
        }
    }

    public static int toInt(Long s) {
        return Integer.parseInt(JavaUtil.toString(Math.round(s)));
    }

    public static int toInt(Double s) {
        return Integer.parseInt(JavaUtil.toString(Math.round(s)));
    }

    public static int toInt(Object s) {
        s = JavaUtil.NVL(s, "0");
        return toInt(s.toString());
    }

    public static int toInt(Object s, int def) {
        s = JavaUtil.NVL(s, def);
        return toInt(s.toString());
    }

    public static String[] toStrArray(Object s) {
        String[] arr = null;
        try {
            arr = (String[]) s;
        } catch (Exception e) {
            arr = new String[0];
        }
        return arr == null ? new String[0] : arr;
    }

    public static String[][] toStrArray2(Object s) {
        String[][] arr = null;
        try {
            arr = (String[][]) s;
        } catch (Exception e) {
            arr = new String[0][0];
        }
        return arr == null ? new String[0][0] : arr;
    }

    public static String[][][] toStrArray3(Object s) {
        String[][][] arr = null;
        try {
            arr = (String[][][]) s;
        } catch (Exception e) {
            arr = new String[0][0][0];
        }
        return arr == null ? new String[0][0][0] : arr;
    }

    public static int[] toIntArray(Object s) {
        int[] arr = null;
        try {
            arr = (int[]) s;
        } catch (Exception e) {
            arr = new int[0];
        }
        return arr == null ? new int[0] : arr;
    }

    public static long toLong(String s) {
        try {
            s = JavaUtil.NVL(s, "0");
            if (!empty(s) && isDigit(s))
                return Long.parseLong(s);
        } catch (Exception e) {
            return 0;
        }
        return 0;
    }

    public static double toDouble(String s) {
        try {
            if (!empty(s))
                return Double.parseDouble(s);
        } catch (Exception e) {
            return 0;
        }
        return 0;
    }

    /**
     * String 이 유효한지 체크한다.
     *
     * @param str
     *            체크할 문자열
     * @return <code>true</code> 유효한 문자열; <code>false</code> 유효하지 않은 문자열
     */
    public static boolean empty(String str) {
        if (str == null || str.length() == 0)
            return true;
        return false;
    }

    /**
     * 넘어온 문자열이 숫자로만 이루어져있는지 체크한다.
     *
     * @param s
     *          체크할 문자열
     * @return <code>true</code> 숫자로만 구성된 경우; <code>false</code> 숫자이외의 문자가 포함된 경우
     */
    public static boolean isDigit(String s) {
        int i, Length;
        if (s == null)
            return false;
        Length = s.length();
        for (i = 0; i < Length; i++) {
            if (s.charAt(i) > '9' || s.charAt(i) < '0')
                return false;
        }
        return true;
    }

    /**
     * 랜덤번호 생성
     *
     * @param null
     * @return 랜덤번호
     */
    public static String random() {
        return "rand" + UUID.randomUUID().toString().replaceAll("\\-", "");
    }

    /**
     * 전달된 파라미터에 맞게 난수를 생성한다
     *
     * @param len   : 생성할 난수의 길이
     * @param dupCd : 중복 허용 여부 (1: 중복허용, 2:중복제거)
     *              Created by 닢향
     *              http://niphyang.tistory.com
     */
    public static String numberGen(int len, int dupCd) {
        Random rand = new Random();
        String numStr = ""; // 난수가 저장될 변수
        for (int i = 0; i < len; i++) {
            // 0~9 까지 난수 생성
            String ran = Integer.toString(rand.nextInt(10));
            if (dupCd == 1) {
                // 중복 허용시 numStr에 append
                numStr += ran;
            } else if (dupCd == 2) {
                // 중복을 허용하지 않을시 중복된 값이 있는지 검사한다
                if (!numStr.contains(ran)) {
                    // 중복된 값이 없으면 numStr에 append
                    numStr += ran;
                } else {
                    // 생성된 난수가 중복되면 루틴을 다시 실행한다
                    i -= 1;
                }
            }
        }
        return numStr;
    }

    /**
     * String Escape 처리
     *
     * @param src
     * @return
     */
    public static String escape(String src) {
        int i;
        char j;
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length() * 6);
        for (i = 0; i < src.length(); i++) {
            j = src.charAt(i);
            if (Character.isDigit(j) || Character.isLowerCase(j) || Character.isUpperCase(j))
                tmp.append(j);
            else if (j < 256) {
                tmp.append("%");
                if (j < 16)
                    tmp.append("0");
                tmp.append(Integer.toString(j, 16));
            } else {
                tmp.append("%u");
                tmp.append(Integer.toString(j, 16));
            }
        }
        return tmp.toString();
    }

    /**
     * String UnEscape 처리
     *
     * @param src
     * @return
     */
    public static String unescape(String src) {
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length());
        int lastPos = 0, pos = 0;
        char ch;
        while (lastPos < src.length()) {
            pos = src.indexOf("%", lastPos);
            if (pos == lastPos) {
                if (src.charAt(pos + 1) == 'u') {
                    ch = (char) Integer.parseInt(src.substring(pos + 2, pos + 6), 16);
                    tmp.append(ch);
                    lastPos = pos + 6;
                } else {
                    ch = (char) Integer.parseInt(src.substring(pos + 1, pos + 3), 16);
                    tmp.append(ch);
                    lastPos = pos + 3;
                }
            } else {
                if (pos == -1) {
                    tmp.append(src.substring(lastPos));
                    lastPos = src.length();
                } else {
                    tmp.append(src.substring(lastPos, pos));
                    lastPos = pos;
                }
            }
        }
        return tmp.toString();
    }

    /**
     * MD5 암호화(MYSQL,PHP 와 동일값)
     *
     * @param 문자열
     * @return 암호화 문자
     */
    public static String md5Encoding(String password) {
        try {
            return org.apache.commons.codec.digest.DigestUtils.md5Hex(password);
        } catch (Exception e) {
            return null;
        }
    }

    public static String base64Encode(String str) {
        try {
            return new String(Base64.encodeBase64(str.getBytes("UTF-8")), "UTF-8");
        } catch (UnsupportedEncodingException e) {
        }
        return "";
    }

    public static String base64Decode(String str) {
        try {
            return new String(Base64.decodeBase64(str.getBytes("UTF-8")), "UTF-8");
        } catch (UnsupportedEncodingException e) {
        }
        return "";
    }

    public static String hexCode(String inputValue) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
        }
        String eip;
        byte[] bip;
        String temp = "";
        String tst = inputValue;
        bip = md5.digest(tst.getBytes());
        for (int i = 0; i < bip.length; i++) {
            eip = "" + Integer.toHexString((int) bip[i] & 0x000000ff);
            if (eip.length() < 2)
                eip = "0" + eip;
            temp = temp + eip;
        }
        return temp;
    }

    /**
     * source에서 target으로의 파일 복사
     *
     * @param source
     * @param target
     */
    public static boolean copy(String source, String target) {
        // 복사 대상이 되는 파일 생성
        File sourceFile = new File(source);
        // 스트림 선언
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
            // 스트림 생성
            inputStream = new FileInputStream(sourceFile);
            outputStream = new FileOutputStream(target);
            int bytesRead = 0;
            // 인풋스트림을 아웃픗스트림에 쓰기
            byte[] buffer = new byte[1024];
            while ((bytesRead = inputStream.read(buffer, 0, 1024)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        } catch (Exception e) {
            return false;
        } finally {
            // 자원 해제
            try {
                outputStream.close();
            } catch (IOException ioe) {
            }
            try {
                inputStream.close();
            } catch (IOException ioe) {
            }
        }
        return true;
    }

    public static String textHTML(String str) {
        return str.replaceAll("\\n", "<br/>\n").replaceAll("\\t", "&emsp;").replaceAll(" ", "&nbsp;");
    }

    /**
     * 문자형에 오늘 날자리턴
     *
     * @param source
     * @param target
     */
    public static String sysDate(String dateType) {
        return new SimpleDateFormat(dateType).format(Calendar.getInstance().getTime());
    }

    public static String sysDate(String dateType, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) + day);
        return new SimpleDateFormat(dateType).format(cal.getTime());
    }

    /**
     * <pre>
     * 스트링을 일정한 길이로 잘라서 append 로 (...) 축약한다
     * 길이를 자를때는 바이트 단위로 자르므로, 한글은 2byte 이다.
     * </pre>
     *
     * @param str
     *               자를 문자열
     * @param append
     *               자르고 나서 append 할 문자열
     * @param len
     *               잘라낼 길이
     */
    public static String cutString(String str, String append, int len) { // 문자열 자르기
        str = JavaUtil.unescapeHTML(str);
        String r_val = str;
        int oF = 0, oL = 0, rF = 0, rL = 0;
        int nLengthPrev = 0;
        try {
            byte[] bytes = r_val.getBytes("UTF-8"); // 바이트로 보관
            // x부터 y길이만큼 잘라낸다. 한글안깨지게.
            int j = 0;
            if (nLengthPrev > 0)
                while (j < bytes.length) {
                    if ((bytes[j] & 0x80) != 0) {
                        oF += 2;
                        rF += 3;
                        if (oF + 2 > nLengthPrev) {
                            break;
                        }
                        j += 3;
                    } else {
                        if (oF + 1 > nLengthPrev) {
                            break;
                        }
                        ++oF;
                        ++rF;
                        ++j;
                    }
                }
            j = rF;
            while (j < bytes.length) {
                if ((bytes[j] & 0x80) != 0) {
                    if (oL + 2 > len) {
                        break;
                    }
                    oL += 2;
                    rL += 3;
                    j += 3;
                } else {
                    if (oL + 1 > len) {
                        break;
                    }
                    ++oL;
                    ++rL;
                    ++j;
                }
            }
            r_val = new String(bytes, rF, rL, "UTF-8"); // charset 옵션
            if (bytes.length > len)
                r_val += append;
        } catch (Exception e) {
            return str;
        }
        return r_val;
    }

    public static MyMap calendarInfo(Date date) {
        MyMap returnInfo = new MyMap();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        cal.set(Calendar.DATE, 1);
        int day = (month * 9 - month * 9 % 8) / 8 % 2;
        if (month != 2) {
            day += 30;
        } else if ((year % 400 == 0) || (year % 4 == 0) && (year % 100 != 0)) {
            day += 29;
        } else {
            day += 28;
        }
        returnInfo.put("year", year);
        returnInfo.put("month", month);
        returnInfo.put("lastDay", day);
        returnInfo.put("firstWeek", cal.get(Calendar.DAY_OF_WEEK));
        return returnInfo;
    }

    public static List<MyMap> calendarBetweenInfo(Date startDate, Date endDate) {
        List<MyMap> returnInfo = new ArrayList<MyMap>();
        MyMap dateInfo = null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.set(Calendar.DATE, 1);
        while (cal.getTime().compareTo(endDate) < 1) {
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH) + 1;
            int day = (month * 9 - month * 9 % 8) / 8 % 2;
            if (month != 2) {
                day += 30;
            } else if ((year % 400 == 0) || (year % 4 == 0) && (year % 100 != 0)) {
                day += 29;
            } else {
                day += 28;
            }
            dateInfo = new MyMap();
            dateInfo.put("year", year);
            dateInfo.put("month", String.format("%02d", month));
            dateInfo.put("lastDay", String.format("%02d", day));
            dateInfo.put("totalDay", String.format("%02d", day + (cal.get(Calendar.DAY_OF_WEEK) - 1)));
            dateInfo.put("firstWeek", String.format("%02d", cal.get(Calendar.DAY_OF_WEEK)));
            returnInfo.add(dateInfo);
            cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
        }
        return returnInfo;
    }

    /**
     * 문자형에 오늘 날자리턴
     *
     * @param source
     * @param target
     */
    public static Date dateFormat(String dateType, String date) {
        try {
            if (JavaUtil.NVL(dateType, "").equals(""))
                return null;
            DateFormat formatter;
            formatter = new SimpleDateFormat(dateType);
            return (Date) formatter.parse(date);
        } catch (Exception e) {
        }
        return null;
    }

    public static String addDate(String strDate, int addDate) {
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = (Date) formatter.parse(strDate);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DATE, addDate);
            return formatter.format(cal.getTime());
        } catch (Exception e) {
        }
        return null;
    }

    public static String dateFormat(String dateType, Date date) {
        try {
            DateFormat formatter = new SimpleDateFormat(dateType);
            return formatter.format(date);
        } catch (Exception e) {
        }
        return null;
    }

    public static String dateFormatToString(String dateType, String date) {
        try {
            if (JavaUtil.NVL(date, "").equals("") || date.matches("^(00)?00(\\.|\\-)?00(\\.|\\-)?00(.+)")) {
                if (dateType.equals("yy-MM-dd"))
                    return "00-00-00";
                else if (dateType.equals("yy.MM.dd"))
                    return "00.00.00";
                return date;
            }
            DateFormat formatter;
            Date newDate;
            if (dateType.matches("^(yy)?yy(\\.|\\-)?MM(\\.|\\-)?dd$")) {
                formatter = new SimpleDateFormat("yyyy-MM-dd");
            } else if (dateType.matches("^(yy)?yy(\\.|\\-)?MM(\\.|\\-)?dd HH\\:mm$")) {
                formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            } else {
                formatter = new SimpleDateFormat(dateType);
            }
            newDate = formatter.parse(date);
            formatter = new SimpleDateFormat(dateType);
            return formatter.format(newDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String StringDateFormat(String strDate, String strDatePatten, String toPatten) {
        try {
            DateFormat sdFormat = new SimpleDateFormat(strDatePatten);
            Date tempDate;
            tempDate = sdFormat.parse(strDate);
            sdFormat = new SimpleDateFormat(toPatten);
            return sdFormat.format(tempDate);
        } catch (ParseException e) {
        }
        return "";
    }

    /**
     * 문자형를 길이만큼 *로 변화
     *
     * @param source
     * @param target
     */
    public static String stringToPassword(String string) {
        String returnString = "";
        for (int i = 0; i < string.length(); i++) {
            returnString += "*";
        }
        return returnString;
    }

    public static String cellValueToString(HSSFCell cell) {
        if (cell == null)
            return "";
        String returnStr = "";
        switch (cell.getCellType()) {
            case 0:
                returnStr = JavaUtil.toString(new DecimalFormat("#,##0").format(cell.getNumericCellValue()))
                        .replaceAll("\\,", "");
                break;
            case 1:
                returnStr = cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_FORMULA:
                returnStr = cell.getCellFormula();
                break;
            default:
                returnStr = "";
        }
        return returnStr;
    }

    public static String unixTimestampToStringDate(long date, String type) {
        DateFormat formatter = new SimpleDateFormat(type);
        return formatter.format(new Date((long) date * 1000)).toString();
    }

    public static String constants(String name) {
        Properties props = ((PropertyConfig) Beans.getBean("globalsProperty")).getProps();
        return props.getProperty(props.getProperty("ENV") + "." + name);
    }

    public static MyMap describe(Object obj) {
        MyMap map = null;
        try {
            map = (MyMap) BeanUtils.describe(obj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static void populate(Object bean, MyMap properties) {
        try {
            BeanUtils.populate(bean, properties);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static String camelCaseConverter(String key) {
        String newColumnName = null;
        if (key.indexOf("_") == -1) {
            if (key.matches("(.+)?[A-Z](.+)?") && key.matches("(.+)?[a-z](.+)?"))
                return key;
            return key.toLowerCase();
        } else {
            StringBuffer sb = new StringBuffer();
            boolean isFirst = true;
            StringTokenizer tokenizer = new StringTokenizer(key, "_");
            while (tokenizer.hasMoreTokens()) {
                if (isFirst)
                    sb.append(tokenizer.nextToken().toLowerCase());
                else {
                    sb.append(StringUtils.capitalize(tokenizer.nextToken().toLowerCase()));
                }
                isFirst = false;
            }
            newColumnName = sb.toString();
        }
        return newColumnName;
    }

    /**
     * 리스트 해당 키값으로 MAP생성
     */
    public static MyMap listKeyToMyMapInfo(List<MyMap> list, String key) {
        MyMap result = new MyMap();
        for (MyMap map : list) {
            result.put(JavaUtil.toString(map.get(key)), map);
        }
        return result;
    }

    /**
     * 리스트 해당 키값으로 MAP생성
     */
    public static MyMap listKeyToMyCamelMapInfo(List<MyCamelMap> list, String key) {
        MyMap result = new MyMap();
        for (MyCamelMap map : list) {
            result.put(JavaUtil.toString(map.get(key)), map);
        }
        return result;
    }

    /**
     * 리스트 해당 키값 List
     */
    public static List<String> listKeyToArrayForMyMap(List<MyMap> list, String key) {
        List<String> result = new ArrayList<String>();
        for (MyMap map : list) {
            result.add(JavaUtil.toString(map.get(key)));
        }
        return result;
    }

    public static List<String> listKeyToArrayForMyCamelMap(List<MyCamelMap> list, String key) {
        List<String> result = new ArrayList<String>();
        for (MyCamelMap map : list) {
            result.add(JavaUtil.toString(map.get(key)));
        }
        return result;
    }

    public static List<Integer> betweenList(int s, int e) {
        List<Integer> result = new ArrayList<Integer>();
        for (int i = s; i <= e; i++) {
            result.add(i);
        }
        return result;
    }

    public static MyMap extendsMap(MyMap paramMap, MyMap menuInfo) {
        for (Entry<Object, Object> e : menuInfo.entrySet()) {
            paramMap.put(e.getKey(), e.getValue());
        }
        return paramMap;
    }

    /**
     * 중복되는 키가 있을경우, paramMap의 값을 보호한다.
     */
    public static MyMap extendsUniqueMap(MyMap paramMap, MyMap menuInfo) {
        menuInfo.entrySet();
        for (Entry<Object, Object> e : menuInfo.entrySet()) {
            if (!paramMap.containsKey(e.getKey())) {
                paramMap.put(e.getKey(), e.getValue());
            }
        }
        return paramMap;
    }

    public static String dateDay(String strDate) {
        try {
            DateFormat sdFormat = new SimpleDateFormat("yyyyMMdd");
            Date tempDate = sdFormat.parse(strDate);
            Date date = tempDate;
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int day = calendar.get(Calendar.DAY_OF_WEEK);
            switch (day) {
                case 1:
                    return "일요일";
                case 2:
                    return "월요일";
                case 3:
                    return "화요일";
                case 4:
                    return "수요일";
                case 5:
                    return "목요일";
                case 6:
                    return "금요일";
                case 7:
                    return "토요일";
            }
            return "알수없는 데이터 값입니다";
        } catch (ParseException e) {
            return "";
        }
    }
}
