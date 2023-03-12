package yildiz.serhat.consumerstatementprocessor.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import yildiz.serhat.consumerstatementprocessor.controller.request.RecordRequestDTO;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Record {

    private String reference;
    private String accountNumber;
    private String description;
    private BigDecimal startBalance;
    private BigDecimal mutation;
    private BigDecimal endBalance;

    public static Record buildRecord(CsvRecordModel csvRecordModel) {
        return Record.builder()
                .accountNumber(csvRecordModel.getAccountNumber())
                .reference(csvRecordModel.getReference())
                .description(csvRecordModel.getDescription())
                .startBalance(new BigDecimal(csvRecordModel.getStartBalance()))
                .endBalance(new BigDecimal(csvRecordModel.getEndBalance()))
                .mutation(new BigDecimal(csvRecordModel.getMutation()))
                .build();
    }

    public static Record buildRecord(RecordRequestDTO recordRequestDTO) {
        return Record.builder()
                .accountNumber(recordRequestDTO.getAccountNumber())
                .reference(recordRequestDTO.getReference())
                .description(recordRequestDTO.getDescription())
                .startBalance(new BigDecimal(recordRequestDTO.getStartBalance()))
                .endBalance(new BigDecimal(recordRequestDTO.getEndBalance()))
                .mutation(new BigDecimal(recordRequestDTO.getMutation()))
                .build();
    }
}
