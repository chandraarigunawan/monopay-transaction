package com.monopay.wallet.validation.validator;

import com.monopay.wallet.repository.BalanceRepository;
import com.monopay.wallet.validation.MemberMustExists;
import com.monopay.wallet.validation.data.MemberData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MemberMustExistsValidator implements ConstraintValidator<MemberMustExists, MemberData> {

  @Autowired
  private BalanceRepository balanceRepository;

  @Override
  public boolean isValid(MemberData value, ConstraintValidatorContext context) {
    if (StringUtils.isEmpty(value.getMemberId()) || StringUtils.isEmpty(value.getMerchantId())) {
      return true;
    } else {
      return balanceRepository.findByIdAndMerchantId(value.getMemberId(), value.getMerchantId()) != null;
    }
  }
}
