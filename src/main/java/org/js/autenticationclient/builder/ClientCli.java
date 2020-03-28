package org.js.autenticationclient.builder;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.cli.*;
import org.apache.commons.lang3.StringUtils;
import org.js.autenticationclient.bean.Application;
import org.js.autenticationclient.bean.Authentication;
import org.js.autenticationclient.registration.Registration;
import org.js.autenticationclient.registration.impl.RegistrationImpl;

import java.util.Random;

public class ClientCli {

    private static final String CREATE = "create";
    private static final String APPNAME ="appname";
    private static final String DESC = "desc";
    private static final String USERNAME = "u";
    private static final String PASSWORD = "p";

    public static void main(String[] args)   {

        Registration registration = new RegistrationImpl();

        CommandLineParser parser = new DefaultParser();
        Options options = prepareOptions();
        String appName = "";
        String appDesc = "";
        String userName = "";
        String password = "";
        try {
            CommandLine commandLine = parser.parse(prepareOptions(), args);
            String className = (String) commandLine.getParsedOptionValue(CREATE);

            if (commandLine.hasOption(APPNAME)) {
                appName = (String) commandLine.getParsedOptionValue(APPNAME);
            }

            if (commandLine.hasOption(DESC)) {
                appDesc = (String) commandLine.getParsedOptionValue(DESC);
            }

            if (commandLine.hasOption(USERNAME)) {
                userName = (String) commandLine.getParsedOptionValue(USERNAME);
            }

            if (commandLine.hasOption(PASSWORD)) {
                password = (String) commandLine.getParsedOptionValue(PASSWORD);
            }


            if(StringUtils.equals("app",className) ) {
                Application application = new Application.ApplicationBuilder()
                        .withAppName(appName)
                        .withDescription(appDesc)
                        .build();
               String applicationJson =  new Application.ApplicationBuilder(
                       registration.registerYouApplication(application)).buildJson();
                System.out.print(applicationJson);

            } else if(StringUtils.equals("auth",className)) {
                String authJson = new Authentication.AuthenticationBuilder(userName,password)
                        .buildJson();
                System.out.print(authJson);
            }

        } catch (ParseException | JsonProcessingException ex) {
            System.out.println(ex.getMessage());
            new HelpFormatter().printHelp("cli-random", options);
        }

    }

    private static Options prepareOptions() {
        Options options = new Options();
        options.addOption(getGenrationClass())
                .addOption(getApplicationName())
                .addOption(getApplicationDescription())
                .addOption(getUserName())
                .addOption(getPassword());
        return options;
    }

     private static Option getGenrationClass() {
        return Option.builder()
                .required()
                .desc("Which object you want? (app/auth)")
                .longOpt(CREATE)
                .type(String.class)
                .hasArg()
                .build();
     }

    private static Option getUserName() {
        return Option.builder()
                .desc("Your user name:")
                .longOpt(USERNAME)
                .type(String.class)
                .hasArg()
                .build();

    }

    private static Option getPassword() {
        return Option.builder()
                .desc("Your password:")
                .longOpt(PASSWORD)
                .type(String.class)
                .hasArg()
                .build();

    }

    private static Option getApplicationName() {
        return Option.builder()
                .desc("Application Name")
                .longOpt(APPNAME)
                .type(String.class)
                .hasArg()
                .build();

    }

    private static Option getApplicationDescription() {
        return Option.builder()
                .desc("Application Name")
                .longOpt(DESC)
                .type(String.class)
                .hasArg()
                .build();

    }
}



