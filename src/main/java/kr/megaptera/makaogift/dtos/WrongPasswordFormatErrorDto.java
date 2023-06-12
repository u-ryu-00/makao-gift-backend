package kr.megaptera.makaogift.dtos;

public class WrongPasswordFormatErrorDto extends ErrorDto {
    public WrongPasswordFormatErrorDto() {
        super(1003, "비밀번호를 다시 확인해주세요");
    }
}
