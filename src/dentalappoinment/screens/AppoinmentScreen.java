/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dentalappoinment.screens;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import dentalappointment.data.Appointments;
import dentalappointment.data.DataList;
import dentalappointment.data.Treatment;


public class AppoinmentScreen {

    static Scanner scanner = new Scanner(System.in);

    public void addAppoiment() {
        displayAvailableChannellingSlots();

        System.out.println("Type Date (YYYY-MM-DD) : ");
        String date = scanner.nextLine();

        // Validate the entered date against the available channelling dates
        if (!isValidChannellingDate(date)) {
            System.out.println("Invalid channelling date. Please select a valid date.");
            return;
        }

        System.out.println("Type Time (hh:mm) : ");
        String time = scanner.nextLine();

        // Validate the entered time against the available channelling times
        if (!isValidChannellingTime(date, time)) {
            System.out.println("Invalid channelling time. Please select a valid time.");
            return;
        }

        System.out.println("Do you really want to reserve the appointment? (yes/no): ");
        String confirmation = scanner.nextLine().toLowerCase();
        System.out.println("1000LKR payment accepted (yes/no): ");
        String pay = scanner.nextLine().toLowerCase();

        if (!confirmation.equals("yes") && !pay.equals("yes")) {
            System.out.println("Appointment reservation canceled.");
            return;
        }

        System.out.println("Type Patient Id: ");
        // No need to get patientId from the user, auto-increment it
        int patientId = DataList.appoinmentList.size() + 1;

        // Ask for additional details
        System.out.println("Type Patient Name: ");
        String patientName = scanner.nextLine();

        System.out.println("Type Patient Address: ");
        String address = scanner.nextLine();

        System.out.println("Type Patient Telephone Number: ");
        String telephoneNumber = scanner.nextLine();

        // Calculate registration fee
        int registrationFee = 1000;

        Appointments appointment = new Appointments();
        appointment.setId(String.valueOf(patientId));
        appointment.setDate(date);
        appointment.setTime(time);
        appointment.setPatientId(patientId);
        appointment.setAddress(address);
        appointment.setMobile(telephoneNumber);
        appointment.setName(patientName);
        appointment.setRegistrationFee(registrationFee);
        DataList.appoinmentList.add(appointment);

        System.out.println("__________________________________");
        displayPatientDetails(appointment, patientName, address, telephoneNumber, registrationFee);
        System.out.println("__________________________________");
        System.out.println("New appointment added successfully");
        System.out.println("__________________________________");

        displayUpdatedChannellingSlots();

        System.out.print("Press any key to continue...");
        scanner.nextLine();
    }

    public void displayPatientDetails(
            Appointments appointment, String patientName, String address, String telephoneNumber, int registrationFee) {
        System.out.println("Patient ID: " + appointment.getId());
        System.out.println("Patient Name: " + patientName);
        System.out.println("Address: " + address);
        System.out.println("Telephone Number: " + telephoneNumber);
        System.out.println("Date: " + appointment.getDate());
        System.out.println("Time: " + appointment.getTime());
        System.out.println("Registration Fee: LKR " + registrationFee);
    }


    public void displayAvailableChannellingSlots() {
        System.out.println("__________________________________");
        System.out.println("Available Channelling Slots:");
        System.out.println("__________________________________");
        System.out.println("Monday: 06.00 pm - 09.00 pm");
        System.out.println("Wednesday: 06.00 pm - 09.00 pm");
        System.out.println("Saturday: 03.00 pm - 10.00 pm");
        System.out.println("Sunday: 03.00 pm - 10.00 pm");
        System.out.println("__________________________________");
    }

    public void displayUpdatedChannellingSlots() {
        System.out.println("Updated Channelling Slots:");
        System.out.println("__________________________________");

        List<Appointments> appointments = DataList.appoinmentList;

        displayChannellingSlotsForDay(appointments, DayOfWeek.MONDAY);
        displayChannellingSlotsForDay(appointments, DayOfWeek.WEDNESDAY);
        displayChannellingSlotsForDay(appointments, DayOfWeek.SATURDAY);
        displayChannellingSlotsForDay(appointments, DayOfWeek.SUNDAY);
    }

