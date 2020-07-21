import java.util.Objects;

public class EmployeeManager {

    public static void main(String args[]) {
        MenuService.printWelcomeMessage();
        Integer optionSelected = null;
        while (!Objects.equals(optionSelected, MenuService.EXIT)) {
            MenuService.printMenu();
            optionSelected = MenuService.captureOption();
            MenuService.processOption(optionSelected);
        }
    }
}
