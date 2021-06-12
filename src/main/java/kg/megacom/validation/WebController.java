package kg.megacom.validation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class WebController implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }

   // @GetMapping("/results")
   // public String showResults(PersonForm personForm) {
   //     return "results";
   // }

    @GetMapping("/")
    public String showForm(PersonForm personForm) {
        return "form";
    }

    @PostMapping("/")
    public String checkPersonInfo(@Valid PersonForm personForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "form";
        }

        return "redirect:/results";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class,
                new DateEditor());
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        PersonForm personForm = new PersonForm();

        for (int i = 1; i <= 3; i++) {
            personForm.addPreviousJob(new PreviousJob());
        }

        model.addAttribute("personForm", personForm);
        return "form";
    }

    @GetMapping("/showAll")
    public  String showAllPersons(Model model){
        List<PreviousJob> previousJobList = new ArrayList<>();
        previousJobList.add(new PreviousJob("Hr"));
        previousJobList.add(new PreviousJob("SB"));
        List<PersonForm>  personFormList = new ArrayList<>();
        personFormList.add(new PersonForm("Max",22,previousJobList,true,"email",3,new Date(),new Date(),new Date(),"ipAddress"));
        personFormList.add(new PersonForm("Max",22,previousJobList,true,"email",3,new Date(),new Date(),new Date(),"ipAddress"));
        personFormList.add(new PersonForm("Max",22,previousJobList,true,"email",3,new Date(),new Date(),new Date(),"ipAddress"));
        personFormList.add(new PersonForm("Max",22,previousJobList,true,"email",3,new Date(),new Date(),new Date(),"ipAddress"));
        personFormList.add(new PersonForm("Max",22,previousJobList,true,"email",3,new Date(),new Date(),new Date(),"ipAddress"));
        personFormList.add(new PersonForm("Max",22,previousJobList,true,"email",3,new Date(),new Date(),new Date(),"ipAddress"));
        model.addAttribute("people",personFormList);
        return "personsList";
    }

}
