

import java.util.*;
import java.util.LinkedList;

public class ClinicRecord {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        LinkList list = new LinkList();
        Date d=new Date();           //calling for Date class from java library
        int currentYear=d.getYear()+1900; //get the current year by using method getYear() in Date class
        LinkedList<Long> id = new LinkedList<Long>();
        String fname="", lname="", address="", bloodt="", gender ,day, month, birth="" ;
        int  year=0,age=0;
        long ID=0,contact=0;
        char stop='y';
        int todoSelect;

        /*id.add(12323564697L);
        id.add(99956498998L);
        id.add(14789731256L);
        id.add(78921346583L);
        list.insert(12323564697L,"Ali","Missaoui","27/11/1995",25,"Male","04546789454","11, Jalan Logan, 10450 George Town, Pulau Pinang","AB");
        list.insert(99956498998L,"Houssem","Hammami","12/06/1995",25,"Male","01234567895","146A, Jalan Macalister, George Town, 10400 George Town, Pulau Pinang","A+");
        list.insert(14789731256L,"Amir","bahri","30/02/1996",24,"Male","01598746532","274, Jalan Macalister, George Town, 11400 George Town, Pulau Pinang","O+");
        list.insert(78921346583L,"Haifa","Missaoui","20/04/1994",26,"Female","09874563214"," 142, Jalan Burma, 10400 George Town, Pulau Pinang","AB");*/

