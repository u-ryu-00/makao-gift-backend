package kr.megaptera.makaogift.dtos;

public class PasswordDoNotMatchErrorDto extends ErrorDto {
    public PasswordDoNotMatchErrorDto() {
        super(1004, "비밀번호가 일치하지 않습니다");
    }
}
