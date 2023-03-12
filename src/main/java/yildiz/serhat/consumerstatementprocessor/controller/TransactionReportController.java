package yildiz.serhat.consumerstatementprocessor.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import yildiz.serhat.consumerstatementprocessor.controller.request.RecordRequestDTO;
import yildiz.serhat.consumerstatementprocessor.service.TransactionService;

import java.util.List;

@RestController
@RequestMapping("/v1/transactions")
@Tag(name = "transaction-report", description = "Get Report from a given transactions")
@RequiredArgsConstructor
public class TransactionReportController {

    private final TransactionService transactionService;

    @PostMapping(value = "/reports", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Endpoint for CSV files.")
    public ResponseEntity<?> validateTransactionByCSV(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(transactionService.getReportForTransactionByCSV(file));
    }

    @PostMapping(value = "/reports", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Endpoint for XML files.")
    public ResponseEntity<?> validateTransactionByXML(@RequestBody List<RecordRequestDTO> records) {
        return ResponseEntity.ok(transactionService.getReportForTransactionByXML(records));
    }
}