    public void displayChannellingSlotsForDay(List<Appointments> appointments, DayOfWeek dayOfWeek) {
        System.out.println(dayOfWeek + ":");
        appointments.stream()
                .filter(appointment -> {
                    LocalDate localDate = LocalDate.parse(appointment.getDate());
                    return localDate.getDayOfWeek() == dayOfWeek;
                })
                .forEach(appointment -> System.out.println(appointment.getTime()));
        System.out.println();
    }

    public boolean isValidChannellingDate(String date) {
        // Implement logic to validate against allowed channelling dates
        return isWeekday(date, DayOfWeek.MONDAY) ||
                isWeekday(date, DayOfWeek.WEDNESDAY) ||
                isWeekday(date, DayOfWeek.SATURDAY) ||
                isWeekday(date, DayOfWeek.SUNDAY);
    }

    public boolean isValidChannellingTime(String date, String time) {
        // Implement logic to validate against allowed channelling times
        LocalTime channellingStartTime = LocalTime.of(15, 0); // 03:00 PM
        LocalTime channellingEndTime = LocalTime.of(22, 0);   // 10:00 PM

        LocalTime enteredTime = LocalTime.parse(time);

        return enteredTime.isAfter(channellingStartTime) && enteredTime.isBefore(channellingEndTime);
    }

    public boolean isWeekday(String date, DayOfWeek dayOfWeek) {
        LocalDate localDate = LocalDate.parse(date);
        return localDate.getDayOfWeek() == dayOfWeek;
    }


    public void listAppoinments() {
        listDownAppointments(DataList.appoinmentList);
        System.out.print("Press any key to continue...");
        scanner.nextLine();
    }

    public void viewAppointmentsByDate() {
        System.out.println("Enter Date to filter appointments (YYYY-MM-DD): ");
        String dateToFilter = scanner.nextLine();

        List<Appointments> filteredAppointments = DataList.appoinmentList.stream()
                .filter(appointment -> appointment.getDate().equals(dateToFilter))
                .collect(Collectors.toList());

        if (filteredAppointments.isEmpty()) {
            System.out.println("No appointments found for the given date.");
        } else {
            System.out.println("Appointments for date " + dateToFilter + ":");
            listDownAppointments(filteredAppointments);
        }

        System.out.print("Press Enter to continue...");
        scanner.nextLine();
    }


    public void searchAppoments() {
        System.out.print("Patient Id: ");
        int id = getPatientIdFromUser();

        List<Appointments> searchedList = DataList.appoinmentList.stream()
                .filter(appointment -> appointment.getPatientId() == id)
                .collect(Collectors.toList());

        listDownAppointments(searchedList);
        System.out.print("Press any key to continue...");
        scanner.nextLine();
    }

    public void searchAppoments(int idno) {
        int id = idno;

        List<Appointments> searchedList = DataList.appoinmentList.stream()
                .filter(appointment -> appointment.getPatientId() == id)
                .collect(Collectors.toList());

        listDownAppointments(searchedList);
        System.out.print("Press any key to continue...");
        scanner.nextLine();
    }

