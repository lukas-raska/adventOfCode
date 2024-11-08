package year_2015.day_11;

public class PasswordGenerator {

    private final String allowedAlphabet = "abcdefghjkmnpqrstvwxyz"; //excluded letters i, o, l

    private static final int ALLOWED_PASSWORD_LENGTH = 8;


    private boolean hasIncreasingStraight(String password,
                                          int lengthOfStraight) {
        for (int i = 0; i < password.length() - lengthOfStraight; i++) {

            int count = 1;
            for (int j = 0; j < lengthOfStraight-1; j++) {
                int currentCharValue = password.charAt(i + j);
                int nextCharValue = password.charAt(i + j + 1);
                if (nextCharValue - currentCharValue == 1) {
                    count++;
                } else {
                    break;
                }
            }
            if (count == lengthOfStraight) {
                return true;
            }
        }

        return false;
    }

    private boolean isValid(String password) {

        int lengthOfStraight = 3;

        if (!password.matches("^[a-z]{%d}$".formatted(ALLOWED_PASSWORD_LENGTH))) {
            return false;
        }
        if (!hasIncreasingStraight(password, lengthOfStraight)) {
            return false;
        }
        return password.matches(".*([a-z])\\1.*([a-z])\\2.*");
    }

    private String incrementPassword(String oldPassword) {

        StringBuilder newPassword = new StringBuilder(oldPassword);
        char lastLetterInAlphabet = allowedAlphabet.charAt(allowedAlphabet.length() - 1);
        char firstLetterInAlphabet = allowedAlphabet.charAt(0);

        int indexToChange = oldPassword.length() - 1;

        char letterToChange = oldPassword.charAt(indexToChange);
        int positionInAlphabet = allowedAlphabet.indexOf(letterToChange);

        if (letterToChange != lastLetterInAlphabet) {
            newPassword.replace(
                    indexToChange,
                    indexToChange + 1,
                    String.valueOf(allowedAlphabet.charAt(positionInAlphabet + 1)));

        } else {
            while (oldPassword.charAt(indexToChange) == lastLetterInAlphabet) {
                newPassword.replace(
                        indexToChange,
                        indexToChange + 1,
                        String.valueOf(firstLetterInAlphabet));
                char previousLetter = oldPassword.charAt(indexToChange - 1);
                char newLetter = allowedAlphabet.charAt((allowedAlphabet.indexOf(previousLetter) + 1) % allowedAlphabet.length());
                newPassword.replace(
                        indexToChange - 1,
                        indexToChange,
                        String.valueOf(newLetter));
                indexToChange--;
            }
        }
        return newPassword.toString();
    }

    public String generateNewPassword(String oldPassword){
        String newPassword = oldPassword;
        do {
             newPassword = incrementPassword(newPassword);
        }        while (!isValid(newPassword));

        return newPassword;
    }
}
