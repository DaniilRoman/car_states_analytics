package com.app.admin.controller;

import com.app.admin.api.ParameterApi;
import com.app.admin.api.model.CarParamMetaResponse;
import com.app.admin.api.model.ParamRequest;
import com.app.admin.data.characteristics.Parameter;
import com.app.admin.service.ParameterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ParameterController implements ParameterApi {

    private final ParameterService parameterService;

    @Override
    public ResponseEntity<CarParamMetaResponse> addParam(ParamRequest paramRequest) {
        Parameter newParameter = parameterService.addNewParameter(paramRequest);
        return ResponseEntity.ok().body(new CarParamMetaResponse()
                .paramId(newParameter.getId())
                .paramName(newParameter.getName())
                .paramType(newParameter.getTypeDescription()));
    }

    @Override
    public ResponseEntity<List<CarParamMetaResponse>> getParams() {
        return ResponseEntity.ok().body(parameterService.findAll().stream()
                .map(param -> new CarParamMetaResponse()
                        .paramId(param.getId())
                        .paramName(param.getName())
                        .paramType(param.getTypeDescription()))
                .collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<Void> deleteParamById(UUID parameterId) {
        parameterService.deleteParameterById(parameterId);
        return ResponseEntity.ok().build();
    }
}
