package com.example.hypersoft.errormodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderErrorMessage {
    //private HttpStatus.Series status;
    private String description;
}
