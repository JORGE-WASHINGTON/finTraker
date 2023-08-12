package jwom.fintrak.budget;

import jwom.fintrak.Model.User;

public interface BudgetService {
    void createBudget(CreateBudgetRequest request, User user);

}
