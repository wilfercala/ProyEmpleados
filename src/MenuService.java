import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

public class MenuService {
    final static Integer EXIT = 5;
    private final static Map<Integer, String> MENU = new HashMap<Integer, String>() {
        {
            put(1, "1. Agregar Empleado.");
            put(2, "2. Eliminar Empleado.");
            put(3, "3. Actualizar Empleado");
            put(4, "4. Mostrar todos los empleados.");
            put(5, "5. Salir.");
        }
    };

    protected static void printWelcomeMessage() {
        final String message = "Welcome";
        System.out.println(message);
        System.out.println();
    }

    protected static void printMenu() {
        EmployeeService.read();
        System.out.println("Menu: ");
        MENU.forEach((key, value) -> System.out.println("\t" + value));
        System.out.println();
    }

    protected static Integer captureOption() {
        System.out.print("Seleccione una opcion: ");
        try {
            Integer option = Util.getScanner().nextInt();
            validateOption(option);
            return option;
        } catch (InputMismatchException e) {
            System.err.println("Opcion invalida!");
            captureOption();
            return null;
        }
    }

    private static void validateOption(final Integer option) {
        if (!MENU.containsKey(option)) {
            System.err.println("Opcion invalida!");
            captureOption();
        }
    }

    protected static void processOption(final Integer option) {
        switch (option) {
            case 1:
                EmployeeService.create();
                break;
            case 2:
                EmployeeService.delete();
                break;
            case 3:
                EmployeeService.update();
                break;
            case 4:
                // EmployeeService.read();
                break;
            case 5:
                MenuService.exit();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + option);
        }
    }

    protected static void exit() {
        System.out.println("Hasta Pronto.");
        System.out.println();
    }

}
