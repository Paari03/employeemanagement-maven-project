import java.util.Scanner;
import java.sql.SQLException;

import com.i2i.employeemanagement.course.controller.CourseController;
import com.i2i.employeemanagement.department.controller.DepartmentController;
import com.i2i.employeemanagement.employee.controller.EmployeeController;
import com.i2i.employeemanagement.exception.EmployeeException;

/**
 * This is the main controller for the Employee Management System.
 * @author Paari
 */
public class MainController {
    private EmployeeController employeeController = new EmployeeController();
    private DepartmentController departmentController = new DepartmentController();
    private CourseController courseController = new CourseController();
    private Scanner scanner = new Scanner(System.in);

    public void mainFunction() {
        boolean isLoop = true;
        while(isLoop) {
            System.out.println("Enter the option -- "
                               + "1-Employee Data\t"
                               + "2-Department Data\t"
                               + "3-Course Data\t"
                               + "4-Exit" );
            System.out.println("Enter your choice");
            int Choice = scanner.nextInt();
            try {
                switch(Choice) {
                    case 1:
                        if (departmentController.isDepartmentEmpty() || courseController.isCourseEmpty()) {
                            System.out.println("Plse add the Department and Course First \n");
                            mainFunction();
                            break;
                        } else {
                            employeeController.employeeFunction();
                        }
                        break;

                    case 2:
                        departmentController.departmentFunction();
                        break;

                    case 3:
                        courseController.courseFunction();
                        break;
  
                    case 4:
                        isLoop = false;
                        break;
                
                    default:
                        System.out.println("Enter the Correct input");
                        mainFunction();
                }
                } catch (EmployeeException e) {
                    System.out.println(e.getMessage());
                }
        }
    }
    public static void main(String[] args) {
        MainController mainController = new MainController();
        mainController.mainFunction();
    }
}

    