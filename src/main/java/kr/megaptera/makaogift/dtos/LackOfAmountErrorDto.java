package kr.megaptera.makaogift.dtos;

public class LackOfAmountErrorDto extends ErrorDto {
    public LackOfAmountErrorDto() {
        super(1001, "❌잔액이 부족하여 선물하기가 불가합니다❌");
    }
}
