package org.Lab4;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
@org.springframework.web.bind.annotation.RestController
public class RestController {
    AddressBookRepository addressBookRepository;
    BuddyInfoRepository buddyInfoRepository;

    public RestController(AddressBookRepository addressBookRepository, BuddyInfoRepository buddyInfoRepository){
        this.addressBookRepository = addressBookRepository;
        this.buddyInfoRepository = buddyInfoRepository;
    }

    @GetMapping("/getAllBooksrest")
    public AllAddressBooksRecord getAddresses(Model m){
        m.addAttribute("Addresses", addressBookRepository.findAll().toString());
        return new AllAddressBooksRecord(addressBookRepository.findAll().toString());
    }

    @GetMapping("/getaddressbookrest")
    public AddressRecord getAddress(@RequestParam Integer id, Model model){
        Optional<AddressBook> a1 = addressBookRepository.findById(id);
        if (a1.isEmpty()) return null;
        model.addAttribute("AddressId", id);
        model.addAttribute("BuddyList", a1.get().getBuds());
        return new AddressRecord(id,a1.get().getBuds().toString());
    }

    @GetMapping("/createaddressbookrest")
    public AddressRecord createAdBook(Model m){
        AddressBook ab = new AddressBook();
        addressBookRepository.save(ab);
        m.addAttribute("AddressId", ab.getId());
        return new AddressRecord(ab.getId(), ab.getBuds().toString());
    }

    @PostMapping("/addbuddyrest")
    public AddressRecord addBuddy(@RequestParam Integer id, @RequestParam String name, @RequestParam String phone, @RequestParam String address, Model m){
        Optional<AddressBook> ab = addressBookRepository.findById(id);
        if(ab.isEmpty()) return null;
        AddressBook temp = ab.get();
        temp.addBuddy(new BuddyInfo(name,phone,address));
        addressBookRepository.save(temp);
        m.addAttribute("AddressId", id);
        m.addAttribute("BuddyList", temp.getBuds());

        return new AddressRecord(id, ab.get().getBuds().toString());
    }

    @DeleteMapping("/deletebuddyrest")
    public AddressRecord deleteBuddy(@RequestParam Integer abId, @RequestParam Integer budId, Model m){
        Optional<AddressBook> a1 = addressBookRepository.findById(abId);
        if(a1.isEmpty()) return null;
        AddressBook a = a1.get();
        Optional<BuddyInfo> b = buddyInfoRepository.findById(budId);
        if(b.isEmpty()) return null;
        a.removeBuddy(b.get());
        addressBookRepository.save(a);
        m.addAttribute("AddressId", abId);
        m.addAttribute("BuddyList", a1.get().getBuds());
        return new AddressRecord(abId, a1.get().getBuds().toString());
    }

    @DeleteMapping("/deletebookrest")
    public AllAddressBooksRecord deleteBuddy(@RequestParam Integer abId, Model m){
        Optional<AddressBook> a1 = addressBookRepository.findById(abId);
        if(a1.isEmpty()) return null;
        addressBookRepository.deleteById(abId);
        m.addAttribute("Addresses", addressBookRepository.findAll().toString());
        return new AllAddressBooksRecord(addressBookRepository.findAll().toString());
    }
}
