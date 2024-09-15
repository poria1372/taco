package com.example.taco;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = false)
public class TacoOrder {
    private static final long serialVersionUID = 1l;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "deliveryName  نمی تواند خالی باشذ")
    private String deliveryName;
    @NotBlank(message = "deliveryStreet  نمی تواند خالی باشذ")
    private String deliveryStreet;
    @NotBlank(message = "deliveryCity  نمی تواند خالی باشذ")
    private String deliveryCity;
    @NotBlank(message = "deliveryState  نمی تواند خالی باشذ")
    private String deliveryState;
    @NotBlank(message = "deliveryZip  نمی تواند خالی باشذ")
    private String deliveryZip;
    @CreditCardNumber(message = "نامعتبر است")
    private String ccNumber;
    @Pattern(regexp = "^(0[1-9] | 1[0-2])([\\/])([2-9][0-9])$", message = "فرمت دهی نادرست است")
    private String ccExpiration;
    @Digits(integer = 3, fraction = 0, message = "نادرست است")
    private String ccCVV;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Taco> tacos = new ArrayList<>();

    public void addTaco(Taco taco) {
        this.tacos.add(taco);
    }
}
