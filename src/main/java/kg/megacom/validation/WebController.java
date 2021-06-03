package kg.megacom.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Controller
public class WebController implements WebMvcConfigurer {

    @Size(max = 100)
    private List<PersonForm> personsForm;

    private WebController(List<PersonForm> personsForm){
        this.personsForm = personsForm;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }

    @GetMapping("/")
    public String showForm(PersonForm personForm) {
        return "form";
    }

    @GetMapping("/results")
    public String showResults(Model model) {
        model.addAttribute("persons", personsForm);
        return "results";
    }

    @PostMapping("/")
    public String checkPersonInfo(@Valid @ModelAttribute("personForm") PersonForm personForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "form";
        }
        personsForm.add(personForm);
        System.out.println(personForm.getName());
        return "redirect:/results";
    }

    @InitBinder                                         //Init Binder используется для завершения привязки полей формы к свойствам бинов
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class,
                new DateEditor());
    }



}
