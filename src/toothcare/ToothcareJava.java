/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package toothcare;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import dentalappointment.data.Appointments;
import dentalappointment.data.DataList;
import dentalappointment.data.Treatment;
import dentalappoinment.screens.AppoinmentScreen;

public class ToothcareJava {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        AppoinmentScreen appoinmentScreen = new AppoinmentScreen();
        saveTreatments();
        while (true) {
            try {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } catch (IOException | InterruptedException e) {
            }
            System.out.println("__________________________________");
            System.out.println("Welcome to the \"Tooth Care\" ");
            System.out.println("__________________________________");
            menu();
            System.out.println("__________________________________");

            System.out.print("Enter option: ");
            String slection = scanner.nextLine();

            if (slection.equals("1")) {
                appoinmentScreen.addAppoiment();
            } else if (slection.equals("2")) {
                appoinmentScreen.listAppoinments();
            } else if (slection.equals("3")) {
                appoinmentScreen.searchAppoments();
            } else if (slection.equals("4")) {
                listTreatments();
            } else if (slection.equals("5")) {
                appoinmentScreen.viewAppointmentsByDate();
            } else if (slection.equals("6")){
                appoinmentScreen.displayUpdatedChannellingSlots();
            }else if (slection.equals("7")){
                appoinmentScreen.listTreatments();
            }else if (slection.equals("8")) {
                appoinmentScreen.acceptPaymentAndGenerateInvoice();
            }else if (slection.equals("9")) {
                appoinmentScreen.updateAppointmentDetails();
            }else if (slection.equals("10")) {
                return;
            }

        }
    }

    private static void menu() {
        System.out.println("\n1.Add Appointment");
        System.out.println("2.List Appointment");
        System.out.println("3.Search Appointment");
        System.out.println("4.List Treatments");
        System.out.println("5.View by Date");
        System.out.println("6.View Schedule");
        System.out.println("7.Calculate Fee");
        System.out.println("8.Payment Now");
        System.out.println("9.Update Appointment");
        System.out.println("10.Exit");

    }

    private static void saveTreatments() {
        Treatment t1 = new Treatment("Filling", 100);
        Treatment t2 = new Treatment("Whitening", 200);
        Treatment t3 = new Treatment("Filling", 300);
        Treatment t4 = new Treatment("Nerve filling", 400);
        Treatment t5 = new Treatment("Root Canel Therapy", 500);

        DataList.treatmentList.add(t1);
        DataList.treatmentList.add(t2);
        DataList.treatmentList.add(t3);
        DataList.treatmentList.add(t4);
        DataList.treatmentList.add(t5);
    }

    private static void listTreatments() {
        for (Treatment treatment : DataList.treatmentList) {
            System.out.println("------------------------");
            System.out.println("Treatment:" + treatment.getTreatmentName());
            System.out.println("Price:" + treatment.getPrice());
        }
        System.out.print("Press any key to continue...");
        scanner.nextLine();
    }

}
