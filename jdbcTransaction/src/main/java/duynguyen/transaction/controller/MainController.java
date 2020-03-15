package duynguyen.transaction.controller;

import duynguyen.transaction.dao.BankAccountDAO;
import duynguyen.transaction.exception.BankTransactionException;
import duynguyen.transaction.model.BankAccountInfor;
import duynguyen.transaction.model.SendMoneyForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/send")
    public String viewSendMoney(ModelMap modelMap) {
        modelMap.addAttribute("sendMoneyForm", new SendMoneyForm());
        return "sendMoney";
    }

    @PostMapping("/send")
    public String processSendMoney(ModelMap modelMap, @ModelAttribute("sendMoneyForm") SendMoneyForm smf) {
        System.out.println(smf.getFromAccountId() +
            " send " + smf.getToAccountId() + " " + smf.getAmount());
        try {
            bankAccountDAO.sendAmount(smf.getFromAccountId(),smf.getToAccountId(), smf.getAmount());
        } catch (BankTransactionException e) {
            modelMap.addAttribute("errorMessage", e.getMessage());
            return "/send";
        }
        return "redirect:/show";
    }
}
