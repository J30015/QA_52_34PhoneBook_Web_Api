package data_providers;

import dto.UserLombok;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserDataProvider {
    @DataProvider
    public Iterator<UserLombok> dataProviderWrongPasswordForRegistration() {
        List<UserLombok> list = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader
                (new FileReader("src/test/resources/error_password_for_registration.csv"))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] splitLine = line.split(",");
                list.add(UserLombok.builder()
                        .username(splitLine[0])
                        .password(splitLine[1]).build());
                line = bufferedReader.readLine();
            }


        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("created exception");
        }
        return list.listIterator();


    }


    @DataProvider
    public Iterator<UserLombok> dataProviderWrongEmailForRegistration() {
        List<UserLombok> list = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader
                (new FileReader("src/test/resources/error_email_for_registration.csv"))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] splitLine = line.split(",");
                list.add(UserLombok.builder()
                        .username(splitLine[0])
                        .password(splitLine[1]).build());
                line = bufferedReader.readLine();
            }


        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("created exception");
        }
        return list.listIterator();

    }


    @DataProvider
    public Iterator<UserLombok> dataProviderWrongPasswordForLogin() {
        List<UserLombok> list = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader
                (new FileReader("src/test/resources/error_password_login.csv"))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] splitLine = line.split(",");
                list.add(UserLombok.builder()
                        .username(splitLine[0])
                        .password(splitLine[1]).build());
                line = bufferedReader.readLine();
            }


        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("created exception");
        }
        return list.listIterator();


    }


    @DataProvider
    public Iterator<UserLombok> dataProviderWrongEmailForLogin() {
        List<UserLombok> list = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader
                (new FileReader("src/test/resources/error_email_for_login.csv"))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] splitLine = line.split(",");
                list.add(UserLombok.builder()
                        .username(splitLine[0])
                        .password(splitLine[1]).build());
                line = bufferedReader.readLine();
            }


        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("created exception");
        }
        return list.listIterator();


    }
}
