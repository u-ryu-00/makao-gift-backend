package kr.megaptera.makaogift.dtos;

public class InvalidNameLengthErrorDto extends ErrorDto {
    public InvalidNameLengthErrorDto() {
        super(1001, "이름을 다시 확인해주세요");
    }
}
