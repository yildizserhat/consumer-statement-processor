package yildiz.serhat.consumerstatementprocessor.service;

import org.springframework.web.multipart.MultipartFile;
import yildiz.serhat.consumerstatementprocessor.controller.request.RecordRequestDTO;
import yildiz.serhat.consumerstatementprocessor.controller.response.RecordResponseDTO;

import java.util.List;

public interface TransactionService {

    RecordResponseDTO getReportForTransactionByCSV(MultipartFile file);

    RecordResponseDTO getReportForTransactionByXML(List<RecordRequestDTO> records);
}
