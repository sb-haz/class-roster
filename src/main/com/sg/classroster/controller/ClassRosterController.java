package main.com.sg.classroster.controller;

import main.com.sg.classroster.ui.ClassRosterView;
import main.com.sg.classroster.ui.UserIO;
import main.com.sg.classroster.ui.UserIOConsoleImpl;

public class ClassRosterController {

    private ClassRosterView view = new ClassRosterView();
    private UserIO io = new UserIOConsoleImpl();

    public void run() {

        boolean keepGoing = true;
        int menuSelection;

        while (keepGoing) {

            menuSelection = getMenuSelection();

            switch (menuSelection) {
                case 1:
                    io.print("LIST STUDENTS");
                    break;
                case 2:
                    io.print("CREATE STUDENT");
                    break;
                case 3:
                    io.print("VIEW STUDENT");
                    break;
                case 4:
                    io.print("REMOVE STUDENT");
                    break;
                case 5:
                    keepGoing = false;
                    break;
                default:
                    io.print("UNKNOWN COMMAND");
            }
        }
        io.print("GOOD BYE");
    }

    private int getMenuSelection(){
        return view.printMenuAndGetSelection();
    }

}