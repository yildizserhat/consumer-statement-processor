package yildiz.serhat.consumerstatementprocessor.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class RecordResponseDTO<T> {
    private String title;
    private T object;
}
