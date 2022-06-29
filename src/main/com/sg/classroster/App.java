package main.com.sg.classroster;

import main.com.sg.classroster.controller.ClassRosterController;
import main.com.sg.classroster.dao.ClassRosterDao;
import main.com.sg.classroster.dao.ClassRosterDaoFileImpl;
import main.com.sg.classroster.ui.ClassRosterView;
import main.com.sg.classroster.ui.UserIO;
import main.com.sg.classroster.ui.UserIOConsoleImpl;

public class App {
    public static void main(String[] args) {

        UserIO io = new UserIOConsoleImpl();
        ClassRosterView view = new ClassRosterView(io);
        ClassRosterDao data = new ClassRosterDaoFileImpl();

        ClassRosterController controller = new ClassRosterController(data, view);

        controller.run();
    }
}
