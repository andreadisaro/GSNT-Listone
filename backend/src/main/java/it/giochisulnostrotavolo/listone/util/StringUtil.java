package it.giochisulnostrotavolo.listone.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import it.giochisulnostrotavolo.listone.exception.PortalException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Collectors;
import org.apache.commons.text.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringUtil {

  private static final Logger LOG = LoggerFactory.getLogger(StringUtil.class);

  public static String toString(Collection<?> pCollection) {
    StringBuilder retval = new StringBuilder();
    Iterator<?> theIterator = pCollection.iterator();
    while (theIterator.hasNext()) {
      retval.append("\n\t").append(toString((Object[]) theIterator.next()));
    }
    return retval.toString();
  }

  public static String toString(Object[] pRow) {
    StringBuilder retval = new StringBuilder(" | ");
    for (int i = 0; i < pRow.length; i++) {
      retval.append(pRow[i]).append(" | ");
    }
    return retval.toString();
  }

  public static String nullToVoid(String pArg) {
    return (pArg != null) ? pArg : "";
  }

  public static String nullToVoid(Integer pArg) {
    return (pArg != null) ? "" + pArg : "";
  }

  /*
   *
   */
  public static int toInt(String pArg, int pDefaultRetval) {
    try {
      return Integer.parseInt(pArg);
    } catch (Exception e) {
      return pDefaultRetval;
    }
  }

  public static boolean isvoid(Collection<?> pCollection) {
    return (pCollection == null || pCollection.isEmpty());
  }

  public static boolean isvoid(String pArg) {
    return (pArg == null || "".equals(pArg.trim()));
  }

  public static boolean isvoid(Object pArg) {
    return (pArg == null || "".equals(pArg.toString().trim()));
  }

  public static List<String> splitToTokensByRegex(
    String pString,
    String pDelimitersRegex,
    boolean pIncludeVoid
  ) {
    ArrayList<String> retval = new ArrayList<String>();
    String[] numberArray = pString.split(pDelimitersRegex);
    for (int i = 0; i < numberArray.length; i++) {
      String elem = numberArray[i].trim();
      if (pIncludeVoid || !elem.isEmpty()) {
        retval.add(elem);
      }
    }
    return retval;
  }

  public static ArrayList<String> splitToTokens(String pLine, String pDelim) {
    String s = pLine;
    ArrayList<String> tokens = new ArrayList<String>();
    if (s != null) {
      int currIndex = 0;
      while (s.contains(pDelim)) {
        currIndex = s.indexOf(pDelim);
        tokens.add(s.substring(0, currIndex));
        s = s.substring(currIndex + pDelim.length());
      }
      if (s.length() > 0) {
        tokens.add(s);
      }
    }
    return tokens;
  }

  public static int getInt(String pValue, int pDefault) {
    int retval = pDefault;
    try {
      retval = Integer.parseInt(pValue.trim());
    } catch (NumberFormatException nfe) {} catch (NullPointerException npe) {}
    return retval;
  }

  public static int getInt(Object pValue, int pDefault) {
    int retval;
    if (pValue == null) {
      retval = pDefault;
    } else if (pValue instanceof Integer) {
      retval = (Integer) pValue;
    } else if (pValue instanceof Short) {
      retval = (Short) pValue;
    } else {
      retval = getInt(pValue.toString(), pDefault);
    }
    return retval;
  }

  public static double getDouble(String pValue, int pDefault) {
    double retval = pDefault;
    try {
      retval = Double.parseDouble(pValue.trim());
    } catch (NumberFormatException nfe) {} catch (NullPointerException npe) {}
    return retval;
  }

  public static double getDouble(Object pValue, int pDefault) {
    double retval;
    if (pValue == null) {
      retval = pDefault;
    } else if (pValue instanceof Double) {
      retval = (Double) pValue;
    } else {
      retval = getDouble(pValue.toString(), pDefault);
    }
    return retval;
  }

  public static boolean equals(Object pObj1, Object pObj2) {
    return (pObj1 == null)
      ? (pObj2 == null)
      : (
        pObj2 != null &&
        pObj1.getClass() == pObj2.getClass() &&
        pObj1.equals(pObj2)
      );
  }

  public static String getRequestParameter(
    String pParameter,
    String pMethod,
    Collection<String> pParNameList,
    String pDefault,
    boolean pMandatory
  ) throws Exception {
    String retval = pParameter;
    StringBuilder parNameStreamPipeSeparator = new StringBuilder("|");
    String firstParFound = null;
    for (String currName : pParNameList) {
      parNameStreamPipeSeparator.append(currName).append("|");
      if (!StringUtil.isvoid(retval)) {
        firstParFound = currName;
        break;
      }
    }

    if (StringUtil.isvoid(retval)) {
      if (pMandatory) {
        throw new PortalException(
          "at least one parameter is mandatory: " + parNameStreamPipeSeparator,
          null
        );
      } else {
        retval = pDefault;
      }
    }
    String foundResult = (firstParFound != null)
      ? "parameter found: " + firstParFound + " = " + retval
      : "no parameter found!";
    LOG.info(
      pMethod +
      " - request.getParameter(" +
      parNameStreamPipeSeparator +
      ") - " +
      foundResult
    );
    return retval;
  }

  public static Integer getRequestIntParameter(
    String pRequest,
    String pMethod,
    List<String> list,
    Integer pDefault,
    boolean pMandatory
  ) throws Exception {
    Integer retvalInt = null;
    String defaultStr = (pDefault != null) ? "" + pDefault : null;
    String retval = getRequestParameter(
      pRequest,
      pMethod,
      list,
      defaultStr,
      pMandatory
    );
    if (!StringUtil.isvoid(retval)) {
      try {
        retvalInt = Integer.parseInt(retval);
      } catch (NumberFormatException nfe) {
        throw new PortalException(
          "parameter not in a proper format (number): " + list + "=" + retval,
          null
        );
      }
    }

    return retvalInt;
  }

  public static String getRequestParameter(
    String pParameter,
    String pMethod,
    String pParName,
    String pDefault,
    boolean pMandatory
  ) throws Exception {
    String retval = pParameter;
    if (StringUtil.isvoid(retval)) {
      if (pMandatory) {
        throw new PortalException("parameter mandatory: " + pParName, null);
      } else {
        retval = pDefault;
      }
    }
    LOG.info(pMethod + " - request.getParameter(" + pParName + ") = " + retval);
    return retval;
  }

  public static String toJSON(Object object) throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    ObjectWriter objectWriter = mapper.writer();
    byte[] bytes = objectWriter.writeValueAsBytes(object);
    ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
    ByteArrayOutputStream result = new ByteArrayOutputStream();
    byte[] buffer = new byte[1024];
    int length;
    while ((length = bis.read(buffer)) != -1) {
      result.write(buffer, 0, length);
    }
    return result.toString(StandardCharsets.UTF_8.name());
  }

  public static Object parseJSON(String json, Class<?> clazz) throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(json, clazz);
  }

  public static byte[] serializeObject(Object obj) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ObjectOutputStream oos = new ObjectOutputStream(baos);
    oos.writeObject(obj);
    return baos.toByteArray();
  }

  public static <T extends Serializable> Object deserializeObject(
    byte[] inputByteArray,
    Class<?> clazz
  ) throws IOException, ClassNotFoundException {
    ByteArrayInputStream baip = new ByteArrayInputStream(inputByteArray);
    ObjectInputStream ois = new ObjectInputStream(baip);
    return clazz.cast(ois.readObject());
  }

  public static void serializeObjectToFile(
    Object obj,
    String dirName,
    String fileName
  ) throws Exception {
    OutputStream ops = null;
    ObjectOutputStream objOps = null;
    try {
      File dir = new File(dirName);
      dir.mkdirs();
      ops =
        new FileOutputStream(dirName.concat(File.separator).concat(fileName));
      objOps = new ObjectOutputStream(ops);
      objOps.writeObject(obj);
      objOps.flush();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (objOps != null) objOps.close();
      } catch (Exception ex) {
        LOG.error("impossibile chiudere objOps", ex);
      }
    }
  }

  public static <T extends Serializable> Object deserializeObjectFromFile(
    String dirName,
    String fileName
  ) throws Exception {
    InputStream fileIs = null;
    ObjectInputStream objIs = null;
    try {
      fileIs =
        new FileInputStream(dirName.concat(File.separator).concat(fileName));
      objIs = new ObjectInputStream(fileIs);
      return objIs.readObject();
    } catch (Exception e) {
      throw e;
    } finally {
      try {
        if (objIs != null) objIs.close();
      } catch (Exception ex) {
        LOG.error("impossibile chiudere objIs", ex);
      }
    }
  }

  public static boolean deleteSerializedObjectFile(
    String dirName,
    String fileName
  ) {
    File file = new File(dirName.concat(fileName));
    return file.delete();
  }

  public static boolean deleteSerializedObjectDir(String dirName) {
    File dir = new File(dirName);
    if (dir.exists() && dir.isDirectory()) {
      for (File f : dir.listFiles()) {
        f.delete();
      }
    }
    return dir.delete();
  }

  public static boolean isRegexMatch(String pText, String pRegexp) {
    boolean retval = false;
    if (!isvoid(pRegexp)) {
      try {
        Pattern pattern = Pattern.compile(pRegexp);
        Matcher matcher = pattern.matcher(pText);
        retval = (!pText.isEmpty() && !pRegexp.isEmpty() && matcher.matches());
      } catch (PatternSyntaxException pse) {
        LOG.error(
          "AAAAAA eccezione smorzata su pattern regex non valido: " + pRegexp
        );
        retval = false;
      }
    }
    return retval;
  }

  public static boolean isInteger(String str) {
    if (str == null) {
      return false;
    }
    int length = str.length();
    if (length == 0) {
      return false;
    }
    int i = 0;
    if (str.charAt(0) == '-') {
      if (length == 1) {
        return false;
      }
      i = 1;
    }
    for (; i < length; i++) {
      char c = str.charAt(i);
      if (c < '0' || c > '9') {
        return false;
      }
    }
    return true;
  }

  public static boolean xmlString01Toboolean(String pFlagText) {
    return ("1".equals(pFlagText));
  }

  public static boolean xmlStringEIToboolean(String pFlagText) {
    return ("I".equalsIgnoreCase(pFlagText));
  }

  public static String encodeAsHtml(String pText) {
    return (pText != null) ? StringEscapeUtils.escapeHtml4(pText) : "";
  }

  public static boolean checkInListByRegex(String[] pList, String pTarget) {
    for (String str : pList) {
      if (isRegexMatch(pTarget, str)) return true;
    }
    return false;
  }

  public static String mapToStringForLog(Map<String, ?> map) {
    return map
      .keySet()
      .stream()
      .map(key -> key.concat("=").concat(String.valueOf(map.get(key))))
      .collect(Collectors.joining(", ", "[", "]"));
  }

  public static String cleanText(String pText) {
    String retval = null;
    if (pText != null) {
      retval = pText.trim();
      // normalizzazione doppi e singoli apici (es le maledette virgolette inglesi di MS Office!!!)
      retval =
        retval
          .replaceAll("\u201c|\u201d|\u201e|\u201f", "\"")
          .replaceAll("\u2018|\u2019|\u201a|\u201b", "'");
    }
    return retval;
  }

  public static String csvSingleLineDecode(String pLine) {
    String escChr = "\\";
    String theLine = (pLine != null) ? pLine : "";
    return theLine
      .replace(escChr + "r", "\r")
      .replace(escChr + "\r", escChr + "r")
      .replace(escChr + "n", "\n")
      .replace(escChr + "\n", escChr + "n");
  }

  public static Boolean equalsIgnoreBlankNull(String pInput1, String pInput2) {
    return (
      (isvoid(pInput1) && isvoid(pInput2)) ||
      (pInput1 != null && pInput1.equals(pInput2))
    );
  }
}
