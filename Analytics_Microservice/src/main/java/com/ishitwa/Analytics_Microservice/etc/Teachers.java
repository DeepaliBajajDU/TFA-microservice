package com.ishitwa.Analytics_Microservice.etc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teachers {
    private List<Long> teachers;
}
