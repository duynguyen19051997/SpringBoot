package duynguyen.jpatransaction.controller;

import duynguyen.jpatransaction.dao.BankAccountDAO;
import duynguyen.jpatransaction.entity.BankAccount;
import duynguyen.jpatransaction.exception.BankAccountException;
import duynguyen.jpatransaction.model.BankAccountInfo;
import duynguyen.jpatransaction.model.SendMoney;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/update/{id}")
    public String updateBankAccount(ModelMap modelMap, @PathVariable("id") Integer id) {
        try {
            BankAccount acc = bankAccountDAO.findById(id);
            modelMap.addAttribute("bankAccount", acc);
            return "updateBankPage";
        } catch (NumberFormatException e) {
            return "redirect:/show";
        }
    }

    @PostMapping("/update{id}")
    public String processUpdateBankAccount(@ModelAttribute("bankAccount") BankAccount acc) {
        if (bankAccountDAO.updateById(acc) > 0) {
            System.out.println("Update success");
            return "redirect:/show";
        }
        System.out.println("Update fail");
        return "updateBankPage";
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
        System.out.println("Send Money: " + sm.getAmount() + " from " + sm.getFromId() + " to " + sm.getToId());
        try {
            bankAccountDAO.sendMoney(sm.getFromId(), sm.getToId(), sm.getAmount());
        } catch (BankAccountException e) {
            modelMap.addAttribute("errorMessage", "Error: " + e.getMessage());
            return "/sendMoneyPage";
        }
        return "redirect:/show";
    }

    @GetMapping("/delete/{id}")
    public String deleteBankAccount(@PathVariable("id") int id, ModelMap modelMap) {
        BankAccount acc = bankAccountDAO.findById(id);
        if (acc != null && bankAccountDAO.deleteOneById(acc.getId()) > 0) {
            System.out.println("Delete success" );
            return "redirect:/show";
        }
        System.out.println("Delete fail");
        return "redirect:/show";
    }
}
