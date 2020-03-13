package duynguyen.transaction.controller;

import duynguyen.transaction.dao.BankAccountDAO;
import duynguyen.transaction.model.BankAccountInfor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    private BankAccountDAO bankAccountDAO;

    @GetMapping("/show")
    public String accoutPage(ModelMap modelMap) {
        List<BankAccountInfor> list = bankAccountDAO.getBankAccounts();
        System.out.println(list);
        modelMap.addAttribute("listOfAccounts", list);
        return "accountPage";
    }
}
