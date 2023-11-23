package base.utils;

import lombok.extern.slf4j.Slf4j;
import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.regex.Pattern;

@Slf4j
@Component
public class CommonUtils {

    public static String generateRandomNum(int quantity) {
        Random random = new Random();
        StringBuilder verifyNum = new StringBuilder();
        while (true) {
            int num = random.nextInt(8) + 1;
            verifyNum.append(num);
            if (verifyNum.length() >= quantity) {
                break;
            }
        }
//        System.out.println(verifyNum.toString());
        return verifyNum.toString();
    }

    public static String generateDefaultPassword() {
        PasswordGenerator gen = new PasswordGenerator();
        CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
        CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
        lowerCaseRule.setNumberOfCharacters(2);

        CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
        CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
        upperCaseRule.setNumberOfCharacters(2);

        CharacterData digitChars = EnglishCharacterData.Digit;
        CharacterRule digitRule = new CharacterRule(digitChars);
        digitRule.setNumberOfCharacters(2);

        CharacterData specialChars = new CharacterData() {
            @Override
            public String getErrorCode() {
                return "99";
            }

            public String getCharacters() {
                return "!@#$%^&*";
            }
        };
        CharacterRule splCharRule = new CharacterRule(specialChars);
        splCharRule.setNumberOfCharacters(2);

        String password = gen.generatePassword(8, splCharRule, lowerCaseRule,
                upperCaseRule, digitRule);
        return password;
    }

    public static final boolean isValidRefNo(String refNo) {
        Pattern pattern = Pattern.compile("^REQ_\\d+_\\d+$");
        if (pattern.matcher(refNo).matches()) {
            return true;
        }
        return false;
    }

    public static final String makeRefNo(String clientId) {
        return "RES" + "_" + clientId + "_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    }

    public static void main(String[] args) {
        generateRandomNum(6);
        System.out.println(generateDefaultPassword());
    }
}
