package root;

import controller.BudgetAppController;

public class BudgetApplication {
    public static void main(String[] args) {
        BudgetAppController bac = new BudgetAppController();
        bac.run();
    }
}
