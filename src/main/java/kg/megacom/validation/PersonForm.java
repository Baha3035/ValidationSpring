package kg.megacom.validation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonForm {
    @NotNull
    @Size(min=2, max=30, message = "Имя должно содержать не менее 2 и не более 30 символов")
    private String name;

    @NotNull(message = "Имя не может быть пустым")
    @Min(18)
    private Integer age;

    private List<PreviousJob> previousJobs;

    @AssertTrue
    private boolean hasDegree;

    @NotEmpty
    @Email()
    private String email;

    @Min(1)
    @Max(5)
    @Positive
    private int experience;

    @Past
    private Date carierStart;

    @Future
    private Date endContract;

    @PastOrPresent
    private Date birthday;

    public Date getBirthday() {
        return birthday;
    }

    public void addPreviousJob(PreviousJob job) {
        this.previousJobs.add(job);
    }


    @NotBlank
    @Pattern(regexp = "^[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}$")
    private String ipAddress;


    public String toString() {
        return "Person(Name: " + this.name + ", Age: " + this.age + ")";
    }
}
