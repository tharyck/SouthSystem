package br.com.southsystem.dto.input;

import br.com.southsystem.exceptions.ErrorCodes;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.br.CPF;

public class AssociatedInput {

    @ApiModelProperty(example = "Jose Da Silva", required = true)
    private String name;

    @CPF(message = ErrorCodes.CPF_INVALID)
    @ApiModelProperty(example = "950.213.940-29")
    private String cpf;

    @ApiModelProperty(example = "83999887766", required = true)
    private String phone;

    @ApiModelProperty(example = "Rua da Acacia Amarela", required = true)
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