    public void listDownAppointments(List<Appointments> list) {
        if (list.isEmpty()) {
            System.out.println("No appointments to display.");
            return;
        }

        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-15s | %-15s | %-20s | %-15s | %-15s | %-15s | %-10s | %-15s | %-10s |\n",
                "Appointment ID", "Pat ID", "Name", "Mobile", "Address", "Date", "Time", "Registration Fee", "Total Fee");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        for (Appointments appointment : list) {
            System.out.printf("| %-15s | %-15s | %-20s | %-15s | %-15s | %-15s | %-10s | %-15s | %-10s |\n",
                    appointment.getId(), appointment.getPatientId(), appointment.getName(), appointment.getMobile(),
                    appointment.getAddress(), appointment.getDate(), appointment.getTime(),
                    appointment.getRegistrationFee() == 0 ? "Not calculated yet" : appointment.getRegistrationFee(),
                    appointment.getTotalFee() == 0 ? "Not calculated yet" : appointment.getTotalFee());
        }

        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }



    public int getPatientIdFromUser() {
        int patientId = 0;
        boolean isValidInput = false;

        while (!isValidInput) {
            System.out.println("Type Patient Id (integer): ");
            String input = scanner.nextLine();

            try {
                patientId = Integer.parseInt(input);
                isValidInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }

        return patientId;
    }

    public void listTreatments() {
        System.out.println("Available Treatments:");
        System.out.println("1. Cleanings");
        System.out.println("2. Whitening");
        System.out.println("3. Filling");
        System.out.println("4. Nerve Filling");
        System.out.println("5. Root Canal Therapy");

        System.out.print("Enter Appointment ID: ");
        String appointmentId = scanner.nextLine();

        Appointments appointment = findAppointmentById(appointmentId);
        if (appointment == null) {
            System.out.println("Appointment not found.");
            return;
        }

        List<Treatment> selectedTreatments = getSelectedTreatments();

        int totalFee = calculateTotalFee(selectedTreatments);
        appointment.setTotalFee(totalFee);

        System.out.println("Bill calculated and added to the total fee for the appointment.");
    }

    private List<Treatment> getSelectedTreatments() {
        List<Treatment> selectedTreatments = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            System.out.print(i + ". " + getTreatmentNameByIndex(i) + " done (yes/no): ");
            String response = scanner.nextLine().toLowerCase();

            if (response.equals("yes")) {
                selectedTreatments.add(getTreatmentByIndex(i));
            }
        }

        return selectedTreatments;
    }

    private String getTreatmentNameByIndex(int index) {
        switch (index) {
            case 1:
                return "Cleanings";
            case 2:
                return "Whitening";
            case 3:
                return "Filling";
            case 4:
                return "Nerve Filling";
            case 5:
                return "Root Canal Therapy";
            default:
                return "Unknown Treatment";
        }
    }

    private Treatment getTreatmentByIndex(int index) {
        return DataList.treatmentList.get(index - 1);
    }

    private int calculateTotalFee(List<Treatment> treatments) {
        return treatments.stream()
                .mapToInt(Treatment::getPrice)
                .sum();
    }


    public void acceptPaymentAndGenerateInvoice() {
        System.out.print("Enter Patient ID for payment: ");
        String patientId = String.valueOf(getPatientIdFromUser());

        Appointments appointment = findAppointmentById(patientId);
        if (appointment == null) {
            System.out.println("Appointment not found for the given Patient ID.");
            return;
        }

        System.out.println("Invoice for Patient ID " + patientId);
        System.out.println("--------------------------------------");
        System.out.println("Appointment ID: " + appointment.getId());
        System.out.println("Patient ID: " + appointment.getPatientId());
        System.out.println("Name: " + appointment.getName());
        System.out.println("Mobile: " + appointment.getMobile());
        System.out.println("Address: " + appointment.getAddress());
        System.out.println("Date: " + appointment.getDate());
        System.out.println("Time: " + appointment.getTime());
        System.out.println("Registration Fee: " + appointment.getRegistrationFee());
        System.out.println("Total Fee: " + appointment.getTotalFee());
        System.out.println("--------------------------------------");

        System.out.print("Enter the full amount for payment: ");
        int paymentAmount = scanner.nextInt();

        if (paymentAmount >= appointment.getTotalFee()) {
            System.out.println("--------------------------------------");
            System.out.println("Payment successful!");
            System.out.println("Thank you for your payment.");
        } else {
            System.out.println("Insufficient payment amount. Payment failed.");
        }
    }

    public void updateAppointmentDetails() {
        System.out.print("Enter Appointment ID for update: ");
        String appointmentId = scanner.nextLine();

        Appointments appointment = findAppointmentById(appointmentId);
        if (appointment == null) {
            System.out.println("Appointment not found for the given ID.");
            return;
        }

        System.out.println("Current Details for Appointment ID " + appointmentId);
        System.out.println("--------------------------------------");
        searchAppoments(Integer.parseInt(appointmentId));

        System.out.println("Enter new details:");

        System.out.print("Enter Date (YYYY-MM-DD): ");
        String newDate = scanner.nextLine();
        appointment.setDate(newDate);

        System.out.print("Enter Time (hh:mm): ");
        String newTime = scanner.nextLine();
        appointment.setTime(newTime);

        System.out.println("Appointment details updated successfully!");
        System.out.println("--------------------------------------");
        searchAppoments(Integer.parseInt(appointmentId));
    }
    private Appointments findAppointmentById(String appointmentId) {
        return DataList.appoinmentList.stream()
                .filter(appointment -> appointment.getId().equalsIgnoreCase(appointmentId))
                .findFirst()
                .orElse(null);
    }

}