        do {
            System.out.println("\t-------------------------------------------------------");
            System.out.println("Greetings! welcome to PRMS: (Patient Record Management System) ");
            System.out.println("\t1/Add new patient record: \n" +
                    "\t2/Delete a patient record: \n" +
                    "\t3/View all patients records: \n" +
                    "\t4/update patients records: \n" +
                    "\t5/search patients record:\n" +
                    "\t6/Exit: ");
            todoSelect = in.nextInt();

            if (todoSelect == 1)         // add new patient record
            {
                if(id.isEmpty())
                {
                    System.out.print("Please enter Patient's ID number: ");
                    ID = in.nextLong();
                    id.add(ID);
                }
                else
                {
                    do {
                        System.out.println("Please enter you ID number:");
                        ID = in.nextLong();
                        if(id.contains(ID)){
                            System.out.println("Error!!, ID number is already used for another patient in records!");
                        }
                    }while(id.contains(ID));
                    id.add(ID);                 //adding ID number to ID list
                }
                in.nextLine();
                System.out.print("Please enter Patient's first name: ");
                fname = in.nextLine();
                System.out.print("Please enter Patient's last name: ");
                lname = in.nextLine();
                System.out.print("Please enter Patient's date of birth:" +
                        "\nDay: ");
                day = in.nextLine();

                System.out.print("Month: ");
                month = in.nextLine();
                System.out.print("Year: ");
                year = checkAge(currentYear);
                age = currentYear - year;
                birth = day + "/" + month + "/" + year;
                System.out.print("Please select Patient's gender: ");
                gender = genderValidator();                                     //a method that return a String male or female only if meets conditions m,M,f,F
                System.out.print("Please enter Patient's contact number: ");
                contact = in.nextLong();
                in.nextLine();
                System.out.print("Please enter Patient's address: \nBuilding number: ");
                String bn = in.nextLine();
                System.out.print("Street name: ");
                String stn = in.nextLine();
                System.out.print("Postal code: ");
                String pc = in.nextLine();
                System.out.print("City name: ");
                String cname = in.nextLine();
                System.out.print("State name: ");
                String staname = in.nextLine();
                address = bn + ", " + stn + ", " + pc + ", " + cname + ", " + staname;
                System.out.print("Please enter patient's blood type: ");
                bloodt = bloodTypeCheck();
                list.insert(ID,fname,lname,birth,age,gender,contact,address,bloodt);

            }
            if (todoSelect == 2)    // delete an existing patient's record
            {
                System.out.print("Please enter ID number of the patient you want to delete: ");
                long inputID = in.nextLong();
                list.deleteRecordID(inputID);
            }
            else if (todoSelect == 3)    // view all patients records
            {
                if(list.isEmpty()){
                    System.out.println("Patients record is empty");
                }
                else {
                    list.displayList();
                }
            }
            else if (todoSelect == 4)    // update a patient's record : Update one of the details inside record
            {
                long IDin;
                do {
                    System.out.println("Please enter patient's ID number:");
                    IDin = in.nextLong();
                    if (id.contains(IDin)) {
                        list.searchID(list.getFirst(),IDin);
                        in.nextLine();
                        int cont=0;
                        do {
                            System.out.println("Please choose one of the details to update in the record: ");
                            System.out.print("  1/ID number:\n  2/first name:\n  3/last name:\n  4/birth:\n  5/gender:\n  6/contact:\n  7/address:\n  8/blood type:\n ");
                            int entry = in.nextInt();
                            if (entry == 1) //update ID number
                            {
                                long newID;
                                System.out.println("Please enter new ID number: ");
                                newID = in.nextLong();
                                updateRecordID(list.getFirst(), ID, newID);
                                id.remove(IDin);
                                id.add(newID);
                            }
                            else if (entry == 2) // update first name
                            {
                                String newfname;
                                System.out.print("Please enter new first name: ");
                                newfname=in.nextLine();
                                updateRecordFname(list.getFirst(), fname,newfname);

                            }
                            else if (entry == 3) // update last name
                            {
                                String newlname="";
                                System.out.print("Please enter new last name: ");
                                newlname=in.nextLine();
                                updateRecordStrings(list.getFirst(), lname, newlname, entry);
                                System.out.print("do you want to continue? press '0' for yes, press '1' for no");
                                cont = in.nextInt();
                            }
                            else if (entry == 4) // update birth date > automatically update age again
                            {
                                System.out.print("Please enter Patient's new date of birth:" +
                                        "\nDay: ");
                                int newDay = in.nextInt();

                                System.out.print("Month: ");
                                int newMonth = in.nextInt();
                                System.out.print("Year: ");
                                int newYear = checkAge(currentYear);
                                String newBirth = newDay + "/" + newMonth + "/" + newYear;
                                int newAge = currentYear - newYear;
                                updateRecordStrings(list.getFirst(),birth,newBirth,entry);
                                updateRecordAge(list.getFirst(),age,newAge);
                                System.out.print("do you want to continue? press '0' for yes, press '1' for no");
                                cont = in.nextInt();
                            }
                            else if (entry == 5) // update gender
                            {
                                String newgender="";
                                System.out.print("Please enter new gender: ");
                                gender = genderValidator();
                                updateRecordStrings(list.getFirst(), gender, newgender, entry);
                                System.out.print("do you want to continue? press '0' for yes, press '1' for no");
                                cont = in.nextInt();
                            }
                            else if (entry == 6) // update contact
                            {
                                long newcontact;
                                System.out.print("Please enter new contact: ");
                                newcontact=in.nextLong();
                                updateRecordContact(list.getFirst(),contact,newcontact);

                            }
                            else if (entry == 7) // update address
                            {
                                String newadress="";
                                System.out.print("Please enter new address: ");
                                updateRecordStrings(list.getFirst(),address,newadress,entry);
                            }
                            else if (entry == 8) // update bloodType
                            {
                                String newbloodtype="";
                                System.out.print("Please enter new boodtype: ");
                                newbloodtype = bloodTypeCheck();
                                updateRecordStrings(list.getFirst(),bloodt,newbloodtype,entry);
                            }
                            System.out.println("do you want to continue? press '0' for yes, press '1' for no: ");
                            cont = in.nextInt();
                        }while(cont==0);
                    }
                    else
                        System.out.println("Error!!ID number not found, Please enter a valid ID number.");
                }while(!id.contains(IDin));
            }
            else if(todoSelect == 5)     // search in records using 1 of the criteria provided
            {
                System.out.print("Please enter ID number of the patient you want to search: ");
                long search;
                do{
                    search = in.nextLong();
                    if(id.contains(search)){
                        list.searchID(list.getFirst(),search);
                    }
                    else{
                        System.out.print("ERROR ID number not found!, Please enter a valid ID number: ");
                    }

                }while(!id.contains(search));


            }
            else if (todoSelect == 6)    // exit program using System.exit() method            {
            {    System.out.println("Thanks for using our PRMS!, Have a nice day Sir/Madame");
                System.exit(0);
            }


            System.out.println("You want to continue?: Press 'y' , No?: press 'n'");
            stop=in.next().charAt(0);
            stop=Character.toUpperCase(stop);
        }while(stop == 'Y');
    }

    public static String genderValidator() {
        Scanner in = new Scanner(System.in);
        char entryIn,entryIn2;
        String entryOut="";
        do{
            entryIn = in.next().charAt(0);
            entryIn2 = Character.toUpperCase(entryIn);
            if(entryIn2 == 'M' ) {
                entryOut="Male";
            }
            else if(entryIn2 == 'F' ){
                entryOut="Female";
            }
            else{
                System.out.println("Sorry, Error!, Please enter only m/M(Male) or f/F(Female)");
            }
        }while(entryIn2 !='M' && entryIn2 !='F');
        return entryOut;
        }  // function return Male or Female uppercase after confirming with user's input

    public static int checkAge (int b) {
        Scanner in = new Scanner(System.in);
        int year;
        do{
            year = in.nextInt();
            if (year > b || year < 0) {
                System.out.println("ERROR!!, please enter a year between 0 and the current year: " + b);
            }

        }while(year > b || year < 0);
        return year;
    }

    public static String bloodTypeCheck(){
        LinkedList<String> bloodType = new LinkedList<String>();
        bloodType.add("A+");
        bloodType.add("A-");
        bloodType.add("B+");
        bloodType.add("B-");
        bloodType.add("O+");
        bloodType.add("O-");
        bloodType.add("AB+");
        bloodType.add("AB-");
        Scanner in = new Scanner(System.in);
        String bloodTypeOut="", a;
        do{
            a = in.nextLine();
            a=a.toUpperCase();
            if(bloodType.contains(a)){
                bloodTypeOut=a;
            }
            else{
                System.out.println("ERROR!!,Please pick one of these blood types:\n    A+\n    A-\n    B+\n    B-\n    O+\n    AB+\n    AB-");
            }
        }while(!bloodType.contains(a));
        return bloodTypeOut;
    }

    public static void updateRecordAge(LinkNode readNode, int oldAge, int newAge){
        Scanner in = new Scanner(System.in);
        while (readNode != null) {
            if (readNode.age == oldAge) {
                readNode.age=newAge;
                readNode.displayLink();

            }
            readNode = readNode.next;
        }
    }

    public static void updateRecordContact(LinkNode readNode, long oldContact, long newContact) {
        while (readNode != null) {
            if (readNode.ID == oldContact) {
                readNode.ID=newContact;
                readNode.displayLink();

            }
            readNode = readNode.next;
        }
    }

    public static void updateRecordID(LinkNode readNode, long oldID, long newID) {
        Scanner in = new Scanner(System.in);
        while (readNode != null) {
            if (readNode.ID == oldID) {
                readNode.ID=newID;
                System.out.println("ID number updated successfully!");
                readNode.displayLink();
            }
            readNode = readNode.next;
        }
    }

    public static void updateRecordFname(LinkNode readNode, String oldFname, String newFname){
        while (readNode != null) {
            if (readNode.fname.equals(oldFname)) {
                readNode.fname = newFname;
                System.out.println("First name updated successfully!");
                readNode.displayLink();
            }
            readNode = readNode.next;
        }
    }

    public static void updateRecordStrings(LinkNode readNode,String oldString, String newString,int a){
        Scanner in = new Scanner(System.in);
            if(a==2) {
                while (readNode != null) {
                    if (readNode.fname.equals(oldString)) {
                        readNode.fname = newString;
                        System.out.println("Record updated successfully!.");
                        readNode.displayLink();
                    }readNode = readNode.next;
                }
            }
            /*else if()
            {}
            else if()
            {}
            else if()
            {}
            else if()
            {}
            else if()
            {}
            else if()
            {}*/
    }


}




