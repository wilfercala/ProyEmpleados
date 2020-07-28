import java.util.*;
import java.util.stream.Collectors;

public class EmployeeService {

  private static Map<Integer, Employee> employees = new HashMap<>();

  public static void create() {
    System.out.println("Crear Empleado");
    System.out.println("Ingrese la siguiente informacion:");
    System.out.println();
    Integer id = captureID();
    while (employees.containsKey(id)) {
      System.out.println("Existe un registro con ese ID.");
      id = captureID();
    }
    Employee employee = new Employee(
        id,
        captureFirstName(),
        captureLastName(),
        captureSalary()
    );
    employees.put(employee.getId(), employee);
    System.out.println();
    System.out.println("El cliente se ha creado satisfactoriamente.");
    System.out.println();
  }

  public static void update() {
    System.out.println("Actualizar Empleado");
    System.out.println("Ingrese el ID del Empleado: ");
    Integer id = captureID();
    if (employees.containsKey(id)) {
      Employee employee = employees.get(id);
      System.out.println(employee.toString());
      employee.setFirstName(captureFirstName());
      employee.setLastName(captureLastName());
      employee.setSalary(captureSalary());
      employees.put(employee.getId(), employee);
      System.out.println();
      System.out.println("El cliente se ha actualizado satisfactoriamente.");
    } else {
      System.out.println("No existe un cliente con este identificador.");
    }
    System.out.println();
  }

  public static void delete() {
    System.out.println("Eliminar Empleado");
    System.out.println("Ingrese el ID del Empleado: ");
    Integer id = captureID();
    if (employees.containsKey(id)) {
      employees.remove(id);
      System.out.println();
      System.out.println("El cliente se ha eliminado satisfactoriamente.");
    } else {
      System.out.println("No existe un cliente con este identificador.");
    }
    System.out.println();
  }

  public static void read() {
    System.out.println("Empleados: ");
    if (employees.isEmpty())
      System.out.println("No existen registros de empleados. Puede crear uno seleccionando la opcion [1] del menu.");
    else
      employees.forEach((key, value) -> System.out.println("\t" + value.toString()));
    System.out.println();
  }

  public static void findEmployeeMaxSalary() {
    System.out.println("Max salary: ");
    if (employees.isEmpty())
      System.out.println("No existen registros de empleados. Puede crear uno seleccionando la opcion [1] del menu.");
    else {
      Employee record = getEmployees()
          .stream()
          .max(Comparator.comparing(Employee::getSalary))
          .orElseThrow(NoSuchElementException::new);
      System.out.println(record.toString());
    }
    System.out.println();
  }

  public static void findEmployeeMinSalary() {
    System.out.println("Min salary: ");
    if (employees.isEmpty())
      System.out.println("No existen registros de empleados. Puede crear uno seleccionando la opcion [1] del menu.");
    else {
      Employee record = getEmployees()
          .stream()
          .min(Comparator.comparing(Employee::getSalary)).orElseThrow(NoSuchElementException::new);
      System.out.println(record.toString());
    }
    System.out.println();
  }

  public static void orderEmployeeByFirstName() {
    System.out.println("Ordered: ");
    if (employees.isEmpty())
      System.out.println("No existen registros de empleados. Puede crear uno seleccionando la opcion [1] del menu.");
    else {
      Comparator<Employee> compareByFirstName = Comparator.comparing(Employee::getFirstName);
      List<Employee> list = getEmployees()
          .stream()
          .sorted(compareByFirstName)
          .collect(Collectors.toList());
      list.forEach(System.out::println);
    }
    System.out.println();
  }

  public static void sumEmployeeSalary() {
    System.out.println("Sum: ");
    if (employees.isEmpty())
      System.out.println("No existen registros de empleados. Puede crear uno seleccionando la opcion [1] del menu.");
    else {
      boolean seen = false;
      double acc = 0;
      for (Employee e : getEmployees()) {
        if (e.getSalary() > 7000000) {
          double salary = e.getSalary();
          if (!seen) {
            seen = true;
            acc = salary;
          } else {
            acc = acc + salary;
          }
        }
      }
      Double sumSalary = (seen ? OptionalDouble.of(acc) : OptionalDouble.empty())
          .getAsDouble();
      System.out.println(sumSalary);
    }
    System.out.println();
  }

  public static void countEmployeeByLastName() {
    System.out.println("Count: ");
    if (employees.isEmpty())
      System.out.println("No existen registros de empleados. Puede crear uno seleccionando la opcion [1] del menu.");
    else {
      Long count = getEmployees()
          .stream()
          .filter(e -> Objects.equals(e.getLastName().charAt(0), 'A') || Objects.equals(e.getLastName().charAt(0), 'a'))
          .count();
      System.out.println(count);
    }
    System.out.println();
  }

  public static void findEmployeeByMaxSalary() {
    System.out.println("Ordered: ");
    if (employees.isEmpty())
      System.out.println("No existen registros de empleados. Puede crear uno seleccionando la opcion [1] del menu.");
    else {
      getEmployees()
          .stream()
          .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
          .limit(5)
          .forEach((e) -> System.out.println(e));
    }
    System.out.println();
  }

  private static Integer captureID() {
    System.out.println("ID del empleado: ");
    try {
      Integer id = Util.getScanner().nextInt();
      validateID(id);
      return id;
    } catch (InputMismatchException e) {
      System.err.println("ID invalido!");
      captureID();
      return null;
    }
  }

  private static void validateID(final Integer salary) {
    if (Objects.isNull(salary)) {
      System.err.println("Apellido invalido!");
      captureID();
    }
  }

  private static String captureFirstName() {
    System.out.println("Nombre del empleado: ");
    try {
      String name = Util.getScanner().nextLine();
      validateFirstName(name);
      return name;
    } catch (InputMismatchException e) {
      System.err.println("Nombre invalido!");
      captureFirstName();
      return null;
    }
  }

  private static void validateFirstName(final String firstName) {
    if (Objects.isNull(firstName)) {
      System.err.println("Nombre invalido!");
      captureFirstName();
    }
  }

  private static String captureLastName() {
    System.out.println("Apellido del empleado: ");
    try {
      String name = Util.getScanner().nextLine();
      validateLastName(name);
      return name;
    } catch (InputMismatchException e) {
      System.err.println("Apellido invalido!");
      captureLastName();
      return null;
    }
  }

  private static void validateLastName(final String lastName) {
    if (Objects.isNull(lastName)) {
      System.err.println("Apellido invalido!");
      captureLastName();
    }
  }

  private static Double captureSalary() {
    System.out.println("Salario del empleado: ");
    try {
      Double salary = Util.getScanner().nextDouble();
      validateSalary(salary);
      return salary;
    } catch (InputMismatchException e) {
      System.err.println("Salario invalido!");
      captureSalary();
      return null;
    }
  }

  private static void validateSalary(final Double salary) {
    if (Objects.isNull(salary)) {
      System.err.println("Apellido invalido!");
      captureSalary();
    }
  }

  private static List<Employee> getEmployees() {
    if (employees.isEmpty())
      return new ArrayList<>();
    else
      return employees.values().stream().collect(Collectors.toList());
  }

}
