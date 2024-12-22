package com.product.management.product.dtos;

public class UpdateStatusDTO {
    private Boolean status;

    public UpdateStatusDTO(Boolean status) {
        this.status = status;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UpdateStatusDTO{" +
                "status=" + status +
                '}';
    }
}
