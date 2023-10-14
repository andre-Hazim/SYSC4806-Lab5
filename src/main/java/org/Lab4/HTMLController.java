package org.Lab4;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;


@org.springframework.stereotype.Controller
public class HTMLController {
    AddressBookRepository addressBookRepository;
    BuddyInfoRepository buddyInfoRepository;

    public HTMLController(AddressBookRepository addressBookRepository, BuddyInfoRepository buddyInfoRepository){
        this.addressBookRepository = addressBookRepository;
        this.buddyInfoRepository = buddyInfoRepository;
    }

    @GetMapping("/getAllBooks")
    public String getAddresses(Model m){
        m.addAttribute("Addresses", addressBookRepository.findAll().toString());
        return "allAddresses";
    }

    @GetMapping("/getaddressbook")
    public String getAddress(@RequestParam Integer id,  Model model){
        Optional<AddressBook> a1 = addressBookRepository.findById(id);
        if (a1.isEmpty()) return null;
        model.addAttribute("AddressId", id);
        model.addAttribute("BuddyList", a1.get().getBuds());
        return "addressBookView";
    }

    @GetMapping("/createaddressbook")
    public String createAdBook(Model m){
        AddressBook ab = new AddressBook();
        addressBookRepository.save(ab);
        m.addAttribute("AddressId", ab.getId());
        return "createAddressBook";
    }

    @PostMapping("/addbuddy")
    public String addBuddy(@RequestParam Integer id, @RequestParam String name, @RequestParam String phone, Model m){
        Optional<AddressBook> ab = addressBookRepository.findById(id);
        if(ab.isEmpty()) return null;
        AddressBook temp = ab.get();
        temp.addBuddy(new BuddyInfo(name,phone));
        addressBookRepository.save(temp);
        m.addAttribute("AddressId", id);
        m.addAttribute("BuddyList", temp.getBuds());

        return "addressBookView";
    }

    @DeleteMapping("/deletebuddy")
    public String deleteBuddy(@RequestParam Integer abId, @RequestParam Integer budId, Model m){
        Optional<AddressBook> a1 = addressBookRepository.findById(abId);
        if(a1.isEmpty()) return null;
        AddressBook a = a1.get();
        Optional<BuddyInfo> b = buddyInfoRepository.findById(budId);
        if(b.isEmpty()) return null;
        a.removeBuddy(b.get());
        addressBookRepository.save(a);
        m.addAttribute("AddressId", abId);
        m.addAttribute("BuddyList", a1.get().getBuds());
        return "addressBookView";
    }

    @DeleteMapping("/deletebook")
    public String deleteBuddy(@RequestParam Integer id, Model m){
        Optional<AddressBook> a1 = addressBookRepository.findById(id);
        if(a1.isEmpty()) return null;
        addressBookRepository.deleteById(id);
        m.addAttribute("Addresses", addressBookRepository.findAll().toString());
        return "allAddresses";
    }
}

