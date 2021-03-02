package br.com.southsystem.dto.input;

import io.swagger.annotations.ApiModelProperty;

public class ScheduleInput {

    @ApiModelProperty(example = "Novo Fundo de Investimentos", required = true)
    private String name;

    @ApiModelProperty(example = "Criação de um novo fundo de investimento", required = true)
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
