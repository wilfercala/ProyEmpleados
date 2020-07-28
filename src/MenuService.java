import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

public class MenuService {

  final static Integer EXIT = 11;
  private final static Map<Integer, String> MENU = new HashMap<Integer, String>() {
    {
      put(1, " 1. Agregar Empleado.");
      put(2, " 2. Eliminar Empleado.");
      put(3, " 3. Actualizar Empleado");
      put(4, " 4. Mostrar todos los empleados.");
      put(5, " 5. Encontrar el empleado con mayor salario.");
      put(6, " 6. Encontrar el empleado con menor salario.");
      put(7, " 7. Ordenar los empleados por nombre.");
      put(8, " 8. Hallar la suma de los salarios de todos los empleados cuyo salario es mayor a 700000.");
      put(9, " 9. Determinar el número total de empleados cuyo apellido comienza por la letra ‘A’ o ‘a’.");
      put(10, "10. Los 5 primeros empleados con el mayor salario.");
      put(11, "11. Salir.");
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
        EmployeeService.findEmployeeMaxSalary();
        break;
      case 6:
        EmployeeService.findEmployeeMinSalary();
        break;
      case 7:
        EmployeeService.orderEmployeeByFirstName();
        break;
      case 8:
        EmployeeService.sumEmployeeSalary();
        break;
      case 9:
        EmployeeService.countEmployeeByLastName();
        break;
      case 10:
        EmployeeService.findEmployeeByMaxSalary();
        break;
      case 11:
        MenuService.exit();
        break;
    }
  }

  protected static void exit() {
    System.out.println("Good by.");
    System.out.println();
  }

}
