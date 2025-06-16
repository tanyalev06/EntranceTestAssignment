public class CaesarCipherImplementation {
    public static String caesarCipher (String text, int shift) {
        StringBuilder result = new StringBuilder();
        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if (Character.isLetter(ch)) {
                if ((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z')) {
                    int old = Character.isUpperCase(ch) ? 'A' : 'a';
                    ch = (char) ((ch - old + shift + 26) % 26 + old);
                } else if ((ch >= 'А' && ch <= 'Я') || (ch >= 'а' && ch <= 'я')) {
                    int old = Character.isUpperCase(ch) ? 'А' : 'а';
                    ch = (char) ((ch - old + shift + 32) % 32 + old);
                }
            }
            result.append(ch);
        }
        return result.toString();
    }
}
