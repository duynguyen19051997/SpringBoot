package duynguyen.jpatransaction.controller;

import duynguyen.jpatransaction.dao.BankAccountDAO;
import duynguyen.jpatransaction.exception.BankAccountException;
import duynguyen.jpatransaction.model.BankAccountInfo;
import duynguyen.jpatransaction.model.SendMoney;
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
    public String showBankAccount(ModelMap modelMap) {
        List<BankAccountInfo> list = bankAccountDAO.findAll();
        modelMap.addAttribute("accountInfos", list);
        return "accountPage";
    }

    @GetMapping("/send-money")
    public String sendMoney(ModelMap modelMap) {
        List<BankAccountInfo> list = bankAccountDAO.findAll();
        modelMap.addAttribute("accountInfos", list);
        modelMap.addAttribute("sendMoney", new SendMoney());
        return "sendMoneyPage";
    }

    @PostMapping("/send-money")
    public String processMoney(@ModelAttribute("sendMoney") SendMoney sm,
                               ModelMap modelMap) {
        System.out.println("Send Money: " + sm.getAmount());

        try {
            bankAccountDAO.sendMoney(sm.getFromId(), //
                sm.getToId(), //
                sm.getAmount());
        } catch (BankAccountException e) {
            modelMap.addAttribute("errorMessage", "Error: " + e.getMessage());
            return "/sendMoneyPage";
        }
        return "redirect:/show";
    }

}
