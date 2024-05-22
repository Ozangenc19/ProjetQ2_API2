package designpattern.builder;



public class Test {
    public static void main(String[] args) {
        try {
            Employe e1 = new Employe.EmployeBuilder().setMatricule("A400").setNom("Dept").setPrenom("Mathilde").setTel("494/06/22/69").setMail("Mathildedept@gmail.com").Build();
            System.out.println(e1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            Employe e2 = new Employe.EmployeBuilder().setMatricule("A400").setNom("Dept").setPrenom("Mathilde").Build();
            System.out.println(e2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            Employe e3 = new Employe.EmployeBuilder().setMatricule("A400").setPrenom("Mathilde").Build();
            System.out.println(e3);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}