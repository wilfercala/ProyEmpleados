import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Objects;

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
        System.out.println("El empleado se ha creado satisfactoriamente.");
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
            System.out.println("El empleado se ha actualizado satisfactoriamente.");
        } else {
            System.out.println("No existe un empleado con este identificador.");
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
}
