package main;

import commandManager.CommandInvoker;
import dataManager.OrganizationManager;
import organization.Organization;
import system.XMLSerializer;


public class Main {
    public static void main(String[] args) {
        CommandInvoker invoker = new CommandInvoker();
        XMLSerializer reader = new XMLSerializer();
        OrganizationManager.getInstance().setCollection(reader.deserialize());
        for (Organization organization : OrganizationManager.)
        invoker.inputCommands();
    }
}
