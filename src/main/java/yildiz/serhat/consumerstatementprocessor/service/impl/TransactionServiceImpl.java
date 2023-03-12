package yildiz.serhat.consumerstatementprocessor.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import yildiz.serhat.consumerstatementprocessor.controller.request.RecordRequestDTO;
import yildiz.serhat.consumerstatementprocessor.controller.response.RecordResponseDTO;
import yildiz.serhat.consumerstatementprocessor.domain.model.CsvRecordModel;
import yildiz.serhat.consumerstatementprocessor.domain.model.Record;
import yildiz.serhat.consumerstatementprocessor.domain.model.Report;
import yildiz.serhat.consumerstatementprocessor.helper.CsvHelper;
import yildiz.serhat.consumerstatementprocessor.service.TransactionService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    @Override
    public RecordResponseDTO getReportForTransactionByCSV(MultipartFile file) {

        validateFile(file);
        List<Record> records = new ArrayList<>();
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            List<CsvRecordModel> csvRecords = CsvHelper.convertFromFileToRecord(reader);
            records = csvRecords.stream().map(Record::buildRecord).toList();
        } catch (Exception ex) {
            log.error("Exception occurred: {}", ex.getMessage());
        }

        if (records.isEmpty()) {
            return RecordResponseDTO.builder()
                    .title("List is empty")
                    .build();
        }

        return buildResponse(records);
    }

    @Override
    public RecordResponseDTO getReportForTransactionByXML(List<RecordRequestDTO> recordRequests) {
        if (recordRequests.isEmpty()) {
            return RecordResponseDTO.builder()
                    .title("List is empty")
                    .build();
        }
        return buildResponse(recordRequests.stream()
                .map(Record::buildRecord)
                .collect(Collectors.toList()));
    }

    private RecordResponseDTO<Object> buildResponse(List<Record> records) {
        Set<Report> invalidReferences = validateReferences(records);
        Set<Report> invalidMutations = validateMutations(records);
        Map<String, Set<Report>> hash = new HashMap<>();

        hash.put("Invalid References", invalidReferences);
        hash.put("Invalid Mutations", invalidMutations);
        return RecordResponseDTO.builder()
                .title("Report is created.")
                .object(hash).build();
    }

    private void validateFile(MultipartFile file) {
        CsvHelper.isCSVFormat(file.getContentType());
    }

    private Set<Report> validateMutations(List<Record> recordRequestDTO) {
        return recordRequestDTO.stream().filter(record -> !record.getEndBalance().equals(record.getStartBalance().add(record.getMutation())))
                .map(record -> new Report(record.getReference(), record.getDescription()))
                .collect(Collectors.toSet());
    }

    private Set<Report> validateReferences(List<Record> recordRequestDTO) {
        Set<String> invalidReferences = new HashSet<>();
        return recordRequestDTO.stream()
                .filter(reference -> !invalidReferences.add(reference.getReference()))
                .map(record -> new Report(record.getReference(), record.getDescription()))
                .collect(Collectors.toSet());
    }
}