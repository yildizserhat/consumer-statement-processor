package yildiz.serhat.consumerstatementprocessor.controller.request;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@XmlRootElement
@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class RecordRequestDTO {
    private String reference;
    private String accountNumber;
    private String description;
    private String startBalance;
    private String mutation;
    private String endBalance;
}
