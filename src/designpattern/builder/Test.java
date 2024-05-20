package designpattern.builder;



public class Test {
    public static void main(String[] args) {
        try {
            Employe e1 = new Employe.EmployeBuilder().setMatricule("ABC45").setNom("Dupont").setPrenom("Jean").Build();
            System.out.println(e1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            Employe e1 = new Employe.EmployeBuilder().setMatricule("ABC45").setNom("Dupont").setPrenom("Jean").Build();
            System.out.println(e1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            Employe e2 = new Employe.EmployeBuilder().setMatricule("ABC45").setPrenom("Jean").Build();
            System.out.println(e2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}