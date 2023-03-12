package yildiz.serhat.consumerstatementprocessor.domain.model;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CsvRecordModel {
    @CsvBindByName
    private String reference;
    @CsvBindByName
    private String accountNumber;
    @CsvBindByName
    private String description;
    @CsvBindByName
    private String startBalance;
    @CsvBindByName
    private String mutation;
    @CsvBindByName
    private String endBalance;
}
