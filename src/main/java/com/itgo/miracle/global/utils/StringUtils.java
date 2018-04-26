package com.itgo.miracle.global.utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class StringUtils
{
   enum PadType
   {
      LEFT, RIGHT;
   }

   public static final String CrLf = "\r\n";
   public static final String Cr = "\r";
   public static final String Lf = "\n";
   public static final String Quot = "\"";

   public static Comparator<String> StringComparator = new Comparator<String>()
   {
      @Override
      public int compare(String one, String two)
      {
         return nullSafeCompare(one, two, false);
      }
   };

   public static Comparator<String> StringComparatorReverse = new Comparator<String>()
   {
      @Override
      public int compare(String one, String two)
      {
         return nullSafeCompare(two, one, false);
      }
   };

   /**
    * PrÃ¼ft alle Ã¼bergebenen Strings mit {@link #isNullOrEmpty(String)}
    * 
    * @param strings
    * @return <code>true</code> - wenn alle Strings mindestens ein Zeichen
    *         enthalten<br>
    *         <code>false</code> - sobald ein String leer oder null ist
    */
   public static boolean checkAllNotNullOrEmpty(String... strings)
   {
      if (strings.length == 0)
         return false;
      for (String string : strings)
      {
         if (isNullOrEmpty(string))
            return false;
      }
      return true;
   }

   public static String openTag(String name, boolean closeDirectly, String... attributes)
   {
      if (isNullOrEmpty(name))
         throw new IllegalArgumentException("Parameter name darf nicht NULL sein");

      if (attributes == null)
         throw new IllegalArgumentException("Parameter attributes darf nicht NULL sein");

      String result = "<";
      result += name;
      if (attributes != null)
      {
         String attributeString = join(attributes, " ", false);
         if (isNotNullOrEmpty(attributeString))
         {
            result += " ";
            result += attributeString;
         }
      }

      if (closeDirectly)
         result += " /";

      result += ">";
      return result;
   }

   public static String openTag(String name, String... attributes)
   {
      return openTag(name, false, attributes);
   }

   public static String closeTag(String name)
   {
      if (isNullOrEmpty(name))
         throw new IllegalArgumentException("Parameter name darf nicht NULL sein");

      String result = "</";
      result += name;
      result += ">";
      return result;
   }

   /**
    * Erstellt eine {@link List Liste} von {@link Long} Werten aus einer
    * komma-separierten {@link String Zeichenkette}. Ist ein Wert nicht als
    * Numerisch zu erkennen, wird er als 0 interpretiert.
    * 
    * @param commaSeparatedList
    *           komma-separierte {@link String Zeichenkette}.
    * @return {@link List Liste} von {@link Long} Werten
    * @see NumberUtilsShared#getValueAsLong(String)
    */
   /**
    * Ermittelt die LÃ¤nge einer {@link String Zeichenkette}. Sicher im Umgang
    * mit NULL-Werten.
    * 
    * @param string
    *           zu prÃ¼fende {@link String Zeichenkette}
    * @return {@link Integer LÃ¤nge} der Zeichenkette. 0 wenn Zeichenkette NULL
    *         oder keine Zeichen enthÃ¤lt, andernfalls > 0.
    */
   public static int getNullSafeLength(String string)
   {
      return toEmptyIfNull(string).length();
   }

   /**
    * Ermittelt das erste Vorkommen eines der Ã¼bergebenen searchStrings in der
    * Zeichenkette inString. Funktioniert wie String.indexOf, nur dass es die
    * erste Position der Ã¼bergebenen Tokens liefert.
    * 
    * @param string
    * @return
    */
   public static int indexOfAny(String inString, boolean ignoreCase, String... searchStrings)
   {
      int result = -1;
      if (inString != null && searchStrings != null)
      {
         if (ignoreCase)
            inString = inString.toLowerCase();

         for (String s : searchStrings)
         {
            if (ignoreCase)
               s = s.toLowerCase();

            int idx = inString.indexOf(s);
            if (result == -1)
            {
               result = idx;
            }
            else if (idx > -1 && idx < result)
            {
               result = idx;
            }
         }
      }
      return result;
   }

   public static boolean isAlpha(String str)
   {
      return str.matches("\\D*");
   }

   public static boolean isNotNullOrEmpty(String string)
   {
      return isNotNullOrEmpty(string, true);
   }

   public static boolean isNotNullOrEmpty(String string, boolean trimmed)
   {
      return !isNullOrEmpty(string, trimmed);
   }

   /**
    * Liefert true, wenn das Argument<br>
    * - null ist<br>
    * - die getrimmte LÃ¤nge ohne Leerzeichen 0 ist
    * 
    * @param string
    * @return
    */
   public static boolean isNullOrEmpty(String string)
   {
      return isNullOrEmpty(string, true);
   }

   public static boolean isNullOrEmpty(String string, boolean trimmed)
   {
      if (string == null)
         return true;

      if (trimmed)
         string = string.trim();

      return string.isEmpty();
   }

   /**
    * @param str
    * @return Liefert <code>true<code> wenn der String <b>ausschlieÃŸlich</b>
    *         aus Ziffern besteht
    */
   public static boolean isNumeric(String str)
   {
      if (isNullOrEmpty(str))
         return false;

      char c;
      for (int i = 0; i < str.length(); i++)
      {
         c = str.charAt(i);
         if (c < 48 || c > 57)
            return false;
      }
      return true;
   }

   /**
    * @param strings
    * @param delimiter
    * @return Gibt eine Zeile zurÃ¼ck, in der die Strings aus
    *         <code>strings</code> durch den <code>delimiter</code> getrennt
    *         wurden. Leere Strings werden berÃ¼cksichtigt.
    * @see StringUtils#join(String[], String, boolean)
    */
   public static String join(List<String> strings, String delimiter)
   {
      return join(strings, delimiter, true);
   }

   /**
    * @param strings
    * @param delimiter
    * @param addEmpty
    * @return Gibt eine Zeile zurÃ¼ck, in der die Strings aus
    *         <code>strings</code> durch den <code>delimiter</code> getrennt
    *         wurden. Leere Strings werden nur berÃ¼cksichtigt, wenn
    *         <code>addEmpty</code> auf <b>true</b> gesetzt ist.
    */
   public static String join(List<String> strings, String delimiter, boolean addEmpty)
   {
      if (strings == null || strings.size() == 0)
         return "";
      StringBuilder sb = new StringBuilder();
      String next = "";
      Iterator<String> iter = strings.iterator();

      boolean firstValue = true;

      while (iter.hasNext())
      {
         next = iter.next();
         if (addEmpty || isNotNullOrEmpty(next))
         {
            if (!firstValue)
               sb.append(delimiter);
            sb.append(StringUtils.toEmptyIfNull(next));
            firstValue = false;
         }
         next = "";
      }
      return sb.toString();
   }

   public static String join(String[] s, String delimiter)
   {
      return join(s, delimiter, true);
   }

   /**
    * @param s
    * @param delimiter
    * @param addEmpty
    * @return Gibt eine Zeile zurÃ¼ck, in der die Strings aus
    *         <code>strings</code> durch den <code>delimiter</code> getrennt
    *         wurden. Leere Strings werden nur berÃ¼cksichtigt, wenn
    *         <code>addEmpty</code> auf <b>true</b> gesetzt ist.
    */
   public static String join(String[] s, String delimiter, boolean addEmpty)
   {
      if (s == null || s.length == 0)
         return "";

      List<String> list = new ArrayList<String>();
      for (String l : s)
         list.add(l);

      return join(list, delimiter, addEmpty);
   }

   /**
    * @param ints
    * @param delimiter
    * @return Liefert einen String mit den Werten aus dem Interger-Array
    */
   public static String joinInts(List<Integer> ints, String delimiter)
   {
      List<String> strings = new ArrayList<String>();
      for (int currValue : ints)
         strings.add(String.valueOf(currValue));

      return join(strings, delimiter);
   }

   /**
    * @param longs
    * @param delimiter
    * @return Liefert einen String mit den Werten aus dem long-Array.
    */
   public static String joinLongs(List<Long> longs, String delimiter)
   {
      if (longs == null)
         return "";

      List<String> strings = new ArrayList<String>();
      for (Long currValue : longs)
      {
         if (currValue != null)
            strings.add(currValue.toString());
      }

      return join(strings, delimiter);
   }

   public static String leftPad(long number, int length, String fill)
   {
      return leftPad(number + "", length, fill);
   }

   public static String leftPad(String string, int length, int fill)
   {
      return leftPad(string, length, String.valueOf(fill));
   }

   public static String leftPad(String string, int length, String fill)
   {
      return pad(string, length, fill, PadType.LEFT);
   }

   /**
    * 
    * @param string
    * @param maxLength
    * @return Schneidet den String auf eine angegebene LÃ¤nge zurecht
    *         anschlieÃŸend wird trim() ausgefÃ¼hrt wird NULL als String
    *         Ã¼bergeben, kommt ein Leerstring zurÃ¼ck. <br>
    *         Ist maxLength <= 0 oder grÃ¶ÃŸer als die StringlÃ¤nge, wird der
    *         String unverÃ¤ndert zurÃ¼ckgegeben.
    */
   public static String limit(String string, int maxLength)
   {
      if (isNullOrEmpty(string))
         return "";

      if (string.length() <= maxLength || maxLength <= 0)
         return string;
      return string.substring(0, maxLength).trim();
   }

   /**
    * @param str
    * @return Liefert eine String fÃ¼r Suchoptimierung: in Kleinbuchstaben, mit
    *         deutschen Umlauten expandiert sowie ohne Accent-Zeichen.
    */
   public static String normalizeUmlauts(String str)
   {
      return normalizeUmlautsDefinedCasing(str, true);
   }

   public static String normalizeUmlautsDefinedCasing(String str, boolean lowerCase)
   {
      return replaceUmlauts(lowerCase ? str.toLowerCase() : str).replaceAll("[Ã©Ã¨ÃªÃ«]", "e")
            .replaceAll("[Ã¡Ã Ã¢]", "a").replaceAll("[ÃºÃ¹Ã»]", "u").replaceAll("[Ã§]", "c")
            .replaceAll("[Ã²Ã³Ã´Ãµ]", "o").replaceAll("[Ã¬Ã­Ã®Ã¯]", "i");
   }

   public static int nullSafeCompare(String one, String two, boolean caseSensitive)
   {
      if (one == null && two == null)
         return 0;

      if (one == null ^ two == null)
         return (one == null) ? -1 : 1;

      int result = caseSensitive ? one.compareTo(two) : one.compareToIgnoreCase(two);
      if (result > 0)
         return 1;
      else if (result < 0)
         return -1;
      return 0;
   }

   public static boolean nullSafeEquals(String val1, String val2)
   {
      return nullSafeCompare(val1, val2, true) == 0;
   }

   /**
    * Diese Mehtode escaped die enthaltenden AnfÃ¼hrungszeichen und umschlieÃŸt
    * den String mit AnfÃ¼hrungszeichen
    * 
    * @param string
    * @return
    */
   public static String quoteString(String string)
   {
      if (isNotNullOrEmpty(string))
         return "\"" + string.replaceAll("\"", "\"\"") + "\"";
      else
         return "\"\"";
   }

   /**
    * @param stringWithDoubleSpaces
    * @return Gibt den String ohne doppelte Leerzeichen zuÃ¼ck. Es wird
    *         auÃŸerdem ein trim ausgefÃ¼hrt.
    */
   public static String removeDoubleSpacesAndTrim(String stringWithDoubleSpaces)
   {
      return toEmptyIfNull(stringWithDoubleSpaces).replaceAll("( )+", " ").trim();
   }

   public static String removeQuotesAndSpaces(String in)
   {
      return toEmptyIfNull(in).replaceAll("[ 'Â´`\"-]", "");
   }

   public static String replaceCharsAndLimit(String value, int limit)
   {
      return limit(normalizeUmlautsDefinedCasing(value, false), limit);
   }

   public static String replaceUmlauts(String str)
   {
      if (isNullOrEmpty(str))
         return "";

      str = str.replace("Ã¤", "ae");
      str = str.replace("Ã¶", "oe");
      str = str.replace("Ã¼", "ue");
      str = str.replace("Ã„", "Ae");
      str = str.replace("Ã–", "Oe");
      str = str.replace("Ãœ", "Ue");
      str = str.replace("ÃŸ", "ss");
      return str;
   }

   public static String rightPad(String string, int length, int fill)
   {
      return rightPad(string, length, String.valueOf(fill));
   }

   public static String rightPad(String string, int length, String fill)
   {
      return pad(string, length, fill, PadType.RIGHT);
   }

   /**
    * PrÃ¼ft, ob eine Zeichenkette <b>null</b> ist und gibt entweder eine leere
    * Zeichenkette oder die Ã¼bergebene Zeichenkette zurÃ¼ck.
    * 
    * @param string
    *           zu prÃ¼fende {@link String Zeichenkette}
    * @return leere {@link String Zeichenkette}, wenn {@link String Parameter}
    *         <b>null</b>, sonst {@link String Parameter}
    */
   public static String toEmptyIfNull(String string)
   {
      if (string == null)
         return "";
      return string;
   }

   /**
    * PrÃ¼ft, ob eine Zeichenkette <b>null</b> oder <b>leer ("")</b> ist und
    * gibt den Platzhalter oder die Ã¼bergebene Zeichenkette zurÃ¼ck.
    * 
    * @param string
    *           zu prÃ¼fende {@link String Zeichenkette}
    * @param placeholder
    *           {@link String Platzhalter}
    * @return {@link String Platzhalter}, wenn {@link String Parameter}
    *         <b>null</b> oder <b>leer ("")</b> ist, sonst {@link String
    *         Parameter}.
    */
   public static String toPlaceholderIfNullOrEmpty(String string, String placeholder)
   {
      if (isNullOrEmpty(string))
         return toEmptyIfNull(placeholder);
      return string;
   }

   public static String toValidFolderName(String folderName)
   {
      folderName = replaceUmlauts(folderName);
      folderName = folderName.replaceAll("[?|<>: &\\\\//]", "_");
      return folderName;
   }

   public static String trimEnd(String string, char character)
   {
      if (isNullOrEmpty(string))
         return "";

      int lastPos = string.length() - 1;
      while (lastPos >= 0 && string.charAt(lastPos) == character)
      {
         string = string.substring(0, lastPos).trim();
         lastPos = string.length() - 1;
      }
      return string;
   }

   public static String trimStart(String string, char character)
   {
      if (isNullOrEmpty(string))
         return "";

      while (string.length() > 0 && string.charAt(0) == character)
         string = string.substring(1).trim();

      return string;
   }

   public static String unquoteString(String quotedString)
   {
      if (isNullOrEmpty(quotedString))
         return "";

      quotedString = quotedString.trim();

      if (quotedString.startsWith("\""))
         quotedString = quotedString.substring(1);

      if (quotedString.endsWith("\""))
         quotedString = quotedString.substring(0, quotedString.length() - 1);

      quotedString = quotedString.replaceAll("\"\"", "\"");

      return quotedString.trim();
   }

   private static String pad(String string, int length, String fill, PadType type)
   {
      if (string == null)
         return "";

      string = string.trim();

      if (string.length() >= length || isNullOrEmpty(fill, false))
         return string;

      while (string.length() < length)
      {
         switch (type)
         {
            case LEFT:
               string = fill + string;
               break;

            case RIGHT:
               string += fill;
               break;
         }
      }

      return string;
   }

   /**
    * Erzeugt ein vollstÃ¤ndiges XML-Tag und fÃ¼hrt dabei die Umwandlung zu
    * XML-Entities {@link convertToEntitiesForXML} durch.
    * 
    * @param tag
    *           Das einschlieÃŸende Tag z.B.: aus tag wird <tag></tag>
    * @param value
    *           Der Wert zum Tag z.B.: aus wert wird <tag>wert</tag>
    * @return vollstÃ¤ndiges XML-Tag bestehend aus Wert und Tag
    */
   public static String wrapTagValue(String tag, String value, String... attributes)
   {
      if (isNullOrEmpty(value))
         return openTag(tag, true, attributes);

      return openTag(tag, attributes) + value + closeTag(tag);
   }

   /**
    * Return the passed string but with the first letter being lower case. This
    * is useful e.g. for making a variable name out of a class name.
    * 
    * @param value
    * @return
    */
   public static String makeFirstLetterLowerCase(String value)
   {
      if (value == null)
         return null;

      if (value.length() == 0)
         return "";

      if (value.length() == 1)
         return value.toLowerCase();

      String first = value.substring(0, 1);
      String remaining = value.substring(1);

      return first.toLowerCase() + remaining;
   }

   public static String makeFirstLetterUpperCase(String value)
   {
      if (value == null)
         return null;

      if (value.length() == 0)
         return "";

      if (value.length() == 1)
         return value.toUpperCase();

      return value.substring(0, 1).toUpperCase() + value.substring(1);
   }

   /**
    * Returns an empty string if value is 0. Otherwise returns the string
    * representation of value.
    * 
    * @param value
    * @return
    */
   public static String toEmptyIfZero(long value)
   {
      if (value == 0)
         return "";

      return String.valueOf(value);
   }

   /**
    * Returns the number of occurrences of string {@code ofString} in string
    * {@code inString}
    * 
    * @param ofString
    * @param inString
    * @return
    */
   public static int getNumberOfOccurrences(String ofString, String inString)
   {
      return inString.length() - inString.replace(ofString, "").length();
   }
}