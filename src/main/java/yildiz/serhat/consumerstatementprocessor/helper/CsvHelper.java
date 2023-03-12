package yildiz.serhat.consumerstatementprocessor.helper;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.http.HttpStatus;
import yildiz.serhat.consumerstatementprocessor.domain.model.CsvRecordModel;
import yildiz.serhat.consumerstatementprocessor.exception.FileNotValidException;

import java.io.Reader;
import java.util.List;

public class CsvHelper {
    private static final char CSV_COLUMN_SEPARATOR = ',';
    public static String CSV_TYPE = "text/csv";

    public static void isCSVFormat(String contentType) {
        if (!CSV_TYPE.equals(contentType)) {
            throw new FileNotValidException("File is not in CSV format", HttpStatus.BAD_REQUEST);
        }
    }

    public static List<CsvRecordModel> convertFromFileToRecord(Reader reader) {
        return new CsvToBeanBuilder<CsvRecordModel>(reader)
                .withSeparator(CSV_COLUMN_SEPARATOR)
                .withIgnoreQuotations(false)
                .withIgnoreLeadingWhiteSpace(true)
                .withType(CsvRecordModel.class)
                .build()
                .parse();
    }
}
