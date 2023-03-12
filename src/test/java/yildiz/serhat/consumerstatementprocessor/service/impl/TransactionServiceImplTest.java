package yildiz.serhat.consumerstatementprocessor.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import yildiz.serhat.consumerstatementprocessor.controller.request.RecordRequestDTO;
import yildiz.serhat.consumerstatementprocessor.controller.response.RecordResponseDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Test
    void shouldGetReferenceNumberIfMultipleReferenceExist() {
        List<RecordRequestDTO> requestDTOList = new ArrayList<>();
        RecordRequestDTO record1 = new RecordRequestDTO("187997", "NL91RABO0315273637", "Clothes for Rik King", "57.6", "-32.98", "24.62");
        RecordRequestDTO record2 = new RecordRequestDTO("187997", "NL91RABO0315273633", "Clothes for Rik", "57.6", "-32.98", "24.62");
        requestDTOList.add(record1);
        requestDTOList.add(record2);

        RecordResponseDTO reportForTransactionByXML = transactionService.getReportForTransactionByXML(requestDTOList);

        Map<String, Set<String>> object = (Map<String, Set<String>>) reportForTransactionByXML.getObject();
        Set<String> invalidReferences = object.get("Invalid References");
        Set<String> invalidMutations = object.get("Invalid Mutations");

        assertFalse(invalidReferences.isEmpty());
        assertTrue(invalidMutations.isEmpty());
    }

    @Test
    void shouldGetReferenceNumberIfMutationIsIncorrect() {
        List<RecordRequestDTO> requestDTOList = new ArrayList<>();
        RecordRequestDTO record1 = new RecordRequestDTO("187998", "NL91RABO0315273637", "Clothes for Rik King", "10", "-10", "5");
        RecordRequestDTO record2 = new RecordRequestDTO("187997", "NL91RABO0315273633", "Clothes for Rik", "57.6", "32.98", "24.62");
        requestDTOList.add(record1);
        requestDTOList.add(record2);

        RecordResponseDTO reportForTransactionByXML = transactionService.getReportForTransactionByXML(requestDTOList);

        Map<String, Set<String>> object = (Map<String, Set<String>>) reportForTransactionByXML.getObject();
        Set<String> invalidMutations = object.get("Invalid Mutations");
        Set<String> invalidReferences = object.get("Invalid References");

        assertTrue(invalidReferences.isEmpty());
        assertFalse(invalidMutations.isEmpty());
    }

}