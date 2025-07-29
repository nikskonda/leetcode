package by.nikskonda.lc;


public class LC1957 {

  public String makeFancyString(String s) {
    if (s.length() < 3) {
      return s;
    }

    StringBuilder sb = new StringBuilder();
    sb.append(s.charAt(0)).append(s.charAt(1));

    for (int i = 2; i < s.length(); i++) {
      int currentLen = sb.length();
      if (sb.charAt(currentLen - 2) == sb.charAt(currentLen - 1)
          && sb.charAt(currentLen - 1) == s.charAt(i)) {
        continue;
      } else {
        sb.append(s.charAt(i));
      }
    }

    return sb.toString();
  }

}