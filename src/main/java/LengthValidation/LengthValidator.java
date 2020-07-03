package LengthValidation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;

public class LengthValidator {

    private static int LINE_MAX_LIMIT = 80;
    private static  boolean isDebugEnabled = false;

    public static void logger(String message) {
        if (isDebugEnabled) {
            System.out.println(message);
        }
    }

    public static void validateLineLength(String path) throws LineLengthExceededException, NoSuchElementException {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(path));
            String line;

            int lineCount = 0;
            do {
                line = reader.readLine();
                if (line != null) {
                    int count = line.length();
                    if(count > LINE_MAX_LIMIT){
                        throw new LineLengthExceededException(String.format("Line length is exceeded in line number %d. Maximum length is %s for a single line.", lineCount, LINE_MAX_LIMIT));
                    }
                }
                lineCount += 1;
            } while (line != null);

            reader.close();
            logger(String.format("Completed length validations. All lines in this file contain less than %s characters",  LINE_MAX_LIMIT));

        } catch (IOException e) {
            logger(String.format("An IOException occurred for file name : %s",  path));
            throw new NoSuchElementException("An error occurred during IO operation");
        } catch(NoSuchElementException e) {
            logger(String.format("Invalid file name given : %s",  path));
            throw new NoSuchElementException("Invalid file name");
        }

    }

    public static void main(String[] args){
        String filePath = "";
        try {
            if(args.length > 0) {
                filePath = args[0];
            }
            if(args.length > 1) {
                isDebugEnabled = Boolean.valueOf(args[1]);
            }

            logger(String.format("Started line length validations for file : %s",  filePath));
            LengthValidator.validateLineLength(filePath);
        } catch(Exception e){
            logger(String.format("An error occurred. - %s",  e.getMessage()));
        }
    }
}
